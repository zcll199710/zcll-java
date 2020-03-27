package basic_test6;

public class AVLTree extends AbstractSelfBalancingBinarySearchTree {

	public Node insert(int element){
		Node newNode = super.insert(element);
		rebalance((AVLNode)newNode);
		return newNode;
	}
	
	public void rebalance(AVLNode node){
		while(node != null){
			Node parent = node.parent;
			int leftHeight = ((AVLNode)node.left).height;
			int rightHeight = ((AVLNode)node.right).height;
			int nodeBalance = rightHeight-leftHeight;
			if(nodeBalance == 2){
				if(node.right.right != null){
					node = (AVLNode)avlRotateLeft(node.parent);
					//node = (AVLNode) node.parent;
					break;
				}else{
					node = (AVLNode)avlDoubleRotateRightLeft(node);
					break;
				}
			}else if(nodeBalance == -2){
				if(node.left.left != null){
					node = (AVLNode) avlRotateRight(node.parent);
					break;
				}else{
					node = (AVLNode) avlDoubleRotateLeftRight(node);
					break;
					
				}
			}else{
				updateHeight(node);
			}
			
			node = (AVLNode)parent;
		}
	}
	
	public Node avlRotateLeft(Node node){
		Node temp = rotateLeft(node);
		updateHeight((AVLNode)temp.left);
		updateHeight((AVLNode)temp);
		return temp;
	}
	
	public Node avlRotateRight(Node node){
		Node temp = rotateRight(node);
		updateHeight((AVLNode)temp.right);
		updateHeight((AVLNode)temp);
		return temp;
		
		
	}
	
	public Node avlDoubleRotateRightLeft(AVLNode node){
		Node temp = avlRotateRight(node.right);
		return avlRotateLeft(temp.parent.parent);
		
		
	}
	
	public Node avlDoubleRotateLeftRight(AVLNode node){
		Node temp = avlRotateLeft(node.left);
		return avlRotateRight(temp.parent.parent);
	}
	
	public  void updateHeight(AVLNode node) {
		
		int leftHeight = ((AVLNode)node.left).height;
		int rightHeight = ((AVLNode)node.right).height;
		node.height = 1+Math.max(leftHeight, rightHeight);
		
		// TODO Auto-generated method stub
		
	}

	public Node createNode(int value,Node parent,Node left,Node right){
		return new AVLNode(value,parent,left,right);
	}
	
	public static class AVLNode extends Node{
		public int height;
		public AVLNode(int value,Node parent,Node left,Node right){
			super(value,parent,left,right);
		}
	}
	
}
