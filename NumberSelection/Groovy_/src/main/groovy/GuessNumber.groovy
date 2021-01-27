package main.groovy

class GuessNumber {
  static def guess(selectedNumber, input, output, attempts = 1) {
    def responses = ["Aim lower", "You guessed it in ${attempts} " + (attempts == 1 ? 'attempt' : 'attempts'), "Aim higher"]

    int guess_match = selectedNumber <=> input.call()

    output.call(responses[guess_match + 1])

    guess_match == 0
  }

  static def play(selectedNumber, input, output){
    (1..Integer.MAX_VALUE).find{attempts -> guess(selectedNumber, input, output, attempts)}
  }

}
