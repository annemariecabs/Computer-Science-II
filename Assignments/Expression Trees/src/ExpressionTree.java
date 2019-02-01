import java.util.Stack;

/**
 * The ExpressionTree class is a subclass of TreeNode and implements Expressions.
 * The key methods are: buildTree, evalTree, toPrefixNotation, toInfixNotation,
 * toPostfixNotation, and postfixEval. These methods enable this class to build
 * binary trees to represent expressions, evaluate said trees, and convert them
 * into prefix, infix and postfix notation. Finally, this class also has the postfixEval
 * method, which evaluates the expression using a stack rather than recursively like
 * evalTree, toPrefixNotation, toInfixNotation, and toPostfixNotation. 
 * 
 * @author AnneMarie Caballero (<a href="https://github.com/annemariecabs">annemariecabs</a>)
 * @see <a href=https://annemariecabs.github.io/Computer-Science-II/Javadocs/TreeNode.html>TreeNode</a>
 */

public class ExpressionTree extends TreeNode implements Expressions {
	
	/**
	 * A plus sign
	 */
	private static final String PLUS_SIGN = "+";
	
	/**
	 * A multiplication sign
	 */
	private static final String TIMES_SIGN = "*";
	
	/**
	 * An empty string
	 */
	private static final String EMPTY = "";
	
	/**
	 * A space
	 */
	private static final String SPACE = " ";
	
	/**
	 * An open parentheses
	 */
	private static final String OPEN_PAREN = "(";
	
	/**
	 * A closed parentheses
	 */
	private static final String CLOSED_PAREN = ")";
	
	/**
	 * Constructs a leaf, an ExpressionTree with a value but no left or right 
	 * node, using the superclass's similar constructor method.
	 * 
	 * @param initValue the value of the ExpressionTree created
	 */
	public ExpressionTree(String initValue) {
		super(initValue);
	}

	/**
	 * Constructs an ExpressionTree with a value, and using a left and right node,
	 * by using the superclass's constructor.
	 * 
	 * @param initValue the value of this ExpressionTree
	 * @param initLeft the left node of this ExpressionTree
	 * @param initRight the right node of this ExpressionTree
	 */
	public ExpressionTree(String initValue, TreeNode initLeft, TreeNode initRight) {
		super(initValue, initLeft, initRight);
	}
	
	/**
	 * Constructs an ExpressionTree by copying the values of the TreeNode
	 * passed to it
	 * 
	 * @param treeNode the TreeNode to be copied
	 */
	public ExpressionTree(TreeNode treeNode) {
		super(treeNode.getValue(), treeNode.getLeft(), treeNode.getRight());
	}


	/**
	 * Builds a tree to represent the expression using a String[], where each item 
	 * of the array is an element in a postfix expression.
	 * 
	 * @param exp a String[] holding the elements of an expression
	 * @return a TreeNode representing the root of the expression
	 */
	@Override
	public TreeNode buildTree(String[] exp) {
		Stack<TreeNode> stk = new Stack<TreeNode>();
		TreeNode tmpL, tmpR, tmpRoot;
		int i;

		for(String s: exp) {
			if(s.equals(EMPTY))
				continue;
			
			try {
				i = Integer.parseInt(s);
				stk.push(new TreeNode(EMPTY + i));
			}
			catch (NumberFormatException n) {
				tmpR = stk.pop();
				tmpL = stk.pop();
				tmpRoot = new TreeNode(s, tmpL, tmpR);
				stk.push(tmpRoot);
			}
		}

		return stk.pop();

	}
	
	/**
	 * Recursively evaluates the tree starting with the root.
	 * 
	 * @return an integer result of the expression represented by the tree
	 */
	@Override
	public int evalTree() {
		int num;
		ExpressionTree left, right;
		
		try {
			num = Integer.parseInt(EMPTY + this.getValue());

			return num;
		}

		catch (NumberFormatException n) {
			left = new ExpressionTree(getLeft());
			right = new ExpressionTree(getRight());
			if(TIMES_SIGN.equals(this.getValue()))
				return left.evalTree() * right.evalTree();
			else
				return left.evalTree() + right.evalTree();
		}
	}
	
	/**
	 * Recursively converts the ExpressionTree to a String in prefix notation.
	 * 
	 * @return a String representing the expression in prefix notation
	 */
	@Override
	public String toPrefixNotation() {
		int num;
		ExpressionTree left, right;

		try {
			num = Integer.parseInt(EMPTY + this.getValue());
			return EMPTY + num;
		}
		catch(NumberFormatException n) {
			left = new ExpressionTree(getLeft());
			right = new ExpressionTree(getRight());
			return getValue() + SPACE + left.toPrefixNotation() + SPACE 
					+ right.toPrefixNotation();
		}
	}
	
	/**
	 * Recursively converts the ExpressionTree to a String in infix notation (including
	 * parentheses around each individual expression within the wider expression).
	 * 
	 * @return a String representing the expression in infix notation
	 */
	@Override
	public String toInfixNotation() {
		int num;
		ExpressionTree left, right;

		try {
			num = Integer.parseInt(EMPTY + this.getValue());
			return EMPTY + num;
		}
		catch(NumberFormatException n) {
			left = new ExpressionTree(getLeft());
			right = new ExpressionTree(getRight());
			
			return OPEN_PAREN + left.toInfixNotation() + SPACE + getValue() 
					+ SPACE + right.toInfixNotation() + CLOSED_PAREN;
		}
	}

	/**
	 * Recursively converts the ExpressionTree to a String in postfix notation.
	 * 
	 * @return a String representing the expression in postfix notation
	 */
	@Override
	public String toPostfixNotation() {
		int num;
		ExpressionTree left, right;

		try {
			num = Integer.parseInt(EMPTY + this.getValue());
			return EMPTY + num;
		}
		catch(NumberFormatException n) {
			left = new ExpressionTree(getLeft());
			right = new ExpressionTree(getRight());
			
			return left.toPostfixNotation() + SPACE + right.toPostfixNotation() + SPACE 
				+ getValue();
		}
	}

	/**
	 * Uses a stack to evaluate the expression given by exp, rather than building a tree
	 * and evaluating the tree.
	 * 
	 * @param exp the expression to be evaluated where each item of the String[] represents 
	 * 	an item in the expression
	 * @return the result of the expression
	 */
	@Override
	public int postfixEval(String[] exp) {
		
		Stack<Integer> stk = new Stack<Integer>();
		
		for(String part: exp) {
			if(part.equals(EMPTY))
				continue;
			
			try {
				stk.push(Integer.parseInt(part));
			}
			catch (NumberFormatException n) {
				if(part.equals(PLUS_SIGN))
					stk.push(stk.pop() + stk.pop());
				else
					stk.push(stk.pop() * stk.pop());
			}
		}
		
		return stk.pop();
	}

}
