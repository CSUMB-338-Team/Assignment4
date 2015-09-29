
/*
* Name
* Assignment 4 - Optical Barcode Readers and Writers
* 
*
* */

public class Assignment4
{

 public static void main(String[] args)
   {
      
      // BarcodeImage test
      String[] sImageIn =
         {
            "                                               ",
            "                                               ",
            "                                               ",
            "     * * * * * * * * * * * * * * * * * * * * * ",
            "     *                                       * ",
            "     ****** **** ****** ******* ** *** *****   ",
            "     *     *    ****************************** ",
            "     * **    * *        **  *    * * *   *     ",
            "     *   *    *  *****    *   * *   *  **  *** ",
            "     *  **     * *** **   **  *    **  ***  *  ",
            "     ***  * **   **  *   ****    *  *  ** * ** ",
            "     *****  ***  *  * *   ** ** **  *   * *    ",
            "     ***************************************** ",  
            "                                               ",
            "                                               ",
            "                                               "

         }; 
      
      String[] sImageIn2 =
      {
         "                                          ",
         "                                          ",
         "* * * * * * * * * * * * * * * * * * *     ",
         "*                                    *    ",
         "**** *** **   ***** ****   *********      ",
         "* ************ ************ **********    ",
         "** *      *    *  * * *         * *       ",
         "***   *  *           * **    *      **    ",
         "* ** * *  *   * * * **  *   ***   ***     ",
         "* *           **    *****  *   **   **    ",
         "****  *  * *  * **  ** *   ** *  * *      ",
         "**************************************    ",
         "                                          ",
         "                                          ",
         "                                          ",
         "                                          "
      };
      
      BarcodeImage bc = new BarcodeImage(sImageIn);

      //for reference
      System.out.println("Reference of orig image");
      bc.displayToConsole();
      System.out.println();
      
      DataMatrix dm = new DataMatrix(bc);
      
      System.out.println("First message:");
      dm.translateImageToText();
      dm.displayTextToConsole();
      dm.displayImageToConsole();
      System.out.println();
      
      bc = new BarcodeImage(sImageIn2);
      dm.scan(bc);
      System.out.println("Second message:");
      dm.translateImageToText();
      dm.displayTextToConsole();
      dm.displayImageToConsole();
      System.out.println();
      
      System.out.println("Create your own message:");
      dm.readText("Testing");
      dm.generateImageFromText();
      dm.displayTextToConsole();
      dm.displayImageToConsole();
      
      
      //System.out.println("Reference of Full image");
      //dm.displayRawImage();
      //System.out.println();
      
      //System.out.println("Cleaned image");
      //dm.displayImageToConsole();
      //System.out.println();
   }

}
