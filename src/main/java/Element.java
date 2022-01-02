import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Element {
    private int x;
    private int y;
    protected Position pos;

    public Element(int x, int y){
        pos = new Position(x, y);
    }

    public Position getPosition(){
        return pos;
    }

    public int getXCor(){
        return pos.getX();
    }

    public int getYCor(){
        return pos.getY();
    }
    public abstract void draw(TextGraphics graphics) throws InterruptedException;
}
