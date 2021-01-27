class RodCuttingSolution {
  def recursiveMaxPrice(length, prices) {
    if (length == 0 || prices.isEmpty())
      return 0


    [1, length].collect{cut -> prices.get(cut, 0) + recursiveMaxPrice(length - cut, prices)}.max()
    }

  def memoizationMaxPriceFor(length, prices){

    def computeMaxPrices
    computeMaxPrices = { int len, def maxPrices = [:] ->

      if (maxPrices.containsKey(len))
        return maxPrices

      if (len == 0 || prices.isEmpty())
        return maxPrices + [(len as Number) : 0]

      [1, len].inject(maxPrices) { accumulatedMaxPrices, int cut ->
        def maxPricesComputed = computeMaxPrices(len - cut, accumulatedMaxPrices) + accumulatedMaxPrices
        int maxPriceForPart = prices.get(cut, 0) + maxPricesComputed.get(len - cut)

        maxPricesComputed + [(len as Number) : Math.max(maxPriceForPart, maxPricesComputed.get(len, 0)) ]
      }
    }
    computeMaxPrices(length).get(length)
  }
}
