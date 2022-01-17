import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

interface Chase{
    void chase(PacMan pacMan, Maze maze);
}

interface Scatter{
    void scatter();
}

interface Frightened{
    void frightened();
}

public class Ghost extends Element{
    private String colour;
    Chase chaseBehaviour;
    Scatter scatterBehaviour;
    Frightened frightenedBehaviour;

    Ghost (int x, int y, String colour){
        super(x, y);
        this.colour = colour;
    }

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