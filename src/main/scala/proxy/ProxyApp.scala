package proxy

object ProxyAp extends App{
  val p = new PrinterProxy("Alice")
  println(s"이름은 현재${p.getPrinterName} 입니다.")
  p.setPrinterName("Bob")
  println(s"이름은 현재${p.getPrinterName} 입니다.")
  p.print("Hello, world.")  
}