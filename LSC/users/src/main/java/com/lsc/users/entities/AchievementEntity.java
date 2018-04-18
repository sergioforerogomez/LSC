package com.lsc.users.entities;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

@Data
@Accessors(chain = true)
public class AchievementEntity {
    @Id
    private String achievementId;
    private String title;
    private String description;
    private String image;
}
