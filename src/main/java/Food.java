import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Food extends Element{
    private char character;
    public Food (int x, int y, char c){
        super(x, y);
        character = c;
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

    public void draw (TextGraphics screen){
        screen.setForegroundColor(TextColor.Factory.fromString("#FFE600"));
        screen.putString(new TerminalPosition(pos.getX(), pos.getY()), String.valueOf(character));
    }

}
