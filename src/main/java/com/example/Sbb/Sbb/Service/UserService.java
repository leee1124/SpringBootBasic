package com.example.Sbb.Sbb.Service;

import com.example.Sbb.Sbb.DataNotFoundException;
import com.example.Sbb.Sbb.Entity.SiteUserEntity;
import com.example.Sbb.Sbb.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUserEntity create(String username, String email, String password){
        SiteUserEntity siteUserEntity = new SiteUserEntity();
        siteUserEntity.setUsername(username);
        siteUserEntity.setEmail(email);

        //패스워드는 암호화해서 저장
        siteUserEntity.setPassword(passwordEncoder.encode(password));

        this.userRepository.save(siteUserEntity);
        return siteUserEntity;
    }

    /**
     * principal객체를 사용하면 로그인한 사용자명을 알 수 있음
     * User 서비스를 통해 사용자를 조회하는 메소드
     * @param username
     * @return
     */
    public SiteUserEntity getUser(String username){
        Optional<SiteUserEntity> siteUser = this.userRepository.findByUsername(username);
        if(siteUser.isPresent()){
            return siteUser.get();
        }else{
            throw new DataNotFoundException("siteuser not found");
        }
    }
}
