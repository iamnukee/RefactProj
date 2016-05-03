package LaneScoring;

public class Strike implements ScoringState {
    @Override
    public void getScore(int ball, int[] scores, int[][] cumulativeScore) {
        int prev = (ball == 0 || ball  == 1? 0 : cumulativeScore[0][ball/2-1]);
//        System.out.println("Prev: " + prev );
		int toFrame = ball/2 == 10 ? 9 : ball/2;
        if(scores[ball+2] == -1){
            cumulativeScore[0][toFrame] = prev + 10;
        }else{
            int bonus = scores[ball+2];
            if(scores[ball+3] == -1){
                bonus += scores[ball+4] != -1 ? scores[ball+4] : 0 ;
            }else{
                bonus += scores[ball+3];
            }
            cumulativeScore[0][toFrame] = prev + 10 + bonus;
        }
    }
}
