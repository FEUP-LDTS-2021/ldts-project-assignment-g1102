import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Fruit extends Element{
    private char character;
    private String colour;
    public Fruit (int x, int y, char c, String colour){
        super (x, y);
        character = c;
        this.colour = colour;
    }
    public void setPosition(Position position){
        pos = position;
    }
    public Position getPosition() {
        return pos;
    }
    public char getCharacter(){
        return character;
    }
    public void setCharacter(char c){
        character = c;
    }
    public void draw (TextGraphics graphics) throws InterruptedException {
        graphics.setForegroundColor(TextColor.Factory.fromString(colour));
        graphics.putString(new TerminalPosition(pos.getX(), pos.getY()), String.valueOf(character));

    }
}
