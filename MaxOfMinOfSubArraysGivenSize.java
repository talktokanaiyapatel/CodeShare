Q2. Given an array A[] of integers and an integer m representing the size of the sub-array.
We need to return maximum among the minimum elements of m-size sub arrays in A.

Input: arr[] = {10, 20, 30, 50, 10, 70, 30}
Output: 70, 30, 20, 10, 10, 10, 10

The first element in the output indicates the maximum of minimums of all
windows of size 1.
Minimums of windows of size 1 are {10}, {20}, {30}, {50}, {10},
{70} and {30}. Maximum of these minimums is 70

The second element in the output indicates the maximum of minimums of all
windows of size 2.
Minimums of windows of size 2 are {10}, {20}, {30}, {10}, {10},
and {30}. Maximum of these minimums is 30
//o(N)
public static int maxSlidingWindow(int[] a, int k) {

  //Base case
	if (a == null || k <= 0) return Integer.MIN_VALUE;
  
	ArrayDeque<Integer> deque = new ArrayDeque<Integer>();
  
	int maxReturn = Integer.MIN_VALUE;
  
	for (int i = 0; i < a.length; i++) {
  
		//if k=4, then deque will have 3 elements. so tha later u can add ith element
    
		while (!deque.isEmpty() && deque.peek() < i - k + 1) // Ensure deque's size doesn't exceed k-1
		deque.poll();
    
		// Remove Max from queue bz u need min.
		while (!deque.isEmpty() && a[deque.peekLast()] > a[i])
		deque.pollLast();
    
		deque.offer(i);// Offer the current index to the deque's tail
    
		if (i >= k - 1)// Starts recording when i is big enough to make the window has k elements
		maxReturn = Integer.max(maxReturn,a[deque.peek()]);
	}
	return maxReturn;
}

