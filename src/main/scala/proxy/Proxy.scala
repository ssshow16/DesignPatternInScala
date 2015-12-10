package proxy

trait Printable{
  def setPrinterName(name:String)
  def getPrinterName():String
  def print(string:String)
}

class Printer(var name:String) extends Printable{
  // 생성시간이 많이 걸림. 시뮬레이션
  heavyJob(s"Printer의 인스턴스(${name})을 생성중.")
  def setPrinterName(name:String) = {
    this.name = name
  }
  def getPrinterName():String = name
  def print(string:String){
    println(s"=== ${name} ===")
    println(string)
  }
  private def heavyJob(msg:String) = {
    println(msg)
    for(i <- 0 until 5) Thread sleep 1000
    println("완료.")
  }
}

class PrinterProxy(var name:String) extends Printable {
  /**
   * lazy loading이 되었는지 확인을 위한 flag
   */ 
  private var printerInitialized = false
  
  lazy val real:Printer = {
    printerInitialized = true
    new Printer(name)
  }
  /**
   * 가벼운 처리는 Proxy가 대행한다.
   */ 
  def setPrinterName(name:String) = {
    if(printerInitialized == true) real.setPrinterName(name)
    this.name = name
  }
  /**
   * 가벼운 처리는 Proxy가 대행한다.
   */ 
  def getPrinterName():String = name
  /**
   * 실제로 Heavy한 로직이 필요하면, 그 때 생성해서 처리하도록 한다. lazy loading
   */ 
  def print(string:String) { 
    real.print(string)
  }
}