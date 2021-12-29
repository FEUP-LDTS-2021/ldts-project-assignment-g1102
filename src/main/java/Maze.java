import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Maze {
    int width, height;
    Position pos = new Position(14,20);
    PacMan pacman = new PacMan(pos);
    List<Wall> walls = new ArrayList<Wall>();
    public Maze(int w, int h){
        width = w;
        height = h;
        this.walls = createWalls();
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
        screen.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        screen.putString(new TerminalPosition(pacman.getX(),pacman.getY()),"C");
        screen.setForegroundColor(TextColor.Factory.fromString("#1919A6"));
        for (Wall w : walls){
            w.draw(screen);
        }
    }

    public boolean isWall(Position p){  //verifica se determinada posição é uma parede
        for (Wall w : walls){
            if (p.getX()== w.getX() && p.getY()== w.getY()){
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

    public void processKey(KeyStroke key){
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
}
