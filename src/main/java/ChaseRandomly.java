public class ChaseRandomly implements Chase{
    @Override
    public Position chase(Position ghostP, Position pacmanP) {
        Maze maze = new Maze();
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
    }
}
//guardar a posição anterior para que o pacman se mova sempre na posição do frame anterior a não ser que encontre uma wall. Caso encontre, movê-lo numa direção diferente, guardá-la, e aplicar o mesmo processo.