
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
               
            
         
         String[] sImageIn_2 =
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
         DataMatrix dm = new DataMatrix(bc);
        
         // First secret message
         dm.translateImageToText();
         dm.displayTextToConsole();
         dm.displayImageToConsole();
         
         // second secret message
         bc = new BarcodeImage(sImageIn_2);
         dm.scan(bc);
         dm.translateImageToText();
         dm.displayTextToConsole();
         dm.displayImageToConsole();
         
         // create your own message
         dm.readText("What a great resume builder this is!");
         dm.generateImageFromText();
         dm.displayTextToConsole();
         dm.displayImageToConsole();
   }

}


/*------------------------------------------------------
 * BarcodeIO Interface
 *---------------------------------------------------- */
interface BarcodeIO
{
   /*
    * scan accepts a BarcodeImage object, bc, and stores a copy 
    */
   public boolean scan(BarcodeImage bc);
   
   /*
    * readText accepts a String object, text, to be encoded
    */
   public boolean readText(String text);
   
   /*
    * generateImageFromText creates a BarCodeImage object
    */
   public boolean generateImageFromText();
   
   /*
    * translateImageToText examines an image and creates a String
    */
   public boolean translateImageToText();
   
   /*
    * displayTextToConsole outputs a String to the console
    */
   public void displayTextToConsole();
   
   /*
    * displayImageToConsole prints an image to the console
    */
   public void displayImageToConsole();
   
}
/*------------------------------------------------------
 * END interface BarcodeIO
 *---------------------------------------------------- */



/*------------------------------------------------------
 * BarcodeImage
 *---------------------------------------------------- */
class BarcodeImage implements Cloneable
{
   public static final int MAX_HEIGHT = 30;
   public static final int MAX_WIDTH = 65;
   private boolean[][] image_data;
   
   /*
    * default constructor has no input and sets all values of image_data
    * to false
    */
   public BarcodeImage()
   {
      this.image_data = new boolean [MAX_HEIGHT][MAX_WIDTH];
      generateBlankImage();
   }
   
   /*
    * constructor accepts a 1 dimensional String[] str_data and converts to 
    * boolean image_data
    */
   public BarcodeImage(String[] str_data)
   {
      
      this.image_data = new boolean[MAX_HEIGHT][MAX_WIDTH];
     int strIndex = str_data.length - 1;
         
      if(!checkSize(str_data))
      {
         generateBlankImage();
         return;
      }
      
      for (int i = MAX_HEIGHT - 1; i >= 0; i--)
      {
         for (int j = 0; j < MAX_WIDTH; j++)
         {
            
            if(strIndex >= 0 && j < str_data[strIndex].length())
            {
               
               if(str_data[strIndex].charAt(j) == ' ')
                  setPixel(i, j, false);
               else 
                  setPixel(i, j, true);
               
            }else
            {
               setPixel(i, j, false);
            }
            
         }
         strIndex--;
      }
   }
   
   /*
    * mutator setPixel accepts ints row and col, and boolean value
    * and sets image_data
    */
   public boolean setPixel(int row, int col, boolean value)
   {
     if (isValid(row, col))
      {
         this.image_data[row][col] = value;
         return true;
      }

      return false;
   }
   
   /*
    * getPixel returns the value of image_data at row, col
    * or false is row or col are invalid
    */
   public boolean getPixel(int row, int col)
   {
      if (isValid(row, col))
         return this.image_data[row][col];
      
      return false;
   }
   
   private boolean isValid(int row, int col)
   {
      return (( row >= 0 && row < MAX_HEIGHT) && 
            (col >= 0 && col < MAX_WIDTH));
   }
   
   public Object clone()
   {
      try
      {
         BarcodeImage copy = (BarcodeImage)super.clone();
         copy.image_data = (boolean[][])image_data.clone();
         return copy;
      }
      catch (CloneNotSupportedException e)
      {
         return null;
      }
   }
   
   private boolean checkSize(String[] data)
   {
      if(data == null || data.length > MAX_HEIGHT)
         return false;
      
      for(String d: data)
      {
         if(d == null || d.length() > MAX_WIDTH)
            return false;
      }
      
      return true;
   }
   
   // only used for debugging
   public void displayToConsole()
   {
      for (int i = 0; i < MAX_HEIGHT; i++)
      {
         for (int j = 0; j < MAX_WIDTH; j++)
         {
            if(image_data[i][j])
               System.out.print('*');
            else
               System.out.print('-');
         }
         System.out.print("\n");
      }
   }
   
   private void generateBlankImage()
   {
      for (int i = 0; i < MAX_HEIGHT; i++)
         for (int j = 0; j < MAX_WIDTH; j++)
           this.image_data[i][j] = false;
   }
   
}
/*------------------------------------------------------
 * END class BarcodeImage
 *---------------------------------------------------- */



/*------------------------------------------------------
 * DataMatrix
 *---------------------------------------------------- */
