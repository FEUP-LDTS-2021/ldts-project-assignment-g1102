import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.AbstractTextGraphics;

abstract public class BlinkyGhost extends Ghost implements Chase, Scatter, Frightened{

    private AbstractTextGraphics graphics;

    BlinkyGhost (int x, int y){
        super(x, y);
    }

    public void setPosition(Position position){ pos = position;}
    public Position getPosition(){return pos;}

    //Agressive
    public void chase(){
        
    }

    //Top Right Corner
    public void scatter(){

    }

    public void frightened(){

    }

}
