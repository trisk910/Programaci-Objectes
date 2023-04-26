package Business;

import java.util.Random;

public class Horse  extends Thread{
    private String name;
    private  int minSpeed;
    private  int maxSpeed;

    private double distanceTraveled;
    private double elapsedSeconds;

    private boolean isRacing = false;
    private boolean isFinished = false;
    public Horse(String name, int minSpeed, int maxSpeed) {
        this.name = name;
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
    }

    public void setRacing(boolean racing) {
        isRacing = racing;
    }

    public boolean isRacing() {
        return isRacing;
    }

    public String getHorseName() {
        return name;
    }

    public void setIsFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }


    @Override
    public void run() {
        while(!isFinished){
            double t0 = System.currentTimeMillis();
            int speed = getCurrentSpeed();

            if(isRacing) {
                if (distanceTraveled >= 100) {
                    //System.out.println(name + " finished the race!");
                    isRacing = false;
                } else {
                    double t1 = System.currentTimeMillis();
                    elapsedSeconds = (t1 - t0) / 1000.0;
                    distanceTraveled += speed * elapsedSeconds;
                }
                //System.out.println(name + " " + distanceTraveled);
            }
        }
    }
    public void setDistanceTraveled(double distanceTraveled) {
        this.distanceTraveled = distanceTraveled;
    }

    public double getDistanceTraveled() {
        return distanceTraveled;
    }

    synchronized public int getCurrentSpeed(){
        Random rand = new Random(System.currentTimeMillis());
        //System.out.println(name + " " +randomSpeed);
        return rand.nextInt(maxSpeed - minSpeed + 1) + minSpeed;
    }

}