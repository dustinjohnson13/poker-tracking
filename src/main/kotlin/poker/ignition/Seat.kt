package poker.ignition

class Seat(val number: Int, val position: Position, val me: Boolean, val stack: Long) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Seat

        if (number != other.number) return false
        if (position != other.position) return false
        if (me != other.me) return false
        if (stack != other.stack) return false

        return true
    }

    override fun hashCode(): Int {
        var result = number
        result = 31 * result + position.hashCode()
        result = 31 * result + me.hashCode()
        result = 31 * result + stack.hashCode()
        return result
    }

    override fun toString(): String {
        return "Seat(number=$number, position=$position, me=$me, stack=$stack)"
    }


}