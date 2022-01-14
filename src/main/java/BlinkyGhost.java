import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.AbstractTextGraphics;

abstract public class BlinkyGhost extends Ghost{

    private AbstractTextGraphics graphics;

    BlinkyGhost (int x, int y){
        super(x, y);
    }

    public void setPosition(Position position){ pos = position;}
    public Position getPosition(){return pos;}


}
