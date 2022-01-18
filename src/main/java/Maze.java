import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Maze {
    private static long milliSecondsPassedFruits;
    private static long milliSecondsPassedFrightened;
    private static boolean entered = false;
    public static boolean alreadyExecuted = false;
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
    public Maze(){
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

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    public void drawMazeElements(TextGraphics graphics) throws InterruptedException {
        milliSecondsPassedFrightened = System.currentTimeMillis() - startTimeFrightened;
        if (milliSecondsPassedFrightened > 8000){
            for (Ghost gh : ghosts){
                //gh.scatter();
            }
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
        blinkyGhost.setPosition(new Position(blinkyGhost.getPosition().getX() + 1, blinkyGhost.getPosition().getY()));
        inkyGhost.setPosition(new Position(inkyGhost.getPosition().getX(), inkyGhost.getPosition().getY() - 1));
        pinkyGhost.setPosition(new Position(pinkyGhost.getPosition().getX(), pinkyGhost.getPosition().getY() - 1));
        clydeGhost.setPosition(new Position(clydeGhost.getPosition().getX(), clydeGhost.getPosition().getY() - 1));
    }
    public void moveGhostsScatter(){
        Position newGhostP;
        for (Ghost ghost: ghosts){
            newGhostP = ghost.scatterBehaviour.scatter(ghost.getPosition(),ghost.getCurrent());
            ghost.setPosition(newGhostP);
        }
    }

    public void moveGhostsChase(){
        PosDir newPosDir;
        Position newGhostP;
        for (Ghost ghost : ghosts){
            newPosDir = ghost.chaseBehaviour.chase(ghost.getPosition(), pacman.getPosition(), ghost.getCurrent());
            newGhostP = newPosDir.getPos();
            ghost.setCurrent(newPosDir.getDir());
            ghost.setPosition(newGhostP);
        }
    }

    public void ghostsFrightened(){
        Position newGhostP;
        for (Ghost ghost : ghosts){
            ghost.setColour("#432AE8");
            newGhostP = ghost.frightenedBehaviour.frightened(ghost.getPosition(), pacman.getPosition());
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
        blinkyGhost = new Ghost(13, 14, "#FF0000", new ScatterTopRight());
        ghosts.add(blinkyGhost);
        clydeGhost = new Ghost(15, 17, "#FFB852", new ScatterBottomRight());
        ghosts.add(clydeGhost);
        inkyGhost = new Ghost(13, 17, "#00FFFF", new ScatterBottomLeft());
        ghosts.add(inkyGhost);
        pinkyGhost = new Ghost(14, 17, "#FFB8FF", new ScatterTopLeft());
        ghosts.add(pinkyGhost);
        return ghosts;
    }

    public void nonFrightenedCollisions(GameStats gs, Game game){
        boolean dead = false;
        for (Ghost g: ghosts){
            if (g.getPosition().equals(pacman.getPosition())){
                if (gs.getLives().length() >= 1){
                    gs.setLives(gs.getLives().substring(0, gs.getLives().length() - 1));
                    dead = true;
                    break;
                }
            }
        }
        if (dead){
            ghosts.clear();
            this.createWalls();
            this.createFruits();
            this.createFruits();
            this.createGhosts();
            pacman = new PacMan(14, 26);
            alreadyExecuted = false;
            milliSecondsPassedFruits = 0;
            Game.elapsedTimeScatter = 0;
            Game.startTimeScatter = System.currentTimeMillis();
            game.key = new KeyStroke(KeyType.ArrowLeft);
            ms.setDisplayFruits(fruit);
        }
    }

    private void retrieveFood(GameStats gs){
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
