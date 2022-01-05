import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PacManTest {
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
