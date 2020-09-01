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
import sun.audio.*;

public class OpticsGame
{
   static Panel0 panel0;
   static Panel1 panel1;
   static Panel2 panel2;
   
   Game [] gameArray;
   
   int noOfCompletedLevels;
   
   
   static PanelHowTo panelHowTo;
   static PanelConvexLens panelConvexLens;
   static PanelFilters panelFilters;
   static PanelMirrors panelMirrors;
   static PanelObstacles panelObstacles;
   static PanelTargetAndLightSource panelTargetAndLightSource;
   
   JFrame f;
   Game game1, game2, game3, game4;
   Game game5, game6, game7, game8;
   Question question;
   
   JButton q = new JButton("Try a Question");
   
   FreeMode fMode;
   AudioStream sa,godfather ;
   
   public OpticsGame()
   {
      // variables 
      gameArray = new Game[8];
      panel0 = new Panel0();
      panel1 = new Panel1();
      panel2 = new Panel2();
      fMode = new FreeMode(this);
      
      
      try {
         sa = new AudioStream(  new FileInputStream( "a.wav" ) );
         // godfather = new AudioStream(  new FileInputStream( "godfather.wav" ) );
      }
      catch ( IOException dfgh)
      {}
      
      
      panelHowTo = new PanelHowTo();
      panelConvexLens = new PanelConvexLens();
      panelFilters = new PanelFilters();
      panelMirrors = new PanelMirrors();
      panelObstacles = new PanelObstacles();
      panelTargetAndLightSource = new PanelTargetAndLightSource();
      
      noOfCompletedLevels = 0;
      
      game1 = new Game(this,1);
      game2 = new Game(this,2);
      game3 = new Game(this,3);
      game4 = new Game(this,4);
      game5 = new Game(this,5);
      game6 = new Game(this,6);
      game7 = new Game(this,7);
      game8 = new Game(this,8);
      
      
      gameArray[0] = game1;
      gameArray[1] = game2;
      gameArray[2] = game3;
      gameArray[3] = game4;
      gameArray[4] = game5;
      gameArray[5] = game6;
      gameArray[6] = game7;
      gameArray[7] = game8;
      
      
      // Level 1      
      try
      {   
         // to create level 1 stuff
         game1.add( new PlanarMirror(0,0,0));
         game1.add( new PlanarMirror(0,1,0));
         
         game1.add( new Target( 5,3, Color.RED));
         game1.add( new LightSource(5,8,0, Color.BLUE, game1)) ; // OpticsGame
         game1.add( new LightSource(17,8,180,Color.RED, game1));
         game1.add( new Target( 17,3, Color.BLUE));
         
         // to add all obstacle we need 
         game1.add( new Obstacle(11,0,2,game1));
         game1.add( new Obstacle(11,1,2,game1));
         game1.add( new Obstacle(11,2,2,game1));
         game1.add( new Obstacle(11,3,2,game1));
         game1.add( new Obstacle(11,4,2,game1));
         game1.add( new Obstacle(11,5,3,game1));
         game1.add( new Obstacle(11,6,1,2,game1)); 
         game1.add( new Obstacle(11,7,2,game1));
         game1.add( new Obstacle(11,9,2,game1));
         game1.add( new Obstacle(11,11,2,game1));
         
      }
      
      catch (IOException e1) {
         
         e1.printStackTrace();
      }
      
      
      // Level 2      
      try
      {  
         // to create Level 2 stuff 
         // adds optical stuff to panel
         game2.add( new PlanarMirror(0,0,0));
         game2.add( new PlanarMirror(0,1,0));
         game2.add( new ConcaveMirror(0,2,0));
         
         // to add target
         game2.add( new Target( 21,11, Color.RED));
         
         game2.add( new Obstacle(8,6,3,game2));
         game2.add( new LightSource(3,1,315,Color.RED, game2));
         
         
         
         for(int i = 2; i <= 20; i++)
         {
            game2.add( new Obstacle(i,11,2,game2));
         }
         for(int i = 0; i <= 11; i++)
         {
            game2.add( new Obstacle(22,i,2,game2));
         }
         
         // to add obstacle in level 2 
         game2.add( new Obstacle(20,10,2,game2));
         game2.add( new Obstacle(19,10,2,game2)); 
         game2.add( new Obstacle(19,9,2,game2));
         game2.add( new Obstacle(18,9,2,game2));     
         game2.add( new Obstacle(21,8,2,game2));
         game2.add( new Obstacle(18,8,2,game2));
         game2.add( new Obstacle(17,8,2,game2));      
         game2.add( new Obstacle(21,7,2,game2));
         game2.add( new Obstacle(20,7,2,game2));     
         game2.add( new Obstacle(17,7,2,game2));
         game2.add( new Obstacle(16,7,2,game2));     
         game2.add( new Obstacle(20,6,2,game2));
         game2.add( new Obstacle(19,6,2,game2));    
         game2.add( new Obstacle(19,5,2,game2));
         game2.add( new Obstacle(18,5,2,game2));
         game2.add( new Obstacle(17,5,2,game2));
         game2.add( new Obstacle(16,5,2,game2));     
         game2.add( new Obstacle(21,9,1,1,game2)); 
         game2.add( new Obstacle(20,8,1,1,game2));
         game2.add( new Obstacle(19,7,1,1,game2));
         game2.add( new Obstacle(18,10,1,1,game2));
         game2.add( new Obstacle(17,9,1,1,game2)); 
         game2.add( new Obstacle(16,8,1,1,game2));   
         game2.add( new Obstacle(18,7,1,2,game2));
         game2.add( new Obstacle(19,8,1,2,game2));
         game2.add( new Obstacle(20,9,1,2,game2)); 
         game2.add( new Obstacle(21,6,1,2,game2));
         game2.add( new Obstacle(20,5,1,2,game2)); 
         
         
         
         
      }
      
      catch (IOException e1) {
         
         e1.printStackTrace();
      }
      
      
      // Level 3      
      try
      {  
         
         // to create Level 3 stuff 
         // adds optical stuff to panel
         game3.add( new LightSource(4,2,0, Color.RED, game3)) ; // OpticsGame
         
         game3.add( new PlanarMirror(0,0,270));
         game3.add( new PlanarMirror(0,1,270));
         game3.add( new PlanarMirror(0,2,270));
         game3.add( new PlanarMirror(0,3,270));
         
         for( int i = 9; i > 3; i--)
         {
            game3.add( new Obstacle(i,3,2,game3));
         }
         for(int i = 4; i <= 9; i++)
         {
            game3.add( new Obstacle( 4,i,2,game3));
         }
         for(int i = 5; i <= 9; i++)
         {
            game3.add( new Obstacle( i,9,2,game3));
         }
         for(int i = 8; i >= 5; i--)
         {
            game3.add( new Obstacle( 9,i,2,game3));
         }
         for(int i = 8; i >= 6; i--)
         {
            game3.add( new Obstacle( i,5,2,game3));
         }
         game3.add( new Obstacle( 6,6,2,game3));
         game3.add( new Obstacle( 6,7,2,game3));
         game3.add( new Obstacle( 7,7,2,game3));
         
         
         
         game3.add( new Target( 7,6, Color.RED));
         
         
      }
      
      catch (IOException e1) {
         
         e1.printStackTrace();
      }
      
      
      // Level 4
      try
      {  
         // to create Level 4 stuff 
         // adds optical stuff to panel
         game4.add( new Obstacle( 10,0,2,game4));
         game4.add( new Obstacle( 10,1,2,game4));
         game4.add( new Obstacle( 10,2,2,game4));
         game4.add( new Obstacle( 10,4,2,game4));
         game4.add( new Obstacle( 10,7,2,game4));
         game4.add( new Obstacle( 10,9,2,game4));
         game4.add( new Obstacle( 10,10,2,game4));
         game4.add( new Obstacle( 10,11,2,game4));
         
         
         game4.add( new LightSource(4,5,0, Color.CYAN, game4));
         game4.add( new LightSource(3,7,0,Color.MAGENTA, game4));       
         game4.add( new LightSource(2,9,0,Color.YELLOW, game4));
         
       //  game4.add( new PlanarMirror(15,0,270));
         
         game4.add( new Target(11,4, Color.RED));
         game4.add( new Target(16,9, Color.GREEN));
         game4.add( new Target(16,11, Color.RED));
         
         game4.add( new Obstacle( 10,5,1,1,game4));
         game4.add( new Obstacle( 10,6,1,2,game4));
         
         
         
         game4.add( new PlanarMirror(0,0,0));
         game4.add( new PlanarMirror(0,1,0));
         game4.add( new ConvexLens(0,2,0));
         game4.add( new ConvexMirror(0,3,0));
         game4.add( new Filter(0,4,0,Color.RED));
         game4.add( new Filter(0,5,0,Color.YELLOW));
      }
      
      catch (IOException e1) {
         
         e1.printStackTrace();
      }
      
      
      
      
      // Level 5
      try
      {  // to create Level 5 obtical stuff
         
         game5.add( new PlanarMirror(0,0,0));
         game5.add( new ConcaveMirror(0,1,0));
         game5.add( new ConvexMirror(0,2,0));
         
         
         // to add all obstac we need in level 5
         game5.add( new Obstacle( 4,4,2,game5));
         game5.add( new Obstacle( 6,3,2,game5));
         game5.add( new Obstacle( 7,3,2,game5));
         game5.add( new Obstacle( 9,4,2,game5));
         game5.add( new Obstacle( 9,5,2,game5));
         game5.add( new Obstacle( 10,5,2,game5));
         game5.add( new Obstacle( 5,5,2,game5));
         game5.add( new Obstacle( 6,6,2,game5));
         game5.add( new Obstacle( 8,6,2,game5));
         game5.add( new Obstacle( 9,6,2,game5));
         game5.add( new Obstacle( 10,6,2,game5));
         game5.add( new Obstacle( 8,7,2,game5));
         game5.add( new Obstacle( 9,7,2,game5));
         game5.add( new Obstacle( 10,7,2,game5));
         game5.add( new Obstacle( 11,8,2,game5));
         game5.add( new Obstacle( 12,9,2,game5));
         game5.add( new Obstacle( 13,8,2,game5));
         
         game5.add( new Obstacle( 8,3,3,game5));
         game5.add( new Obstacle( 7,4,3,game5));
         game5.add( new Obstacle( 8,5,3,game5));
         
         game5.add( new Obstacle( 5,3,1,1,game5));
         game5.add( new Obstacle( 4,5,1,1,game5));
         game5.add( new Obstacle( 5,6,1,1,game5)); 
         game5.add( new Obstacle( 12,7,1,1,game5));
         
         game5.add( new Obstacle( 5,4,1,2,game5));
         game5.add( new Obstacle( 6,5,1,2,game5));
         game5.add( new Obstacle( 11,7,1,2,game5));
         game5.add( new Obstacle( 10,4,1,2,game5)); 
         
         game5.add( new LightCombiner( 7,5,50,game5));
         
         game5.add( new Target(12,8,Color.MAGENTA));  
         
         // to add light sources 
         game5.add( new LightSource( 2,2,0, Color.RED, game5)) ; // OpticsGame
         game5.add( new LightSource(2,5,315,Color.BLUE, game5)); 
         
      }
      
      catch (IOException e1) {
         
         e1.printStackTrace();
      }
      
      
      // Level 6
      try
      {  
         // to create Level6 optical stuff 
         // add obstacle in level 6
         game6.add( new Obstacle( 6,1,2,game6));
         game6.add( new Obstacle( 6,9,2,game6));
         game6.add( new Obstacle( 8,2,2,game6));
         game6.add( new Obstacle( 8,3,2,game6));
         game6.add( new Obstacle( 8,4,2,game6));
         game6.add( new Obstacle( 4,4,2,game6));
         game6.add( new Obstacle( 9,4,2,game6));
         game6.add( new Obstacle( 8,5,2,game6));
         game6.add( new Obstacle( 8,7,2,game6));
         game6.add( new Obstacle( 8,8,2,game6));
         game6.add( new Obstacle( 9,8,2,game6));
         game6.add( new Obstacle( 8,9,2,game6));
         
         // to add light source in level 6
         
         game6.add( new LightSource( 4,2,315, Color.RED, game6)) ; // OpticsGame
         game6.add( new LightSource(4,5,45,Color.MAGENTA, game6));           
         game6.add( new LightSource( 4,6,315, Color.CYAN, game6)) ; // OpticsGame
         game6.add( new LightSource(4,10,45,Color.BLUE, game6));
         
         game6.add( new Target(16,2,Color.GREEN));  
         game6.add( new Target(16,5,Color.MAGENTA));  
         game6.add( new Target(16,8,Color.RED));  
         
         game6.add( new Filter(0,0,0,Color.YELLOW));
         game6.add( new Filter(0,1,0,Color.BLUE));
         game6.add( new Filter(0,2,0,Color.RED));
         
         // to add Light Combiner which combines  the lights  
         game6.add( new LightCombiner( 8,1,50,game6));        
         game6.add( new LightCombiner( 8,6,50,game6));
         game6.add( new LightCombiner( 8,10,50,game6));
         
      }
      
      catch (IOException e1) {
         
         e1.printStackTrace();
      }
      
      
      // Level 7      
      try
      {  
         // to create Level 7
         
         // to add obstacles in level 
         game7.add( new Obstacle( 8,5,2,game7));
         game7.add( new Obstacle( 9,5,2,game7));
         game7.add( new Obstacle( 10,5,2,game7));
         game7.add( new Obstacle( 11,5,2,game7));
         game7.add( new Obstacle( 12,5,2,game7));
         game7.add( new Obstacle( 13,5,2,game7));
         game7.add( new Obstacle( 14,5,2,game7));
         game7.add( new Obstacle( 15,5,2,game7));
         
         game7.add( new Obstacle( 10,6,2,game7));
         game7.add( new Obstacle( 10,7,2,game7));
         game7.add( new Obstacle( 10,8,2,game7));
         game7.add( new Obstacle( 9,8,2,game7));
         game7.add( new Obstacle( 11,6,2,game7));
         game7.add( new Obstacle( 11,8,2,game7));
         game7.add( new Obstacle( 12,6,2,game7));
         game7.add( new Obstacle( 12,8,2,game7));
         game7.add( new Obstacle( 13,6,2,game7));
         game7.add( new Obstacle( 13,8,2,game7));
         game7.add( new Obstacle( 14,7,2,game7));
         game7.add( new Obstacle( 14,8,2,game7));
         game7.add( new Obstacle( 15,6,2,game7));
         
         game7.add( new LightSource(6,2,0, Color.CYAN, game7)) ; // OpticsGame
         game7.add( new LightSource(17,1,180,Color.MAGENTA, game7)); 
         
         game7.add( new Obstacle( 8,6,1,1,game7));
         game7.add( new Obstacle( 14,6,1,1,game7));
         game7.add( new Obstacle( 14,7,1,2,game7));
         
         // to add targets 
         game7.add( new Target( 9,6,Color.RED));
         game7.add( new Target( 13,6,Color.BLUE));
         
         // to add filters and concaveMirrors
         game7.add( new Filter(0,1,0,Color.RED));
         game7.add( new Filter(0,2,0,Color.BLUE));
         game7.add( new ConcaveMirror(0,3,0));
         game7.add( new ConcaveMirror(0,4,0));
         game7.add( new ConcaveMirror(0,5,0));
      }
      
      catch (IOException e1) {
         
         e1.printStackTrace();
      }
      
      
      // Level 8
      try
      {  
         // to create level 8 
         game8.add( new LightSource(13,6,90, Color.RED, game8)) ; // OpticsGame
         game8.add( new LightSource(18,0,270,Color.RED, game8)); 
         
         game8.add( new PlanarMirror(15,0,270));
      }
      
      catch (IOException e1) {
         
         e1.printStackTrace();
      }
      
      f = new MyJFrame();
      
   }
   
