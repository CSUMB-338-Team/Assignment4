
/*------------------------------------------------------
 * DataMatrix
 *---------------------------------------------------- */
public class DataMatrix implements BarcodeIO
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
      this.actualWidth = 0;
      this.actualHeight = 0;
      this.image = new BarcodeImage();
   }
   
   public DataMatrix(BarcodeImage image)
   {
      scan(image);
   }
   
   public DataMatrix(String text)
   {
      readText(text);
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
      if(text == null || text.equals(""))
         return false;
      
      this.text = text;
      return true;
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
      return actualHeight;
   }
   
   public int getActualWidth()
   {
      return actualWidth;
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