import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GhostTest {
     Ghost ghost;
     @BeforeEach
     public void setup(){
         ghost = new Ghost(13, 14, "#FF0000", new ScatterTopRight());
     }

     @Test
    public void setPosition(){
         ghost.setPosition(new Position(13, 15));
         Assertions.assertEquals(13,ghost.getPosition().getX(),"Not setting x correctly.");
         Assertions.assertEquals(15,ghost.getPosition().getY(),"Not setting y correctly.");
    }

    @Test
    public void getPosition(){
        Assertions.assertEquals(13,ghost.getPosition().getX(),"Not getting the correct x coordinate.");
        Assertions.assertEquals(14,ghost.getPosition().getY(),"Not getting the correct y coordinate.");
    }

    @Test
    public void setDirection(){
         ghost.setDirection(LEFT);
         Assertions.assertEquals(LEFT,ghost.getDirection(),"Not setting the correct direction.");
    }

    @Test
    public void getDirection(){
         Assertions.assertEquals(UP, ghost.getCurrent(), "Not getting the correct direction.");
    }

    @Test
    public void setColour(){
         ghost.setColour("#0C14F2");
         Assertions.assertEquals("0C14F2", ghost.getColour(), "Not setting the correct colour correctly.");
    }

    @Test
    public void getColour(){
         Assertions.assertEquals("#FF0000", ghost.getColour(), "Not getting the correct colour.");
    }


}
