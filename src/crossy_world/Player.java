import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Leaf
{
    /**
     * Act - do whatever the player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int speed = 2; //movement speed  
    private int vSpeed = 0; //vertical speed  
    private int acceleration = 2; //gravity effect while falling  
    private int jumpStrength = -8; 
    private boolean isFinishLevelReached = false;
    protected Mediator mediator;
    MyWorld myworld;
    
    public void act() 
    {
        if (mediator == null){
            List<Mediator> objects = myworld.getObjects(Mediator.class);
            mediator = objects.get(0);
        }
        Actor log;
        if (!myworld.getActionPaused())
        {
            // List<LifeCounter> lcs = myWorld.getObjects(LifeCounter.class);   
            // LifeCounter lc = lcs.get(0);
            checkKeys();
            if (isTouching(Car.class) || isTouching(CarBlue.class)) {
                mediator.lostLife();
            } else if (isTouching(River.class)) {
                List<River> river = myworld.getObjects(River.class);
                int riverY = river.get(0).getY();
                if (getY() > riverY-50) {        //not yet crossed the river
                    List<Log> logs = getNeighbours(75, true, Log.class);
                    if (logs.size() <=0) {
                        mediator.lostLife();
                    }
                    else {
                        if(!(Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("right"))) {
                            for (int i=0; i<logs.size();i++) {
                                log = (Actor)logs.get(i);
                                if (intersects(log)) {
                                    setLocation(log.getX(),log.getY());
                                    break;
                                }

                            }
                        }
            
                    }
            
                }
            }
        }
    }
 

    private boolean checkObstacle(int dx, int dy) {
        if(getObjectsAtOffset(dx, dy, Tree.class).size() == 0) {
            if(getObjectsAtOffset(dx, dy, Rock.class).size() == 0)
                return false;
        }
        return true;
    }

    public void checkKeys()
    {
        if (mediator == null){
            List<Mediator> objects = myworld.getObjects(Mediator.class);
            mediator = objects.get(0);
        }
        boolean obstacle = true;
        if( Greenfoot.isKeyDown("left"))
        {
            obstacle = checkObstacle(-10, 0);
            if(!obstacle)
                move(-5);
        }
        else if(Greenfoot.isKeyDown("right"))
        {
            obstacle = checkObstacle(10, 0);
            if(!obstacle)
                move(5);
        }
        else if(Greenfoot.isKeyDown("up")) 
        { 
            jump("up");
        }
        else if(Greenfoot.isKeyDown("down")) 
        {
            jump("down");
        }
        else {
            // Drag player down if he stands at one place and doesn't move
            if(getFinishLevel()==false)
                    setLocation(getX(), getY()+1);
            
            if(isAtEdge()) {
                    // Stop the level
                    // TODO: Stop only when the Player reaches the lowermost edge of the world. Otherwise, do nothing.
                    mediator.lostLife();
            }
        }

    }

    public void jump(String keyEvent)  
    {  

        vSpeed = jumpStrength;  
        if(keyEvent.equals("up"))
            fall();   
        else
            fallDown();
    }


    public void fall()  
    {  
        boolean obstacle = checkObstacle(0, vSpeed*2);
        if(!obstacle) {
            setLocation(getX(), getY()+vSpeed);  
            vSpeed = vSpeed + acceleration;  
        }
    }
    
    public void fallDown()  
    {  
        boolean obstacle = checkObstacle(0, -vSpeed*2);
        if(!obstacle) {
            setLocation(getX(), getY()-vSpeed);  
            vSpeed = vSpeed - acceleration;  
        }
    }  
     
    public void attachObserver(MyWorld myWorld){
        myworld = myWorld;
    }
    public void update(boolean state){
        this.isFinishLevelReached =  state;
    }
    public boolean getFinishLevel(){
        return isFinishLevelReached;
    }
}


