
public class nonTerminal {
	char name;
	Rule[] rules;
	nonTerminal(String s){
		String tmp[];
		tmp = s.split("->");
		name = tmp[0].charAt(0);
		String[] tmp2 = tmp[1].split("|");
		rules = new Rule[tmp2.length];
		for (int i = 0; i < tmp2.length; ++i){
			Rule currRule = new Rule(name, tmp2[i], 0);
			rules[i] = currRule;
		}
	}
}
