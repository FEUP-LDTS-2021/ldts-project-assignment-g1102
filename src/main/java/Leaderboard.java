import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.*;
import java.util.*;

public class Leaderboard extends ReadName{

    private TerminalSize terminalSize, terminalSize1;
    private Terminal terminal;
    private Screen screen;
    KeyStroke key;

    List<Person> persons = new ArrayList<>();

    public Leaderboard() throws IOException {
        terminalSize = new TerminalSize(80, 60);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null); // we don't need a cursor
        screen.startScreen(); // screens must be started
        screen.doResizeIfNecessary(); // resize screen if necessary

        readLeaderBoard("scores.txt");
        displayLeaderboard(screen.newTextGraphics());
        screen.refresh();

        key = screen.readInput();

        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'x')
            screen.close();
    }

    public void readLeaderBoard(String fileName) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;

        while ((line = reader.readLine()) != null) { // reads a line of the file and puts it in the file variable while the file has things to read
            String[] s = line.split("-");
            Person p = new Person(s[0], Integer.valueOf(s[1]));
            persons.add(p);
        }

        Collections.sort(persons, (p1, p2) -> p2.getScore() - p1.getScore());
        reader.close();
    }


    public void displayLeaderboard(TextGraphics graphics) throws IOException {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(34, 3, "TOP 10 BEST PLAYERS");
        graphics.putString(19,5,"RANK                NAME                SCORE");

        int row = 6, rank = 1;
        if (persons.size() < 10) {
            for (int i = 0; i < persons.size(); i++) {
                if (rank == 1) {
                    graphics.setForegroundColor(TextColor.Factory.fromString("#DED307"));
                    graphics.putString(20, row, String.valueOf(rank) + "st");
                } else if (rank == 2) {
                    graphics.setForegroundColor(TextColor.Factory.fromString("#B5B5B3"));
                    graphics.putString(20, row, String.valueOf(rank) + "nd");
                } else if (rank == 3) {
                    graphics.setForegroundColor(TextColor.Factory.fromString("#CD7F32"));
                    graphics.putString(20, row, String.valueOf(rank) + "rd");
                } else {
                    graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
                    graphics.putString(20, row, String.valueOf(rank) + "th");
                }

                graphics.putString(38, row, persons.get(i).getName());
                graphics.putString(60, row, String.valueOf(persons.get(i).getScore()));
                row++;
                rank++;
            }
        }
        else{
            for (int i = 0; i < 10; i++){
                if (rank == 1) {
                    graphics.setForegroundColor(TextColor.Factory.fromString("#DED307"));
                    graphics.putString(20, row, String.valueOf(rank) + "st");}
                else if (rank == 2) {
                    graphics.setForegroundColor(TextColor.Factory.fromString("#B5B5B3"));
                    graphics.putString(20, row, String.valueOf(rank) + "nd");
                }
                else if (rank == 3) {
                    graphics.setForegroundColor(TextColor.Factory.fromString("#CD7F32"));
                    graphics.putString(20, row, String.valueOf(rank) + "rd");
                }
                else {
                    graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
                    graphics.putString(20, row, String.valueOf(rank) + "th");
                }

                graphics.putString(38, row, persons.get(i).getName());
                graphics.putString(60, row, String.valueOf(persons.get(i).getScore()));
                row++;
                rank++;
            }
        }
        graphics.putString(29, row+2, "Press x to close this window");
    }

    public void updateLeaderboard(int score) throws IOException{
        ReadName readName = new ReadName();
        this.name = readName.getNamePlayer();

        Person p = new Person(this.name, score);

        BufferedWriter writer = new BufferedWriter(new FileWriter("scores.txt", true));

        writer.write(p.getName() + "-" + p.getScore() + '\n');
        writer.close();
    }
}