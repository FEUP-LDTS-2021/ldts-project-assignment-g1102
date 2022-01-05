import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class GameStats {
    private int score;
    /*private int eatenDots;
    private int eatenPP;
    private int eatenFruits;
    private int round;*/
    private String lives;

    public GameStats(int score, /*int eatenDots, int eatenPP, int eatenFruits, int round,*/String lives){ //when player looses, say in which round
        this.score = score;
        /*this.eatenDots = eatenDots;
        this.eatenPP = eatenPP;
        this.eatenFruits = eatenFruits;
        this.round = round;*/
        this.lives = lives;
    }

/*    public int getRound(){
        return round;
    }

    public void setRound(int round){
        this.round = round;
    }

    public int getEatenDotsPerRound() {
        return eatenDots;
    }

    public int getEatenPPPerRound() {
        return eatenPP;
    }

    public int getEatenFruitsPerRound() {
        return eatenFruits;
    }*/

    public int getScore() {
        return score;
    }

    public String getLives() {
        return lives;
    }

    /*public void setEatenDotsPerRound(int eatenDots) {
        this.eatenDots = eatenDots;
    }

    public void setEatenPPPearRound(int eatenPP) {
        this.eatenPP = eatenPP;
    }

    public void setEatenFruitsPerRound(int eatenFruits) {
        this.eatenFruits = eatenFruits;
    }*/

    public void setScore(){
        this.score = score;
    }

    public void setLives(String lives) {
        this.lives = lives;
    }

   /* public void incrementEatenFoodPerRound(Food food){
        if (food.getCharacter() == '.'){
            eatenDots++;
            score += 10;
        }
        else if (food.getCharacter() == 'o'){
            eatenPP++;
            score += 50;
        }
    }

    public void incrementEatenFruitsPerRound(Fruit fruit){
        if (fruit.getCharacter() == 'O'){
            eatenFruits++;
            score+= 100;
        }
        else if (fruit.getCharacter() == 'M'){
            eatenFruits++;
            score+= 300;
        }
    }*/

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

        //MAYBE ADD A FEATURE TO STORE MAKE THE HIGH SCORE APPEAR IN THE TOP MIDDLE
    }


}
