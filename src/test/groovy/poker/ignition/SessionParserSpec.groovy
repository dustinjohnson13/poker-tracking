package poker.ignition

import spock.lang.Specification

import static java.time.LocalDateTime.of as ldt
import static java.time.Month.MARCH
import static poker.ignition.Position.BIG_BLIND
import static poker.ignition.Position.DEALER
import static poker.ignition.Position.SMALL_BLIND
import static poker.ignition.Position.UTG
import static poker.ignition.Position.UTG_1
import static poker.ignition.Position.UTG_2
import static poker.ignition.SessionType.RING
import static poker.ignition.SessionType.ZONE

class SessionParserSpec extends Specification {

    def 'should parse a ring game session file correctly'() {

        def expectedHands = [
                new Hand(2577017255L, [
                        new Seat(1, UTG_1, false, 737L, 22L),
                        new Seat(2, UTG_2, false, 554L, 0L),
                        new Seat(3, DEALER, false, 532L, -17L),
                        new Seat(4, SMALL_BLIND, false, 478L, -2L),
                        new Seat(5, BIG_BLIND, true, 500L, -5L),
                        new Seat(6, UTG, false, 704L, 0L)]
                ),
                new Hand(2577017654L, [
                        new Seat(1, UTG, false, 759L, -40L),
                        new Seat(2, UTG_1, false, 554L, 43L),
                        new Seat(4, DEALER, false, 476L, 0L),
                        new Seat(5, SMALL_BLIND, true, 495L, -2L),
                        new Seat(6, BIG_BLIND, false, 704L, -5L)]
                ),
                new Hand(2577018056L, [
                        new Seat(1, BIG_BLIND, false, 719L, -435L),
                        new Seat(2, UTG, false, 597L, 0L),
                        new Seat(3, UTG_1, false, 210L, -25L),
                        new Seat(4, UTG_2, false, 476L, 0L),
                        new Seat(5, DEALER, true, 493L, 420L),
                        new Seat(6, SMALL_BLIND, false, 699L, -5L)]
                ),
                new Hand(2577018523L, [
                        new Seat(1, SMALL_BLIND, false, 284L, 172L),
                        new Seat(2, BIG_BLIND, false, 597L, -5L),
                        new Seat(3, UTG, false, 185L, 0L),
                        new Seat(4, UTG_1, false, 476L, 0L),
                        new Seat(5, UTG_2, true, 913L, 0L),
                        new Seat(6, DEALER, false, 694L, 0L)]
                ),
                new Hand(2577018859L, [
                        new Seat(1, DEALER, false, 456L, 0L),
                        new Seat(2, SMALL_BLIND, false, 592L, -2L),
                        new Seat(4, BIG_BLIND, false, 476L, -5L),
                        new Seat(5, UTG, true, 913L, 7L),
                        new Seat(6, UTG_1, false, 694L, 0L)]
                ),
                new Hand(2577019088L, [
                        new Seat(1, UTG_1, false, 456L, 27L),
                        new Seat(2, DEALER, false, 590L, -22L),
                        new Seat(4, SMALL_BLIND, false, 471L, -2L),
                        new Seat(5, BIG_BLIND, true, 920L, -5L),
                        new Seat(6, UTG, false, 694L, 0L)]
                ),
                new Hand(2577019337L, [
                        new Seat(1, UTG, false, 483L, 0L),
                        new Seat(2, UTG_1, false, 568L, 0L),
                        new Seat(3, UTG_2, false, 500L, -5L),
                        new Seat(4, DEALER, false, 469L, 0L),
                        new Seat(5, SMALL_BLIND, true, 915L, -25L),
                        new Seat(6, BIG_BLIND, false, 694L, 28L)]
                ),
                new Hand(2577019658L, [
                        new Seat(1, BIG_BLIND, false, 483L, 15L),
                        new Seat(2, UTG, false, 568L, 0L),
                        new Seat(3, UTG_1, false, 495L, -5L),
                        new Seat(4, UTG_2, false, 469L, 0L),
                        new Seat(5, DEALER, true, 890L, -5L),
                        new Seat(6, SMALL_BLIND, false, 722L, -5L)]
                ),
                new Hand(2577019862L, [
                        new Seat(1, SMALL_BLIND, false, 498L, -15L),
                        new Seat(2, BIG_BLIND, false, 568L, -5L),
                        new Seat(3, UTG, false, 490L, -35L),
                        new Seat(4, UTG_1, false, 469L, 51L),
                        new Seat(5, UTG_2, true, 885L, 0L),
                        new Seat(6, DEALER, false, 717L, 0L)]
                ),
                new Hand(2577020350L, [
                        new Seat(1, DEALER, false, 483L, 10L),
                        new Seat(2, SMALL_BLIND, false, 563L, -5L),
                        new Seat(3, BIG_BLIND, false, 455L, -5L),
                        new Seat(4, UTG, false, 520L, 0L),
                        new Seat(5, UTG_1, true, 885L, 0L),
                        new Seat(6, UTG_2, false, 717L, 0L)]
                ),
                new Hand(2577020789L, [
                        new Seat(1, UTG_2, false, 493L, 37L),
                        new Seat(2, DEALER, false, 558L, -17L),
                        new Seat(3, SMALL_BLIND, false, 450L, -17L),
                        new Seat(4, BIG_BLIND, false, 520L, -5L),
                        new Seat(5, UTG, true, 885L, 0L),
                        new Seat(6, UTG_1, false, 717L, 0L)]
                ),
                new Hand(2577021229L, [
                        new Seat(1, UTG_1, false, 530L, -27L),
                        new Seat(2, UTG_2, false, 541L, 0L),
                        new Seat(3, DEALER, false, 433L, 31L),
                        new Seat(4, SMALL_BLIND, false, 515L, -2L),
                        new Seat(5, BIG_BLIND, true, 885L, -5L),
                        new Seat(6, UTG, false, 717L, 0L)]
                ),
                new Hand(2577021449L, [
                        new Seat(1, UTG, false, 503L, 7L),
                        new Seat(2, UTG_1, false, 541L, 0L),
                        new Seat(3, UTG_2, false, 464L, 0L),
                        new Seat(4, DEALER, false, 513L, 0L),
                        new Seat(5, SMALL_BLIND, true, 880L, -2L),
                        new Seat(6, BIG_BLIND, false, 717L, -5L)]
                ),
                new Hand(2577021587L, [
                        new Seat(1, BIG_BLIND, false, 510L, -5L),
                        new Seat(2, UTG, false, 541L, 0L),
                        new Seat(3, UTG_1, false, 464L, -274L),
                        new Seat(4, UTG_2, false, 513L, -15L),
                        new Seat(5, DEALER, true, 878L, 0L),
                        new Seat(6, SMALL_BLIND, false, 712L, 266L)]
                ),
                new Hand(2577022015L, [
                        new Seat(1, SMALL_BLIND, false, 505L, 4L),
                        new Seat(2, BIG_BLIND, false, 541L, 5L),
                        new Seat(3, UTG, false, 190L, -10L),
                        new Seat(4, UTG_1, false, 498L, 0L),
                        new Seat(5, UTG_2, true, 878L, 0L),
                        new Seat(6, DEALER, false, 978L, 0L)]
                ),
                new Hand(2577022336L, [
                        new Seat(1, DEALER, false, 509L, 70L),
                        new Seat(2, SMALL_BLIND, false, 546L, -2L),
                        new Seat(3, BIG_BLIND, false, 180L, -5L),
                        new Seat(4, UTG, false, 498L, 0L),
                        new Seat(5, UTG_1, true, 878L, -70L),
                        new Seat(6, UTG_2, false, 978L, 0L)]
                ),
                new Hand(2577022716L, [
                        new Seat(1, UTG_2, false, 579L, -74L),
                        new Seat(2, DEALER, false, 544L, 74L),
                        new Seat(3, SMALL_BLIND, false, 175L, -2L),
                        new Seat(4, BIG_BLIND, false, 498L, -5L),
                        new Seat(5, UTG, true, 808L, 0L),
                        new Seat(6, UTG_1, false, 978L, 0L)]
                ),
                new Hand(2577022985L, [
                        new Seat(1, UTG_1, false, 505L, -15L),
                        new Seat(2, UTG_2, false, 618L, 0L),
                        new Seat(3, DEALER, false, 173L, -15L),
                        new Seat(4, SMALL_BLIND, false, 493L, -2L),
                        new Seat(5, BIG_BLIND, true, 808L, -40L),
                        new Seat(6, UTG, false, 978L, 67L)]
                ),
                new Hand(2577023411L, [
                        new Seat(1, UTG, false, 490L, 0L),
                        new Seat(2, UTG_1, false, 618L, 0L),
                        new Seat(3, UTG_2, false, 158L, -20L),
                        new Seat(4, DEALER, false, 491L, -116L),
                        new Seat(5, SMALL_BLIND, true, 768L, 129L),
                        new Seat(6, BIG_BLIND, false, 1045L, -5L)]
                ),
                new Hand(2577023960L, [
                        new Seat(1, BIG_BLIND, false, 490L, -5L),
                        new Seat(2, UTG, false, 618L, 7L),
                        new Seat(3, UTG_1, false, 138L, 0L),
                        new Seat(4, UTG_2, false, 375L, 0L),
                        new Seat(5, DEALER, true, 897L, 0L),
                        new Seat(6, SMALL_BLIND, false, 1040L, -2L)]
                ),
                new Hand(2577024178L, [
                        new Seat(1, SMALL_BLIND, false, 485L, -2L),
                        new Seat(2, BIG_BLIND, false, 625L, -15L),
                        new Seat(3, UTG, false, 138L, -15L),
                        new Seat(4, UTG_1, false, 375L, -15L),
                        new Seat(5, UTG_2, true, 897L, -109L),
                        new Seat(6, DEALER, false, 1038L, 143L)]
                ),
                new Hand(2577024771L, [
                        new Seat(1, DEALER, false, 483L, 0L),
                        new Seat(2, SMALL_BLIND, false, 610L, -17L),
                        new Seat(3, BIG_BLIND, false, 123L, 32L),
                        new Seat(4, UTG, false, 500L, 0L),
                        new Seat(5, UTG_1, true, 788L, -17L),
                        new Seat(6, UTG_2, false, 1181L, 0L)]
                )
        ]

        def filename = 'HH20190322-143439 - 3568450 - RING - $0.02-$0.05 - HOLDEM - NL - TBL No.11131131.txt'
        def actual = new SessionParser().parse(filename, SessionParserSpec.getResourceAsStream(filename).text)

        expect:
        actual == new Session('HH20190322-143439', ldt(2019, MARCH, 22, 14, 34, 39), RING, 2L, 5L,
                11131131L, 500L, 271L, expectedHands)
    }

