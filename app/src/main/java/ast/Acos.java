package ast;

public class Acos extends UnaryOperation {

	public Acos(Operation op) {
		super(op);
	}

	public String toString() {
		return "acos(" + op.toString() + ")";
	}

	@Override
	public Double getNumericResult(Double val) {
		return Math.acos(op.getNumericResult(val));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Acos))
			return false;
		Acos abs = (Acos) o;
		return (op.equals(abs.op));
	}

	public int hashCode() {
		return 11 * op.hashCode();
	}

	@Override
	public Operation getDerivative() {
		return new Negate(new Division(op.getDerivative(),
				new Sqrt(new Subtraction(new Constant("1"), new Pow(op, new Constant("2"))))));
	}
}
