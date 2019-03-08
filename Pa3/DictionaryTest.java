public class DictionaryTest {
	public static void main(String[] args) {
		String v;
		Dictionary A = new Dictionary();
		// test.insert("1", "a");
		// test.insert("2", "b");
		// test.insert("3", "c");
		// System.out.println(test.lookup("1"));
		// System.out.println(test.lookup("2"));
		// System.out.println(test.lookup("3"));
		// System.out.println();
		// System.out.println(test.toString());

		// test.lookup("1");
		// test.findKey("1");
		// test.delete("1");

		// test.delete("1");
		// System.out.println(test.toString());

	    A.insert("1","a");
	    A.insert("2","b");
	    A.insert("3","c");
	    A.insert("4","d");
	    A.insert("5","e");
	    A.insert("6","f");
	    A.insert("7","g");
	    System.out.println(A.size());
	    A.delete("1");
	   	System.out.println(A.size());

	    // System.out.println(A);


      	v = A.lookup("1");
      	System.out.println("lookup: " + v);
      	// System.out.println("findKey: " + A.findKey("1"));
      	// System.out.println(v);
      	// System.out.println("key=1 "+(v==null?"not found":("value="+v)));



	}
}