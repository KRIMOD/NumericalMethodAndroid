package tokenizer;

import java.util.*;

import net.objecthunter.exp4j.function.Function;
import net.objecthunter.exp4j.function.Functions;
import net.objecthunter.exp4j.operator.Operator;
import net.objecthunter.exp4j.shuntingyard.ShuntingYard;
import net.objecthunter.exp4j.tokenizer.Token;

/**
 * Factory class for {@link Expression} instances. This class is the main API entrypoint. Users should create new
 * {@link Expression} instances using this factory class.
 */
public class ExpressionParser {

    private final String expression;

    private final Map<String, Function> userFunctions;

    private final Map<String, Operator> userOperators;

    private final Set<String> variableNames;

    private boolean implicitMultiplication = true;

    /**
     * Create a new ExpressionBuilder instance and initialize it with a given expression string.
     * @param expression the expression to be parsed
     */
    public ExpressionParser(String expression) {
        if (expression == null || expression.trim().length() == 0) {
            throw new IllegalArgumentException("Expression can not be empty");
        }
        this.expression = expression;
        this.userOperators = new HashMap<String, Operator>(4);
        this.userFunctions = new HashMap<String, Function>(4);
        this.variableNames = new HashSet<String>(4);
    }

    /**
     * Add a {@link Function} implementation available for use in the expression
     * @param function the custom {@link Function} implementation that should be available for use in the expression.
     * @return the ExpressionBuilder instance
     */
    public ExpressionParser function(Function function) {
        this.userFunctions.put(function.getName(), function);
        return this;
    }

    /**
     * Add multiple {@link Function} implementations available for use in the expression
     * @param functions the custom {@link Function} implementations
     * @return the ExpressionBuilder instance
     */
    public ExpressionParser functions(Function... functions) {
        for (Function f : functions) {
            this.userFunctions.put(f.getName(), f);
        }
        return this;
    }

    /**
     * Add multiple {@link Function} implementations available for use in the expression
     * @param functions A {@link List} of custom {@link Function} implementations
     * @return the ExpressionBuilder instance
     */
    public ExpressionParser functions(List<Function> functions) {
        for (Function f : functions) {
            this.userFunctions.put(f.getName(), f);
        }
        return this;
    }

    /**
     * Declare variable names used in the expression
     * @param variableNames the variables used in the expression
     * @return the ExpressionBuilder instance
     */
    public ExpressionParser variables(Set<String> variableNames) {
        this.variableNames.addAll(variableNames);
        return this;
    }

    /**
     * Declare variable names used in the expression
     * @param variableNames the variables used in the expression
     * @return the ExpressionBuilder instance
     */
    public ExpressionParser variables(String ... variableNames) {
        Collections.addAll(this.variableNames, variableNames);
        return this;
    }

    /**
     * Declare a variable used in the expression
     * @param variableName the variable used in the expression
     * @return the ExpressionBuilder instance
     */
    public ExpressionParser variable(String variableName) {
        this.variableNames.add(variableName);
        return this;
    }

    public ExpressionParser implicitMultiplication(boolean enabled) {
        this.implicitMultiplication = enabled;
        return this;
    }

    /**
     * Add an {@link Operator} which should be available for use in the expression
     * @param operator the custom {@link Operator} to add
     * @return the ExpressionBuilder instance
     */
    public ExpressionParser operator(Operator operator) {
        this.checkOperatorSymbol(operator);
        this.userOperators.put(operator.getSymbol(), operator);
        return this;
    }

    private void checkOperatorSymbol(Operator op) {
        String name = op.getSymbol();
        for (char ch : name.toCharArray()) {
            if (!Operator.isAllowedOperatorChar(ch)) {
                throw new IllegalArgumentException("The operator symbol '" + name + "' is invalid");
            }
        }
    }

    /**
     * Add multiple {@link Operator} implementations which should be available for use in the expression
     * @param operators the set of custom {@link Operator} implementations to add
     * @return the ExpressionBuilder instance
     */
    public ExpressionParser operator(Operator... operators) {
        for (Operator o : operators) {
            this.operator(o);
        }
        return this;
    }

    /**
     * Add multiple {@link Operator} implementations which should be available for use in the expression
     * @param operators the {@link List} of custom {@link Operator} implementations to add
     * @return the ExpressionBuilder instance
     */
    public ExpressionParser operator(List<Operator> operators) {
        for (Operator o : operators) {
            this.operator(o);
        }
        return this;
    }

    /**
     * Build the {@link Expression} instance using the custom operators and functions set.
     * @return an {@link Expression} instance which can be used to evaluate the result of the expression
     */
    public Token[] build() {
        if (expression.length() == 0) {
            throw new IllegalArgumentException("The expression can not be empty");
        }
        /* set the contants' varibale names */
        variableNames.add("pi");
        variableNames.add("π");
        variableNames.add("e");
        variableNames.add("φ");
        /* Check if there are duplicate vars/functions */
        for (String var : variableNames) {
            if (Functions.getBuiltinFunction(var) != null || userFunctions.containsKey(var)) {
                throw new IllegalArgumentException("A variable can not have the same name as a function [" + var + "]");
            }
        }
        Token[] a = ShuntingYard.convertToRPN(this.expression, this.userFunctions, this.userOperators,
                this.variableNames, this.implicitMultiplication);    
        
        return a;
    }

}
