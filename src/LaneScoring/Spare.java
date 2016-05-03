package LaneScoring;
//
public class Spare implements ScoringState {
    @Override
    public void getScore(int ball, int[] scores, int[][] cumulativeScore) {
        int prev = (ball == 0|| ball  == 1? 0 : cumulativeScore[0][ball/2-1]);
//        System.out.println("Prev: " + prev );
		int toFrame = ball/2 == 10 ? 9 : ball/2;
        if(scores[ball+2] != -1){
            cumulativeScore[0][toFrame] = prev + 10 + scores[ball+2];
        }else{
            cumulativeScore[0][toFrame] = prev + 10;
        }
    }
}
