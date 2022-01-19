import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PersonTest {
    Person p;
    @BeforeEach
    public void setup(){
        p = new Person("RandomNickName", 1000);
    }
    @Test
    public void Person(){
        Assertions.assertEquals(p.getName(),"RandomNickName","Constructor initiates Person's Name Incorrectly");
        Assertions.assertEquals(p.getScore(),1000,"Constructor initiates Person's Score Incorrectly");
    }
}
