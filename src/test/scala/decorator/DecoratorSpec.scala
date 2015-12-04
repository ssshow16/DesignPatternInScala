package decorator

import org.specs2._

class DecoratorSpec extends org.specs2.mutable.Specification {

  "Decorator" >> {

    "SideBorder display" >> {
    	val mixedDisplay = new StringDisplay("String Test") with SideBorder { val borderChar = "*"}
    	mixedDisplay.show
    	1 == 1
    }

    "FullBorder display" >> {
        val mixedDisplay = new StringDisplay("String Test") 
            with FullBorder { 
                val edgeChar = "+"
                val sideChar = "|"
                val upDownChar = "-"
            }
        mixedDisplay.show
        1 == 1
    }

     "SideBorder and UpDownBorder display" >> {
        trait StarUpDownBorder extends UpDownBorder{
            val upDownChar = "*"
        }

         trait PlusSideBorder extends SideBorder{
            val borderChar = "+"
        }

        val mixedDisplay = new StringDisplay("String Test") with PlusSideBorder with StarUpDownBorder
        mixedDisplay.show
        1 == 1
    }
  }
}
