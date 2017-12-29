import java.util.Random;
import java.util.Arrays;

public class StockSimulator {
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

            double profit = -optionPrice * numTrials; // we spent this much to buy   numTrials # of stock options
            System.out.println("initial profit was " + profit);

            for (int i = 0; i < numTrials; ++i) {
                profit += maxProfit(optionPrice, numDays, avgFluctuation);
            }
            System.out.println("   profits over " + numTrials + " days were " + profit);

        } catch (Exception e) { // parse failed
            usage(); 
        }
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
                            And that question is one that can only be answered by financial data
                                            NOT theoretically
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

    /**
     *  Disclaimer: (!!! important to remember !!!)  Supposing we could   A) *** PREDICT PERFECTLY *** (which we can't) and that   B) future stock movements can be approximated by taking the average movement over a past few days and taking a "random walk" (uniform distribution) with the same average daily price movement,  what is the max profit we could make
     *
     */
    public static double maxProfit(double optionPrice, int numDays, double avgFluctuation) {
        double maxProfit = 0.0;
        double currPrice = 0.0;

        Random rand = new Random();

        for (int day = 0; day < numDays; ++day) {
            double priceChange = rand.nextDouble() * avgFluctuation * 2; // always positive
            priceChange = priceChange - (priceChange / 2.0); // allow negative
            currPrice += priceChange;
            if (currPrice > maxProfit) {
                maxProfit = currPrice;
            }
        }

        if (SHOW) {
            System.out.println(" max profit was  " + maxProfit);
        }
        return maxProfit;
    }

    /**
     * Tells the program's user how to input arguments
     *
     */
    public static void usage() {
        System.out.println(" Usage:");
        System.out.println("     java StockSimulator numDays optionPrice numTrials avgFluctuation");
        System.out.println(" ie. java StockSimulator      10       10.00       100           5.00");

    }

    public static int MAX = 7;
    public static int AVG_FLUCTUATION = 5;
    public static boolean SHOW = true;
}