class DataMatrix implements BarcodeIO
{
   
   public static final char BLACK_CHAR = '*';
   public static final char WHITE_CHAR = ' ';
   public static final char BOARDER_HORIZONTAL = '-';
   public static final char BOARDER_VERTICAL = '|';
   private BarcodeImage image;
   private String text;
   private int actualWidth;
   private int actualHeight;
   
   /*
    * Add constructor comment
    * */
   public DataMatrix()
   {
      this.text = "";
      this.actualWidth = 0;
      this.actualHeight = 0;
      this.image = new BarcodeImage();
   }
   
   /*
    * Add constructor comment
    * */
   public DataMatrix(BarcodeImage image)
   {
      scan(image);
   }
   
   /*
    * Add constructor comment
    * */
   public DataMatrix(String text)
   {
      readText(text);
   }
   
   /*
    * Scan comment
    * */
   @Override
   public boolean scan(BarcodeImage bc)
   {
      try
      {
         
         image = (BarcodeImage) bc.clone();
         cleanImage();
         actualWidth = computeSignalWidth();
         actualHeight = computeSignalHeight();
         
      } catch (Exception e)
      {
         
         return false;
         
      }

      return true;
   }

   /*
    * readText comment
    * */
   @Override
   public boolean readText(String text)
   {
      if(text == null || text.equals(""))
         return false;
      
      this.text = text;
      return true;
   }
   
   
   /*
    * generateImageFromText comment
    * */
   @Override
   public boolean generateImageFromText()
   {
      if(text == null || text.equals("") || 
            text.length() > BarcodeImage.MAX_WIDTH)
         return false;
         
      int valueASCII;
      
      clearImage();
      actualWidth = text.length() + 2;
      
      for(int i = 0; i < text.length(); i++)
      {
         valueASCII = (int)text.charAt(i);
         writeCharToCol(i + 1, valueASCII);
      }
      
      generateOpenBorders();
      generateLimitationLines();
      
      cleanImage();
      return true;

   }

   /*
    * translateImageToText
    * */
   @Override
   public boolean translateImageToText()
   {
      text = "";
      
      if (image == null)
         return false;
      
      for (int i = 1; i <= actualWidth - 2; i++)
         text += readCharFromCol(i);
      
      return true;   
   }

   /*
    * displayTextToConsole
    * */
   @Override
   public void displayTextToConsole()
   {
      
      System.out.println(text);
      
   }

   /*
    * displayImageToConsole
    * */
   @Override
   public void displayImageToConsole()
   {
      
      generateHorizontalLine(0, actualWidth + 2);
      
      for(int i = BarcodeImage.MAX_HEIGHT - actualHeight; 
            i < BarcodeImage.MAX_HEIGHT; i++)
      {        
         for (int j = 0; j < actualWidth + 2; j++)
         {
            if(j == 0)
               System.out.print(BOARDER_VERTICAL);
            else if(j == actualWidth + 1)
               System.out.print(BOARDER_VERTICAL);
            else if(image.getPixel(i, j - 1))
               System.out.print(BLACK_CHAR);
            else
               System.out.print(WHITE_CHAR);
         }
         System.out.println();
      }
      
      generateHorizontalLine(0, actualWidth + 2);
      
   }
  
   /*
    * displayRawImage
    * */
   public void displayRawImage()
   {
      generateHorizontalLine(0, BarcodeImage.MAX_WIDTH + 2);
      
      for (int i = 0; i < BarcodeImage.MAX_HEIGHT; i++)
      {
         for (int j = 0; j < BarcodeImage.MAX_WIDTH + 2; j++)
         {
            if(j == 0)
               System.out.print(BOARDER_VERTICAL);
            else if(j == BarcodeImage.MAX_WIDTH + 1)
               System.out.print(BOARDER_VERTICAL);
            else if(image.getPixel(i, j))
               System.out.print(BLACK_CHAR);
            else
               System.out.print(' ');
         }
         System.out.println();
      }
      
      generateHorizontalLine(0, BarcodeImage.MAX_WIDTH + 2);
   }
   
   /*
    * getActualHeight
    * */
   public int getActualHeight()
   {
      return actualHeight;
   }
   
   /*
    * getActualWidth
    * */
   public int getActualWidth()
   {
      return actualWidth;
   }
   
   
   // private helper functions
   /*
    * computeSignalWidth
    * */
   private int computeSignalWidth()
   {
      int count = 0;
      
      for(int i = 0; i < BarcodeImage.MAX_WIDTH; i++)
      {
         if(image.getPixel(BarcodeImage.MAX_HEIGHT - 1, i))
            count++;
      }
      
      return count;
   }
   
   /*
    * computeSignalHeight
    * */
   private int computeSignalHeight()
   {
      int count = 0;
      
      for(int i = BarcodeImage.MAX_HEIGHT - 1; i >= 0; i--)
      {
         if(image.getPixel(i, 0))
            count++;
      }
      
      return count;
   }
   
