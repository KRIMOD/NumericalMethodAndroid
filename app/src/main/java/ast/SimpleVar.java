package ast;

public class SimpleVar implements Operation {
	
	public String toString(){
		return "x";
 	}

	@Override
	public Double getNumericResult(Double val) {
		if (val == null) throw new NullPointerException("Variable set to null!");
		return val;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		return (o instanceof SimpleVar);
	}
	
	public int hashCode(){
		return 43;
	}

	@Override
	public Operation getDerivative(){
		return new Constant("1");
		
	}
}