   public static void main (String[] args)
   {
      OpticsGame a = new OpticsGame();
      
   }
   
   
   class MyJFrame extends JFrame {
      public MyJFrame() {
         
         // to set resizable false
         setResizable(false);
         
         Container c = getContentPane();
         c.setLayout(null);
         c.add(panel0);
         panel1.setVisible(false);
         c.add(panel1);
         panel2.setVisible(false);
         c.add(panel2);
         
         //////////////////////////////HOW TO//////////////////////////////////////
         panelHowTo.setVisible(false);
         c.add(panelHowTo);
         panelConvexLens.setVisible(false);
         c.add(panelConvexLens);
         panelFilters.setVisible(false);
         c.add(panelFilters);
         panelMirrors.setVisible(false);
         c.add(panelMirrors);
         panelObstacles.setVisible(false);
         c.add(panelObstacles);
         panelTargetAndLightSource.setVisible(false);
         c.add(panelTargetAndLightSource);
         ////////////////////////////////////////////////////////
         
         game1.levelPanel.setVisible(false);
         c.add(game1.levelPanel);
         game2.levelPanel.setVisible(false);
         c.add(game2.levelPanel);
         game3.levelPanel.setVisible(false);
         c.add(game3.levelPanel);
         game4.levelPanel.setVisible(false);
         c.add(game4.levelPanel);
         game5.levelPanel.setVisible(false);
         c.add(game5.levelPanel);
         game6.levelPanel.setVisible(false);
         c.add(game6.levelPanel);
         game7.levelPanel.setVisible(false);
         c.add(game7.levelPanel);
         game8.levelPanel.setVisible(false);
         c.add(game8.levelPanel); 
         
         
         
         // to add title
         setTitle( "Optics Game");
         setBounds( 0, 0, 1440, 900);
         setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
         setVisible(true);
         
      }
   }
   
   
   public void saveGame() throws IOException
   {
      // code saves the game 
      FileOutputStream fileOut = new FileOutputStream("save.txt");
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject( new Integer(noOfCompletedLevels));
      out.close();
   }
   
