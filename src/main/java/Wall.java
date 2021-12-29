import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall {
    Position pos;
    char caracter;
    public Wall(int x, int y, char caracter){
        pos = new Position(x,y);
        this.caracter = caracter;
    }

    public int getX() {
        return pos.getX();
    }

    public int getY() {
        return pos.getY();
    }

    public char getCaracter() {
        return caracter;
    }
    public void draw(TextGraphics screen){
        screen.putString(new TerminalPosition(pos.getX(),pos.getY()),String.valueOf(caracter));
    }
}
