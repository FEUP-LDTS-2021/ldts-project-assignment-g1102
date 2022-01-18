public class ScatterBottomLeft implements Scatter{
    @Override
    public Position scatter(Position ghostP, Direction current) {
        Maze maze = new Maze();
        int x = ghostP.getX(), y = ghostP.getY();
        Position newP = ghostP;

        if (((x >= 1 && x <= 6) || (x >= 9 && x <= 13)) && (y >= 27 && y <= 30)){     //verifica se está no quadrante
            if (!maze.isWall(new Position(x, y+1)) && ((y + 1 >= 27))){     //verifica se pode mover-se para cima
                newP = new Position(x, y+1);
            }

            else if (!maze.isWall(new Position(x-1, y) )&& ((x-1 >= 1) || (x-1 >= 9))){     //verifica se pode mover-se para a esquerda
                newP = new Position(x-1, y);
            }

            else if (!maze.isWall(new Position(x, y+1)) && !maze.isHole(new Position(x, y+1))){
                newP = new Position(x, y+1);
            }

            else if (!maze.isWall(new Position(x-1, y)) && !maze.isHole(new Position(x-1, y))){
                newP = new Position(x-1, y);
            }
        }

        else {
            if (!maze.isWall(new Position(x, y+1)) && !maze.isHole(new Position(x, y+1))){
                newP = new Position(x, y+1);
            }

            else if (!maze.isWall(new Position(x-1, y)) && !maze.isHole(new Position(x-1, y))){
                newP = new Position(x-1, y);
            }

            else {
                int outcome = (int)Math.random()*2 + 1;

                switch(outcome){
                    case 1:
                        if (!maze.isWall(new Position(x, y-1)) && !maze.isHole(new Position(x, y-1))){
                            newP = new Position(x, y-1);
                        }

                        else if (!maze.isWall(new Position(x+1, y)) && !maze.isHole(new Position(x+1, y))){
                            newP = new Position(x+1, y);
                        }

                    case 2:
                        if (!maze.isWall(new Position(x+1, y)) && !maze.isHole(new Position(x+1, y))){
                            newP = new Position(x+1, y);
                        }

                        else if (!maze.isWall(new Position(x, y-1)) && !maze.isHole(new Position(x, y-1))){
                            newP = new Position(x, y-1);
                        }
                }
            }

        }

        return newP;
    }
}