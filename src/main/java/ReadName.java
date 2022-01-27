import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class ReadName {
    private TerminalSize terminalSize;
    private Terminal terminal;
    private Screen screen;
    KeyStroke key;
    String name;

    public ReadName() throws IOException {
        terminalSize = new TerminalSize(40, 30);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

        this.name = getName(screen.newTextGraphics());
        screen.close();
    }

    public String getName(TextGraphics graphics) throws IOException {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(3, 1, "Please input your name");
        screen.refresh();

        key = screen.readInput();

        name = key.toString();
        System.out.println(name);

        screen.close();
        return name;
    }

    public String getNamePlayer(){
        return name;
    }

}
