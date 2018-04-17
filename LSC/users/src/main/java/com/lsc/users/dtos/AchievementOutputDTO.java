package com.lsc.users.dtos;

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
