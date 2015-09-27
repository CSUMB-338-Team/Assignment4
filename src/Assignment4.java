
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
      
      BarcodeImage bc = new BarcodeImage(sImageIn);
      //for reference
      System.out.println("Reference of orig image");
      bc.displayToConsole();
      System.out.println();
      
      DataMatrix dm = new DataMatrix(bc);
      
      System.out.println("Reference of Full image");
      dm.displayRawImage();
      System.out.println();
      
      System.out.println("Cleaned image");
      dm.displayImageToConsole();
      System.out.println();

   }

}
