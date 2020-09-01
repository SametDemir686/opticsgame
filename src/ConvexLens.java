
public class ConvexLens extends Lenses { 
   double length1 = 0;
   double length2 = 0; 
    
    
   public ConvexLens(int row, int column, double angle) {
      super(row*60, column*60, angle); 
   } 
    
   public double getXCenter1()
   { 
      return getCenterX() + (CENTER-10) * Math.cos( Math.toRadians(angle) ); 
   } 
    
    
   public double getYCenter1()
   { 
      return getCenterY() - (CENTER-10) * Math.sin( Math.toRadians(angle) ); 
   } 
    
   public double getXCenter2()
   { 
      return getCenterX() - (CENTER-10) * Math.cos( Math.toRadians(angle) ); 
   } 
    
    
   public double getYCenter2()
   { 
      return getCenterY() + (CENTER-10) * Math.sin( Math.toRadians(angle) ); 
   } 
    
   @Override 
   public Light reflect(Light light) {
       
      Vector lightVect = new Vector(light.vector.x, light.vector.y); 
       
       
      if (length1 <= CENTER + TOLERANCE && length1 >= CENTER)
      { 
         Vector centerVect1 = new Vector(light.endX-getXCenter1(), -light.endY+getYCenter1()).negate(); 
         double angleDifference1 = centerVect1.angle() - light.vector.angle();
          
         if(angleDifference1 < 0)
         { 
            angleDifference1+=360; 
         } 
          
         else if (angleDifference1 > 180) 
         { 
            angleDifference1-=360; 
         } 
         double dotProduct = lightVect.x * centerVect1.x + lightVect.y * centerVect1.y;
         double magnitudeProduct = lightVect.length() * centerVect1.length(); 
         double alpha = Math.toDegrees(Math.acos(dotProduct / magnitudeProduct)); 
          
         if (alpha > 90)
         { 
            alpha = 180 - alpha; 
         } 
          
         double beta = Math.abs(Math.toDegrees(Math.asin(  Math.sin( Math.toRadians(alpha)) / GLASS_INDEX  )));
          
          
         if (angleDifference1 > 180) // Light is greater
         { 
            double angle = - alpha + beta + light.angle; 
            return new Light( light.getEndX(), light.getEndY(), angle, light.color, light.game);    
             
         } 
          
         else if (angleDifference1 < -180) // Center is greater 
         { 
            double angle = alpha - beta + light.angle; 
            return new Light( light.getEndX(), light.getEndY(), angle, light.color, light.game); 
             
         } 
         else 
         { 
            if (angleDifference1 <= 0) // Light is greater 
            { 
               double angle =  - alpha + beta + light.angle; 
               return new Light( light.getEndX(), light.getEndY(), angle, light.color, light.game); 
            } 
            else // Center is greater 
            { 
               double angle = alpha - beta + light.angle; 
               return new Light( light.getEndX(), light.getEndY(), angle, light.color, light.game); 
            } 
         } 
      } 
       
      else if ( length1 >= CENTER - TOLERANCE && length1 < CENTER)
      { 
          
         Vector centerVect1 = new Vector(light.endX-getXCenter1(), -light.endY+getYCenter1()); 
         double angleDifference1 = centerVect1.angle() - light.vector.angle();
         
          
         if(angleDifference1 < 0)
         { 
            angleDifference1+=360; 
         } 
          
         else if (angleDifference1 > 180) 
         { 
            angleDifference1-=360; 
         } 
          
         double dotProduct = lightVect.x * centerVect1.x + lightVect.y * centerVect1.y;
         double magnitudeProduct = lightVect.length() * centerVect1.length(); 
         double alpha = Math.toDegrees(Math.acos(dotProduct / magnitudeProduct)); 
          
         if (alpha > 90)
         { 
            alpha = 180 - alpha; 
         } 
         if ( alpha > LIMITING_ANGLE)
         { 
            if (angleDifference1 > 180)
            { 
                
               double angle = -2 * alpha - 180 + light.angle; 
               return new Light( light.getEndX(), light.getEndY(), angle, light.color, light.game);    
                
            } 
             
            else if (angleDifference1 < -180) 
            { 
                
               double angle =  180 + 2 * alpha + light.angle; 
               return new Light( light.getEndX(), light.getEndY(), angle, light.color, light.game); 
                
            } 
            else 
            { 
               if (angleDifference1 <= 0) 
               { 
                  double angle =  -2 * alpha - 180 + light.angle ; 
                  return new Light( light.getEndX(), light.getEndY(), angle, light.color, light.game); 
               } 
               else 
               { 
                  double angle = 180 + 2 * alpha + light.angle; 
                  return new Light( light.getEndX(), light.getEndY(), angle, light.color, light.game); 
               } 
            } 
         } 
         double beta = Math.abs(Math.toDegrees(Math.asin(  Math.sin( Math.toRadians(alpha)) * GLASS_INDEX  )));
         if (angleDifference1 > 180) // Light is greater
         { 
            double angle = - alpha + beta + light.angle; 
            return new Light( light.getEndX(), light.getEndY(), angle, light.color, light.game);    
             
         } 
          
         else if (angleDifference1 < -180) // Center is greater 
         { 
             
            double angle = alpha - beta + light.angle; 
            return new Light( light.getEndX(), light.getEndY(), angle, light.color, light.game); 
             
         } 
         else 
         { 
            if (angleDifference1 <= 0) // Light is greater  
            { 
               double angle =  - alpha + beta + light.angle; 
               return new Light( light.getEndX(), light.getEndY(), angle, light.color, light.game); 
            } 
            else // Center is greater  
            { 
               double angle = alpha - beta + light.angle; 
               return new Light( light.getEndX(), light.getEndY(), angle, light.color, light.game); 
            } 
         }              
      } 
      else if (length2 <= CENTER + TOLERANCE && length2 >= CENTER)
      { 
         Vector centerVect2 = new Vector(light.endX-getXCenter2(), -light.endY+getYCenter2()).negate(); 
         double angleDifference2 = centerVect2.angle() - light.vector.angle();
          
         if(angleDifference2 < 0)
         { 
            angleDifference2+=360; 
         } 
          
         else if (angleDifference2 > 180) 
         { 
            angleDifference2-=360; 
         } 
          
          
         centerVect2 = new Vector(light.endX-getXCenter2(), -light.endY+getYCenter2()).negate(); 
         double dotProduct = lightVect.x * centerVect2.x + lightVect.y * centerVect2.y;
         double magnitudeProduct = lightVect.length() * centerVect2.length(); 
         double alpha = Math.toDegrees(Math.acos(dotProduct / magnitudeProduct)); 
                   
         if (alpha > 90)
         { 
            alpha = 180 - alpha; 
         } 
          
         double beta = Math.abs(Math.toDegrees(Math.asin(  Math.sin( Math.toRadians(alpha)) / GLASS_INDEX  )));
          
         if (angleDifference2 > 180) // Light is greater
         { 
            double angle = - alpha + beta + light.angle; 
            return new Light( light.getEndX(), light.getEndY(), angle, light.color, light.game);    
             
         } 
          
         else if (angleDifference2 < -180) //Center is greater   
         { 
             
            double angle = alpha - beta + light.angle; 
            return new Light( light.getEndX(), light.getEndY(), angle, light.color, light.game); 
             
         } 
         else 
         { 
            if (angleDifference2 <= 0) // Light is greater
            { 
               double angle =  - alpha + beta + light.angle; 
               return new Light( light.getEndX(), light.getEndY(), angle, light.color, light.game); 
            } 
            else //Center is greater 
            { 
               double angle = alpha - beta + light.angle; 
               return new Light( light.getEndX(), light.getEndY(), angle, light.color, light.game); 
            } 
         } 
          
      } 
       
      else if ( length2 >= CENTER - TOLERANCE && length2 <= CENTER)
      { 
               
         Vector centerVect2 = new Vector(light.endX-getXCenter2(), -light.endY+getYCenter2()); 
         double angleDifference2 = centerVect2.angle() - light.vector.angle();
          
         if(angleDifference2 < 0)
         { 
            angleDifference2+=360; 
         } 
          
         else if (angleDifference2 > 180) 
         { 
            angleDifference2-=360; 
         } 
          
         double dotProduct = lightVect.x * centerVect2.x + lightVect.y * centerVect2.y;
         double magnitudeProduct = lightVect.length() * centerVect2.length(); 
         double alpha = Math.toDegrees(Math.acos(dotProduct / magnitudeProduct)); 
          
         if (alpha > 90)
         { 
            alpha = 180 - alpha; 
         } 
          
         if ( alpha > LIMITING_ANGLE)
         { 
            if (angleDifference2 > 180)
            { 
                
               double angle = -2 * alpha - 180 + light.angle; 
               return new Light( light.getEndX(), light.getEndY(), angle, light.color, light.game);    
                
            } 
             
            else if (angleDifference2 < -180) 
            { 
                
               double angle =  180 + 2 * alpha + light.angle; 
               return new Light( light.getEndX(), light.getEndY(), angle, light.color, light.game); 
                
            } 
            else 
            { 
               if (angleDifference2 <= 0) 
               { 
                  double angle =  -2 * alpha - 180 + light.angle ; 
                  return new Light( light.getEndX(), light.getEndY(), angle, light.color, light.game); 
               } 
               else 
               { 
                  double angle = 180 + 2 * alpha + light.angle; 
                  return new Light( light.getEndX(), light.getEndY(), angle, light.color, light.game); 
               } 
            } 
         } 
         double beta = Math.abs(Math.toDegrees(Math.asin(  Math.sin( Math.toRadians(alpha)) * GLASS_INDEX  )));
          
         if (angleDifference2 > 180) // Light  is greater
         { 
            double angle = - alpha + beta + light.angle; 
            return new Light( light.getEndX(), light.getEndY(), angle, light.color, light.game);    
             
         } 
          
         else if (angleDifference2 < -180) // Center is greater 
         { 
            double angle = alpha - beta + light.angle; 
            return new Light( light.getEndX(), light.getEndY(), angle, light.color, light.game); 
             
         } 
         else 
         { 
            if (angleDifference2 <= 0) // Light is greater 
            { 
               double angle =  - alpha + beta + light.angle; 
               return new Light( light.getEndX(), light.getEndY(), angle, light.color, light.game); 
            } 
            else // Center is greater 
            { 
               double angle = alpha - beta + light.angle; 
               return new Light( light.getEndX(), light.getEndY(), angle, light.color, light.game); 
            } 
         } 
      }      
      return null; 
   } 
    
   @Override 
   public int touches(Light light) {
      length1 = Math.sqrt( Math.abs((light.endy-getYCenter1())*(light.endy-getYCenter1())+(light.endx - getXCenter1())*(light.endx - getXCenter1() )));
      length2 = Math.sqrt( Math.abs((light.endy-getYCenter2())*(light.endy-getYCenter2())+(light.endx - getXCenter2())*(light.endx - getXCenter2() )));
       
       
      if ( (length1 <= CENTER + TOLERANCE && length1 >= CENTER - TOLERANCE) && length2 < CENTER)
      { 
         return 1; 
      } 
       
      else if ( (length2 <= CENTER + TOLERANCE && length2 >= CENTER - TOLERANCE) && length1 < CENTER) 
      { 
         return 1; 
      } 
       
       
      return -1; 
   } 
    
   public OpticStuff cloneOp()
   { 
      return new ConvexLens(getRow(), getColumn(), angle); 
   } 
    
   @Override 
   public String getImageName() {
      return "images/convex lens.png";
   } 
} 
