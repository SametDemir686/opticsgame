import java.awt.Point;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.EventQueue;             
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class Game {
   
       
   Level level;
   GamePanel gamePanel;
   LevelPanel levelPanel;
   ArrayList<OpticStuff> list;
   ArrayList<OpticStuff> firstList;
   
   Image[][] array;
   
   OpticStuff newStuff;
   
   boolean freeMode = false;
   boolean movable = false;
   boolean rotating = false;
   int firstRow;
   int firstColumn;
   double thetaStart = 0;
   double thetaEnd = 0;
   double firstAngle = 0;
   int x ;
   int y ;
   int levelNumber;
   OpticsGame opticsGame;
   int selection = -2;
   
   public Game( OpticsGame opticsGame, int levelNumber )
   {
      this.levelNumber = levelNumber;
      this.opticsGame = opticsGame;
      newStuff = null;
      this.level = new Level(false,false);
      levelPanel = new LevelPanel(this);
      gamePanel = new GamePanel(this);
      array = new Image[23][12];
      list = new ArrayList<OpticStuff>();
      firstList = new ArrayList<OpticStuff>();
      
      setGamePanelVisible(true);
      levelPanel.add(gamePanel);
      levelPanel.setVisible(true);
      
        
      if( this instanceof FreeMode)
      {
         freeMode = true;
         gamePanel.addMouseListener(new MyMouseListenerFM());
      }
      
      else
         gamePanel.addMouseListener(new MyMouseListener());
      gamePanel.addMouseMotionListener( new MouseMotionAdapter()
                                          {
         @Override
         public void mouseDragged( MouseEvent e)
         {
            if (SwingUtilities.isLeftMouseButton(e))
            {
               if( movable)
               {
                  ((Movable)array[firstRow][firstColumn].getStuff()).setX(e.getX() - 30);
                  ((Movable)array[firstRow][firstColumn].getStuff()).setY(e.getY() - 30);
                  x = e.getX();
                  y = e.getY();
                  
               }
            }
            else if (SwingUtilities.isRightMouseButton(e))
            {
               ArrayList<OpticStuff> list1 = list;
               if(rotating)
               {
                  double dx = e.getX() - (firstRow*60+30);
                  double dy = e.getY() - (firstColumn*60+30);
                  double theta = Math.atan2(dy, dx);
                  ((Rotatable)array[firstRow][firstColumn].getStuff()).rotate(Math.toDegrees(firstAngle + thetaStart - theta));
                  
                  
               }
                
               lightDeterminer();
               
               gamePanel.repaint();
            }
            
            gamePanel.repaint();
            
         }
      });
      
   }
   public boolean add(OpticStuff stuff) throws IOException
   {
      boolean result;
      if (array[stuff.getRow()][stuff.getColumn()] != null)
         result = false;
      else
      {
         array[stuff.getRow()][stuff.getColumn()] = new Image(stuff);
         
         OpticStuff firstStuff = stuff.cloneOp();
         list.add(stuff);
         firstList.add(firstStuff);
         gamePanel.repaint();
         
         result = true;
      }
      lightDeterminer();
      
      return result;
   }
   
   public void setGamePanelVisible(boolean visibility)
   {
      gamePanel.setVisible(visibility);
   }
   public boolean level_isCompleted()
   {
      if (gamePanel.allTargetsAreHit())
      {
         level.setCompleted(true);
         return true;
      }
      return false;
   }
   
   public void lightDeterminer()
   {
      gamePanel.formatLightArrayList();
      boolean isReflected;
      for (OpticStuff stuff : list)
      { 
         if (stuff instanceof LightSource || stuff instanceof LightSourceFM)
         {
            Light light;
            if (stuff instanceof LightSource)
               light = ( (LightSource)stuff).getLight();
            else
               light = ( (LightSourceFM)stuff).getLight();
            do
            {
               isReflected = false;
                
               if( light.getEndX() >= gamePanel.getWidth()-1 || light.getEndX() <= 121 ||
                  light.getEndY() >= gamePanel.getHeight()-1 || light.getEndY() <= 1)
               {
                  isReflected = false;
               }
               else if ( !(array[(int) (light.endX/gamePanel.SquareSide)][(int) (light.endY/gamePanel.SquareSide)].getStuff() instanceof Reflactable) )
               {
                  isReflected = false;
               }
                 
               else
               {
                  OpticStuff opticStuff = array[(int) (light.endX/gamePanel.SquareSide)][(int) (light.endY/gamePanel.SquareSide)].getStuff();
                  if( opticStuff.touches(light) == 0 )
                  {
                     isReflected = false;
                  }
                  else if( opticStuff.touches(light) == 1 )
                  { 
                     if (opticStuff instanceof LightCombiner)
                        ((LightCombiner)opticStuff).addLight(light);
                     else if (opticStuff instanceof LightCombinerFM)
                        ((LightCombinerFM)opticStuff).addLight(light);
                     isReflected = true;
                  }
               }
               gamePanel.addLight( light );
               
               if (isReflected)
               {
                  OpticStuff opticStuff = array[(int) (light.endX/gamePanel.SquareSide)][(int) (light.endY/gamePanel.SquareSide)].getStuff();
                  Light f_light = ( (Reflactable)opticStuff).reflect(light);
                  while (Math.sqrt( ( f_light.endY-f_light.startY) * ( f_light.endY-f_light.startY)+ ( f_light.endX-f_light.startX) * ( f_light.endX-f_light.startX ) ) < 1.75)
                  {
                     f_light = new Light( f_light.startX + f_light.vector.unitVector().x, f_light.startY - f_light.vector.unitVector().y , f_light.getAngle(), f_light.getColor(), f_light.game);
                  } 
                  
                  light = f_light;
                  
               }
            }while(isReflected);
            
         }
         
      }
      
      
   }
   
   
   
   
   
   class MyMouseListenerFM extends MouseAdapter
   {
      
      @Override
      public void mousePressed( MouseEvent e)
      { 
         firstRow = e.getX()/60;
         firstColumn = e.getY()/60;
         if (SwingUtilities.isLeftMouseButton(e))
         {
            if( array[e.getX()/60][e.getY()/60] != null)
            {
               if (firstRow == 0)
               {
                  array[1][firstColumn] = array[firstRow][firstColumn].cloneOp();
               }
               
               movable = true;
            }
            else 
               movable = false;
         }
         else if (SwingUtilities.isRightMouseButton(e))
         {
            Point p = e.getPoint();
            if (  array[e.getX()/60][e.getY()/60] != null && array[e.getX()/60][e.getY()/60].getStuff() instanceof Rotatable)
            {
               firstAngle = Math.toRadians(((Rotatable)array[e.getX()/60][e.getY()/60].getStuff()).getAngle());
               double dx = p.x - (firstRow*60 + 30);
               double dy = p.y - (firstColumn*60 + 30);
               thetaStart = Math.atan2(dy, dx);
               rotating = true;
            }
            else
               rotating = false;
         }
      }
      @Override
      public void mouseClicked( MouseEvent e)
      {
         if (SwingUtilities.isRightMouseButton(e))
         {
            
            if( array[e.getX()/60][e.getY()/60] != null && e.getX()/60 == 0)
            { 
               if( array[e.getX()/60][e.getY()/60].getStuff() instanceof Changeable )
               {
                  (( Changeable) array[e.getX()/60][e.getY()/60].getStuff()).setColor(((Changeable)array[e.getX()/60][e.getY()/60].getStuff()).nextColor());
                  
               }
            }
            
         }
         
      }
       
      @Override
      public void mouseReleased( MouseEvent e)
      {
         if (SwingUtilities.isLeftMouseButton(e))
         {
            if (movable)
            {
               if (firstRow != 0)
               {
                  if ( e.getX() < gamePanel.getWidth()         &&
                      e.getX() > 120                          &&
                      e.getY() < gamePanel.getHeight()        &&
                      e.getY() > 0 )
                  {
                     if ( array[e.getX()/60][e.getY()/60] != null )
                     {
                        (  (Movable) array[firstRow][firstColumn].getStuff()).setX( firstRow*60 ); // Eski Yere G r
                        (  (Movable) array[firstRow][firstColumn].getStuff()).setY( firstColumn*60 );
                     }
                     
                     else
                     {
                        
                        (  (Movable) array[firstRow][firstColumn].getStuff()).setX( e.getX()/60*60);
                        (  (Movable) array[firstRow][firstColumn].getStuff()).setY( e.getY()/60*60);
                        
                        array[e.getX()/60][e.getY()/60] = array[firstRow][firstColumn];
                        array[firstRow][firstColumn] = null;
                        
                     }
                  }
                  
                  
                  else
                  {
                     for ( int i = 0; i < list.size(); i++)
                     {
                        if ( array[firstRow][firstColumn] == list.get(i) )
                        {
                           list.remove(i);
                        }
                     }
                     array[firstRow][firstColumn] = null;
                  }
               }
               
               
               else
               {
                  if ( e.getX() < gamePanel.getWidth()         &&
                      e.getX() > 120                          &&
                      e.getY() < gamePanel.getHeight()        &&
                      e.getY() > 0 )
                  {
                     if ( array[e.getX()/60][e.getY()/60] != null )
                     {
                        array[1][firstColumn] = null;
                        ( (Movable) array[0][firstColumn].getStuff()).setX( firstRow*60);
                        ( (Movable)array[0][firstColumn].getStuff()).setY( firstColumn*60);
                     }
                     
                     else
                     {
                        ( (Movable) array[1][firstColumn].getStuff()).setX( e.getX()/60*60);
                        ( (Movable) array[1][firstColumn].getStuff()).setY( e.getY()/60*60);
                        
                        ( (Movable) array[0][firstColumn].getStuff()).setX( firstRow*60);
                        ( (Movable) array[0][firstColumn].getStuff()).setY( firstColumn*60);
                        
                        array[1][firstColumn] = null;
                        
                        newStuff = array[firstRow][firstColumn].getStuff().cloneOp();
                        ((Movable)newStuff).setX( e.getX()/60*60 );
                        ((Movable)newStuff).setY( e.getY()/60*60 );
                        try{
                           array[e.getX()/60][e.getY()/60] = new Image(newStuff);
                        }
                        catch (IOException asdfg)
                        {}
                        list.add( newStuff );
                     }
                     
                     
                  }
                  
                  else
                  {
                     array[1][firstColumn] = null;
                     
                     ( (Movable) array[0][firstColumn].getStuff()).setX( firstRow*60);
                     ( (Movable) array[0][firstColumn].getStuff()).setY( firstColumn*60);
                  }
                  
               }
               
            }
         }
         else if (SwingUtilities.isRightMouseButton(e))
         {
            if (rotating)
            {
               double dx = e.getX() - (firstRow*60+30);
               double dy = e.getY() - (firstColumn*60+30);
               double theta = Math.atan2(dy, dx);
               ((Rotatable)array[firstRow][firstColumn].getStuff()).rotate(Math.toDegrees(firstAngle + thetaStart - theta));
            }
         }
         lightDeterminer();
         
         gamePanel.repaint();
         
      }
   }
   
   
   
   // if the game is not freeMode
   
   class MyMouseListener extends MouseAdapter
   {
      
      @Override
      public void mousePressed( MouseEvent e)
      {
         firstRow = e.getX()/60;
         firstColumn = e.getY()/60;
         if (SwingUtilities.isLeftMouseButton(e))
         {  
            if( array[e.getX()/60][e.getY()/60] != null && array[e.getX()/60][e.getY()/60].getStuff() instanceof Movable)
            {
               movable = true;
            }
            else 
               movable = false;
         }
         else if (SwingUtilities.isRightMouseButton(e))
         {
            Point p = e.getPoint();
            if (  array[e.getX()/60][e.getY()/60] != null && array[e.getX()/60][e.getY()/60].getStuff() instanceof Rotatable)
            {
               firstAngle = Math.toRadians(((Rotatable)array[e.getX()/60][e.getY()/60].getStuff()).getAngle());
               double dx = p.x - (firstRow*60 + 30);
               double dy = p.y - (firstColumn*60 + 30);
               thetaStart = Math.atan2(dy, dx);
               rotating = true;
            }
            else
               rotating = false;
         }
      }
      @Override
      public void mouseDragged( MouseEvent e)
      {
         if (SwingUtilities.isLeftMouseButton(e))
         {
            if( movable)
            {
               ((Movable)array[firstRow][firstColumn].getStuff()).setX(e.getX());
               ((Movable)array[firstRow][firstColumn].getStuff()).setY(e.getY());
               x = e.getX();
               y = e.getY();
               
            }
         }
         
         else if (SwingUtilities.isRightMouseButton(e))
         {
            if(rotating)
            {
               double dx = e.getX() - (firstRow*60+30);
               double dy = e.getY() - (firstColumn*60+30);
               double theta = Math.atan2(dy, dx);
               ((Rotatable)array[firstRow][firstColumn].getStuff()).rotate(Math.toDegrees(firstAngle + thetaStart - theta));
               
            }
         }
         
         gamePanel.repaint();
         
      }
      @Override
      public void mouseReleased( MouseEvent e)
      {
         if (SwingUtilities.isLeftMouseButton(e))
         {
            if (movable)
            {
               if(
                  e.getX()/60 == 1                        ||
                  e.getX() > gamePanel.getWidth()     ||
                  e.getX() < 0             ||
                  e.getY() > gamePanel.getHeight()        ||
                  e.getY() < 0       ||
                  array[e.getX()/60][e.getY()/60] != null 
                     
                 )
               {
                  (  (Movable) array[firstRow][firstColumn].getStuff()).setX( firstRow*60 );
                  (  (Movable) array[firstRow][firstColumn].getStuff()).setY( firstColumn*60 );
                  
               }
               else if(array[e.getX()/60][e.getY()/60] == null && e.getX()/60 != 1)
               {
                  (  (Movable) array[firstRow][firstColumn].getStuff()).setX( e.getX()/60*60);
                  (  (Movable)array[firstRow][firstColumn].getStuff()).setY( e.getY()/60*60);
                  
                  array[e.getX()/60][e.getY()/60] = array[firstRow][firstColumn];
                  array[firstRow][firstColumn] = null; 
               }
               else if(array[e.getX()/60][e.getY()/60] == array[firstRow][firstColumn])
               {
                  (  (Movable) array[firstRow][firstColumn].getStuff()).setX( e.getX()/60*60);
                  (  (Movable)array[firstRow][firstColumn].getStuff()).setY( e.getY()/60*60);
                  
               }
               
               
            }
         }
         else if (SwingUtilities.isRightMouseButton(e))
         { 
            if (rotating)
            {
               double dx = e.getX() - (firstRow*60+30);
               double dy = e.getY() - (firstColumn*60+30);
               double theta = Math.atan2(dy, dx);
               ((Rotatable)array[firstRow][firstColumn].getStuff()).rotate(Math.toDegrees(firstAngle + thetaStart - theta));
            }
         }
         lightDeterminer();
         
         if( !freeMode)
         {
            if (gamePanel.allTargetsAreHit())
            {
               JFrame frame = new JFrame();
               
               if (levelNumber < 8)
               {
                  Object[] options = {"Back", "Next Level"};
                  int n = JOptionPane.showOptionDialog(frame,
                                                       "LEVEL COMPLETED",
                                                       "Congrats!",
                                                       JOptionPane.YES_NO_OPTION,
                                                       JOptionPane.QUESTION_MESSAGE,
                                                       null,
                                                       options,
                                                       options[1]);
                  
                  selection = n;
                  if ( levelNumber == opticsGame.noOfCompletedLevels + 1)
                  {
                     opticsGame.noOfCompletedLevels++;
                  }
                  // saving the game
                  try
                  {
                     opticsGame.saveGame();
                  }
                  catch ( IOException e1)
                  {
                     e1.printStackTrace();
                  }
                  
                  level.setCompleted(true);
                  opticsGame.gameArray[levelNumber].level.setLocked(false);
                  
                  if (selection == JOptionPane.YES_OPTION) // BACK
                  {
                     levelPanel.setVisible(false); 
                     opticsGame.panel2.setVisible(true);
                  }
                  
                  else if (selection == JOptionPane.NO_OPTION) // Next Level
                  { 
                     if(levelNumber==8)
                        levelPanel.setVisible(false);
                     else
                     {                        
                        opticsGame.gameArray[levelNumber].gamePanel.setVisible(true);
                        opticsGame.gameArray[levelNumber].levelPanel.setVisible(true);
                        levelPanel.setVisible(false); 
                     }
                  }
               }
               else
               {
                  // showing the message when the 8th level is completed
                  Object[] options = {"Back to Level Menu"};
                  int n = JOptionPane.showOptionDialog(frame,
                                                       "The game has been successfully completed!?",
                                                       "Congrats!",
                                                       JOptionPane.OK_OPTION,
                                                       JOptionPane.QUESTION_MESSAGE,
                                                       null,
                                                       options,
                                                       options[0]);
                  
                  if ( levelNumber == opticsGame.noOfCompletedLevels + 1)
                  {
                     opticsGame.noOfCompletedLevels++;
                  }
                  try
                  {
                     opticsGame.saveGame();
                  }
                  catch ( IOException e1)
                  {
                     e1.printStackTrace();
                  }
                  level.setCompleted(true);
                  
                  if (n == JOptionPane.OK_OPTION)
                  { 
                     levelPanel.setVisible(false);
                     opticsGame.panel2.setVisible(true);
                  }
                  
               }
               
               
            }
         }
         
         gamePanel.repaint();
         
      }
   }
}
