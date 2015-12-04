package composite

import org.specs2._

import composite.jstyle._

class CompositeSpec extends org.specs2.mutable.Specification {

  "Composite" >> {

    "Create File " >> {
    	val file = new File("test",0)
    	file.toString must_== "test (0)"
    }

    "File and Directory Composite" >> {

        val vi = new File("vi",1000)
        val latex = new File("latex",20000)

        val binDir = new Directory("bin",List(vi,latex))
        val tmpDir = new Directory("tmp",List())
        val userDir = new Directory("usr",List())
         
        val root = new Directory("root", List(binDir,tmpDir,userDir))

        Entry.println(root){
            s: String => System.out.println(s)
        }

        println(" - add new Entry")

        root.addEntry(new File("home",0))

        Entry.println(root){
            s: String => System.out.println(s)
        }

        1 == 1
    }   


   }
}
