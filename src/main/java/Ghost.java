import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

interface Chase{
    PosDir chase(Position ghostP, Position pacmanP, Direction current) throws IOException;
}

interface Scatter{
    PosDir scatter(Position ghostP, Direction current) throws IOException;
}

interface Frightened{
    PosDir frightened(Position ghostP, Position pacmanP, Direction current) throws IOException;
}

public class Ghost extends Element{
    public Direction current;
    private String name;
    private String colour;
    Chase chaseBehaviour = new ChaseRandomly();
    Scatter scatterBehaviour;
    Frightened frightenedBehaviour = new FrightenedNormal();

    Ghost (int x, int y, String colour, Scatter ScatterStrat, String name){
        super(x, y);
        this.colour = colour;
        this.name = name;
        current = Direction.UP;
        scatterBehaviour = ScatterStrat;
    }

    public void setPosition(Position position){ pos = position;}
    public Position getPosition(){return pos;}
    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    public Direction getCurrent(){return current;}
    public String getColour(){return colour;}
    public void setColour(String colour){this.colour = colour;}
    public void setCurrent(Direction current) {this.current = current;}

    public void draw (TextGraphics graphics) throws InterruptedException {
        graphics.setForegroundColor(TextColor.Factory.fromString(colour));
        graphics.putString(new TerminalPosition(pos.getX(), pos.getY()), "F");
    }
}
