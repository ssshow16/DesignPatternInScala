package facade

import org.specs2._

class FacadeSpec extends org.specs2.mutable.Specification {

"FacadeSpec" >> {

  "Start " >> {

    FacadeClient.callFacade()

    1 == 1    
   }
  }
}
