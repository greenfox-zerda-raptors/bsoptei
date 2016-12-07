package wanderer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Söp on 2016.12.05.. 
 */
class GameElement extends GameObject {
    private PositionedImage tileImage;


    GameElement(int xPos, int yPos, String type) {
        super(type);
        this.xPos = xPos;
        this.yPos = yPos;
        this.type = type;
        createElementImage();
    }

    @Override
    public void createElementImage() {
        this.tileImage = new PositionedImage(imageSelector.get(type), xPos * imageSize, yPos * imageSize);
    }

    @Override
    void move(int deltaX, int deltaY) {
        xPos += deltaX;
        yPos += deltaY;
        moveElementImage();
    }


    void fight() {
    }

    @Override
    public void moveElementImage() {
        tileImage.setPosX(xPos * imageSize);
        tileImage.setPosY(yPos * imageSize);
    }

    @Override
    void setStartingStats() {
    }

    @Override
    String getHP() {
        return null;
    }

    @Override
    String getDP() {
        return null;
    }

    @Override
    String getSP() {
        return null;
    }

    public PositionedImage getTileImage() {
        return tileImage;
    }


}