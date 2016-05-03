package LaneScoring;
//
public class OpenFrame implements ScoringState {
    @Override
    public void getScore(int ball, int[] scores, int[][] cumulativeScore) {
        int prev = (ball == 0|| ball  == 1? 0 : cumulativeScore[0][ball/2-1]);
//        System.out.println("Prev: " + prev );
        if(ball % 2 == 1) prev += scores[ball-1];
		int toFrame = ball/2 == 10 ? 9 : ball/2;
        cumulativeScore[0][toFrame] = prev + scores[ball];
    }
}
