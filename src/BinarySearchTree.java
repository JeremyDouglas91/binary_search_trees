/**
* The BinarySearchTree class inherits from the BinaryTree class. It comprises of a binary tree
* data structure containing nodes with data of generic dataType. The methods provide utility for search,
* node insertion, as well as all inherited methods (see BinaryTree class).
*
* @author Hussein Suleman
* @author Jeremy du Plessis
**/
public class BinarySearchTree<dataType extends Comparable<? super dataType>> extends BinaryTree<dataType>
{

   /**
   * Insert method for adding a new node to the binary tree data structure.
   * The number of comparisons are tracked and stored.
   * 
   * @param dataType d
   * @param int[] opCountInsert
   **/
   public void insert ( dataType d, int [] opCountInsert)
   {
      if (root == null)
         root = new BinaryTreeNode<dataType> (d, null, null);
      else
         insert (d, root, opCountInsert);
   }

   /**
   * Insert method for adding a new node to the binary tree data structure.
   * The number of comparisons are tracked and stored. 
   *
   * @param dataType d
   * @param BinaryTreeNode<dataType> node
   * @param opCountinsert
   **/
   public void insert ( dataType d, BinaryTreeNode<dataType> node, int [] opCountInsert )
   {

      opCountInsert[0] += 1;
      if (d.compareTo (node.data) <= 0)
      {
         if (node.left == null)
            node.left = new BinaryTreeNode<dataType> (d, null, null);
         else
            insert (d, node.left, opCountInsert);
      }
      else
      {
         if (node.right == null)
            node.right = new BinaryTreeNode<dataType> (d, null, null);
         else
            insert (d, node.right, opCountInsert);
      }
   }
   
   /**
   * Find method for searching for a node in the binary tree data structure.
   * 
   * @param dataType d
   * @param int[] opCount
   **/
   public BinaryTreeNode<dataType> find ( dataType d , int [] opCountFind)
   {
      if (root == null){
         return null;
      }
      else
         return find (d, root, opCountFind);
   }

   /**
   * Find method for searching for a node in the binary tree data structure.
   * 
   * @param dataType d
   * @param BinaryTreeNode<dataType> node
   * @param int[] opCount
   **/
   public BinaryTreeNode<dataType> find ( dataType d, BinaryTreeNode<dataType> node, int[] opCountFind)
   {
      int check = d.compareTo (node.data);
      opCountFind[0] += 1;

      if (check == 0){ 
         return node;
      }
      else if (check < 0){
         return (node.left == null) ? null : find (d, node.left, opCountFind);
      }
      else{
         return (node.right == null) ? null : find (d, node.right, opCountFind);
      }
   }     
}
