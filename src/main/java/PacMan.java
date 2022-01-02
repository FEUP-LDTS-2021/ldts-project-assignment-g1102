import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.concurrent.TimeUnit;

public class PacMan extends Element{
    boolean executeEveryReload;
    public PacMan(int x, int y){
        super(x, y);
        executeEveryReload = false;
    }

    public void setPosition(Position position){
        pos = position;
    }
    public Position getPosition() {
        return pos;
    }
/*    public void moveUp(){
        pos.setVelY(-5);
        pos.tick();
    }
    public void moveDown(){
        pos.setVelY(5);
        pos.tick();
    }
    public void moveLeft(){
        pos.setVelX(-5);
        pos.tick();
    }
    public void moveRight(){
        pos.setVelX(1);
        pos.tick();
    }*/
    public void moveHero(Position pos){
        this.pos = pos;
    }


    public void draw (TextGraphics screen) throws InterruptedException {
        screen.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        screen.putString(new TerminalPosition(pos.getX(),pos.getY()),"C");

        if (!executeEveryReload){
            screen.setForegroundColor(TextColor.Factory.fromString("#FFE600"));
            screen.putString(12, 20, "READY!");
            screen.putString(12, 20, "");
            executeEveryReload = true;
        }
    }
}
