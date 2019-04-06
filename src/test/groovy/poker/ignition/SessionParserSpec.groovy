package poker.ignition

import spock.lang.Specification

import static java.time.LocalDateTime.of as ldt
import static java.time.Month.MARCH
import static poker.ignition.SessionType.RING

class SessionParserSpec extends Specification {

    def 'should parse a session file correctly'() {

        def expectedHands = [
                new Hand(2577017255L, [
                        new Seat(1, Position.UTG_1, false, 737L, 39L),
                        new Seat(2, Position.UTG_2, false, 554L, 0L),
                        new Seat(3, Position.DEALER, false, 532L, -17L),
                        new Seat(4, Position.SMALL_BLIND, false, 478L, -2L),
                        new Seat(5, Position.BIG_BLIND, true, 500L, -5L),
                        new Seat(6, Position.UTG, false, 704L, 0L)]
                ),
                new Hand(2577017654L, [
                        new Seat(1, Position.UTG, false, 759L, -40L),
                        new Seat(2, Position.UTG_1, false, 554L, 83L),
                        new Seat(4, Position.DEALER, false, 476L, 0L),
                        new Seat(5, Position.SMALL_BLIND, true, 495L, -2L),
                        new Seat(6, Position.BIG_BLIND, false, 704L, -5L)]
                ),
                new Hand(2577018056L, [
                        new Seat(1, Position.BIG_BLIND, false, 719L, -5L),
                        new Seat(2, Position.UTG, false, 597L, 0L),
                        new Seat(3, Position.UTG_1, false, 210L, -20L),
                        new Seat(4, Position.UTG_2, false, 476L, 0L),
                        new Seat(5, Position.DEALER, true, 493L, 430L),
                        new Seat(6, Position.SMALL_BLIND, false, 699L, -5L)]
                ),
                new Hand(2577018523L, [
                        new Seat(1, Position.SMALL_BLIND, false, 284L, 172L),
                        new Seat(2, Position.BIG_BLIND, false, 597L, -5L),
                        new Seat(3, Position.UTG, false, 185L, 0L),
                        new Seat(4, Position.UTG_1, false, 476L, 0L),
                        new Seat(5, Position.UTG_2, true, 913L, 0L),
                        new Seat(6, Position.DEALER, false, 694L, 0L)]
                ),
                new Hand(2577018859L, [
                        new Seat(1, Position.DEALER, false, 455L, 0L),
                        new Seat(2, Position.SMALL_BLIND, false, 592L, -2L),
                        new Seat(4, Position.BIG_BLIND, false, 476L, -5L),
                        new Seat(5, Position.UTG, true, 913L, 7L),
                        new Seat(6, Position.UTG_1, false, 694L, 0L)]
                ),
                new Hand(2577019088L, [
                        new Seat(1, Position.UTG_1, false, 455L, 49L),
                        new Seat(2, Position.DEALER, false, 590L, -22L),
                        new Seat(4, Position.SMALL_BLIND, false, 471L, -2L),
                        new Seat(5, Position.BIG_BLIND, true, 919L, -5L),
                        new Seat(6, Position.UTG, false, 694L, 0L)]
                ),
                new Hand(2577019337L, [
                        new Seat(1, Position.UTG, false, 483L, 0L),
                        new Seat(2, Position.UTG_1, false, 568L, 0L),
                        new Seat(3, Position.UTG_2, false, 500L, 0L),
                        new Seat(4, Position.DEALER, false, 469L, 0L),
                        new Seat(5, Position.SMALL_BLIND, true, 915L, -10L),
                        new Seat(6, Position.BIG_BLIND, false, 694L, 33L)]
                ),
                new Hand(2577019658L, [
                        new Seat(1, Position.BIG_BLIND, false, 483L, 15L),
                        new Seat(2, Position.UTG, false, 568L, 0L),
                        new Seat(3, Position.UTG_1, false, 495L, -5L),
                        new Seat(4, Position.UTG_2, false, 469L, 0L),
                        new Seat(5, Position.DEALER, true, 890L, -5L),
                        new Seat(6, Position.SMALL_BLIND, false, 722L, -5L)]
                ),
                new Hand(2577019862L, [
                        new Seat(1, Position.SMALL_BLIND, false, 498L, -15L),
                        new Seat(2, Position.BIG_BLIND, false, 568L, -5L),
                        new Seat(3, Position.UTG, false, 490L, -30L),
                        new Seat(4, Position.UTG_1, false, 469L, 81L),
                        new Seat(5, Position.UTG_2, true, 885L, 0L),
                        new Seat(6, Position.DEALER, false, 717L, 0L)]
                ),
                new Hand(2577020350L, [
                        new Seat(1, Position.DEALER, false, 483L, 10L),
                        new Seat(2, Position.SMALL_BLIND, false, 563L, -5L),
                        new Seat(3, Position.BIG_BLIND, false, 455L, -5L),
                        new Seat(4, Position.UTG, false, 520L, 0L),
                        new Seat(5, Position.UTG_1, true, 885L, 0L),
                        new Seat(6, Position.UTG_2, false, 717L, 0L)]
                ),
                new Hand(2577020789L, [
                        new Seat(1, Position.UTG_2, false, 493L, 54L),
                        new Seat(2, Position.DEALER, false, 558L, -17L),
                        new Seat(3, Position.SMALL_BLIND, false, 450L, -17L),
                        new Seat(4, Position.BIG_BLIND, false, 520L, -5L),
                        new Seat(5, Position.UTG, true, 885L, 0L),
                        new Seat(6, Position.UTG_1, false, 717L, 0L)]
                ),
                new Hand(2577021229L, [
                        new Seat(1, Position.UTG_1, false, 530L, -5L),
                        new Seat(2, Position.UTG_2, false, 541L, 0L),
                        new Seat(3, Position.DEALER, false, 433L, 35L),
                        new Seat(4, Position.SMALL_BLIND, false, 515L, -2L),
                        new Seat(5, Position.BIG_BLIND, true, 885L, -5L),
                        new Seat(6, Position.UTG, false, 717L, 0L)]
                ),
                new Hand(2577021449L, [
                        new Seat(1, Position.UTG, false, 503L, 12L),
                        new Seat(2, Position.UTG_1, false, 541L, 0L),
                        new Seat(3, Position.UTG_2, false, 463L, 0L),
                        new Seat(4, Position.DEALER, false, 513L, 0L),
                        new Seat(5, Position.SMALL_BLIND, true, 880L, -2L),
                        new Seat(6, Position.BIG_BLIND, false, 717L, -5L)]
                ),
                new Hand(2577021587L, [
                        new Seat(1, Position.BIG_BLIND, false, 509L, -5L),
                        new Seat(2, Position.UTG, false, 541L, 0L),
                        new Seat(3, Position.UTG_1, false, 463L, -254L),
                        new Seat(4, Position.UTG_2, false, 513L, 0L),
                        new Seat(5, Position.DEALER, true, 877L, 0L),
                        new Seat(6, Position.SMALL_BLIND, false, 712L, 525L)]
                ),
                new Hand(2577022015L, [
                        new Seat(1, Position.SMALL_BLIND, false, 505L, 9L),
                        new Seat(2, Position.BIG_BLIND, false, 541L, 5L),
                        new Seat(3, Position.UTG, false, 190L, -10L),
                        new Seat(4, Position.UTG_1, false, 498L, 0L),
                        new Seat(5, Position.UTG_2, true, 877L, 0L),
                        new Seat(6, Position.DEALER, false, 977L, 0L)]
                ),
                new Hand(2577022336L, [
                        new Seat(1, Position.DEALER, false, 509L, 140L),
                        new Seat(2, Position.SMALL_BLIND, false, 546L, -2L),
                        new Seat(3, Position.BIG_BLIND, false, 180L, -5L),
                        new Seat(4, Position.UTG, false, 498L, 0L),
                        new Seat(5, Position.UTG_1, true, 877L, -65L),
                        new Seat(6, Position.UTG_2, false, 977L, 0L)]
                ),
                new Hand(2577022716L, [
                        new Seat(1, Position.UTG_2, false, 579L, -47L),
                        new Seat(2, Position.DEALER, false, 544L, 148L),
                        new Seat(3, Position.SMALL_BLIND, false, 175L, -2L),
                        new Seat(4, Position.BIG_BLIND, false, 498L, -5L),
                        new Seat(5, Position.UTG, true, 808L, 0L),
                        new Seat(6, Position.UTG_1, false, 977L, 0L)]
                ),
                new Hand(2577022985L, [
                        new Seat(1, Position.UTG_1, false, 505L, -15L),
                        new Seat(2, Position.UTG_2, false, 618L, 0L),
                        new Seat(3, Position.DEALER, false, 173L, -15L),
                        new Seat(4, Position.SMALL_BLIND, false, 493L, -2L),
                        new Seat(5, Position.BIG_BLIND, true, 808L, -35L),
                        new Seat(6, Position.UTG, false, 977L, 107L)]
                ),
                new Hand(2577023411L, [
                        new Seat(1, Position.UTG, false, 490L, 0L),
                        new Seat(2, Position.UTG_1, false, 618L, 0L),
                        new Seat(3, Position.UTG_2, false, 158L, -20L),
                        new Seat(4, Position.DEALER, false, 491L, -96L),
                        new Seat(5, Position.SMALL_BLIND, true, 768L, 225L),
                        new Seat(6, Position.BIG_BLIND, false, 1045L, -5L)]
                ),
                new Hand(2577023960L, [
                        new Seat(1, Position.BIG_BLIND, false, 490L, -5L),
                        new Seat(2, Position.UTG, false, 618L, 12L),
                        new Seat(3, Position.UTG_1, false, 138L, 0L),
                        new Seat(4, Position.UTG_2, false, 375L, 0L),
                        new Seat(5, Position.DEALER, true, 897L, 0L),
                        new Seat(6, Position.SMALL_BLIND, false, 1040L, -2L)]
                ),
                new Hand(2577024178L, [
                        new Seat(1, Position.SMALL_BLIND, false, 484L, -2L),
                        new Seat(2, Position.BIG_BLIND, false, 625L, -15L),
                        new Seat(3, Position.UTG, false, 138L, -15L),
                        new Seat(4, Position.UTG_1, false, 375L, 0L),
                        new Seat(5, Position.UTG_2, true, 897L, -103L),
                        new Seat(6, Position.DEALER, false, 1038L, 237L)]
                ),
                new Hand(2577024771L, [
                        new Seat(1, Position.DEALER, false, 483L, 0L),
                        new Seat(2, Position.SMALL_BLIND, false, 610L, -10L),
                        new Seat(3, Position.BIG_BLIND, false, 123L, 37L),
                        new Seat(4, Position.UTG, false, 500L, 0L),
                        new Seat(5, Position.UTG_1, true, 788L, -17L),
                        new Seat(6, Position.UTG_2, false, 1181L, 0L)]
                )
        ]

        def filename = 'HH20190322-143439 - 3568450 - RING - $0.02-$0.05 - HOLDEM - NL - TBL No.11131131.txt'
        def actual = new SessionParser().parse(filename, SessionParserSpec.getResourceAsStream(filename).text)

        expect:
        actual == new Session('HH20190322-143439', ldt(2019, MARCH, 22, 14, 34, 39), RING, 2L, 5L,
                11131131L, 500L, 271L, expectedHands)
    }
}
