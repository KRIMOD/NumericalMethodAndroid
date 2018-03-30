package ast;

import static java.util.Objects.requireNonNull;

public abstract class UnaryOperation implements Operation {
	final protected Operation op;
	
	public UnaryOperation(Operation op) {
		this.op = requireNonNull(op);
	}
	
	public Operation getOp(){
		return op;
	}
}
