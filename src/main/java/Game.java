import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.util.concurrent.TimeUnit;

import java.io.IOException;

import static com.googlecode.lanterna.input.KeyType.EOF;

public class Game {

    private Screen screen;
    private Terminal terminal;
    private TerminalSize terminalSize;
    private Maze maze = new Maze(29,36);
    KeyStroke key;

    public Game() throws IOException, InterruptedException {
        terminalSize = new TerminalSize(maze.getWidth(), maze.getHeight());
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null); // we don't need a cursor
        screen.startScreen(); // screens must be started
        screen.doResizeIfNecessary(); // resize screen if necessary
    }
    private void draw() throws IOException, InterruptedException {
        screen.clear();
        maze.drawElements(screen.newTextGraphics());
        screen.refresh();
    }
    public void run() {
        while (true) {
            try {
                draw();
                if (!Maze.alreadyExecuted){ //this is only called every time the screen is reloaded (the players eats all food or loses one life)
                    TimeUnit.SECONDS.sleep(1);
                    Maze.alreadyExecuted = true;
                }
                key = screen.readInput();
                processKey(key);
                if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'q'|| key.getCharacter() == 'Q')) {//caso o user pressione q ou Q, o jogo fecha
                    screen.close();
                }
                if (key.getKeyType() == EOF) {
                    break;
                }
            } catch (IOException | InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    private void processKey(KeyStroke key) throws IOException, InterruptedException {
        maze.processKey(key);
    }
}