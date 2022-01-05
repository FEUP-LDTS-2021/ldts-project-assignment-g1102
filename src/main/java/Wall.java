import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall extends Element{
    char character;
    public Wall(int x, int y, char c){
        super(x, y);
        character = c;
    }

    public void setPosition(Position position){
        pos = position;
    }
    public Position getPosition() {
        return pos;
    }

    public char getCaracter() {
        return character;
    }
    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#1919A6"));
        graphics.putString(new TerminalPosition(pos.getX(),pos.getY()),String.valueOf(character));
    }
}
