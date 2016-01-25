import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.util.stream.Stream;


public class Earley {
	
	public static Rule[] getRulesBeginingWith(ArrayList<Rule> list, char ch){
		Vector<Rule> resList = new Vector<Rule>();
		for (int i = 0; i < list.size(); ++i){
			if(list.get(i).getFrom() == ch)
				resList.add(list.get(i));
		}
		Rule[] supportArray = new Rule[resList.size()];
		resList.toArray(supportArray);
		return supportArray;
	}
	
	
	
	final static char FirstNonTerminal = 'P';
	final static int sizeOfAlphabet = 26;
	final static int firstSymb = Character.getNumericValue('A');
	final static int lastSymb = Character.getNumericValue('Z');
	
	static boolean isNonTerminal (char ch){
		if (firstSymb <= Character.getNumericValue(ch) && Character.getNumericValue(ch) <= lastSymb && Character.isUpperCase(ch))	
			return true;
		else
			return false;
	}
	
	public static void parse(String s, ArrayList<Rule> rules){ //TODO: finish'em!
		int n = s.length() + 1;
		Rule firstRule = new Rule(FirstNonTerminal, "S", 0);
		rules.add(0, firstRule);
		ArrayList<ArrayList<Rule>> steps = new ArrayList<ArrayList<Rule>>();
		for (int i = 0; i < n; ++i){
			ArrayList<Rule> currentStep = new ArrayList <Rule>(); 
			if (i == 0){
				currentStep.add(firstRule);
			}
			else {
				char currChar = s.charAt(i - 1);
				ArrayList<Rule> prevStep = steps.get(i - 1);
				for (int j = 0; j < prevStep.size(); ++j){
					Rule tmpRule = new Rule (prevStep.get(j));
					int tmpPos = tmpRule.getPosition();
					String tmpStr = tmpRule.getTo();
					if (tmpPos != tmpStr.length() && currChar == tmpStr.charAt(tmpPos)){
						tmpRule.setPosition(tmpPos + 1);
						tmpRule.genericLink = new Link(i - 1, j);
						currentStep.add(tmpRule);
						
					}
				}
				for (int j = 0; j < currentStep.size(); ++j){
					Rule tmpRule = new Rule(currentStep.get(j));
					int tmpPos = tmpRule.getPosition();
					String tmpStr = tmpRule.getTo();
					int tmpLink = tmpRule.genericLink.line;
					while(tmpLink != -1){
				/*	    System.out.print(tmpStr);
						System.out.print(": ");
						System.out.print(tmpLink);
						System.out.print(" ");
						System.out.print(tmpRule.genericLink.column);
						System.out.println();
						System.out.println();*/
						if (tmpPos == tmpStr.length()){	
						char ClosedCh = tmpRule.getFrom(); //nonTerminal that closed higher
							prevStep = steps.get(tmpLink);
							for (int k = 0; k < prevStep.size(); ++k){ //looking in previous step
								Rule tmpRule2 = new Rule(prevStep.get(k));
								int tmpPos2 = tmpRule2.getPosition();
								String tmpStr2 = tmpRule2.getTo();
							/*	System.out.println(tmpPos2);
								System.out.println(tmpRule2.getFrom());
								System.out.println(tmpStr2);
								System.out.println();*/
								
								if(tmpPos2 != tmpStr2.length() && ClosedCh == tmpStr2.charAt(tmpPos2)){
									tmpRule2.setPosition(tmpPos2 + 1);
									tmpRule2.genericLink = new Link(tmpLink, k);
									currentStep.add(tmpRule2);
								}	
							}
						}
						tmpLink = steps.get(tmpLink).get(tmpRule.genericLink.column).genericLink.line; //oh, such a lovely codestyle!
					}
				}
			}
			
			boolean[] isUsed = new boolean[sizeOfAlphabet];
			boolean f = true;
			while (f){
				f = false;
				for (int k = 0; k < currentStep.size(); ++k){
					Rule tmpRule = new Rule(currentStep.get(k));
					int tmpPos = tmpRule.getPosition();
					if (tmpPos != tmpRule.getTo().length()){
						char symb = tmpRule.getTo().charAt(tmpPos);
						int symbNumb = Character.getNumericValue(symb);
						if (isNonTerminal(symb)){
							symbNumb -= firstSymb;
							if (!isUsed[symbNumb]){
								isUsed[symbNumb] = true;
								f = true;
								Rule[] tmpListOfRules = getRulesBeginingWith(rules, symb);
								for (int l = 0; l < tmpListOfRules.length; ++l){
									tmpListOfRules[l].genericLink = new Link(-1, k);
									tmpListOfRules[l].setPosition(0);
									currentStep.add(tmpListOfRules[l]);
								}
							}
						}
					}
				}
			}
			System.out.print("Step #");
			System.out.println(i);
			for (int jj = 0; jj < currentStep.size(); ++jj){
				Rule cr = currentStep.get(jj);
				System.out.print(cr.getFrom());
				System.out.print("->");
				String currStr = cr.getTo();
				for (int ii = 0; ii < currStr.length(); ++ii){
					if (ii == cr.getPosition()){
						System.out.print('_');
					}
					System.out.print(currStr.charAt(ii));
				}
				if (currStr.length() == cr.getPosition()){
					System.out.print('_');
				}
				System.out.print("; Links: ");
				if (cr.genericLink.line != -1){
					System.out.print(cr.genericLink.line);
					System.out.print(" ");
				}
				System.out.print(cr.genericLink.column);
				System.out.println();
			}
			System.out.println();
			steps.add(currentStep);
		}
	}
}
