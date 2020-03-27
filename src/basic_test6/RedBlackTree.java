package basic_test6;

public class RedBlackTree extends AbstractSelfBalancingBinarySearchTree {

	public static final RedBlackNode nilNode = new RedBlackNode(null, null, null, null, ColorEnum.BLACK);

	public Node insert(int element) {
		RedBlackNode newNode = (RedBlackNode) super.insert(element);
		newNode.left = nilNode;
		newNode.right = nilNode;
		root.parent = nilNode;
		insertRBFixup((RedBlackNode) newNode);
		return newNode;
	}
	
	public Node delete(Node deleteNode){
		/*Node replaceNode = null;
		if(deleteNode != null && deleteNode != nilNode){
			Node removeOrMovedNode = deleteNode;
			ColorEnum removeOrMovedNodeColor = ((RedBlackNode)removeOrMovedNode).color;
			
			if(deleteNode.left == nilNode){
				replaceNode = deleteNode.right;
				rbTreeTransplant(deleteNode,deleteNode.right);
			}
			
			size--;
			if(removeOrMovedNodeColor == ColorEnum.BLACK){
				deleteRBFixup((RedBlackNode)replaceNode);
			}
			
		}*/
		return null;
	}
	
	
	
	public void deleteRBFixup(RedBlackNode x) {
		
		while(x != root && isBlack(x)){
			
		}
		
	}

	public boolean isBlack(RedBlackNode x) {
		// TODO Auto-generated method stub
		return ColorEnum.BLACK == x.color;
	}

	public Node rbTreeTransplant(Node nodeToReplace, Node newNode) {
		if(nodeToReplace.parent == nilNode){
			this.root = newNode;
		}else if(nodeToReplace == nodeToReplace.parent.left){
			nodeToReplace.parent.left = newNode;
		}else{
			nodeToReplace.parent.right = newNode;
		}
		
		newNode.parent = nodeToReplace.parent;
		return newNode;
		
	}

	public void insertRBFixup(RedBlackNode currentNode) {
		while(currentNode.parent != root && ((RedBlackNode)currentNode.parent).color == ColorEnum.RED){
			RedBlackNode parent = (RedBlackNode) currentNode.parent;
			RedBlackNode grandParent = (RedBlackNode)parent.parent;
			
			if(parent == grandParent.left){
				RedBlackNode uncle = (RedBlackNode) grandParent.right;
				if(uncle.color == ColorEnum.RED){
					parent.color = ColorEnum.BLACK;
					uncle.color = ColorEnum.BLACK;
					grandParent.color = ColorEnum.RED;
					currentNode = grandParent;
				}else{
					if(currentNode == parent.right){
						currentNode = parent;
						rotateLeft(currentNode);
						
					}
					
					parent.color = ColorEnum.BLACK;
					grandParent.color = ColorEnum.RED;
					rotateRight(grandParent);
				}
			}else if(parent == grandParent.right){
				RedBlackNode uncle = (RedBlackNode) grandParent.left;
				if(uncle.color == ColorEnum.RED){
					parent.color = ColorEnum.BLACK;
					uncle.color = ColorEnum.BLACK;
					grandParent.color = ColorEnum.RED;
					currentNode = grandParent;
					
				}else{
					if(currentNode == parent.left){
						currentNode = parent;
						rotateRight(currentNode);
					}
					parent.color = ColorEnum.BLACK;
					grandParent.color = ColorEnum.RED;
					rotateLeft(grandParent);
					
				}
			}
			
		}
		
		((RedBlackNode)root).color = ColorEnum.BLACK;
		
	}
	
	public Node rotateRight(Node node){
		Node temp = node.left;
		temp.parent = node.parent;
		
		node.left = temp.right;
		if(node.left != null){
			node.left.parent = temp;
		}
		
		temp.right = node;
		node.parent = temp;
		
		if(temp.parent != nilNode){
			if(temp.parent.left == node){
				temp.parent.left = temp;
			}else{
				temp.parent.right = temp;
			}
		}else{
			root = temp;
		}
		
		return temp;
		
	}

	public Node rotateLeft(Node node) {
        Node temp = node.right;
        temp.parent = node.parent;
        
        node.right = temp.left;
        if (node.right != nilNode) {
            node.right.parent = node;
        }

        temp.left = node;
        node.parent = temp;

        // temp took over node's place so now its parent should point to temp
        if (temp.parent != nilNode) {
            if (node == temp.parent.left) {
                temp.parent.left = temp;
            } else {
                temp.parent.right = temp;
            }
        } else {
            root = temp;
        }
        
        return temp;
    }

	public Node createNode(int value, Node parent, Node left, Node right) {
		return new RedBlackNode(value, parent, left, right, ColorEnum.RED);
	}

	public enum ColorEnum {
		RED, BLACK
	};

	public static class RedBlackNode extends Node {
		public ColorEnum color;

		public RedBlackNode(Integer value, Node parent, Node left, Node right, ColorEnum color) {
			super(value, parent, left, right);
			this.color = color;
		}
	}
}
