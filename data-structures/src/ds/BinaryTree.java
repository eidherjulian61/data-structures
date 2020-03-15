package ds;

public interface BinaryTree<X extends Comparable<X>> {

	int size();

	void add(X item);

	boolean contains(X item);

	boolean delete(X item);

}