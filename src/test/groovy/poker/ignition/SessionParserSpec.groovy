package poker.ignition

import spock.lang.Specification

import java.time.LocalDateTime
import java.time.Month

import static java.time.Month.MARCH
import static poker.ignition.SessionType.RING

class SessionParserSpec extends Specification {

    def 'should parse correct session types'() {

        def actual = new SessionParser().parse('HH20190322-143439 - 3568450 - RING - $0.02-$0.05 - HOLDEM - NL - TBL No.11131131.txt')

        expect:
        actual == new Session('HH20190322-143439', LocalDateTime.of(2019, MARCH, 22, 14, 34, 39), RING, 2L, 5L, 11131131L)
    }
}
