package Sound;


import window.Menu;

import javax.sound.sampled.*;
import java.io.File;
import java.util.ArrayList;

public class MusicPlayer implements Runnable{

    Menu menu;
    private ArrayList<String> musicFiles;
    private int currentSongIndex;
    Clip clip1;

    public MusicPlayer(String... files) {
        this.menu = menu;
        musicFiles = new ArrayList<>();
        for (String file : files) {
            musicFiles.add("./res/audio/" + file + ".wav");
        }
    }

    private void playSound(String fileName) {
        try {
            File soundFile = new File(fileName);
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat format = ais.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip1 = clip;
            clip.open(ais);
            //FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            //gainControl.setValue(-10);
            clip.start();
            clip.loop(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void playSound1(String fileName) {
        try {
            File soundFile = new File(fileName);
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat format = ais.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip1 = clip;
            clip.open(ais);
            //FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            //gainControl.setValue(-10);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void run() {
        playSound(musicFiles.get(currentSongIndex));
    }
    public void run1() {
        playSound1(musicFiles.get(currentSongIndex));
    }
    public void stop() {
        clip1.stop();
    }
}
