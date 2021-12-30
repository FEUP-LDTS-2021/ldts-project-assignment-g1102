import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.Vector;

public class Menu {

    private TerminalSize terminalSize;
    private Terminal terminal;
    private Screen screen;
    private KeyStroke key;

    public void draw(TextGraphics screen) {
        screen.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        screen.putString(new TerminalPosition(0, 1), "PAC-MAN");
        screen.putString(0, 4, "Menu");
        screen.putString(0, 6, "G - Game");
        screen.putString(0, 7, "L - Leaderboard");
        screen.putString(0, 8, "E - Exit");
    }

    public Menu() throws IOException {

        terminalSize = new TerminalSize(80, 60);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        terminal = terminalFactory.createTerminal();
        Screen screen = new TerminalScreen(terminal);

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

    public void processKey(KeyStroke key) throws IOException {
        switch (key.getCharacter()) {
            case 'G':
                Game game = new Game();
                game.run();
                break;
            case 'L':
                Leaderboard leaderboard = new Leaderboard();
                break;
            case 'E':
                System.exit(0);
                screen.close();
                break;
            default:
                key = screen.readInput();
                processKey(key);
                break;
        }
    }
}
