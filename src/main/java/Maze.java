import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Maze {
    int width, height;
    Position pos = new Position(14,20);
    PacMan pacman = new PacMan(pos);
    List<Wall> walls = new ArrayList<Wall>();
    List<Food> foods = new ArrayList<>();
    public Maze(int w, int h){
        width = w;
        height = h;
        this.walls = createWalls();
        this.foods = createFoods();
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

    public void draw(TextGraphics screen){
        pacman.draw(screen);

        for (Wall w : walls){
            w.draw(screen);
        }

        for (Food f : foods){
            f.draw(screen);
        }
    }
    /*public boolean isInaccessible(Position p){ //this function returns true if the given position is inaccessible by pac-man
        if (p)
    }*/
    public boolean isWall(Position p){  //verifica se determinada posição é uma parede
        for (Wall w : walls){
            if (w.getY() > p.getY()){
                break;
            }
            if (p.getX()== w.getX() && p.getY()== w.getY()) {
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

    public void processKey(KeyStroke key) throws IOException {
        Position newP = null;
        switch (key.getKeyType()) {
            case ArrowUp:
                newP = new Position(pacman.getX(),pacman.getY()-1);
                if (isWall(newP)){
                    break;
                }
                else if (isHole(newP)){
                    newP = goThroughHole(newP);
                }
                pacman.moveHero(newP);
                break;
            case ArrowDown:
                newP = new Position(pacman.getX(),pacman.getY()+1);
                if (isWall(newP)){
                    break;
                }
                else if (isHole(newP)){
                    newP = goThroughHole(newP);
                }
                pacman.moveHero(newP);
                break;
            case ArrowLeft:
                newP = new Position(pacman.getX()-1,pacman.getY());
                if (isWall(newP)){
                    break;
                }
                else if (isHole(newP)){
                    newP = goThroughHole(newP);
                }
                pacman.moveHero(newP);
                break;
            case ArrowRight:
                newP = new Position(pacman.getX()+1,pacman.getY());
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
                    Position pos = new Position(column, line);
                    if (data.charAt(column) == '.' || data.charAt(column) == 'o') {
                        Food f = new Food(pos,  data.charAt(column));
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
            if (f.getX() == pacman.getX() && f.getY() == pacman.getY()){
                foods.remove(f);
                break;
            }
        }
    }

    private void endOfFood() throws IOException { //when food ends, the map has to be loaded again, and the game continues with the same score and the same number of lives (this only restarts, it does not mantain the future score and the number of lives I think)
        if (foods.isEmpty()){
            Game game = new Game();
            game.run();
        }
    }
}
