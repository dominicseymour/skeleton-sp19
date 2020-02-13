public class TestBody {

	public static void main (String[] args) {

		Body b1 = new Body(0.0, 0.0, 2.0, 2.0, 200.0, "example.img");
		Body b2 = new Body(1.0, 1.0, 5.0, 5.0, 1000.0, "example.img");

		Body[] bodies = new Body[2];
		bodies[0] = b1;
		bodies[1] = b2;

		System.out.println(b1.calcNetForceExertedByX(bodies));
		System.out.println(b1.calcNetForceExertedByY(bodies));
	}
}