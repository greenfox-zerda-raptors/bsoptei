package wanderer;

import java.util.HashMap;

/**
 * Created by Söp on 2016.12.07.. The hero
 */
class Hero extends GameObject {
    private HashMap<String, PositionedImage> heroDirectionImages = new HashMap<String, PositionedImage>() {{
        put("down", new PositionedImage("src/wanderer/image/hero-down.png", 0, 0));
        put("up", new PositionedImage("src/wanderer/image/hero-up.png", 0, 0));
        put("left", new PositionedImage("src/wanderer/image/hero-left.png", 0, 0));
        put("right", new PositionedImage("src/wanderer/image/hero-right.png", 0, 0));
    }};
    private int numberOfMoves;
    private GameObject currentOpponent;
    private int heroLevel = 1;

    Hero(int xPos, int yPos) {
        super("H");
        this.xPos = xPos;
        this.yPos = yPos;
        obstacle = false;
        numberOfMoves = 0;
        name = "Hero";
        swanSong = "src/wanderer/wav/56901__syna-max__wilhelm-scream-outtake.wav";
        createElementImage();
        setDefaultStats();
    }

    void reset() {
        xPos = 0;
        yPos = 0;
        moveElementImage();
        numberOfMoves = 0;
        restoreHealth(dice.nextInt(10) + 1);
    }

    private void restoreHealth(int diceThrow) {
        if (diceThrow > 9) {
            healthPoint = defaultHealthPoint;
        } else if (healthPoint > 5) {
            healthPoint = (healthPoint < defaultHealthPoint * 0.75) ? (int) (healthPoint * 1.333) : defaultHealthPoint;
        } else {
            healthPoint = (healthPoint < defaultHealthPoint * 0.9) ? (int) (healthPoint * 1.1) : defaultHealthPoint;
        }
    }

    @Override
    void move(int deltaX, int deltaY) {
        if (movementIsPossible(deltaX, deltaY)) {
            xPos += deltaX;
            yPos += deltaY;
            AudioPlayer.play("src/wanderer/wav/268758__legowanwan__footsteps.wav");
        } else {
            AudioPlayer.play("src/wanderer/wav/8838__churd-tzu__water-bottle-snare-15-bonk.wav");
        }
        changeElementImage(deltaX, deltaY);
        moveElementImage();
        incrementNumberOfMoves();
    }

    void changeElementImage(int deltaX, int deltaY) {
        if (deltaX == -1) {
            tileImage = heroDirectionImages.get("left");
        } else if (deltaX == 1) {
            tileImage = heroDirectionImages.get("right");
        } else if (deltaY == -1) {
            tileImage = heroDirectionImages.get("up");
        } else {
            tileImage = heroDirectionImages.get("down");
        }
    }

    @Override
    void strike() {
        if (currentOpponent != null) {
            AudioPlayer.play("src/wanderer/wav/326847__johnbuhr__sword-clash-37.wav");
            currentOpponent.getHit(strikePoint);
        }
    }

    @Override
    void setDefaultStats() {
        defaultHealthPoint = 20 + 3 * (dice.nextInt(6) + 1);
        healthPoint = defaultHealthPoint;
        defensePoint = 2 * (dice.nextInt(6) + 1);
        strikePoint = 5 + (dice.nextInt(6) + 1);
    }

    @Override
    PositionedImage getTileImage() {
        return tileImage;
    }

    public void setGameArea(Area gameArea) {
        this.gameArea = gameArea;
    }

    @Override
    boolean neighborIsObstacle(int deltaX, int deltaY) {
        int xNeighbor = xPos + deltaX;
        int yNeighbor = yPos + deltaY;
        return ((deltaX != 0 && gameArea.getTiles()[xNeighbor][yPos].isObstacle())
                || (deltaY != 0 && gameArea.getTiles()[xPos][yNeighbor].isObstacle()));
    }

    @Override
    String getLevelToString() {
        return String.format("Level %s", String.valueOf(heroLevel));
    }

    @Override
    void getHit(int damage) {
        if (damage + (dice.nextInt(6) + 1) * 2 > defensePoint) {
            AudioPlayer.play("src/wanderer/wav/19421__awfulthesample__awfultheaudio-watschn2.wav");
            decreaseHealthPoint(damage);
        }
    }

    @Override
    void setHero(Hero hero) {
    }

    private void decreaseHealthPoint(int damage) {
        healthPoint -= damage;
        if (healthPoint <= 0) {
            if (!(swanSong == null)) {
                AudioPlayer.play(swanSong);
            }
            alive = false;
        }
    }

    private void incrementNumberOfMoves() {
        numberOfMoves++;
    }

    int getNumberOfMoves() {
        return numberOfMoves;
    }

    void setCurrentOpponent(GameObject currentOpponent) {
        this.currentOpponent = currentOpponent;
    }

    void levelUp() {
        heroLevel++;
        defaultHealthPoint += (dice.nextInt(6) + 1);
        defensePoint += (dice.nextInt(6) + 1);
        strikePoint += (dice.nextInt(6) + 1);
//        AudioPlayer.play("src/wanderer/wav/345111__toiletrolltube__rec034-guitar-1-p-1.wav");
    }

    int getHeroLevel() {
        return heroLevel;
    }
}