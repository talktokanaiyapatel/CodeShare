package src.com;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
/**
 * Note : Performance is always dependent on the real time situations. Threaded and Non Threaded environments differ in terms of performance. 
 * I am assuming muti threaded environment. There is always scope for improvement.
 * @author Kanaiya Patel
 *
 */
public class DeDup {
/** 
 * making static array.
 */
    public static int[] randomIntegers = {1,2,34,34,25,1,45,3,26,85,4,34,86,25,43,2,1,10000,11,16,19,1,18,4,9,3,
                                   20,17,8,15,6,2,5,10,14,12,13,7,8,9,1,2,15,12,18,10,14,20,17,16,3,6,19,
                                   13,5,11,4,7,19,16,5,9,12,3,20,7,15,17,10,6,1,8,18,4,14,13,2,11};   

    /**
    MainTain Order : NO
    ThreadSafe: Yes
    Uses inplace sorting
    More efficient on CPU cycles
    Improvements : Can be improved for order and concurrency.
    Can be improved with lock interface for optimistic locking and stampedlock can help improve concurrency.
    Time Complexity:O(n) - creating copy and iterating.
    Space Complexity:O(n) - linear with array size.
      * @param A
  * @return
    **/
    public static Optional<int[]> removeDuplicateUseSort(final int[] input) {
    	if(null == input || input.length == 0 )
    		return Optional.empty();
    	int [] A = Arrays.copyOf(input, input.length);
    	if (A.length < 2)
    		return Optional.of(A);
     
    	int j = 0;
    	int i = 1;
    	Arrays.sort(A);
    	synchronized (A) {
        	while (i < A.length) {
        		if (A[i] == A[j]) {
        			i++;
        		} else {
        			j++;
        			A[j] = A[i];
        			i++;
        		}
        	}
		}

    	A = Arrays.copyOf(A, j + 1);
       	return Optional.of(A);
    }
    /**
    MainTain Order : Yes
    ThreadSafe: NO
    Improvements : Can be improved for concurrency and parallelism.Can be improved with lock interface for optimistic locking and stampedlock can help improve concurrency.
    Time Complexity:O(n)
    Space Complexity:O(2n) creates the set and array. Ignoring the space for input and return values.
      * @param A
  * @return
    **/    
    public static Optional<int[]> removeDuplicateOriginalOrder(final int[] inputs)
    {
    	
    	if(null == inputs || inputs.length == 0 )
    		return Optional.empty();
    	else 
    	{
	        final Set<Integer> set = new LinkedHashSet<>();
	        final int[] tmp = new int[inputs.length];
	        int index = 0;
	        for (final int i: inputs)
	            if (set.add(i))
	                tmp[index++] = i;
	
	        return Optional.of(Arrays.copyOfRange(tmp, 0, index));
    	} 
    }
    /**
    MainTain Order : Yes
    ThreadSafe: YES
    Improvements : Can be improved for concurrency and parallelism.Can be improved with lock interface for optimistic locking and stampedlock can help improve concurrency.
    Time Complexity:O(n)
    Space Complexity:O(2n) creates the set and array. Ignoring the space for input and return values.
      * @param A
  * @return
    **/       
    public static Optional<int[]> removeDuplicateOriginalOrderThreaded(final int[] inputs)
    {
    	
    	if(null == inputs || inputs.length == 0 )
    		return Optional.empty();
    	else 
    	{
	        final Set<Integer> set = new LinkedHashSet<>();
	        final int[] tmp = new int[inputs.length];
	        int index = 0;
	        synchronized (tmp) {
		        for (final int i: inputs)
		            if (set.add(i))
		                tmp[index++] = i;
			}
	        return Optional.of(Arrays.copyOfRange(tmp, 0, index));
    	} 
    }    
    
    /**
    MainTain Order : Yes
    ThreadSafe: YES
    Uses stream api and internal iterators. Uses the CPU cores. This code scales well with increasing size of array and more CPU cores.
    Improvements : Can be improved for concurrency and parallelism.Can be improved with lock interface for optimistic locking and stampedlock can help improve concurrency.
    			   Customized fork join pool can improve the performance.
    Time Complexity:O(2n) - This is going to improve depending on CPU cores.
    Space Complexity:O(n) creates the list. Ignoring the space for input and return values.
      
  * @return
    **/
    //RETAINS the Original ORDER
    public static Optional<int[]> removeDuplicateParallel(final int[] inputs)
    {
    	if(null == inputs || inputs.length == 0 )
    		return Optional.empty();
    	else 
    	{
	        List<Integer> list = IntStream.of(inputs).boxed().distinct().collect(Collectors.toList());
	        return Optional.of(list.parallelStream().mapToInt(i->i).toArray());
    	}
   }
    public static void printArray(int[] anArray) {
        for (int i = 0; i < anArray.length; i++) {
           if (i > 0) {
              System.out.print(", ");
           }
           System.out.print(anArray[i]);
        }
     }
    
    public static void main(String [] args) {
    	

    	DeDup.printArray(DeDup.removeDuplicateUseSort(randomIntegers).get());
    	System.out.println();
    	DeDup.printArray(DeDup.removeDuplicateOriginalOrder(randomIntegers).get());
    	System.out.println();
    	DeDup.printArray(DeDup.removeDuplicateOriginalOrderThreaded(randomIntegers).get());
    	System.out.println();
    	DeDup.printArray(DeDup.removeDuplicateParallel(randomIntegers).get());
    	


    }
}
