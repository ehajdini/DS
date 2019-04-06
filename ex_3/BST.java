/*
 * 
 * Homework assignment 3
 * Data Structures , Spring 2016
 * 
 **********************/
/**
 * The BST class will contain the main methods:
 * Insert -> inserting nodes in BST
 * isEmpty-> checks if BST is empty or not
 * min -> returns the min value of BST
 * max -> returns the max value of BST
 * contains -> checks if it contains a specific value
 * get		-> returns the Node that contains a specific value
 * swap  	-> swaps two nodes
 * monkeyBusiness -> messing up the tree
 * fix			  -> repairs the damages in the tree
 * isBST		  -> checks whether it fulfills the conditions of BST
 * 
 * Other methods which will be called from the main methods are described below
 **/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;

public class BST {
	
	public Node root;             // root of BST
	public int N;				  // # of BST nodes
	int steps = 0;				  // # of steps needed to fix the tree
	
	/**
	 * Default constructor */
    public BST() {
    }
    
    /**
     * This method checks whether the BST is empty */
    public boolean isEmpty() {
        return root == null;
    }
    /**
     * This method will return the number of nodes */
    public int size() {
        return N;
    }
    
    /** This method will insert a new Node which will have a specific value,
     * by calling the recursive method put(root , Node)
     * After inserting it into the tree the number of Nodes will be incremented
     * */
    public void insert(int value){
    	Node node = new Node(value);
        if (root == null) {
          root = node;
        }
      root = put(root,node);
      N++;
    }
    
    /** Recursive method put will check for the exact place of 
     * the new node that will be inserted in the tree , by checking its value
     * if value is > than the current node it will search for a place in right subtree
     * if value is < than the current node it will search for a place in left subtree
     * */
    private Node put(Node x,Node node){
    	if(x == null)
    			return node;
    	if(node.value < x.value) 
    			x.left = put(x.left,node);
    	else if(node.value > x.value)
    			x.right = put(x.right,node);
    	else 	x.value = node.value;
    	return x;
    }
    /**
     * min() method will call the recursive min(root) method
     * @return the min value of BST
     */
    public int min() {
        if (isEmpty()) throw new NoSuchElementException("called min() , but tree has no elements");
        return min(root).value;
    } 
    /**
     * Recursively search for the minimum value of the BST
     * always by checking the left subtree
     */
    private Node min(Node x) { 
        if (x.left == null) return x; 
        else                return min(x.left); 
    } 
    
    /**
     * max() method will call the recursive max(root) method
     * @return the max value of BST
     */
    public int max() {
        if (isEmpty()) throw new NoSuchElementException("called max(), but tree has no elementse");
        return max(root).value;
    } 
    
    /**
     * Recursively search for the maximum value of the BST
     * always by checking the right subtree
     */
    private Node max(Node x) {
        if (x.right == null) return x; 
        else                 return max(x.right); 
    } 
    
   /**
    * boolean method contains will 
    * return true if the value from the get(int) function is not null
    * otherwise it will return false
    */
    public boolean contains(Integer x){
    	if(x == null)throw new NullPointerException("null arg");
    	return get(x) != null;
    }
   /**
    * this method will call the recursive get(Node ,Integer) method
    */
   public Node get(int value){
	   return get(root, value);
   }
   /**
    * This is a recursive method which will search for a specific value
    * in the BST , in the same fashion like insert method and will return 
    * the node which contains that value
    */
   private Node get(Node x,Integer value){
	   if (x == null) return null;
     int cmp = value.compareTo(x.value);
     if      (cmp < 0) return get(x.left, value);
     else if (cmp > 0) return get(x.right, value);
     else              return x;
   }
    
   /**
    * Swap method will change only the values of the Nodes,
    * by keeping a temporary Integer value  
    */
   public void swap (Node a, Node b){
	   int temp = a.value;
	   a.value = b.value;
	   b.value = temp;
       }
   
