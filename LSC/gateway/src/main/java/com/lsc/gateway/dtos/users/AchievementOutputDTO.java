package com.lsc.gateway.dtos.users;

import com.lsc.gateway.dtos.ErrorDTO;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AchievementOutputDTO extends ErrorDTO {
    private String achievementId;
    private String title;
    private String description;
    private String image;
}
