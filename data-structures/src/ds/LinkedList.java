package ds;

public interface LinkedList<X> {

	void add(X item);

	void insert(X item, int position);

	X removeAt(int position);

	X remove();

	X get(int position);

	int find(X item);

	String toString();

	int size();

}