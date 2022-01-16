import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.AbstractTextGraphics;
import com.googlecode.lanterna.graphics.TextGraphics;

public class ClydeGhost extends Ghost implements Chase, Scatter, Frightened{

    private AbstractTextGraphics graphics;
    private String colour;

    ClydeGhost (int x, int y, String colour){
        super(x, y);
        this.colour = colour;
    }

    public void setPosition(Position position){ pos = position;}
    public Position getPosition(){return pos;}
    public void setColour(String colour){this.colour = colour;}

    //Random
    public void chase(PacMan pacMan, Maze maze){
        setColour("#FFB852");
    }

    //Bottom Rigth Corner
    public void scatter(){
        setColour("#FFB852");
    }

    public void frightened(){
        setColour("#432AE8");
    }

    public void draw (TextGraphics graphics) throws InterruptedException {
        graphics.setForegroundColor(TextColor.Factory.fromString(colour));
        graphics.putString(new TerminalPosition(pos.getX(), pos.getY()), "F");
    }
}
