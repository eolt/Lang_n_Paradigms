object GuessNumber {
  def guess(selectedNumber: Int, input: () => Int, output: String => Unit, attempts:Int = 1): Boolean = {
    val responses = Array("Aim lower", s"You guessed it in $attempts ${if (attempts == 1) "attempt" else "attempts"}", "Aim higher")
    
    val guessMatch = (selectedNumber compare input()).sign
    
    output(responses(guessMatch + 1))
    
    guessMatch == 0
  }
  
  def play(selectedNumber: Int, input:() => Int, output: String => Unit): Unit ={
    Iterator.from(1).find( attempts => guess(selectedNumber, input, output, attempts) )
  }
}
