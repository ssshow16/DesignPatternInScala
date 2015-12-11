package interpreter

object InterpreterApp extends App { 
  val node = new ProgramNode()
  node.parse(new Context("program repeat 4 repeat 3 go right go left end right end end"))
  println(s"node = ${node}")
}