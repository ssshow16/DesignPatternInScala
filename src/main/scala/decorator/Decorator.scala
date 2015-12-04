package decorator

trait Display{
	def columns : Int
	def rows : Int
	def rowText(row:Int) : String
	final def show = for(i <- 0 until rows) println(rowText(i))
}

class StringDisplay(string:String) extends Display {
	def columns = string.getBytes().length
	def rows = 1
	def rowText(row:Int) = if(row == 0) string else null
}

trait SideBorder extends Display {
	val borderChar:String
	abstract override def columns = 1 + super.columns + 1
	abstract override def rows = super.rows
	abstract override def rowText(row:Int) = borderChar + super.rowText(row) + borderChar
}

trait UpDownBorder extends Display { 
	val upDownChar:String
	abstract override def columns = super.columns
	abstract override def rows = 1 + super.rows + 1
	abstract override def rowText(row:Int) = 
		if(row == 0) makeLine(upDownChar,super.columns)
		else if(row == super.rows + 1) makeLine(upDownChar,super.columns)
		else super.rowText(row - 1)

	def makeLine(ch:String, count:Int) = ch * count
}

trait FullBorder extends Display {
	val edgeChar:String
	val upDownChar:String
	val sideChar:String
	abstract override def columns = 1 + super.columns + 1
	abstract override def rows = 1 + super.rows + 1
	abstract override def rowText(row:Int) = {
		if(row == 0) "+" + makeLine(upDownChar,super.columns) + edgeChar
		else if(row == super.rows + 1) edgeChar + makeLine(upDownChar,super.columns) + edgeChar
		else sideChar + super.rowText(row - 1) + sideChar
	}
	def makeLine(ch:String, count:Int) = ch * count
}