   public void loadGame() throws IOException
   {
      // code loads the game 
      FileInputStream fileIn = new FileInputStream("save.txt"); // this codes take the inforations from txt file
      ObjectInputStream in = new ObjectInputStream(fileIn);
      try
      {
         noOfCompletedLevels = ((Integer) in.readObject());
      } catch (ClassNotFoundException e1)
      {
         e1.printStackTrace();
      }
      in.close();
      fileIn.close();
   }
   
   
   
   public class Panel0 extends JPanel{
      
      public Panel0(){
         setBounds(0, 0, 1440, 900);
         setLayout(null);
         
         
         // first panel when game is oppened 
         JLabel txt = new JLabel(" OPTICS GAME ");
         txt.setBounds(150,196,300,90);
         txt.setForeground(Color.GREEN);
         add(txt);
         
         Font newLabelFont =  new Font ( "OPTICS GAME ", Font.BOLD, 32);
         
         txt.setFont( newLabelFont );
         
         JButton btnNewButton_2 = new JButton("Play");
         btnNewButton_2.addActionListener( new ActionListener(){
            public void actionPerformed( ActionEvent e) {
               
               // to close panel0 panel
               panel0.setVisible(false);
               panel1.setVisible(true);
            }
         });
         add(btnNewButton_2);
         btnNewButton_2.setBounds(1000,370,300,75);
         
         
         // how to button 
         JButton btnNewButton_1 = new JButton("How to Play");
         btnNewButton_1.addActionListener( new ActionListener(){
            public void actionPerformed( ActionEvent e) {
               
               setVisible(false);
               // opens how to panel
               panelHowTo.setVisible(true);
            }
         });
         add(btnNewButton_1);
         btnNewButton_1.setBounds(1000,490,300,75);
         
         
         
         JButton btnNewButton = new JButton("Quit");
         btnNewButton.addActionListener( new ActionListener(){
            public void actionPerformed( ActionEvent e) {
               
               // to close system  / exit 
               f.dispose();
            }
         });
         add(btnNewButton);
         btnNewButton.setBounds(1000,610,300,75);
         
         // to ad picture for background 
         JLabel pic = new JLabel( new ImageIcon("images/panel0.png"));
         //setBackGround( java.awt.Color.RED);
         pic.setBounds(0,0,1440,900);
         pic.setVisible(true);
         add(pic);
         
         
      }
      
   }
   public class Panel1 extends JPanel 
   {
      public Panel1()
      {
         
         setBounds(0, 0, 1440, 900);
         setLayout(null);
         
         // panel 1 
         JButton a = new JButton("Back");
         add(a); 
         a.setBounds(25,717,120,60);
         a.addActionListener( new ActionListener(){
            public void actionPerformed( ActionEvent e) {
               setVisible(false);
               // opens first page 
               panel0.setVisible(true);
               
               
            }
         });
         // to set txt 
         JLabel txt = new JLabel(" OPTICS GAME ");
         txt.setBounds(150,196,300,90);
         txt.setForeground(Color.GREEN);
         add(txt);
         
         Font newLabelFont =  new Font ( "OPTICS GAME ", Font.BOLD, 32);
         
         txt.setFont( newLabelFont );
         
         JButton btnNewButton_2 = new JButton("New Game");
         btnNewButton_2.addActionListener( new ActionListener(){
            public void actionPerformed( ActionEvent e) {
               noOfCompletedLevels = 0;
               try
               { 
                  //  to save the game
                  saveGame();
               } catch (IOException e1)
               {
                  e1.printStackTrace();
               }
               panel1.setVisible(false);
               //to open panel 2 
               panel2.setVisible(true);
            }
         });
         add(btnNewButton_2);
         btnNewButton_2.setBounds(1000,370,300,75);
         
         
         
         JButton btnNewButton_1 = new JButton("Load Game");
         btnNewButton_1.addActionListener( new ActionListener()
                                             {
            public void actionPerformed( ActionEvent e) {
               
               try
               {
                  // to load game
                  loadGame();
               } catch (IOException e1)
               {
                  e1.printStackTrace();
               }
               
               panel1.setVisible(false);
               // to open panel 2 
               panel2.setVisible(true);
               
            }
         });
         
         
         
         add(btnNewButton_1);
         btnNewButton_1.setBounds(1000,490,300,75);
         
         
         
         // to create free button  
         JButton btnNewButton = new JButton("Free Mode");
         btnNewButton.addActionListener( new ActionListener(){
            public void actionPerformed( ActionEvent e) {
               setVisible(false);
               
               freeModePanel free = new freeModePanel();
               
               // to show instructors 
               
               f.add(free);
               free.setVisible(true);
               JFrame frame = new JFrame();
               Object[] options = {"OK"};
               int n = JOptionPane.showOptionDialog(frame,
                                                    "Moving an item: Left press on the item and drag the mouse \n" + 
                                                    "Rotating an item: Right press on the rotatable item and drag the mouse\n" +
                                                    "Changing the color of an item standing on left: Right click on the item that stands on left and whose color can change\n" +                    
                                                    "Removing an item: Drag the item to outside of the game panel \n" +
                                                    "Arranging the angle of an item which stands on left: Enter a number in the text field ",
                                                    "Free Mode Instructor",
                                                    JOptionPane.OK_OPTION,
                                                    JOptionPane.QUESTION_MESSAGE,
                                                    null,
                                                    options,
                                                    options[0]);
               
            }
         });
         add(btnNewButton);
         btnNewButton.setBounds(1000,610,300,75);
         
         
         // to pic 
         JLabel pic = new JLabel( new ImageIcon("images/panel0.png"));
         //setBackGround( java.awt.Color.RED);
         pic.setBounds(0,0,1440,900);
         pic.setVisible(true);
         add(pic);
         
         
      }
   }
   
   
   public class Panel2 extends JPanel 
   {
      // to show panel2 
      JButton a = new JButton("Turn Menu");
      JButton b = new JButton("1");
      JButton c = new JButton("2");
      JButton d = new JButton("3");
      JButton e = new JButton("4");
      JButton o = new JButton("5");
      JButton g = new JButton("6");
      JButton h = new JButton("7");
      JButton i = new JButton("8");
      JButton[] buttons = {b, c, d, e, o, g, h, i };
      public Panel2 () 
      {
         setLayout(null);
         
         setBounds(0, 0, 1440, 900);
         
         
         a.setBounds(25,600,120,60);
         b.setBounds(225,180,180,180);
         c.setBounds(495,180,180,180);
         d.setBounds(765,180,180,180);
         e.setBounds(1035,180,180,180);
         o.setBounds(225,450,180,180);
         g.setBounds(495,450,180,180);
         h.setBounds(765,450,180,180);
         i.setBounds(1035,450,180,180);
         
         for ( JButton button : buttons)
         {
            button.addActionListener( new MyActionListener());
            add(button);
         }
         
         //  back button 
         JButton x = new JButton("Back");
         add(x);
         x.setBounds(25,717,120,60);
         x.addActionListener( new ActionListener(){
            public void actionPerformed( ActionEvent e) {
               setVisible(false);
               panel1.setVisible(true);
            }
         });
         
         
         a.addActionListener( new ActionListener(){
            public void actionPerformed( ActionEvent e) {
               
               
               setVisible(false);
               panel2.setVisible(false);
               // to turn first panel 
               panel1.setVisible(true);
               
               
            }
         });
         
         
         
         JLabel pic = new JLabel( new ImageIcon("images/panel2.png"));
         //setBackGround( java.awt.Color.RED);
         pic.setBounds(0,0,1440,900);
         pic.setVisible(true);
         add(pic);
      }
      
