import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class car_blue here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CarBlue extends Leaf implements Cloneable
{
    private LevelStrategy currentStrategy;
    private boolean isFinishLevelReached = false;
    public CarBlue() {
        this.getImage().scale(70,30);
    }
    
    @Override 
    public CarBlue clone() throws CloneNotSupportedException {
          return (CarBlue) super.clone();
    }
      
    public void attachStrategy(LevelStrategy s) {
       currentStrategy = s;
    }
    /**
     * Act - do whatever the car_blue wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private static int Y=0;
    public void act() 
    {
        World world = getWorld();
        // Add your action code here.
        int worldX = world.getWidth();
        
        int worldY = world.getHeight();
        int pos = 0;
        if (getX() == 0)
            pos = 1200;
        else
            pos = 0;
            
        if(getFinishLevel()==false) {
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