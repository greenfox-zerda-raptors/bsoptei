package wanderer;


import java.util.HashMap;
import java.util.Random;

/**
 * Created by Söp on 2016.12.05.. Generic class for pretty much everything in the game
 */
abstract class GameObject {
    Integer defaultHealthPoint, healthPoint, defensePoint, strikePoint, xPos, yPos;
    String name;
    public Hero hero;
    String type;
    boolean alive = true;
    PositionedImage tileImage;
    final int imageSize = 72;
    int level;
    boolean obstacle;
    Area gameArea;
    Random dice = new Random();
    final HashMap<String, String> imageSelector = new HashMap<String, String>() {{
        put("F", "src/wanderer/image/floor.png");
        put("W", "src/wanderer/image/wall.png");
        put("H", "src/wanderer/image/hero-down.png");
        put("S", "src/wanderer/image/skeleton.png");
        put("B", "src/wanderer/image/boss.png");
    }};
    String swanSong;

    GameObject(String type) {
        this.type = type;

    }

    abstract void changeElementImage(int deltaX, int deltaY);

    abstract void move(int deltaX, int deltaY);

    abstract void strike();

    public void createElementImage() {
        this.tileImage = new PositionedImage(imageSelector.get(type), xPos * imageSize, yPos * imageSize);
    }

    public void moveElementImage() {
        tileImage.setPosX(xPos * imageSize);
        tileImage.setPosY(yPos * imageSize);
    }

    abstract void setDefaultStats();

    PositionedImage getTileImage() {
        return tileImage;
    }

    boolean isAlive() {
        return alive;
    }

    boolean isObstacle() {
        return obstacle;
    }

    String getName() {
        return name;
    }

    String getStats() {
        return String.format("HP: %d/%d | DP: %d | SP: %d",
                healthPoint, defaultHealthPoint,
                defensePoint, strikePoint);
    }

    Integer[] getCoordinates() {
        return new Integer[]{xPos, yPos};
    }

    public void setGameArea(Area gameArea) {
        this.gameArea = gameArea;
    }

    boolean movementIsPossible(int deltaX, int deltaY) {
        return (!(boundaryOfMap(deltaX, deltaY)) && !(neighborIsObstacle(deltaX, deltaY)));
    }

    abstract boolean neighborIsObstacle(int deltaX, int deltaY) ;

    private boolean boundaryOfMap(int deltaX, int deltaY) {
        return (xPos == 0 && deltaX == -1
                || xPos == 9 && deltaX == 1
                || yPos == 0 && deltaY == -1
                || yPos == 9 && deltaY == 1);
    }

    void decreaseHealthPoint(int damage) {
        healthPoint -= damage;
        if (healthPoint <= 0) {
            if (!(swanSong == null)) {
                AudioPlayer.play(swanSong);
            }
            alive = false;
            hero.levelUp();
        }
    }

    abstract void getHit(int damage);

    String getType(){ return type;}

    public void setHero(Hero hero) {
        this.hero = hero;
    }
}