import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

interface Chase{
    void chase(PacMan pacMan, Maze maze);
}

interface Scatter{
    Position scatter(Position ghostP, Direction current);
}

interface Frightened{
    void frightened();
}

public class Ghost extends Element{
    public Direction current;
    private String colour;
    Chase chaseBehaviour;
    Scatter scatterBehaviour;
    Frightened frightenedBehaviour;

    Ghost (int x, int y, String colour, Scatter ScatterStrat){
        super(x, y);
        this.colour = colour;
        current = Direction.UP;
        scatterBehaviour = ScatterStrat;
    }

    public void setPosition(Position position){ pos = position;}
    public Position getPosition(){return pos;}
    public Direction getCurrent(){return current;}
    public void setColour(String colour){this.colour = colour;}


    public void draw (TextGraphics graphics) throws InterruptedException {
        graphics.setForegroundColor(TextColor.Factory.fromString(colour));
        graphics.putString(new TerminalPosition(pos.getX(), pos.getY()), "F");
        graphics.setForegroundColor(TextColor.Factory.fromString(colour));
        graphics.putString(new TerminalPosition(pos.getX(), pos.getY()), "F");
        graphics.setForegroundColor(TextColor.Factory.fromString(colour));
        graphics.putString(new TerminalPosition(pos.getX(), pos.getY()), "F");
        graphics.setForegroundColor(TextColor.Factory.fromString(colour));
        graphics.putString(new TerminalPosition(pos.getX(), pos.getY()), "F");
    }
}
