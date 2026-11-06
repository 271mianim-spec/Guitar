/**
 * This GuitarSimulator object . . .
 * 
 * @author  
 * @version 
 */
public class GuitarSimulator
{
    public final static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static void main(String[] args)
    {
        GuitarString[] strings = new GuitarString[37];
        for (int i = 0; i < strings.length; i++) {
            double expo = Math.pow(1.05956, (i - 24));
            int frequency = (int) (Math.round(440 * expo));
            strings[i] = new GuitarString(frequency);
        }

        // the main input loop
        while (true)
        {
            // check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped())
            {
                // the user types this character
                char key = StdDraw.nextKeyTyped();

                int keyNumber = keyboard.indexOf(key);
                System.out.println(key + " " + keyNumber);
                if (keyNumber == -1 || strings[keyNumber] == null) {
                    continue;
                }

                // pluck the corresponding string
                strings[keyNumber].pluck();
            }

            // compute the superposition of the samples
            double sample = 0;
            for (int i = 0; i < strings.length; i++) {
                GuitarString string = strings[i];
                if (string == null) {
                    continue;
                }
                sample += string.sample();
                string.tic();
            }


            // send the result to standard audio
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
//            stringA.tic();
//            stringC.tic();
        }
    }
}
