package com.strzal.hungry.audio;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.strzal.gdxUtilLib.BasicGame;
import com.strzal.hungry.constants.SoundPaths;

public class AudioHandler {
    private BasicGame game;
    private Music backgroundMusic;

    private AssetManager assetManager;
    long gameSoundID;
    float volume = 1;// 0.3f;

    Sound alarmSound;
    Boolean loaded = false;

    public AudioHandler(BasicGame game){
        game = game;
        assetManager = game.getAssetManager();
    }

    public void playHappySound(){
        Sound sound = assetManager.get(SoundPaths.CORRECT_AUDIO);
//        if(game.getGameStatsHandler().isAudioOn())
        sound.play(volume);
    }

    public void playWrongSound(){
        Sound sound = assetManager.get(SoundPaths.WRONG_AUDIO);
//        if(game.getGameStatsHandler().isAudioOn())
        sound.play(volume);
    }

    public void playButtonSound(){
        Sound sound = assetManager.get(SoundPaths.MENU_BUTTON_AUDIO);
//        if(game.getGameStatsHandler().isAudioOn())
        sound.play(volume);
    }

    public void playKittenSound(){
        Sound sound = assetManager.get(SoundPaths.KITTEN_MEOW_AUDIO);
//        if(game.getGameStatsHandler().isAudioOn())
        sound.play(volume);
    }


    public void playOrderCompleteSound()  {
        Sound sound = assetManager.get(SoundPaths.ORDER_COMPLETE_AUDIO);
//        if(game.getGameStatsHandler().isAudioOn())
        sound.play(volume * 3);
    }

    public void playAlarmSound()  {
        loadAlarmSound();
        alarmSound.resume();
    }

    public void stopAlarmSound(){
        loadAlarmSound();
        alarmSound.pause();
    }

    private void loadAlarmSound(){
        if(!loaded) {
            alarmSound = assetManager.get(SoundPaths.ALARM_AUDIO);
            alarmSound.loop(volume);
            alarmSound.pause();
            loaded = true;
        }
    }


}
