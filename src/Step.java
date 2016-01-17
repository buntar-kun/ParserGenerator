
public class Step {
	private Rule[] convolution;
	private Rule[] translation;
	private Rule[] prediction;
	void setOptions(Rule[] A, Rule[] B, Rule[] C){
		this.convolution = A;
		this.translation = B;
		this.prediction = C;
	}
}
