package flyweight

object FlyWeightApp{
  
  def main(args:Array[String]){
    if(args == null || args.length == 0){
      println("Usage: sbt \"run-main flyweight.FlyWeightApp digit\"")
      println("Example: sbt \"run-main flyweight.FlyWeightApp 123123\"")
      System.exit(0)
    }
    new BigString(args(0)).print()
  }
}