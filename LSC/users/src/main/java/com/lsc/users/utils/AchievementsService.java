package com.lsc.users.utils;

import com.lsc.users.entities.ProfileEntity;

import java.util.HashMap;
import java.util.Map;

public class AchievementsService {
    private static Map<String, String> achievementsMap;

    private static void fillAchievementsMap() {
        achievementsMap = new HashMap<>();
        achievementsMap.put("abecedario", "a deletrear");
        achievementsMap.put("tiempos", "llegaste lejos");
        achievementsMap.put("numeros", "conteo");
        achievementsMap.put("lugares", "desbloqueador");
        achievementsMap.put("en el estudio", "estrategico");
        achievementsMap.put("pronombres", "leccion realizada");
    }

    public static void updateAchievementsByCompletedLesson(ProfileEntity profileEntity, String completedLesson) {
        if (achievementsMap == null) {
            fillAchievementsMap();
        }
        if (achievementsMap.containsKey(completedLesson)) {
            profileEntity.addReachedAchievement(achievementsMap.get(completedLesson));
        }
    }
}
