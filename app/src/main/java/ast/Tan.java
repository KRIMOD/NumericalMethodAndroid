package ast;

public class Tan extends UnaryOperation {

	public Tan(Operation op) {
		super(op);
	}

	public String toString(){
		return "tan(" + op.toString() + ")";
 	}

	@Override
	public Double getNumericResult(Double val) {
		return Math.tan(op.getNumericResult(val));
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (!(o instanceof Tan)) return false;
		Tan abs = (Tan) o;
		return (op.equals(abs.op));
	}
	
	public int hashCode(){
		return 59 * op.hashCode();
	}

	@Override
	public Operation getDerivative() {
		return new Division(op.getDerivative(), new Pow(new Cos(op), new Constant("2")));
	}
}
