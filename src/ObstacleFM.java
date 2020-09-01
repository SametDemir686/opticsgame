
import java.awt.Color;

public class ObstacleFM implements OpticStuff, Movable
{
   int row;
   int column;
   int number;
   int side;
   int x;
   int y;
   FreeMode game; 
   Color color;
   GamePanel gamePanel;
   public ObstacleFM (int row, int column, int number, FreeMode game)
   {
      this.row = row;
      this.column = column;
      this.game = game;
      this.number = number;
      side = 0;
      x = row*60;
      y = column*60;
   }
   public ObstacleFM(int row, int column, int number, int side,FreeMode game)
   {
      this.row = row;
      this.column = column;
      this.game = game;
      this.side = side;
      this.number = number;
      x = row*60;
      y = column*60;
   }
   public OpticStuff cloneOp()
   {
      if (side == 0)
         return new ObstacleFM(getRow(), getColumn(), number, game);
      else
         return new ObstacleFM(getRow(), getColumn(), number, side, game);
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
   @Override
   public int touches( Light light)
   {
      if( number == 1 && side == 2)
      {
         if( getRow()*60 <= light.endx && (getRow()+1)*60 >= light.endx && getColumn()*60 <= light.endy && (getColumn()+1)*60 >= light.endy && light.endy >= light.endx + 60*(-getRow()+getColumn()))
         {      
            return 0;  
         } 
         else return -1;
      }
      if( number == 1 && side == 1)
      {
         if( getRow()*60 <= light.endx && (getRow()+1)*60 >= light.endx && getColumn()*60 <= light.endy && (getColumn()+1)*60 >= light.endy && light.endx >= light.endy + 60*(getRow()-getColumn()))
         {      
            return 0;  
         } 
         else return -1;
      }
      if( number == 3)
      {
         if( Math.sqrt( (light.endy-getYCenter())*(light.endy-getYCenter()) +  (light.endx-getXCenter())*(light.endx-getXCenter()))< 20 )
         {      
            return 0;  
         } 
         else return -1;
      }
      else 
      {
         if( getRow()*60 <= light.endx && (getRow()+1)*60 >= light.endx && getColumn()*60 <= light.endy && (getColumn()+1)*60 >= light.endy)
         {      
            
            return 0;  
         } 
         else return -1;
      }
      
   }
   @Override
   //Setter for the x location of obstacles, for the sake of movability
   public void setX( int newLocX )
   {
      x = newLocX;
   }
   @Override
    //Setter for the y location of obstacles, for the sake of movability
   public void setY( int newLocY )
   {
      y = newLocY;  
   }
      //Accessor for the image of it
   public String getImageName()
   {
      if( number == 1) return "images/obstacle1-"+side+".png";
      else
         return "images/obstacle"+number+".png";
   }
   
}
