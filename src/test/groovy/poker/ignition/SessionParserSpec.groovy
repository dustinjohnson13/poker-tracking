package poker.ignition

import poker.domain.HoleCards
import spock.lang.Specification

import static java.time.LocalDateTime.of as ldt
import static java.time.Month.MARCH
import static java.time.Month.OCTOBER
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
import static poker.ignition.Position.BIG_BLIND
import static poker.ignition.Position.DEALER
import static poker.ignition.Position.SMALL_BLIND
import static poker.ignition.Position.UTG
import static poker.ignition.Position.UTG_1
import static poker.ignition.Position.UTG_2
import static poker.ignition.Position.UTG_3
import static poker.ignition.Position.UTG_4
import static poker.ignition.Position.UTG_5
import static poker.ignition.SessionType.RING
import static poker.ignition.SessionType.STT
import static poker.ignition.SessionType.ZONE

class SessionParserSpec extends Specification {

    def 'should parse a ring game session file correctly'() {

        def expectedHands = [
                new Hand(2577017255L, new Blinds(2L, 5L), [
                        new Seat(1, UTG_1, new HoleCards(EIGHT_OF_CLUBS, FOUR_OF_CLUBS), false, 737L, 22L, 0L),
                        new Seat(2, UTG_2, new HoleCards(THREE_OF_DIAMONDS, EIGHT_OF_SPADES), false, 554L, 0L, 0L),
                        new Seat(3, DEALER, new HoleCards(FIVE_OF_HEARTS, FIVE_OF_DIAMONDS), false, 532L, -17L, 0L),
                        new Seat(4, SMALL_BLIND, new HoleCards(JACK_OF_SPADES, THREE_OF_HEARTS), false, 478L, -2L, 0L),
                        new Seat(5, BIG_BLIND, new HoleCards(FOUR_OF_DIAMONDS, KING_OF_HEARTS), true, 500L, -5L, 0L),
                        new Seat(6, UTG, new HoleCards(TWO_OF_SPADES, FIVE_OF_CLUBS), false, 704L, 0L, 0L)]
                ),
                new Hand(2577017654L, new Blinds(2L, 5L), [
                        new Seat(1, UTG, new HoleCards(JACK_OF_SPADES, QUEEN_OF_HEARTS), false, 759L, -40L, 0L),
                        new Seat(2, UTG_1, new HoleCards(KING_OF_DIAMONDS, KING_OF_SPADES), false, 554L, 43L, 0L),
                        new Seat(4, DEALER, new HoleCards(TWO_OF_CLUBS, TEN_OF_HEARTS), false, 476L, 0L, 0L),
                        new Seat(5, SMALL_BLIND, new HoleCards(THREE_OF_DIAMONDS, ACE_OF_SPADES), true, 495L, -2L, 0L),
                        new Seat(6, BIG_BLIND, new HoleCards(JACK_OF_CLUBS, SIX_OF_HEARTS), false, 704L, -5L, 0L)
                ]),
                new Hand(2577018056L, new Blinds(2L, 5L), [
                        new Seat(1, BIG_BLIND, new HoleCards(FIVE_OF_HEARTS, KING_OF_HEARTS), false, 719L, -435L, 0L),
                        new Seat(2, UTG, new HoleCards(FIVE_OF_DIAMONDS, TEN_OF_HEARTS), false, 597L, 0L, 0L),
                        new Seat(3, UTG_1, new HoleCards(TEN_OF_CLUBS, FOUR_OF_CLUBS), false, 210L, -25L, 0L),
                        new Seat(4, UTG_2, new HoleCards(EIGHT_OF_SPADES, THREE_OF_DIAMONDS), false, 476L, 0L, 0L),
                        new Seat(5, DEALER, new HoleCards(JACK_OF_HEARTS, NINE_OF_CLUBS), true, 493L, 420L, 0L),
                        new Seat(6, SMALL_BLIND, new HoleCards(TWO_OF_DIAMONDS, TEN_OF_SPADES), false, 699L, -5L, 0L)
                ]),
                new Hand(2577018523L, new Blinds(2L, 5L), [
                        new Seat(1, SMALL_BLIND, new HoleCards(TEN_OF_DIAMONDS, JACK_OF_CLUBS), false, 284L, 172L, 0L),
                        new Seat(2, BIG_BLIND, new HoleCards(FIVE_OF_HEARTS, THREE_OF_SPADES), false, 597L, -5L, 0L),
                        new Seat(3, UTG, new HoleCards(ACE_OF_CLUBS, EIGHT_OF_HEARTS), false, 185L, -185L, 0L),
                        new Seat(4, UTG_1, new HoleCards(ACE_OF_HEARTS, JACK_OF_SPADES), false, 476L, 0L, 0L),
                        new Seat(5, UTG_2, new HoleCards(JACK_OF_DIAMONDS, THREE_OF_HEARTS), true, 913L, 0L, 0L),
                        new Seat(6, DEALER, new HoleCards(FOUR_OF_DIAMONDS, NINE_OF_SPADES), false, 694L, 0L, 0L)
                ]),
                new Hand(2577018859L, new Blinds(2L, 5L), [
                        new Seat(1, DEALER, new HoleCards(THREE_OF_DIAMONDS, SEVEN_OF_SPADES), false, 456L, 0L, 0L),
                        new Seat(2, SMALL_BLIND, new HoleCards(QUEEN_OF_SPADES, EIGHT_OF_HEARTS), false, 592L, -2L, 0L),
                        new Seat(4, BIG_BLIND, new HoleCards(SIX_OF_DIAMONDS, NINE_OF_SPADES), false, 476L, -5L, 0L),
                        new Seat(5, UTG, new HoleCards(TEN_OF_SPADES, JACK_OF_DIAMONDS), true, 913L, 7L, 0L),
                        new Seat(6, UTG_1, new HoleCards(TWO_OF_DIAMONDS, FOUR_OF_HEARTS), false, 694L, 0L, 0L)
                ]),
                new Hand(2577019088L, new Blinds(2L, 5L), [
                        new Seat(1, UTG_1, new HoleCards(EIGHT_OF_SPADES, TEN_OF_HEARTS), false, 456L, 27L, 0L),
                        new Seat(2, DEALER, new HoleCards(ACE_OF_CLUBS, JACK_OF_SPADES), false, 590L, -22L, 0L),
                        new Seat(4, SMALL_BLIND, new HoleCards(TWO_OF_HEARTS, THREE_OF_DIAMONDS), false, 471L, -2L, 0L),
                        new Seat(5, BIG_BLIND, new HoleCards(EIGHT_OF_DIAMONDS, FIVE_OF_DIAMONDS), true, 920L, -5L, 0L),
                        new Seat(6, UTG, new HoleCards(NINE_OF_CLUBS, THREE_OF_HEARTS), false, 694L, 0L, 0L)
                ]),
                new Hand(2577019337L, new Blinds(2L, 5L), [
                        new Seat(1, UTG, new HoleCards(FIVE_OF_HEARTS, THREE_OF_CLUBS), false, 483L, 0L, 0L),
                        new Seat(2, UTG_1, new HoleCards(KING_OF_CLUBS, TWO_OF_DIAMONDS), false, 568L, 0L, 0L),
                        new Seat(3, UTG_2, new HoleCards(THREE_OF_HEARTS, SEVEN_OF_DIAMONDS), false, 500L, -5L, 0L),
                        new Seat(4, DEALER, new HoleCards(JACK_OF_HEARTS, TWO_OF_HEARTS), false, 469L, 0L, 0L),
                        new Seat(5, SMALL_BLIND, new HoleCards(SIX_OF_SPADES, QUEEN_OF_DIAMONDS), true, 915L, -25L, 0L),
                        new Seat(6, BIG_BLIND, new HoleCards(ACE_OF_DIAMONDS, NINE_OF_SPADES), false, 694L, 28L, 0L)
                ]),
                new Hand(2577019658L, new Blinds(2L, 5L), [
                        new Seat(1, BIG_BLIND, new HoleCards(QUEEN_OF_CLUBS, TEN_OF_SPADES), false, 483L, 15L, 0L),
                        new Seat(2, UTG, new HoleCards(SIX_OF_CLUBS, JACK_OF_SPADES), false, 568L, 0L, 0L),
                        new Seat(3, UTG_1, new HoleCards(QUEEN_OF_SPADES, TWO_OF_CLUBS), false, 495L, -5L, 0L),
                        new Seat(4, UTG_2, new HoleCards(QUEEN_OF_HEARTS, EIGHT_OF_DIAMONDS), false, 469L, 0L, 0L),
                        new Seat(5, DEALER, new HoleCards(KING_OF_DIAMONDS, SIX_OF_DIAMONDS), true, 890L, -5L, 0L),
                        new Seat(6, SMALL_BLIND, new HoleCards(JACK_OF_HEARTS, THREE_OF_CLUBS), false, 722L, -5L, 0L)
                ]),
                new Hand(2577019862L, new Blinds(2L, 5L), [
                        new Seat(1, SMALL_BLIND, new HoleCards(NINE_OF_SPADES, NINE_OF_HEARTS), false, 498L, -15L, 0L),
                        new Seat(2, BIG_BLIND, new HoleCards(FOUR_OF_HEARTS, EIGHT_OF_SPADES), false, 568L, -5L, 0L),
                        new Seat(3, UTG, new HoleCards(SEVEN_OF_DIAMONDS, EIGHT_OF_DIAMONDS), false, 490L, -35L, 0L),
                        new Seat(4, UTG_1, new HoleCards(KING_OF_CLUBS, QUEEN_OF_CLUBS), false, 469L, 51L, 0L),
                        new Seat(5, UTG_2, new HoleCards(SIX_OF_HEARTS, QUEEN_OF_SPADES), true, 885L, 0L, 0L),
                        new Seat(6, DEALER, new HoleCards(SEVEN_OF_SPADES, THREE_OF_HEARTS), false, 717L, 0L, 0L)
                ]),
                new Hand(2577020350L, new Blinds(2L, 5L), [
                        new Seat(1, DEALER, new HoleCards(SIX_OF_HEARTS, NINE_OF_SPADES), false, 483L, 10L, 0L),
                        new Seat(2, SMALL_BLIND, new HoleCards(SEVEN_OF_HEARTS, FIVE_OF_CLUBS), false, 563L, -5L, 0L),
                        new Seat(3, BIG_BLIND, new HoleCards(JACK_OF_SPADES, FOUR_OF_SPADES), false, 455L, -5L, 0L),
                        new Seat(4, UTG, new HoleCards(KING_OF_HEARTS, JACK_OF_HEARTS), false, 520L, 0L, 0L),
                        new Seat(5, UTG_1, new HoleCards(QUEEN_OF_HEARTS, EIGHT_OF_SPADES), true, 885L, 0L, 0L),
                        new Seat(6, UTG_2, new HoleCards(JACK_OF_CLUBS, TWO_OF_SPADES), false, 717L, 0L, 0L)
                ]),
                new Hand(2577020789L, new Blinds(2L, 5L), [
                        new Seat(1, UTG_2, new HoleCards(TWO_OF_SPADES, THREE_OF_DIAMONDS), false, 493L, 37L, 0L),
                        new Seat(2, DEALER, new HoleCards(FIVE_OF_CLUBS, THREE_OF_CLUBS), false, 558L, -17L, 0L),
                        new Seat(3, SMALL_BLIND, new HoleCards(QUEEN_OF_HEARTS, NINE_OF_CLUBS), false, 450L, -17L, 0L),
                        new Seat(4, BIG_BLIND, new HoleCards(JACK_OF_HEARTS, EIGHT_OF_SPADES), false, 520L, -5L, 0L),
                        new Seat(5, UTG, new HoleCards(FOUR_OF_CLUBS, JACK_OF_DIAMONDS), true, 885L, 0L, 0L),
                        new Seat(6, UTG_1, new HoleCards(SEVEN_OF_HEARTS, TEN_OF_DIAMONDS), false, 717L, 0L, 0L)
                ]),
                new Hand(2577021229L, new Blinds(2L, 5L), [
                        new Seat(1, UTG_1, new HoleCards(SEVEN_OF_SPADES, THREE_OF_SPADES), false, 530L, -27L, 0L),
                        new Seat(2, UTG_2, new HoleCards(QUEEN_OF_SPADES, EIGHT_OF_HEARTS), false, 541L, 0L, 0L),
                        new Seat(3, DEALER, new HoleCards(FOUR_OF_DIAMONDS, TEN_OF_DIAMONDS), false, 433L, 31L, 0L),
                        new Seat(4, SMALL_BLIND, new HoleCards(KING_OF_SPADES, JACK_OF_DIAMONDS), false, 515L, -2L, 0L),
                        new Seat(5, BIG_BLIND, new HoleCards(SEVEN_OF_CLUBS, FIVE_OF_DIAMONDS), true, 885L, -5L, 0L),
                        new Seat(6, UTG, new HoleCards(SIX_OF_HEARTS, TWO_OF_CLUBS), false, 717L, 0L, 0L)
                ]),
                new Hand(2577021449L, new Blinds(2L, 5L), [
                        new Seat(1, UTG, new HoleCards(EIGHT_OF_SPADES, SIX_OF_SPADES), false, 503L, 7L, 0L),
                        new Seat(2, UTG_1, new HoleCards(JACK_OF_CLUBS, THREE_OF_CLUBS), false, 541L, 0L, 0L),
                        new Seat(3, UTG_2, new HoleCards(THREE_OF_DIAMONDS, SIX_OF_HEARTS), false, 464L, 0L, 0L),
                        new Seat(4, DEALER, new HoleCards(KING_OF_HEARTS, SEVEN_OF_DIAMONDS), false, 513L, 0L, 0L),
                        new Seat(5, SMALL_BLIND, new HoleCards(ACE_OF_CLUBS, SIX_OF_CLUBS), true, 880L, -2L, 0L),
                        new Seat(6, BIG_BLIND, new HoleCards(FOUR_OF_DIAMONDS, KING_OF_CLUBS), false, 717L, -5L, 0L)
                ]),
                new Hand(2577021587L, new Blinds(2L, 5L), [
                        new Seat(1, BIG_BLIND, new HoleCards(TEN_OF_SPADES, FIVE_OF_HEARTS), false, 510L, -5L, 0L),
                        new Seat(2, UTG, new HoleCards(EIGHT_OF_DIAMONDS, QUEEN_OF_HEARTS), false, 541L, 0L, 0L),
                        new Seat(3, UTG_1, new HoleCards(KING_OF_DIAMONDS, ACE_OF_DIAMONDS), false, 464L, -274L, 0L),
                        new Seat(4, UTG_2, new HoleCards(FOUR_OF_CLUBS, ACE_OF_CLUBS), false, 513L, -15L, 0L),
                        new Seat(5, DEALER, new HoleCards(SIX_OF_DIAMONDS, TEN_OF_HEARTS), true, 878L, 0L, 0L),
                        new Seat(6, SMALL_BLIND, new HoleCards(SEVEN_OF_SPADES, SEVEN_OF_CLUBS), false, 712L, 266L, 0L)
                ]),
                new Hand(2577022015L, new Blinds(2L, 5L), [
                        new Seat(1, SMALL_BLIND, new HoleCards(JACK_OF_CLUBS, TWO_OF_CLUBS), false, 505L, 4L, 0L),
                        new Seat(2, BIG_BLIND, new HoleCards(FIVE_OF_DIAMONDS, JACK_OF_HEARTS), false, 541L, 5L, 0L),
                        new Seat(3, UTG, new HoleCards(FOUR_OF_SPADES, NINE_OF_SPADES), false, 190L, -10L, 0L),
                        new Seat(4, UTG_1, new HoleCards(KING_OF_SPADES, THREE_OF_DIAMONDS), false, 498L, 0L, 0L),
                        new Seat(5, UTG_2, new HoleCards(TEN_OF_HEARTS, FOUR_OF_CLUBS), true, 878L, 0L, 0L),
                        new Seat(6, DEALER, new HoleCards(SIX_OF_DIAMONDS, QUEEN_OF_SPADES), false, 978L, 0L, 0L)
                ]),
                new Hand(2577022336L, new Blinds(2L, 5L), [
                        new Seat(1, DEALER, new HoleCards(JACK_OF_HEARTS, KING_OF_HEARTS), false, 509L, 70L, 0L),
                        new Seat(2, SMALL_BLIND, new HoleCards(TEN_OF_SPADES, EIGHT_OF_HEARTS), false, 546L, -2L, 0L),
                        new Seat(3, BIG_BLIND, new HoleCards(THREE_OF_SPADES, FOUR_OF_DIAMONDS), false, 180L, -5L, 0L),
                        new Seat(4, UTG, new HoleCards(ACE_OF_DIAMONDS, SIX_OF_CLUBS), false, 498L, 0L, 0L),
                        new Seat(5, UTG_1, new HoleCards(FOUR_OF_HEARTS, FOUR_OF_CLUBS), true, 878L, -70L, 0L),
                        new Seat(6, UTG_2, new HoleCards(SEVEN_OF_SPADES, QUEEN_OF_CLUBS), false, 978L, 0L, 0L)
                ]),
                new Hand(2577022716L, new Blinds(2L, 5L), [
                        new Seat(1, UTG_2, new HoleCards(QUEEN_OF_HEARTS, ACE_OF_HEARTS), false, 579L, -74L, 0L),
                        new Seat(2, DEALER, new HoleCards(QUEEN_OF_SPADES, QUEEN_OF_DIAMONDS), false, 544L, 74L, 0L),
                        new Seat(3, SMALL_BLIND, new HoleCards(THREE_OF_HEARTS, TWO_OF_HEARTS), false, 175L, -2L, 0L),
                        new Seat(4, BIG_BLIND, new HoleCards(FIVE_OF_DIAMONDS, TEN_OF_SPADES), false, 498L, -5L, 0L),
                        new Seat(5, UTG, new HoleCards(SIX_OF_HEARTS, TEN_OF_CLUBS), true, 808L, 0L, 0L),
                        new Seat(6, UTG_1, new HoleCards(FIVE_OF_HEARTS, NINE_OF_SPADES), false, 978L, 0L, 0L)
                ]),
                new Hand(2577022985L, new Blinds(2L, 5L), [
                        new Seat(1, UTG_1, new HoleCards(SEVEN_OF_CLUBS, THREE_OF_CLUBS), false, 505L, -15L, 0L),
                        new Seat(2, UTG_2, new HoleCards(JACK_OF_HEARTS, TWO_OF_CLUBS), false, 618L, 0L, 0L),
                        new Seat(3, DEALER, new HoleCards(EIGHT_OF_SPADES, QUEEN_OF_CLUBS), false, 173L, -15L, 0L),
                        new Seat(4, SMALL_BLIND, new HoleCards(SIX_OF_CLUBS, KING_OF_SPADES), false, 493L, -2L, 0L),
                        new Seat(5, BIG_BLIND, new HoleCards(NINE_OF_CLUBS, QUEEN_OF_HEARTS), true, 808L, -40L, 0L),
                        new Seat(6, UTG, new HoleCards(ACE_OF_CLUBS, ACE_OF_HEARTS), false, 978L, 67L, 0L)
                ]),
                new Hand(2577023411L, new Blinds(2L, 5L), [
                        new Seat(1, UTG, new HoleCards(TEN_OF_HEARTS, FOUR_OF_SPADES), false, 490L, 0L, 0L),
                        new Seat(2, UTG_1, new HoleCards(EIGHT_OF_SPADES, THREE_OF_CLUBS), false, 618L, 0L, 0L),
                        new Seat(3, UTG_2, new HoleCards(FIVE_OF_CLUBS, KING_OF_DIAMONDS), false, 158L, -20L, 0L),
                        new Seat(4, DEALER, new HoleCards(QUEEN_OF_DIAMONDS, QUEEN_OF_HEARTS), false, 491L, -116L, 0L),
                        new Seat(5, SMALL_BLIND, new HoleCards(ACE_OF_SPADES, ACE_OF_CLUBS), true, 768L, 129L, 0L),
                        new Seat(6, BIG_BLIND, new HoleCards(SIX_OF_DIAMONDS, NINE_OF_CLUBS), false, 1045L, -5L, 0L)
                ]),
                new Hand(2577023960L, new Blinds(2L, 5L), [
                        new Seat(1, BIG_BLIND, new HoleCards(SIX_OF_SPADES, JACK_OF_HEARTS), false, 490L, -5L, 0L),
                        new Seat(2, UTG, new HoleCards(JACK_OF_DIAMONDS, ACE_OF_HEARTS), false, 618L, 7L, 0L),
                        new Seat(3, UTG_1, new HoleCards(FOUR_OF_DIAMONDS, TWO_OF_SPADES), false, 138L, 0L, 0L),
                        new Seat(4, UTG_2, new HoleCards(NINE_OF_DIAMONDS, EIGHT_OF_CLUBS), false, 375L, 0L, 0L),
                        new Seat(5, DEALER, new HoleCards(THREE_OF_CLUBS, TEN_OF_CLUBS), true, 897L, 0L, 0L),
                        new Seat(6, SMALL_BLIND, new HoleCards(FIVE_OF_CLUBS, SEVEN_OF_HEARTS), false, 1040L, -2L, 0L)
                ]),
                new Hand(2577024178L, new Blinds(2L, 5L), [
                        new Seat(1, SMALL_BLIND, new HoleCards(QUEEN_OF_HEARTS, FOUR_OF_SPADES), false, 485L, -2L, 0L),
                        new Seat(2, BIG_BLIND, new HoleCards(ACE_OF_CLUBS, FIVE_OF_CLUBS), false, 625L, -15L, 0L),
                        new Seat(3, UTG, new HoleCards(JACK_OF_HEARTS, SIX_OF_CLUBS), false, 138L, -15L, 0L),
                        new Seat(4, UTG_1, new HoleCards(QUEEN_OF_DIAMONDS, ACE_OF_SPADES), false, 375L, -15L, 140L),
                        new Seat(5, UTG_2, new HoleCards(EIGHT_OF_SPADES, ACE_OF_DIAMONDS), true, 897L, -109L, 0L),
                        new Seat(6, DEALER, new HoleCards(EIGHT_OF_DIAMONDS, TEN_OF_DIAMONDS), false, 1038L, 143L, 0L)
                ]),
                new Hand(2577024771L, new Blinds(2L, 5L), [
                        new Seat(1, DEALER, new HoleCards(EIGHT_OF_HEARTS, JACK_OF_CLUBS), false, 483L, 0L, 0L),
                        new Seat(2, SMALL_BLIND, new HoleCards(FIVE_OF_SPADES, EIGHT_OF_DIAMONDS), false, 610L, -17L, 0L),
                        new Seat(3, BIG_BLIND, new HoleCards(ACE_OF_CLUBS, FOUR_OF_SPADES), false, 123L, 32L, 0L),
                        new Seat(4, UTG, new HoleCards(TWO_OF_HEARTS, SEVEN_OF_SPADES), false, 500L, 0L, 0L),
                        new Seat(5, UTG_1, new HoleCards(TEN_OF_SPADES, QUEEN_OF_SPADES), true, 788L, -17L, 0L),
                        new Seat(6, UTG_2, new HoleCards(JACK_OF_SPADES, EIGHT_OF_CLUBS), false, 1181L, 0L, 0L)]
                )]

        def filename = 'HH20190322-143439 - 3568450 - RING - $0.02-$0.05 - HOLDEM - NL - TBL No.11131131.txt'
        def actual = new SessionParser().parse(filename, SessionParserSpec.getResourceAsStream(filename).text)

        expect:
        expectedHands.eachWithIndex { Hand expectedHand, int idx ->
            assert actual.hands[idx] == expectedHand
        }

        actual == new Session('HH20190322-143439', ldt(2019, MARCH, 22, 14, 34, 39), RING,
                500L, 771L, 11131131L, expectedHands)
    }

