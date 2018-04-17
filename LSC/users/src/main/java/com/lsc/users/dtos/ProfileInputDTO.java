package com.lsc.users.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProfileInputDTO {
    private String name;
    private String password;
    private String confirmPassword;
    private String currentPassword;
}
