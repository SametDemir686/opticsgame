import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class GamePanel extends JPanel
{
   // variables 
   final int gameAreaX_Start = 120;
   final int gameAreaY_Start = 0; 
   final int gameAreaX_End   = 1380;
   final int gameAreaY_End   = 720;
   final int SquareSide      = 60;
   
   Graphics2D g;
   ArrayList<JLabel> siderbarList;
   ArrayList<JPanel> objectsList;
   ArrayList<Light> lightArrayList;
   JLabel tmpLabel;
   Game game;
   JTextField planarField;
   JTextField convexMirrorField;
   JTextField concaveMirrorField;
   JTextField convexLensField;
   JTextField filterField;
   JTextField lightSourceField;
   JTextField field1;
   JTextField field2;
   JTextField field3;
   JTextField field4;
   JTextField field5;
   JTextField field6;
   
   public GamePanel(Game game)
   {
      // variables 
      setLayout(null);
      planarField = new JTextField();
      convexMirrorField = new JTextField();
      concaveMirrorField = new JTextField();
      convexLensField = new JTextField();
      filterField = new JTextField();
      lightSourceField = new JTextField();
      field1 = new JTextField();
      field2 = new JTextField();
      field3 = new JTextField();
      field4 = new JTextField();
      field5 = new JTextField();
      field6 = new JTextField();
      
      planarField.setBounds(70,20,40,20);
      convexMirrorField.setBounds(70,80,40,20);
      concaveMirrorField.setBounds(70,140,40,20);
      convexLensField.setBounds(70,200,40,20);
      filterField.setBounds(70,260,40,20);
      lightSourceField.setBounds(70,320,40,20);
      field1.setBounds(70,380,40,20);
      field2.setBounds(70,440,40,20);
      field3.setBounds(70,500,40,20);
      field4.setBounds(70,560,40,20);
      field5.setBounds(70,620,40,20);
      field6.setBounds(70,680,40,20);
      
      add(planarField);
      add(convexMirrorField);
      add(concaveMirrorField);
      add(convexLensField);
      add(filterField);
      add(lightSourceField);
      add(field1);
      add(field2);
      add(field3);
      add(field4);
      add(field5);
      add(field6);
      
      // to add addActionListener  
      planarField.addActionListener( new AngleActionListener() );
      convexMirrorField.addActionListener( new AngleActionListener() );
      concaveMirrorField.addActionListener( new AngleActionListener() );
      convexLensField.addActionListener( new AngleActionListener() );
      filterField.addActionListener( new AngleActionListener() );
      lightSourceField.addActionListener( new AngleActionListener() );
      
      setBounds(30,30,1381,721);
      this.game = game;
      objectsList = new ArrayList<JPanel>();
      siderbarList = new ArrayList<JLabel>();
      lightArrayList = new ArrayList<Light>();
      repaint();
   }
   
   public void paintComponent( Graphics g)
   {
      super.paintComponent(g);
      
      g.drawLine(60,0,60,720);
      g.drawLine(0,0,0,720);
      
      for( int i = 0; i < 13; i++)
      {
         g.drawLine(0,60*i,60,60*i);
      }
      
      for( int i = 0; i < 13; i++)
      {
         g.drawLine(gameAreaX_Start, gameAreaY_Start + (i*SquareSide),gameAreaX_End,gameAreaY_Start + i*SquareSide);
      }
      for( int i = 0; i < 22; i++)
      {
         g.drawLine(gameAreaX_Start + (i*SquareSide),gameAreaY_Start,gameAreaX_Start + (i*SquareSide),gameAreaY_End);
      }
      
      allTargetsAreHit();
      
      
      
      for(int i = 0; i < game.array.length; i++)
      {
         for( int j = 0; j < game.array[i].length; j++)
         {
            if ( game.array[i][j] != null ) 
            {
               BufferedImage image = null;
               AffineTransform transform = new AffineTransform();
               if ( game.array[i][j].getStuff() instanceof Rotatable)
               {
                  try
                  {
                     image = ImageIO.read(new File (game.array[i][j].getStuff().getImageName()));
                  } catch (IOException e) {
                     e.printStackTrace();
                  }
                  
                  if ( game.array[i][j].getStuff() instanceof LightCombiner)
                     ((LightCombiner) game.array[i][j].getStuff()).formatColorList();
                  if ( game.array[i][j].getStuff() instanceof LightCombinerFM)
                     ((LightCombinerFM) game.array[i][j].getStuff()).formatColorList();
                  
                  transform.rotate(Math.toRadians(360-((Rotatable)game.array[i][j].getStuff()).getAngle()), image.getWidth()/2 ,image.getHeight()/2);
                  
                  AffineTransformOp op = new AffineTransformOp(transform,AffineTransformOp.TYPE_BILINEAR);
                  
                  image = op.filter(image, null);
                  g.drawImage(image, game.array[i][j].getStuff().getX(),game.array[i][j].getStuff().getY(), this);
               }
               
               else if(game.array[i][j].getStuff() instanceof LightSource)
               {
                  try
                  {
                     image = ImageIO.read(new File (game.array[i][j].getStuff().getImageName()));
                  } catch (IOException e) {
                     e.printStackTrace();
                  }
                  transform.rotate(Math.toRadians(360-((LightSource)game.array[i][j].getStuff()).getAngle()), image.getWidth()/2 ,image.getHeight()/2);
                  
                  AffineTransformOp op = new AffineTransformOp(transform,AffineTransformOp.TYPE_BILINEAR);
                  
                  image = op.filter(image, null);
                  g.drawImage(image, game.array[i][j].getStuff().getX(),game.array[i][j].getStuff().getY(), this);
               }
               
               else{
                  try
                  {
                     image = ImageIO.read(new File (game.array[i][j].getStuff().getImageName()));
                  } catch (IOException e) {
                     e.printStackTrace();
                  }
                  g.drawImage(image, game.array[i][j].getStuff().getX(),game.array[i][j].getStuff().getY(), this);
               }
            }
         }
      }
      
      
      for(int i = 0; i < lightArrayList.size(); i++)
      {
         g.setColor(lightArrayList.get(i).getColor());
         g.drawLine((int)lightArrayList.get(i).getStartX(), (int)lightArrayList.get(i).getStartY(), (int)lightArrayList.get(i).getEndX(), (int)lightArrayList.get(i).getEndY());
         
      }
      
      for(int i = 0; i < lightArrayList.size()-1; i++)
      {
         for(int j = i+1; j < lightArrayList.size(); j++)
         {
            Line2D firstLight = new Line2D.Float( (int)lightArrayList.get(i).startX,  (int)lightArrayList.get(i).startY,  (int)lightArrayList.get(i).endX,   (int)lightArrayList.get(i).endY);
            Line2D secondLight = new Line2D.Float( (int)lightArrayList.get(j).startX,  (int)lightArrayList.get(j).startY,  (int)lightArrayList.get(j).endX,   (int)lightArrayList.get(j).endY);
            
            double m1 = Math.tan(Math.toRadians(lightArrayList.get(i).getAngle()));
            double m2 = Math.tan(Math.toRadians(lightArrayList.get(j).getAngle()));
            
            double n1 = -lightArrayList.get(i).startY - m1 * lightArrayList.get(i).startX;
            double n2 = -lightArrayList.get(j).startY - m2 * lightArrayList.get(j).startX;
            
            if ( firstLight.intersectsLine(secondLight) && !(lightArrayList.get(i).getColor().equals(lightArrayList.get(j).getColor() ) ) )
            {
               int red = Math.max(lightArrayList.get(i).getColor().getRed(), lightArrayList.get(j).getColor().getRed());
               int green = Math.max(lightArrayList.get(i).getColor().getGreen(), lightArrayList.get(j).getColor().getGreen());
               int blue = Math.max(lightArrayList.get(i).getColor().getBlue(), lightArrayList.get(j).getColor().getBlue());
               
               if( ((int)lightArrayList.get(i).getAngle()) == (int)lightArrayList.get(j).getAngle() || ((int)Math.abs(lightArrayList.get(i).getAngle() - lightArrayList.get(j).getAngle()) ) == 180)
               {
                  Color color = new Color (red,green,blue);
                  lightArrayList.add( new Light( (int)lightArrayList.get(i).getStartX(), (int)lightArrayList.get(i).getStartY(), lightArrayList.get(i).getAngle(), color, game) );
                  lightArrayList.add( new Light( (int)lightArrayList.get(i).getEndX(), (int)lightArrayList.get(i).getEndY(), lightArrayList.get(i).getAngle() - 180, color, game) );
                  
                  lightArrayList.remove( j );
                  lightArrayList.remove( i );
                  repaint();
                  
               }
               
               else
               {
                  double xCoordinate = (n2-n1)/(m1-m2);
                  
                  double yCoordinate = -(m1 * xCoordinate + n1);
                  
                  g.setColor(new Color(red,green,blue));
                  g.drawLine((int)xCoordinate, (int)yCoordinate, (int)xCoordinate, (int)yCoordinate);
               }
               
            }
         }
         
      }
   }
   
   public void formatLightArrayList()
   {
      lightArrayList = new ArrayList<Light>();
   }
   
   public void addLight(Light light) 
   {
      lightArrayList.add(light);
   }
   
   public boolean allTargetsAreHit()
   {
      boolean result = true;
      for ( OpticStuff stuff : game.list)
      {
         if (stuff instanceof Target )
         {
            if ( !((Target)stuff).isHit(lightArrayList))
            {
               result = false;
            }
         }
         
         else if (stuff instanceof TargetFM )
         {
            if ( !((TargetFM)stuff).isHit(lightArrayList))
            {
               result = false;
            }
         }
      }
    
      return result;
   }
   
   class AngleActionListener implements ActionListener
   {
      @Override
      public void actionPerformed (ActionEvent e)
      {
         try
         {
            double newAngle = Double.parseDouble( ( (JTextField) e.getSource()).getText() );
            ((Rotatable)game.array[( (JTextField)e.getSource()).getX()/60-1][( (JTextField)e.getSource()).getY()/60].getStuff()).rotate(newAngle);
            repaint();
         }
         catch (NumberFormatException e1)
         {
            JFrame frame = new JFrame();
            Object[] options = {"OK"};
            int n = JOptionPane.showOptionDialog(frame,
                                                 "Please enter a double such as 5.56",
                                                 "Error",
                                                 JOptionPane.OK_OPTION,
                                                 JOptionPane.QUESTION_MESSAGE,
                                                 null,
                                                 options,
                                                 options[0]);
         }
         
         catch (NullPointerException e1)
         {
            JFrame frame = new JFrame();
            Object[] options = {"OK"};
            int n = JOptionPane.showOptionDialog(frame,
                                                 "The place that you are trying to rotate is empty",
                                                 "Error",
                                                 JOptionPane.OK_OPTION,
                                                 JOptionPane.QUESTION_MESSAGE,
                                                 null,
                                                 options,
                                                 options[0]);
         }
      }
   }
}