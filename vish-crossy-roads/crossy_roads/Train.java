import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Train here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Train extends Leaf
{
    /**
     * Act - do whatever the Train wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
 
    public void act() 
    {
        World world = getWorld();
        int worldX = world.getWidth();
        
        int worldY = world.getHeight();
        if(Level1Strategy.getFinalLevelState()==false)
        setLocation((getX())%worldX, (getY()+1)%worldY);
    }  
}