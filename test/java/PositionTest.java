import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PositionTest {
    @Test
    public void equals(){
        Position a = new Position (2,3);
        Position b = new Position (2,3);
        Position c = new Position (3,2);
        Position d = new Position (3,2);
        Assertions.assertTrue(a.equals(b),"Doesn't detect when two positions are equal.");
        Assertions.assertTrue(c.equals(d),"Doesn't detect when two positions are equal.");
        Assertions.assertFalse(b.equals(c),"Doesn't detect when two positions are different.");
    }
}
