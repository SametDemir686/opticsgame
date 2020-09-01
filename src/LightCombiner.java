import java.util.*; 
import java.awt.*; 

public class LightCombiner implements OpticStuff, Rotatable, Reflactable
{ 
   double angle; 
   int row; 
   int column; 
   int x; 
   int y; 
   ArrayList<Light> colorList; //that is used to combine lights 
   final int radius = 20; 
   Game game; 
   int redMax; 
   int greenMax; 
   int blueMax; 
   Light p_Light;
    
   //Constructor taking its parameters based on row and column 
   public LightCombiner (int row, int column, double angle, Game game) 
   { 
      redMax = 0; 
      greenMax = 0; 
      blueMax = 0; 
      this.game = game; 
      this.row = row; 
      this.column = column; 
      this.angle = angle; 
      colorList = new ArrayList<Light>(); 
   } 
    
   @Override   
   //Simply rotates the mirror with the given angle, counter-clockwise 
   public void rotate (double newAngle) 
   { 
      angle = newAngle; 
      if (angle < 0) 
         angle = angle % 360 + 360; 
      else  
         angle = angle % 360; 
   } 
    
   @Override 
   //Accesor for the angle of the light combiner 
   public double getAngle() 
   { 
      return angle; 
   } 
    
   @Override 
   //This method behaves like a copy constructor, simply makes a clone of it 
   public OpticStuff cloneOp() 
   { 
      return new LightCombiner(getRow(), getColumn(), angle, game); 
   } 
    @Override 
   //Accessor for the row of light combiner 
   public int getRow()  
   { 
      return row; 
   } 
    @Override 
   //Accessor for the column of light combiner 
   public int getColumn() 
   { 
      return column; 
   } 
    @Override 
   //Accessor for the x-location of light combiner 
   public int getX() 
   { 
      return row*60; 
   } 
    @Override 
   //Accessor for the y-location of light combiner 
   public int getY() 
   { 
      return column*60; 
   } 
  
   //Accessor for the x-location of center of light combiner 
   public int getXCenter() 
   { 
      return row*60 +30; 
   } 
     
   //Accessor for the y-location of center of light combiner 
   public int getYCenter() 
   { 
      return column*60 +30; 
   } 
    @Override 
   //Accessor for the image of it 
   public String getImageName() 
   { 
      return "images/combiner.png"; 
   } 
     
    @Override 
   // This method determines whether the light touches to the light combiner like a contains method 
   // if light touches to the light combiner , returns 1 and combines it all the other touched lights 
   // if light does not touch at all to the light combiner, returns -1 
   public int touches( Light light) 
   { 
      if( Math.sqrt( (light.endy-getYCenter())*(light.endy-getYCenter()) +  (light.endx-getXCenter())*(light.endx-getXCenter())) < radius ) 
      {       
         return 1;   
      }  
      else return -1; 
   } 
   //Adding the given light to the color list  
   public void addLight(Light light) 
   { 
      colorList.add(light ); 
   } 
   //Simply resets the color list 
   public void formatColorList() 
   { 
      redMax = 0; 
      greenMax = 0; 
      blueMax = 0; 
      colorList = new ArrayList<Light>(); 
   } 
  @Override 
   //This method determines how a light combiner would reflect a light 
   public Light reflect(Light light) 
   { 
      if( colorList.size() > 2 ) { 
         p_Light = findThePreviousLight(); 
         removeThePreviousLight(); 
      } 
     //Determining the color of light that will return 
      for (int i = 0; i < colorList.size(); i++) 
      { 
         redMax = Math.max(colorList.get(i).getColor().getRed(), redMax); 
         greenMax = Math.max(colorList.get(i).getColor().getGreen(), greenMax); 
         blueMax = Math.max(colorList.get(i).getColor().getBlue(), blueMax); 
      } 
      return new Light( getXCenter()+(radius+5)*Math.cos(Math.toRadians(angle)), getYCenter()-(radius+5)*Math.sin(Math.toRadians(angle)), angle,  new Color(redMax,greenMax,blueMax), game); 
   } 
   //Finding the previous light to better combine 
   public Light findThePreviousLight() { 
      for( int i =0; i<game.gamePanel.lightArrayList.size(); i++) { 
         if( Math.abs(game.gamePanel.lightArrayList.get(i).startX -  getXCenter()+(radius+5)*Math.cos(Math.toRadians(angle))) < 1 && Math.abs(game.gamePanel.lightArrayList.get(i).startY -  getYCenter()-(radius+5)*Math.sin(Math.toRadians(angle))) < 1 ){    
            return game.gamePanel.lightArrayList.get(i); 
         } 
      } 
      return null; 
} 
   //Removing the previous light to better combine 
   public void removeThePreviousLight() { 
      for( int i =0; i<game.gamePanel.lightArrayList.size(); i++) { 
         if( game.gamePanel.lightArrayList.get(i) == p_Light ) 
         { 
            game.gamePanel.lightArrayList.remove(i); 
         } 
      } 
} 
   
    
    
}