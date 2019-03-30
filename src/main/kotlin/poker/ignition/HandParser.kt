package poker.ignition

class HandParser {

    fun parse(hand: String): Hand {
        val id = "Hand #(\\d+)".toRegex().find(hand)!!.groups[1]!!.value.toLong()
        val seatMatches = "Seat (\\d+):(.*?)\\s\\(\\$(.*?)\\sin chips\\)".toRegex().findAll(hand)
        val seats = seatMatches.map {
            val seatNumber = it.groups[1]!!.value.toInt()
            val positionDescription = it.groups[2]!!.value.trim()
            val me = positionDescription.contains("[ME]")
            val position = if (positionDescription.contains("Big Blind")) {
                Position.BIG_BLIND;
            } else if (positionDescription.contains("Small Blind")) {
                Position.SMALL_BLIND
            } else if (positionDescription.contains("Dealer")) {
                Position.DEALER
            } else if (positionDescription.contains("UTG+2")) {
                Position.UTG_2
            } else if (positionDescription.contains("UTG+1")) {
                Position.UTG_1
            } else {
                Position.UTG
            }

            val stack = (it.groups[3]!!.value.toDouble() * 100).toLong()

            Seat(seatNumber, position, me, stack)
        }.toList()

        return Hand(id, seats)
    }
}