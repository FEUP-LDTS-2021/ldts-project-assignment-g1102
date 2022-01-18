public class ChaseAgressively implements Chase{
    @Override
    public Position chase(Position ghostP, Position pacmanP){
        Maze maze = new Maze();
        int x = ghostP.getX();
        int y = ghostP.getY();
        Position newP = ghostP;

        if (pacmanP.getX() > ghostP.getX()){
            if (!maze.isWall(new Position (x + 1, y))) {
                newP = new Position(x + 1, y);
            }
            else{
                int randDirection = (int)(Math.random()*2 + 1);
                if (randDirection == 1){
                    newP = new Position(x, y - 1);
                }
                else{
                    newP = new Position(x, y + 1);
                }
            }
        }
        else if (pacmanP.getX() < ghostP.getX()){
            if (!maze.isWall(new Position (x - 1, y))){
                newP = new Position (x - 1, y);
            }
            else{
                int randDirection = (int)(Math.random()*2 + 1);
                if (randDirection == 1){
                    newP = new Position(x, y - 1);
                }
                else{
                    newP = new Position(x, y + 1);
                }
            }
        }
        else if (pacmanP.getY() > ghostP.getY()){
            if (!maze.isWall(new Position (x, y + 1))){
                newP = new Position (x, y + 1);
            }
            else{
                int randDirection = (int)(Math.random()*2 + 1);
                if (randDirection == 1){
                    newP = new Position(x - 1, y);
                }
                else{
                    newP = new Position(x + 1, y);
                }
            }
        }
        else if (pacmanP.getY() < ghostP.getY()){
            if (!maze.isWall(new Position (x, y - 1))){
                newP = new Position (x, y - 1);
            }
            else{
                int randDirection = (int)(Math.random()*2 + 1);
                if (randDirection == 1){
                    newP = new Position(x - 1, y);
                }
                else{
                    newP = new Position(x + 1, y);
                }
            }
        }
        return newP;
    }
}
