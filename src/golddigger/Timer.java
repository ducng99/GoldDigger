package golddigger;

/**
 *
 * @author kqn1498
 */
public class Timer extends Thread {
    private int time;
    private boolean start = false;

    public double getTime() {
        return (double)time / 10.;
    }
    
    @Override
    public void run()
    {
        start = true;
        while (start)
        {
            time++;
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Timer cannot be paused!");
            }
        }
    }
    
    public void stopTimer()
    {
        start = false;
    }
}
