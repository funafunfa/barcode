package main;

import javax.imageio.ImageIO;
import javax.print.*;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;
import usseless.TextToGraphicConverter;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.*;

import static main.doublingPNGs.doubleBufferedImage;
import static main.mergingPNGs.joinBufferedImage;

public class Main {

    public static String firstText, textForBarcode, problem, barcodeFileName, combinedFileName;
// For catching and storing arguments


    public static void main(String args[]) throws BarcodeException, OutputException, ArrayIndexOutOfBoundsException {
        try {
            firstText = args[0];
            textForBarcode = args[1];
            problem = args[2];


            outputtingBarcodeAsPNG(textForBarcode);

            System.out.println("BarCode name: " + barcodeFileName);

            BufferedImage firstTextImage = new TextToGraphicConverter().convertTextToGraphic(firstText, new Font("Arial", Font.PLAIN, 24));
            ImageIO.write(firstTextImage, "png", new File(firstText + ".png"));

            System.out.println("Making firstTextImage picture: "+ firstText + ".png");
            BufferedImage problemImage = new TextToGraphicConverter().convertTextToGraphic(problem, new Font("Arial", Font.PLAIN, 24));

            System.out.println("Making problemImage picture: "+ problem + ".png");
            ImageIO.write(problemImage, "png", new File(problem + ".png"));

            BufferedImage img1 = ImageIO.read(new File(firstText + ".png"));
            BufferedImage img2 = ImageIO.read(new File(barcodeFileName));
            BufferedImage img3 = ImageIO.read(new File(problem + ".png"));
            BufferedImage joinedImg = joinBufferedImage(img1,img2, img3);

            combinedFileName = firstText + ".png";

            boolean success = ImageIO.write(joinedImg, "png", new File(combinedFileName));

            if (success) System.out.println("Combined picture was made: " + combinedFileName);

            BufferedImage combinedImage = ImageIO.read(new File(combinedFileName));
            BufferedImage doubledImage = doubleBufferedImage(combinedImage, combinedImage);
            // TODO: 8/3/2017 Change paths
            // TODO: 8/3/2017 Make normal logs for nerds
            success = ImageIO.write(doubledImage, "png", new File(combinedFileName));

            if (success) System.out.println("Doubled and Combined picture was made: " + combinedFileName);

            PrintService service = PrintServiceLookup.lookupDefaultPrintService();
            DocPrintJob job = service.createPrintJob();
            DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
            SimpleDoc doc = new SimpleDoc(new oneMoreTimeBabe.MyPrintable(), flavor, null);
            job.print(doc, null);
            System.out.println("Sent to the: " + service.getName());

        } catch (IOException | PrintException e) {
            e.printStackTrace();
        }
    }

    public static void outputtingBarcodeAsPNG(String given_data) throws BarcodeException {
        // get a Barcode from the BarcodeFactory
        Barcode barcode = BarcodeFactory.createCode128(given_data);
        try {
            File f = new File(given_data + ".png");
            System.out.println("Creating file");

            // Let the barcode image handler do the hard work
            BarcodeImageHandler.savePNG(barcode, f);
            System.out.println("Making a BarCode");
            barcodeFileName = given_data + ".png";
        } catch (Exception e) {
            // Error handling here
        }
    }
}
