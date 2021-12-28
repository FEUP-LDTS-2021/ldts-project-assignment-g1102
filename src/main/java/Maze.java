import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

public class Maze {
    int width, height;
    Position pos = new Position(15,19);
    PacMan pacman = new PacMan(pos);
    public Maze(int w, int h){
        width = w;
        height = h;
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

    public void draw(Screen screen){
        screen.setCharacter(pacman.getX(), pacman.getY(), TextCharacter.fromCharacter('C')[0]);
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
}
