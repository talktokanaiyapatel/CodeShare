import java.util.concurrent.PriorityBlockingQueue;
public class CustomThreadPool {
	static class MyRunnable implements Runnable, Comparable {
		String myName = null;
		int priority = 0;

		MyRunnable(String name, int priority) {
			this.myName = name;
			this.priority = priority;
		}

		public String toString() {
			return "" + this.myName;
		}

		public void run() {
			System.out.println("MyRunnable running : " + this.myName);
		}

		@Override
		public int compareTo(Object o) {
			MyRunnable obj1 = (MyRunnable) this;
			MyRunnable obj2 = (MyRunnable) o;

			return obj1.priority - obj2.priority;
		}
	}
private class Worker extends Thread {
		private Runnable taskToPerform = null;
		boolean shutdownSignal = false;
		String namePrint = null;

		Worker(String name) {
			this.namePrint = name;
		}

		@Override
		public void run() {
			while (true && !shutdownSignal) {
				taskToPerform = blockingQueue.poll();
				if (taskToPerform != null) {
					System.out.println(" Thread :" + this.namePrint);
					System.out.println(" Task :" + taskToPerform.toString());
					taskToPerform.run();

				}
				if (shutdownSignal) {
					break;
				}
			}
		}
	}

	private final PriorityBlockingQueue<MyRunnable> blockingQueue;
	private final Worker[] workers;

	public CustomThreadPool(final int numOfThreads) {
		blockingQueue = new PriorityBlockingQueue<MyRunnable>(10);
		workers = new Worker[numOfThreads];

		for (int i = 0; i < numOfThreads; i++) {
			workers[i] = new Worker("thread -- " + i);
			workers[i].start();
		}
	}

	public void execute(final MyRunnable task) {
		blockingQueue.add(task);
	}

	public void shutdownImmediately() {
		for (int i = 0; i < workers.length; i++) {
			workers[i].shutdownSignal = true;
			workers[i] = null;
		}
	}

	
	public static void main(String[] args) throws Exception {

		final CustomThreadPool threadPool = new CustomThreadPool(2);

		for (int i = 0; i < 20; i++) {

			threadPool.execute(new CustomThreadPool.MyRunnable("Task -> " + i, 3));
		}
		Thread.sleep(1 * 1000);
		threadPool.shutdownImmediately();
	}
}
