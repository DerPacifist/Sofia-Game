package game1929;

/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author thomasmeyer
 * @version Oct 20, 2014
 */
public class Stocks
{
    /**
     * the stock's ID number that will represent its position in both "Game's"
     * stocks ArrayList and "Players" share vector. Not needed but makes testing
     * and coding less messy.
     */
    public int    number;
    /**
     * the stock's formal name, used for displaying on the game visible board
     */
    public String name;
    /**
     * the price at which the stock will be bought and sold for
     */
    public int    price;
    /**
     * the item used to calculate whether or not a stock will increase or
     * decrease. The lower the number the more likely the stock will increase
     */
    public int    chance;


    /**
     * @param number
     *            Creates the stock with its ID number.
     * @param name
     *            Gives the stock its formal name other fields begin at a
     *            constant value
     */
    Stocks(int number, String name)
    {
        this.number = number;
        this.name = name;
        this.price = 50;
        this.chance = 20;
    }


    /**
     * increases the value of the stock by 10 as well as decreasing the chance
     * that the stock will increase again
     */
    public void increase()
    {
        price = price + 10;
        chance = chance + 7;
    }


    /**
     * decreases the value of the stock by 10 as well as decreasing the chance
     * that the stock will decrease again
     */
    public void decrease()
    {

        price = price - 10;
        chance = chance - 5;
    }


    /**
     * sets the stocks value to 50 sets the chance to 30
     */
    public void split()
    {
        price = 50;
        chance = 30;
    }


    /**
     * doesn't change stock price, but increases chance of the stock increasing
     */
    public void buffer()
    {
        chance = chance - 2;
    }


    /**
     * bankrupts the stock
     */
    public void end()
    {
        price = 0;
    }

    /**
     * Used only for test purposes
     * @return int
     *  returns the chance value
     */
    public int chance() {
        return chance;
    }

    /**
     * Used only for test purposes
     * @return int
     *  returns the price value
     */
    public int price() {
        return price;
    }
}
