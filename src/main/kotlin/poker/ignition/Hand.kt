package poker.ignition

class Hand(val id: Long, val seats: List<Seat>) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Hand

        if (id != other.id) return false
        if (seats != other.seats) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + seats.hashCode()
        return result
    }

    override fun toString(): String {
        return "Hand(id=$id, seats=$seats)"
    }


}