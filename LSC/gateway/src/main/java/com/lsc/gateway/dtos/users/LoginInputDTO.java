package com.lsc.gateway.dtos.users;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginInputDTO {
    private String email;
    private String password;
}