    def 'should parse a zone game session file correctly'() {

        def expectedHands = [
                new Hand(5234324480L, new Blinds(2L, 5L), [
                        new Seat(1, BIG_BLIND, new HoleCards(THREE_OF_HEARTS, EIGHT_OF_SPADES), true, 500L, -5L, 0L),
                        new Seat(2, UTG, new HoleCards(FIVE_OF_HEARTS, SEVEN_OF_HEARTS), false, 448L, 0L, 0L),
                        new Seat(3, UTG_1, new HoleCards(TEN_OF_CLUBS, FOUR_OF_CLUBS), false, 487L, 0L, 0L),
                        new Seat(4, UTG_2, new HoleCards(FIVE_OF_SPADES, EIGHT_OF_HEARTS), false, 555L, 0L, 0L),
                        new Seat(5, DEALER, new HoleCards(NINE_OF_SPADES, QUEEN_OF_DIAMONDS), false, 1080L, 0L, 0L),
                        new Seat(6, SMALL_BLIND, new HoleCards(FIVE_OF_DIAMONDS, FOUR_OF_SPADES), false, 204L, -2L, 0L)
                ]),
                new Hand(5234324606L, new Blinds(2L, 5L), [
                        new Seat(1, SMALL_BLIND, new HoleCards(TEN_OF_HEARTS, KING_OF_DIAMONDS), false, 380L, -380L, 0L),
                        new Seat(2, BIG_BLIND, new HoleCards(FOUR_OF_DIAMONDS, KING_OF_SPADES), false, 474L, -5L, 0L),
                        new Seat(3, UTG, new HoleCards(JACK_OF_CLUBS, ACE_OF_CLUBS), false, 297L, -297L, 0L),
                        new Seat(4, UTG_1, new HoleCards(THREE_OF_HEARTS, SEVEN_OF_DIAMONDS), true, 502L, 0L, 0L),
                        new Seat(5, UTG_2, new HoleCards(TWO_OF_SPADES, FOUR_OF_SPADES), false, 389L, 0L, 0L),
                        new Seat(6, DEALER, new HoleCards(JACK_OF_SPADES, TEN_OF_CLUBS), false, 500L, -380L, 0L)
                ]),
                new Hand(5234324715L, new Blinds(2L, 5L), [
                        new Seat(1, UTG, new HoleCards(FOUR_OF_CLUBS, ACE_OF_SPADES), false, 710L, 0L, 0L),
                        new Seat(2, UTG_1, new HoleCards(QUEEN_OF_SPADES, TWO_OF_HEARTS), true, 502L, 0L, 0L),
                        new Seat(3, UTG_2, new HoleCards(TEN_OF_CLUBS, KING_OF_HEARTS), false, 611L, -182L, 0L),
                        new Seat(4, DEALER, new HoleCards(QUEEN_OF_DIAMONDS, ACE_OF_HEARTS), false, 1127L, -182L, 0L),
                        new Seat(5, SMALL_BLIND, new HoleCards(SEVEN_OF_CLUBS, FIVE_OF_SPADES), false, 2453L, -2L, 0L),
                        new Seat(6, BIG_BLIND, new HoleCards(SIX_OF_CLUBS, JACK_OF_HEARTS), false, 871L, -5L, 0L)
                ]),
                new Hand(5234324773L, new Blinds(2L, 5L), [
                        new Seat(1, UTG_2, new HoleCards(FIVE_OF_HEARTS, JACK_OF_DIAMONDS), false, 397L, 0L, 0L),
                        new Seat(2, DEALER, new HoleCards(EIGHT_OF_CLUBS, SIX_OF_SPADES), true, 502L, 0L, 0L),
                        new Seat(3, SMALL_BLIND, new HoleCards(SIX_OF_DIAMONDS, NINE_OF_SPADES), false, 374L, -2L, 0L),
                        new Seat(4, BIG_BLIND, new HoleCards(TWO_OF_SPADES, KING_OF_HEARTS), false, 927L, -5L, 0L),
                        new Seat(5, UTG, new HoleCards(NINE_OF_CLUBS, TEN_OF_DIAMONDS), false, 592L, 0L, 0L),
                        new Seat(6, UTG_1, new HoleCards(QUEEN_OF_HEARTS, QUEEN_OF_CLUBS), false, 109L, -5L, 0L)
                ])
        ]

        def filename = 'HH20190326-200544 - 7713864 - ZONE - $0.02-$0.05 - HOLDEMZonePoker - NL - ZonePoker No.3156.txt'
        def actual = new SessionParser().parse(filename, SessionParserSpec.getResourceAsStream(filename).text)

        expect:
        expectedHands.eachWithIndex { Hand expectedHand, int idx ->
            assert actual.hands[idx] == expectedHand
        }

        actual == new Session('HH20190326-200544', ldt(2019, MARCH, 26, 20, 5, 44), ZONE,
                500L, 502L, 3156L, expectedHands)
    }

