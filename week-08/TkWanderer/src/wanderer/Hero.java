package wanderer;

import java.util.ArrayList;
import java.util.HashMap;

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
    public Area gameArea;

    public Hero(int xPos, int yPos) {
        super("H");
        this.xPos = xPos;
        this.yPos = yPos;
        createElementImage();
    }

    @Override
    void move(int deltaX, int deltaY) {
        if (movementIsPossible(deltaX, deltaY)) {

            xPos += deltaX;
            yPos += deltaY;
        }
        changeElementImage(deltaX, deltaY);
        moveElementImage();
    }

    private boolean movementIsPossible(int deltaX, int deltaY) {
        boolean possible = true;
        if (boundaryOfMap(deltaX, deltaY) || neighborIsObstacle(deltaX, deltaY)) {
            possible = false;
        }
        return possible;
    }

    private boolean neighborIsObstacle(int deltaX, int deltaY) {
        boolean obstacle = false;

        if (deltaX != 0 && gameArea.getTiles()[xPos + deltaX][yPos].getType().equals("W")
                || deltaY != 0 && gameArea.getTiles()[xPos][yPos + deltaY].getType().equals("W")) {
            obstacle = true;
        }
        return obstacle;
    }

    private boolean boundaryOfMap(int deltaX, int deltaY) {
        return (xPos == 0 && deltaX == -1
                || xPos == 9 && deltaX == 1
                || yPos == 0 && deltaY == -1
                || yPos == 9 && deltaY == 1);
    }

    private void changeElementImage(int deltaX, int deltaY) {
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
    void createElementImage() {
        changeElementImage(0, 1);
    }

    @Override
    void moveElementImage() {
        tileImage.setPosX(xPos * imageSize);
        tileImage.setPosY(yPos * imageSize);
    }

    @Override
    void setStartingStats() {

    }

    @Override
    ArrayList<GameElement> inspectNeighbors() {
        return null;
    }

    @Override
    Integer getXPosition() {
        return null;
    }

    @Override
    Integer getYPosition() {
        return null;
    }

    @Override
    Integer getHealthPoint() {
        return null;
    }

    @Override
    Integer getDefensePoint() {
        return null;
    }

    @Override
    Integer getStrikePoint() {
        return null;
    }

    @Override
    GameElement getNeighbor() {
        return null;
    }

    @Override
    PositionedImage getTileImage() {
        return tileImage;
    }

    public void setGameArea(Area gameArea) {
        this.gameArea = gameArea;
    }
}
