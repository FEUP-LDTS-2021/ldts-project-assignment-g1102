import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MazeTest {
    @Test
    public void isWall(){
        Maze mazeTest = new Maze(29,36);
        Position a = new Position(14,26);       //posição inicial do Pac-Man
        Position b = new Position(14,25);       //parede -
        Position c = new Position(13,26);       //espaço livre
        Position d = new Position(14,25);       //parede +
        Position e = new Position(18,24);       //parede |
        Assertions.assertEquals(false,mazeTest.isWall(a));
        Assertions.assertEquals(true,mazeTest.isWall(b));
        Assertions.assertEquals(false,mazeTest.isWall(c));
        Assertions.assertEquals(true,mazeTest.isWall(d));
        Assertions.assertEquals(true,mazeTest.isWall(e));
    }

    @Test
    public void isFood(){
        Maze mazeTest = new Maze(29,36);
        Position a = new Position(14,26);       //posição inicial do Pac-Man
        Position b = new Position(14,25);       //parede -
        Position c = new Position(13,26);       //pellet normal
        Position d = new Position(14,25);       //parede +
        Position e = new Position(18,24);       //parede |
        Position f = new Position(31,24);       //Power-Up
        Assertions.assertEquals(false,mazeTest.isFood(a));
        Assertions.assertEquals(false,mazeTest.isFood(b));
        Assertions.assertEquals(true,mazeTest.isFood(c));
        Assertions.assertEquals(false,mazeTest.isFood(d));
        Assertions.assertEquals(false,mazeTest.isFood(e));
        Assertions.assertEquals(true,mazeTest.isFood(f));   //este teste não passa mas classe Power-Up provavelmente será necessária em breve
    }

    @Test
    public void isFruit(){

    }

    @Test
    public void isHole(){
        Maze mazeTest = new Maze(29,36);
        Assertions.assertEquals(mazeTest.isHole(new Position(0,20)),true);
        Assertions.assertEquals(mazeTest.isHole(new Position(36,20)),true);
        Assertions.assertEquals(mazeTest.isHole(new Position(36,21)),false);
        Assertions.assertEquals(mazeTest.isHole(new Position(36,21)),true); //Hole sem acesso
    }

    @Test
    public void canPacMove(){
        Maze mazeTest = new Maze(29,36);
        Assertions.assertEquals(mazeTest.canPacMove(Direction.LEFT),true);
        Assertions.assertEquals(mazeTest.canPacMove(Direction.RIGHT),true);
        Assertions.assertEquals(mazeTest.canPacMove(Direction.UP),false);
        Assertions.assertEquals(mazeTest.canPacMove(Direction.DOWN),false);
        mazeTest.setPacMan(22,27);
        Assertions.assertEquals(mazeTest.canPacMove(Direction.LEFT),false);
        Assertions.assertEquals(mazeTest.canPacMove(Direction.RIGHT),false);
        Assertions.assertEquals(mazeTest.canPacMove(Direction.UP),true);
        Assertions.assertEquals(mazeTest.canPacMove(Direction.DOWN),true);
    }
}
