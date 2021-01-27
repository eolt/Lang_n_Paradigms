import GuessNumber._
import org.scalatest.flatspec.AnyFlatSpec

class GuessNumberTest extends AnyFlatSpec
{
  it should "guess correct on first attempt" in {
    val selectedNumber = 10
    var received = ""

    guess(selectedNumber, () => 10, (response:String) => received = response)
    assert(received == "You guessed it in 1 attempt")
  }

  it should "guess smaller on first attempt" in {
    val selectedNumber = 10
    var received = ""

    guess(selectedNumber, () => 9, (response:String) => received = response)
    assert(received == "Aim higher")
  }

  it should "guess higher on first attempt" in {
    val selectedNumber = 10
    var received = ""

    guess(selectedNumber, () => 11, (response:String) => received = response)
    assert(received == "Aim lower")
  }

  it should "guess correct on second attempt" in {
    val selectedNumber = 12
    val attempts = 2
    var received = ""

    guess(selectedNumber, () => 12, (response:String) => received = response, attempts)
    assert(received == "You guessed it in 2 attempts")
  }

  it should "return true for correct guess" in {
    val selectedNumber = 12

    assert(guess(selectedNumber, () => 12, _ => {}))
  }

  it should "return true on correct guess to any attempts" in {
    val selectedNumber = 15
    val attempts = 3

    assert(guess(selectedNumber, () => 15, _ => {}, attempts))
  }

  it should "return false on incorrect guess" in {
    val selectedNumber = 15
    val attempts = 4

    assert(!guess(selectedNumber, () => 1, _ => {}, attempts))
  }
  
  it should "play correct value on first attempt" in {
    val selectedNumber = 20
    var received = ""

    play(selectedNumber, () => 20, (response:String) => received = response)
    assert(received == "You guessed it in 1 attempt")
  }
  
  it should "play lower and then correct value on second attempt" in {
    val selectedNumber = 20
    var guesses = Array(15, 20)
    var received:Array[String] = Array()


    play(selectedNumber, () =>{ val input = guesses.head; guesses = guesses.filter(!_.equals(input)); input }, (response:String) => received = received ++ Array(response) )
    assert(received sameElements Array("Aim higher", "You guessed it in 2 attempts"))
  }

  it should "play higher and then correct value on second attempt" in {
    val selectedNumber = 15
    var guesses = Array(25, 15)
    var received:Array[String] = Array()


    play(selectedNumber, () => { val input = guesses.head; guesses = guesses.filter(!_.equals(input)); input }, (response:String) => received = received ++ Array(response) )
    assert(received sameElements Array("Aim lower", "You guessed it in 2 attempts"))
  }

  it should "play higher, higher, and then correct value on the third attempt" in {
    val selectedNumber = 15
    var guesses = Array(25, 20, 15)
    var received:Array[String] = Array()

    play(selectedNumber, () => { val input = guesses.head; guesses = guesses.filter(!_.equals(input)); input}, (response:String) => received = received ++ Array(response))
    assert(received sameElements Array("Aim lower", "Aim lower", "You guessed it in 3 attempts"))
  }

  it should "play lower, higher, and then correct value on the third attempt" in {
    val selectedNumber = 15
    var guesses = Array(5, 20, 15)
    var received:Array[String] = Array()

    play(selectedNumber, () => { val input = guesses.head; guesses = guesses.filter(!_.equals(input)); input}, (response:String) => received = received ++ Array(response))
    assert(received sameElements Array("Aim higher", "Aim lower", "You guessed it in 3 attempts"))
  }
}
