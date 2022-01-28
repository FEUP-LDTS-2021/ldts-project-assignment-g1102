import java.io.IOException;

public class FrightenedNormal implements Frightened{
    @Override
    public PosDir frightened(Position ghostP, Position pacmanP, Direction current) throws IOException {
        Maze maze = new Maze();
        Position newP = ghostP;
        int x = ghostP.getX(), y=ghostP.getY();
        switch(current){
            case UP:
                if (!maze.isWall(new Position(x,y-1)) && y <= pacmanP.getY() && !maze.isGhost(new Position(x,y-1))){
                    newP = new Position(x,y-1);
                    current=Direction.UP;
                }
                else if (!maze.isWall(new Position(x-1,y)) && x <= pacmanP.getX() && !maze.isGhost(new Position(x-1,y))){
                    newP = new Position(x-1,y);
                    current = Direction.LEFT;
                }
                else if (!maze.isWall(new Position(x+1,y)) && x >= pacmanP.getX() && !maze.isGhost(new Position(x+1,y))){
                    newP = new Position(x+1,y);
                    current = Direction.RIGHT;
                }
                else if (!maze.isWall(new Position(x,y+1)) && y >= pacmanP.getY() && !maze.isGhost(new Position(x,y+1))){
                    newP = new Position(x,y+1);
                    current = Direction.DOWN;
                }
                break;
            case DOWN:
                if (!maze.isWall(new Position(x,y+1)) && y >= pacmanP.getY() && !maze.isGhost(new Position(x,y+1))){
                    newP = new Position(x,y+1);
                    current = Direction.DOWN;
                }
                else if (!maze.isWall(new Position(x+1,y)) && x >= pacmanP.getX() && !maze.isGhost(new Position(x+1,y))){
                    newP = new Position(x+1,y);
                    current = Direction.RIGHT;
                }
                else if (!maze.isWall(new Position(x-1,y)) && x <= pacmanP.getX() && !maze.isGhost(new Position(x-1,y))){
                    newP = new Position(x-1,y);
                    current = Direction.LEFT;
                }
                else if (!maze.isWall(new Position(x,y-1)) && y <= pacmanP.getY() && !maze.isGhost(new Position(x,y-1))){
                    newP = new Position(x,y-1);
                    current=Direction.UP;
                }
                break;
            case RIGHT:
                if (!maze.isWall(new Position(x+1,y)) && x >= pacmanP.getX() && !maze.isGhost(new Position(x+1,y))){
                    newP = new Position(x+1,y);
                    current = Direction.RIGHT;
                }
                else if (!maze.isWall(new Position(x,y+1))&& y>=pacmanP.getY() && !maze.isGhost(new Position(x,y+1))){
                    newP = new Position(x,y+1);
                    current=Direction.DOWN;
                }
                else if (!maze.isWall(new Position(x,y-1)) && y <= pacmanP.getY() && !maze.isGhost(new Position(x,y-1))){
                    newP = new Position(x,y-1);
                    current = Direction.UP;
                }
                else if (!maze.isWall(new Position(x-1,y)) && x <= pacmanP.getX() && !maze.isGhost(new Position(x-1,y))){
                    newP = new Position(x-1,y);
                    current = Direction.LEFT;
                }
                break;
            case LEFT:
                if (!maze.isWall(new Position(x-1,y)) && x <= pacmanP.getX() && !maze.isGhost(new Position(x-1,y))){
                    newP = new Position(x-1,y);
                    current = Direction.LEFT;
                }
                else if (!maze.isWall(new Position(x,y-1)) && y <= pacmanP.getY() && !maze.isGhost(new Position(x,y-1))){
                    newP = new Position(x,y-1);
                    current = Direction.UP;
                }
                else if (!maze.isWall(new Position(x,y+1))&& y>=pacmanP.getY() && !maze.isGhost(new Position(x,y+1))){
                    newP = new Position(x,y+1);
                    current=Direction.DOWN;
                }
                else if (!maze.isWall(new Position(x+1,y)) && x >= pacmanP.getX() && !maze.isGhost(new Position(x+1,y))){
                    newP = new Position(x+1,y);
                    current = Direction.RIGHT;
                }
                break;
        }
        return new PosDir(newP,current);
    }
}