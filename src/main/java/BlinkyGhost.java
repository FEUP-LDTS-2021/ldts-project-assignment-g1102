/*
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

        if(getPosition().getX() == pacMan.getPosition().getX() && getPosition().getY() < pacMan.getPosition().getY() && !maze.isWall(new Position(getPosition().getX(), getPosition().getY()+1))){
            setPosition(new Position(getPosition().getX(), getPosition().getY()+1));
        }

        else if(getPosition().getX() == pacMan.getPosition().getX() && getPosition().getY() > pacMan.getYCor() && !maze.isWall(new Position(getPosition().getX(), getPosition().getY()-1))){
            setPosition(new Position(getPosition().getX(), getPosition().getY()-1));
        }

        else if (getPosition().getX() < pacMan.getPosition().getX() && getPosition().getY() == pacMan.getPosition().getY() && !maze.isWall(new Position(getPosition().getX()+1, getPosition().getY()))){
            setPosition(new Position(getPosition().getX()+1, getPosition().getY()));
        }

        else if (getPosition().getX() > pacMan.getXCor() && getPosition().getY() == pacMan.getPosition().getY() && !maze.isWall(new Position(getPosition().getX()-1, getPosition().getY()))){
            setPosition(new Position(getPosition().getX()-1, getPosition().getY()));
        }

        else if (getPosition().getX() < pacMan.getPosition().getX() && getPosition().getY() < pacMan.getPosition().getY() && !maze.isWall(new Position(getPosition().getX()+1, getPosition().getY()))){
            setPosition(new Position(getPosition().getX()+1, getPosition().getY()));
        }

        else if (getPosition().getX() < pacMan.getPosition().getX() && getPosition().getY() < pacMan.getPosition().getY() && !maze.isWall(new Position(getPosition().getX(), getPosition().getY()+1))){
            setPosition(new Position(getPosition().getX(), getPosition().getY()+1));
        }

        else if (getPosition().getX() < pacMan.getPosition().getX() && getPosition().getY() > pacMan.getPosition().getY() && !maze.isWall(new Position(getPosition().getX(), getPosition().getY()-1))){
            setPosition(new Position(getPosition().getX(), getPosition().getY()-1));
        }

        else if (getPosition().getX() < pacMan.getPosition().getX() && getPosition().getY() > pacMan.getPosition().getY() && !maze.isWall(new Position(getPosition().getX()+1, getPosition().getY()))){
            setPosition(new Position(getPosition().getX()+1, getPosition().getY()));
        }

        else if (getPosition().getX() > pacMan.getPosition().getX() && getPosition().getY() < pacMan.getPosition().getY() && !maze.isWall(new Position(getPosition().getX()-1, getPosition().getY()))){
            setPosition(new Position(getPosition().getX()-1, getPosition().getY()));
        }

        else if (getPosition().getX() > pacMan.getPosition().getX() && getPosition().getY() < pacMan.getPosition().getY() && !maze.isWall(new Position(getPosition().getX(), getPosition().getY()+1))){
            setPosition(new Position(getPosition().getX(), getPosition().getY()+1));
        }

        else if (getPosition().getX() > pacMan.getPosition().getX() && getPosition().getY() > pacMan.getPosition().getY() && !maze.isWall(new Position(getPosition().getX(), getPosition().getY()-1))){
            setPosition(new Position(getPosition().getX(),getPosition().getY()-1));
        }

        else if (getPosition().getX() > pacMan.getPosition().getX() && getPosition().getY() > pacMan.getPosition().getY() && !maze.isWall(new Position(getPosition().getX()-1, getPosition().getY()))){
            setPosition(new Position(getPosition().getX()-1,getPosition().getY()));
        }
    }

    //Top Right Corner
    public void scatter(){
        setColour("#FF0000");
    }

    public void frightened(){
        setColour("#432AE8");
    }
}
*/
