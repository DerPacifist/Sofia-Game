package game1929;

import java.util.ArrayList;

/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author thomasmeyer
 * @version Oct 20, 2014
 */
public class Players
{
    /**
     * The player's number used for identification purpose. Not needed but makes
     * coding and testing neater.
     */
    public int   number;
    /**
     * The player's current amount of cash on hand
     */
    public int   cash;
    /**
     * A vector used to store how many shares of each stock is owned by the
     * player. Note the position in the vector matches with the ID from the
     * Stock
     */
    public int[] shares;


    /**
     * @param number
     *            Gives the player its identification number the rest of the
     *            fields start at a constant value
     */
    Players(int number)
    {
        this.number = number;
        this.cash = 250;
        this.shares = new int[6];
        for (int x = 0; x < 6; x++)
        {
            shares[x] = 0;
        }

    }


    /**
     * @param stock
     *            which stock the player is buying Adds a share of the desired
     *            stock and subtracts the cost of that stock from the player's
     *            cash
     */
    public void buy(Stocks stock)
    {
        if (stock.price > 0 && cash >= stock.price)
        {
            shares[stock.number]++;
            cash = cash - stock.price;
        }
    }


    /**
     * @param stock
     *            which stock the player is selling Subtracts a share of the
     *            desired stock and then adds the price of that stock to the
     *            player's cash
     */
    public void sell(Stocks stock)
    {
        if (shares[stock.number] > 0)
        {
            shares[stock.number]--;
            cash = cash + stock.price;
        }
    }


    /**
     * @return int gives the current cash ammount of the player
     */
    public int worth()
    {
        return (cash);
    }


    /**
     * @return ArrayList Used to simulate the response to what stock the player
     *         wants to buy/sell
     */
    public ArrayList<Object> ask()
    {
        ArrayList<Object> result = new ArrayList<Object>();
        int answer1 = 0;
        boolean answer2 = false;
        boolean answer3 = false;
        boolean answer4 = false;
        boolean answer5=false;
        int input6 = 0;
        result.add(answer1);
        result.add(answer2);
        result.add(answer3);
        result.add(answer4);
        result.add(answer5);
        result.add(input6);

        return result;
    }
    /**
     * returns the number of shares of a particular stock
     * used for testing only
     * @param stock
     *  which stock we are calculating the number of shares for
     *@return int
     *  the number of shares we actually have
     */
    public int amount(Stocks stock) {
        return shares[stock.number];
    }
}
