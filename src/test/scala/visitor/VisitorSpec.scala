package visitor

import org.specs2._

class VisotorSpec extends org.specs2.mutable.Specification {

  "Visotor" >> {

    "Start" >> {
        val vi = new File("vi",1000)
        val latex = new File("latex",20000)

        val kim = new File("kim.html",100)
        val lee = new File("lee.html",200)

        val binDir = new Directory("bin",List(vi,latex))
        val tmpDir = new Directory("tmp",List(kim))
        val userDir = new Directory("usr",List(lee))
         
        val root = new Directory("root", List(binDir,tmpDir,userDir))

        root.accept(new ListVisitor())

        val ffv = new FileFindVisitor(".html")
        root.accept(ffv)
        ffv.foundFiles.foreach(f => println(f.name))

    	1 == 1 
    }
  }
}
