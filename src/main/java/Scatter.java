import java.io.IOException;

public interface Scatter {
    PosDir scatter(Position ghostP, Direction current) throws IOException;
}
