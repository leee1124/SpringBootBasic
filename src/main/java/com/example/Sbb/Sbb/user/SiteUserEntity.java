package com.example.Sbb.Sbb.user;

import com.example.Sbb.Sbb.question.QuestionEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SiteUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;

    public SiteUserDTO toDTO(){
        return SiteUserDTO.builder()
                .id(id)
                .username(username)
                .password(password)
                .email(email)
                .build();
    }
}
