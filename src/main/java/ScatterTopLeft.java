public class ScatterTopLeft implements Scatter{
    @Override
    public Position scatter(Position ghostP, Direction current) {
        Maze m = new Maze();
        int x = ghostP.getX(), y = ghostP.getY();
        Position newP = ghostP;
        Position cA = new Position(1, 4), cB = new Position(6, 8);
        if ((x >= 1 && x <= 6) && (y >= 4 && y <= 8)) {        //verifica se fantasma está no quadrante especificado
            if (!m.isWall(new Position(x, y - 1)) && (y - 1 >= 4)) { //se puder mover-se para cima, move-se
                newP = new Position (x,y-1);
                current = Direction.UP;
            }
            else if (!m.isWall(new Position(x - 1, y)) && (x - 1 >= 1)) {   //se puder mover-se para a esquerda, move-se
                newP = new Position(x - 1, y);
                current = Direction.LEFT;
            }
            else if (!m.isWall(new Position(x, y + 1))){ //move-se para baixo
                newP = new Position (x, y + 1);
                current = Direction.DOWN;
            }
            else if (!m.isWall(new Position(x + 1, y))){ //move-se para a direita
                newP = new Position (x + 1, y);
                current = Direction.RIGHT;
            }
        }
        else{
            if (!m.isWall(new Position(x, y - 1))) { //se puder mover-se para cima, move-se
                newP = new Position (x,y-1);
                current = Direction.UP;
            }
            else if (!m.isWall(new Position(x - 1, y))) {   //se puder mover-se para a esquerda, move-se
                newP = new Position(x - 1, y);
                current = Direction.LEFT;
            }
            else{
                int outcome = (int)Math.random() * 2 + 1; //1 ou 2 aleatoriamente
                switch (outcome){
                    case 1:
                        if (!m.isWall(new Position(x, y + 1))) { //tenta mover-se para baixo primeiro
                            newP = new Position (x,y+1);
                            current = Direction.DOWN;
                        }
                        else if (!m.isWall(new Position(x + 1, y))) {   //tenta mover-se para a direita se não puder para baixo
                            newP = new Position(x + 1, y);
                            current = Direction.RIGHT;
                        }
                    case 2:
                        if (!m.isWall(new Position(x + 1, y))) {   //tenta mover-se para a direita primeiro
                            newP = new Position(x + 1, y);
                            current = Direction.RIGHT;
                        }
                        else if (!m.isWall(new Position(x, y + 1))) { //tenta mover-se para baixo se não conseguir ir para a direita
                            newP = new Position (x,y+1);
                            current = Direction.DOWN;
                        }
                }
            }
        }
        return newP;

    }
}
