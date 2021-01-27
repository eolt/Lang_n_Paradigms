package groovy

import main.groovy.GuessNumber
import org.junit.jupiter.api.Test
import static org.junit.jupiter.api.Assertions.*

class GuessNumberTest {
  @Test
  void guess_correct_on_first_attempt(){
    int selectedNumber = 10
    String received = ""

    GuessNumber.guess(selectedNumber, { 10 }, { response -> received = response })
    assertEquals(received, "You guessed it in 1 attempt")
  }

  @Test
  void guess_smaller_on_first_attempt() {
     int selectedNumber = 10
     String received = ""

      GuessNumber.guess(selectedNumber, { 9 }, { response -> received = response })
      assertEquals(received, "Aim higher") 
  }

  @Test
  void guess_higher_on_first_attempt(){
    int selectedNumber = 10
    String received = ""

    GuessNumber.guess(selectedNumber, { 11 }, { response -> received = response })
    assertEquals(received, "Aim lower")
  }

  @Test
  void guess_correct_on_second_attempt(){
    int selectedNumber = 12
    int attempts = 2
    String received = ""

    GuessNumber.guess(selectedNumber, { 12 }, { response -> received = response }, attempts)
    assertEquals(received, "You guessed it in 2 attempts")
  }

  @Test
  void return_true_for_correct_guess(){
    int selectedNumber = 12

    assertTrue(GuessNumber.guess(selectedNumber, { 12 }, { }))
  }

  @Test
  void return_true_on_correct_guess_on_any_attempt(){
    int selectedNumber = 15
    int attempts = 3

    assertTrue(GuessNumber.guess(selectedNumber, { 15 }, {  }, attempts))
  }
  
  @Test
  void return_false_on_incorrect_guess(){
    int selectedNumber = 15
    int attempts = 3

    assertFalse(GuessNumber.guess(selectedNumber, { 1 }, {  }, attempts))
  }
  
  @Test
  void play_correct_value_on_first_attempt(){
    int selectedNumber = 15
    String received = ""

    GuessNumber.play(selectedNumber, { 15 }, { response -> received = response})
    assertEquals(received, "You guessed it in 1 attempt")
  }

  @Test
  void play_lower_and_then_correct_value_on_second_attempt(){

   
    int selectedNumber = 20
    def guesses = [15, 20]
    def received = []

    GuessNumber.play(selectedNumber, { guesses.pop()  }, {response -> received.push(response)})
    assert(received == ["You guessed it in 2 attempts", "Aim higher" ])

  }


  @Test
  void play_higher_and_then_correct_value_on_second_attempt(){
     int selectedNumber = 20
    def guesses = [25, 20]
    def received = []

    GuessNumber.play(selectedNumber, { guesses.pop()  }, {response -> received.push(response)})
    assert(received == ["You guessed it in 2 attempts", "Aim lower" ])
  }

  @Test
  void play_higher_higher_and_then_correct_value_on_third_attempt(){
    int selectedNumber = 15
    def guesses = [25, 20, 15]
    def received = []

    GuessNumber.play(selectedNumber, { guesses.pop()  }, {response -> received.push(response)})
    assert(received == ["You guessed it in 3 attempts",  "Aim lower", "Aim lower" ])
  }

  @Test
  void play_lower_higher_and_then_correct_value_on_third_attempt(){
    int selectedNumber = 15
    def guesses = [5, 20, 15]
    def received = []

    GuessNumber.play(selectedNumber, { guesses.pop()  }, {response -> received.push(response)})
    assert(received == ["You guessed it in 3 attempts",  "Aim lower", "Aim higher" ])
  }
}
