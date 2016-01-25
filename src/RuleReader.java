import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class RuleReader {
	public static ArrayList<Rule> read(String path) throws FileNotFoundException{
		String s = "";
		ArrayList<Rule> result = new ArrayList<Rule>();
		Scanner in = new Scanner(new File(path));
		while(in.hasNext()){
			s = in.nextLine();
		//	System.out.println(s);
			String[] parts = s.split("->");
			String[] parts2 = parts[1].split("\\|");
			for (int i = 0; i < parts2.length; ++i){
				Rule newRule = new Rule(parts[0].charAt(0), parts2[i].replaceAll(" ", ""), 0);
				if (newRule.getTo() != ""){
					//System.out.println(newRule.getTo());
					result.add(newRule);
				}
			}
		}
		in.close();
		return result;
	}

}
