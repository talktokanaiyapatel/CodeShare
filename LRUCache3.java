
import java.util.concurrent.ConcurrentHashMap;

public final class LRUCache3<K, V> {
	private static class Node<K, V> {
		K key;
		V value;
		Node<K, V> prev = null;
		Node<K, V> next = null;

		Node(K key, V value) {
			this.key = key;
			this.value = value;
		}

		// Dont miss to implement.
		public boolean equals(Object obj) {
			Node node = (Node) obj;
			return this.key == node.key;
		}

		// Verbose output
		public String toString() {
			return this.key + "-" + this.value;
		}
	}

	// Dummy head and tail nodes for easy management.
	Node<K, V> head = null;
	Node<K, V> tail = null;
	int capacity = 0;
	// Always do map operations first. Then LinkList operations.
	ConcurrentHashMap<K, Node<K, V>> map = null;

	LRUCache3(int cap) {
		this.capacity = cap;
		// Dummy Initialization. Do not make same.
		head = new Node(Integer.MIN_VALUE, Integer.MIN_VALUE);
		tail = new Node(Integer.MAX_VALUE, Integer.MAX_VALUE);
		map = new ConcurrentHashMap<>();
	}

	public void put(K data, V val) {
		// Already present. Just updating the value and remove/add again.
		if (map.containsKey(data)) {
			Node<K, V> node = map.get(data);
			node.value = val;
			removeNode(node);
			addFirst(node);
		} else {
			if (map.size() == this.capacity) {
				// Already full. Remove tail and add first.
				// Always remove from Map because changes happen in pointers when node removed.
				map.remove(tail.prev.key);
				removeNode(tail.prev);
				Node<K, V> forAdd = new Node<K, V>(data, val);
				map.put(data, forAdd);
				addFirst(forAdd);
			} else {
				// Not Full. Just add
				Node forAdd = new Node<K, V>(data, val);
				map.put(data, forAdd);
				addFirst(forAdd);

			}
		}
	}

	public V get(K data) {
		if (!map.containsKey(data)) {
			return null;
		}
		// If present, then change order and return
		Node<K, V> node = map.get(data);
		removeNode(node);
		addFirst(node);
		// print();
		return node.value;
	}

// Node to be removed will NOT NULL
	public void removeNode(Node<K, V> node) {
		// Manage both ends of node.

		// change next nodes pointer and update next
		node.next.prev = node.prev;

		// Change prev nodes pointer
		node.prev.next = node.next;
		// make null at end. if not then error. see above statement.
		node.prev = null;
		node.next = null;
	}

	// We add to Front for Recently used list.
	public void addFirst(Node<K, V> node) {
		if (map.size() == 1) {// Just added in map. means first entry.

			head.next = node;
			node.prev = head;

			tail.prev = node;
			node.next = tail;
		} else {
			// add to the head.
			Node temp = head.next;
			head.next = node;
			node.prev = head;
			node.next = temp;
			temp.prev = node;
		}
	}

	public void print() {
		Node<K, V> curr = head.next;
		;
		while (curr != null && curr.next != null) {
			System.out.print(curr.key + " - " + curr.value + " ");
			curr = curr.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		// LRUCache2 cache = new LRUCache2(4);
		/*
		 * cache.put(1, 1); cache.put(2, 2); cache.put(3, 3); cache.put(4, 4);
		 * cache.put(5, 5); cache.put(6, 5); cache.get(4);
		 */
		// LRUCache3 cache = new LRUCache3(4);
		LRUCache3<String, String> cache = new LRUCache3<String, String>(3);
		cache.put("one", "1");
		cache.put("TWO", "2");
		cache.put("Three", "3");
		cache.put("Four", "4");
		// cache.put("one", "11");
		cache.print();
		System.out.println(" getting :" + cache.get("TWO"));

		//SCANNER API ..
		Scanner scan = new Scanner(System.in);
		while (true) {
			// String input = scan.nextLine();
			int input = scan.nextInt();
			System.out.println(input);
			if (input == 0)
				break;
		}

	}

}
