
public class Rule {
	private char from;
	private String to;
	private int currentPosition;
	Rule (char x, String y, int z){
		from = x;
		to = y;
		currentPosition = z;
	}
	
	Rule(){}
	
	Rule(Rule r){
		this.from = r.getFrom();
		this.to = r.getTo();
		this.currentPosition = r.getPosition();
	}
	
	
	char getFrom(){
		return this.from;
	}
	
	void setFrom(char ch){
		this.from = ch;
	}
	
	String getTo(){
		return this.to;
	}
	
	void setTo(String s){
		this.to = s;
	}
	
	int getPosition(){
		return this.currentPosition;
	}
	
	void setPosition(int n){
		this.currentPosition = n;
	}
}
