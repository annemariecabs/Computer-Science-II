/**
 * This interface was created as a framework for classes created to model expressions 
 * in prefix, postfix and infix form. I was given this class to use for my assignment and 
 * I generated the Javadocs for the class
 * 
 * @author Mrs. Teresa Kelly
 *
 */
public interface Expressions {
	
	/**
	 * Builds a tree to represent the expression using a String[], where each item 
	 * of the array is an element in an expression.
	 * 
	 * @param exp the expression broken up into an array
	 * @return a TreeNode that is the root of the tree that was built to represent the 
	 * 	expression
	 */
	TreeNode buildTree(String[] exp);
	
	/**
	 * Evaluates the expression held in a tree in the class
	 * 
	 * @return the result of the expression sent into buildTree
	 */
	int evalTree();

	/**
	 * Converts the current expression into prefix notation
	 * 
	 * @return a String representing the expression in prefix notation
	 */
	String toPrefixNotation();
	
	/**
	 * Converts the current expression into infix notation with parentheses to help show
	 * the order of operations of infix notation
	 * 
	 * @return a String representing the expression in infix notation
	 */
	String toInfixNotation();

	/**
	 * Converts the current expression into postfix notation
	 * 
	 * @return a String representing the expression in postfix notation
	 */
	String toPostfixNotation();
	
	/**
	 * Uses a stack to evaluate the expression given by exp, rather than building a tree
	 * and evaluating the tree.
	 * 
	 * @param exp the expression to be evaluated where each item of the String[] represents 
	 * 	an item in the expression
	 * @return the result of the expression
	 */
	int postfixEval(String[] exp);
}