   /*
    * cleanImage
    * */
   private void cleanImage()
   {
      moveImageToLowerLeft();
   }
   
   /*
    * moveImageToLowerLeft
    * */
   private void moveImageToLowerLeft()
   {
      boolean foundBottmLeft = false;
      
      for(int i = BarcodeImage.MAX_HEIGHT - 1; i >= 0; i--)
      {
         if(foundBottmLeft)
            break;
         
         for (int j = 0; j < BarcodeImage.MAX_WIDTH; j++)
         {
            if(image.getPixel(i, j))
            {
               shiftImageDown((BarcodeImage.MAX_HEIGHT -1) - i);
               shiftImageLeft(j);
               foundBottmLeft = true;
               break;
            }
         }
      }

   }
   
   /*
    * shiftImageDown
    * */
   private void shiftImageDown(int offset)
   {
      
      if(offset == 0)
         return;
      
      for(int i = BarcodeImage.MAX_HEIGHT - 1; i >= 0; i--)
      {  
        
         for (int j = 0; j < BarcodeImage.MAX_WIDTH; j++)
         {
            if(image.getPixel(i - offset, j))
               image.setPixel(i, j, true);
            else
               image.setPixel(i, j, false);
         }
         
      }
   }
   
   /*
    * shiftImageLeft
    * */
   private void shiftImageLeft(int offset)
   {
      
      if(offset == 0)
         return;
      
      for(int i = BarcodeImage.MAX_HEIGHT - 1; i >= 0; i--)
      {        
         for (int j = 0; j < BarcodeImage.MAX_WIDTH; j++)
         {
            if(j + offset > BarcodeImage.MAX_WIDTH)
               break;
            
            if(image.getPixel(i, j + offset))
               image.setPixel(i, j, true);
            else
               image.setPixel(i, j, false);
         }
      }
   }
   
   /*
    * clearImage
    * */
   private void clearImage()
   {
      for(int i = BarcodeImage.MAX_HEIGHT - 1; i >= 0; i--)
      {        
         for (int j = 0; j < BarcodeImage.MAX_WIDTH; j++)
         {
            image.setPixel(i, j, false);
         }
      }
   }
   
   /*
    * readCharFromCol
    * */
   private char readCharFromCol(int col)
   {
      int valueAt = 0;
      int power = 0;
      
      for (int i = BarcodeImage.MAX_HEIGHT - 2; 
            i > BarcodeImage.MAX_HEIGHT - actualHeight; i--, power++)
      {
         if (image.getPixel(i, col))
            valueAt += Math.pow(2,  power);
      }

      return (char) valueAt;
   }
  
   /*
    * generateOpenBorders
    * */
   private void generateOpenBorders()
   {
     
      generateTopOpenBorders();
      generateRightOpenBorders();

   }
   
   /*
    * generateTopOpenBorders
    * */
   private void generateTopOpenBorders()
   {
      for(int i = 0; i < actualWidth; i++)
      {
         if(i % 2 == 0)
            image.setPixel(0, i, true);
         else
            image.setPixel(0, i, false);
      }
   }
   
   /*
    * generateRightOpenBorders
    * */
   private void generateRightOpenBorders()
   {
      for(int i = 1; i < actualHeight; i++)
      {
         if(i % 2 != 0)
            image.setPixel(i, actualWidth - 1, true);
         else
            image.setPixel(i, actualWidth - 1, false);
      }
   }
   
   /*
    * generateLimitationLines
    * */
   private void generateLimitationLines()
   {
      generateLeftLimitationLines();
      generateBottomLimitationLines();
   }
   
   /*
    * generateBottomLimitationLines
    * */
   private void generateBottomLimitationLines()
   {
      for(int i = 0; i < actualWidth; i++)
         image.setPixel(actualHeight - 1, i, true);
   }
   
   /*
    * generateLeftLimitationLines
    * */
   private void generateLeftLimitationLines()
   {
      for(int i = 0; i < actualHeight; i++)
         image.setPixel(i, 0, true);
   }
   
   /*
    * writeCharToCol
    * */
   private boolean writeCharToCol(int col, int code)
   {
      
      if(col > BarcodeImage.MAX_WIDTH)
         return false;
      
      int maxASCII = 128;
      
      for(int i = 0; i < actualHeight - 2; i++)
      {
         if(code >= maxASCII)
         {
            code -= maxASCII;
            image.setPixel(i + 1, col, true);
         }
      
         maxASCII /= 2;
      }
      
      return true;
   }
   
   /*
    * private helper
    * */
   private void generateHorizontalLine(int start, int end)
   {
      for(int i = start; i < end; i++)
         System.out.print(BOARDER_HORIZONTAL);
      System.out.println();
   }
   
}
/*------------------------------------------------------
 * END class DataMatrix
 *---------------------------------------------------- */
