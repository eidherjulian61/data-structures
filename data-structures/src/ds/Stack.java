package ds;

public interface Stack<X> {

	void push(X newItem);

	X pop();

	boolean contains(X item);

	X access(X item);

	int size();

}