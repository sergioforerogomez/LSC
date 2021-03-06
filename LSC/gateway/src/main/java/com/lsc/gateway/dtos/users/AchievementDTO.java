package com.lsc.gateway.dtos.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class AchievementDTO {
    private String achievementId;
    private String title;
    private String description;
    private String image;
}
