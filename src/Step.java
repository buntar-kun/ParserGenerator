
public class Step {
	private LinkedRule[] convolution;
	private LinkedRule[] translation;
	private LinkedRule[] prediction;
	void setOptions(LinkedRule[] A, LinkedRule[] B, LinkedRule[] C){
		this.convolution = A;
		this.translation = B;
		this.prediction = C;
	}
}
