class test {
	public static void main(String[] args) {
		int x = 1;
		changeX(x);
		System.out.println(x);
	}

	static void changeX(int x) {
		x += 1;
	}
}