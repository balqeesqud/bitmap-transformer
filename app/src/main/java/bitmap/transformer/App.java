/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package bitmap.transformer;

import java.awt.*;
import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {

        if(args.length>0){

            String file=args[0];String file2=args[1];

            BitMap bitmap=new BitMap(file);
            if(args[2].equals("gray")){
                bitmap.addBorder(200, Color.WHITE);
                bitmap.write(file2);


            }
            if(args[2].equals("invert")){
                bitmap.rotateImage(95.5);
                bitmap.invertColors();
                bitmap.addBorder(50, Color.RED);
                bitmap.write(file2);


            }


        }


    }

}
