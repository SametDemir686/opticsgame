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

public class LevelPanel extends JPanel 
{ 
   Game game; 
   Question question; 
   JLabel level = new JLabel(" LEVEL NO "); 
   JButton q = new JButton("Try a Question"); 
    
   public LevelPanel(Game game)
   { 
      this.game = game; 
       
      setLayout(null);  
      setBounds(0, 0, 1440, 900);  
      level.setText(" LEVEL " + game.levelNumber);
      add(level);   
       
      add(q);  
       
      level.setBounds(30,0,300,30); 
      level.setForeground(Color.BLUE); 
      question = new Question(game); 
      q.setBounds(225,757,200,40); 
      q.setBackground( Color.BLUE ); 
       
      q.addActionListener( new ActionListener(){
         public void actionPerformed( ActionEvent e)  
         { 
            question.tryAQuestion(); 
         }}); 
       
      JButton a = new JButton("Back");
      add(a); 
      a.setBounds(25,757,70,40); 
      a.addActionListener( new ActionListener(){ 
         public void actionPerformed( ActionEvent e) { 
            setVisible(false); 
            game.opticsGame.panel2.setVisible(true); 
         } 
      }); 
   } 
}