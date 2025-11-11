import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class NPC1 {

    GamePanel gp;
    public int worldX, worldY;
    public int speed;
    public String direction;
    public BufferedImage up1, down1, left1, right1;
    public String[][] dialogues = new String[10][10];
    public int actionLockCounter = 0;

    public NPC1(GamePanel gp) {
        this.gp = gp;
        worldX = 200;
        worldY = 200;
        direction = "down";
        speed = 1;
        getImage();
        setDialogue();
    }

    public void getImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc1_up_1.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc1_down_1.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc1_left_1.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/npc/npc1_right_1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("NPC1 image not found in /resources/npc/ folder!");
        }
    }

    public void setDialogue() {
        dialogues[0][0] = "Hello there!";
        dialogues[0][1] = "It's a nice day to explore, isn't it?";
        dialogues[0][2] = "Be careful of the monsters ahead!";
        dialogues[0][3] = "If you need help, come talk to me again.";
        dialogues[0][4] = "Good luck on your adventure!";
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
