package me.ciu.o.pattern;

public class Adapter {

    public interface MediaPlayer {
        void play(String audioType, String fileName);
    }

    public interface VideoPlayer {
        void playVlc(String fileName);
        void playAvi(String fileName);
    }

    public static class VlcPlayer implements VideoPlayer {
        @Override
        public void playVlc(String fileName) {
            System.out.println("Playing vlc file. Name: " + fileName);
        }

        @Override
        public void playAvi(String fileName) {
        }
    }

    public static class AviPlayer implements VideoPlayer {
        @Override
        public void playVlc(String fileName) {
        }

        @Override
        public void playAvi(String fileName) {
            System.out.println("Playing avi file. Name: " + fileName);
        }
    }

    public static class MediaAdapter implements MediaPlayer {
        VideoPlayer videoPlayer;

        public MediaAdapter(String audioType) {
            if (audioType.equalsIgnoreCase("vlc")) {
                videoPlayer = new VlcPlayer();
            } else if (audioType.equalsIgnoreCase("avi")) {
                videoPlayer = new AviPlayer();
            }
        }

        @Override
        public void play(String audioType, String fileName) {
            if (audioType.equalsIgnoreCase("vlc")) {
                videoPlayer.playVlc(fileName);
            } else if (audioType.equalsIgnoreCase("avi")) {
                videoPlayer.playAvi(fileName);
            }
        }
    }

    public static class AudioPlayer implements MediaPlayer {
        MediaAdapter mediaAdapter;

        @Override
        public void play(String audioType, String fileName) {
            if (audioType.equalsIgnoreCase("MP3")) {  // 播放MP3音乐文件的内置支持
                System.out.println("Playing mp3 file. Name: " + fileName);
            } else if (audioType.equalsIgnoreCase("VLC") || audioType.equalsIgnoreCase("AVI")) {  // mediaAdapter提供了播放其他文件格式的支持
                mediaAdapter = new MediaAdapter(audioType);
                mediaAdapter.play(audioType, fileName);
            } else {
                System.out.println("Invalid media. " + audioType + " format not supported");
            }
        }
    }

    public static void main(String[] args) {
        AudioPlayer player = new AudioPlayer();
        player.play("MP3", "beyond.mp3");
        player.play("VLC", "far away.vlc");
        player.play("AVI", "mind me.avi");
        player.play("MP4", "alone.mp4");
    }
}