/*
 * Sliding window: `l` = best buy day, `r` scans forward. If `prices[r] > prices[l]`,
 * update max profit. Else move `l = r` (found a cheaper buy day — no point keeping
 * old left).
 */
class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        int l = 0, maxProfit = 0;
        for (int r = 1; r < prices.length; r++) {
            if (prices[r] > prices[l]) {
                maxProfit = Math.max(maxProfit, prices[r] - prices[l]);
            } else {
                l = r;
            }
        }
        return maxProfit;
    }
}
