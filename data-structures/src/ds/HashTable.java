package ds;

public interface HashTable<X, Y> {

	Y get(X key);

	void put(X key, Y value);

	Y delete(X key);

	boolean hasKey(X key);

	boolean hasValue(Y value);

	int size();

}