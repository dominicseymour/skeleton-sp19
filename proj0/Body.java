import java.lang.Math;

public class Body {

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Body(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName) {

		this.xxPos = xxPos;
		this.yyPos = yyPos;
		this.xxVel = xxVel;
		this.yyVel = yyVel;
		this.mass = mass;
		this.imgFileName = imgFileName;

	}

	public Body(Body b) {

		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = b.imgFileName;
	}

	public double calcDistance(Body body) {

		return Math.sqrt((Math.pow(this.xxPos - body.xxPos, 2.0) + Math.pow(this.yyPos - body.yyPos, 2.0)));
	}

	public double calcForceExertedBy(Body body) {

		return  ((6.67 * Math.pow(10.0, -11.0)) * this.mass * body.mass) / (calcDistance(body) * calcDistance(body));
	}

	public double calcForceExertedByX(Body body) {

		return (calcForceExertedBy(body) * (body.xxPos - this.xxPos)) / calcDistance(body);
	}

	public double calcForceExertedByY(Body body) {

		return (calcForceExertedBy(body) * (body.yyPos - this.yyPos)) / calcDistance(body);
	}

	public double calcNetForceExertedByX(Body[] array) {

		double netForceExertedByX = 0.0;

		for (int i = 0; i < array.length; i++) {

			if (array[i].mass != this.mass) {
				netForceExertedByX += calcForceExertedByX(array[i]);
			}
		}

		return netForceExertedByX;
	}

	public double calcNetForceExertedByY(Body[] array) {

		double netForceExertedByY = 0.0;

		for (int i = 0; i < array.length; i++) {

			if (!this.equals(array[i])) {
				netForceExertedByY += calcForceExertedByY(array[i]);
			}
		}

		return netForceExertedByY;
	}

	public void update(double seconds, double xForce, double yForce) {

		double accelerationX = xForce / mass;
		double accelerationY = yForce / mass;

		xxVel = xxVel + seconds * accelerationX;
		yyVel = yyVel + seconds * accelerationY;

		xxPos = xxPos + seconds * xxVel;
		yyPos = yyPos + seconds * yyVel;

	}

	public void draw() {

		StdDraw.picture(xxPos, yyPos, imgFileName);
	}

}