    def 'should parse a zone game session file correctly'() {

        def expectedHands = [
                new Hand(5234324480L, [
                        new Seat(1, BIG_BLIND, true, 500L, -5L),
                        new Seat(2, UTG, false, 448L, 0L),
                        new Seat(3, UTG_1, false, 487L, 0L),
                        new Seat(4, UTG_2, false, 555L, 0L),
                        new Seat(5, DEALER, false, 1080L, 0L),
                        new Seat(6, SMALL_BLIND, false, 204L, -2L)
                ]),
                new Hand(5234324606L, [
                        new Seat(1, SMALL_BLIND, false, 380L, -65L),
                        new Seat(2, BIG_BLIND, false, 474L, -5L),
                        new Seat(3, UTG, false, 297L, -65L),
                        new Seat(4, UTG_1, true, 502L, 0L),
                        new Seat(5, UTG_2, false, 389L, 0L),
                        new Seat(6, DEALER, false, 500L, -380L)
                ]),
                new Hand(5234324715L, [
                        new Seat(1, UTG, false, 710L, 0L),
                        new Seat(2, UTG_1, true, 502L, 0L),
                        new Seat(3, UTG_2, false, 611L, -182L),
                        new Seat(4, DEALER, false, 1127L, -182L),
                        new Seat(5, SMALL_BLIND, false, 2453L, -2L),
                        new Seat(6, BIG_BLIND, false, 871L, -5L)
                ]),
                new Hand(5234324773L, [
                        new Seat(1, UTG_2, false, 397L, 0L),
                        new Seat(2, DEALER, true, 502L, 0L),
                        new Seat(3, SMALL_BLIND, false, 374L, -2L),
                        new Seat(4, BIG_BLIND, false, 927L, -5L),
                        new Seat(5, UTG, false, 592L, 0L),
                        new Seat(6, UTG_1, false, 109L, -5L)
                ])
        ]

        def filename = 'HH20190326-200544 - 7713864 - ZONE - $0.02-$0.05 - HOLDEMZonePoker - NL - ZonePoker No.3156.txt'
        def actual = new SessionParser().parse(filename, SessionParserSpec.getResourceAsStream(filename).text)

        expect:
        actual == new Session('HH20190326-200544', ldt(2019, MARCH, 26, 20, 5, 44), ZONE, 2L, 5L,
                3156L, 500L, 2L, expectedHands)
    }
}
