import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class Maze {
    public static boolean alreadyExecuted = false; //this does not belong to any object. I declared this as static so I could use it in the class game without any problems
    int width, height;
    int score = 0;
    String lives = "CCCCC";
    private PacMan pacman;
    List<Wall> walls = new ArrayList<Wall>();
    List<Food> foods = new ArrayList<>();
    public Maze(int w, int h){
        width = w;
        height = h;
        this.walls = createWalls();
        this.foods = createFoods();
        pacman = new PacMan(14, 26);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    public void drawElements(TextGraphics screen) throws InterruptedException {
        screen.putString(0,0, "Score: " + score);
        screen.setForegroundColor(TextColor.Factory.fromString("#FFE600"));
        screen.putString(0, 34, lives);

        for (Wall w : walls){
            w.draw(screen);
        }

        for (Food f : foods){
            f.draw(screen);
        }
        pacman.draw(screen);
    }
    /*public boolean isInaccessible(Position p){ //this function returns true if the given position is inaccessible by pac-man
        if (p)
    }*/
    public boolean isWall(Position p){  //verifica se determinada posição é uma parede
        for (Wall w : walls){
            if (w.getPosition().getY() > p.getY()){
                break;
            }
            if (p.getX()== w.getPosition().getX() && p.getY()== w.getPosition().getY()) {
                return true;
            }
        }
        return false;
    }

    public boolean isFood(Position p){  //verifica se determinada posição é uma parede
        for (Food f : foods){
            if (f.getPosition().getY() > p.getY()){
                break;
            }
            if (p.getX()== f.getPosition().getX() && p.getY()== f.getPosition().getY()) {
                return true;
            }
        }
        return false;
    }

    public boolean isHole(Position p){
        if (!isWall(p)&&((p.getX()<0 || p.getX()==width+1)||(p.getY()<0 || p.getY()==height+1))){
            return true;
        }
        return false;
    }

    public Position goThroughHole(Position p){
        if (p.getX()<0){
            p = new Position(width,p.getY());
        }
        else if (p.getX()>width){
            p = new Position(0,p.getY());
        }
        else if (p.getY()<0){
            p = new Position(p.getX(),height);
        }
        else if (p.getY()>height){
            p = new Position(p.getX(),0);
        }
        return p;
    }

    public void processKey(KeyStroke key) throws IOException, InterruptedException {
        Position newP = null;
        switch (key.getKeyType()) {
            case ArrowUp:
                newP = new Position(pacman.getPosition().getX(),pacman.getPosition().getY()-1);
                if (isWall(newP)){
                    break;
                }
                else if (isHole(newP)){
                    newP = goThroughHole(newP);
                }
                pacman.moveHero(newP); //put just one move hero on the end of the switch case
                break;
            case ArrowDown:
                newP = new Position(pacman.getPosition().getX(),pacman.getPosition().getY()+1);
                if (isWall(newP)){
                    break;
                }
                else if (isHole(newP)){
                    newP = goThroughHole(newP);
                }
                pacman.moveHero(newP);
                break;
            case ArrowLeft:
                newP = new Position(pacman.getPosition().getX()-1,pacman.getPosition().getY());
                if (isWall(newP)){
                    break;
                }
                else if (isHole(newP)){
                    newP = goThroughHole(newP);
                }
                pacman.moveHero(newP);
                break;
            case ArrowRight:
                newP = new Position(pacman.getPosition().getX()+1,pacman.getPosition().getY());
                if (isWall(newP)){
                    break;
                }
                else if (isHole(newP)){
                    newP = goThroughHole(newP);
                }
                pacman.moveHero(newP);
                break;
        }
        retrieveFood();
        endOfFood();
    }

    private List<Wall> createWalls(){
        Scanner mazeReader = null;
        try {
            File mazeFile = new File("maze.txt");
            mazeReader = new Scanner(mazeFile);
            int line = 3;
            while (mazeReader.hasNextLine()) {
                String data = mazeReader.nextLine();
                int column = 0;
                while (column < data.length()) {
                    if (data.charAt(column) == '|' || data.charAt(column) == '+' || data.charAt(column) == '-' || data.charAt(column) == '=') {
                        Wall w = new Wall(column, line, data.charAt(column));
                        walls.add(w);
                    }
                    column++;
                }
                line++;
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("ERROR:Maze file not found.");
        }
        mazeReader.close();
        return walls;
    }

    private List<Food> createFoods(){
        Scanner mazeReader = null;
        try {
            File mazeFile = new File("maze.txt");
            mazeReader = new Scanner(mazeFile);
            int line = 3;
            while (mazeReader.hasNextLine()) {
                String data = mazeReader.nextLine();
                int column = 0;
                while (column < data.length()) {
                    if (data.charAt(column) == '.' || data.charAt(column) == 'o') {
                        Food f = new Food(column, line, data.charAt(column));
                        foods.add(f);
                    }
                    column++;
                }
                line++;
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("ERROR:Maze file not found.");
        }
        mazeReader.close();
        return foods;
    }

    private void retrieveFood(){ //also, don't forget to increase score
        for (Food f : foods){
            if (f.getPosition().getX() == pacman.getPosition().getX() && f.getPosition().getY() == pacman.getPosition().getY()){ //override equals in Position class to write f.getPosition == pacman.getPosition
                if (f.getCharacter() == '.'){
                    score += 10;
                }
                else if(f.getCharacter() == 'o'){
                    score += 50;
                }
                foods.remove(f);
                break;
            }
        }
    }

    private void endOfFood() throws IOException, InterruptedException { //when food ends, the map has to be loaded again, and the game continues with the same score and the same number of lives (and pacman returns to its initial position)
        if (foods.isEmpty()){
            this.createWalls();
            this.createFoods();
            pacman = new PacMan(14, 26);
            alreadyExecuted = false;
        }
    }

    public boolean canPacMove(Direction dir){
        Position checkP;
        boolean answer = false;
        switch (dir){
            case UP:
                checkP = new Position(pacman.getXCor(),pacman.getYCor()-1);
                answer = isWall(checkP);
                break;
            case DOWN:
                checkP = new Position(pacman.getXCor(),pacman.getYCor()+1);
                answer = isWall(checkP);
                break;
            case LEFT:
                checkP = new Position(pacman.getXCor()-1,pacman.getYCor());
                answer = isWall(checkP);
                break;
            case RIGHT:
                checkP = new Position(pacman.getXCor()+1,pacman.getYCor());
                answer = isWall(checkP);
                break;
        }
        return !answer;
    }
}
