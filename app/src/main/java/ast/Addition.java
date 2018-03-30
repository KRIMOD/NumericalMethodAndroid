package ast;

public class Addition extends BinaryOperation {

	public Addition(Operation left, Operation right) {
		super(left, right);
	}

	public Operation getLeft(){
		return left;
	}
	
	public Operation getRight(){
		return right;
	}
	
	@Override
	public String toString(){
		return left.toString() + "+" + right.toString();
	}
	
	public Double getNumericResult(Double val)
	{
		return left.getNumericResult(val) + right.getNumericResult(val);
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (!(o instanceof Addition)) return false;
		Addition abs = (Addition) o;
		return (left.equals(abs.left) && right.equals(abs.right));
	}
	
	public int hashCode(){
		return 61 * (left.hashCode() + right.hashCode());
	}

	@Override
	public Operation getDerivative() {
		return new Addition(left.getDerivative(), right.getDerivative());
	}
}
