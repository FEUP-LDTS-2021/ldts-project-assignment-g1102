import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class PacManTest {
    Maze m = new Maze();

    public PacManTest() throws IOException {
    }

    @BeforeEach
    public void setup(){
        m.setPacMan(14,26);
    }
    @Test
    public void Pacman(){
        PacMan p = new PacMan(14,26);
        Assertions.assertEquals(14,p.getXCor(),"Not building the correct x coordinate.");
        Assertions.assertEquals(26,p.getYCor(),"Not building the correct y coordinate.");
    }
    @Test
    public void getPosition(){
        Assertions.assertEquals(m.getPacman().getPosition(), new Position(14,26), "Not getting the correct position.");
    }
    @Test
    public void moveHero(){
        PacMan obj = new PacMan(1,3);
        Assertions.assertEquals(obj.getXCor(),1);
        Assertions.assertEquals(obj.getYCor(),3);
        obj.moveHero(new Position(2,4));
        Assertions.assertEquals(obj.getXCor(),2);
        Assertions.assertEquals(obj.getYCor(),4);
    }

}