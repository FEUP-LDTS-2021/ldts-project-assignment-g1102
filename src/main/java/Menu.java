import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Menu {

    private TerminalSize terminalSize;
    private Terminal terminal;
    private Screen screen;
    private KeyStroke key;

    public void draw(TextGraphics screen) {
        screen.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        screen.putString(23, 4, "Character / Nickname");
        screen.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        screen.putString(20, 6, "F -SHADOW         BLINKY");
        screen.setForegroundColor(TextColor.Factory.fromString("#ED5AAB"));
        screen.putString(20, 7, "F -SPEEDY         PINKY");
        screen.setForegroundColor(TextColor.Factory.fromString("#6ED2EB"));
        screen.putString(20, 8, "F -BASHFUL        INKY");
        screen.setForegroundColor(TextColor.Factory.fromString("#ECF54C"));
        screen.putString(20, 9, "F -POKEY          CLYDE");
        screen.setForegroundColor(TextColor.Factory.fromString("#0C14F2"));
        screen.putString(25, 11, "1st F");
        screen.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        screen.putString(35, 11, "200 PTS");
        screen.setForegroundColor(TextColor.Factory.fromString("#0C14F2"));
        screen.putString(25, 12, "2nd F");
        screen.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        screen.putString(35, 12, "400 PTS");
        screen.setForegroundColor(TextColor.Factory.fromString("#0C14F2"));
        screen.putString(25, 13, "3rd F") ;
        screen.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        screen.putString(35, 13, "800 PTS");
        screen.setForegroundColor(TextColor.Factory.fromString("#0C14F2"));
        screen.putString(25, 14, "4th F");
        screen.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        screen.putString(34, 14, "1600 PTS");
        screen.putString(30, 16, ".");
        screen.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        screen.putString(32, 16, "10 PTS");
        screen.setForegroundColor(TextColor.Factory.fromString("#ECF54C"));
        screen.putString(30, 17, "o");
        screen.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        screen.putString(32, 17, "50 PTS");
        screen.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        screen.putString(22, 19, "Press G to play");
        screen.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        screen.putString(22, 20, "Press L to see leaderboard");
        screen.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        screen.putString(22, 21, "Press E to exit");
    }

    public Menu() throws IOException, InterruptedException {

        terminalSize = new TerminalSize(80, 60);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null); // we don't need a cursor
        screen.startScreen(); // screens must be started
        screen.doResizeIfNecessary(); // resize screen if necessary

        draw(screen.newTextGraphics());
        screen.refresh();


        while (true) {
            key = screen.readInput();
            processKey(key);
        }
    }

    public void processKey(KeyStroke key) throws IOException{
        if (key.getKeyType() == null){
            key = screen.readInput();
            processKey(key);
        }
        switch (Character.toUpperCase(key.getCharacter())) {
            case 'G':
                Game game = new Game();
                game.run();
                break;
            case 'L':
                Leaderboard leaderboard = new Leaderboard();
                break;
            case 'E':
                System.exit(0);
                break;
            default:
                key = screen.readInput();
                processKey(key);
                break;
        }
    }
}
