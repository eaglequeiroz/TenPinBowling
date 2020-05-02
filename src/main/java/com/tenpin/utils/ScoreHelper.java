package com.tenpin.utils;

public class ScoreHelper {

    public static Integer getIntScore(String score){
        return score.equalsIgnoreCase("F") ? Integer.parseInt("0") : Integer.parseInt(score);
    }
}
