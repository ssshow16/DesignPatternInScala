package ch10

class Player(name:String, strategy:Strategy){

  var wincount:Int = 0
  var losecount:Int = 0
  var gamecount:Int = 0

  def nextHand = strategy.nextHand

  def win = {
    strategy.study(true)
    wincount = wincount + 1
    gamecount = gamecount + 1
  }

  def lose = {
    strategy.study(false)
    losecount = losecount + 1
    gamecount = gamecount + 1
  }

  def even = {
    gamecount = gamecount + 1
  }

  override def toString = s"$name:$gamecount games, $wincount win, $losecount lose ]"
}