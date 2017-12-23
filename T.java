import java.util.Random;
import java.util.Arrays;

public class T {
    /**
     *   Given cmd line args, tells you how often the option price is worth it
     * @param args[0] numDays
     * @param args[1] optionPrice
     * @param args[2] numTimes to run it
     * @param args[3] avgFluctuation
     *
     *
     * TODO:
     *   make args[] the variable arguments we want
     *
     */
    public static void main(String[] args) {
        int count = 0;

        int numDays;
        double optionPrice;
        int numTrials;
        double avgFluctuation;

        try {  // parse cmd line args
            numDays = Integer.parseInt(args[0]);
            optionPrice = Double.parseDouble(args[1]);
            numTrials = Integer.parseInt(args[2]);
            avgFluctuation = Double.parseDouble(args[3]);
        } catch (Exception e) {
            usage();
        }

        for (int i = 0; i < numTrials; ++i) {
            if (stockPriceExceeds(MAX, optionPrice)) {
                ++count;
            }
        }

        System.out.println("  price exceeded... " + count + " times out of " + numTrials);
    }

    /**
     *  Tells you whether, during this particular random walk of the stock market, you can make money off the option
     *  This concept should be extended to determine 
     *    1) HOW MUCH profit one can make
              a) double, rather than int
          2) For 

          **********************************************************************************************************************
                The real f*cking question is this:   how do we know when to sell and when to let the stock keep rising?
          **********************************************************************************************************************
     *
     */
    public static boolean stockPriceExceeds(int startPrice, int numDays, int optionCost) {
        int goalPrice = startPrice + optionCost;
        int currPrice = startPrice;
        boolean costExceeded = false;
        Random r = new Random();

        for (int day = 0; day < numDays && !costExceeded; ++day) {
            int change = AVG_FLUCTUATION - r.nextInt(2 * AVG_FLUCTUATION + 1); // because fluctuations should go from -FLUC to FLUC, 
                                                                               // whereas nextInt(FLUC) goes from 0 to FLUC - 1
            currPrice += change;

            if (currPrice > goalPrice) {
                costExceeded = true;
            }
        }

        return costExceeded;
    }

    public static void usage() {
        System.out.println(" Usage:");
        System.out.println("     java ... numDays optionPrice numTrials avgFluctuation");
        System.out.println(" ie. java ...    1000       10.00         1           5.00");

    }

    public static int MAX = 7;
    public static int AVG_FLUCTUATION = 5;
}

