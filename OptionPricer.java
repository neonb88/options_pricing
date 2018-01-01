/**
 * We're just generally looking for a way to make money.
 *
 * TODO:
 * 1)
 *   start with stock options
 *   if bond options give higher yields, do that
 *   ... currency options ''
 *   commodities, whatever
 *
 *   Find a way to record option prices from option sites like (https://finance.yahoo.com/quote/TSLA/options?straddle=true).  I may end up doing this first one by hand simply because I don't have a better way, but unless the company I'm working for has a way to do it by...   I just had an idea related to this; let me use a language that controls the mouse and keyboard to do this.  ie. AppleScript (https://en.wikipedia.org/wiki/AppleScript)
 *
 * 2) optimize this with AppleScript
 *
 *
 * Method:
 *   Remember, we have to make sure we can make money IN GENERAL by betting on these options at a particular price.  That means that FOR ALL periods in the stock's history, we have to be reasonably sure that with this volatility, we can make net profit.  Of course, as Taleb says, this is literally impossible to predict with any precision.  But we can try it, and from experience, see whether we made money, and how many bets that the stock market will fall remain in our portfolio at the end
 *
 */
public class OptionPricer {
    public static void main(String[] args) {
        String dirPath = "/home/n/Documents/todo/personal_projs/hedge_funds/data";
        List<List<Double>> data = readCSVs(dirPath); // could use a different type of data structure here

    }
}

