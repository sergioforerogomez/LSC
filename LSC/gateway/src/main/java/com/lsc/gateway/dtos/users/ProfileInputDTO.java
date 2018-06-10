package com.lsc.gateway.dtos.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ProfileInputDTO {
    private String email;
    private String password;
    private String confirmPassword;
    private String currentPassword;
    private String name;
    private String completedLesson;
}
