package mediator

import java.awt._
import java.awt.event._

class LoginFrame(title:String) extends Frame(title) with ActionListener with Mediator{

	var checkGuest:CalleagueCheckBox = null
	var checkLogin:CalleagueCheckBox = null
	var textUser:CalleagueTextField = null
	var textPass:CalleagueTextField = null
	var buttonOk:CalleadueButton = null
	var buttonCancel:CalleadueButton = null

	def init(){
		setBackground(Color.lightGray)
		setLayout(new GridLayout(4,2))
		createColleagues()
		add(checkGuest)
		add(checkLogin)
		add(new Label("Username:"))
		add(textUser)
		add(new Label("Password:"))
		add(textPass)
		add(buttonOk)
		add(buttonCancel)
		colleagueChanged(checkGuest)
		pack()
		show()
	}

	def createColleagues(){
		val g = new CheckboxGroup()
		checkGuest = new CalleagueCheckBox("Guest",g,true)
		checkLogin = new CalleagueCheckBox("Login",g,false)
		textUser = new CalleagueTextField("",10)
		textPass = new CalleagueTextField("",10)
		textPass.setEchoChar('*')
		buttonOk = new CalleadueButton("OK")
		buttonCancel = new CalleadueButton("Cancel")

		checkGuest.setMediator(this)
		checkLogin.setMediator(this)
		textUser.setMediator(this)
		textPass.setMediator(this)
		buttonOk.setMediator(this)
		buttonCancel.setMediator(this)

		checkGuest.addItemListener(checkGuest)
		checkLogin.addItemListener(checkLogin)
		textUser.addTextListener(textUser)
		textPass.addTextListener(textPass)
		buttonOk.addActionListener(this)
		buttonCancel.addActionListener(this)
	}

	def colleagueChanged(c:Colleague){
		if(c == checkGuest || c == checkLogin){
			if(checkGuest.getState()){
				textUser.setColleagueEnabled(false)
				textPass.setColleagueEnabled(false)
				buttonOk.setColleagueEnabled(true)
			} else {
				textUser.setColleagueEnabled(true)
				userpassChanged()
			}
		} else if(c == textUser || c == textPass){
			userpassChanged()
		} else {
			println(s"unknown colleague $c")
		}
	}

	def userpassChanged(){
		if(textUser.getText().length > 0){
			textPass.setColleagueEnabled(true)
			if(textPass.getText().length > 0){
				buttonOk.setColleagueEnabled(true)
			} else {
				buttonOk.setColleagueEnabled(false)
			}
		} else {
			textPass.setColleagueEnabled(false)
			buttonOk.setColleagueEnabled(false)
		}
	}

	def actionPerformed(e:ActionEvent){
		println(s"$e")
		System.exit(1)
	}

	init()
}

object MediatorApp extends App{
	new LoginFrame("Mediator Sample")
}
