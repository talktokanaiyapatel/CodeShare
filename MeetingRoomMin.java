class MeetingRoomMin {
    public int minMeetingRooms(int[][] intervals) {
    // Check for the base case. If there are no intervals, return 0
    if (intervals.length == 0) {
      return 0;
    }
   // Sort the intervals by start time
    Arrays.sort(intervals,(int[] a,int[] b)->a[0]-b[0]);
   // Min heap - NoOfRoomsNeeded
    PriorityQueue<Integer> freeRoomEndTime =
        new PriorityQueue<Integer>();
    // Add the first meeting End time. We only need to check end time
    freeRoomEndTime.add(intervals[0][1]);
    // Iterate over remaining intervals
    for (int i = 1; i < intervals.length; i++) {
//No overlap - no need for new room - remove from queue.
      if (intervals[i][0] >= freeRoomEndTime.peek()) {
        freeRoomEndTime.poll();
      }
     // Always add latest as sorted.- add endtime.
     freeRoomEndTime.add(intervals[i][1]);
    }
    // The size of the heap tells us the minimum rooms required for all the meetings.
    return freeRoomEndTime.size();
  }
}
