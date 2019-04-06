package poker.ignition

import spock.lang.Specification

class HandParserSpec extends Specification {

    def 'should be able to parse a 6 person hand'() {

        def hand = '''Ignition Hand #9876543210 TBL#12345678 HOLDEM No Limit - 2018-02-23 13:44:50
Seat 1: UTG+1 ($7.37 in chips)
Seat 2: UTG+2 ($5.54 in chips)
Seat 3: Dealer ($5.32 in chips)
Seat 4: Small Blind ($4.78 in chips)
Seat 5: Big Blind [ME] ($5 in chips)
Seat 6: UTG ($7.04 in chips)
Dealer : Set dealer [3] 
Small Blind : Small Blind $0.02 
Big Blind  [ME] : Big blind $0.05 
*** HOLE CARDS ***
UTG+1 : Card dealt to a spot [8c 4c] 
UTG+2 : Card dealt to a spot [3d 8s] 
Dealer : Card dealt to a spot [5h 5d] 
Small Blind : Card dealt to a spot [Js 3h] 
Big Blind  [ME] : Card dealt to a spot [4d Kh] 
UTG : Card dealt to a spot [2s 5c] 
UTG : Folds
UTG+1 : Raises $0.17 to $0.17
UTG+2 : Folds
Dealer : Calls $0.17 
Small Blind : Folds
Big Blind  [ME] : Folds
*** FLOP *** [9d Jd 3s]
UTG+1 : Checks
Dealer : Checks
*** TURN *** [9d Jd 3s] [Th]
UTG+1 : Checks
Dealer : Checks
*** RIVER *** [9d Jd 3s Th] [Td]
UTG+1 : Bets $0.41 
Dealer : Folds
UTG+1 : Return uncalled portion of bet $0.41 
UTG+1 : Does not show [8c 4c] Show1 [8c] (One pair)
UTG+1 : Hand result $0.39 
Dealer : Seat sit out
*** SUMMARY ***
Total Pot($0.41)
Board [9d Jd 3s Th Td]
Seat+1: UTG+1 $0.39 [Does not show]  
Seat+2: UTG+2 Folded before the FLOP
Seat+3: Dealer Folded on the RIVER
Seat+4: Small Blind Folded before the FLOP
Seat+5: Big Blind Folded before the FLOP
Seat+6: UTG Folded before the FLOP'''

        def expectedSeats = [new Seat(1, Position.UTG_1, false, 737L, 22L, 0L), new Seat(2, Position.UTG_2, false, 554L, 0L, 0L),
                             new Seat(3, Position.DEALER, false, 532L, -17L, 0L), new Seat(4, Position.SMALL_BLIND, false, 478L, -2L, 0L),
                             new Seat(5, Position.BIG_BLIND, true, 500L, -5L, 0L), new Seat(6, Position.UTG, false, 704L, 0L, 0L)]
        def expected = new Hand(9876543210L, new Blinds(2L, 5L), expectedSeats)
        def actual = new HandParser().parse(hand, { s -> new Blinds(2L, 5L) })

        expect:
        actual == expected
    }

    def 'should be able to parse a 9 person hand'() {
        def hand = '''Ignition Hand #6875559237 TBL#37813594 HOLDEM No Limit - 2019-03-25 21:18:46
Seat 1: Small Blind [ME] ($4.95 in chips)
Seat 2: Big Blind ($5 in chips)
Seat 3: UTG ($4.40 in chips)
Seat 5: UTG+1 ($2.17 in chips)
Seat 6: UTG+2 ($11.39 in chips)
Seat 7: UTG+3 ($4.68 in chips)
Seat 8: UTG+4 ($1.43 in chips)
Seat 9: Dealer ($5.42 in chips)
Dealer : Set dealer [9] 
Small Blind  [ME] : Small Blind $0.02 
Big Blind : Big blind $0.05 
*** HOLE CARDS ***
Small Blind  [ME] : Card dealt to a spot [Ts Tc] 
Big Blind : Card dealt to a spot [3d 4d] 
UTG : Card dealt to a spot [2d 6s] 
UTG+1 : Card dealt to a spot [Kd 8h] 
UTG+2 : Card dealt to a spot [9c 5h] 
UTG+3 : Card dealt to a spot [Th 3h] 
UTG+4 : Card dealt to a spot [As 7s] 
Dealer : Card dealt to a spot [Td Js] 
UTG : Folds
UTG+1 : Folds
UTG+2 : Folds
UTG+3 : Folds
UTG+4 : Raises $0.15 to $0.15
Dealer : Calls $0.15 
Small Blind  [ME] : Calls $0.13 
Big Blind : Calls $0.10 
*** FLOP *** [Qs 5c 4h]
Small Blind  [ME] : Bets $0.10 
Big Blind : Folds
UTG+4 : Folds
Dealer : Calls $0.10 
*** TURN *** [Qs 5c 4h] [6h]
Small Blind  [ME] : Bets $0.15 
Dealer : Folds
Small Blind  [ME] : Return uncalled portion of bet $0.15 
Small Blind  [ME] : Does not show [Ts Tc] (One pair)
Small Blind  [ME] : Hand result $0.76 
*** SUMMARY ***
Total Pot($0.80)
Board [Qs 5c 4h 6h ]
Seat+1: Small Blind $0.76 [Does not show]  
Seat+2: Big Blind Folded on the FLOP
Seat+3: UTG Folded before the FLOP
Seat+5: UTG+1 Folded before the FLOP
Seat+6: UTG+2 Folded before the FLOP
Seat+7: UTG+3 Folded before the FLOP
Seat+8: UTG+4 Folded on the FLOP
Seat+9: Dealer Folded on the TURN'''

        def expectedSeats = [new Seat(1, Position.SMALL_BLIND, true, 495L, 51L, 0L), new Seat(2, Position.BIG_BLIND, false, 500L, -15L, 0L),
                             new Seat(3, Position.UTG, false, 440L, 0L, 0L), new Seat(5, Position.UTG_1, false, 217L, 0L, 0L),
                             new Seat(6, Position.UTG_2, false, 1139L, 0L, 0L), new Seat(7, Position.UTG_3, false, 468L, 0L, 0L),
                             new Seat(8, Position.UTG_4, false, 143L, -15L, 0L), new Seat(9, Position.DEALER, false, 542L, -25L, 0L)]
        def expected = new Hand(6875559237L, new Blinds(2L, 5L), expectedSeats)
        def actual = new HandParser().parse(hand, { s -> new Blinds(2L, 5L) })

        expect:
        actual == expected
    }

