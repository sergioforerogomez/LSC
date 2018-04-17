package com.lsc.users.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginOutputDTO extends ErrorDTO {
    private String token;
    private String profileId;
}
