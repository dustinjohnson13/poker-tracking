package poker.ignition

import spock.lang.Specification

import static java.time.LocalDateTime.of as ldt
import static java.time.Month.MARCH
import static java.time.Month.OCTOBER
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
                        new Seat(1, UTG_1, false, 737L, 22L, 0L),
                        new Seat(2, UTG_2, false, 554L, 0L, 0L),
                        new Seat(3, DEALER, false, 532L, -17L, 0L),
                        new Seat(4, SMALL_BLIND, false, 478L, -2L, 0L),
                        new Seat(5, BIG_BLIND, true, 500L, -5L, 0L),
                        new Seat(6, UTG, false, 704L, 0L, 0L)]
                ),
                new Hand(2577017654L, new Blinds(2L, 5L), [
                        new Seat(1, UTG, false, 759L, -40L, 0L),
                        new Seat(2, UTG_1, false, 554L, 43L, 0L),
                        new Seat(4, DEALER, false, 476L, 0L, 0L),
                        new Seat(5, SMALL_BLIND, true, 495L, -2L, 0L),
                        new Seat(6, BIG_BLIND, false, 704L, -5L, 0L)]
                ),
                new Hand(2577018056L, new Blinds(2L, 5L), [
                        new Seat(1, BIG_BLIND, false, 719L, -435L, 0L),
                        new Seat(2, UTG, false, 597L, 0L, 0L),
                        new Seat(3, UTG_1, false, 210L, -25L, 0L),
                        new Seat(4, UTG_2, false, 476L, 0L, 0L),
                        new Seat(5, DEALER, true, 493L, 420L, 0L),
                        new Seat(6, SMALL_BLIND, false, 699L, -5L, 0L)]
                ),
                new Hand(2577018523L, new Blinds(2L, 5L), [
                        new Seat(1, SMALL_BLIND, false, 284L, 172L, 0L),
                        new Seat(2, BIG_BLIND, false, 597L, -5L, 0L),
                        new Seat(3, UTG, false, 185L, -185L, 0L),
                        new Seat(4, UTG_1, false, 476L, 0L, 0L),
                        new Seat(5, UTG_2, true, 913L, 0L, 0L),
                        new Seat(6, DEALER, false, 694L, 0L, 0L)]
                ),
                new Hand(2577018859L, new Blinds(2L, 5L), [
                        new Seat(1, DEALER, false, 456L, 0L, 0L),
                        new Seat(2, SMALL_BLIND, false, 592L, -2L, 0L),
                        new Seat(4, BIG_BLIND, false, 476L, -5L, 0L),
                        new Seat(5, UTG, true, 913L, 7L, 0L),
                        new Seat(6, UTG_1, false, 694L, 0L, 0L)]
                ),
                new Hand(2577019088L, new Blinds(2L, 5L), [
                        new Seat(1, UTG_1, false, 456L, 27L, 0L),
                        new Seat(2, DEALER, false, 590L, -22L, 0L),
                        new Seat(4, SMALL_BLIND, false, 471L, -2L, 0L),
                        new Seat(5, BIG_BLIND, true, 920L, -5L, 0L),
                        new Seat(6, UTG, false, 694L, 0L, 0L)]
                ),
                new Hand(2577019337L, new Blinds(2L, 5L), [
                        new Seat(1, UTG, false, 483L, 0L, 0L),
                        new Seat(2, UTG_1, false, 568L, 0L, 0L),
                        new Seat(3, UTG_2, false, 500L, -5L, 0L),
                        new Seat(4, DEALER, false, 469L, 0L, 0L),
                        new Seat(5, SMALL_BLIND, true, 915L, -25L, 0L),
                        new Seat(6, BIG_BLIND, false, 694L, 28L, 0L)]
                ),
                new Hand(2577019658L, new Blinds(2L, 5L), [
                        new Seat(1, BIG_BLIND, false, 483L, 15L, 0L),
                        new Seat(2, UTG, false, 568L, 0L, 0L),
                        new Seat(3, UTG_1, false, 495L, -5L, 0L),
                        new Seat(4, UTG_2, false, 469L, 0L, 0L),
                        new Seat(5, DEALER, true, 890L, -5L, 0L),
                        new Seat(6, SMALL_BLIND, false, 722L, -5L, 0L)]
                ),
                new Hand(2577019862L, new Blinds(2L, 5L), [
                        new Seat(1, SMALL_BLIND, false, 498L, -15L, 0L),
                        new Seat(2, BIG_BLIND, false, 568L, -5L, 0L),
                        new Seat(3, UTG, false, 490L, -35L, 0L),
                        new Seat(4, UTG_1, false, 469L, 51L, 0L),
                        new Seat(5, UTG_2, true, 885L, 0L, 0L),
                        new Seat(6, DEALER, false, 717L, 0L, 0L)]
                ),
                new Hand(2577020350L, new Blinds(2L, 5L), [
                        new Seat(1, DEALER, false, 483L, 10L, 0L),
                        new Seat(2, SMALL_BLIND, false, 563L, -5L, 0L),
                        new Seat(3, BIG_BLIND, false, 455L, -5L, 0L),
                        new Seat(4, UTG, false, 520L, 0L, 0L),
                        new Seat(5, UTG_1, true, 885L, 0L, 0L),
                        new Seat(6, UTG_2, false, 717L, 0L, 0L)]
                ),
                new Hand(2577020789L, new Blinds(2L, 5L), [
                        new Seat(1, UTG_2, false, 493L, 37L, 0L),
                        new Seat(2, DEALER, false, 558L, -17L, 0L),
                        new Seat(3, SMALL_BLIND, false, 450L, -17L, 0L),
                        new Seat(4, BIG_BLIND, false, 520L, -5L, 0L),
                        new Seat(5, UTG, true, 885L, 0L, 0L),
                        new Seat(6, UTG_1, false, 717L, 0L, 0L)]
                ),
                new Hand(2577021229L, new Blinds(2L, 5L), [
                        new Seat(1, UTG_1, false, 530L, -27L, 0L),
                        new Seat(2, UTG_2, false, 541L, 0L, 0L),
                        new Seat(3, DEALER, false, 433L, 31L, 0L),
                        new Seat(4, SMALL_BLIND, false, 515L, -2L, 0L),
                        new Seat(5, BIG_BLIND, true, 885L, -5L, 0L),
                        new Seat(6, UTG, false, 717L, 0L, 0L)]
                ),
                new Hand(2577021449L, new Blinds(2L, 5L), [
                        new Seat(1, UTG, false, 503L, 7L, 0L),
                        new Seat(2, UTG_1, false, 541L, 0L, 0L),
                        new Seat(3, UTG_2, false, 464L, 0L, 0L),
                        new Seat(4, DEALER, false, 513L, 0L, 0L),
                        new Seat(5, SMALL_BLIND, true, 880L, -2L, 0L),
                        new Seat(6, BIG_BLIND, false, 717L, -5L, 0L)]
                ),
                new Hand(2577021587L, new Blinds(2L, 5L), [
                        new Seat(1, BIG_BLIND, false, 510L, -5L, 0L),
                        new Seat(2, UTG, false, 541L, 0L, 0L),
                        new Seat(3, UTG_1, false, 464L, -274L, 0L),
                        new Seat(4, UTG_2, false, 513L, -15L, 0L),
                        new Seat(5, DEALER, true, 878L, 0L, 0L),
                        new Seat(6, SMALL_BLIND, false, 712L, 266L, 0L)]
                ),
                new Hand(2577022015L, new Blinds(2L, 5L), [
                        new Seat(1, SMALL_BLIND, false, 505L, 4L, 0L),
                        new Seat(2, BIG_BLIND, false, 541L, 5L, 0L),
                        new Seat(3, UTG, false, 190L, -10L, 0L),
                        new Seat(4, UTG_1, false, 498L, 0L, 0L),
                        new Seat(5, UTG_2, true, 878L, 0L, 0L),
                        new Seat(6, DEALER, false, 978L, 0L, 0L)]
                ),
                new Hand(2577022336L, new Blinds(2L, 5L), [
                        new Seat(1, DEALER, false, 509L, 70L, 0L),
                        new Seat(2, SMALL_BLIND, false, 546L, -2L, 0L),
                        new Seat(3, BIG_BLIND, false, 180L, -5L, 0L),
                        new Seat(4, UTG, false, 498L, 0L, 0L),
                        new Seat(5, UTG_1, true, 878L, -70L, 0L),
                        new Seat(6, UTG_2, false, 978L, 0L, 0L)]
                ),
                new Hand(2577022716L, new Blinds(2L, 5L), [
                        new Seat(1, UTG_2, false, 579L, -74L, 0L),
                        new Seat(2, DEALER, false, 544L, 74L, 0L),
                        new Seat(3, SMALL_BLIND, false, 175L, -2L, 0L),
                        new Seat(4, BIG_BLIND, false, 498L, -5L, 0L),
                        new Seat(5, UTG, true, 808L, 0L, 0L),
                        new Seat(6, UTG_1, false, 978L, 0L, 0L)]
                ),
                new Hand(2577022985L, new Blinds(2L, 5L), [
                        new Seat(1, UTG_1, false, 505L, -15L, 0L),
                        new Seat(2, UTG_2, false, 618L, 0L, 0L),
                        new Seat(3, DEALER, false, 173L, -15L, 0L),
                        new Seat(4, SMALL_BLIND, false, 493L, -2L, 0L),
                        new Seat(5, BIG_BLIND, true, 808L, -40L, 0L),
                        new Seat(6, UTG, false, 978L, 67L, 0L)]
                ),
                new Hand(2577023411L, new Blinds(2L, 5L), [
                        new Seat(1, UTG, false, 490L, 0L, 0L),
                        new Seat(2, UTG_1, false, 618L, 0L, 0L),
                        new Seat(3, UTG_2, false, 158L, -20L, 0L),
                        new Seat(4, DEALER, false, 491L, -116L, 0L),
                        new Seat(5, SMALL_BLIND, true, 768L, 129L, 0L),
                        new Seat(6, BIG_BLIND, false, 1045L, -5L, 0L)]
                ),
                new Hand(2577023960L, new Blinds(2L, 5L), [
                        new Seat(1, BIG_BLIND, false, 490L, -5L, 0L),
                        new Seat(2, UTG, false, 618L, 7L, 0L),
                        new Seat(3, UTG_1, false, 138L, 0L, 0L),
                        new Seat(4, UTG_2, false, 375L, 0L, 0L),
                        new Seat(5, DEALER, true, 897L, 0L, 0L),
                        new Seat(6, SMALL_BLIND, false, 1040L, -2L, 0L)]
                ),
                new Hand(2577024178L, new Blinds(2L, 5L), [
                        new Seat(1, SMALL_BLIND, false, 485L, -2L, 0L),
                        new Seat(2, BIG_BLIND, false, 625L, -15L, 0L),
                        new Seat(3, UTG, false, 138L, -15L, 0L),
                        new Seat(4, UTG_1, false, 375L, -15L, 140L),
                        new Seat(5, UTG_2, true, 897L, -109L, 0L),
                        new Seat(6, DEALER, false, 1038L, 143L, 0L)]
                ),
                new Hand(2577024771L, new Blinds(2L, 5L), [
                        new Seat(1, DEALER, false, 483L, 0L, 0L),
                        new Seat(2, SMALL_BLIND, false, 610L, -17L, 0L),
                        new Seat(3, BIG_BLIND, false, 123L, 32L, 0L),
                        new Seat(4, UTG, false, 500L, 0L, 0L),
                        new Seat(5, UTG_1, true, 788L, -17L, 0L),
                        new Seat(6, UTG_2, false, 1181L, 0L, 0L)]
                )
        ]

        def filename = 'HH20190322-143439 - 3568450 - RING - $0.02-$0.05 - HOLDEM - NL - TBL No.11131131.txt'
        def actual = new SessionParser().parse(filename, SessionParserSpec.getResourceAsStream(filename).text)

        expect:
        expectedHands.eachWithIndex{ Hand expectedHand, int idx ->
            assert actual.hands[idx] == expectedHand
        }

        actual == new Session('HH20190322-143439', ldt(2019, MARCH, 22, 14, 34, 39), RING,
                500L, 771L, 11131131L, expectedHands)
    }

    def 'should parse a zone game session file correctly'() {

        def expectedHands = [
                new Hand(5234324480L, new Blinds(2L, 5L), [
                        new Seat(1, BIG_BLIND, true, 500L, -5L, 0L),
                        new Seat(2, UTG, false, 448L, 0L, 0L),
                        new Seat(3, UTG_1, false, 487L, 0L, 0L),
                        new Seat(4, UTG_2, false, 555L, 0L, 0L),
                        new Seat(5, DEALER, false, 1080L, 0L, 0L),
                        new Seat(6, SMALL_BLIND, false, 204L, -2L, 0L)
                ]),
                new Hand(5234324606L, new Blinds(2L, 5L), [
                        new Seat(1, SMALL_BLIND, false, 380L, -380L, 0L),
                        new Seat(2, BIG_BLIND, false, 474L, -5L, 0L),
                        new Seat(3, UTG, false, 297L, -297L, 0L),
                        new Seat(4, UTG_1, true, 502L, 0L, 0L),
                        new Seat(5, UTG_2, false, 389L, 0L, 0L),
                        new Seat(6, DEALER, false, 500L, -380L, 0L)
                ]),
                new Hand(5234324715L, new Blinds(2L, 5L), [
                        new Seat(1, UTG, false, 710L, 0L, 0L),
                        new Seat(2, UTG_1, true, 502L, 0L, 0L),
                        new Seat(3, UTG_2, false, 611L, -182L, 0L),
                        new Seat(4, DEALER, false, 1127L, -182L, 0L),
                        new Seat(5, SMALL_BLIND, false, 2453L, -2L, 0L),
                        new Seat(6, BIG_BLIND, false, 871L, -5L, 0L)
                ]),
                new Hand(5234324773L, new Blinds(2L, 5L), [
                        new Seat(1, UTG_2, false, 397L, 0L, 0L),
                        new Seat(2, DEALER, true, 502L, 0L, 0L),
                        new Seat(3, SMALL_BLIND, false, 374L, -2L, 0L),
                        new Seat(4, BIG_BLIND, false, 927L, -5L, 0L),
                        new Seat(5, UTG, false, 592L, 0L, 0L),
                        new Seat(6, UTG_1, false, 109L, -5L, 0L)
                ])
        ]

        def filename = 'HH20190326-200544 - 7713864 - ZONE - $0.02-$0.05 - HOLDEMZonePoker - NL - ZonePoker No.3156.txt'
        def actual = new SessionParser().parse(filename, SessionParserSpec.getResourceAsStream(filename).text)

        expect:
        expectedHands.eachWithIndex{ Hand expectedHand, int idx ->
            assert actual.hands[idx] == expectedHand
        }

        actual == new Session('HH20190326-200544', ldt(2019, MARCH, 26, 20, 5, 44), ZONE,
                500L, 502L, 3156L, expectedHands)
    }

    def 'should parse a tournament session file correctly'() {

        def expectedHands = [
                new Hand(2365702372L, new Blinds(10L, 20L), [
                        new Seat(1, UTG, false, 150000L, 0L, 0L),
                        new Seat(8, UTG_1, true, 150000L, 0L, 0L),
                        new Seat(4, UTG_2, false, 150000L, 0L, 0L),
                        new Seat(6, UTG_3, false, 150000L, 0L, 0L),
                        new Seat(2, UTG_4, false, 150000L, 0L, 0L),
                        new Seat(5, UTG_5, false, 150000L, 0L, 0L),
                        new Seat(9, DEALER, false, 150000L, 0L, 0L),
                        new Seat(7, SMALL_BLIND, false, 150000L, 0L, 0L),
                        new Seat(3, BIG_BLIND, false, 150000L, 0L, 0L)
                ]),
                new Hand(2365710356L, new Blinds(15L, 30L), [
                        new Seat(1, SMALL_BLIND, false, 144000L, 0L, 0L),
                        new Seat(8, BIG_BLIND, true, 72200L, 0L, 0L),
                        new Seat(4, UTG, false, 139000L, 0L, 0L),
                        new Seat(6, UTG_1, false, 262100L, 0L, 0L),
                        new Seat(5, UTG_2, false, 147000L, 0L, 0L),
                        new Seat(9, UTG_3, false, 313000L, 0L, 0L),
                        new Seat(7, UTG_4, false, 140000L, 0L, 0L),
                        new Seat(3, DEALER, false, 132700L, 0L, 0L)
                ]),
                new Hand(2365711424L, new Blinds(15L, 30L), [
                        new Seat(1, DEALER, false, 142500L, 0L, 0L),
                        new Seat(8, SMALL_BLIND, true, 69200L, 0L, 0L),
                        new Seat(4, BIG_BLIND, false, 139000L, 0L, 0L),
                        new Seat(6, UTG, false, 259100L, 0L, 0L),
                        new Seat(5, UTG_1, false, 147000L, 0L, 0L),
                        new Seat(9, UTG_2, false, 313000L, 0L, 0L),
                        new Seat(7, UTG_3, false, 140000L, 0L, 0L),
                        new Seat(3, UTG_4, false, 140200L, 0L, 0L)
                ]),
                new Hand(2365740574L, new Blinds(100L, 200L), [
                        new Seat(8, SMALL_BLIND, true, 257400L, 0L, 0L),
                        new Seat(6, BIG_BLIND, false, 698600L, 0L, 0L),
                        new Seat(9, DEALER, false, 394000L, 0L, 0L)
                ])

        ]

        def filename = 'HH20171016-190220 - 1152521 - STT - Holdem - $1-$0.10 - HOLDEM - NL -Tourney No.37262539.txt'
        def actual = new SessionParser().parse(filename, SessionParserSpec.getResourceAsStream(filename).text)

        expect:
        expectedHands.eachWithIndex{ Hand expectedHand, int idx ->
            assert actual.hands[idx] == expectedHand
        }

        actual == new Session('HH20171016-190220', ldt(2017, OCTOBER, 16, 19, 2, 20), STT,
                110L, 180L, 37262539L, expectedHands)
    }

}
