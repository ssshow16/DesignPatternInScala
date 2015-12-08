package mediator

import java.awt._
import java.awt.event._

trait Mediator{
	def createColleagues()
	def colleagueChanged(colleague:Colleague)
}

trait Colleague {
	def setMediator(mediator:Mediator)
	def setColleagueEnabled(enabled:Boolean)
}

class CalleadueButton(caption:String) extends Button(caption) with Colleague{
	var mediator:Mediator = null
	
	def setMediator(mediator:Mediator){
		this.mediator = mediator
	}

	def setColleagueEnabled(enabled:Boolean){
		setEnabled(enabled)
	}
}

class CalleagueTextField(text:String, columns:Int) 
	extends TextField(text,columns) with Colleague with TextListener {

	var mediator:Mediator = null
	
	def setMediator(mediator:Mediator){
		this.mediator = mediator
	}

	def setColleagueEnabled(enabled:Boolean){
		setEnabled(enabled)
		setBackground( if(enabled == true) Color.white else Color.lightGray)
	}

	def textValueChanged(e:TextEvent){
		mediator.colleagueChanged(this)
	}
}

class CalleagueCheckBox(caption:String, group:CheckboxGroup, state:Boolean) 
	extends Checkbox(caption,group,state) with Colleague with ItemListener {

	var mediator:Mediator = null
	
	def setMediator(mediator:Mediator){
		this.mediator = mediator
	}

	def setColleagueEnabled(enabled:Boolean){
		setEnabled(enabled)
	}

	def itemStateChanged(e:ItemEvent){
		mediator.colleagueChanged(this)
	}
}
