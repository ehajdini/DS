/*
 * Egla Hajdini
 * 
 * Homework assignment 3
 * Data Structures , Spring 2016
 * 
 **********************/
public class driver {

	public static void main(String[] args) {
		In in; // Declaration of In
		Out out; // Declaration of Out
		//out=new Out() ; //Write to console
		out = new Out("output.txt"); // Write to a file
		in = new In("./input.txt"); // Read from the file
		BST bst = new BST(); // Declaration and Instantiation of BST
		int num = in.readInt(); // the number of the nodes
		int cnt = 1;
		
		// Reading from the file the values and inserting them in BST 
		while (!in.isEmpty()) {
			int value = in.readInt();
			bst.insert(value);
			cnt++;
			if(cnt > num)break;
		}
		
		out.print("Size of the BST: ");
		out.println(bst.size());
		
		/**
		 * Getting min and max values of the BST , that will be used by the monkey to 
		 * generate random numbers within this range and calling monkeyBusiness (min,max)
		 */
		
		int max = bst.max();
		int min = bst.min();
		bst.monkeyBusiness(min, max);
		out.println();
		out.println("After MonkeyBusiness");
		out.println("Is this a BST?: " + bst.isBST());
		out.println("In-order traversal of the damaged BST:");
		bst.printInorder(out);
		out.println();
		bst.fix();
		out.println("FIXED");
		out.println("Is this a BST?: " + bst.isBST());
		out.println("In-order traversal of the fixed BST:");
		bst.printInorder(out);
		out.println();
		if(bst.steps * 100 < 10000)out.println("Cost of fixing the tree will be: " + bst.steps * 100 + " LEK");
		else out.println("Cost of fixing the tree will be: " + 10000 + " LEK");
		out.close();
		
	}
}
