package builder

object BuilderPattern {
  sealed abstract class Preparation
  case object Neat extends Preparation
  case object OnTheRocks extends Preparation
  case object WithWater extends Preparation

  sealed abstract class Glass
  case object Short extends Glass
  case object Tall extends Glass
  case object Tulip extends Glass

  case class OrderOfScotch(val brand:String, val mode:Preparation, val isDouble:Boolean, val glass:Option[Glass])

  // Phantom Types이라 불림 
  // 타입으로만 사용되며, 절대 하위 클래스를 가질수 없다.
  abstract class TRUE
  abstract class FALSE

  class ScotchBuilder[HB, HM, HD](
  	val theBrand:Option[String], 
  	val theMode:Option[Preparation], 
  	val theDoubleStatus:Option[Boolean], 
  	val theGlass:Option[Glass]) {

    def withBrand(b:String) = 
      new ScotchBuilder[TRUE, HM, HD](Some(b), theMode, theDoubleStatus, theGlass)

    def withMode(p:Preparation) = 
      new ScotchBuilder[HB, TRUE, HD](theBrand, Some(p), theDoubleStatus, theGlass)

    def isDouble(b:Boolean) = 
      new ScotchBuilder[HB, HM, TRUE](theBrand, theMode, Some(b), theGlass)

    def withGlass(g:Glass) = new ScotchBuilder[HB, HM, HD](theBrand, theMode, theDoubleStatus, Some(g))
  }

  // brand, mode, double이 모두 TRUE일 경우만 enableBuild가 활성화 된다. 
  implicit def enableBuild(builder:ScotchBuilder[TRUE, TRUE, TRUE]) = new {
    def build() = 
      new OrderOfScotch(builder.theBrand.get, builder.theMode.get, builder.theDoubleStatus.get, builder.theGlass);
  }

  def builder = new ScotchBuilder[FALSE, FALSE, FALSE](None, None, None, None)
} 
