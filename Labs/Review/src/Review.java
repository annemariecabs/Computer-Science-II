import java.util.Arrays;
import java.util.Stack;

public class Review {
	//TODO is this too redundant?
	
	
	// Replaces the value in each node with the sum of the values
	// in all the nodes of its subtree.
	// Precondition: the nodes of the tree rooted at root
//	               hold Integer values.
	public static void accumulate(TreeNode root) {
		accumulateHelper(root);
		
	}
	
	public static int accumulateHelper(TreeNode node) {
		if(node == null) return 0;
		
		int value = (int) node.getValue();
		int newVal = 0;

		if(node.getLeft() != null)
			newVal += accumulateHelper(node.getLeft()) + (int) node.getLeft().getValue();

		if(node.getRight() != null)
			newVal += accumulateHelper(node.getRight()) + (int) node.getRight().getValue();

		node.setValue(newVal);
		return value;
			
 	}
	
	
	public static Object[] toArray(TreeNode root, int numNodes) {
		Object[] tree = new Object[numNodes + 1];
		
		toArray(root, tree, 1);
		
		return tree;
	}
		
	private static void toArray(TreeNode node, Object[] values, int i) {
		if(node == null) return;
		
		values[i] = node.getValue();
		
		toArray(node.getLeft(), values, 2*i);
		toArray(node.getRight(), values, 2*i + 1);
	
		
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.setLeft(new TreeNode(2));
		root.setRight(new TreeNode(2));
		root.getLeft().setLeft(new TreeNode(3));
		root.getLeft().setRight(new TreeNode(3));
		
		System.out.println(Arrays.toString(toArray(root, 5)));
		
		accumulate(root);
		
		System.out.println((int) (root.getValue()) == 2 + 2 + 3 + 3);
		System.out.println(Arrays.toString(toArray(root, 5)));
		
	}

}
