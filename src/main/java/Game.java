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
    private int nRows =36, nColumns = 29, x= 15, y=19;
    KeyStroke key;

    public Game() throws IOException {
        terminalSize = new TerminalSize(nColumns, nRows);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null); // we don't need a cursor
        screen.startScreen(); // screens must be started
        screen.doResizeIfNecessary(); // resize screen if necessary
    }
    private void draw() throws IOException {
        screen.clear();
        screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
        screen.refresh();
    }
    public void run() {
        while (true) {
            try {
                draw();
                key = screen.readInput();
                processKey(key);
                if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
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
        switch (key.getKeyType()) {
            case ArrowUp:
                y -= 1;
                break;
            case ArrowDown:
                y += 1;
                break;
            case ArrowLeft:
                x -= 1;
                break;
            case ArrowRight:
                x += 1;
                break;
        }
    }
}
