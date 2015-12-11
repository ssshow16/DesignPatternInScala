package command

import scala.collection.mutable.Stack

trait Command {
  def execute
}

class MacroCommand extends Command{
  var commands = new Stack[Command]
  def execute = commands.foreach(_.execute)
  def append(c:Command) = commands.push(c)
  def undo = if(!commands.isEmpty) commands.pop
  def clear = commands.clear
}
