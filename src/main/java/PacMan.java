import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class PacMan extends Element{
    public PacMan(int x, int y){
        super(x, y);
    }

    public void setPosition(Position position){
        pos = position;
    }
    public Position getPosition() {
        return pos;
    }
    public void moveUp(){
        pos.setY(pos.getY()-1);
    }
    public void moveDown(){
        pos.setY(pos.getY()+1);
    }
    public void moveLeft(){
        pos.setX(pos.getX()-1);
    }
    public void moveRight(){
        pos.setX(pos.getX()+1);
    }
    public void moveHero(Position pos){
        this.pos = pos;
    }

    public void draw (TextGraphics screen){
        screen.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        screen.putString(new TerminalPosition(pos.getX(),pos.getY()),"C");
    }
}
