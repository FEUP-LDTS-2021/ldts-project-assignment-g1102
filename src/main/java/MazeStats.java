import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class MazeStats {
    private int eatenDotsPerMaze;
    private int eatenPPPerMaze;
    private int eatenFruitsPerMaze;
    private int round;
    private Fruit displayFruits = new Fruit (21, 34, 'C', "#FF0000");

    public MazeStats(int eatenDots, int eatenPP, int eatenFruits, int round){ //when player looses, say in which round
        this.eatenDotsPerMaze = eatenDots;
        this.eatenPPPerMaze = eatenPP;
        this.eatenFruitsPerMaze = eatenFruits;
        this.round = round;
    }

    public Fruit getDisplayFruits(){
        return displayFruits;
    }

    public void setDisplayFruits(Fruit displayFruits) {
        this.displayFruits = displayFruits;
    }

    public int getRound(){
        return round;
    }

    public int getEatenDotsPerRound() {
        return eatenDotsPerMaze;
    }

    public int getEatenPPPerRound() {
        return eatenPPPerMaze;
    }

    public int getEatenFruitsPerRound() {
        return eatenFruitsPerMaze;
    }

    public void setRound(int round){
        this.round = round;
    }

    public void setEatenDotsPerRound(int eatenDots) {
        this.eatenDotsPerMaze = eatenDots;
    }

    public void setEatenPPPearRound(int eatenPP) {
        this.eatenPPPerMaze = eatenPP;
    }

    public void setEatenFruitsPerRound(int eatenFruits) {
        this.eatenFruitsPerMaze = eatenFruits;
    }

    public void incrementEatenDotsPerRound() {
        eatenDotsPerMaze++;
    }

    public void incrementEatenPPPerRound(){
        eatenPPPerMaze++;
    }

    public void incrementEatenFruitsPerRound(){
        eatenFruitsPerMaze++;
    }

    public void drawDisplayFruits(TextGraphics graphics){
        switch (round){
            case 1:
            case 2:
            case 5:
            case 6:
                graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
                break;
            case 3:
            case 4:
                graphics.setForegroundColor(TextColor.Factory.fromString("#FF9900"));
                break;
            case 7:
            case 8:
                graphics.setForegroundColor(TextColor.Factory.fromString("#004D26"));
                break;
            case 9:
            case 10:
            case 11:
            case 12:
                graphics.setForegroundColor(TextColor.Factory.fromString("#EAFF00"));
                break;
            case 13:
            case 14:
                graphics.setForegroundColor(TextColor.Factory.fromString("#B0B0B0"));
                break;
        }
        graphics.putString(21,34, String.valueOf(displayFruits.getCharacter()));
    }
}
