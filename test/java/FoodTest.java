import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FoodTest {
    Food f;
    @BeforeEach
    public void setup(){
        f = new Food(4,3,'.');
    }
    @Test
    public void getPosition(){
        Assertions.assertEquals(4,f.getPosition().getX(),"Not getting the correct x coordinate.");
        Assertions.assertEquals(3,f.getPosition().getY(),"Not getting the correct y coordinate.");
    }
    @Test
    public void setPosition(){
        f.setPosition(new Position(5,6));
        Assertions.assertEquals(5,f.getPosition().getX(),"Not setting x correctly.");
        Assertions.assertEquals(6,f.getPosition().getY(),"Not setting y correctly.");
    }
    @Test
    public void getCharacter(){
        Assertions.assertEquals('.',f.getCharacter(),"Not getting the correct character.");
    }
    @Test
    public void setCharacter(){
        f.setCharacter('o');
        Assertions.assertEquals('o',f.getCharacter(),"Not setting characters correctly.");
    }
}
