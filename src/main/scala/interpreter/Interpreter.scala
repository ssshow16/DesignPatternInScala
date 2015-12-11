package interpreter

import scala.collection.mutable._

trait Node{
	def parse(context:Context)
}

class Context(text:String) {

	private val iterator = text.split(" ").iterator
	private var _currentToken:String = _
	
	// 첫 토큰으로 이동
	nextToken

	def nextToken:String = {
		if(iterator.hasNext) _currentToken = iterator.next
		else _currentToken = null

		_currentToken
	}
	def skipToken(token:String) {
		if(_currentToken != token) 
			throw new ParseException(s"Warning:${token} is expected, but ${_currentToken} is found.")
		nextToken
	}
	def currentToken:String = _currentToken
	def currentNumber:Int = 
		try{
			_currentToken.toInt
		} catch {
			case e:Exception => throw new ParseException("Warning: ${e}")
		}
}

class ParseException(msg:String) extends Exception(msg)

class ProgramNode() extends Node{
	private var commandListNode:Node = _
	def parse(context:Context){
		context.skipToken("program")
		commandListNode = new CommandListNode()
		commandListNode.parse(context)
	}
	override def toString = s"[program ${commandListNode}]"
}

//<command list> ::= <command>* end
class CommandListNode extends Node{
	private var list = new ListBuffer[Node]
	
	def parse(context:Context){
		def findEndOrNextCommand(){
			if(context.currentToken == null){
				throw new ParseException("Missing 'end'")
			} else if(context.currentToken == "end"){
				context.skipToken("end")
			} else { 
				val commandNode = new CommandNode()
				commandNode.parse(context)
				list += commandNode
				findEndOrNextCommand()
			}
		}
		findEndOrNextCommand()
	}
	override def toString = list mkString ","
}

// <command> ::= <repeat command> | <primitive command>
class CommandNode extends Node{
	private var node:Node = _
	def parse(context:Context) { 		
		node = context.currentToken match {
			case "repeat" => new RepeatCommandNode()
			case _ => new PrimitiveCommandNode()
		}
		node.parse(context)
	}
	override def toString = s"${node}"
}

// <repear command> ::= repear <number> <command list>
class RepeatCommandNode extends Node{
	private var number:Int = _
	private var commandListNode:Node = _
	def parse(context:Context) = {
		context.skipToken("repeat")
		number = context.currentNumber
		context.nextToken
		commandListNode = new CommandListNode()
		commandListNode.parse(context)
	}
	override def toString = s"[repeat ${number} ${commandListNode}]"
}

// <primitive command> ::= go | right | left
class PrimitiveCommandNode extends Node{
	private var name:String = _
	private val commands = Array("go","right","left")
	def parse(context:Context) = {
		name = context.currentToken
		context.skipToken(name)
		if(!commands.contains(name)) 
			throw new ParseException(s"${name} is undefined")
	}
	override def toString = name
}
