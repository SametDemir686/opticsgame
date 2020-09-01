import java.awt.*; 
import javax.swing.*;

public class JOptionPaneQuestion 
{ 
   int answer; 
   String imageName; 
   JOptionPane pane; 
   String questionText; 
   JTextField answerText; 
    
   public JOptionPaneQuestion(String imageName, int answer, String questionText, JTextField answerText)
   { 
      this.answer = answer; 
      this.imageName = imageName; 
      this.questionText = questionText; 
      this.answerText = answerText; 
   } 
   public int getAnswer()
   { 
      return answer; 
   } 
   public int showQuestion()
   { 
      JFrame frame = new JFrame(); 
      frame.setLocation(600,400); 
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
       
      JPanel panel = new JPanel( new GridLayout(2, 2) ); 
      panel.add( new JLabel("Question goes here") ); 
      JLabel questionTextx = new JLabel(questionText); 
       
      panel.add( questionTextx ); 
      panel.add( new JLabel("Answer goes here") ); 
       
      panel.add( answerText ); 
       
      ImageIcon icon = new ImageIcon(); 
      icon = new ImageIcon( Question.class.getResource(imageName)); 
       
      Object[] msg = { icon, questionText, answerText}; 
       
       
      return JOptionPane.showConfirmDialog(frame,msg,"Trying a Question!",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE); 
   } 
    
}