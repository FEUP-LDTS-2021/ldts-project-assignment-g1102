import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.AbstractTextGraphics;

abstract public class PinkyGhost extends Ghost{

    private AbstractTextGraphics graphics;

    PinkyGhost (int x, int y){
        super(x, y);
    }

    public void setPosition(Position position){ pos = position;}
    public Position getPosition(){return pos;}

    //Ambush
    public void chase(){

    }

    //Top Left Corner
    public void scatter(){

    }

    public void frightened(){

    }

}
