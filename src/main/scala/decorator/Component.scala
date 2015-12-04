package decorator

sealed abstract class Component{
	def draw
}

class EncapsulateTextView(c:TextView) extends Component { 
	def draw = c.draw
}

class TextView(var s:String) extends Component{
	def draw = println("Drawing.." + s)
}

trait BorderDecorator extends Component {
	abstract override def draw = { super.draw ; drawBorder}
	def drawBorder = println("Drawing_border")
}

trait ScrollDecorator extends Component { 
	abstract override def draw = { super.draw ; scrollTo }
	def scrollTo = println("Scrolling..")
}
