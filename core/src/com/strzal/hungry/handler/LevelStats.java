package com.strzal.hungry.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LevelStats {
    private int totalTimesPlayed;
    private int week;
    private int highScore;
    private boolean isGameWon;
}
