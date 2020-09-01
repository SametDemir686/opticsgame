import java.util.*;
import java.awt.*;

public class LightCombinerFM implements OpticStuff, Rotatable, Movable, Reflactable
{
   double angle;
   int row;
   int column;
   int x;
   int y;
   ArrayList<Light> colorList;
   final int radius = 20;
   Game game;
   int redMax;
   int greenMax;
   int blueMax;
   Light p_Light;
   
   public LightCombinerFM (int row, int column, double angle, Game game)
   {
      redMax = 0;
      blueMax = 0;
      greenMax = 0;
      this.game = game;
      this.row = row;
      this.column = column;
      x = row * 60;
      y = column * 60;
      this.angle = angle;
      colorList = new ArrayList<Light>();
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
   public void setX(int newLocX) {
      x = newLocX;
   }
   
   @Override
   public void setY( int newLocY) {
      y = newLocY;
   }
   
   @Override
   public double getAngle()
   {
      return angle;
   }
   
   
   public OpticStuff cloneOp()
   {
      return new LightCombinerFM(getRow(), getColumn(), angle, game);
   }
   
   public int getRow() 
   {
      return x/60;
   }
   
   public int getColumn()
   {
      return y/60;
   }
   
   public int getX()
   {
      return x;
   }
   
   public int getY()
   {
      return y;
   }
   
   public int getXCenter()
   {
      return getRow()*60 +30;
   }
   
   public int getYCenter()
   {
      return getColumn()*60 +30;
   }
   
   public String getImageName()
   {
      return "images/combiner.png";
   }
   
   public int touches( Light light)
   {
      if( Math.sqrt( (light.endy-getYCenter())*(light.endy-getYCenter()) +  (light.endx-getXCenter())*(light.endx-getXCenter())) < radius )
      {      
         return 1;  
      } 
      else return -1;
   }
   
   public void addLight(Light light)
   {
      colorList.add(light);
   }
   
   public void formatColorList()
   {
      redMax = 0;
      blueMax = 0;
      greenMax = 0;
      colorList = new ArrayList<Light>();
   }
      @Override 
      public Light reflect(Light light)
   { 
      for (int i = 0; i < colorList.size(); i++)
      { 
         redMax = Math.max(colorList.get(i).getColor().getRed(), redMax); 
         greenMax = Math.max(colorList.get(i).getColor().getGreen(), greenMax); 
         blueMax = Math.max(colorList.get(i).getColor().getBlue(), blueMax); 
      } 
      if( colorList.size() > 2 ) { 
         p_Light = findThePreviousLight(); 
         removeThePreviousLight();
      } 
      return new Light( getXCenter()+(radius+5)*Math.cos(Math.toRadians(angle)), getYCenter()-(radius+5)*Math.sin(Math.toRadians(angle)), angle,  new Color(redMax,greenMax,blueMax), game); 
   } 
   public Light findThePreviousLight() {
      for( int i =0; i<game.gamePanel.lightArrayList.size(); i++) { 
         if( Math.abs(game.gamePanel.lightArrayList.get(i).startX -  getXCenter()+(radius+5)*Math.cos(Math.toRadians(angle))) < 5 && Math.abs(game.gamePanel.lightArrayList.get(i).startY -  getYCenter()-(radius+5)*Math.sin(Math.toRadians(angle))) < 5 ){    
            return game.gamePanel.lightArrayList.get(i); 
         } 
      } 
      return null; 
   } 
   public void removeThePreviousLight() {
      for( int i =0; i<game.gamePanel.lightArrayList.size(); i++) { 
         if( game.gamePanel.lightArrayList.get(i) == p_Light ) 
         { 
            game.gamePanel.lightArrayList.remove(i); 
            break;
         } 
      }
      game.gamePanel.repaint();
   }
   
   
   
   
}