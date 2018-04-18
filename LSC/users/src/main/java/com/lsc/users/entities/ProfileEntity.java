package com.lsc.users.entities;

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
    private String profileImage;
    private String name;
    private String progressName;
    private int generalProgress;
    private List<String> reachedAchievements;
    private List<String> completedLessons;

    public void addCompletedLesson(String completedLesson) {
        if (this.completedLessons == null) {
            this.completedLessons = new ArrayList<>();
        }
        this.completedLessons.add(completedLesson);
    }
}
