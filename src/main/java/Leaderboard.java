import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyStroke;

import javax.swing.*;
import java.io.*;
import java.security.Key;
import java.util.ArrayList;

public class Leaderboard {

        private TerminalSize terminalSize;
        private Terminal terminal;
        private Screen screen;
        KeyStroke key;

        public Leaderboard() throws IOException {
            terminalSize = new TerminalSize(80, 60);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            terminal = terminalFactory.createTerminal();
            Screen screen = new TerminalScreen(terminal);

            screen.setCursorPosition(null); // we don't need a cursor
            screen.startScreen(); // screens must be started
            screen.doResizeIfNecessary(); // resize screen if necessary

            displayLeaderboard(screen.newTextGraphics());
            screen.refresh();

            key = screen.readInput();

            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'X')
                screen.close();

        }


        public void displayLeaderboard(TextGraphics screen) throws IOException {
            BufferedReader reader = new BufferedReader(new FileReader("scores.txt"));
            screen.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
            screen.putString(0, 0, "Leaderboard");
            String line;

            int row = 1;
            while ((line = reader.readLine()) != null) { // reads a line of the file and puts it in the file variable while the file has things to read

                screen.putString(0, row, line);
                row++;
            }

            screen.putString(0, row+2, "Press X for close this window"); //press key x and close

        }


        public static void updateLeaderboard(int score, String playerName) throws IOException{
            ArrayList<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader("scores.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            lines.add(playerName + "         " + score);
            BufferedWriter writer = new BufferedWriter(new FileWriter("scores.txt"));
            for (String content: lines){
                writer.write(content + '\n');
            }
            writer.close();
        }
}
