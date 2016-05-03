package LaneScoring;
//
public class Spare implements ScoringState {
    @Override
    public void getScore(int ball, int[] scores, int[][] cumulativeScore, int bowlerIndex) {
        int prev = (ball == 0|| ball  == 1? 0 : cumulativeScore[bowlerIndex][ball/2-1]);
//        System.out.println("Prev: " + prev );
		int toFrame = ball/2 == 10 ? 9 : ball/2;
        if(ball == 20){
            prev = cumulativeScore[bowlerIndex][ball/2-2];
            cumulativeScore[bowlerIndex][toFrame] = prev + scores[ball] + scores[ball-1] + scores[ball-2];
        } else if(scores[ball+2] != -1){
            cumulativeScore[bowlerIndex][toFrame] = prev + 10 + scores[ball+2];
        }else{
            cumulativeScore[bowlerIndex][toFrame] = prev + 10;
        }
    }
}
