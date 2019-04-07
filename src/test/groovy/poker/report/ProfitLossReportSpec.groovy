package poker.report

import spock.lang.Specification

class ProfitLossReportSpec extends Specification {

    def 'should return correct profit/loss report'() {

        def expected = '''10811 2019-03-24T12:54:39
11160 2019-03-25T21:10:37
10712 2019-03-27T18:40:35
10212 2019-03-27T20:28:44
10240 2019-03-29T18:58:59
9740 2019-03-29T20:23:35
9783 2019-03-29T21:08:45
10748 2019-03-29T21:49:50
11245 2019-03-30T20:13:33
10745 2019-04-01T20:09:22
10511 2019-04-01T21:20:35
10219 2019-04-02T10:29:09
10150 2019-04-02T19:43:06
10178 2019-04-02T19:56:09
10248 2019-04-02T20:45:20
10670 2019-04-03T20:39:02
10592 2019-04-04T19:53:33
10830 2019-04-04T20:53:08
11127 2019-04-05T21:08:13
'''

        def actual = new ProfitLossReporter(new File("src/test/resources/poker/ignition/regression"), 11002L)
                .getReport()

        expect:
        actual == expected

    }
}
