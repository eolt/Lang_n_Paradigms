import RodCuttingSolution._
import org.scalatest.flatspec.AnyFlatSpec

trait RodCuttingTests extends AnyFlatSpec {

  def maxPriceFor(length:Int, prices: Map[Int, Int]) : Int
    
  it should "return max profit for length 0 where prices list is empty" in {
    assert(0 == maxPriceFor(0, Map()))
  }

  it should "return max profit for length 1 where price 1 is present" in {
    val prices = Map(1 -> 1)
    val length = 1
    assert(1 == maxPriceFor(length, prices))

  }

  it should "return max profit for length 2 where price 1 and 2 are present" in {
    val prices = Map(1 -> 1, 2 -> 5)
    val length = 2
    assert(5 == maxPriceFor(length, prices))
  }

  it should "return max profit for length 2 where prices 1 and 3 are present" in {
    val prices = Map(1 -> 1, 3 -> 8)
    val length = 2
    assert(2 == maxPriceFor(length, prices))
  }

  it should "return max profit for length 2 where price 1 is twice price 2" in {
    val prices = Map(1 -> 10, 2 -> 5)
    val length = 2
    assert(20 == maxPriceFor(length, prices))
  }

  it should "return max profit for length 5 where length 1, length 2, length 3 > length 5" in {
    val prices = Map(1 -> 10, 2 -> 5, 3 -> 8, 4 -> 9, 5 -> 1)
    val length = 5
    assert(50 == maxPriceFor(length, prices))
  }  
}

class RodCuttingRecursiveTest extends RodCuttingTests  {
  override def maxPriceFor(length: Int, prices: Map[Int, Int]) : Int  = recursiveMaxPriceFor(length, prices)
}

class RodCuttingMemoizationTest extends RodCuttingTests {
  override def maxPriceFor(length : Int, prices: Map[Int, Int]) : Int =  memoizationMaxPriceFor(length, prices)
  
   it should "return assert that the time for recursive is at least 10 times the time for memoized" in {
    def measureTime(computeMaxFunction : (Int, Map[Int, Int]) => Int) : Long = {
      val prices = Map(1 -> 10, 2 -> 5, 3 -> 8, 4 -> 9, 5 -> 1)
      val length = 20
        
      val start = System.nanoTime()
      computeMaxFunction(length, prices)
      System.nanoTime() - start
    }

    val recursiveRuntime = measureTime(recursiveMaxPriceFor)
    val memoizationRuntime = measureTime(memoizationMaxPriceFor)

    assert(recursiveRuntime > memoizationRuntime * 10)
  }
}
