/**Que - Maximum Frequency Stack

Solution
Implement FreqStack, a class which simulates the operation of a stack-like data structure.

FreqStack has two functions:

push(int x), which pushes an integer x onto the stack.
pop(), which removes and returns the most frequent element in the stack.
If there is a tie for most frequent element, the element closest to the top of the stack is removed and returned.
 
Example 1:

Input: 
["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
[[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
Output: [null,null,null,null,null,null,null,5,7,5,4]
Explanation:
After making six push operations, the stack is [5,7,5,7,4,5] from bottom to top.  Then:

pop() -> returns 5, as 5 is the most frequent.
The stack becomes [5,7,5,7,4]. **/

class Pair {

	int x;
	int freq;
	int time;

	public Pair(int x, int freq, int time) {
		this.x = x;
		this.freq = freq;
		this.time = time;
	}
}
class FreqStack2 {
	PriorityQueue<Pair> heap;
	HashMap<Integer, Integer> freq;
	int time;
	public FreqStack() {
		// for stak
		heap = new PriorityQueue<>(new Comparator<Pair>() {
			@Override
			public int compare(Pair o1, Pair o2) {
				if (o1.freq == o2.freq)
					return o2.time - o1.time;
				return o2.freq - o1.freq;
			}
		});
		// for maintaining frequency..
		freq = new HashMap<Integer, Integer>();
		
		time = 0;
	}

	// O(logn)
	public void push(int x) {
		time++;
		int f = freq.getOrDefault(x, 0) + 1;
		freq.put(x, f);
		//one element is added in the heap many times..
		Pair p = new Pair(x, f, time);
		heap.add(p);
	}

	// O(logn)
	public int pop() {
		//Not adding in the heap again.
		Pair p = heap.remove();
		int x = p.x;
		freq.put(x, freq.get(x) - 1);
		return x;
	}

}
