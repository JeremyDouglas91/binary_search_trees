/**
* The BinaryTreeNode class contains data, forms part of a binary tree data structure.
* Methods provide utility for returning child nodes, left and right.
*
* @author Hussein Suleman
* @author Jeremy du Plessis
**/
public class BinaryTreeNode<dataType>
{
   dataType data;
   BinaryTreeNode<dataType> left;
   BinaryTreeNode<dataType> right;
   int height;
   
   /**
   * BinaryTreeNode contructor.
   *
   * @param dataType d
   * @param BinaryTreeNode<dataType> l
   * @param BinaryTreeNode<dataType>
   **/
   public BinaryTreeNode ( dataType d, BinaryTreeNode<dataType> l, BinaryTreeNode<dataType> r )
   {
      data = d;
      left = l;
      right = r;
      height = 0;
   }
   
   /**
   * getLeft method, returns left node.
   *
   * @return BinaryTreeNode<dataType> left
   **/
   BinaryTreeNode<dataType> getLeft () { return left; }

   /**
   * getRight method, returns right node.
   *
   * @return BinaryTreeNode<dataType> right
   **/
   BinaryTreeNode<dataType> getRight () { return right; }
}
