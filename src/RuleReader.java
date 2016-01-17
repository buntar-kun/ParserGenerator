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
			s = in.nextLine() + "\r\n";
			String[] parts = s.split("->");
			Rule newRule = new Rule(parts[0].charAt(0), parts[1].replaceAll(" ", ""), 0);
			result.add(newRule);
		}
		in.close();
		return result;
	}

}
