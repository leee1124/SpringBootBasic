package com.example.Sbb.Sbb.Service;

import com.example.Sbb.Sbb.Entity.SiteUserEntity;
import com.example.Sbb.Sbb.Repository.UserRepository;
import com.example.Sbb.Sbb.Role.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * UserSecurityService는 UserDetailsService 인터페이스의 구현체임
 * 스프링 시큐리티의 USerDetailsService는 loadUserByUsername을 구현해야 함
 * loadUserByUsername 메서드는 사용자명으로 비밀번호를 조회하여 리턴하는 메서드임
 */
@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<SiteUserEntity> _siteuser = this.userRepository.findByUsername(username);
        if(_siteuser.isEmpty()){
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
        SiteUserEntity siteUser = _siteuser.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        if("admin".equals(username)){
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
        }else{
            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
        }
        return new User(siteUser.getUsername(), siteUser.getPassword(), authorities);
    }
}
