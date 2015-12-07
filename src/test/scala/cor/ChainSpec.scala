package cor

import org.specs2._

class ChainSpec extends org.specs2.mutable.Specification {

"ChainSpec" >> {

  "Start " >> {

    val alice = new NoSupport("alice")
    val bob = new LimitSupport("bob",100)
    val charlie = new SpecialSupport("charlie",429)
    val diana = new LimitSupport("diana",200)
    val elmo = new OddSupport("elmo")
    val fred = new LimitSupport("fred",300)

    alice.setNext(bob)
        .setNext(charlie)
        .setNext(diana)
        .setNext(elmo)
        .setNext(fred)

    for(i <- 0 until 500 by 33){
        alice.support(new Trouble(i))
    }

    1 == 1    
   }
  }
}
