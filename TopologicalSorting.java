// Topological sorting using Kahns algorithm..
//Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every directed edge uv, vertex u comes before v in the ordering. Topological Sorting for a graph is not possible if the graph is not a DAG.
//Time Complexity: O(V+E)
//A DAG has at least one vertex with in-degree 0 and one vertex with out-degree 0
class TopologicalSorting{
	// cannot have HashSet in creation ..right side.
	private Map<Integer, Set<Integer>> adjList = new HashMap<Integer, Set<Integer>>();

	// there is dependency. from should be completed before to.
	// Make sure every node is added and then relationship if any.
	public void add(Integer from, Integer to) {
		// Does not contain the key.
		if (!adjList.containsKey(from)) {
			adjList.put(from, new HashSet<Integer>());
		}
		if (!adjList.containsKey(to)) {
			adjList.put(to, new HashSet<Integer>());
		}
		// add to map. if not null. will have empty set.
		// Element without outdegrees.
		if (to != null) {
			adjList.get(from).add(to);

		}
	}
private Map<Integer, Integer> getInDegrees() {
		if (adjList == null)
			return null;

		// result
		Map<Integer, Integer> result = new HashMap<>();
		// actual processing.. both key and
		for (Integer key : adjList.keySet()) {
			// adding keys as well> BZ we may have key w/o values
			// nodes without any connection
			if (!result.containsKey(key)) {
				result.put(key, 0);
			}
			for (Integer value : adjList.get(key)) {
				result.put(value, result.getOrDefault(value, 0) + 1);
			}
		}
		return result;
	}

	public List<Integer> doTopologicalSorting() {
		Map<Integer, Integer> inDegress = getInDegrees();
		if (inDegress == null || inDegress.size() == 1)
			return null;
		// BFS..
		Queue<Integer> queue = new ArrayDeque<>();
		// Add nodes with Zero indegrees..
		for (Integer node : inDegress.keySet()) {
			if (inDegress.get(node) == 0) {
				queue.add(node);
			}
		}
		// visited count to be used to compare if we are done with OR there are cycles..
		int countVisited = 0;
		
// Holds SORTED nodes. 
		List<Integer> result = new ArrayList<Integer>();
		while (!queue.isEmpty()) {
			Integer task = queue.poll();
			result.add(task);
			for (Integer afterTask : adjList.get(task)) {
				inDegress.put(afterTask, inDegress.getOrDefault(afterTask, 0) - 1);
				// if ingress is ZERO then add.
				if (inDegress.get(afterTask) == 0) {
					queue.add(afterTask);
				}
			}
			countVisited++;
		}
		//Visited vertices and actual vertices
		if (countVisited != inDegress.size()) {
			System.out.println(" Graph has CYCLE....");
			return null;
		}
		return result;
	}
}
