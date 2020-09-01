import java.awt.Color; 

public class Filter extends Mirrors implements Changeable 
{ 
   Color color; 
   Color newColor; 
    
   public Filter(int row, int column, double angle, Color color)
   { 
      super(row*60,60*column,angle); 
       
      this.color = color; 
       
   } 
    
   @Override 
   public OpticStuff cloneOp()
   { 
      return new Filter (getRow(),getColumn(), angle, color); 
   } 
    
   @Override 
   public String getImageName() {
      if( color == Color.RED ) return "images/red filter.png"; 
      else if( color == Color.GREEN ) return "images/green filter.png"; 
      else if( color == Color.BLUE ) return "images/blue filter.png"; 
      else if( color == Color.MAGENTA ) return "images/magenta filter.png"; 
      else if( color == Color.CYAN ) return "images/cyan filter.png"; 
      else if( color == Color.YELLOW ) return "images/yellow filter.png"; 
      return ""; 
   } 
    
   @Override 
   public Light reflect(Light light)
   { 
      return new Light(light.endX, light.endY, light.getAngle(), newColor, light.game); 
   } 
    
   @Override  
   public int touches(Light light) {
       
      int colorR=0, colorG=0,colorB=0;
       
      if(color.getRed() == light.getColor().getRed() && color.getRed() == 255) 
      { 
         colorR = 255; 
      } 
      if(color.getGreen() == light.getColor().getGreen() && color.getGreen() == 255) 
      { 
         colorG = 255; 
      } 
      if(color.getBlue() == light.getColor().getBlue() && color.getBlue() == 255) 
      { 
         colorB = 255; 
      } 
       
      newColor = new Color (colorR, colorG, colorB); 
       
       
      double length = Math.sqrt((light.endy-getCenterY())*(light.endy-getCenterY())+(light.endx - getCenterX())*(light.endx - getCenterX() ));
       
      if(angle < 3.5 || angle > 356.5 || angle > 176.5 && angle < 183.5)
      { 
         if (Math.abs(getCenterX() - light.endx) < TOLERANCE && length < HEIGHT/2 ) 
         { 
            if ( newColor.getBlue() != 0 || newColor.getGreen() != 0 || newColor.getRed() != 0) 
            { 
               return 1; 
            } 
            return 0; 
         } 
         else 
            return -1; 
      } 
       
      else if ( Math.abs(getCenterY() + Math.tan( (Math.toRadians(90-angle)))*( light.endx - getCenterX())- light.endy) < TOLERANCE && length <= HEIGHT/2) 
      { 
         if ( newColor.getBlue() != 0 || newColor.getGreen() != 0 || newColor.getRed() != 0) 
         { 
            return 1; 
         } 
         else return 0; 
      } 
      return -1; 
   } 
    
   @Override 
   public void rotate (double newAngle)
   { 
      angle = newAngle; 
      if (angle < 0) 
         angle = angle % 360 + 360; 
      else  
         angle = angle % 360; 
   } 
   @Override 
   public void setColor( Color c)
   { 
      color = c; 
   } 
   @Override 
   public Color nextColor()
   { 
      if(color == Color.RED) return Color.BLUE; 
      else if( color == Color.BLUE) return Color.GREEN; 
      else if( color == Color.GREEN) return Color.MAGENTA; 
      else if( color == Color.MAGENTA) return Color.CYAN; 
      else if( color == Color.CYAN) return Color.YELLOW; 
      else  return Color.RED; 
   } 
    
} 
