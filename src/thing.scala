package thing

/*
 * TODO: Every atom should have a location as well
 */
trait Atom {
	val location: Location
	val velocity: Velocity
	def combine(other: Atom): Atom
	def move(): Atom
}

/**
So we want to build up a system from the most basic thing
In order to do this, we will have at the base a binary structure
made up of only two things:
	- Positive Atom
	- Negative Atom
That will build up the rest of the system
**/

case class PositiveAtom(location: Location, velocity: Velocity) extends Atom {
	def combine(other: Atom): Atom = {
		other match {
			case PositiveAtom(loc, vel) => 	//don't combine
				this
			case NegativeAtom(loc, vel) =>
				if(loc == location)
					CompositeAtom(location, velocity, this, other)
				else
					this
			case _ =>
				this
		}
	}

	def move(): Atom = {
		PositiveAtom(Location(location.xPos + velocity.xVelocity, location.yPos + velocity.yVelocity, location.zPos + velocity.zVelocity), velocity)
	}

	override def toString(): String =  "PositiveAtom at " + location 
	
}

case class NegativeAtom(location: Location, velocity: Velocity) extends Atom {
	def combine(other: Atom): Atom = {
		other match {
			case NegativeAtom(loc, vel) => 	//don't combine
				this
			case PositiveAtom(loc, vel) =>
				if(loc == location)
					CompositeAtom(location, velocity, this, other)
				else
					this
			case _ =>
				this
		}
	}

	def move(): Atom = {
		PositiveAtom(Location(location.xPos + velocity.xVelocity, location.yPos + velocity.yVelocity, location.zPos + velocity.zVelocity), velocity)
	}

	override def toString(): String =  "NegativeAtom at " + location
}

case class CompositeAtom(location: Location, velocity: Velocity, children: Atom*) extends Atom {

	def combine(other: Atom): CompositeAtom = {
		other match {
			case PositiveAtom(loc, vel) =>
				if(loc == location)
					CompositeAtom(location, velocity, this, other)
				else
					this
			case NegativeAtom(loc, vel) =>
				if(loc == location)
					CompositeAtom(location, velocity, this, other)
				else
					this
			case _ =>
				this
		}
	}

	def move(): Atom = {
		PositiveAtom(Location(location.xPos + velocity.xVelocity, location.yPos + velocity.yVelocity, location.zPos + velocity.zVelocity), velocity)
	}

	override def toString(): String =  "CompositeAtom at " + location

}

case class World(atoms: Atom*) {
	
	override def toString(): String =  {
		atoms.foldLeft("")((a, b) =>  a + b.toString() + "\n")
	}	
}

case class Location(xPos: Double, yPos: Double, zPos: Double)

case class Velocity(xVelocity: Double, yVelocity: Double, zVelocity: Double)


