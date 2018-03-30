package ast;

public class Abs extends UnaryOperation {
	
	public Abs(Operation op) {
		super(op);
	}
	
	public String toString(){
		return "|" + op.toString() + "|";
 	}

	@Override
	public Double getNumericResult(Double val) {
		return Math.abs(op.getNumericResult(val));
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (!(o instanceof Abs)) return false;
		Abs abs = (Abs) o;
		return (op.equals(abs.op));
	}
	
	public int hashCode(){
		return 7 * op.hashCode();
	}

	@Override
	public Operation getDerivative() {
		Operation Numerator = new Product(new Abs(op), op.getDerivative());
		return new Division(Numerator, op);
	}
}
