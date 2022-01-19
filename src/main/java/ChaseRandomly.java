import java.io.IOException;

public class ChaseRandomly implements Chase{
    @Override
    public PosDir chase(Position ghostP, Position pacmanP, Direction current) throws IOException {
        Maze maze = new Maze();
        int x = ghostP.getX();
        int y = ghostP.getY();
        Position newP = ghostP;
        switch(current){
            case UP:
                if (!maze.isWall(new Position(x,y-1))){
                    newP = new Position(x,y-1);
                    current=Direction.UP;
                }
                else if (!maze.isWall(new Position(x-1,y)) && x>pacmanP.getX()){
                    newP = new Position(x-1,y);
                    current = Direction.LEFT;
                }
                else if (!maze.isWall(new Position(x+1,y))){
                    newP = new Position(x+1,y);
                    current = Direction.RIGHT;
                }
                break;
            case DOWN:
                if (!maze.isWall(new Position(x,y+1))){
                    newP = new Position(x,y+1);
                    current =Direction.DOWN;
                }
                else if (!maze.isWall(new Position(x+1,y)) && x<pacmanP.getX()){
                    newP = new Position(x+1,y);
                    current=Direction.RIGHT;
                }
                else if (!maze.isWall(new Position(x-1,y))){
                    newP = new Position(x-1,y);
                    current = Direction.LEFT;
                }
                break;
            case LEFT:
                if (!maze.isWall(new Position(x-1,y))){
                    newP = new Position(x-1,y);
                    current=Direction.LEFT;
                }
                else if (!maze.isWall(new Position(x,y-1)) && y>pacmanP.getY()){
                    newP = new Position(x,y-1);
                    current =Direction.UP;
                }
                else if (!maze.isWall(new Position(x,y+1))){
                    newP = new Position(x,y+1);
                    current =Direction.DOWN;
                }
                break;
            case RIGHT:
                if (!maze.isWall(new Position(x+1,y))){
                    newP = new Position(x+1,y);
                    current=Direction.RIGHT;
                }
                else if (!maze.isWall(new Position(x,y+1)) && y<pacmanP.getY()){
                    newP = new Position(x,y+1);
                    current =Direction.DOWN;
                }
                else if (!maze.isWall(new Position(x,y-1))){
                    newP = new Position(x,y-1);
                    current =Direction.UP;
                }
                break;
        }
        return new PosDir(newP,current);
        /*Maze maze = new Maze();
        int x = ghostP.getX();
        int y = ghostP.getY();
        Position newP = ghostP;
        if (!maze.isWall(new Position(x, y - 1))){
            newP = new Position(x, y - 1);
        }
        else{
            if (!maze.isWall(new Position(x - 1, y))){
                newP = new Position(x - 1, y);
            }
            else if (maze.isWall(new Position(x - 1, y)) && !maze.isWall(new Position(x + 1, y))){
                newP = new Position(x + 1, y);
            }
        }

        if (!maze.isWall(new Position(x, y + 1))){
            newP = new Position(x, y + 1);
        }
        else{
            if (!maze.isWall(new Position(x + 1, y))){
                newP = new Position(x + 1, y);
            }
            else if (maze.isWall(new Position(x + 1, y)) && !maze.isWall(new Position(x - 1, y))){
                newP = new Position(x - 1, y);
            }
        }
        if (!maze.isWall(new Position(x - 1, y))){
            newP = new Position(x - 1, y);
        }
        else{
            if (!maze.isWall(new Position(x, y + 1))){
                newP = new Position(x, y + 1);
            }
            else if (maze.isWall(new Position(x, y + 1)) && !maze.isWall(new Position(x, y - 1))){
                newP = new Position(x, y - 1);
            }
        }

        if (!maze.isWall(new Position(x + 1, y))){
            newP = new Position(x + 1, y);
        }
        else{
            if (!maze.isWall(new Position(x, y - 1))){
                newP = new Position(x, y - 1);
            }
            else if (maze.isWall(new Position(x, y - 1)) && !maze.isWall(new Position(x, y + 1))){
                newP = new Position(x, y + 1);
            }
        }
        return newP;
    */}
}
//guardar a posição anterior para que o pacman se mova sempre na posição do frame anterior a não ser que encontre uma wall. Caso encontre, movê-lo numa direção diferente, guardá-la, e aplicar o mesmo processo.