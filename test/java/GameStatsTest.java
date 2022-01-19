import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameStatsTest {
    GameStats gs;
    @BeforeEach
    public void setup(){
        gs = new GameStats(0,"CCCCC");
    }
    @Test
    public void GameStats(){
        Assertions.assertEquals(gs.getScore(),0,"Not initializing score with correct value.");
        Assertions.assertEquals(gs.getLives(),"CCCCC", "Not initializing lives string correctly.");
    }
}
