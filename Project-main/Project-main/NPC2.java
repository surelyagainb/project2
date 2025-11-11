import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class NPC2 {

    GamePanel gp;
    public int worldX, worldY;
    public int speed;
    public String direction;
    public BufferedImage up1, down1, left1,  right1;
    public String[][] dialogues = new String[10][10];
    public int actionLockCounter = 0;

    public NPC2(GamePanel gp) {
        this.gp = gp;
        worldX = 300;
        worldY = 200;
        direction = "down";
        speed = 1;
        getImage();
        setDialogue();
    }

    public void getImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc2_up_1.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc2_down_1.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc2_left_1.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc2_right_1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("NPC2 image not found in /resources/npc/ folder!");
        }
    }

    public void setDialogue() {
        dialogues[0][0] = "He he ha, so you found me. I have some good stuff. Do you want to trade?";
        dialogues[0][1] = "Come again, hehe!";
        dialogues[0][2] = "You need more coin to buy that!";
        dialogues[0][3] = "You can not carry any more!";
        dialogues[0][4] = "You can not sell an equipped item!";
    }

    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(4);
            switch (i) {
                case 0: direction = "up"; break;
                case 1: direction = "down"; break;
                case 2: direction = "left"; break;
                case 3: direction = "right"; break;
            }
            actionLockCounter = 0;
        }
    }

    public void update() {
        setAction();
        switch (direction) {
            case "up":    worldY -= speed; break;
            case "down":  worldY += speed; break;
            case "left":  worldX -= speed; break;
            case "right": worldX += speed; break;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":    image = up1; break;
            case "down":  image = down1; break;
            case "left":  image = left1; break;
            case "right": image = right1; break;
        }
        if (image != null) {
            g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
        }
    }
}
