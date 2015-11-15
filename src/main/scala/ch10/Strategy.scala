package ch10

trait Strategy{
  def nextHand : Hand
  def study(win:Boolean)
}

class WinningStrategy(seed:Int) extends Strategy{

  import java.util.Random

  val random = new Random(seed)
  var won = false
  var prevHand:Hand = _

  def nextHand : Hand = {
    if(!won) { prevHand = Hand.getHand(random.nextInt(3)); prevHand }
    else prevHand
  }

  def study(win:Boolean) = won = win
}

class ProbStrategy(seed:Int) extends Strategy {

  import java.util.Random

  val random = new Random(seed)
  var prevHandValue = 0
  var currentHandValue = 0

  var history = Array(
    Array(1,1,1),
    Array(1,1,1),
    Array(1,1,1))

  def nextHand : Hand = {
    val bet = random.nextInt(getSum(currentHandValue))
    val handValue = 
      if(bet < history(currentHandValue)(0)) 0
      else if(bet < history(currentHandValue)(0) + history(currentHandValue)(1)) 1
      else 2

    prevHandValue = currentHandValue
    currentHandValue = handValue

    Hand.getHand(handValue)
  }

  def getSum(hv:Int) = history(hv).reduce(_+_)

  def study(win:Boolean) = 
    if(win) {
      history(prevHandValue)(currentHandValue) = history(prevHandValue)(currentHandValue)+1
    } else {
      history(prevHandValue)((currentHandValue + 1) % 3) = history(prevHandValue)((currentHandValue + 1) % 3)+1
      history(prevHandValue)((currentHandValue + 2) % 3) = history(prevHandValue)((currentHandValue + 2) % 3)+1
    }
}

class RandomStrategy(seed:Int) extends Strategy {
  import java.util.Random

  val random = new Random(seed)
  var won = false
  
  def nextHand : Hand = Hand.getHand(random.nextInt(3))
  def study(win:Boolean) = won = win 
}

