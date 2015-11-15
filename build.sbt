name := """DesignPatternInScala"""

version := "1.0"

scalaVersion := "2.11.7"

// Change this to another test framework if you prefer
libraryDependencies ++= Seq(
"org.scalatest" %% "scalatest" % "2.2.4" % "test",
"org.specs2" %% "specs2-core" % "3.6.5" % "test"
)
// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.11"