      @Override
      public void paintComponent(Graphics g)
      {
         for (int i = 0; i < 8; i++)
         {
            
            // to sets button backgrounds according to level situation   
            if (i < noOfCompletedLevels)
            {
               buttons[i].setBackground(Color.YELLOW);
               buttons[i].setEnabled(true);
            }
            else if ( i == noOfCompletedLevels )
            {
               buttons[i].setBackground(Color.BLUE);
               buttons[i].setEnabled(true);
            }
            else
            {
               buttons[i].setBackground(Color.RED);
               buttons[i].setEnabled(false);
            }
            
            
         }
      }
      
   }
   
   
   public class PanelHowTo extends JPanel
   {
      // to create how to panel 
      public PanelHowTo()
      {
         setLayout(null);
         setBounds(0, 0, 1440, 900);
         JButton a = new JButton("Back");
         add(a);
         a.setBounds(25,717,120,60);
         a.addActionListener( new ActionListener(){
            public void actionPerformed( ActionEvent e) {
               setVisible(false);
               panel0.setVisible(true);
            }
         });
         
         JButton b = new JButton();
         b.setOpaque(true);
         b.setIcon(new ImageIcon("images/convex lens icon.png"));
         b.setBounds(225,180,180,180);
         add(b);
         b.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e)  {
               setVisible(false);
               panelConvexLens = new PanelConvexLens();
               f.add(panelConvexLens); 
               panelConvexLens.setVisible(true);               
            }});
         
         JButton c = new JButton();
         add(c);
         c.setOpaque(true);
         c.setIcon(new ImageIcon("images/filters icon.png"));
         c.setBounds(540,180,180,180);
         c.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e)  {
               setVisible(false);
               panelFilters = new PanelFilters();
               f.add(panelFilters); 
               panelFilters.setVisible(true);           
            }});
         
         JButton d = new JButton();
         add(d);
         d.setOpaque(true);
         d.setIcon(new ImageIcon("images/mirrors icon.png"));
         d.setBounds(855,180,180,180);
         d.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e)  {
               setVisible(false);
               panelMirrors = new PanelMirrors();
               f.add(panelMirrors); 
               panelMirrors.setVisible(true);     
            }});
         
         JButton e = new JButton();
         add(e);
         e.setOpaque(true);
         e.setIcon(new ImageIcon("images/obstacles icon.png"));
         e.setBounds(375,450,180,180);
         e.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e)  {
               setVisible(false);
               panelObstacles = new PanelObstacles();
               f.add(panelObstacles); 
               panelObstacles.setVisible(true);    
            }});
         
         JButton q = new JButton();
         add(q);
         q.setOpaque(true);
         q.setIcon(new ImageIcon("images/targetandlightsource icon.png"));
         q.setBounds(705,450,180,180);
         q.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e)  {
               setVisible(false);
               panelTargetAndLightSource = new PanelTargetAndLightSource();
               f.add(panelTargetAndLightSource); 
               panelTargetAndLightSource.setVisible(true); 
            }});
         
         JLabel pic = new JLabel( new ImageIcon("images/panel.png"));
         pic.setBounds(0,0,1440,900);
         pic.setVisible(true);
         add(pic);
      }
   }
   
   public class PanelConvexLens extends JPanel
   {
      // to create convex lens panel 
      public PanelConvexLens()
      {
         setLayout(null);
         setBounds(0, 0, 1440, 900);
         JButton a = new JButton("Back");
         add(a);
         a.setBounds(25,717,120,60);
         a.addActionListener( new ActionListener(){
            public void actionPerformed( ActionEvent e) {
               setVisible(false);
               panelHowTo.setVisible(true);
            }
         });
         
         JLabel pic = new JLabel( new ImageIcon("images/panelconvexlens.png"));
         pic.setBounds(0,0,1440,900);
         pic.setVisible(true);
         add(pic);
      }
   }
   
   public class PanelFilters extends JPanel
   {
      // to create panel filters 
      public PanelFilters()
      {
         setLayout(null);
         setBounds(0, 0, 1440, 900);
         JButton a = new JButton("Back");
         add(a);
         a.setBounds(25,717,120,60);
         a.addActionListener( new ActionListener(){
            public void actionPerformed( ActionEvent e) {
               setVisible(false);
               panelHowTo.setVisible(true);
            }
         });
         
         // to add picture 
         JLabel pic = new JLabel( new ImageIcon("images/panelfilters.png"));
         pic.setBounds(0,0,1440,900);
         pic.setVisible(true);
         add(pic);
      }
   }
   public class PanelMirrors extends JPanel
   {
      // to add panel mirrors 
      public PanelMirrors()
      {
         setLayout(null);
         setBounds(0, 0, 1440, 900);
         JButton a = new JButton("Back");
         add(a);
         a.setBounds(25,717,120,60);
         a.addActionListener( new ActionListener(){
            public void actionPerformed( ActionEvent e) {
               setVisible(false);
               panelHowTo.setVisible(true);
            }
         });
         // to add pictures of mirrors all together
         JLabel pic = new JLabel( new ImageIcon("images/panelmirrors.png"));
         pic.setBounds(0,0,1440,900);
         pic.setVisible(true);
         add(pic);
      }
   }
   public class PanelObstacles extends JPanel
   {
      // to create obstacle panel
      public PanelObstacles()
      {
         setLayout(null);
         setBounds(0, 0, 1440, 900);
         JButton a = new JButton("Back");
         add(a);
         a.setBounds(25,717,120,60);
         a.addActionListener( new ActionListener(){
            public void actionPerformed( ActionEvent e) {
               setVisible(false);
               // sets how to panel visibility true 
               panelHowTo.setVisible(true);
            }
         });
         
         // to add picture of all obstacle 
         JLabel pic = new JLabel( new ImageIcon("images/panelobstacles.png"));
         pic.setBounds(0,0,1440,900);
         pic.setVisible(true);
         add(pic);
      }
   }
   public class PanelTargetAndLightSource extends JPanel
   {
      // to add panel target 
      public PanelTargetAndLightSource()
      {
         setLayout(null);
         setBounds(0, 0, 1440, 900);
         JButton a = new JButton("Back");
         add(a);
         a.setBounds(25,717,120,60);
         a.addActionListener( new ActionListener(){
            public void actionPerformed( ActionEvent e) {
               setVisible(false);
               panelHowTo.setVisible(true);
            }
         });
         // to add picture
         JLabel pic = new JLabel( new ImageIcon("images/paneltargetandlightsource.png"));
         pic.setBounds(0,0,1440,900);
         pic.setVisible(true);
         add(pic);
      }
   }
   
   
   public class MyActionListener implements ActionListener
   {
      // to add action performs methods 
      public void actionPerformed( ActionEvent e)  {
         
         int levelNumber = Integer.parseInt(   ((JButton)e.getSource()).getText()   );
         Game particularGame = gameArray[levelNumber-1];
         
         panel2.setVisible(false);
         
         LevelPanel levelPanel00 = particularGame.levelPanel;
         f.add(levelPanel00);
         levelPanel00.setVisible(true);     
      }
   }
   
   public class freeModePanel extends JPanel 
   {
      
      // to create free Mode panel 
      public freeModePanel()
      {
         setLayout(null);
         setBounds(0, 0, 1440, 900);
         
         fMode.setGamePanelVisible(true);
         add(fMode.gamePanel);
         
         
         JLabel level = new JLabel(" FREE MODE ");
         level.setBounds(30,0,300,30);
         add(level); 
         
         // to add back button
         JButton ba = new JButton("Back");
         ba.setBounds(25,757,70,40);
         add(ba);
         ba.addActionListener( new ActionListener(){
            public void actionPerformed( ActionEvent e) {
               //   game1.setGamePanelVisible(false);
               setVisible(false);
               // to set panel1 visibility to true
               panel1.setVisible(true); 
            }
         });
         
         try
         {
            //TEST FREE MODE
            // to add optic stuff 
            fMode.add( new PlanarMirror(0,0,90));
            fMode.add( new ConcaveMirror(0,1,90));
            fMode.add( new ConvexMirror(0,2,90));
            fMode.add( new ConvexLens(0,3,90));
            fMode.add( new Filter(0,4,0,Color.RED));
            fMode.add( new LightSourceFM( 0,5,0,Color.RED,fMode));
            fMode.add( new TargetFM( 0,6,90,Color.RED));
            fMode.add( new ObstacleFM( 0,7,1,1,fMode));
            fMode.add( new ObstacleFM( 0,8,1,2,fMode));
            fMode.add( new ObstacleFM( 0,9,2,fMode));
            fMode.add( new ObstacleFM( 0,10,3,fMode));
            fMode.add( new LightCombinerFM( 0,11,3,fMode)); 
         }
         
         catch (IOException e1) {
            
            e1.printStackTrace();
         }
         
      }
   }
   
   
}