package visitor

import org.specs2._

class VisotorSpec extends org.specs2.mutable.Specification {

  "Visotor" >> {

    "Start" >> {
        val vi = new File("vi",1000)
        val latex = new File("latex",20000)

        val binDir = new Directory("bin",List(vi,latex))
        val tmpDir = new Directory("tmp",List())
        val userDir = new Directory("usr",List())
         
        val root = new Directory("root", List(binDir,tmpDir,userDir))

        root.accept(new ListVisitor())

    	1 == 1 
    }
  }
}
