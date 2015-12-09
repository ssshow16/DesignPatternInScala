package state

trait State {
  def doClock(context:Context, hour:Int)
  def doUse(context:Context)
  def doAlarm(context:Context)
  def doPhone(context:Context)
}

class DayState private() extends State {

  def doClock(context:Context, hour:Int) { 
    if(hour < 9 || 17 <= hour){
      context.changeState(NightState.getInstance)
    }
  }
  def doUse(context:Context) {
    context.recordLog("금고사용(주간)")
  }
  def doAlarm(context:Context) {
    context.callSecurityCenter("비상벨(주간)")
  }
  def doPhone(context:Context) {
    context.callSecurityCenter("일반통화(주간)")
  }
  override def toString() = "[주간]"
}

object DayState {
  private val instance:State = new DayState()
  def getInstance():State = instance
}

class NightState private() extends State {
   def doClock(context:Context, hour:Int) { 
    if(9 <= hour && hour < 17 ){
      context.changeState(DayState.getInstance)
    }
  }
  def doUse(context:Context) {
    context.callSecurityCenter("비상:야간의 금고사용")
  }
  def doAlarm(context:Context) {
    context.callSecurityCenter("비상벨(야간)")
  }
  def doPhone(context:Context) {
    context.callSecurityCenter("야간의 통화 녹음")
  }
  override def toString() = "[야간]"
}

object NightState {
  private val instance:State = new NightState()
  def getInstance:State = instance
}

trait Context{
  def setColor(hour:Int)
  def changeState(state:State)
  def callSecurityCenter(msg:String)
  def recordLog(msg:String)
}
