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
    public Screen screen;
    private Terminal terminal;
    private TerminalSize terminalSize;
    private Maze maze = new Maze();
    private GameStats gs;
    public static long startTimeScatter;
    public static long elapsedTimeScatter;
    public static boolean countedStartTime = false;
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

    private void render() throws IOException, InterruptedException {
        if (!Maze.alreadyExecuted) { //this is only called every time the screen is reloaded (the players eats all food or loses one life)
            TimeUnit.SECONDS.sleep(1);
            Maze.alreadyExecuted = true;
        }
        TextGraphics graphics = screen.newTextGraphics();
        screen.clear();
        gs.drawGameElements(graphics);
        maze.drawMazeElements(graphics);
        screen.refresh();
    }

    public void run(Game game) {
        key = new KeyStroke(KeyType.ArrowLeft);
        //startTimeScatter = System.currentTimeMillis();
        while (true) {
            try {
                Thread.sleep(170);
                render();
                processInput(game);
                //elapsedTimeScatter = System.currentTimeMillis() - startTimeScatter;
                //System.out.println(elapsedTimeScatter);
                updateGhosts(game);
                //maze.blinkyGhost.chase(maze.pacman, maze);
                //maze.blinkyGhost.scatter(maze);
                if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'q' || key.getCharacter() == 'Q')) {
                    screen.close();
                }
                if (key.getKeyType() == EOF) {
                    break;
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void processInput(Game game) throws IOException, InterruptedException {
        keyRead = new Thread() {
            @Override
            public void run() {
                KeyStroke newKey = new KeyStroke(KeyType.ArrowLeft);
                try {
                    newKey = screen.readInput();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (key.getKeyType() != newKey.getKeyType()) {
                    updateKey(newKey);
                }
            }
        };
        keyRead.start();
        processKey(key, gs, game);
    }

    private void updateGhosts(Game game) throws IOException, InterruptedException {
        if (maze.isGhost(new Position(14, 17)) || maze.isGhost(new Position(14, 16)) || maze.isGhost(new Position(14, 15))) {
            ghostsExitHouse();
        }
        else {
            if (!countedStartTime){
                startTimeScatter = System.currentTimeMillis();
                countedStartTime = true;
            }
            elapsedTimeScatter = System.currentTimeMillis() - startTimeScatter;
            //System.out.println(elapsedTimeScatter);
            if ((elapsedTimeScatter >= 0 && elapsedTimeScatter <= 5000) || (elapsedTimeScatter >= 25000 && elapsedTimeScatter <= 30000) || (elapsedTimeScatter >= 50000 && elapsedTimeScatter <= 55000) || elapsedTimeScatter >= 75000 && elapsedTimeScatter <= 80000) {
                moveGhostsScatter();
            }
            else {
                moveGhostsChase();
            }
        }
        nonFrightenedCollisions(gs, game);
        frightenedCollisions(gs);
    }

    private void updateKey(KeyStroke newKey){
        switch (newKey.getKeyType()) {
            case ArrowUp:
                if (canPacMove(Direction.UP)) {
                    key = newKey;
                }
                break;
            case ArrowDown:
                if (canPacMove(Direction.DOWN)) {
                    key = newKey;
                }
                break;
            case ArrowLeft:
                if (canPacMove(Direction.LEFT)) {
                    key = newKey;
                }
                break;
            case ArrowRight:
                if (canPacMove(Direction.RIGHT)) {
                    key = newKey;
                }
                break;
        }
    }

    private void processKey(KeyStroke key, GameStats gs, Game game) throws IOException, InterruptedException {
        maze.processKey(key, gs, game);
    }

    private void moveGhostsScatter() throws IOException {
        maze.moveGhostsScatter();
    }

    private void moveGhostsChase() throws IOException {
        maze.moveGhostsChase();
    }

    private void nonFrightenedCollisions(GameStats gs, Game game) throws IOException, InterruptedException {
        maze.nonFrightenedCollisions(gs, game);
    }

    private void frightenedCollisions(GameStats gs) {
        maze.frightenedCollisions(gs);
    }

    private boolean canPacMove (Direction dir){
        return maze.canPacMove(dir);
    }

    private void ghostsExitHouse () {
        maze.ghostsExitHouse();
    }
}