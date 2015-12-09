package state

import java.awt._
import java.awt.event._

class SafeFrame(title:String) extends Frame(title) 
	with ActionListener with Context {

	val textColor = new TextField(60)
	val textScreen = new TextArea(10,60)
	val buttonUse = new Button("금고사용")
	val buttonAlarm = new Button("비상벨")
	val buttonPhone = new Button("일반통화")
	val buttonExit = new Button("종료")

	var fstate:State = DayState.getInstance

	def init(){
		setBackground(Color.lightGray)
		setLayout(new BorderLayout())
		add(textColor, BorderLayout.NORTH)
		textColor.setEditable(false)
		add(textScreen, BorderLayout.CENTER)
		textScreen.setEditable(false)

		val panel = new Panel()
		panel.add(buttonUse)
		panel.add(buttonAlarm)
		panel.add(buttonPhone)
		panel.add(buttonExit)
		
		add(panel, BorderLayout.SOUTH)

		pack()
		show()

		buttonUse.addActionListener(this)
		buttonAlarm.addActionListener(this)
		buttonPhone.addActionListener(this)
		buttonExit.addActionListener(this)			
	}

	def actionPerformed(e:ActionEvent) = {
		println(s"$e")

		// 현재 상태에 따라 각 버튼 액션에 대한 처리를 한다.
		if(e.getSource == buttonUse){
			fstate.doUse(this)
		} else if(e.getSource == buttonAlarm) {
			fstate.doAlarm(this)	
		} else if(e.getSource == buttonPhone) {
			fstate.doPhone(this)
		} else if(e.getSource == buttonExit) {
			System.exit(0)
		}
	}

  def setClock(hour:Int) {
  	val clockStr = "현재시작은" + {
  		if(hour < 10){
  			s"0${hour}:00"
  		}else{
  			s"${hour}:00"
  		}
  	}
  	println(s"${clockStr}")
  	textColor.setText(clockStr)
  	// 시간에 따라 DayState에서 NightState로 변경함
  	fstate.doClock(this,hour)
  }

  def changeState(state:State) {
  	println(s"${this.fstate}에서 ${state} 로 상태가 변경되었습니다.")
  	this.fstate = state
  }
  def callSecurityCenter(msg:String) {
  	textScreen.append(s"call! ${msg} \n")
  }
  def recordLog(msg:String) {
  	textScreen.append(s"record ... ${msg} \n")
  }

  init()
}

object StateApp extends App{

	val frame = new SafeFrame("State Sample")
	while(true){
		for ( i <- 0 until 24){
			frame.setClock(i)
			Thread sleep 1000
		}
	}

}