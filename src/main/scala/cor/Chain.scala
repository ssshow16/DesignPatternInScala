package cor

case class Trouble(number:Int){
  override def toString() = s"[Trouble $number]"
}

abstract class Support(name:String){
  var next:Option[Support] = None
  
  def setNext(next:Support) : Support = {
    this.next = Some(next)
    this.next.get
  }

  final def support(trouble:Trouble):Unit = {
    if(resolve(trouble)){
      done(trouble)
    }else{
      next.map(n => n.support(trouble)).getOrElse(fail(trouble))
    }
  }

  protected def resolve(trouble:Trouble) : Boolean
  protected def done(trouble:Trouble):Unit = println(s"$trouble is resolved by $this.")
  protected def fail(trouble:Trouble):Unit = println(s"$trouble cannot be resolved.")

  override def toString() = s"[$name]"
}

class NoSupport(name:String) extends Support(name) { 
  override protected def resolve(trouble:Trouble) : Boolean = false
}

class LimitSupport(name:String, limit:Int) extends Support(name) {
  override protected def resolve(trouble:Trouble) : Boolean = 
    if(trouble.number < limit) true
    else false
}

class OddSupport(name:String) extends Support(name) {
  override protected def resolve(trouble:Trouble) : Boolean = 
    if(trouble.number % 2 == 1) true
    else false
}

class SpecialSupport(name:String, number:Int) extends Support(name) {
  override protected def resolve(trouble:Trouble) : Boolean = 
    if(trouble.number == number) true
    else false
}