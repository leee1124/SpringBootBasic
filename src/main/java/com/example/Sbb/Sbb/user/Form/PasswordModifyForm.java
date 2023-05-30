package com.example.Sbb.Sbb.user.Form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class PasswordModifyForm {
    @NotEmpty(message = "변경할 비밀번호는 필수입니다.")
    private String password;

    @NotEmpty(message = "비밀번호 확인은 필수입니다.")
    private String passwordConfirm;

    @NotEmpty(message = "이전 비밀번호는 필수입니다.")
    private String beforePassword;
}
