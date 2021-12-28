import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

import static com.googlecode.lanterna.input.KeyType.EOF;

public class Game {

    private Screen screen;
    private Terminal terminal;
    private TerminalSize terminalSize;
    private Maze maze = new Maze(36,29);
    KeyStroke key;

    public Game() throws IOException {
        terminalSize = new TerminalSize(maze.getWidth(), maze.getHeight());
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null); // we don't need a cursor
        screen.startScreen(); // screens must be started
        screen.doResizeIfNecessary(); // resize screen if necessary
    }
    private void draw() throws IOException {
        screen.clear();
        maze.draw(screen);
        screen.refresh();
    }
    public void run() {
        while (true) {
            try {
                draw();
                key = screen.readInput();
                processKey(key);
                if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'q'|| key.getCharacter() == 'Q')) {       //caso o user pressione q ou Q, o jogo fecha
                    screen.close();
                }
                if (key.getKeyType() == EOF) {
                    break;
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    private void processKey(KeyStroke key) throws IOException {
        maze.processKey(key);
    }
}
