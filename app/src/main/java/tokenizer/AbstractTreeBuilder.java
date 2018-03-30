package tokenizer;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import ast.*;
import net.objecthunter.exp4j.tokenizer.*;

public class AbstractTreeBuilder {
	private String f;
	private static Iterator <Token> it = null;
	
	public AbstractTreeBuilder(String f){
		if (f == null) throw new IllegalArgumentException("Invalid expression");
		this.f = f;
		init();
	}
	
	private void init(){
		ExpressionParser expBuilder = new ExpressionParser(f);
		List <Token> li = Arrays.asList(expBuilder.variable("x").build());
		Collections.reverse(li);
		it = li.iterator();
	}
	
	private Operation getFunction(Token t) throws TokenizerException
	{
		FunctionToken token = (FunctionToken) t;
		switch (token.getFunction().getName())
		{
			case "acos": return new Acos(getTree());
			case "asin": return new Asin(getTree());
			case "atan": return new Atan(getTree());
			case "log": return new Log(getTree());
			case "cos": return new Cos(getTree());
			case "sin": return new Sin(getTree());
			case "sqrt": return new Sqrt(getTree());
			case "tan": return new Tan(getTree());
			case "exp": return new Exp(getTree());
			case "abs": return new Abs(getTree());
			default: throw new TokenizerException("Function error");
		}
	}
	
	private Operation getOperator(Token t) throws TokenizerException
	{
		Operation right = getTree();
		Operation left = getTree();

		switch (((OperatorToken)t).getOperator().getSymbol())
		{			
			case "+":	return new Addition(left, right); 
			case "-":	return new Subtraction(left, right);
			case "*":	return new Product(left, right); 
			case "/":	return new Division(left, right);
			case "^":	return new Pow(left, right);
			default: throw new TokenizerException("Function error");
		}
	}
	
	public Operation getTree() throws TokenizerException
	{
		Token t = it.next();
		
		switch (t.getType()){
			case Token.TOKEN_FUNCTION:	return getFunction(t);	
			case Token.TOKEN_NUMBER:	return new Constant(""+((NumberToken)t).getValue());	
			case Token.TOKEN_OPERATOR:	return getOperator(t);								
			case Token.TOKEN_PARENTHESES_OPEN: return getTree();
			case Token.TOKEN_PARENTHESES_CLOSE: return getTree();
			case Token.TOKEN_VARIABLE: return new SimpleVar();
			default: throw new TokenizerException("Invalid expression");
		}
	}
}
