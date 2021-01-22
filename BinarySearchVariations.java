	//Basic search 
	public static int normalBinarySearch(int data[], int target) {
		// Start by defining your lo/hi index
		int lo = 0;
		int hi = data.length-1;
		int mid = (data.length)/2;
		while(lo<=hi) { 
			mid = (lo+hi)/2;
			if(data[mid]==target) {
				return mid;
			}
			if(data[mid] > target) { 
				hi = mid-1;
			} else {
				lo = mid+1;
			}
		}
		return -1; 
	}
	//Only difference is in equals section.
	//Variation one : Binary search only right most occurence
	public static int binaryRightBoundarySearch(int data[], int target) {
		// Start by defining your lo/hi index
		int lo = 0;
		int hi = data.length-1;
		int mid = (data.length)/2;
		int result = -1;
		while(lo<=hi) { 
			mid = (lo+hi)/2;
			if(data[mid]==target) {
				result = mid;
				lo = mid +1;
			}
			if(data[mid] > target) { 
				hi = mid-1;
			} else {
				lo = mid+1;
			}
		}
		// Above loop won't stop, 
		// we always get here.
		return result; 
	}
	////Binary search only left most occurence
	public static int binaryLeftBoundarySearch(int data[], int target) {
		// Start by defining your lo/hi index
		int lo = 0;
		int hi = data.length-1;
		int mid = (data.length)/2;
		int result =-1;
		while(lo<=hi) { 
			mid = (lo+hi)/2;
			// if equals, then go left and store result.
			if(data[mid]==target) {
				result = mid;
				hi=mid-1;
			}
		    if(data[mid] < target) { 
				lo = mid + 1;
			}
		    if(data[mid] > target) {
		    	hi = mid-1;
		    }
		}
		return result; 
	}
