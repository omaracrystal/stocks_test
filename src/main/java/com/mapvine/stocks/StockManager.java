package com.mapvine.stocks;

import com.mapvine.stocks.model.Stock;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
/* I am adding */
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IllegalFormatCodePointException;
import java.util.Map;

public class StockManager {

    List<Stock> results = new ArrayList<Stock>();
    String[] str=new String[100];
    int[] shr=new int[100];
    BigDecimal[] prc=new BigDecimal[100];
    int i=0,j=0;
    int share=0;
    double total=0;
    /**
     * Get the list of stocks currently under management by its ticker symbol.
     */
    public List<Stock> findByTicker(final String ticker) {
        // TODO: Implement me.
        List<Stock> r = new ArrayList<Stock>();
        for (Stock listItem : results) {
            if(listItem.getTicker().equalsIgnoreCase(ticker))
            {
                r.add(listItem);
            }
        }
        return r;
    }

    /**
     * For a given ticker symbol, add up the total stock values under management and return.
     */
    public BigDecimal getValueUnderManagerByTicker(final String ticker) {
        // TODO: Implement me.
        total=0;
        for(int i=0;i<j;i++) {
            if (str[i].equals(ticker.toLowerCase())) {
                total = total + prc[i].doubleValue() * shr[i];
            }
        }
        if(total==0)
        {
            BigDecimal bg=new BigDecimal(total);
            total=0;
            return bg;
        }
        else {
            BigDecimal bg = new BigDecimal(total).setScale(4, RoundingMode.HALF_EVEN);
            total = 0;
            return bg;
        }
    }

    /**
     * For a given ticker symbol, get the number of stocks under management.
     */
    public int numberOfStocksByTicker(final String ticker) {
        // TODO: Implement me.
        share=0;
        for(int i=0;i<j;i++) {
            if (str[i].equals(ticker.toLowerCase())) {
                share=share+shr[i];
            }
        }
        return share;
    }

    /**
     * Add a new stock to be managed by our system (here 'buy' is equal to 'add').
     *
     * @param stock          - The {@link com.mapvine.stocks.model.Stock} to buy
     * @param numberOfShares - The number of shares to purchase
     * @throws java.lang.IllegalArgumentException if numberOfShares is <= 0
     */
    public void buyStock(final Stock stock, final int numberOfShares) {
        // TODO: Implement me.
        if(numberOfShares<=0)
        {
            throw new IllegalArgumentException("There are currently no Shares to buy!");
        }
        else
        {
            results.add(stock);
            String stk=stock.getTicker().toLowerCase();
            BigDecimal price=stock.getPrice();
            str[j]=stk;
            shr[j]=numberOfShares;
            prc[j]=price;
            j++;
        }
    }

    /**
     * For a given ticker, sell the stock. The stock sold should be returned before being removed from management.
     * <p>
     * This method will attempt to sell stock starting with the lowest price.
     *
     * @param ticker         - The stock ticker to sell
     * @param numberOfShares - The number of shares to purchase
     * @param sharePrice     - The price we are selling numberOfShares
     * @return A list of the stock prices that were sold sorted from most to least expensive
     * @throws java.lang.IllegalArgumentException if numberOfShares is <= 0
     */
    public Optional<Set<BigDecimal>> sellStock(final String ticker, final int numberOfShares, final BigDecimal sharePrice) {
        // TODO: Implement me.
        if(numberOfShares<=0)
        {
            throw new IllegalArgumentException("Number of shares is less than or eqaul to zero");
        }
        else {
            share = 0;
            double price = 0;
            int chk=0,chk1=0;
            for (int i = 0; i < j; i++) {
                if (str[i].equals(ticker.toLowerCase())) {
                    share = share + shr[i];
                    price = price + prc[i].doubleValue();
                    chk++;
                    if(chk==2)
                    {
                        if(shr[i]>shr[i-1])
                        {
                            if(numberOfShares<shr[i-1])
                            {
                                int b;
                                b=shr[i-1]-numberOfShares;
                                shr[i-1]=b;
                            }
                            else if(numberOfShares>shr[i-1])
                            {
                                int b;
                                b=numberOfShares-shr[i-1];
                                shr[i-1]=b;
                                if(b!=0)
                                {
                                    shr[i]=shr[i]-b;
                                }
                            }
                        }
                        else if(shr[i]<shr[i-1])
                        {
                            if(numberOfShares<shr[i])
                            {
                                int b;
                                b=shr[i]-numberOfShares;
                                shr[i]=b;
                            }
                            else if(numberOfShares>shr[i])
                            {
                                int b;
                                b=numberOfShares-shr[i];
                                shr[i]=b;
                                if(b!=0)
                                {
                                    shr[i-1]=shr[i-1]-b;
                                }
                            }
                        }
                    }
                }
            }
            // share = share - numberOfShares;
            double pr = price - sharePrice.doubleValue();
            BigDecimal big = new BigDecimal(share);
            Set<BigDecimal> set = new HashSet<BigDecimal>();
            set.add(big);

            Optional<Set<BigDecimal>> s = Optional.of(set);
            return s;
        }
    }

    /**
     * For a given stock ticker, get the P&L.
     *
     * @return The total profit made so far or an empty option if the stock is not under management
     */
    public Optional<BigDecimal> getProfitForStockByTicker(final String ticker) {
        // TODO: Implement me.
        return null;

    }
}
