package com.lsc.users.entities;

import com.lsc.users.dtos.ProgressName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class ProfileEntity {
    @Id
    private ObjectId profileId;
    private String email;
    private String password;
    private String name;
    private ProgressName progressName;
    private int generalProgress;
    private List<String> reachedAchievements;
    private List<String> completedLessons;

    ProfileEntity() {
        this.reachedAchievements = new ArrayList<>();
        this.completedLessons = new ArrayList<>();
    }

    public void addReachedAchievement(String reachedAchievement) {
        if (this.reachedAchievements == null) {
            this.reachedAchievements = new ArrayList<>();
        }
        if (reachedAchievements.stream().noneMatch((item) -> item.equals(reachedAchievement))) {
            this.reachedAchievements.add(reachedAchievement);
        }
    }

    private void updateProgress() {
        this.generalProgress = this.completedLessons.size() * 100 / 8;
        if (this.generalProgress > 66) {
            this.progressName = ProgressName.AVANZADO;
        } else if (this.generalProgress > 33) {
            this.progressName = ProgressName.INTERMEDIO;
        }
    }

    public void addCompletedLesson(String completedLesson) {
        if (this.completedLessons == null) {
            this.completedLessons = new ArrayList<>();
        }
        if (completedLessons.stream().noneMatch((item) -> item.equals(completedLesson))) {
            this.completedLessons.add(completedLesson);
        }
        updateProgress();
    }
}
