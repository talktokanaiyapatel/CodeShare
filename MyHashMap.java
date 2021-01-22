import java.util.Arrays;

public class MyHashMap {
	class Entry {
		Integer key;
		Integer value;
		Entry next;

		Entry(Integer data, Integer val) {
			this.key = data;
			this.value = val;
			this.next = null;
		}

		public boolean equals(Object obj) {
			Entry temp = (Entry) obj;
			return temp.key == this.key;
		}

		public String toString() {
			return this.key + "-" + this.value;
		}
	}

//length
	public static Integer cap = 10;
	Entry[] table = null;

	MyHashMap(Integer cap) {
		this.cap = cap;
		table = new Entry[this.cap];
		Arrays.fill(table, null);
	}

	public void put(Integer data, Integer val) {
		int bucket = data.hashCode() % this.cap;
		Entry found = table[bucket];
		if (found == null) {
			table[bucket] = new Entry(data, val);
		} else {

			boolean isNodeWithKey = false;
			while (found.next != null) {
				if (found.key == data) {
					isNodeWithKey = true;
					found.value = val;
					break;
				}
				found = found.next;
			}
			if (!isNodeWithKey) {
				found.next = new Entry(data, val);
			}

		}

	}

	public Integer get(Integer data) {
		int bucket = data.hashCode() % this.cap;
		if (table[bucket] == null) {
			return -1;
		}
		// BE ALERT
		Entry entryFound = table[bucket];
		// will have started comparing from the first node.
		while (entryFound != null) {
			if (entryFound.key == data) {
				return entryFound.value;
			}
			entryFound = entryFound.next;
		}
		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyHashMap map = new MyHashMap(5);
		map.put(10, 20);
		map.put(5, 10);
		map.put(15, 30);
		map.put(11, 22);
		map.put(12, 24);
		map.put(13, 26);
		System.out.println(" Map " + map.get(5));
	}

}
