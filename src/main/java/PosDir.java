public class PosDir {
    Position pos;
    Direction dir;

    PosDir(Position p, Direction d){
        pos = p;
        dir = d;
    }

    public Position getPos() {
        return pos;
    }

    public Direction getDir() {
        return dir;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }
}
