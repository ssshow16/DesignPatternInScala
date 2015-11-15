package ch10

class Hand(v:Int){

  def handValue = v

  def fight(h:Hand):Int = {
    if(this == h) 0
    else if((this.handValue + 1) % 3 == h.handValue) 1
    else -1
  }

  def isStrongerThan(h:Hand) = fight(h) == 1
  def isWeakerThan(h:Hand) = fight(h) == -1

  override def toString():String = Hand.name(v)
}

object Hand{
  val HandValueGUU = 0
  val HandValueCHO = 1
  val HandValuePAA = 2

  val hand = Array(
    Hand(HandValueGUU), 
    Hand(HandValueCHO), 
    Hand(HandValuePAA))

  val name = Array("주먹","가위","보")

  def apply(handValue:Int) : Hand = new Hand(handValue)

  def getHand(handValue:Int) : Hand = hand(handValue)
}
