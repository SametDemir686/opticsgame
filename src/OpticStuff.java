
public interface OpticStuff {
    
   int getRow(); 
   int getColumn(); 
   int getX(); 
   int getY(); 
   int touches( Light light); 
   String getImageName(); 
    
   public OpticStuff cloneOp(); 
} 
