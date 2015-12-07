package visitor

import scala.collection.Iterator
import scala.collection.mutable.ListBuffer

abstract class Visitor{
	def visit(file:File)
	def visit(directory:Directory)
}

trait Acceptor {
	def accept(v:Visitor)
}

class FileTreatmentException extends RuntimeException{}

abstract class Entry extends Acceptor{
	def name:String
	def size:Int
	def add(entry:Entry):Entry = {
		throw new FileTreatmentException
	}
	def iterator:Iterator[Entry] = {
		throw new FileTreatmentException
	}
	override def toString() = name + " (" + size + ")"
}

class File(n:String, s:Int) extends Entry{
	def name = n
	def size = s
	def accept(v:Visitor) = v.visit(this)
}

class Directory(n:String, var children:List[Entry]) extends Entry{
	def name = n
	def size = children.map(_.size).sum
	override def iterator:Iterator[Entry] = children.iterator
	def accept(v:Visitor) = v.visit(this)
}

class ListVisitor extends Visitor{
	var currentDir = ""  // 하위 탐색시 이 값이 계속 변경됨.!!
	def visit(file:File) = println(s"$currentDir/$file")
	def visit(directory:Directory) = {
		println(s"$currentDir/$directory")
		val savedDir = currentDir
		currentDir = s"$currentDir/${directory.name}"
		directory.iterator.foreach(e => e.accept(this))
		currentDir = savedDir
	}
}

class FileFindVisitor(pattern:String) extends Visitor{
	var files = new ListBuffer[File]
	def foundFiles:Iterator[File] = files.iterator
	def visit(file:File) = {
		if(file.name.endsWith(pattern)) files += file
	}
	def visit(directory:Directory) = 
		directory.iterator.foreach(e => e.accept(this))
}


