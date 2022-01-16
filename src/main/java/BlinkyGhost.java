import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.AbstractTextGraphics;
import com.googlecode.lanterna.graphics.TextGraphics;

public class BlinkyGhost extends Ghost implements Chase, Scatter, Frightened{

    private AbstractTextGraphics graphics;
    private String colour;

    BlinkyGhost (int x, int y, String colour){
        super(x, y);
        this.colour = colour;
    }

    public void setPosition(Position position){ pos = position;}
    public Position getPosition(){return pos;}
    public void setColour(String colour){this.colour = colour;}

    //Agressive
    public void chase(PacMan pacMan, Maze maze){
        setColour("#FF0000");

        if(getXCor() == pacMan.getXCor() && getYCor() < pacMan.getYCor() && maze.isWall(new Position(getXCor(), getYCor()+1))){
            setPosition(new Position(getXCor(), getYCor()+1));
        }

        else if(getXCor() == pacMan.getXCor() && pacMan.getYCor() < getYCor() && maze.isWall(new Position(getXCor(), getYCor()+1))){
            setPosition(new Position(getXCor(), getYCor()-1));
        }

        else if (getXCor() < pacMan.getXCor() && getYCor() == pacMan.getYCor() && maze.isWall(new Position(getXCor(), getYCor()+1))){
            setPosition(new Position(getXCor()+1, getYCor()));
        }

        else if (pacMan.getXCor() < getPosition().getX() && getYCor() == pacMan.getYCor() && maze.isWall(new Position(getXCor(), getYCor()+1))){
            setPosition(new Position(getXCor()-1, getYCor()));
        }

        else if (getXCor() < pacMan.getXCor() && getYCor() < pacMan.getYCor() && maze.isWall(new Position(getXCor()+1, getYCor()))){
            setPosition(new Position(getXCor()+1, getYCor()));
        }

        else if (getXCor() < pacMan.getXCor() && getYCor() < pacMan.getYCor() && maze.isWall(new Position(getXCor(), getYCor()+1))){
            setPosition(new Position(getXCor(), getYCor()+1));
        }

        else if (pacMan.getXCor() < getXCor() && pacMan.getYCor() < getYCor() && maze.isWall(new Position(getXCor()-1, getYCor()))){
            setPosition(new Position(getXCor()-1, getYCor()));
        }

        else if (pacMan.getXCor() < getXCor() && pacMan.getYCor() < getYCor() && maze.isWall(new Position(getXCor(), getYCor()-1))){
            setPosition(new Position(getXCor(), getYCor()-1));
        }
    }

    //Top Right Corner
    public void scatter(){
        setColour("#FF0000");
    }

    public void frightened(){
        setColour("#432AE8");
    }

    public void draw (TextGraphics graphics) throws InterruptedException {
        graphics.setForegroundColor(TextColor.Factory.fromString(colour));
        graphics.putString(new TerminalPosition(pos.getX(), pos.getY()), "F");
    }
}