   /**
    * This method will be used from monkey to swap the nodes in BST
    * Monkey will swap 11 pairs of numbers 
    * Range of the numbers will be [min(),max()] of BST 
    * (min() and max() will be called in the driver class)
    * He will generate random numbers till the tree contains a pair of such values
    * 
    * !!! pair[] created here is only used by monkey , there's no cheating
    */
    public void monkeyBusiness(int a, int b){
    	Random ran = new Random();
    	pairs []pair=new pairs[11];
    	for(int i = 0 ; i < 11 ; i++){
    		int randomInt_1 = a + ran.nextInt( b-a+1 );	
    		int randomInt_2 = a + ran.nextInt( b-a+1 );	
    	    	while(true){
    	    		if(!this.contains(randomInt_1) || !this.contains(randomInt_2) || (randomInt_2-randomInt_1 == 0)){
    	    			randomInt_1 = a + ran.nextInt( b-a+1 );	
    	    	    	randomInt_2 = a + ran.nextInt( b-a+1 );
    	    		}
    	    		if(this.contains(randomInt_1) && this.contains(randomInt_2) && (randomInt_2-randomInt_1 != 0)){
    	    		break;
    	    		}
    	    			
    	    	}
    	    	pair[i] = new pairs(get(randomInt_1) , get(randomInt_2));
    		}
    	
    	for(int i=0 ; i<11 ; i++){
    		swap(pair[i].getX() , pair[i].getY());
    	}
}
    /**
     *  printInorder method will call the Recursive method  printInorder(...,...);
     */
    public void printInorder(Out out){
    	printInOrder(root,out);
    	out.println("");
    }
    /**
     * InOrder traversal is used to sort the BST
     * Firstly it will check :eftSubtree
     * print
     * Check RightSubtree
     */
    private void printInOrder(Node current,Out out){
    	if(current == null)return;
    	printInOrder(current.left, out);
    	out.print(current.value + ", ");
    	printInOrder(current.right, out);
    }
    
    /**
     * This method will be used by fix method
     * It will store the values of the damaged tree , 
     * by performing the inorder traveral of the BST;
     * inorder[] will be the array used to store this values
     *  
     * 	-Firsty , it will store the left subtree values
     * 	-Then , it will store the root value 
     * 	-Next , increment the index
     * 	-Last , store the right subtree values
     */
    private void storeInOrderRec(Node current,Integer[] inorder,int index[]){
    	if(current == null)return;
    	storeInOrderRec(current.left,inorder,index);
    	inorder[index[0]++] = current.value;
    	storeInOrderRec(current.right,inorder,index);
    }
    
    	/**
    	 * This method will fix the tree by :
    	 * 1- Creating an array inorder[] that stores values of damaged tree (using in-order traversal)
    	 * 2- Sorting inorder[]
    	 * 3- Replacing tree node values by comparing the values with inorder[] array and 
    	 * 	  using in-order traversal 
    	 */
      	public void fix(){
      		Integer inorder[] = new Integer[this.size()];
      		int[] index = new int[1];
      		
      		storeInOrderRec(root, inorder, index);
      		Arrays.sort(inorder);		
      		index[0] = 0;
      		updateNodeValues(root,inorder,index);      	
      		}
      
      	/**
      	 * This method will be used again by fix method
      	 * It will perform the in-order traversal of the tree ,
      	 * and it will change the values of the damaged tree 
      	 * 	
      	 * 	-Firstly it will update the left subtree
      	 * 	-Secondly it will update the root data ,
      	 * 	 if it is different from the current value in inorder[] array
      	 * 	-Then, it will increment the index of the array
      	 * 	-Finally it will update the right subtree
      	 * 
      	 * 	Will be counted as step :
      	 * 	1-Invocation of updateNodeValues(...,...,...)each time , a)for left and b)right subtree 
      	 * 	2-Checking each node (if statement)
      	 * 	3-Replacing the node value if it is different from the current value in inorder[] array
      	 */
      	private void updateNodeValues(Node current, Integer[] inorder, int[] index) {
      				if(current == null)return;
      				updateNodeValues(current.left,inorder,index);
      				steps++;  // condition 1a
      				steps++;  // condition 2
      				if(current.value != inorder[index[0]]){
      					current.value = inorder[index[0]];
      					steps++; //condition 3
      				}
          			index[0]++;
          			steps++; //condition 1b
          			updateNodeValues(current.right,inorder,index);    				
      			}

    /**
     * Calls the recursive isBinarySearchTree method
     */
    public boolean isBST(){
    	return isBST(root,this.min(),this.max()); 	
    }
    
    /**
     * This method will recursively check left and right subtrees .
     * If one of the conditions is violated it will return false
     * Otherwise -> true
     */
	private boolean isBST(Node x, int min, int max) {
		if(x == null) return true;
		if(x.value < min || x.value > max)return false;
		return (isBST(x.left,min,x.value+1)) && 
				(isBST(x.right,x.value-1,max));
	}
	
}
