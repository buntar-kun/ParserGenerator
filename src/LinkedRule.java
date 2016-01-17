
public class LinkedRule extends Rule{
	private int link;
	LinkedRule(char x, String y, int z, int w) {
		super(x, y, z);
		link = w;
	}
	
	LinkedRule(Rule r, int l){
		super(r);
		this.link = l;
	}
	
	LinkedRule() {}
	
	int getLink(){
		return this.link;
	}
	
	void setLink(int x){
		this.link = x;
	}
	
	void setOptions(Rule r, int l){
		this.setFrom(r.getFrom());
		this.setTo(r.getTo());
		this.setPosition(r.getPosition());
		this.setLink(l);
	}
}
