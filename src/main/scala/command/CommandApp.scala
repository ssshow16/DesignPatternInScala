package command

import command._
import drawer._

import java.awt._
import java.awt.event._
import javax.swing._

class MainFrame(title:String) extends JFrame(title:String){

  val history = new MacroCommand()
  val canvas = new DrawCanvas(400,400,history)
  val clearButton = new JButton("clear")
  val undoButton = new JButton("undo")
  val changeColorButton = new JButton("change")
  
  var currentColor = Color.red

  def init(){
    this.addWindowListener(new InnerWindowAdapter())
    canvas.addMouseMotionListener(new InnerMouseMotionAdaper())
    clearButton.addActionListener(new InnerActionListener())
    undoButton.addActionListener(new InnerActionListener())
    changeColorButton.addActionListener(new InnerActionListener())

    val buttonBox = new Box(BoxLayout.X_AXIS)
    buttonBox.add(clearButton)
    buttonBox.add(undoButton)    
    buttonBox.add(changeColorButton)

    val mainBox = new Box(BoxLayout.Y_AXIS)
    mainBox.add(buttonBox)
    mainBox.add(canvas)
    getContentPane().add(mainBox)

    appendAndExecuteCommand(new ChangeColorCommand(canvas, Color.red))

    pack()
    setVisible(true)
  }
 
  class InnerActionListener extends ActionListener{
    def actionPerformed(e:ActionEvent){
      e.getSource match { 
        case `clearButton` => history.clear;canvas.repaint()
        case `undoButton` => history.undo;canvas.repaint()
        case `changeColorButton` => 
          val command = if(currentColor == Color.red) {
              currentColor = Color.blue
              new ChangeColorCommand(canvas, Color.blue)
            }else{
              currentColor = Color.red
              new ChangeColorCommand(canvas, Color.red)
            }
          appendAndExecuteCommand(command)
          canvas.repaint()
      }
    }
  }

  private def appendAndExecuteCommand(c:Command){
    history.append(c)
    c.execute
  }

  class InnerMouseMotionAdaper extends MouseMotionAdapter{
    override def mouseDragged(e:MouseEvent) {
      appendAndExecuteCommand(new DrawCommand(canvas, e.getPoint()))
    }
  }

  class InnerWindowAdapter extends WindowAdapter{ 
    override def windowClosing(x$1: java.awt.event.WindowEvent): Unit = {
      System.exit(0)
    }
  }

  init()
}

object CommandApp extends App {
  new MainFrame("Command Pattern Sample")
}