/**
 * Thing: Defined as anything that replicates
 **/
sealed trait Thing {
	def replicate(): Thing
	def act()
}

/**
 * Made up of other things
 
case class CompositeThing(children: Thing*) extends Thing {

}
*/

case class TestThing extends Thing{
	def replicate(): Thing = {
		println("Test!")
		return this
	}

	def act() {

	}
}

/*
object Main extends App {
	var lastTick = System.currentTimeMillis
	val objects = List[Thing](TestThing(), TestThing())
	val numIters = 10
	var i = 0
	while(i < numIters) {
		//println(System.currentTimeMillis, lastTick)
		if (System.currentTimeMillis - lastTick > 1000) {
			objects map (_.replicate())
			lastTick = System.currentTimeMillis
			i += 1
		}
	}
}
*/