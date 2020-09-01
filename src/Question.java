
import java.util. ArrayList; 
import java.awt.*; 
import javax.swing.*; 
import java.io.*; 
 
public class Question { 
    
   JOptionPaneQuestion[] question;
   JTextField answerText; 
   Game game; 
    
   public Question( Game game) {
       
      this.game = game; 
      question = new JOptionPaneQuestion[6]; 
      answerText = new JTextField(); 
       
      question[0] = new JOptionPaneQuestion("images/q1.png", 70, "What will be the leaving angle of this light from the Z? ", answerText);
      question[1] = new JOptionPaneQuestion("images/q2.jpg", 45, "", answerText); 
      question[2] = new JOptionPaneQuestion("images/q3.gif", 2, "Which of the light beams may pass through the point P after reflections? ", answerText); 
      question[3] = new JOptionPaneQuestion("images/q4.jpg", 3, "How many illustrations are true as to how a concave lens behaves? ", answerText); 
      question[4] = new JOptionPaneQuestion("images/q5.jpg", 35, "If there is a planar mirror in the region P and coming angle of light to this mirror is theta, find theta. ", answerText); 
      question[5] = new JOptionPaneQuestion("images/q6.jpg", 2, "How many possible virtual views of X may be alleged? ", answerText); 
   } 
    
   public void tryAQuestion() {
       
      int random = (int)(Math.random()*6);
       
      int result = question[random].showQuestion();
       
      if (result == JOptionPane.OK_OPTION)
      { 
         if ( answerText.getText().equals("" + question[random].getAnswer() ) )
         { 
            JFrame optionPane = new JFrame();
             
            Object[] options = {"Back", "Next Level"}; 
            int n = JOptionPane.showOptionDialog(optionPane, 
                                                 "LEVEL COMPLETED", 
                                                 "Congrats!", 
                                                 JOptionPane.YES_NO_OPTION, 
                                                 JOptionPane.QUESTION_MESSAGE, 
                                                 null, 
                                                 options, 
                                                 options[1]); 
             
            if ( game.levelNumber == game.opticsGame.noOfCompletedLevels + 1 )
            { 
               game.opticsGame.noOfCompletedLevels++; 
            } 
            try
            { 
               game.opticsGame.saveGame(); 
            } 
            catch ( IOException e1) 
            { 
               e1.printStackTrace(); 
            } 
             
            game.level.setCompleted(true);
            game.opticsGame.gameArray[game.levelNumber].level.setLocked(false); 
             
            if (n == JOptionPane.YES_OPTION)
            { 
               game.levelPanel.setVisible(false);
               game.opticsGame.panel2.setVisible(true); 
            } 
             
            else if (n == JOptionPane.NO_OPTION)
            { 
               game.opticsGame.gameArray[game.levelNumber].levelPanel.setVisible(true);
               game.levelPanel.setVisible(false); 
                
            } 
         } 
         else 
         { 
            JFrame optionPane = new JFrame();
             
            Object[] options = {"OK"}; 
            int n = JOptionPane.showOptionDialog(optionPane, 
                                                 "WRONG ANSWER!", 
                                                 "Oops...", 
                                                 JOptionPane.OK_OPTION, 
                                                 JOptionPane.QUESTION_MESSAGE, 
                                                 null, 
                                                 options, 
                                                 options[0]); 
         } 
      } 
       
   }       
}