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

public abstract class Ghost extends Element implements Frightened, Scatter, Chase{
    Chase chaseBehaviour;
    Scatter scatterBehaviour;
    Frightened frightenedBehaviour;

    Ghost (int x, int y){
        super(x, y);
    }
}
