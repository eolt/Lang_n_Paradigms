package main.groovy


class GuessNumberApp {
  static void main(String[] args) {
    println("I've selected a number, can you guess it")
     GuessNumber.play(new Random().nextInt() % 100 + 1, { print("Your guess: "); System.in.newReader().readLine() as Integer }, {response -> println(response)})
  }
}
