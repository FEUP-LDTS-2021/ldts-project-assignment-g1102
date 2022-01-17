public class ScatterBottomRight implements Scatter {
    @Override
    public Position scatter(Position ghostP) {
        Maze maze = new Maze();
        int x = ghostP.getX(), y = ghostP.getY();
        Position newP = ghostP;
        Position c1 = new Position(16, 27), c2 = new Position(23, 30);
                //20                                            //29

        if ((x >= 16 && x <= 20) || (x >= 23 && x <=29) || (y >= 27 && x <= 30)){
            if (!maze.isWall(new Position(x, y+1)) && (y + 1 >= 27)){
                newP = new Position(x, y+1);
            }

            else if (!maze.isWall(new Position(x+1, y)) && ((x+1 >= 16) || (x+1 >= 23))){
                newP = new Position(x+1, y);
            }
        }

        else {
            if (!maze.isWall(new Position(x, y+1))){
                newP = new Position(x, y+1);
            }

            else if (!maze.isWall(new Position(x+1, y))){
                newP = new Position(x+1, y);
            }

            else {
                int outcome = (int)Math.random()*2 + 1;

                switch(outcome){
                    case 1:
                        if (!maze.isWall(new Position(x, y-1))){
                            newP = new Position(x, y-1);
                        }

                        else if (!maze.isWall(new Position(x+1, y))){
                            newP = new Position(x+1, y);
                        }

                    case 2:
                        if (!maze.isWall(new Position(x+1, y))){
                            newP = new Position(x+1, y);
                        }

                        else if (!maze.isWall(new Position(x, y-1))){
                            newP = new Position(x, y-1);
                        }
                }
            }
        }
        return newP;
    }
}
