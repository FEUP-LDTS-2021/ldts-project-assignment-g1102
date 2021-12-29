import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.Vector;

public class Application {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("PAC-MAN");

        while (true) {
            menu();
            System.out.println("What option do you want?");
            Scanner scanner = new Scanner(System.in);

            int option = scanner.nextInt();
            boolean option_fail = false;

            if (option < 1 || option > 5) {
                System.out.println("Invalid input");
                option_fail = true;
            }

            while (option_fail) {
                Scanner sc = new Scanner(System.in);
                option = sc.nextInt();

                if (option < 1 || option > 5) {
                    System.out.println("Invalid input");
                } else option_fail = false;
            }

            switch (option) {
                case 1:
                    rules();
                    break;
                case 2: {
                    Game game = new Game();
                    game.run();
                    break;
                }
                case 3: //options
                    break;
                case 4:
                    displayLeaderboard();
                    break;
                case 5:
                    System.exit(0);
                    break;
            }
        }
    }

    public static void menu() {
        System.out.println("MENU");
        System.out.println("1 - Rules");
        System.out.println("2 - Game");
        System.out.println("3 - Options");
        System.out.println("4 - Leaderboard");
        System.out.println("5 - Exit");
    }

    public static void rules() {System.out.println("RULES:");}

    public static void displayLeaderboard() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("scores.txt"));
        String line;
        while ((line = reader.readLine()) != null) { // reads a line of the file and puts it in the file variable while the file has things to read
            System.out.println(line);
        }
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