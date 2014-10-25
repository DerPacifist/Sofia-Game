package game1929;

import java.util.Random;
import java.util.Stack;
// import sofia.util.Random;
import java.util.ArrayList;

/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author thomasmeyer
 * @version Oct 20, 2014
 */
public class Game
{
    private int                      players;
    private int                      turn;
    private int                      end;
    /**
     * the arrayList of Players objects. Used to determine which player is take
     * his/her turn
     */
    public ArrayList<Players>        person;
    /**
     * the arrayList of Stocks objects. Used whenever a stock is manipulated
     */
    public ArrayList<Stocks>         stocks;
    /**
     * creates the history of each Stock, so that it can be called and reviewed
     * later. The purposes of this is for interest and so that the game can
     * fairly be balanced
     */
    public Stack<ArrayList<Stocks>> historyS;
    /**
     * creates the history of each Player, so that it can be called and reviewed
     * later. The purposes of this is for interest and so that the game can
     * fairly be balanced
     */
    public Stack<ArrayList<Players>> historyP;


    /**
     * @param players
     *            used to create the appropriate number of Players Objects in
     *            the person arrayList
     * @param gameLength
     *            used to extend the length game based on the players desires
     */
    Game(int players, int gameLength)
    {
        this.players = players;
        this.turn = 0;
        Random ran = new Random();
        /**
         * determines how many turns the game will run until the Stock Market
         * Crashes
         */
        this.end =
            (1 + 4 * gameLength + ran.nextInt(16)) * players
                + ran.nextInt(players);
        this.historyS = new Stack<ArrayList<Stocks>>();
        historyS.push(stocks);
        this.historyP = new Stack<ArrayList<Players>>();
        historyP.push(person);
    }


    /**
     * Begins the playing of the game
     */
    public void startGame()
    {
        for (int x = 0; x < players; x++)
        {
            Players addP = new Players(x);
            person.add(addP);
        }
        /**
         * initializes all the stocks
         */
        Stocks A = new Stocks(0, "A");
        stocks.add(A);
        Stocks B = new Stocks(1, "B");
        stocks.add(B);
        Stocks C = new Stocks(2, "C");
        stocks.add(C);
        Stocks D = new Stocks(3, "D");
        stocks.add(D);
        Stocks E = new Stocks(4, "E");
        stocks.add(E);
        Stocks F = new Stocks(5, "F");
        stocks.add(F);

        /**
         * opening turn sequence where players can buy and sell stocks
         */
        for (int i = 0; i < players; i++)
        {
            turn(10);
            turn++;
        }

        /**
         * this is the meat of the game where each player takes turns and the
         * stocks fluctuate in price. This goes on until the game reaches its
         * end
         */
        while (turn != end)
        {
            turn(3);
            event();
            turn++;
            /**
             * saves the state of all the Players and Stocks
             * so that they can be recalled later
             */
            historyS.push(stocks);
            historyP.push(person);
        }
        /**
         * ends the game and returns the winner
         */
        endGame();
    }


    /**
     * Event determines how stocks change in price on a given turn takes a
     * random stock and gets a random number. This number is compared to the
     * chance to see if the stock will increase or decrease in price if the
     * stock increases to 100 the stock will split instead of increasing if the
     * stock is bankrupt it won't increase or decrease in price the stocks that
     * weren't choosen get an increased chance to increase when they are next
     * choosen
     */
    private void event()
    {
        for (int x = 0; x < 2; x++)
        {
            Random ran = new Random();
            int stockNumber = ran.nextInt(6);
            int eventChance = ran.nextInt(101);
            /**
             * stock increases criteria
             */
            if (stocks.get(stockNumber).chance >= eventChance
                && stocks.get(stockNumber).price < 90
                && stocks.get(stockNumber).price != 0)
            {
                stocks.get(stockNumber).increase();
            }
            /**
             * stock split criteria
             */
            else if (stocks.get(stockNumber).chance >= eventChance
                && stocks.get(stockNumber).price == 90
                && stocks.get(stockNumber).price != 0)
            {
                stocks.get(stockNumber).split();
                for (int z = 0; z < players; z++)
                {
                    person.get(z).shares[stockNumber] =
                        person.get(z).shares[stockNumber] * 2;
                }
            }
            /**
             * stock decreases criteria
             */
            else if ((stocks.get(stockNumber).chance < eventChance && stocks
                .get(stockNumber).price != 0))
            {
                stocks.get(stockNumber).decrease();
            }
            /**
             * stock buffers if they didn't get altered
             */
            for (int y = 0; y < 6; y++)
            {
                if (y != stockNumber)
                {
                    stocks.get(y).buffer();
                }
            }
        }

    }


    /**
     * Turn is the players chance to buy and sell stocks
     */
    private void turn(int limit)
    {
        /**
         * this is a placeholder until an interactive screen for the player is
         * made
         *
         * *Once the interface is implemented change this the interact with*
         */
        ArrayList<Object> what = person.get(turn % players).ask();
        int actions = 0;
        while (actions < limit)
        {
            /**
             *buys the stock that was selected
             */
            if ((Boolean)what.get(1) == true)
            {
                person.get(turn % players)
                    .buy(stocks.get((Integer)what.get(0)));
                actions++;
            }
            /**
             *sells the stock that was selected
             */
            if ((Boolean)what.get(2) == true)
            {
                person.get(turn % players)
                    .sell(stocks.get((Integer)what.get(0)));
                actions++;
            }
            /**
             *ends turn
             */
            if ((Boolean)what.get(3) == true)
            {
                actions = limit;
            }
            if ((Boolean)what.get(4) == true)
            {
                int i= (Integer) what.get(5);
                recall(i);
            }
        }

    }


    /**
     * Ends the Game Bankrupts all the stocks and determines which player has
     * the highest cash holdings
     *
     * @return Players returns the winning PLayer
     */
    private Players endGame()
    {
        for (int x = 0; x < 6; x++)
        {
            stocks.get(x).end();
        }
        Players winner = person.get(0);
        for (int y = 1; y < players; y++)
        {
            if (person.get(y).worth() > winner.worth())
            {
                winner = person.get(y);
            }
        }
        return winner;

    }

    /**
     * reset the game to turn x
     * @param recall
     *  number of turns you want to go back
     */
    public void recall(int recall) {
        /**
         * checks to make sure you aren't going back
         * fartherer then there is data
         */
        if(recall<turn) {
        /**
         * goes back and loads the data
         */
        for (int a=0;a<recall;a++) {
            historyS.pop();
            historyP.pop();
        }
        stocks=historyS.peek();
        person=historyP.peek();
        turn=turn-recall;

        /**
         * after recalling the data the game starts being played again
         */
        while (turn != end)
        {
            turn(3);
            event();
            turn++;
            /**
             * saves the state of all the Players and Stocks
             * so that they can be recalled later
             */
            historyS.push(stocks);
            historyP.push(person);
        }


        /**
         * ends the game and returns the winner
         */
        endGame();}
    }
}
