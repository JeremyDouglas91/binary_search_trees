/**
* The AVLTree class inherits from the BinaryTree class. It comprises of a binary tree
* data structure containing nodes with data of generic dataType. The methods provide utility for search,
* node insertion, as well as methods for balancing the tree(using rotations), and 
* calculating the balance factor.
*
* @author Hussein Suleman
* @author Jeremy du Plessis
**/
public class AVLTree<dataType extends Comparable<? super dataType>> extends BinaryTree<dataType>
{
   /**
   * The height method calculates and returns the height of a subtree.
   * 
   * @param node BinaryTreeNode<dataType>
   * @return int 
   **/
   public int height ( BinaryTreeNode<dataType> node )
   {
      if (node != null)
         return node.height;
      return -1;
   }
   
   /**
   * The balanceFactor method calculates and returns the balance factor of a subtree.
   * 
   * @param node BinaryTreeNode<dataType>
   * @return int 
   **/
   public int balanceFactor ( BinaryTreeNode<dataType> node )
   {
      return height (node.right) - height (node.left);
   }
   
   /**
   * The fixHeight method reassigns the correct height to the sub-tree's height variable.
   * 
   * @param node BinaryTreeNode<dataType>
   **/
   public void fixHeight ( BinaryTreeNode<dataType> node )
   {
      node.height = Math.max (height (node.left), height (node.right)) + 1;
   }
   
   /**
   * The rotateRight method performs a right rotation on the given node.
   * 
   * @param node BinaryTreeNode<dataType>
   **/
   public BinaryTreeNode<dataType> rotateRight ( BinaryTreeNode<dataType> p )
   {
      BinaryTreeNode<dataType> q = p.left;
      p.left = q.right;
      q.right = p;
      fixHeight (p);
      fixHeight (q);
      return q;
   }

   /**
   * The rotateLeft method performs a left rotation on the given node.
   * 
   * @param node BinaryTreeNode<dataType>
   **/
   public BinaryTreeNode<dataType> rotateLeft ( BinaryTreeNode<dataType> q )
   {
      BinaryTreeNode<dataType> p = q.right;
      q.right = p.left;
      p.left = q;
      fixHeight (q);
      fixHeight (p);
      return p;
   }
   
   /**
   * The balance method considers the height of a subtree and balances it if necessary through the necessary rotations.
   * 
   * @param node BinaryTreeNode<dataType>
   **/
   public BinaryTreeNode<dataType> balance ( BinaryTreeNode<dataType> p )
   {
      fixHeight (p);
      if (balanceFactor (p) == 2)
      {
         if (balanceFactor (p.right) < 0)
            p.right = rotateRight (p.right);
         return rotateLeft (p);
      }
      if (balanceFactor (p) == -2)
      {
         if (balanceFactor (p.left) > 0)
            p.left = rotateLeft (p.left);
         return rotateRight (p);
      }
      return p;
   }

   /**
   * The insert method adds a node onto the AVL tree structure.
   * The method tracks the number of comparison operations executed during insertion.
   *
   * @param d dataType 
   * @param opCountInsert int[]
   **/
   public void insert ( dataType d, int[] opCountInsert)
   {
      root = insert (d, root, opCountInsert);
   }
   /**
   * The insert method adds a node onto the AVL tree structure.
   * The method tracks the number of comparison operations executed during insertion.
   *
   * @param d dataType 
   * @param opCountInsert int[]
   * @param node BinaryTreeNode<dataType>
   **/
   public BinaryTreeNode<dataType> insert ( dataType d, BinaryTreeNode<dataType> node, int[] opCountInsert)
   {
      opCountInsert[0] += 1;
      if (node == null)
         return new BinaryTreeNode<dataType> (d, null, null);
      if (d.compareTo (node.data) <= 0)
         node.left = insert (d, node.left, opCountInsert);
      else
         node.right = insert (d, node.right, opCountInsert);
      return balance (node);
   }
   /**
   * The findMin method locates the node with the minmum value in  the AVL tree structure.
   *
   * @param node BinaryTreeNode<dataType> 
   **/
   public BinaryTreeNode<dataType> findMin ( BinaryTreeNode<dataType> node )
   {
      if (node.left != null)
         return findMin (node.left);
      else
         return node;
   }
   /**
   * The removeMin method locates and removes the node with the minmum value in  the AVL tree structure.
   *
   * @param node BinaryTreeNode<dataType> 
   **/
   public BinaryTreeNode<dataType> removeMin ( BinaryTreeNode<dataType> node )
   {
      if (node.left == null)
         return node.right;
      node.left = removeMin (node.left);
      return balance (node);
   }

   /**
   * The find method locates the node with the given value in  the AVL tree structure.
   * The number of comparison operations are recorded.
   *
   * @param d dataType
   * @param opCount int[]
   **/
   public BinaryTreeNode<dataType> find ( dataType d , int[] opCount )
   {
      if (root == null)
         return null;
      else
         return find (d, root, opCount);
   }

   /**
   * The find method locates the node with the given value in  the AVL tree structure.
   * The number of comparison operations are recorded.
   *
   * @param d dataType
   * @param opCount int[]
   * @param node BinaryTreeNode<dataType> 
   **/
   public BinaryTreeNode<dataType> find ( dataType d, BinaryTreeNode<dataType> node, int[] opCount )
   {
      int check = d.compareTo (node.data);
      opCount[0] += 1;

      if (check == 0) 
         return node;
      else if (check < 0)
         return (node.left == null) ? null : find (d, node.left, opCount);
      else
         return (node.right == null) ? null : find (d, node.right, opCount);
   }
   /**
   * The treeOrder method prints the data of each node in the AVL structure.
   **/
   public void treeOrder ()
   {
      treeOrder (root, 0);
   }
   /**
   * The treeOrder method prints the data of each node in the AVL structure.
   *
   * @param node BinaryTreeNode<dataType>
   * @param level int
   **/
   public void treeOrder ( BinaryTreeNode<dataType> node, int level )
   {
      if (node != null)
      {
         for ( int i=0; i<level; i++ )
            System.out.print (" ");
         System.out.println ("\n"+node.data);
         treeOrder (node.left, level+1);
         treeOrder (node.right, level+1);
      }
   }
}

