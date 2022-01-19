import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;


public class PacMan extends Element {
    boolean executeEveryReload;
    int stateCode;
    public PacMan(int x, int y){
        super(x, y);
        executeEveryReload = false;
        stateCode = 0;
    }

    public Position getPosition() {
        return pos;
    }

    public void setPosition(Position position){
        pos = position;
    }

    public void moveHero(Position pos){
        this.pos = pos;
    }


    public void draw (TextGraphics graphics) throws InterruptedException {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        if (stateCode <= 3) {
            graphics.putString(new TerminalPosition(pos.getX(), pos.getY()), "C");
            stateCode++;
        }
        else if (stateCode > 3) {
            graphics.putString(new TerminalPosition(pos.getX(), pos.getY()), "O");
            if (stateCode == 6){
                stateCode = 1;
            }
            else{stateCode++;}
        }

        if (!executeEveryReload){
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFE600"));
            graphics.putString(12, 20, "READY!");
            graphics.putString(12, 20, "");
            executeEveryReload = true;
        }
    }
}