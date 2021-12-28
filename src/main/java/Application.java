import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws IOException {
        System.out.println("PAC-MAN");

        while (true) {
            menu();
            System.out.println("What option do you want?");
            Scanner scanner = new Scanner(System.in);

            int option = scanner.nextInt();
            boolean option_fail = false;

            if (option < 0 || option > 5){
                System.out.println("Invalid input");
                option_fail = true;
            }

            while (option_fail){
                Scanner sc = new Scanner(System.in);
                option = sc.nextInt();

                if (option < 0 || option > 5){
                    System.out.println("Invalid input");
                }

                else option_fail = false;
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
                case 3: //leaderboard
                    break;
                case 4:
                    System.exit(0);
                    break;
            }
        }
    }

    public static void menu(){
        System.out.println("MENU");
        System.out.println("1 - Rules");
        System.out.println("2 - Game");
        System.out.println("3 - Leaderboard");
        System.out.println("4 - Exit");
    }

    public static void rules(){
        System.out.println("RULES:");
    }
}

