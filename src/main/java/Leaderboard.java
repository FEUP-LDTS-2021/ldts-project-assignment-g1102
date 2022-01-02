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
import java.util.*;

public class Leaderboard{

    private TerminalSize terminalSize;
    private Terminal terminal;
    private Screen screen;
    KeyStroke key;

    List<Person> persons = new ArrayList<>();

    public Leaderboard() throws IOException {
        terminalSize = new TerminalSize(80, 60);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        terminal = terminalFactory.createTerminal();
        Screen screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null); // we don't need a cursor
        screen.startScreen(); // screens must be started
        screen.doResizeIfNecessary(); // resize screen if necessary

        /*Scanner scanner = new Scanner(System.in);
        System.out.println("name: ");
        String name = scanner.nextLine();               //Se quiserem testar a escrita para o leaderboard (atenção que isto não é para estar na consola, mas sim depois no screen de game over)
        updateLeaderboard(name, 53);*/

        readLearderBoard();
        displayLeaderboard(screen.newTextGraphics());
        screen.refresh();

        key = screen.readInput();

        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'x')
            screen.close();
        }


    //O READLEADERBOARD SÓ PODE ESTAR NUMA DAS FUNÇÕES QUE DEVE SER O CONSTRUTOR
    //O SCREEN NÃO FAZ REFRESH NAS OUTRAS FUNÇÕES POR ISSO É QUE ESTA NO CONSTRUTOR
    public void readLearderBoard() throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader("scores.txt"));
        String line;

        while ((line = reader.readLine()) != null) { // reads a line of the file and puts it in the file variable while the file has things to read
            String[] s = line.split("-");
            Person p = new Person(s[0], Integer.valueOf(s[1]));
            persons.add(p);
        }

        Collections.sort(persons, (p1, p2) -> p2.getScore() - p1.getScore());
        reader.close();
    }


    public void displayLeaderboard(TextGraphics screen) throws IOException {
        screen.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        screen.putString(34, 3, "TOP 10 BEST PLAYERS");
        screen.putString(19,5,"RANK                NAME                SCORE");

        int row = 6, rank = 1;
        if (persons.size() < 10) {
            for (int i = 0; i < persons.size(); i++) {
                if (rank == 1) {
                    screen.setForegroundColor(TextColor.Factory.fromString("#DED307"));
                    screen.putString(20, row, String.valueOf(rank) + "st");
                } else if (rank == 2) {
                    screen.setForegroundColor(TextColor.Factory.fromString("#B5B5B3"));
                    screen.putString(20, row, String.valueOf(rank) + "nd");
                } else if (rank == 3) {
                    screen.setForegroundColor(TextColor.Factory.fromString("#CD7F32"));
                    screen.putString(20, row, String.valueOf(rank) + "rd");
                } else {
                    screen.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
                    screen.putString(20, row, String.valueOf(rank) + "th");
                }

                screen.putString(38, row, persons.get(i).getName());
                screen.putString(60, row, String.valueOf(persons.get(i).getScore()));
                row++;
                rank++;
            }
        }
        else{
            for (int i = 0; i < 10; i++){
                if (rank == 1) {
                    screen.setForegroundColor(TextColor.Factory.fromString("#DED307"));
                    screen.putString(20, row, String.valueOf(rank) + "st");}
                else if (rank == 2) {
                    screen.setForegroundColor(TextColor.Factory.fromString("#B5B5B3"));
                    screen.putString(20, row, String.valueOf(rank) + "nd");
                }
                else if (rank == 3) {
                    screen.setForegroundColor(TextColor.Factory.fromString("#CD7F32"));
                    screen.putString(20, row, String.valueOf(rank) + "rd");
                }
                else {
                    screen.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
                    screen.putString(20, row, String.valueOf(rank) + "th");
                }

                screen.putString(38, row, persons.get(i).getName());
                screen.putString(60, row, String.valueOf(persons.get(i).getScore()));
                row++;
                rank++;
            }
        }
        screen.putString(29, row+2, "Press x to close this window"); //press key x and close
    }

    public void updateLeaderboard(String playerName, int score) throws IOException{
        //readLearderBoard();

        Person p = new Person(playerName, score);
        //persons.add(p);

        BufferedWriter writer = new BufferedWriter(new FileWriter("scores.txt", true));

        writer.write(p.getName() + "-" + p.getScore() + '\n');
        writer.close();
    }
}