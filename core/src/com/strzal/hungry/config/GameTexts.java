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
            "{FASTER} \n \n {COLOR=WHITE}" +
                    "                                                           " +
                    "This is {COLOR=RED}{SHAKE}ENDLESS MODE{ENDSHAKE}\n\n" +
                    "                                                        " +
                    "{COLOR=WHITE}...how long can you last? ";


    public static final String GAME_OVER_TEXT =
            "{FASTER} \n \n{COLOR=RED}" +
                    "                                                                  " +
                    "{SHAKE}GAME OVER{ENDSHAKE} \n \n" +
                    "                                      " +
                    "{COLOR=WHITE}Always check your {COLOR=ORANGE}Oxygen {COLOR=WHITE}and your {COLOR=ORANGE}Energy" ;



    public static final String WAVE_COMPLETE_TEXT =
            "{FASTER} \n \n" +
                    "                                                            " +
                    "{COLOR=GREEN}Excellent work! \n \n" +
                    "         " +
                    "{COLOR=WHITE}This {COLOR=GREEN}week {COLOR=WHITE}is over, but it is not all done yet. " +
                    "The number of cats has {COLOR=RED}increased" ;

    public static final String GAME_WON_TEXT =
            "{FASTER} \n \n \n" +
                    "                                                                  " +
                    "{RAINBOW}YOU WON {ENDRAINBOW} \n \n" +
                    "               " +
                    "{COLOR=WHITE}You have unlocked the {COLOR=RED}{SHAKE}ENDLESS MODE! {ENDSHAKE}{COLOR=WHITE}How long can you last? ";

    public static final String GAME_STATS_TEXT =
            "{FASTER} \n" +
                    "                                           " +
                    "{COLOR=WHITE}Number of times Played:    {COLOR=GREEN} {VAR=timesPlayed} \n \n" +
                    "                                           " +
                    "{COLOR=WHITE}Longest week:    {COLOR=GREEN} {VAR=week} \n \n" +
                    "                                           " +
                    "{COLOR=WHITE}Top Cash:    {COLOR=GREEN} {VAR=cash} \n";

    public static final String TUTORIAL_TEXT =
            "{FASTER}{COLOR=GREEN}" +
                    "Careful!  \n" +
                    "{COLOR=WHITE}Most things consume {COLOR=GREEN}ENERGY{COLOR=WHITE} \n" +
                    "but Oxygen and Energy generators consume {COLOR=GREEN}CASH";

    private GameTexts(){}
}
