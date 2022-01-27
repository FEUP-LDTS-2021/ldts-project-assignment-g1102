import java.io.IOException;

public interface Frightened {
    PosDir frightened(Position ghostP, Position pacmanP, Direction current) throws IOException;
}
