/**
 * This GuitarString object . . .
 * 
 * @author  
 * @version 
 */
public class GuitarString 
{
    private RingBuffer buffer;

    private final double energyDecay = 0.994;

    private int ticks;
    private int length;

    public GuitarString(double frequency) 
    {
        length = (int) (Math.ceil(44100/frequency));
        this.buffer = new RingBuffer(length);
        for (int i = 0; i < length; i++) {
            buffer.add(0);
        }
    }

    public GuitarString(double[] array) 
    {
        int frequency = array.length;

        length = (int) (Math.ceil(44100/frequency));
        this.buffer = new RingBuffer(length);
        for (int i = 0; i < length; i++) {
            buffer.add(0);
        }
    }

    public void pluck() 
    {
        for (int i = 0; i < length; i++) {
            buffer.remove();

            int randomNum = (int) ((Math.random()*10)-5);

            buffer.add(randomNum);
        }
    }

    // advance the simulation one time step
    public void tic() 
    {
        double firstValue = buffer.remove();
        double secondValue = buffer.peek();

        double average = (firstValue + secondValue)/2;
        double finalAverage = average * energyDecay;

        buffer.add(finalAverage);

        ticks++;
    }

    // return the current sample
    public double sample() 
    {
        return buffer.peek();
    }

    // return number of times tic was called
    public int time() 
    {
        return ticks;
    }

    public static void main(String[] args) 
    {
        double[] samples = { .2, .4, .5, .3, -.2, .4, .3, .0, -.1, -.3 };  
        GuitarString testString = new GuitarString(samples);
        for (int i = 0; i < 25; i++) 
        {
            int t = testString.time();
            double sample = testString.sample();
            System.out.printf("%6d %8.4f\n", t, sample);
            testString.tic();
        }
    }
}
