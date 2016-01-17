import java.util.Arrays;
import java.util.Vector;
import java.util.stream.Stream;


public class Earley {
	
	public Rule[] getRulesBeginingWith(Rule[] list, char ch){
		Vector<Rule> resList = new Vector<Rule>();
		for (int i = 0; i < list.length; ++i){
			if(list[i].getFrom() == ch)
				resList.add(list[i]);
		}
		Rule[] supportArray = new Rule[resList.size()];
		resList.toArray(supportArray);
		return supportArray;
	}
	
	final char FirstNonTerminal = 'S';
	
	
	
	void parse(String s, Rule[] rules){ //TODO: finish'em!
		int n = s.length() + 1;
		Step[] steps = new Step[n];
		Step firstStep = new Step();
		Rule[] firstStepPred = getRulesBeginingWith(rules, FirstNonTerminal);
		LinkedRule[] fSP = new LinkedRule[firstStepPred.length];
		for (int i = 0; i < firstStepPred.length; i++)
			fSP[i].setOptions(firstStepPred[i], 0);
		firstStep.setOptions(null, null, fSP); //TODO: EPS-moves
		for (int j = 1; j < n; j++){
			char currCh = s.charAt(j - 1);
			
			//TODO: convolutions, translation, predictions
		}
	}
}
