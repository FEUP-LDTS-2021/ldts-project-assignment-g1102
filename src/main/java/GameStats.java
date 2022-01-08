import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class GameStats {
    private int score;
    private String lives;

    public GameStats(int score, String lives){
        this.score = score;
        this.lives = lives;
    }

    public int getScore() {
        return score;
    }

    public String getLives() {
        return lives;
    }

    public void setScore(){
        this.score = score;
    }

    public void setLives(String lives) {
        this.lives = lives;
    }

    public void incrementScorePellets(Food food) {
        if (food.getCharacter() == '.') {
            score += 10;
        } else if (food.getCharacter() == 'o') {
            score += 50;
        }
    }

    public void increaseScoreFruits(Fruit fruit){
        switch (fruit.getCharacter()){
            case 'C':
                score += 100;
                break;
            case 'M':
                score += 300;
                break;
            case 'O':
                score += 500;
                break;
            case 'A':
                score += 700;
                break;
            case 'E':
                score += 1000;
            case 'G':
                score += 2000;
            case 'B':
                score += 3000;
            case 'K':
                score += 5000;
        }
    }

    public void incrementLives(){
        if (score == 10000){
            lives += 'C';
        }
    }

    public void drawGameElements(TextGraphics graphics){
        graphics.putString(0,0, "Score: " + score);
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFE600"));
        graphics.putString(0, 34, lives);

    }
}
