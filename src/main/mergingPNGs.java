package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 * This code try to join two BufferedImage
 * @author wangdq
 * 2013-12-29
 */
public class mergingPNGs {
    public static void main(String args[])
    {
        try {
            // TODO: 8/3/2017 Change Paths
            BufferedImage img1 = ImageIO.read(new File("motherboard.png"));
            BufferedImage img2 = ImageIO.read(new File("iAmaBarCode.png"));
            BufferedImage img3 = ImageIO.read(new File("no beep dead motherboard.png"));
            BufferedImage joinedImg = joinBufferedImage(img1,img2, img3);
            boolean success = ImageIO.write(joinedImg, "png", new File("joined.png"));
            System.out.println("saved success? "+success);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * join two BufferedImage
     * you can add a orientation parameter to control direction
     * you can use a array to join more BufferedImage
     */

    public static BufferedImage joinBufferedImage(BufferedImage img1,BufferedImage img2, BufferedImage img3) {

        //do some calculate first
        int offset  = 10;
//        int wid = img1.getWidth()+img2.getWidth()+offset;
        int wid = Math.max(Math.max(img1.getWidth(),img2.getWidth()),Math.max(img2.getWidth(),img3.getWidth()))+offset;
//        int height =300;
        int height =img1.getHeight()+img2.getHeight()+img3.getHeight()+(offset*2);
        System.out.println(height);
        //create a new buffer and draw two image into the new image
        BufferedImage newImage = new BufferedImage(wid,height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = newImage.createGraphics();
        Color oldColor = g2.getColor();
        //fill background
        g2.setPaint(Color.WHITE);
        g2.fillRect(0, 0, wid, height);
        //draw image
        g2.setColor(oldColor);
        Integer one, two ,three, max = null;
        one = img1.getWidth();
        two = img2.getWidth();
        three = img3.getWidth();
        max = Math.max(Math.max(one, two),Math.max(two, three));
        System.out.println(one);
        System.out.println(two);
        System.out.println(three);
        System.out.println(max);
        g2.drawImage(img1, null, 4 + ((max - img1.getWidth())/2), 0);
        g2.drawImage(img2, null, 4 + ((max - img2.getWidth())/2),img1.getHeight()+offset);
        g2.drawImage(img3, null, 4 + ((max - img3.getWidth())/2), (img1.getHeight()+ img2.getHeight())+(offset * 2));
        g2.dispose();
        return newImage;
    }
}