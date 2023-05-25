package granc3k.semestralproject.reservationsystemtenis.utils;

import javax.sound.sampled.*;
import java.io.InputStream;
import java.util.concurrent.CompletableFuture;

public class Music {

        /**
         * @brief Utility that plays.wav file from resources folder
         * @param src Source file name e.g. music.wav
         * @param volume Volume in between 0-1 calculated by volume/100
         * */
        private static void playMusic(String src, float volume){
            try {
                InputStream inputStream = Music.class.getResourceAsStream("/"+src);
                if(inputStream == null) return;

                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream);
                AudioFormat format = audioInputStream.getFormat();

                DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
                SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
                line.open(format);

                //Update volume before starting
                setVolume(line, volume);

                line.start();

                byte[] buffer = new byte[4096];
                int bytesRead = 0;
                while ((bytesRead = audioInputStream.read(buffer)) != -1) {
                    line.write(buffer, 0, bytesRead);
                }
                line.drain();
                line.close();
                audioInputStream.close();
                playMusic(src, volume);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * @param line Source input line
         * @param volume volume in float 0.00-1.00F
         */
        public static void setVolume(SourceDataLine line, float volume) {
            if (line != null && volume >= 0.0f && volume <= 1.0f) {
                FloatControl gainControl = (FloatControl) line.getControl(FloatControl.Type.MASTER_GAIN);
                float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
                gainControl.setValue(dB);
            }
        }

        /**
         * @brief Utility that plays.wav file from resources folder
         * @param src Source file name e.g. music.wav
         * @param volume Volume in between 0-1 calculated by volume/100
         * */
        public static void play(String src, float volume){
            CompletableFuture.runAsync(()->{
                playMusic(src, volume);
            });
        }
}
