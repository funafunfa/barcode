package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class doublingPNGs {
    public static void main(String[] args) {
        try {
            BufferedImage img1 = ImageIO.read(new File("joined.png"));
            BufferedImage joinedImg = joinBufferedImage(img1,img1);
            joinedImg.getScaledInstance(10,10,Image.SCALE_DEFAULT);
            // TODO: 8/3/2017 Change paths
            // TODO: 8/3/2017 Make normal logs for nerds
            boolean success = ImageIO.write(joinedImg, "png", new File("doubled.png"));
            System.out.println("saved success? "+success);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static BufferedImage joinBufferedImage(BufferedImage img1, BufferedImage img2) {

        //do some calculate first
        int offset  = 100;
//        int wid = img1.getWidth()+img2.getWidth()+offset;
        int wid =img1.getWidth();
        int height =(img1.getHeight() *2) + offset ;
        //create a new buffer and draw two image into the new image
        BufferedImage newImage = new BufferedImage(wid,height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = newImage.createGraphics();
        Color oldColor = g2.getColor();
        //fill background
        g2.setPaint(Color.WHITE);
        g2.fillRect(0, 0, wid, height);
        //draw image
        g2.setColor(oldColor);
        g2.drawImage(img1, null, 0, img2.getHeight()+offset);
        g2.drawImage(img2, null, 0, 0);
        g2.dispose();
        return newImage;
    }
}
