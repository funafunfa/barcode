package main;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.swing.*;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.formatter.BarcodeFormatter;
import net.sourceforge.barbecue.output.OutputException;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.*;
public class Main {

    public static String argument1, argument2, argument3;
// For catching and storing arguments


    public static void main(String args[]) throws BarcodeException, OutputException {
//        usingBarbecueAsSwingComponent(args[0]);
//        drawingBarcodeDirectToGraphics(args[0]);
        argument1 = args[0];
        argument2 = args[1];
        argument3 = args[2];
        outputtingBarcodeAsPNG(text);
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




//            Graphics g = f;
//            g.setFont(g.getFont().deriveFont(30f));
//            g.drawString("Hello World!", 100, 100);
//            g.dispose();


        } catch (Exception e) {
            // Error handling here
        }
    }
}
