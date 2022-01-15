import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.AbstractTextGraphics;

abstract public class ClydeGhost extends Ghost{

    private AbstractTextGraphics graphics;

    ClydeGhost (int x, int y){
        super(x, y);
    }

    public void setPosition(Position position){ pos = position;}
    public Position getPosition(){return pos;}

    //Random
    public void chase(){

    }

    //Bottom Rigth Corner
    public void scatter(){

    }

    public void frightened(){

    }

}
