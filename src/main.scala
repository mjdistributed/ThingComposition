import thing._
import scala.util.Random


/**
what we want to do here is set up the system and let it loose
**/
object Main extends App {

	def setup(): World = {
		var atoms = Set[Atom]()
		for(i <- 0 to 100) {
			atoms = atoms + PositiveAtom(Location(0, 0, 0), Velocity(Helpers.randomInRange(3), Helpers.randomInRange(3), Helpers.randomInRange(3)))
	 		atoms = atoms + NegativeAtom(Location(0, 0, 0), Velocity(Helpers.randomInRange(3), Helpers.randomInRange(3), Helpers.randomInRange(3)))
		}
		World(atoms.toSeq:_*)
	}

	def simulate: World =  {
		val atoms: List[Atom] = setup().atoms.toList
		var movedAtoms = List[Atom]()
		atoms map (a => {
			var newA = a.move()
			movedAtoms = movedAtoms ++ List(newA)
		})
		World(movedAtoms.toSeq:_*)
	}

	println(simulate)

}

object Helpers {
	
	val random = new Random()

	/*
	 * Return a random number between -magnitude and +magnitude
	 */
	def randomInRange(magnitude: Double): Double = {

		val abs = (random.nextDouble() * magnitude)
		val posOrNeg = random.nextInt(2) //0 or 1
		if(posOrNeg == 0)
			abs
		else
			abs * -1
	}
}