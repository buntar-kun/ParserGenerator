import java.io.FileNotFoundException;
import java.util.ArrayList;


public class ParserGenerator {
	final static String defaultPath = "src/rules.txt";
	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<Rule> T = RuleReader.read(defaultPath);
	}
}
