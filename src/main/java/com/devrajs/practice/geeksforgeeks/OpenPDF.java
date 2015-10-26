package com.devrajs.practice.geeksforgeeks;

import com.itextpdf.text.pdf.PdfReader;

import java.io.File;
import java.io.IOException;

/**
 * Created by devraj.singh on 10/2/15.
 */
public class OpenPDF {

    public static void main(String[] args)
    {
        String pdfFile="/Users/devraj.singh/Downloads/noname";
        File file = new File(pdfFile);
        if(!file.exists())
        {
            System.out.println("No file..:(");
            return;
        }
        for(int i=0;i<999999999;i++) {
            try {
                PdfReader pdfReader = new PdfReader(pdfFile, (i+"").getBytes());
            } catch (IOException e) {
                //System.out.println("oops");
                continue;
            }
            System.out.println("got the key..yay : " + i);
            break;
        }
        /*long t1 = System.currentTimeMillis();
        for(int i=0;i<999999999;i++)
        {
            int a=1;
        }
        long t2 = System.currentTimeMillis();
        System.out.println(t2-t1);*/
    }
}