    def 'should parse a tournament session file correctly'() {

        def expectedHands = [
                new Hand(2365702372L, new Blinds(10L, 20L), [
                        new Seat(1, UTG, new HoleCards(KING_OF_DIAMONDS, THREE_OF_DIAMONDS), false, 150000L, 0L, 0L),
                        new Seat(8, UTG_1, new HoleCards(NINE_OF_DIAMONDS, TEN_OF_SPADES), true, 150000L, 0L, 0L),
                        new Seat(4, UTG_2, new HoleCards(KING_OF_HEARTS, EIGHT_OF_CLUBS), false, 150000L, 0L, 0L),
                        new Seat(6, UTG_3, new HoleCards(NINE_OF_CLUBS, ACE_OF_SPADES), false, 150000L, 0L, 0L),
                        new Seat(2, UTG_4, new HoleCards(FIVE_OF_SPADES, FOUR_OF_HEARTS), false, 150000L, 0L, 0L),
                        new Seat(5, UTG_5, new HoleCards(EIGHT_OF_DIAMONDS, KING_OF_SPADES), false, 150000L, 0L, 0L),
                        new Seat(9, DEALER, new HoleCards(TWO_OF_HEARTS, JACK_OF_SPADES), false, 150000L, 0L, 0L),
                        new Seat(7, SMALL_BLIND, new HoleCards(SEVEN_OF_SPADES, THREE_OF_CLUBS), false, 150000L, 0L, 0L),
                        new Seat(3, BIG_BLIND, new HoleCards(SIX_OF_CLUBS, TWO_OF_CLUBS), false, 150000L, 0L, 0L)
                ]),
                new Hand(2365710356L, new Blinds(15L, 30L), [
                        new Seat(1, SMALL_BLIND, new HoleCards(THREE_OF_SPADES, FIVE_OF_DIAMONDS), false, 144000L, 0L, 0L),
                        new Seat(8, BIG_BLIND, new HoleCards(SEVEN_OF_HEARTS, TWO_OF_CLUBS), true, 72200L, 0L, 0L),
                        new Seat(4, UTG, new HoleCards(KING_OF_SPADES, FOUR_OF_HEARTS), false, 139000L, 0L, 0L),
                        new Seat(6, UTG_1, new HoleCards(SEVEN_OF_SPADES, NINE_OF_CLUBS), false, 262100L, 0L, 0L),
                        new Seat(5, UTG_2, new HoleCards(THREE_OF_CLUBS, KING_OF_DIAMONDS), false, 147000L, 0L, 0L),
                        new Seat(9, UTG_3, new HoleCards(EIGHT_OF_SPADES, QUEEN_OF_DIAMONDS), false, 313000L, 0L, 0L),
                        new Seat(7, UTG_4, new HoleCards(SIX_OF_HEARTS, QUEEN_OF_CLUBS), false, 140000L, 0L, 0L),
                        new Seat(3, DEALER, new HoleCards(JACK_OF_HEARTS, SIX_OF_SPADES), false, 132700L, 0L, 0L)
                ]),
                new Hand(2365711424L, new Blinds(15L, 30L), [
                        new Seat(1, DEALER, new HoleCards(TEN_OF_HEARTS, QUEEN_OF_DIAMONDS), false, 142500L, 0L, 0L),
                        new Seat(8, SMALL_BLIND, new HoleCards(EIGHT_OF_CLUBS, ACE_OF_HEARTS), true, 69200L, 0L, 0L),
                        new Seat(4, BIG_BLIND, new HoleCards(TWO_OF_SPADES, TEN_OF_SPADES), false, 139000L, 0L, 0L),
                        new Seat(6, UTG, new HoleCards(QUEEN_OF_HEARTS, KING_OF_CLUBS), false, 259100L, 0L, 0L),
                        new Seat(5, UTG_1, new HoleCards(SIX_OF_DIAMONDS, KING_OF_HEARTS), false, 147000L, 0L, 0L),
                        new Seat(9, UTG_2, new HoleCards(JACK_OF_HEARTS, FIVE_OF_SPADES), false, 313000L, 0L, 0L),
                        new Seat(7, UTG_3, new HoleCards(TWO_OF_HEARTS, THREE_OF_DIAMONDS), false, 140000L, 0L, 0L),
                        new Seat(3, UTG_4, new HoleCards(JACK_OF_DIAMONDS, ACE_OF_SPADES), false, 140200L, 0L, 0L)
                ]),
                new Hand(2365740574L, new Blinds(100L, 200L), [
                        new Seat(8, SMALL_BLIND, new HoleCards(TEN_OF_DIAMONDS, JACK_OF_SPADES), true, 257400L, 0L, 0L),
                        new Seat(6, BIG_BLIND, new HoleCards(ACE_OF_HEARTS, ACE_OF_SPADES), false, 698600L, 0L, 0L),
                        new Seat(9, DEALER, new HoleCards(FIVE_OF_DIAMONDS, EIGHT_OF_DIAMONDS), false, 394000L, 0L, 0L)
                ])
        ]

        def filename = 'HH20171016-190220 - 1152521 - STT - Holdem - $1-$0.10 - HOLDEM - NL -Tourney No.37262539.txt'
        def actual = new SessionParser().parse(filename, SessionParserSpec.getResourceAsStream(filename).text)

        expect:
        expectedHands.eachWithIndex { Hand expectedHand, int idx ->
            assert actual.hands[idx] == expectedHand
        }

        actual == new Session('HH20171016-190220', ldt(2017, OCTOBER, 16, 19, 2, 20), STT,
                110L, 180L, 37262539L, expectedHands)
    }

}
