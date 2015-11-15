package ch10

object Main extends App{

	var seed1 = 1
	var seed2 = 2

	val player1 = new Player("홍길동",new WinningStrategy(seed1))
	val player2 = new Player("임꺽정",new ProbStrategy(seed2))

	for(i <- 0 to 100){
		val nextHand1 = player1.nextHand
		val nextHand2 = player2.nextHand

		if(nextHand1.isStrongerThan(nextHand2)){

			println("Winner: $player1")

			player1.win
			player2.lose
		} else if(nextHand2.isStrongerThan(nextHand1)){

			println("Winner: $player2")

			player2.win
			player1.lose
		} else {

			println("Even ...")

			player1.even
			player2.even
		}
	}

	println("Total Result :")
	println(s"$player1")
	println(s"$player2")
}