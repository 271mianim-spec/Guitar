/**
 * This RingBuffer object . . .
 * 
 * @author  
 * @version 
 */
public class RingBuffer
{

    private double[] buffer;
    private int size;
    private int capacity;

    private int headPos;
    private int tailPos;

    public RingBuffer(int capacity)
    {
        this.buffer = new double[capacity];
        this.capacity = capacity;
        this.size = 0;
        this.headPos = 0;
        this.tailPos = 0;
    }

    public int size()
    {
        return size;
    }
    
    public boolean isEmpty()
    {
        return size <= 0;
    }
    
    public boolean isFull()
    {
        return size == capacity;
    }
    
    public void add(double value)
    {
        size++;

        buffer[tailPos] = value;
        tailPos++;
        if (tailPos == buffer.length) {
            tailPos = 0;
        }
    }
    
    public double peek()
    {
        return this.buffer[headPos];
    }
    
    public double remove()
    {
        double item = buffer[headPos];
        // source for null inputs: https://docs.oracle.com/javase/8/docs/api/java/lang/Double.html
        this.buffer[this.headPos] = Double.NaN;
        this.headPos++;
        if (headPos == buffer.length) {
            this.headPos = 0;
        }

        size--;

        return item;
    }
    
    // a simple test of the constructor and methods in RingBuffer
    public static void main(String[] args) 
    {
        int capacity = 100;
        RingBuffer buffer = new RingBuffer(capacity);  
        for (int i = 1; i <= capacity; i++) 
            buffer.add(i);
      
        double t = buffer.remove();
        buffer.add(t);
        System.out.println("Size after wrap-around is " + buffer.size());
        while (buffer.size() >= 2) 
        {
            double x = buffer.remove();
            double y = buffer.remove();
            buffer.add(x + y);
        }
        System.out.println(buffer.peek());
    }

}
