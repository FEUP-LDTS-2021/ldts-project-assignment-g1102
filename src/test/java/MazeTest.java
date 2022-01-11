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
}
