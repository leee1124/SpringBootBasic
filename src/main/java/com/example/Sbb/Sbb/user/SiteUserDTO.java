package com.example.Sbb.Sbb.user;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SiteUserDTO {
    private Long id;
    private String username;
    private String password;
    private String email;

    public SiteUserEntity toEntity(){
        return SiteUserEntity.builder()
                .id(id)
                .username(username)
                .password(password)
                .email(email)
                .build();
    }
}
