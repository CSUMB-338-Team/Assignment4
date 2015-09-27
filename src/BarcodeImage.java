/*------------------------------------------------------
 * BarcodeImage
 *---------------------------------------------------- */
public class BarcodeImage implements Cloneable
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
      
      for (int i = 0; i < MAX_HEIGHT; i++)
         for (int j = 0; j < MAX_WIDTH; j++)
            this.image_data[i][j] = false;
   }
   
   /*
    * constructor accepts a 1 dimensional String[] str_data and converts to 
    * boolean image_data
    */
   public BarcodeImage(String[] str_data)
   {
      if(str_data.length > MAX_HEIGHT || str_data[0].length() > MAX_WIDTH
            || str_data == null)
      {
         
      }
      
      this.image_data = new boolean[MAX_HEIGHT][MAX_WIDTH];
      for (int i = MAX_HEIGHT - 1; i == 0; i--)
      {
         for (int j = 0; j < MAX_WIDTH; j++)
         {
            if (str_data[i].charAt(j) == '*')
               setPixel(i, j, true);
            else setPixel(i, j, false);
         }
      }
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
      
      return false;
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