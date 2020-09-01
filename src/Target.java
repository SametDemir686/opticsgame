
import java.awt.*;
import java.util.*;

public class Target implements OpticStuff{

   Color color;
   int x;
   int y;
   boolean hit;
   ArrayList<Light> list;

   public Target(int row, int column, Color newColor )
   {
      hit = false;
      x = row * 60;
      y = column * 60;
      color = newColor;
      list = new ArrayList<Light>();

   }

   public OpticStuff cloneOp()
   {
      return new Target(getRow(), getColumn(), color);
   }

   public boolean isHit (ArrayList<Light> list)
   {
      this.list = list;

      for (Light light : list)
      {
         if ( touches(light) == 0 && light.getColor().equals(color))
         {
            hit = true;
            return true;
         }
      }

      hit = false;
      return false;
   }

   @Override
   public int getRow() {
      return x/60;
   }

   @Override
   public int getColumn() {
      return y/60;
   }

   @Override
   public int getX() {
      return x;
   }

   @Override
   public int getY() {
      return y;
   }

   @Override
   public int touches(Light light) {

      int centerX = x + 30;
      int centerY = y + 30;


      if ( (light.endx - centerX)*(light.endx - centerX) + (light.endy - centerY)*(light.endy - centerY) <= 400)
      {
         return 0;
      }

      return -1;

   }

   @Override
   public String getImageName()
   {
      if(hit)
      {
         if( color == Color.RED ) return "images/red hit target.png";
         else if( color == Color.GREEN ) return "images/green hit target.png";
         else if( color == Color.BLUE ) return "images/blue hit target.png";
         else if( color == Color.MAGENTA ) return "images/magenta hit target.png";
         else if( color == Color.YELLOW ) return "images/yellow hit target.png";
         else if( color == Color.CYAN ) return "images/cyan hit target.png";
      }
      else
      {
         if( color == Color.RED ) return "images/red target.png";
         else if( color == Color.GREEN ) return "images/green target.png";
         else if( color == Color.BLUE ) return "images/blue target.png";
         else if( color == Color.MAGENTA ) return "images/magenta target.png";
         else if( color == Color.YELLOW ) return "images/yellow target.png";
         else if( color == Color.CYAN ) return "images/cyan target.png";
      }
      return "";
   }
}
