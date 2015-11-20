package ch11.jstyle

import scala.collection.mutable.ListBuffer

object Entry{

	def println(entry:Entry)(pr : String => Unit){

		def printlnList(entry:Entry, parent:String, paths:ListBuffer[String]): Unit = {
			entry match {
				case f @ File(name,size) => paths += f.getPath(parent)
				case d @ Directory(name,children) => {
					paths += d.getPathAndsize(parent)
					for{
						e <- children
						path = printlnList(e,d.getPath(parent),paths) // 
					} yield(path)
				}
			}
		}	

		val paths = new ListBuffer[String]
		
		printlnList(entry,"",paths)

		paths.foreach(path => pr(s"${path}"))
	}	
}

sealed abstract class Entry{
	def getName() : String
	def getSize() : Int
	def addEntry(entry:Entry)

	def getPath(parent:String) : String
	override def toString() : String = s"${getName} (${getSize})"
}

case class File(name:String, size:Int) extends Entry{

	def getName = name
	def getSize = size
	def addEntry(entry:Entry) = throw new Exception("FileTreatmentException")
	def getPath(parent:String) : String = s"$parent/$this"
}

case class Directory(name:String, var children:List[Entry]) extends Entry{
	def getName = name
	def getSize() = children.map(_.getSize).sum
	def addEntry(entry:Entry) = children = entry :: children
	def getPath(parent:String) : String = s"$parent/${getName}"
	def getPathAndsize(parent:String) : String = s"$parent/${this}"
}