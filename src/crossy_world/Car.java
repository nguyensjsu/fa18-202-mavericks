import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class car here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Car extends Leaf implements Cloneable
{
    private LevelStrategy currentStrategy;
    private boolean isFinishLevelReached = false;
    World world;

 
    public Car() {
        this.getImage().scale(70,30);
    }
    
    @Override 
    public Car clone() throws CloneNotSupportedException {
          return (Car) super.clone();
    }
      
    public void attachStrategy(LevelStrategy s) {
       currentStrategy = s;
    }
    
    /**
     * Act - do whatever the car wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private static int Y=0;
    public void act() 
    {
        world = getWorld();
        // Add your action code here.
        int worldX = world.getWidth();
        
        int worldY = world.getHeight();
 
        
        int pos = 0;
        if (getX() == 0)
            pos = 1200;
        else
            pos = 0;
            
        if(getFinishLevel() == false) {
            setLocation((pos + getX()- currentStrategy.getCarSpeed()), (getY()+1)%worldY);
        }
    }  
    public void update(boolean state){
        this.isFinishLevelReached =  state;
    }
    public boolean getFinishLevel(){
        return isFinishLevelReached;
    }
    
}