public class NBody {

	public static void main(String[] args) {

		// Get command line args
		double T = Double.valueOf(args[0]);
		double dt = Double.valueOf(args[1]);
		String filename = args[2];

		// Read in bodies and universe radius from file
		Body[] bodies = readBodies(filename);
		double radius = readRadius(filename);

		// Set scale of universe
		StdDraw.setScale(-radius, radius);

		// Drawing the background image
		StdDraw.picture(0, 0, "images/starfield.jpg");

		// Drawing bodies in their original position
		for (int i = 0; i < bodies.length; i++) {
			bodies[i].draw();
		}

		double time = 0;
		while (time < T) {

			// Create arrays for storing net forces of each body
			double[] xForces = new double[bodies.length];
			double[] yForces = new double[bodies.length];

			// Calculate forces for each body and add to the array
			for (int i = 0; i < bodies.length; i++) {

				xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
				yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
			}	

			// Update bodies
			for (int j = 0; j < bodies.length; j++) {

				bodies[j].update(dt, xForces[j], yForces[j]);
			}

			// Draw the bodies
			for (int k = 0; k < bodies.length; k++) {
				bodies[k].draw();
			}

			// Show the drawing
			StdDraw.show();
			StdDraw.pause(10);

			// Increase time
			time += dt;
		}

		StdOut.printf("%d\n", bodies.length);
		StdOut.printf("%.2e\n", radius);
		
		for (int i = 0; i < bodies.length; i++) {
    	StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                  bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
		}

	}

	public static double readRadius(String filename) {

		In in = new In(filename);

		int numberOfPlanets = in.readInt();
		double radius = in.readDouble();

		return radius;

	}

	public static Body[] readBodies(String filename) {

		In in = new In(filename);

		int numberOfPlanets = in.readInt();
		double radius = in.readDouble();

		Body planet1 = new Body(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), "images/" + in.readString());
		Body planet2 = new Body(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), "images/" + in.readString());
		Body planet3 = new Body(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), "images/" + in.readString());
		Body planet4 = new Body(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), "images/" + in.readString());
		Body planet5 = new Body(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), "images/" + in.readString());		

		Body[] planets = new Body[5];
		planets[0] = planet1;
		planets[1] = planet2;
		planets[2] = planet3;
		planets[3] = planet4;
		planets[4] = planet5;

		return planets;

	}
}