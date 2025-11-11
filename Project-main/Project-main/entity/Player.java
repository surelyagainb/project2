package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;
import main.CollisionChecker;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y; 
        solidArea.width = 20;  // smaller hitbox in order to fit in 2 solid objects
        solidArea.height = 20; // smaller hitbox in order to fit in 2 solid objects

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23; 
        worldY = gp.tileSize * 23;
        speed = 1; 
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/yes/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/yes/up2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/yes/up3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/yes/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/yes/down2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/yes/down3.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/yes/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/yes/left2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/yes/left3.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/yes/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/yes/right2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/yes/right3.png"));
        } 
        catch (IOException e) {
            e.printStackTrace();
        } 
    }

    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            
            if (keyH.upPressed) {
                direction = "up";
            } 
            else if (keyH.downPressed) {
                direction = "down";
            } 
            else if (keyH.leftPressed) {
                direction = "left";
            } 
            else if (keyH.rightPressed) {
                direction = "right";
            }

            spriteCounter++;
            if(spriteCounter > 60){
                if(spriteNum == 1){
                    spriteNum = 2;
                    spriteCounter =0;
                }
                else if(spriteNum == 2){
                    spriteNum = 3;
                    spriteCounter =0;
                }
                else if(spriteNum == 3){
                    spriteNum = 1;
                    spriteCounter =0;
                }
            }

            collisionOn = false;
            gp.cChecker.checkTile(this);

            if (collisionOn == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                if(spriteNum == 3){
                    image = up3;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                if(spriteNum == 3){
                    image = down3;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                if(spriteNum == 3){
                    image = left3;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                if(spriteNum == 3){
                    image = right3;
                }
                break;
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

}

