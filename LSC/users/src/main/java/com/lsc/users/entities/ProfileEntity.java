package com.lsc.users.entities;

import com.lsc.users.dtos.Level;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class ProfileEntity {
    @Id
    private String profileId;
    private String email;
    private String password;
    private String name;
    private Level level;
    private int generalProgress;
    private List<String> reachedAchievements;
    private List<String> completedLessons;

    public void addReachedAchievement(String reachedAchievement) {
        if (this.reachedAchievements == null) {
            this.reachedAchievements = new ArrayList<>();
        }
        this.reachedAchievements.add(reachedAchievement);
    }

    public void addCompletedLesson(String completedLesson) {
        if (this.completedLessons == null) {
            this.completedLessons = new ArrayList<>();
        }
        this.completedLessons.add(completedLesson);
    }
}
