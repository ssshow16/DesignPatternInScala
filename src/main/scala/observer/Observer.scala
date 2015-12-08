package observer

import scala.collection.mutable.ListBuffer
import java.util.Random

trait Subject[T]{
	self: T => // notifyObserver의 this의 대상을 지정한다.
	private val observers = new ListBuffer[T => Unit]
	def addObserver(observer:T => Unit) = observers += observer
	def deleteObserver(observer:T => Unit) = observers -= observer
	def notifyObserver() = observers.foreach(_(this))
}

// 관찰대상
abstract class NumberGenerator{
	def number:Int
	def execute
}

// 관찰대상과 Mix을 통해 Notify하기 위한 trait
// 관찰대상은 Oberserver 패턴과 상관없이 구현할 수 있으며, 이 trait를 통해 필요에 따라 Subject로 지정할 수 있음
trait RandomNumberGenerator extends NumberGenerator with Subject[NumberGenerator]{
	val random = new Random
	var randomNumber:Int = _
	def number = randomNumber
	def execute(){
		for(i <- 0 until 20){
			randomNumber = random.nextInt(50)
			notifyObserver()
		}
	}
}

class DigitObserver{
	def update(generator:NumberGenerator){
		println(s"DigitObserver: ${generator.number}")
		Thread sleep 100 
	}
}

class GraphObserver{
	def update(generator:NumberGenerator){
		println(s"GraphObserver:")
		for(i <- 0 until generator.number) print("*")
		println("")
		Thread sleep 100
	}
}

object ObserverApp extends App {
	val generator = new NumberGenerator with RandomNumberGenerator

	val observer1 = new DigitObserver
	val observer2 = new GraphObserver

	generator.addObserver(observer1.update)
	generator.addObserver(observer2.update)
	generator.execute
}


