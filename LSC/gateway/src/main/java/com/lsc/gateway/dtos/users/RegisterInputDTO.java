package com.lsc.gateway.dtos.users;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RegisterInputDTO {
    private String email;
    private String password;
    private String confirmPassword;
}
