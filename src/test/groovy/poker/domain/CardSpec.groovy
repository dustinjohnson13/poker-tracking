package poker.domain

import spock.lang.Specification
import spock.lang.Unroll

import static poker.domain.Card.ACE_OF_CLUBS
import static poker.domain.Card.ACE_OF_DIAMONDS
import static poker.domain.Card.ACE_OF_HEARTS
import static poker.domain.Card.ACE_OF_SPADES
import static poker.domain.Card.EIGHT_OF_CLUBS
import static poker.domain.Card.EIGHT_OF_DIAMONDS
import static poker.domain.Card.EIGHT_OF_HEARTS
import static poker.domain.Card.EIGHT_OF_SPADES
import static poker.domain.Card.FIVE_OF_CLUBS
import static poker.domain.Card.FIVE_OF_DIAMONDS
import static poker.domain.Card.FIVE_OF_HEARTS
import static poker.domain.Card.FIVE_OF_SPADES
import static poker.domain.Card.FOUR_OF_CLUBS
import static poker.domain.Card.FOUR_OF_DIAMONDS
import static poker.domain.Card.FOUR_OF_HEARTS
import static poker.domain.Card.FOUR_OF_SPADES
import static poker.domain.Card.JACK_OF_CLUBS
import static poker.domain.Card.JACK_OF_DIAMONDS
import static poker.domain.Card.JACK_OF_HEARTS
import static poker.domain.Card.JACK_OF_SPADES
import static poker.domain.Card.KING_OF_CLUBS
import static poker.domain.Card.KING_OF_DIAMONDS
import static poker.domain.Card.KING_OF_HEARTS
import static poker.domain.Card.KING_OF_SPADES
import static poker.domain.Card.NINE_OF_CLUBS
import static poker.domain.Card.NINE_OF_DIAMONDS
import static poker.domain.Card.NINE_OF_HEARTS
import static poker.domain.Card.NINE_OF_SPADES
import static poker.domain.Card.QUEEN_OF_CLUBS
import static poker.domain.Card.QUEEN_OF_DIAMONDS
import static poker.domain.Card.QUEEN_OF_HEARTS
import static poker.domain.Card.QUEEN_OF_SPADES
import static poker.domain.Card.SEVEN_OF_CLUBS
import static poker.domain.Card.SEVEN_OF_DIAMONDS
import static poker.domain.Card.SEVEN_OF_HEARTS
import static poker.domain.Card.SEVEN_OF_SPADES
import static poker.domain.Card.SIX_OF_CLUBS
import static poker.domain.Card.SIX_OF_DIAMONDS
import static poker.domain.Card.SIX_OF_HEARTS
import static poker.domain.Card.SIX_OF_SPADES
import static poker.domain.Card.TEN_OF_CLUBS
import static poker.domain.Card.TEN_OF_DIAMONDS
import static poker.domain.Card.TEN_OF_HEARTS
import static poker.domain.Card.TEN_OF_SPADES
import static poker.domain.Card.THREE_OF_CLUBS
import static poker.domain.Card.THREE_OF_DIAMONDS
import static poker.domain.Card.THREE_OF_HEARTS
import static poker.domain.Card.THREE_OF_SPADES
import static poker.domain.Card.TWO_OF_CLUBS
import static poker.domain.Card.TWO_OF_DIAMONDS
import static poker.domain.Card.TWO_OF_HEARTS
import static poker.domain.Card.TWO_OF_SPADES

class CardSpec extends Specification {

    @Unroll
    def 'should parse card from text: #expected'() {

        def actual = Card.parse(text)

        expect:
        actual == expected

        where:
        text | expected
        'As' | ACE_OF_SPADES
        '2s' | TWO_OF_SPADES
        '3s' | THREE_OF_SPADES
        '4s' | FOUR_OF_SPADES
        '5s' | FIVE_OF_SPADES
        '6s' | SIX_OF_SPADES
        '7s' | SEVEN_OF_SPADES
        '8s' | EIGHT_OF_SPADES
        '9s' | NINE_OF_SPADES
        'Ts' | TEN_OF_SPADES
        'Js' | JACK_OF_SPADES
        'Qs' | QUEEN_OF_SPADES
        'Ks' | KING_OF_SPADES
        'Ac' | ACE_OF_CLUBS
        '2c' | TWO_OF_CLUBS
        '3c' | THREE_OF_CLUBS
        '4c' | FOUR_OF_CLUBS
        '5c' | FIVE_OF_CLUBS
        '6c' | SIX_OF_CLUBS
        '7c' | SEVEN_OF_CLUBS
        '8c' | EIGHT_OF_CLUBS
        '9c' | NINE_OF_CLUBS
        'Tc' | TEN_OF_CLUBS
        'Jc' | JACK_OF_CLUBS
        'Qc' | QUEEN_OF_CLUBS
        'Kc' | KING_OF_CLUBS
        'Ah' | ACE_OF_HEARTS
        '2h' | TWO_OF_HEARTS
        '3h' | THREE_OF_HEARTS
        '4h' | FOUR_OF_HEARTS
        '5h' | FIVE_OF_HEARTS
        '6h' | SIX_OF_HEARTS
        '7h' | SEVEN_OF_HEARTS
        '8h' | EIGHT_OF_HEARTS
        '9h' | NINE_OF_HEARTS
        'Th' | TEN_OF_HEARTS
        'Jh' | JACK_OF_HEARTS
        'Qh' | QUEEN_OF_HEARTS
        'Kh' | KING_OF_HEARTS
        'Ad' | ACE_OF_DIAMONDS
        '2d' | TWO_OF_DIAMONDS
        '3d' | THREE_OF_DIAMONDS
        '4d' | FOUR_OF_DIAMONDS
        '5d' | FIVE_OF_DIAMONDS
        '6d' | SIX_OF_DIAMONDS
        '7d' | SEVEN_OF_DIAMONDS
        '8d' | EIGHT_OF_DIAMONDS
        '9d' | NINE_OF_DIAMONDS
        'Td' | TEN_OF_DIAMONDS
        'Jd' | JACK_OF_DIAMONDS
        'Qd' | QUEEN_OF_DIAMONDS
        'Kd' | KING_OF_DIAMONDS
    }
}