    def 'should be able to parse a hand with a table deposit'() {
        def hand = '''Ignition Hand #3752288142 TBL#27364369 HOLDEM No Limit - 2018-03-22 19:49:40
Seat 1: UTG ($5.03 in chips)
Seat 2: UTG+1 [ME] ($1.31 in chips)
Seat 3: UTG+2 ($3.21 in chips)
Seat 4: UTG+3 ($2.45 in chips)
Seat 6: Dealer ($5.11 in chips)
Seat 8: Small Blind ($4.91 in chips)
Seat 9: Big Blind ($6.61 in chips)
Dealer : Set dealer [6] 
Small Blind : Small Blind $0.02 
Big Blind : Big blind $0.05 
*** HOLE CARDS ***
UTG : Card dealt to a spot [3s 4h] 
UTG+1  [ME] : Card dealt to a spot [Th 7d] 
UTG+2 : Card dealt to a spot [Ad 2c] 
UTG+3 : Card dealt to a spot [Ac 3h] 
Dealer : Card dealt to a spot [6d Kh] 
Small Blind : Card dealt to a spot [2d 6s] 
Big Blind : Card dealt to a spot [9c 2s] 
UTG : Folds
UTG+1  [ME] : Folds
UTG+2 : Calls $0.05 
UTG+3 : Calls $0.05 
Dealer : Folds
Small Blind : Calls $0.03 
Seat re-join
Big Blind : Checks
*** FLOP *** [Jc Qd Kd]
Small Blind : Checks
UTG+1  [ME] : Table deposit $3.69 
Big Blind : Checks
UTG+2 : Bets $0.10 
UTG+3 : Calls $0.10 
Small Blind : Folds
Big Blind : Folds
*** TURN *** [Jc Qd Kd] [2h]
UTG+2 : Bets $0.13 
UTG+3 : Calls $0.13 
*** RIVER *** [Jc Qd Kd 2h] [9h]
UTG+2 : Checks
UTG+3 : Bets $0.76 
UTG+2 : Folds
UTG+3 : Return uncalled portion of bet $0.76 
UTG+3 : Does not show [Ac 3h] (High Card)
UTG+3 : Hand result $0.63 
UTG : Seat sit out
*** SUMMARY ***
Total Pot($0.66)
Board [Jc Qd Kd 2h 9h]
Seat+1: UTG Folded before the FLOP
Seat+2: UTG+1 Folded before the FLOP
Seat+3: UTG+2 Folded on the RIVER
Seat+4: UTG+3 $0.63 [Does not show]  
Seat+6: Dealer Folded before the FLOP
Seat+8: Small Blind Folded on the FLOP
Seat+9: Big Blind Folded on the FLOP'''

        def expectedSeats = [new Seat(1, Position.UTG, false, 503L, 0L, 0L), new Seat(2, Position.UTG_1, true, 131L, 0L, 369L),
                             new Seat(3, Position.UTG_2, false, 321, -28L, 0L), new Seat(4, Position.UTG_3, false, 245L, 35L, 0L),
                             new Seat(6, Position.DEALER, false, 511L, 0L, 0L), new Seat(8, Position.SMALL_BLIND, false, 491L, -5L, 0L),
                             new Seat(9, Position.BIG_BLIND, false, 661L, -5L, 0L)]
        def expected = new Hand(3752288142L, new Blinds(2L, 5L), expectedSeats)
        def actual = new HandParser().parse(hand, { s -> new Blinds(2L, 5L) })

        expect:
        actual == expected
    }

}
