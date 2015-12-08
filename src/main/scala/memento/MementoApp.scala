package memento

import memento.game._

object MementoApp extends App{
  
  val gamer = new Gamer(100)
  var memento = gamer.createMemento

  for(i <- 0 until 30){

    println(s"==== $i")
    println(s"현 상태 $gamer")

    gamer.bet

    println(s"돈은 ${gamer.money} 원이 되었습니다.")

    if(gamer.money > memento.getMoney){
      println("  (많이 증가했으니, 현재의 상태를 보존해두자)")
      memento = gamer.createMemento
      println(memento.getState)
    }else if(gamer.money < memento.getMoney / 2){
      println("  (많이 줄었으니 이전의 상태로 복귀하자)")
      gamer.setMemento(memento)
    }

    Thread sleep 1000
  }
}