package com.lsc.gateway.dtos.users;

import com.lsc.gateway.dtos.ErrorDTO;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginOutputDTO extends ErrorDTO {
    private String token;
    private String profileId;
}
