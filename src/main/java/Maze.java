import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Maze {
    private static long milliSecondsPassedFruits;
    private static long milliSecondsPassedFrightened;
    private static boolean entered = false;
    public static boolean alreadyExecuted = false;
    public int eatenGhostsInSuccession = 0;
    private static long startTimeFruits;
    private static long startTimeFrightened;
    private int width, height;
    public PacMan pacman;

    public Ghost blinkyGhost;
    public Ghost pinkyGhost;
    public Ghost inkyGhost;
    public Ghost clydeGhost;

    private Fruit fruit;
    private List<Wall> walls = new ArrayList<>();
    public List<Food> foods = new ArrayList<>();
    private List<Fruit> fruits = new ArrayList<>();
    public List<Ghost> ghosts =  new ArrayList<>();
    private MazeStats ms;
    public Maze() throws IOException {
        width = 29;
        height = 36;
        ms = new MazeStats(0,0,0,1);
        this.walls = createWalls();
        this.foods = createFoods();
        this.fruits = createFruits();
        this.fruits = createFruits();
        this.ghosts = createGhosts();
        pacman = new PacMan(14, 26);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public PacMan getPacman() { return pacman; }              //para usar em testes

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setPacMan(int x, int y){                    //para usar em testes
        pacman = new PacMan(x,y);
    }

    public int getEatenDotsPerRound(){return ms.getEatenDotsPerRound();};
    public void setEatenDotsPerRound(int a){ms.setEatenDotsPerRound(a);};
    public int getEatenFruitsPerRound(){return ms.getEatenFruitsPerRound();};
    public void setEatenFruitsPerRound(int a){ms.setEatenFruitsPerRound(a);};


    public void drawMazeElements(TextGraphics graphics) throws InterruptedException, IOException {
        if (blinkyGhost.getColour().equals("#432AE8") || inkyGhost.getColour().equals("#432AE8") || pinkyGhost.getColour().equals("#432AE8") || clydeGhost.getColour().equals("#432AE8")){
            milliSecondsPassedFrightened = System.currentTimeMillis() - startTimeFrightened;
        }
        if (milliSecondsPassedFrightened > 8000 && ((Game.elapsedTimeScatter >= 0 && Game.elapsedTimeScatter <= 5000) || (Game.elapsedTimeScatter >= 25000 && Game.elapsedTimeScatter <= 30000) || (Game.elapsedTimeScatter >= 50000 && Game.elapsedTimeScatter <= 55000) || Game.elapsedTimeScatter >= 75000 && Game.elapsedTimeScatter <= 80000)){
            eatenGhostsInSuccession = 0;
            try {
                moveGhostsScatter();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (milliSecondsPassedFrightened > 8000 && ((Game.elapsedTimeScatter > 5000 && Game.elapsedTimeScatter < 25000) || (Game.elapsedTimeScatter > 30000 && Game.elapsedTimeScatter < 50000) || (Game.elapsedTimeScatter > 55000 && Game.elapsedTimeScatter <= 75000) || Game.elapsedTimeScatter > 80000)){
            eatenGhostsInSuccession = 0;
            moveGhostsChase();
        }
        ms.drawDisplayFruits(graphics);
        for (Wall w : walls){
            w.draw(graphics);
        }

        for (Food f : foods){
            f.draw(graphics);
        }
        pacman.draw(graphics);

        for (Ghost g: ghosts){
            g.draw(graphics);
        }

        if (ms.getEatenDotsPerRound() >= 70 && (ms.getEatenFruitsPerRound() == 0) && (milliSecondsPassedFruits <= 10000)) {
            for (Fruit fruit : fruits){
                fruit.draw(graphics);
                if (!entered){
                    startTimeFruits = System.currentTimeMillis();
                    entered = true;
                }
            }
        }
        if (ms.getEatenDotsPerRound() >= 170 && (ms.getEatenFruitsPerRound() == 0 || ms.getEatenFruitsPerRound() == 1)) {
            for (Fruit fruit : fruits){
                fruit.draw(graphics);
                if (!entered){
                    startTimeFruits = System.currentTimeMillis();
                    entered = true;
                }
            }
        }
    }

    public boolean isWall(Position p){
        for (Wall w : walls){
            if (w.getPosition().getY() > p.getY()){
                break;
            }
            if (w.getPosition().equals(p)) {
                return true;
            }
        }
        return false;
    }

    public boolean isFood(Position p){
        for (Food f : foods){
            if (f.getPosition().getY() > p.getY()){
                break;
            }
            if (f.getPosition().equals(p)) {
                return true;
            }
        }
        return false;
    }

    public boolean isFruit(Position p){
        for (Fruit fruit : fruits){
            if (fruit.getPosition().getY() > p.getY()){
                break;
            }
            if (fruit.getPosition().equals(p)) {
                return true;
            }
        }
        return false;
    }

    public boolean isGhost(Position p){
        for (Ghost ghost : ghosts){
            if (ghost.getPosition().equals(p)){
                return true;
            }
        }
        return false;
    }

    public boolean isHole(Position p){
        if ((!isWall(p)&&((p.getX()<0 || p.getX()>width-1)))||(!isWall(p)&&((p.getY()<0 || p.getY()>height-1)))){
            return true;
        }
        return false;
    }

    public boolean isEmpty(Position p){
        return !isWall(p) && !isHole(p) && !isFood(p);
    }

    public Position goThroughHole(Position p) throws InterruptedException {
        if (p.getX()<0){
            p = new Position(width-1,p.getY());
        }
        else if (p.getX()>width-1){
            p = new Position(0,p.getY());
        }
        else if (p.getY()<0){
            p = new Position(p.getX(),height-1);
        }
        else if (p.getY()>height-1){
            p = new Position(p.getX(),0);
        }
        return p;
    }

    public void processKey(KeyStroke key, GameStats gs, Game game) throws IOException, InterruptedException {
        Position newP = null;
        switch (key.getKeyType()) {
            case ArrowUp:
                newP = new Position(pacman.getPosition().getX(),pacman.getPosition().getY()-1);
                if (isWall(newP)){
                    break;
                }
                else if (isHole(newP)){
                    newP = goThroughHole(newP);
                }
                pacman.moveHero(newP);
                break;
            case ArrowDown:
                newP = new Position(pacman.getPosition().getX(),pacman.getPosition().getY()+1);
                if (isWall(newP)){
                    break;
                }
                else if (isHole(newP)){
                    newP = goThroughHole(newP);
                }
                pacman.moveHero(newP);
                break;
            case ArrowLeft:
                newP = new Position(pacman.getPosition().getX()-1,pacman.getPosition().getY());
                if (isWall(newP)){
                    break;
                }
                else if (isHole(newP)){
                    newP = goThroughHole(newP);
                }
                pacman.moveHero(newP);
                break;
            case ArrowRight:
                newP = new Position(pacman.getPosition().getX()+1,pacman.getPosition().getY());
                if (isWall(newP)){
                    break;
                }
                else if (isHole(newP)){
                    newP = goThroughHole(newP);
                }
                pacman.moveHero(newP);
                break;
        }
        retrieveFood(gs);
        if (entered){
            milliSecondsPassedFruits = System.currentTimeMillis() - startTimeFruits;
            retrieveFruit(gs, milliSecondsPassedFruits);
        }
        endOfFood(game);
    }
    public void ghostsExitHouse(){
        if (!isWall(new Position(blinkyGhost.getXCor()+1,blinkyGhost.getYCor())) || blinkyGhost.getYCor() ==15)
            blinkyGhost.setPosition(new Position(blinkyGhost.getPosition().getX() + 1, blinkyGhost.getPosition().getY()));
        if (!isWall(new Position(inkyGhost.getXCor(),inkyGhost.getYCor()-1)) || inkyGhost.getYCor() - 1==15)
            inkyGhost.setPosition(new Position(inkyGhost.getPosition().getX(), inkyGhost.getPosition().getY() - 1));
        if (!isWall(new Position(pinkyGhost.getXCor(),pinkyGhost.getYCor()-1)) || pinkyGhost.getYCor() - 1==15)
            pinkyGhost.setPosition(new Position(pinkyGhost.getPosition().getX(), pinkyGhost.getPosition().getY() - 1));
        if (!isWall(new Position(clydeGhost.getXCor(),clydeGhost.getYCor()-1)) || clydeGhost.getYCor() - 1==15)
            clydeGhost.setPosition(new Position(clydeGhost.getPosition().getX(), clydeGhost.getPosition().getY() - 1));
    }
    public void moveGhostsScatter() throws IOException {
        Position newGhostP;
        PosDir newPosDir;
        for (Ghost ghost: ghosts){
            newPosDir = ghost.scatterBehaviour.scatter(ghost.getPosition(),ghost.getCurrent());
            newGhostP = newPosDir.getPos();
            ghost.setPosition(newGhostP);
            ghost.setCurrent(newPosDir.getDir());
        }
    }

    public void moveGhostsChase() throws IOException {
        PosDir newPosDir;
        Position newGhostP;
        for (Ghost ghost : ghosts){
            newPosDir = ghost.chaseBehaviour.chase(ghost.getPosition(), pacman.getPosition(), ghost.getCurrent());
            newGhostP = newPosDir.getPos();
            ghost.setCurrent(newPosDir.getDir());
            ghost.setPosition(newGhostP);
        }
    }

    public void ghostsFrightened() throws IOException {
        Position newGhostP;
        PosDir newPD;
        for (Ghost ghost : ghosts){
            ghost.setColour("#432AE8");
            newPD = ghost.frightenedBehaviour.frightened(ghost.getPosition(), pacman.getPosition(),ghost.getCurrent());
            newGhostP = newPD.getPos();
            ghost.setPosition(newGhostP);
        }
    }

    private List<Wall> createWalls(){
        Scanner mazeReader = null;
        try {
            File mazeFile = new File("maze.txt");
            mazeReader = new Scanner(mazeFile);
            int line = 3;
            while (mazeReader.hasNextLine()) {
                String data = mazeReader.nextLine();
                int column = 0;
                while (column < data.length()) {
                    if (data.charAt(column) == '|' || data.charAt(column) == '+' || data.charAt(column) == '-' || data.charAt(column) == '=') {
                        Wall w = new Wall(column, line, data.charAt(column));
                        walls.add(w);
                    }
                    column++;
                }
                line++;
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("ERROR:Maze file not found.");
        }
        mazeReader.close();
        return walls;
    }

    private List<Food> createFoods(){
        Scanner mazeReader = null;
        try {
            File mazeFile = new File("maze.txt");
            mazeReader = new Scanner(mazeFile);
            int line = 3;
            while (mazeReader.hasNextLine()) {
                String data = mazeReader.nextLine();
                int column = 0;
                while (column < data.length()) {
                    if (data.charAt(column) == '.' || data.charAt(column) == 'o') {
                        Food f = new Food(column, line, data.charAt(column));
                        foods.add(f);
                    }
                    column++;
                }
                line++;
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("ERROR:Maze file not found.");
        }
        mazeReader.close();
        return foods;
    }

    private List<Fruit> createFruits(){
        switch (ms.getRound()){
            case 1:
                fruit = new Fruit (14, 20, 'C' , "#FF0000");
                fruits.add(fruit);
                break;
            case 2:
                fruit = new Fruit (14, 20, 'M', "#FF0000");
                fruits.add(fruit);
                break;
            case 3:
            case 4:
                fruit = new Fruit(14, 20, 'O', "#FF9900");
                fruits.add(fruit);
                break;
            case 5:
            case 6:
                fruit = new Fruit(14, 20, 'A', "#FF0000");
                fruits.add(fruit);
                break;
            case 7:
            case 8:
                fruit = new Fruit(14, 20, 'E', "#004d26");
                fruits.add(fruit);
                break;
            case 9:
            case 10:
                fruit = new Fruit(14, 20, 'G', "#EAFF00");
                fruits.add(fruit);
                break;
            case 11:
            case 12:
                fruit = new Fruit(14, 20, 'B', "#EAFF00");
                fruits.add(fruit);
                break;
            case 13:
            case 14:
                fruit = new Fruit(14, 20, 'K', "#B0B0B0");
                fruits.add(fruit);
                break;
        }
        return fruits;
    }

    private List<Ghost> createGhosts(){
        if (ghosts.size()>0){
            ghosts.clear();
        }
        blinkyGhost = new Ghost(13, 14, "#FF0000", new ScatterTopRight(), "Blinky");
        ghosts.add(blinkyGhost);
        clydeGhost = new Ghost(15, 17, "#FFB852", new ScatterBottomLeft(), "Clyde");
        ghosts.add(clydeGhost);
        inkyGhost = new Ghost(13, 17, "#00FFFF", new ScatterBottomRight(), "Inky");
        ghosts.add(inkyGhost);
        pinkyGhost = new Ghost(14, 17, "#FFB8FF", new ScatterTopLeft(), "Pinky");
        ghosts.add(pinkyGhost);
        return ghosts;
    }

    public void nonFrightenedCollisions(GameStats gs, Game game) throws IOException, InterruptedException {
        boolean dead = false;
        for (Ghost g : ghosts) {
            if (g.getPosition().equals(pacman.getPosition()) && !g.getColour().equals("#432AE8")) {
                if (gs.getLives().length() >= 1) {
                    gs.setLives(gs.getLives().substring(0, gs.getLives().length() - 1));
                    dead = true;
                    break;
                }
                else{
                    if (g.getPosition().equals(pacman.getPosition()) && !g.getColour().equals("#432AE8")){
                        game.screen.close();

                        Leaderboard leaderboard = new Leaderboard();
                        leaderboard.updateLeaderboard(gs.getScore());

                        Menu menu = new Menu();
                    }
                }
            }
        }
        if (dead) {
            ghosts.clear();
            this.createWalls();
            this.createFruits();
            this.createFruits();
            this.createGhosts();
            pacman = new PacMan(14, 26);
            alreadyExecuted = false;
            milliSecondsPassedFruits = 0;
            eatenGhostsInSuccession = 0;
            Game.elapsedTimeScatter = 0;
            Game.startTimeScatter = System.currentTimeMillis();
            game.key = new KeyStroke(KeyType.ArrowLeft);
            ms.setDisplayFruits(fruit);
        }
    }

    public void frightenedCollisions(GameStats gs){
        for (Ghost g : ghosts){
            if (g.getPosition().equals(pacman.getPosition()) && g.getColour().equals("#432AE8")){
                eatenGhostsInSuccession++;
                if (g.getName().equals("Blinky")){
                    gs.incrementScoreGhosts(eatenGhostsInSuccession);
                    g.setPosition(new Position(13, 14));
                    g.setColour("#FF0000");
                }
                else if (g.getName().equals("Clyde")){
                    gs.incrementScoreGhosts(eatenGhostsInSuccession);
                    g.setPosition(new Position(15, 14));
                    g.setColour("#FFB852");
                }
                else if (g.getName().equals("Inky")){
                    gs.incrementScoreGhosts(eatenGhostsInSuccession);
                    g.setPosition(new Position(13, 14));
                    g.setColour("#00FFFF");
                }
                else if (g.getName().equals("Pinky")){
                    gs.incrementScoreGhosts(eatenGhostsInSuccession);
                    g.setPosition(new Position(14, 14));
                    g.setColour("#FFB8FF");
                }
            }
        }
    }

    private void retrieveFood(GameStats gs) throws IOException {
        for (Food f : foods){
            if (f.getPosition().equals(pacman.getPosition())){
                foods.remove(f);
                gs.incrementScorePellets(f);
                if (f.getCharacter() == '.'){
                    ms.incrementEatenDotsPerRound();
                }
                else if (f.getCharacter() == 'o'){
                    ms.incrementEatenPPPerRound();
                    for (Ghost g : ghosts){
                        startTimeFrightened = System.currentTimeMillis();
                        ghostsFrightened();
                    }
                }
                gs.incrementLives();
                break;
            }
        }
    }

    private void retrieveFruit(GameStats gs, long milliSecondsPassedFruits){
        if (fruit.getPosition().equals(pacman.getPosition())){
            fruits.remove(0);
            gs.increaseScoreFruits(fruit);
            ms.incrementEatenFruitsPerRound();
            gs.incrementLives();
            entered = false;
        }
        else if (milliSecondsPassedFruits > 10000){
            entered = false;
            fruits.remove(0);
        }
    }

    private void endOfFood(Game game) {
        if (foods.isEmpty()){
            ghosts.clear();
            ms.setRound(ms.getRound() + 1);
            this.createWalls();
            this.createFoods();
            this.createFruits();
            this.createFruits();
            this.createGhosts();
            ms.setEatenDotsPerRound(0);
            ms.setEatenPPPearRound(0);
            ms.setEatenFruitsPerRound(0);
            pacman = new PacMan(14, 26);
            alreadyExecuted = false;
            milliSecondsPassedFruits = 0;
            Game.elapsedTimeScatter = 0;
            eatenGhostsInSuccession = 0;
            Game.startTimeScatter = System.currentTimeMillis();
            game.key = new KeyStroke(KeyType.ArrowLeft);
            ms.setDisplayFruits(fruit);
        }
    }

    public boolean canPacMove(Direction dir){
        Position checkP;
        boolean answer = false;
        switch (dir){
            case UP:
                checkP = new Position(pacman.getXCor(),pacman.getYCor()-1);
                answer = isWall(checkP);
                break;
            case DOWN:
                checkP = new Position(pacman.getXCor(),pacman.getYCor()+1);
                answer = isWall(checkP);
                break;
            case LEFT:
                checkP = new Position(pacman.getXCor()-1,pacman.getYCor());
                answer = isWall(checkP);
                break;
            case RIGHT:
                checkP = new Position(pacman.getXCor()+1,pacman.getYCor());
                answer = isWall(checkP);
                break;
        }
        return !answer;
    }
    public void setPacmanPos(Position newP){
        pacman.setPosition(newP);
    }
}