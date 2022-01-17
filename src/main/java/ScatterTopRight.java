public class ScatterTopRight implements Scatter{
    @Override
    public Position scatter(Position ghostP) {
        Maze maze = new Maze();
        int x = ghostP.getX();
        int y = ghostP.getY();
        Position p1 = new Position(22,4);
        Position p2 = new Position(27,8);
        Position newP = ghostP;
        if ((x >= 22 && x <= 27) && (y >= 4 && y <= 8)){
            if (!maze.isWall(new Position(x, y - 1))){ //move-se para cima
                newP = new Position (x, y - 1);
            }
            else if (!maze.isWall(new Position(x + 1, y))){ //move-se para a direita
                newP = new Position (x + 1, y);
            }
            else if (!maze.isWall(new Position(x, y + 1))){ //move-se para baixo
                newP = new Position (x, y + 1);
            }
            else if (!maze.isWall(new Position(x - 1, y))){ //move-se para a esquerda
                newP = new Position (x - 1, y);
            }
        }
        else{
            if (!maze.isWall(new Position(x, y - 1))){ //move-se para cima
                newP = new Position (x, y - 1);
            }
            else if (!maze.isWall(new Position(x + 1, y))){ //move-se para a direita
                newP = new Position (x + 1, y);
            }
            else if (!maze.isWall(new Position(x, y + 1))){
                newP = new Position (x, y + 1);
            }
            else if (!maze.isWall(new Position(x - 1, y))){
                newP = new Position (x - 1, y);
            }

        }
        return newP;
    }
}
