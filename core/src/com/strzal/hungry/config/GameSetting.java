package com.strzal.hungry.config;


public class GameSetting {


    //Time
    public static final long WATER_DRILL_COOKING_TIME = 5; //seconds
    public static final long DRILL_COOKING_TIME = 5; //seconds
    public static final long COOKING_PLACE_TIME = 5; //seconds
    public static final long FISH_MAKING_PLACE_TIME = 5; //seconds
    public static final long ENERGY_MAKER_PLACE_TIME = 25; //seconds

    public static final int OXYGEN_INITIAL_TIME = 100;//Seconds


    //Energy Used
    public static final int WATER_DRILL_USED_ENERGY = 10;
    public static final int DRILL_USED_ENERGY = 10;
    public static final int COOKING_PLACE_USED_ENERGY = 5;
    public static final int FISH_MAKER_PLACE_USED_ENERGY = 5;

    //
    public static final int ENERGY_MAKER_ADDED_ENERGY = 20;

    public static final int ENERGY_MAKER_CASH_COST = 200;

    //Levels in Game Mode
    public static final int NUMBER_OF_HUNGRY_IN_WAVE = 3;
    public static final int MAXIMUM_WAVE_IN_GAME_MODE = 10;
    public static final int CASH_PER_ORDER = 100;

    private GameSetting(){}
}
