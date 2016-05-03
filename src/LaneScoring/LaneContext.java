package LaneScoring;
//
public class LaneContext {

    int ball; 
    int[] scoreArray;
    int[][] cumulScores;
    static LaneContext context;
    
    public static LaneContext getContext(){
        if(context == null){
            context = new LaneContext();
        }
        return context;
    }
    
    public void update(int ball, int[] scoreArray, int[][] cumulScores) {
        this.ball = ball;
        this.scoreArray = scoreArray;
        this.cumulScores = cumulScores;
    }

    public void scoreGame() {
        for (int i = 0; i != 10; i++){
			cumulScores[0][i] = 0;
        }
        boolean endOfScore = false;
        for(int x = 0 ; x < scoreArray.length && !endOfScore; x++ ){
//            System.out.println("<Ball: " + x);
            ScoringState state = pickStratagy(scoreArray,ball);
            state.getScore(x ,scoreArray, cumulScores);
            if(!(state instanceof OpenFrame)) x++;
            if(scoreArray[x+1] == -1) endOfScore = true;
        }
    }

    private ScoringState pickStratagy(int[] scoreArray, int ball){
        if(scoreArray[ball] == 10){
            //If its a Strike
            return new Strike();
        }else if (ball % 2 == 0 &&(scoreArray[ball] + scoreArray[ball+1] == 10 )){
            //If there is a spare
            return new Spare();
        }else{
            //its an open frame
            return new OpenFrame();
        }
    }
}
