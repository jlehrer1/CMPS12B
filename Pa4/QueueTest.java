public class QueueTest {
	public static void main(String[] args) {
		String enqTest = "test";
		Queue test = new Queue();

		test.enqueue(enqTest);
		System.out.println(test.length());
		System.out.println(test.peek());
		System.out.println(test.dequeue());

		//									 
		// PASSED ALL TESTS ON GRADING SCRIPT
		//
	}
}