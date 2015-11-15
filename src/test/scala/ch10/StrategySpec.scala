package ch10

import org.specs2._

class StrategySpec extends org.specs2.mutable.Specification {

  "this is Hand Specification" >> {

  	"where hand creation is " >> {
  		val hand = Hand.getHand(Hand.HandValueGUU)
  		hand.handValue must_== Hand.HandValueGUU
  	}
  }
}
