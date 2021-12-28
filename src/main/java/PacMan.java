public class PacMan {
    public int x, y;
    public PacMan(int a, int b){
        x = a;
        y = b;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public void moveUp(){
        y -=1;
    }
    public void moveDown(){
        y+=1;
    }
    public void moveLeft(){
        x-=1;
    }
    public void moveRight(){
        x+=1;
    }
}
