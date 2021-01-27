import scala.math._

object RodCuttingSolution {
  def recursiveMaxPriceFor(length: Int, prices : Map[Int, Int]) : Int = {
     if (length <= 0 || prices.isEmpty) {
      return 0
    }

    (1 to length).map { cut => prices.getOrElse(cut, 0) + recursiveMaxPriceFor(length - cut, prices) }.max
  }

  def memoizationMaxPriceFor(length: Int, prices : Map[Int, Int]) : Int = {
    def computeMaxPrices(length:Int, maxPrices : Map[Int, Int] = Map()): Map[Int, Int] = {
      if (maxPrices.contains(length)) {
        return maxPrices
      }

      if (length == 0 || prices.isEmpty) {
        return maxPrices + (length -> 0)
      }
      
     (1 to length).foldLeft(maxPrices)((accumulatedMaxPrices, cut) => {
        val maxPricesComputed = computeMaxPrices(length - cut, accumulatedMaxPrices) ++ accumulatedMaxPrices 
        val maxPriceForPart = prices.getOrElse(cut, 0) + maxPricesComputed(length - cut)

         maxPricesComputed + (length -> max(maxPriceForPart, maxPricesComputed.getOrElse(length, 0)))
      })
    }

    computeMaxPrices(length)(length)
  }
}
