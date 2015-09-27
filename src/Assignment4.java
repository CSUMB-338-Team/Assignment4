
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
      bc.displayToConsole();
      DataMatrix dm = new DataMatrix(bc);
      dm.displayImageToConsole();

   }

}
