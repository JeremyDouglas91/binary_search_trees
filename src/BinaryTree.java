/**
* The BinaryTree class comnprises of a binary tree data structure containing nodes 
* with data of generic dataType. The methods provide utility for qurying the height and
* size of the tree, visiting (printing out data on) a particular node, and printing
* information on all nodes in the tree structure by pre order traversal.
*
* @author Hussein Suleman
* @author Jeremy du Plessis
**/
public class BinaryTree<dataType>
{
   BinaryTreeNode<dataType> root;
   
   /**
   * BinaryTree class constructor.
   **/
   public BinaryTree ()
   {
      root = null;
   }
    
   /**
   * getHeight method for assertaining the height of the binary tree data structure.
   * 
   * @return int
   **/
   public int getHeight ()
   {
      return getHeight (root);
   }   
   
   /**
   * getHeight method for assertaining the height of the binary tree data structure.
   *
   * @param BinaryTreeNode<dataType> node
   * @return int
   **/
   public int getHeight ( BinaryTreeNode<dataType> node )
   {
      if (node == null)
         return -1;
      else
         return 1 + Math.max (getHeight (node.getLeft ()), getHeight (node.getRight ()));
   }
   
   /**
   * getSize method for assertaining the size (number of nodes) of the binary tree 
   * data structure.
   * 
   * @return int
   **/
   public int getSize ()
   {
      return getSize (root);
   }   
   
   /**
   * getSize method for assertaining the size (number of nodes) of the binary tree 
   * data structure.
   * 
   * @param BinaryTreeNode<dataType> node
   * @return int
   **/
   public int getSize ( BinaryTreeNode<dataType> node )
   {
      if (node == null)
         return 0;
      else
         return 1 + getSize (node.getLeft ()) + getSize (node.getRight ());
   }
   
   /**
   * visit method for printing out data contained in a node. 
   * 
   * @param BinaryTreeNode<dataType> node
   **/
   public void visit ( BinaryTreeNode<dataType> node )
   {
      System.out.println (node.data);
   }
   
   /**
   * preOrder method for printing out data contained in all nodes, traversing by
   * pre order. 
   **/
   public void preOrder ()
   {
      preOrder (root);
   }

   /**
   * preOrder method for printing out data contained in all nodes, traversing by
   * pre order. 
   * 
   * @param BinaryTreeNode<dataType> node
   **/
   public void preOrder ( BinaryTreeNode<dataType> node )
   {
      if (node != null)
      {
         visit (node);
         preOrder (node.getLeft ());
         preOrder (node.getRight ());
      }   
   }
}
