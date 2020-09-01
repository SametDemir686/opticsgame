import java.awt.Color;

public class LightSourceFM implements OpticStuff, Movable, Rotatable, Changeable{
   
   Light light;
   int row;
   int column;
   int x;
   int y;
   double angle;
   Game game; 
   Color color;
   private final int WIDTH = 50;
   private final double TOLERANCE = WIDTH/2;
   private final int HEIGHT = 30;
   
   public LightSourceFM(int row, int column, double angle, Color color, Game game)
   {
      this.row= row;
      this.column = column;
      this.color = color;
      this.game =game;
      this.angle = angle;
      light = new Light ( row*60+30+HEIGHT/2.0*Math.cos(Math.toRadians(angle)), column*60+30-HEIGHT/2.0*Math.sin(Math.toRadians(angle)), angle, color ,game);
      x = row*60;
      y = column*60;
   }
   
   @Override
      public OpticStuff cloneOp()
   {
      return new LightSourceFM(getRow(), getColumn(), angle, color, game);
   }
   
   @Override
   public int touches(Light light)
   {
      
      double length = Math.sqrt((light.endy-getCenterY())*(light.endy-getCenterY())+(light.endx - getCenterX())*(light.endx - getCenterX() ));
      
      if ( Math.abs(getCenterY() + Math.tan( (int)(Math.toRadians(90-angle)*1000)/1000.0)*( light.endx - getCenterX())- light.endy) < TOLERANCE && length <= HEIGHT/2)
      {
         return 0;
      }
      else 
         return -1;
   }      
   
   @Override
   public String getImageName()
   {
      if( color == Color.RED )
         return  "images/red light source.png";
      else if( color == Color.BLUE )
         return "images/blue light source.png";
      else if ( color == Color.GREEN )
         return "images/green light source.png";
      else if ( color == Color.MAGENTA )
         return "images/magenta light source.png";
      else if ( color == Color.CYAN )
         return "images/cyan light source.png";
      else
         return "images/yellow light source.png";
   }
   
   @Override
   public int getRow()
   {
      return x/60;
   }

   public double getAngle() 
   {
      return angle;
   }
   
   @Override
   public int getColumn() {
      return y/60;
   }
   
   @Override
   public void setX( int newLocX )
   {
      x = newLocX;
   }
   @Override
   public void setY( int newLocY )
   {
      y = newLocY;  
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
   public int getX() {
      return x;
   }
   public int getY() {
      return y;
   }
   public int getCenterX() {
      return x + 30;
   }
   public int getCenterY() {
      return y + 30;
   }
   public Light getLight()
   {
      return new Light ( getRow()*60+30+HEIGHT/2*Math.cos(Math.toRadians(getAngle() )), getColumn()*60+30-HEIGHT/2*Math.sin(Math.toRadians(getAngle())), getAngle(), color ,game);
   }
   @Override
   public Color nextColor()
   {
      if(color == Color.RED) return Color.BLUE;
      else if( color == Color.BLUE) return Color.GREEN;
      else if( color == Color.GREEN) return Color.YELLOW;
      else if( color == Color.YELLOW) return Color.MAGENTA;
      else if( color == Color.MAGENTA) return Color.CYAN;
      else if( color == Color.CYAN) return Color.RED;
      else return Color.RED;
   }
   @Override
   public void setColor( Color c)
   {
      color = c;
   }
   
}