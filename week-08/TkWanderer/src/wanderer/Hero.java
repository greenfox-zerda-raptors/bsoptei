package wanderer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Söp on 2016.12.07..
 */
public class Hero extends GameObject {
    public HashMap<String, PositionedImage> heroDirectionImages = new HashMap<String, PositionedImage>() {{
        put("down", new PositionedImage("src/wanderer/image/hero-down.png", 0, 0));
        put("up", new PositionedImage("src/wanderer/image/hero-up.png", 0, 0));
        put("left", new PositionedImage("src/wanderer/image/hero-left.png", 0, 0));
        put("right", new PositionedImage("src/wanderer/image/hero-right.png", 0, 0));
    }};

    public int numberOfMoves;

    public Hero(int xPos, int yPos) {
        super("H");
        this.xPos = xPos;
        this.yPos = yPos;
        obstacle = false;
        numberOfMoves = 0;
        name = "Hero";
        createElementImage();
        setDefaultStats();
    }

    @Override
    void move(int deltaX, int deltaY) {
        if (movementIsPossible(deltaX, deltaY)) {
            xPos += deltaX;
            yPos += deltaY;
        }
        incrementNumberOfMoves();
        changeElementImage(deltaX, deltaY);
        moveElementImage();
    }

//    private boolean movementIsPossible(int deltaX, int deltaY) {
//        boolean possible = true;
//        if (boundaryOfMap(deltaX, deltaY) || neighborIsObstacle(deltaX, deltaY)) {
//            possible = false;
//        }
//        return possible;
//    }
//
//    private boolean neighborIsObstacle(int deltaX, int deltaY) {
//        boolean obstacle = false;
//        int xNeighbor = xPos + deltaX;
//        int yNeighbor = yPos + deltaY;
//
//        if (deltaX != 0 && gameArea.getTiles()[xNeighbor][yPos].isObstacle()
//                || deltaY != 0 && gameArea.getTiles()[xPos][yNeighbor].isObstacle()) {
//            obstacle = true;
//        }
//        return obstacle;
//    }
//
//    private boolean boundaryOfMap(int deltaX, int deltaY) {
//        return (xPos == 0 && deltaX == -1
//                || xPos == 9 && deltaX == 1
//                || yPos == 0 && deltaY == -1
//                || yPos == 9 && deltaY == 1);
//    }

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
    void fight() {

    }

    @Override
    void setDefaultStats() {
        Random dice = new Random();
        defaultHealthPoint = 20 + 3 * (dice.nextInt(5) + 1);
        healthPoint = defaultHealthPoint;
        defensePoint = 2 * (dice.nextInt(5) + 1);
        strikePoint = 5 + (dice.nextInt(5) + 1);
    }

    @Override
    PositionedImage getTileImage() {
        return tileImage;
    }

    public void setGameArea(Area gameArea) {
        this.gameArea = gameArea;
    }

    public void incrementNumberOfMoves() {
        numberOfMoves++;
    }

    public int getNumberOfMoves(){
        return numberOfMoves;
    }

}