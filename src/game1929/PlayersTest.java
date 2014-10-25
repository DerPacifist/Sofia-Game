package game1929;
import student.TestCase;
/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author thomasmeyer
 *  @version Oct 21, 2014
 */
public class PlayersTest
    extends TestCase
{
    private Players testPlayer1;
    private Players testPlayer2;
    private Stocks testStock;

    public void setUp() {
        testPlayer1=new Players(1);
        testPlayer2=new Players(2);
        testStock=new Stocks(1,"test");
    }

    /**
     * tests to see if the buy method works properly
     * and will not buy more stock if the player is out of cash
     */
    public void testBuy() {
        testPlayer1.buy(testStock);
        assertEquals(testPlayer1.worth(),200);
        testPlayer1.buy(testStock);
        testPlayer1.buy(testStock);
        testPlayer1.buy(testStock);
        testPlayer1.buy(testStock);
        testPlayer1.buy(testStock);
        assertEquals(testPlayer1.amount(testStock),5);
            }

    /**
     * tests to see if the sell method works properly
     * and will not sell more stock if the player is out of shares
     */
    public void testSell() {
        testPlayer2.sell(testStock);
        assertEquals(testPlayer2.amount(testStock),0);
        testPlayer2.buy(testStock);
        testPlayer2.sell(testStock);
        assertEquals(testPlayer2.amount(testStock),0);
        assertEquals(testPlayer2.worth(),250);

    }
}
