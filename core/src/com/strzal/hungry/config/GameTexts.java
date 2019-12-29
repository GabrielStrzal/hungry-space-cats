package com.strzal.hungry.config;


public class GameTexts {


    //Texts
    public static final String START_TEXT =
            "{COLOR=GREEN}We need your help!{WAIT} \n"

            + "{COLOR=WHITE} Hungry for worlds to eat, {COLOR=RED}{SICK} SPACE CATS {ENDSICK}{COLOR=WHITE} came and were eating {SICK}everything{ENDSICK}. {WAIT} \n"

            + "To stop them we started providing {COLOR=GREEN}TOP MEALS, {COLOR=WHITE}made from our planet itself. \n"

            + "But we need {RAINBOW}YOU{ENDRAINBOW} to make these special meals! \n"

            + "They pay us for each meal. \n"

            + "If you can feed them for {COLOR=RED}{VAR=weeks}{COLOR=WHITE} weeks we will be able to rest in peace. \n \n"

            + "{RAINBOW}You are our only hope.{ENDRAINBOW}";


    public static final String ENDLESS_TEXT =
            "{FASTER} \n \n \n{COLOR=RED}" +
                    "                                              " +
                    "{SHAKE}This is ENDLESS MODE...how long can you last? {ENDSHAKE}";


    public static final String GAME_OVER_TEXT =
            "{FASTER} \n \n \n{COLOR=RED}" +
                    "                                                                  " +
                    "{SHAKE}GAME OVER {ENDSHAKE}";



    public static final String WAVE_COMPLETE_TEXT =
            "{FASTER} \n \n \n" +
                    "                                                                  " +
                    "Week Complete! ";

    public static final String GAME_WON_TEXT =
            "{FASTER} \n \n \n" +
                    "                                                                  " +
                    "{RAINBOW}YOU WON {ENDRAINBOW}";

    public static final String GAME_STATS_TEXT =
            "{FASTER} \n" +
                    "                                           " +
                    "{COLOR=WHITE}Number of times Played:    {COLOR=GREEN} {VAR=timesPlayed} \n \n" +
                    "                                           " +
                    "{COLOR=WHITE}Longest week:    {COLOR=GREEN} {VAR=week} \n \n" +
                    "                                           " +
                    "{COLOR=WHITE}Top Cash:    {COLOR=GREEN} {VAR=cash} \n";

    private GameTexts(){}
}
