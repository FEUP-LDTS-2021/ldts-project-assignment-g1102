import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Food {
    private Position pos;
    private char character;
    public Food (Position p, char c){
        pos = p;
        character = c;
    }

    public int getX(){
        return pos.getX();
    }

    public int getY(){
        return pos.getY();
    }

    public void setX(int x){
        pos.setX(x);
    }

    public void setY(int y){
        pos.setY(y);
    }

    public void draw (TextGraphics screen){
        screen.setForegroundColor(TextColor.Factory.fromString("#FFE600"));
        screen.putString(new TerminalPosition(pos.getX(), pos.getY()), String.valueOf(character));
    }

}
