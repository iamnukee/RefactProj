package LaneScoring;
//
public interface ScoringState {

    void getScore(int ball, int[] scores, int[][] cumulativeScore, int bowlerIndex);
}
