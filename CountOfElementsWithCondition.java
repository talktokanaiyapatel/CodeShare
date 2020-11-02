Q1. Given two arrays A[] and B[] containing positive integers and an integer 'd'.
we need to return the count of elements,A[i], in A[] that satisfies below condition
| A[i] - B[j] | > d (0<=j<n where n is size of B).
e.g.
Input : A = [7, 6, 9] B = [13, 1, 4] and d = 3
Output : 1
Explanation : For A[i] = 7, Difference Absolute | A[i] - B[j] | is greater than d for B[j] = 13, 1 but equal to 3 for B[j] = 4. Hence, 7 does not qualify.

Note : we can use Java Treeset (OlogN for every operation) to get the ceiling of required distance.
private static int process(int [] A, int [] B, int d){
       TreeSet<Integer> set = new TreeSet<>();
       for(int element :B){
           set.add(element);
       }
       
       int count = 0;
       for(int element:A){
           int aMin = element-d;
           Integer bMin = set.ceiling(aMin);
           if(null == bMin || bMin>(element+d) ){
               count++;
           }
       }
       return count;
   }
