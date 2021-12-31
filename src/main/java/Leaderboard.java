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

class Person{
    String name; Integer score;
    public Person(String name, Integer score){
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public Integer getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}

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
        screen.putString(0, 0, "Leaderboard");

        int row = 1;
        for (Person person : persons){
            screen.putString(0, row, person.getName() + " " + person.getScore());
            row++;
        }
        screen.putString(0, row+2, "Press x for close this window"); //press key x and close
    }

    public void updateLeaderboard(String playerName, int score) throws IOException{
        readLearderBoard();

        Person p = new Person(playerName, score);
        persons.add(p);

        BufferedWriter writer = new BufferedWriter(new FileWriter("scores.txt", true));

        writer.write(p.getName() + "-" + p.getScore() + '\n');

        writer.close();
    }
}