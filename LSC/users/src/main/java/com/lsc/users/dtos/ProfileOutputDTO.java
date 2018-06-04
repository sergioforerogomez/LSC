package com.lsc.users.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ProfileOutputDTO {
    private String profileId;
    private String name;
    private String level;
    private int generalProgress;
    private List<String> reachedAchievements;
    private List<String> completedLessons;
}
