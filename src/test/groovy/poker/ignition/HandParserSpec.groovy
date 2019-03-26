package poker.ignition

import spock.lang.Specification

class HandParserSpec extends Specification {

    def 'should be able to parse a hand'() {

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

        def expected = new Hand(9876543210L)
        def actual = new HandParser().parse(hand)

        expect:
        actual == expected
    }

}
