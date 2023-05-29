package com.example.Sbb.Sbb.user.Service;

import com.example.Sbb.Sbb.DataNotFoundException;
import com.example.Sbb.Sbb.user.Data.SiteUserDTO;
import com.example.Sbb.Sbb.user.Data.SiteUserEntity;
import com.example.Sbb.Sbb.user.Data.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUserDTO create(String username, String email, String password){
        SiteUserDTO siteUserDTO = new SiteUserDTO();
        siteUserDTO.setUsername(username);
        siteUserDTO.setEmail(email);

        //패스워드는 암호화해서 저장
        siteUserDTO.setPassword(passwordEncoder.encode(password));
        this.userRepository.save(siteUserDTO.toEntity());

        return siteUserDTO;
    }

    /**
     * principal객체를 사용하면 로그인한 사용자명을 알 수 있음
     * User 서비스를 통해 사용자를 조회하는 메소드
     * @param username
     * @return
     */
    public SiteUserDTO getUser(String username){
        SiteUserEntity siteUser = this.userRepository.findByUsername(username).orElseThrow(()->new DataNotFoundException("siteuser not found"));
        SiteUserDTO siteUserDTO = new SiteUserDTO(siteUser.getId(), siteUser.getUsername(), siteUser.getPassword(), siteUser.getEmail());
        return siteUserDTO;
    }
}
