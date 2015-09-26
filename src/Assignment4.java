
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
      // TODO Auto-generated method stub

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
   
}//END interface BarcodeIO



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
      
      for (int i = 0; i < this.image_data.length; i++)
         for (int j = 0; j < this.image_data[i].length; j++)
            this.image_data[i][j] = false;
   }
   
   /*
    * constructor accepts a 1 dimensional String[] str_data and converts to 
    * boolean image_data
    */
   public BarcodeImage(String[] str_data)
   {
      this.image_data = new boolean[MAX_HEIGHT][MAX_WIDTH];
      for (int i = MAX_HEIGHT - 1; i == 0; i--)
         for (int j = 0; j < MAX_WIDTH; j++)
            if (str_data[i].charAt(j) == '*')
               setPixel(i, j, true);
            else setPixel(i, j, false);         
   }
   
   /*
    * mutator setPixel accepts ints row and col, and boolean value
    * and sets image_data
    */
   public boolean setPixel(int row, int col, boolean value)
   {
      if (isValid(row, col) && value == true)
      {
         this.image_data[row][col] = value;
         return true;
      }
      else if (isValid(row, col) && value == false)
      {
         this.image_data[row][col] = false;
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
      else return false;
   }
   
   /*
    * isValid takes ints, row and col, returns true if they fit in the 
    * matrix size
    */
   private boolean isValid(int row, int col)
   {
      return (row < MAX_WIDTH && col < MAX_HEIGHT);
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
   
   // to be built
   private boolean checkSize(String[] data)
   {
      return false;
   }
   
   // to be built
   public void displayToConsole()
   {
      
   }
   
} //END class BarcodeImage



/*------------------------------------------------------
 * DataMatrix
 *---------------------------------------------------- */
class DataMatrix implements BarcodeIO
{
   
   public static final char BLACK_CHAR = '*';
   public static final char WHITE_CHAR = ' ';
   private BarcodeImage image;
   private String text;
   private int actualWidth;
   private int actualHeight;
   
   public DataMatrix()
   {
      this.text = "";
      this.image = new BarcodeImage();
   }
   
   public DataMatrix(BarcodeImage image)
   {
      this.image = image;
      scan(this.image);
   }
   
   public DataMatrix(String text)
   {
      this.text = text;
      readText(this.text);
   }
   
   @Override
   public boolean scan(BarcodeImage bc)
   {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean readText(String text)
   {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean generateImageFromText()
   {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean translateImageToText()
   {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public void displayTextToConsole()
   {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void displayImageToConsole()
   {
      // TODO Auto-generated method stub
      
   }
  
   public void displayRawImage()
   {
      
   }
   
   public int getActualHeight()
   {
      return 0;
   }
   
   public int getActualWidth()
   {
      return 0;
   }
   
   private int computeSignalWidth()
   {
      return 0;
   }
   
   private void cleanImage()
   {
      
   }
   
   private void clearImage()
   {
      
   }

} //END class DataMatrix
