import java.io.IOException;

public interface Chase {
        PosDir chase(Position ghostP, Position pacmanP, Direction current) throws IOException;
}
