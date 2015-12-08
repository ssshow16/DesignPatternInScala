package memento.game

import scala.collection.mutable.ListBuffer
import java.util.Random


trait Originator {
	type T <: Memento
	def createMemento:T 
	def setMemento(m:T)
	trait Memento {
		def getState[Originator]
		def setState[Originator]
	}
}

class Gamer(var money:Int) extends Originator {
	type T = GameMemento

	val random = new Random
	var fruits = new ListBuffer[String]
	val fruitsName = Array("사과","포도","바나나","귤")

	class GameMemento extends Memento{
		var m:Int = _
		var f = new ListBuffer[String]
		def getMoney = m

		/**
		 * TODO : private 으로, How?
		 **/
		def getState[Originator] = {
			money = this.m
			fruits = this.f
		}
			
		/**
		 * TODO : private 으로, How?
		 **/
		def setState[Originator] = { 
			this.m = money
			fruits.filter(_.startsWith("맛있다")).map(this.f += _)
		}
	}

	def createMemento:GameMemento = {
		val m = new GameMemento()
		m.setState
		m
	}

	def setMemento(m:GameMemento) = m.getState

	def bet() {
		/**
		 * TODO : 각 조건에 따른 로직을 Lamda로 ??
		 **/
		val dice = random.nextInt(6) + 1		
		if(dice == 1){
			money += 100
			println("돈이 증가했습니다.")
		}else if(dice == 2){
			money /= 2
			println("돈이 반으로 줄어들었습니다.")
		}else if(dice == 6){
			val fruit = getFruit()
			println(s"과일($fruit)을 받았습니다.")
			fruits += fruit	
		}else{
			println("아무일도 일어나지 않았습니다.")
		}
	}

	def getFruit() : String = {
		val prefix = if(random.nextBoolean) "맛있다" else ""
		prefix + fruitsName(random.nextInt(fruitsName.length))
	}
	override def toString:String = s"[money = $money , fruits = $fruits ]"
}