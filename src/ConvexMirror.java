public class ConvexMirror extends SphericalMirrors {
    
   double alpha; 
    
   public ConvexMirror(int row, int column, double angle) {
      super(row*60, column*60, angle); 
   } 
    
    
   public ConvexMirror(double locX, double locY, double angle) {
      super((int)locX, (int)locY, angle); 
   } 
    
    
   @Override 
   public Light reflect (Light light)
   { 
      Vector centerVect = new Vector(light.endX-getXCenter(), -light.endY+getYCenter()).negate();
      Vector lightVect = new Vector(light.vector.x, light.vector.y); 
       
      double dotProduct = lightVect.x * centerVect.x + lightVect.y * centerVect.y;
      double magnitudeProduct = lightVect.length() * centerVect.length(); 
      alpha = Math.toDegrees(Math.acos(dotProduct / magnitudeProduct)); 
       
       
      if (alpha > 90)
      { 
         alpha = 180 - alpha; 
      } 
       
      double angleDifference = centerVect.angle() - light.vector.angle(); 
       
       
      if(angleDifference < 0)
      { 
         angleDifference+=360; 
      } 
       
      else if (angleDifference > 180) 
      { 
         angleDifference-=360; 
      } 
       
      if (angleDifference > 180) 
      { 
         double angle = -2 * alpha - 180 + light.angle; 
         return new Light( light.getEndX(), light.getEndY(), angle, light.color, light.game);    
      } 
       
      else if (angleDifference < -180) 
      { 
          
         double angle =  180 + 2 * alpha + light.angle; 
         return new Light( light.getEndX(), light.getEndY(), angle, light.color, light.game); 
          
      } 
      else 
      { 
         if (angleDifference <= 0) 
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
    
   @Override 
   public int touches(Light light) {
       
      boolean appropriateAngle = false;
       
       
      double lightCenterAngle = Math.toDegrees(Math.atan2( - light.endy + getYCenter(), light.endx - getXCenter()));
      
      if( lightCenterAngle < 0)
         lightCenterAngle += 360; 
       
       
      if( angle <= 150 && angle >= 0)
      { 
         if(lightCenterAngle > angle + 150 && lightCenterAngle < angle + 210) 
            appropriateAngle = true; 
         else 
            appropriateAngle = false; 
      } 
       
      else if ( angle <= 210 && angle > 150) 
      { 
         if(lightCenterAngle >= angle + 150 && lightCenterAngle < 360 || lightCenterAngle >= 0 && lightCenterAngle < angle - 150 ) 
            appropriateAngle = true; 
         else 
            appropriateAngle = false; 
      } 
       
      else 
      { 
         if(lightCenterAngle > angle - 210 && lightCenterAngle < angle - 150) 
            appropriateAngle = true; 
         else 
            appropriateAngle = false; 
      } 
       
          
      double length = Math.sqrt( Math.abs((light.endy-getYCenter())*(light.endy-getYCenter())+(light.endx - getXCenter())*(light.endx - getXCenter() )));
       
       
      if ( length <= CENTER + TOLERANCE && length >= CENTER - TOLERANCE && appropriateAngle )
      { 
         if ( length >= CENTER ) 
         { 
            return 1; 
         } 
         else 
         { 
            return 0; 
         } 
      } 
       
      return -1; 
   } 
    
   public void rotate (double newAngle)
   { 
      angle = newAngle; 
      if (angle < 0) 
         angle = angle % 360 + 360; 
      else  
         angle = angle % 360; 
   } 
    
   public double getXCenter()
   { 
      return getCenterX() + CENTER * Math.cos( Math.toRadians(angle) ); 
   } 
    
    
   public double getYCenter()
   { 
      return getCenterY() - CENTER * Math.sin( Math.toRadians(angle) ); 
   } 
    
   public OpticStuff cloneOp()
   { 
      return new ConvexMirror(getRow(), getColumn(), angle); 
   } 
    
    
   @Override   
   public String getImageName() {
      return "images/convex mirror.png"; 
   } 
    
    
} 
