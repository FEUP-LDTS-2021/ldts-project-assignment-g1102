import java.io.IOException;

public class ScatterTopRight implements Scatter{
    @Override
    public PosDir scatter(Position ghostP, Direction current) throws IOException {
        Maze maze = new Maze();
        int x = ghostP.getX();
        int y = ghostP.getY();
        Position p1 = new Position(22,4);
        Position p2 = new Position(27,8);
        Position newP = ghostP;
        if (isInQuadrant(ghostP)){
            if (!maze.isWall(new Position(x, y - 1)) && !maze.isGhost(new Position(x,y-1))){ //move-se para cima
                newP = new Position (x, y - 1);
                current = Direction.UP;
            }
            else if (!maze.isWall(new Position(x + 1, y)) && !maze.isGhost(new Position(x+1,y))){ //move-se para a direita
                newP = new Position (x + 1, y);
                current = Direction.RIGHT;
            }
            else if (!maze.isWall(new Position(x, y + 1)) && !maze.isGhost(new Position(x,y+1))){ //move-se para baixo
                newP = new Position (x, y + 1);
                current = Direction.DOWN;
            }
            else if (!maze.isWall(new Position(x - 1, y)) && !maze.isGhost(new Position(x-1,y))){ //move-se para a esquerda
                newP = new Position (x - 1, y);
                current = Direction.LEFT;
            }
        }
        else{
            if (!maze.isWall(new Position(x, y - 1)) && !maze.isGhost(new Position(x,y-1))){ //move-se para cima
                newP = new Position (x, y - 1);
                current = Direction.UP;
            }
            else if (!maze.isWall(new Position(x + 1, y)) && !maze.isGhost(new Position(x+1,y))){ //move-se para a direita
                newP = new Position (x + 1, y);
                current = Direction.RIGHT;
            }
            else if (!maze.isWall(new Position(x, y + 1)) && !maze.isGhost(new Position(x,y+1))){
                newP = new Position (x, y + 1);
                current = Direction.DOWN;
            }
            else if (!maze.isWall(new Position(x - 1, y)) && !maze.isGhost(new Position(x-1,y))){
                newP = new Position (x - 1, y);
                current = Direction.LEFT;
            }

        }
        return new PosDir(newP,current);
    }

    @Override
    public boolean isInQuadrant(Position ghostP) {
        int x = ghostP.getX(), y= ghostP.getY();
        return (x >= 22 && x <= 27) && (y >= 4 && y <= 8);
    }
}
