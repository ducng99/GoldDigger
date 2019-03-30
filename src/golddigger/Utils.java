
package golddigger;

import java.util.Random;

/**
 *
 * @author Duc Nguyen 17974984
 */
public class Utils {
    private static final Random RAND = new Random(System.currentTimeMillis());
    
    public static void log(String value)
    {
        System.out.println(value);
    }
    
    /**
     * Returns a random integer from min to max
     * @param min
     * @param max
     * @return a random integer
     */
    public static int getRand(int min, int max)
    {
        return RAND.nextInt(max - min + 1) + min;
    }
}
