public class ScatterBottomLeft implements Scatter{
    @Override
    public PosDir scatter(Position ghostP, Direction current) {
        Maze maze = new Maze();
        int x = ghostP.getX(), y = ghostP.getY();
        Position newP = ghostP;

        if (((x >= 1 && x <= 6) || (x >= 9 && x <= 13)) && (y >= 27 && y <= 30)){     //verifica se está no quadrante
            if (!maze.isWall(new Position(x, y+1)) && ((y + 1 >= 27))){     //verifica se pode mover-se para cima
                newP = new Position(x, y+1);
            }

            else if (!maze.isWall(new Position(x-1, y))&& ((x-1 >= 1) && (x-1 >= 9))){     //verifica se pode mover-se para a esquerda
                newP = new Position(x-1, y);
            }

            else if (!maze.isWall(new Position(x, y+1)) && !maze.isHole(new Position(x, y+1))){
                newP = new Position(x, y+1);
            }

            else if (!maze.isWall(new Position(x-1, y)) && !maze.isHole(new Position(x-1, y))){
                newP = new Position(x-1, y);
            }
        }
        else{
            switch(current){
                case UP:
                    if (!maze.isWall(new Position(x,y-1))){
                        newP = new Position(x,y-1);
                        current=Direction.UP;
                    }
                    else if (!maze.isWall(new Position(x-1,y))){
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
                    else if (!maze.isWall(new Position(x-1,y))){
                        newP = new Position(x-1,y);
                        current = Direction.LEFT;
                    }
                    else if (!maze.isWall(new Position(x+1,y))){
                        newP = new Position(x+1,y);
                        current=Direction.RIGHT;
                    }
                    break;
                case LEFT:
                    if (!maze.isWall(new Position(x-1,y))){
                        newP = new Position(x-1,y);
                        current=Direction.LEFT;
                    }
                    else if (!maze.isWall(new Position(x,y+1))){
                        newP = new Position(x,y+1);
                        current =Direction.DOWN;
                    }
                    else if (!maze.isWall(new Position(x,y-1))){
                        newP = new Position(x,y-1);
                        current =Direction.UP;
                    }
                    break;
                case RIGHT:
                    if (!maze.isWall(new Position(x+1,y))){
                        newP = new Position(x+1,y);
                        current=Direction.RIGHT;
                    }
                    else if (!maze.isWall(new Position(x,y+1))){
                        newP = new Position(x,y+1);
                        current =Direction.DOWN;
                    }
                    else if (!maze.isWall(new Position(x,y-1))){
                        newP = new Position(x,y-1);
                        current =Direction.UP;
                    }
                    break;
            }
        }

        /*else {
            if (!maze.isWall(new Position(x, y+1)) && !maze.isHole(new Position(x, y+1))){
                newP = new Position(x, y+1);
            }

            else if (!maze.isWall(new Position(x-1, y)) && !maze.isHole(new Position(x-1, y))){
                newP = new Position(x-1, y);
            }

            else {
                int outcome = (int)(Math.random()*2 + 1);

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

                        else if (!maze.isWall(new Position(x, y-1)) && !maze.isHole(new Position(x, y-1  ))){
                            newP = new Position(x, y-1);
                        }
                }
            }

        }*/

        return new PosDir(newP,current);
    }
}