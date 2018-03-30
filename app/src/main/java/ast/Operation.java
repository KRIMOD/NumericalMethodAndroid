package ast;

public interface Operation {
	Double getNumericResult(Double val);
	Operation getDerivative();
}
