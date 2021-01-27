import org.junit.jupiter.api.Test
import static org.junit.jupiter.api.Assertions.*
import RodCuttingSolution

trait RodCuttingTest {
  abstract def maxPriceFor(length, prices)

  @Test
  void prices_list_empty_Test(){
    assertEquals(0, maxPriceFor(0, [:]))
    }

  @Test
  void prices_list_length_One_Test(){
    def prices = [1 : 1]
    int length = 1
    assertEquals(1, maxPriceFor(length, prices))
    }

  @Test
  void prices_list_length_one_and_two_Test(){
    def prices = [1 : 1, 2 : 5]
    int length = 2
    assertEquals(5, maxPriceFor(length, prices))
    }

  @Test
  void prices_list_length_one_and_tree_Test(){
    def prices = [1 : 1, 3 : 8]
    int length = 2
    assertEquals(2, maxPriceFor(length, prices))
    }

  @Test
  void prices_list_length_one_two_Length_one_greater_than_two_Test(){
    def prices = [1 : 10, 2 : 5]
    int length = 2
    assertEquals(20, maxPriceFor(length, prices))
    }

  @Test
  void prices_list_length_one_two_three_four_five_Length_five_less_than_one_two_three_Test(){
    def prices = [1 : 10, 2 : 5, 3 : 8, 4 : 9, 5 : 1]
    int length = 5
    assertEquals(50, maxPriceFor(length, prices))
    }
}

class RecursiveRodCuttingTest implements RodCuttingTest {
  @Override
  def maxPriceFor(Object length, Object prices) {
    return new RodCuttingSolution().recursiveMaxPrice(length, prices)
  }
}

class MemoizationRodCuttingTest implements RodCuttingTest {
  @Override
  def maxPriceFor(Object length, Object prices) {
    return new RodCuttingSolution().memoizationMaxPriceFor(length, prices)
  }
  
  @Test
    void measure_time_Recursive_and_Memoization_length_20_Test(){
      def measureTime = { Closure computeMaxFunction ->
        def prices = [1 : 10, 2 : 5, 3 : 8, 4 : 9, 5 : 1]
        int length = 20

        long start = System.nanoTime()
        computeMaxFunction(length, prices)
        System.nanoTime() - start
    }

    def recursiveRuntime = measureTime(new RodCuttingSolution().&recursiveMaxPrice)
    def memoizationRuntime = measureTime(this.&maxPriceFor)

    assertTrue(recursiveRuntime > memoizationRuntime)
  }
}
