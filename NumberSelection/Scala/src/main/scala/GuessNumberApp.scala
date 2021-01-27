import GuessNumber._
import scala.util.Random._
import scala.io.StdIn.readLine 

object GuessNumberApp extends App {
  println("I've selected a number, cam you guess it") 
    
  play(between(1, 101), () => { print(s"Your guess:"); readLine().toInt }, println)
}
