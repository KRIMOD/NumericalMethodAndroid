package ast;

public class Asin extends UnaryOperation {

	public Asin(Operation op) {
		super(op);
	}

	public String toString() {
		return "asin(" + op.toString() + ")";
	}

	@Override
	public Double getNumericResult(Double val) {
		return Math.asin(op.getNumericResult(val));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Asin))
			return false;
		Asin abs = (Asin) o;
		return (op.equals(abs.op));
	}

	public int hashCode() {
		return 13 * op.hashCode();
	}

	@Override
	public Operation getDerivative() {
		return new Division(op.getDerivative(),
				new Sqrt(new Subtraction(new Constant("1"), new Pow(op, new Constant("2")))));
	}
}
