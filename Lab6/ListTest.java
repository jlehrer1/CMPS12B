class ListTest {
	public static void main(String[] args) {
		List<String> test = new List<String>();
		test.add(1, "hello");
		System.out.println(test.size());
		test.add(2, "goodbye");
		System.out.println(test.size());

		System.out.println();
		System.out.println(test);

		List<Double> test2 = new List<Double>();
		// test2.add(1, "test");
		// System.out.println(test2);
		test2.add(1, 2.01);
		test2.add(2, 55.0);
		System.out.println(test2);

		test2.removeAll();
		System.out.println("Test2 should be empty: " + test2);
	}

}