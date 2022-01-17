import com.googlecode.lanterna.TerminalSize;
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
    public static Thread keyRead;
    private Screen screen;
    private Terminal terminal;
    private TerminalSize terminalSize;
    private Maze maze = new Maze(29,36);
    private GameStats gs;
    //TextGraphics graphics = screen.newTextGraphics();
    KeyStroke key;


    public Game() throws IOException {
        gs = new GameStats(0, "CCCC");
        terminalSize = new TerminalSize(maze.getWidth(), maze.getHeight());
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null); // we don't need a cursor
        screen.startScreen(); // screens must be started
        screen.doResizeIfNecessary(); // resize screen if necessary
    }

    private void draw() throws IOException, InterruptedException {
        TextGraphics graphics = screen.newTextGraphics();
        screen.clear();
        gs.drawGameElements(graphics);
        maze.drawMazeElements(graphics);
        screen.refresh();
    }
    public void run() {
        key = new KeyStroke(KeyType.ArrowLeft);
        while (true) {
            try {
                Thread.sleep(170);
                draw();
                if (!Maze.alreadyExecuted){ //this is only called every time the screen is reloaded (the players eats all food or loses one life)
                    TimeUnit.SECONDS.sleep(1);
                    Maze.alreadyExecuted = true;
                }
                keyRead = new Thread() {
                    @Override
                    public void run(){
                        KeyStroke newKey = new KeyStroke(KeyType.ArrowLeft);
                        try {
                            newKey = screen.readInput();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (key.getKeyType() != newKey.getKeyType()){
                            switch (newKey.getKeyType()){
                                case ArrowUp:
                                    if (canPacMove(Direction.UP)){
                                        key = newKey;
                                    }
                                    break;
                                case ArrowDown:
                                    if (canPacMove(Direction.DOWN)){
                                        key = newKey;
                                    }
                                    break;
                                case ArrowLeft:
                                    if (canPacMove(Direction.LEFT)){
                                        key = newKey;
                                    }
                                    break;
                                case ArrowRight:
                                    if (canPacMove(Direction.RIGHT)){
                                        key = newKey;
                                    }
                                    break;
                            }
                        }
                    }
                };
                keyRead.start();
                processKey(key, gs);
                //maze.blinkyGhost.chase(maze.pacman, maze);
                //maze.blinkyGhost.scatter(maze);
                if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'q'|| key.getCharacter() == 'Q')) {
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
    private void processKey(KeyStroke key, GameStats gs) throws IOException, InterruptedException {
        maze.processKey(key, gs);
    }

    private boolean canPacMove(Direction dir){
        return maze.canPacMove(dir);
    }
}