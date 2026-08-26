class Solution {
    public int maxProfit(int[] prices) {
        int cheapest = prices[0];
        int profit = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < cheapest) {
                cheapest = prices[i];
            } else {
                profit = Math.max(profit, prices[i] - cheapest);
            }
        }

        return profit;
    }
}