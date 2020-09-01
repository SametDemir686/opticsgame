import static java.lang.Math.*;
 
final public class Vector { 
    
   public double x = 0, y = 0;

   public Vector(double x, double y)
   { 
      this.x = x; 
      this.y = y; 
   } 
    
   public Vector rotate(double angleRadians)  
   { 
      double xx = x * cos(angleRadians) - y * sin(angleRadians);
      double yy = y * cos(angleRadians) + x * sin(angleRadians); 
      return new Vector(xx, yy); 
   } 
    
   public Vector negate()  
   { 
      return new Vector(-x, -y);
   } 
    
   public Vector unitVector()
   { 
      if (x != 0 || y != 0)
      { 
         double m = length(); 
         return new Vector(x/m,y/m); 
      }  
      else  
      { 
         return new Vector(0, 0); 
      } 
   } 
    
   public double angle()
   { 
      if (Math.toDegrees(atan2(y, x)) < 0) 
         return 360 + Math.toDegrees(atan2(y, x)); 
      else 
         return Math.toDegrees(atan2(y, x)); 
   } 
    
   public double length()
   { 
      return sqrt(x * x + y * y); 
   } 
    
   public String toString()
   { 
      return String.format("{%f,%f}", x, y); 
   } 
}