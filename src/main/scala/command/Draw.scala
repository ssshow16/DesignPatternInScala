package drawer

import command._
import java.awt._
import java.awt.event._
import javax.swing._
import java.util._

trait Drawable {
  def draw(x:Int,y:Int)
  def changeColor(color:Color)
}

class ChangeColorCommand(drawable:Drawable, color:Color) extends Command{
  def execute = {
    drawable.changeColor(color)
    println(s"ChangeColorCommand ${color}")
  }
}

class DrawCommand(drawable:Drawable, position:Point) extends Command{
  def execute = {    
    drawable.draw(position.x, position.y)
    println(s"DrawCommand")
  }
}

class DrawCanvas(width:Int, height:Int, history:MacroCommand) extends Canvas with Drawable{
  private var color:Color = _
  private val radius:Int = 6
  def init(){
    setSize(width,height)
    setBackground(Color.white)
  }
  override def paint(g:Graphics){
    history.execute
    println("=============================")
  }
  def changeColor(color:Color){
    this.color = color
  }
  def draw(x:Int, y:Int){
    val g = getGraphics()
    g.setColor(this.color)
    g.fillOval(x - radius, y - radius, radius * 2, radius * 2)
  }
  init()
}
