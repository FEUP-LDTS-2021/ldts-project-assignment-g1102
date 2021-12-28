import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;


public class Maze {
    int width, height;
    Position pos = new Position(15,19);
    PacMan pacman = new PacMan(pos);
    List<Wall> walls;
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

    public void processKey(KeyStroke key){
        switch (key.getKeyType()) {
            case ArrowUp:
                pacman.moveUp();
                break;
            case ArrowDown:
                pacman.moveDown();
                break;
            case ArrowLeft:
                pacman.moveLeft();
                break;
            case ArrowRight:
                pacman.moveRight();
                break;
        }
    }
    private List<Wall> createWalls(){
        File mazeFile = new File("maze.txt");
        Scanner mazeReader = null;
        try {
            mazeReader = new Scanner(mazeFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("ERROR:Maze file not found.");
        }
        int line = 3;
        while (mazeReader.hasNextLine()){
            String data = mazeReader.nextLine();
            int column = 0;
            while (column < data.length()){
                if (data.charAt(column) == '|' || data.charAt(column) == '+' || data.charAt(column) == '-' || data.charAt(column) == '='){
                    Wall w = new Wall(column,line,data.charAt(column));
                    walls.add(w);
                }
                column++;
            }
            line++;
        }
        mazeReader.close();
        return walls;
    }
}
