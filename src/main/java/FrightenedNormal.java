public class FrightenedNormal implements Frightened{
    @Override
    public Position frightened(Position ghostP, Position pacmanP){
        Maze maze = new Maze();
        Position pos = ghostP;

        /*for (Food f : maze.foods){
            if (f.getCharacter() == 'o' && (pacmanP.getX() == f.getXCor()) && (pacmanP.getY() == f.getYCor())){
                for (Ghost ghost : maze.ghosts){
                    ghost.setColour("#0C14F2");
                }
            }
        }*/

        if ((pacmanP.getX() < ghostP.getX()) && (pacmanP.getY()) == ghostP.getY() && !maze.isWall(new Position(ghostP.getX()+1, ghostP.getY()))){
            pos = new Position(ghostP.getX()+1, ghostP.getY());
        }

        else if ((pacmanP.getX() == ghostP.getX()) && (pacmanP.getY()) < ghostP.getY() && !maze.isWall(new Position(ghostP.getX(), ghostP.getY()+1))){
            pos = new Position(ghostP.getX(), ghostP.getY()+1);
        }

        else if ((pacmanP.getX() > ghostP.getX()) && (pacmanP.getY()) == ghostP.getY() && !maze.isWall(new Position(ghostP.getX()-1, ghostP.getY()))){
            pos = new Position(ghostP.getX()-1, ghostP.getY());
        }

        else if ((pacmanP.getX() == ghostP.getX()) && (pacmanP.getY()) > ghostP.getY() && !maze.isWall(new Position(ghostP.getX(), ghostP.getY()-1))){
            pos = new Position(ghostP.getX(), ghostP.getY()-1);
        }

        else if ((pacmanP.getX() < ghostP.getX()) && (pacmanP.getY()) < ghostP.getY() && !maze.isWall(new Position(ghostP.getX()+1, ghostP.getY()))){
            pos = new Position(ghostP.getX()+1, ghostP.getY());
        }

        else if ((pacmanP.getX() < ghostP.getX()) && (pacmanP.getY()) < ghostP.getY() && !maze.isWall(new Position(ghostP.getX(), ghostP.getY()+1))){
            pos = new Position(ghostP.getX(), ghostP.getY()+1);
        }

        else if ((pacmanP.getX() > ghostP.getX()) && (pacmanP.getY()) > ghostP.getY() && !maze.isWall(new Position(ghostP.getX()-1, ghostP.getY()))){
            pos = new Position(ghostP.getX()-1, ghostP.getY());
        }

        else if ((pacmanP.getX() > ghostP.getX()) && (pacmanP.getY()) > ghostP.getY() && !maze.isWall(new Position(ghostP.getX(), ghostP.getY()-1))){
            pos = new Position(ghostP.getX(), ghostP.getY()-1);
        }

        else if ((pacmanP.getX() > ghostP.getX()) && (pacmanP.getY()) < ghostP.getY() && !maze.isWall(new Position(ghostP.getX()-1, ghostP.getY()))){
            pos = new Position(ghostP.getX()-1, ghostP.getY());
        }

        else if ((pacmanP.getX() > ghostP.getX()) && (pacmanP.getY()) < ghostP.getY() && !maze.isWall(new Position(ghostP.getX(), ghostP.getY()+1))){
            pos = new Position(ghostP.getX(), ghostP.getY()+1);
        }

        else if ((pacmanP.getX() < ghostP.getX()) && (pacmanP.getY()) > ghostP.getY() && !maze.isWall(new Position(ghostP.getX()+1, ghostP.getY()))){
            pos = new Position(ghostP.getX()+1, ghostP.getY());
        }

        else if ((pacmanP.getX() < ghostP.getX()) && (pacmanP.getY()) > ghostP.getY() && !maze.isWall(new Position(ghostP.getX(), ghostP.getY()-1))){
            pos = new Position(ghostP.getX(), ghostP.getY()-1);
        }

        return pos;
    }
}
