package flyweight

import scala.io.Source
import scala.collection.mutable.{Map,SynchronizedMap, HashMap}

trait FlyWeightFactory[T1,T2] extends Function[T1,T2]{
  /**
   * 한번 생성된 객체를 Pool에 저장을 하고, 
   * 같은 객체를 원하면 Pool에 저장되어 있는 객체를 전달한다.
   * Flyweight Patter은 한번 생성된 객체를 공유해서 쓰기위함
   */ 
  private var pool = new HashMap[T1, T2] with SynchronizedMap[T1, T2]
  def createFlyWeight(intrinsic:T1) : T2
  def apply(index:T1) : T2 = pool.getOrElseUpdate(index, createFlyWeight(index))
  def update(index:T1, elem:T2) = pool(index) = elem    
}

class BigChar(charname:Char){ 
  private val fontdata:String = initFontdata()
  private def initFontdata():String = 
    Source.fromFile(s"resource/flyweight/big${charname}.txt").getLines.mkString("\n")
  def print() = println(fontdata)
}

object BigCharFactory extends FlyWeightFactory[Char,BigChar] {
  def createFlyWeight(charname:Char) = new BigChar(charname)  
}

class BigString(string:String){
  private val bigchars:Array[BigChar] = init().toArray
  private def init():Seq[BigChar] = 
    for(i <- 0 until string.length)
      yield BigCharFactory(string(i))
  def print() = bigchars.foreach(_.print)
}