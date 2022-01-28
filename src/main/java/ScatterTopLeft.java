import java.io.IOException;

public class ScatterTopLeft implements Scatter{
    @Override
    public PosDir scatter(Position ghostP, Direction current) throws IOException {
        Maze maze = new Maze();
        int x = ghostP.getX(), y = ghostP.getY();
        Position newP = ghostP;
        Position cA = new Position(1, 4), cB = new Position(6, 8);
        if ((x >= 1 && x <= 6) && (y >= 4 && y <= 8)) {        //verifica se fantasma está no quadrante especificado
            if (!maze.isWall(new Position(x, y - 1)) && (y - 1 >= 4) && !maze.isGhost(new Position(x,y-1))) { //se puder mover-se para cima, move-se
                newP = new Position (x,y-1);
                current = Direction.UP;
            }
            else if (!maze.isWall(new Position(x - 1, y)) && (x - 1 >= 1) && !maze.isGhost(new Position(x-1,y))) {   //se puder mover-se para a esquerda, move-se
                newP = new Position(x - 1, y);
                current = Direction.LEFT;
            }
            else if (!maze.isWall(new Position(x, y + 1)) && !maze.isGhost(new Position(x,y+1))){ //move-se para baixo
                newP = new Position (x, y + 1);
                current = Direction.DOWN;
            }
            else if (!maze.isWall(new Position(x + 1, y)) && !maze.isGhost(new Position(x+1,y))){ //move-se para a direita
                newP = new Position (x + 1, y);
                current = Direction.RIGHT;
            }
        }
        else{switch(current){
            case UP:
                if (!maze.isWall(new Position(x,y-1)) && !maze.isGhost(new Position(x,y-1))){
                    newP = new Position(x,y-1);
                    current=Direction.UP;
                }
                else if (!maze.isWall(new Position(x-1,y)) && !maze.isGhost(new Position(x-1,y))){
                    newP = new Position(x-1,y);
                    current = Direction.LEFT;
                }
                else if (!maze.isWall(new Position(x+1,y)) && !maze.isGhost(new Position(x+1,y))){
                    newP = new Position(x+1,y);
                    current = Direction.RIGHT;
                }
                break;
            case DOWN:
                if (!maze.isWall(new Position(x,y+1)) && !maze.isGhost(new Position(x,y+1))){
                    newP = new Position(x,y+1);
                    current =Direction.DOWN;
                }
                else if (!maze.isWall(new Position(x-1,y)) && !maze.isGhost(new Position(x-1,y))){
                    newP = new Position(x-1,y);
                    current = Direction.LEFT;
                }
                else if (!maze.isWall(new Position(x+1,y)) && !maze.isGhost(new Position(x+1,y))){
                    newP = new Position(x+1,y);
                    current=Direction.RIGHT;
                }
                break;
            case LEFT:
                if (!maze.isWall(new Position(x-1,y)) && !maze.isGhost(new Position(x-1,y))){
                    newP = new Position(x-1,y);
                    current=Direction.LEFT;
                }
                else if (!maze.isWall(new Position(x,y-1)) && !maze.isGhost(new Position(x,y-1))){
                    newP = new Position(x,y-1);
                    current =Direction.UP;
                }
                else if (!maze.isWall(new Position(x,y+1)) && !maze.isGhost(new Position(x,y+1))){
                    newP = new Position(x,y+1);
                    current =Direction.DOWN;
                }
                break;
            case RIGHT:
                if (!maze.isWall(new Position(x+1,y)) && !maze.isGhost(new Position(x+1,y))){
                    newP = new Position(x+1,y);
                    current=Direction.RIGHT;
                }
                else if (!maze.isWall(new Position(x,y-1)) && !maze.isGhost(new Position(x,y-1))){
                    newP = new Position(x,y-1);
                    current =Direction.UP;
                }
                else if (!maze.isWall(new Position(x,y+1)) && !maze.isGhost(new Position(x,y+1))){
                    newP = new Position(x,y+1);
                    current =Direction.DOWN;
                }
                break;
        }
        }
        return new PosDir(newP,current);

    }

    @Override
    public boolean isInQuadrant(Position ghostP) {
        int x = ghostP.getX(), y= ghostP.getY();
        return (x >= 1 && x <= 6) && (y >= 4 && y <= 8);
    }
}
