package com.example.Sbb.Sbb.user.Form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserCreateForm {
    @Size(min=3, max=25)
    @NotEmpty(message = "사용자 ID는 필수입니다.")
    private String username;

    @NotEmpty(message = "비밀번호는 필수입니다.")
    private String password;

    @NotEmpty(message = "비밀번호 확인은 필수입니다.")
    private String passwordConfirm;

    @NotEmpty(message = "E-mail은 필수입니다.")
    @Email
    private String email;

}
