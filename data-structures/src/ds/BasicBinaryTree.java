package ds;

public class BasicBinaryTree<X extends Comparable<X>> implements BinaryTree<X> {
	private Node root;
	private int size;

	public BasicBinaryTree() {
		this.root = null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void add(X item) {
		Node node = new Node(item);

		// If this is the first item in the tree, set it as root
		if (root == null) {
			this.root = node;
			System.out.println("Set root: " + node.getItem());
			this.size++;
		} else { // Otherwise we need to insert the item into the tree using the binary tree
					// insert algorithm
			insert(this.root, node);
		}
	}

	@Override
	public boolean contains(X item) {
		Node currentNode = getNode(item);
		if (currentNode == null) {
			return false; // didn't find it
		} else {
			return true;
		}
	}

	@Override
	public boolean delete(X item) {
		boolean deleted = false;
		if (this.root == null) {
			return false;
		}
		Node currentNode = getNode(item);
		if (currentNode != null) {
			// If the node to delete doesn't have any children, just delete it
			if (currentNode.getLeft() == null && currentNode.getRight() == null) {
				unlink(currentNode, null);
				deleted = true;
			} else if (currentNode.getLeft() == null && currentNode.getRight() != null) {
				// If the node to delete only has a right child, remove it in the hierarchy
				unlink(currentNode, currentNode.getRight());
				deleted = true;
			} else if (currentNode.getLeft() != null && currentNode.getRight() == null) {
				// If the node to delete only has a left child, remove it in the hierarchy
				unlink(currentNode, currentNode.getLeft());
				deleted = true;
			} else { // The node has both children, do a node swap to delete
				// You can swap out the ndoe with the right most leaf node on the left side
				Node child = currentNode.getLeft();
				while (child.getRight() != null && child.getLeft() != null) {
					child = child.getRight();
				}
				// We have the right most leaf node. We can replace the current nothe with this
				// node
				child.getParent().setRight(null); // remove the leaf node from it's current position
				child.setLeft(currentNode.getLeft());
				child.setRight(currentNode.getRight());
				unlink(currentNode, child);
				deleted = true;
			}
		}

		if (deleted) {
			this.size--;
		}
		return deleted;
	}

	private void unlink(Node currentNode, Node newNode) {
		// Of this is the root node, we replace that a little differently
		if (currentNode == this.root) {
			newNode.setLeft(currentNode.getLeft());
			newNode.setRight(currentNode.getRight());
			this.root = newNode;
		} else if (currentNode.getParent().getRight() == currentNode) {
			currentNode.getParent().setRight(newNode);
		} else {
			currentNode.getParent().setLeft(newNode);
		}
	}

	private Node getNode(X item) {
		Node currentNode = this.root;

		while (currentNode != null) {
			int val = item.compareTo(currentNode.getItem());

			// See if the node is a match
			if (val == 0) {
				return currentNode;
			} else if (val < 0) { // We go to the left side of the tree
				currentNode = currentNode.getLeft();
			} else { // Otherwise we go to the right side
				currentNode = currentNode.getRight();
			}
		}
		return null;
	}

	private void insert(Node parent, Node child) {
		// If the child is less than the parent, it goes on the left
		if (child.getItem().compareTo(parent.getItem()) < 0) {
			// If the left node is null, we've found our spot
			if (parent.getLeft() == null) {
				parent.setLeft(child);
				child.setParent(parent);
				this.size++;
			} else { // Otherwise we need to call insert again and test for left or right (recursion)
				insert(parent.getLeft(), child);
			}
		} else if (child.getItem().compareTo(parent.getItem()) > 0) {
			// If the right node is null, we've found our spot
			if (parent.getRight() == null) {
				parent.setRight(child);
				child.setParent(parent);
				this.size++;
			} else { // Otherwise we need to call insert again and test for left or right (recursion)
				insert(parent.getRight(), child);
			}
		}

		// If the parent and child happen to be equal, we don't do anything
		// data in a binary tree is assumed to be unique and the valule already exists
		// in the tree
	}

	private class Node {
		private Node left;
		private Node right;
		private Node parent;
		private X item;

		public Node(X item) {
			this.item = item;
			this.left = null;
			this.right = null;
			this.parent = null;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}

		public Node getParent() {
			return parent;
		}

		public void setParent(Node parent) {
			this.parent = parent;
		}

		public X getItem() {
			return item;
		}

		public void setItem(X item) {
			this.item = item;
		}
	}
}
