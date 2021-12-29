import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class PacMan {
    public Position pos;
    public PacMan(Position p){
        pos = p;
    }

    public int getX() {
        return pos.getX();
    }

    public int getY() {
        return pos.getY();
    }

    public void setX(int x) {
        pos.setX(x);
    }

    public void setY(int y) {
        pos.setY(y);
    }
    public void moveUp(){pos.setY(pos.getY()-1);}
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
