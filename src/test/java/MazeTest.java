import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MazeTest {
    Maze maze;
    @BeforeEach
    public void setupMaze(){maze = new Maze(29,36);}
    @Test
    public void isWall(){
        Position a = new Position(14,26);       //posição inicial do Pac-Man
        Position b = new Position(14,25);       //parede -
        Position c = new Position(13,26);       //espaço livre
        Position d = new Position(14,25);       //parede +
        Position e = new Position(18,24);       //parede |
        Assertions.assertFalse(maze.isWall(a),"Identifies walls on empty spaces.");
        Assertions.assertTrue(maze.isWall(b),"Doesn't identify horizontal walls");
        Assertions.assertFalse(maze.isWall(c),"Identifies walls on empty spaces.");
        Assertions.assertTrue(maze.isWall(d),"Doesn't identify wall edges.");
        Assertions.assertTrue(maze.isWall(e),"Doesn't identify vertical walls");
    }

    @Test
    public void isFood(){
        Position a = new Position(14,26);       //posição inicial do Pac-Man
        Position b = new Position(14,25);       //parede -
        Position c = new Position(13,26);       //pellet normal
        Position d = new Position(14,25);       //parede +
        Position e = new Position(18,24);       //parede |
        Position f = new Position(14,1);       //Power-Up (não está a ser identificado)
        Assertions.assertFalse(maze.isFood(a), "Program is recognizing food where there is none.");
        Assertions.assertFalse(maze.isFood(b), "Program is recognizing food where there should be a wall.");
        Assertions.assertTrue(maze.isFood(c), "Program is not recognizing normal pellets.");
        Assertions.assertFalse(maze.isFood(d), "Program is recognizing food where it has a wall.");
        Assertions.assertFalse(maze.isFood(e), "Program is recognizing food where it has a wall.");
    }

    @Test
    public void isHole(){
        Assertions.assertTrue(maze.isHole(new Position(0, 17)), "Doesn't recognize hole.");
        Assertions.assertTrue(maze.isHole(new Position(28, 17)), "Doesn't recognize hole.");
        Assertions.assertFalse(maze.isHole(new Position(28, 18)), "Recognizes holes where there are walls.");
        Assertions.assertFalse(maze.isHole(new Position(0, 24)),"Recognizes holes where there are walls.");
        Assertions.assertTrue(maze.isHole(new Position(28,21)),"Doesn't recognize inaccessible holes");
        Assertions.assertTrue(maze.isHole(new Position(0,21)),"Doesn't recognize inaccessible holes");

    }

    @Test
    public void canPacMove(){
        Assertions.assertTrue(maze.canPacMove(Direction.LEFT));
        Assertions.assertTrue(maze.canPacMove(Direction.RIGHT));
        Assertions.assertFalse(maze.canPacMove(Direction.UP));
        Assertions.assertFalse(maze.canPacMove(Direction.DOWN));
        maze.setPacMan(22,27);
        Assertions.assertFalse(maze.canPacMove(Direction.LEFT));
        Assertions.assertFalse(maze.canPacMove(Direction.RIGHT));
        Assertions.assertTrue(maze.canPacMove(Direction.UP));
        Assertions.assertTrue(maze.canPacMove(Direction.DOWN));
    }
}
