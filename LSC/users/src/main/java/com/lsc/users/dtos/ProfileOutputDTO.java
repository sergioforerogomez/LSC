package com.lsc.users.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ProfileOutputDTO extends ErrorDTO {
    private String profileId;
    private String profileImage;
    private String name;
    private String level;
    private int generalProgress;
    private List<String> reachedAchievements;
    private List<String> completedLevels;
}
