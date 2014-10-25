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
public class StocksTest
    extends TestCase
{
    private Stocks testStock1;
    private Stocks testStock2;
    private Stocks testStock3;
    private Stocks testStock4;
    private Stocks testStock5;

    public void setUp() {
        testStock1=new Stocks(1,"test");
        testStock2=new Stocks(2,"test");
        testStock3=new Stocks(3,"test");
        testStock4=new Stocks(4,"test");
        testStock5=new Stocks(5,"test");
    }

    /**
     * tests to make sure this method adjusts
     * the stocks price and chance accordingly
     */
    public void testIncrease() {
        testStock1.increase();
        assertEquals(60,testStock1.price());
        assertEquals(27,testStock1.chance());
    }

    /**
     * tests to make sure this method adjusts
     * the stocks price and chance accordingly
     */
    public void testDecrease() {
        testStock2.decrease();
        assertEquals(40,testStock2.price());
        assertEquals(15,testStock2.chance());
    }

    /**
     * tests to make sure this method adjusts
     * the stocks price and chance accordingly
     */
    public void testSplit() {
        testStock3.split();
        assertEquals(50,testStock3.price());
        assertEquals(30,testStock3.chance());
    }

    /**
     * tests to make sure this method adjusts
     * the stocks price and chance accordingly
     */
    public void testBuffer() {
        testStock4.buffer();
        assertEquals(18,testStock4.chance());

    }

    /**
     * tests to make sure this method adjusts
     * the stocks price and chance accordingly
     */
    public void testEnd() {
        testStock5.end();
        assertEquals(0,testStock5.price());
    }
}
