// Abstraction
enum Type { MP3, WAV, FLAC, NULL }
class MusicPlayer {
    void start(String fileName) {}
    void decode(String fileName) {}
    Type musicType;
}

class JazzMusicPlayer extends MusicPlayer {
    public JazzMusicPlayer(MusicType type) {
        musicType = type.getType();
    }
    public void start(String fileName) {
        System.out.println("Started Jazz Player...");
        decode(fileName);
    }
    public void decode(String fileName) {
        System.out.println("Playing " + fileName + " using " + musicType + " codec.");
    }
}
class PopMusicPlayer extends MusicPlayer {
    public PopMusicPlayer(MusicType type) {
        musicType = type.getType();
    }
    public void start(String fileName) {
        System.out.println("Started Pop Player...");
        decode(fileName);
    }
    public void decode(String fileName) {
        System.out.println("Playing " + fileName + " using " + musicType + " codec.");
    }
}
class RockMusicPlayer extends MusicPlayer {
    public RockMusicPlayer(MusicType type) {
        musicType = type.getType();
    }
    public void start(String fileName) {
        System.out.println("Started Rock Player...");
        decode(fileName);
    }
    public void decode(String fileName) {
        System.out.println("Playing " + fileName + " using " + musicType + " codec.");
    }
}

class MusicType {
    Type type = Type.NULL;
    Type getType() { return this.type; }
}
class MP3MusicType extends MusicType {
    Type type = Type.MP3;
    Type getType() { return this.type; }
}
class WAVMusicType extends MusicType {
    Type type = Type.WAV;
    Type getType() { return this.type; }
}
class FLACMusicType extends MusicType {
    Type type = Type.FLAC;
    Type getType() { return this.type; }
}

public class Main {
    public static void main(String[] args) {
        // Concrete implementations
        MusicPlayer popMusicPlayer1 = new PopMusicPlayer(new MP3MusicType());
        MusicPlayer jazzMusicPlayer = new JazzMusicPlayer(new MP3MusicType());
        MusicPlayer popMusicPlayer2 = new PopMusicPlayer(new WAVMusicType());
        MusicPlayer rockMusicPlayer1 = new RockMusicPlayer(new MP3MusicType());
        MusicPlayer rockMusicPlayer2 = new RockMusicPlayer(new WAVMusicType());
        MusicPlayer rockMusicPlayer3 = new RockMusicPlayer(new FLACMusicType());

        popMusicPlayer1.start("pop_song_1.mp3");
        jazzMusicPlayer.start("jazz_song.mp3");
        popMusicPlayer2.start("pop_song_2.wav");
        rockMusicPlayer1.start("rock_song_1.mp3");
        rockMusicPlayer2.start("rock_song_2.wav");
        rockMusicPlayer3.start("acid_venom.flac");
    }
}
