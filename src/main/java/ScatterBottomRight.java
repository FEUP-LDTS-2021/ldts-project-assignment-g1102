public class ScatterBottomRight implements Scatter {
    @Override
    public PosDir scatter(Position ghostP, Direction current) {
        Maze maze = new Maze();
        int x = ghostP.getX(), y = ghostP.getY();
        Position newP = ghostP;
        Position c1 = new Position(16, 27), c2 = new Position(23, 30);

        if (((x >= 16 && x <= 20) || (x >= 23 && x <=29)) && (y >= 27 && x <= 30)){
            if (!maze.isWall(new Position(x, y+1)) && (y + 1 >= 27) && !maze.isGhost(new Position(x,y+1))){
                newP = new Position(x, y+1);
                current=Direction.DOWN;
            }

            else if (!maze.isWall(new Position(x+1, y)) && ((x+1 >= 16) && (x+1 >= 23)) && !maze.isGhost(new Position(x+1,y))){
                newP = new Position(x+1, y);
                current = Direction.RIGHT;
            }

            else if (!maze.isWall(new Position(x, y-1)) && !maze.isHole(new Position(x, y-1)) && !maze.isGhost(new Position(x,y-1))){
                newP = new Position(x, y-1);
                current = Direction.UP;
            }

            else if (!maze.isWall(new Position(x-1, y)) && !maze.isHole(new Position(x-1, y)) && !maze.isGhost(new Position(x-1,y))){
                newP = new Position(x-1, y);
            }
        }

        else {
            switch(current){
                case UP:
                    if (!maze.isWall(new Position(x,y-1)) && !maze.isGhost(new Position(x,y-1))){
                        newP = new Position(x,y-1);
                        current=Direction.UP;
                    }
                    else if (!maze.isWall(new Position(x+1,y)) && !maze.isGhost(new Position(x+1,y))){
                        newP = new Position(x+1,y);
                        current = Direction.RIGHT;
                    }
                    else if (!maze.isWall(new Position(x-1,y)) && !maze.isGhost(new Position(x-1,y))){
                        newP = new Position(x-1,y);
                        current = Direction.LEFT;
                    }
                    break;
                case DOWN:
                    if (!maze.isWall(new Position(x,y+1)) && !maze.isGhost(new Position(x,y+1))){
                        newP = new Position(x,y+1);
                        current =Direction.DOWN;
                    }
                    else if (!maze.isWall(new Position(x+1,y)) && !maze.isGhost(new Position(x+1,y))){
                        newP = new Position(x+1,y);
                        current=Direction.RIGHT;
                    }
                    else if (!maze.isWall(new Position(x-1,y)) && !maze.isGhost(new Position(x-1,y))){
                        newP = new Position(x-1,y);
                        current = Direction.LEFT;
                    }
                    break;
                case LEFT:
                    if (!maze.isWall(new Position(x-1,y)) && !maze.isGhost(new Position(x-1,y))){
                        newP = new Position(x-1,y);
                        current=Direction.LEFT;
                    }
                    else if (!maze.isWall(new Position(x,y+1)) && !maze.isGhost(new Position(x,y+1))){
                        newP = new Position(x,y+1);
                        current =Direction.DOWN;
                    }
                    else if (!maze.isWall(new Position(x,y-1)) && !maze.isGhost(new Position(x,y-1))){
                        newP = new Position(x,y-1);
                        current =Direction.UP;
                    }
                    break;
                case RIGHT:
                    if (!maze.isWall(new Position(x+1,y)) && !maze.isGhost(new Position(x+1,y))){
                        newP = new Position(x+1,y);
                        current=Direction.RIGHT;
                    }
                    else if (!maze.isWall(new Position(x,y+1)) && !maze.isGhost(new Position(x,y+1))){
                        newP = new Position(x,y+1);
                        current =Direction.DOWN;
                    }
                    else if (!maze.isWall(new Position(x,y-1)) && !maze.isGhost(new Position(x,y-1))){
                        newP = new Position(x,y-1);
                        current =Direction.UP;
                    }
                    break;
            }
        }
        return new PosDir(newP,current);
    }
}