package codejam2012;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;

public class HallOfMirrorsDrat {

	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(new BufferedReader(new FileReader(
				"A-large-practice.in.txt")));
		Output out = new Output("D.out");

		int T = scanner.nextInt();

		// final double sqr2 = Math.sqrt(2);
		// final int[][] directions = { {-1, 0}, };

		for (int t = 1; t <= T; t++) {

			int H = scanner.nextInt();
			int W = scanner.nextInt();
			int D = scanner.nextInt();
			scanner.nextLine();

			double x0 = 0;
			double y0 = 0;
			int[][] corners = { { W - 2, H - 2 }, { 0, H - 2 }, { 0, 0 },
					{ W - 2, 0 }, };
			for (int i = 0; i < H; i++) {

				char[] arr = scanner.next().toCharArray();
				for (int j = 0; j < W; j++) {
					if (arr[j] == 'X') {
						x0 = j - 0.5;
						y0 = i - 0.5;
					}
				}
			}

			int count = 0;
			for (double angle = 0; angle < 360; angle += 0.01) {

				double theta = angle;
				double d = D;

				double x1 = x0, y1 = y0;

				// find next point
				double x2 = 0, y2 = 0;
				boolean isCornerSave = false;

				do {
					double[] angles = findAngles(x1, y1, corners);

					boolean isCorner = false;
					int wall = 0;
					for (int i = 0; i < angles.length - 1; i++) {

						if (equals(angles[i], theta)) {
							isCorner = true;
						}

						if (angles[i] <= theta && theta < angles[i + 1]) {
							wall = i + 1;
							break;
						}
					}

					if (equals(theta, angles[3])) {
						isCorner = true;
					}

					if (false) {
						// wall--;
						// if (wall < 0) {
						// wall = 3;
						// }
						//
						// x2 = corners[wall][0];
						// y2 = corners[wall][1];
						// theta = (theta + 180) % 360;
					} else {
						switch (wall) {
						case 0:
							x2 = W - 2;
							y2 = y1 + (x2 - x1)
									* Math.tan(Math.toRadians(theta));

							if (isCorner(x2, y2, corners)) {
								theta = (theta + 180) % 360;
							} else {
								theta = (360 + 180 - theta) % 360;
							}
							break;
						case 1:
							y2 = H - 2;
							if (theta == 90) {
								x2 = x1;
							} else {
								x2 = x1 + (y2 - y1)
										/ Math.tan(Math.toRadians(theta));
							}
							if (isCorner(x2, y2, corners)) {
								theta = (theta + 180) % 360;
							} else {
								theta = 360 - theta;
							}
							break;
						case 2:
							x2 = 0;
							y2 = y1 + (x2 - x1)
									* Math.tan(Math.toRadians(theta));
							if (isCorner(x2, y2, corners)) {
								theta = (theta + 180) % 360;
							} else {
								theta = (360 + 180 - theta) % 360;
							}
							break;
						case 3:
							y2 = 0;
							if (theta == 270) {
								x2 = x1;
							} else {
								x2 = x1 + (y2 - y1)
										/ Math.tan(Math.toRadians(theta));
							}
							// theta = (360 + 180 - theta) % 360;
							if (isCorner(x2, y2, corners)) {
								theta = (theta + 180) % 360;
							} else {
								theta = 360 - theta;
							}
						}
					}
					if (!(equals(x0, x1) && equals(y0, y1))
							&& sameLine(x0, x1, x2, y0, y1, y2)
							&& dist(x0, y0, x1, y1) <= d) {
						count++;
						break;
					}

					d -= dist(x1, y1, x2, y2);
					x1 = x2;
					y1 = y2;

				} while (d > 0);

			}

			out.format("Case #%d: %d\n", t, count);
		}

		scanner.close();
		out.close();
	}

	private static boolean isCorner(double x2, double y2, int[][] corners) {

		for (int[] arr : corners) {
			if (equals(x2, arr[0]) && equals(y2, arr[1])) {
				return true;
			}
		}
		return false;
	}

	private static double dist(double x0, double y0, double x1, double y1) {

		double x = x1 - x0;
		double y = y1 - y0;
		return Math.sqrt(x * x + y * y);
	}

	private static boolean sameLine(double x0, double x1, double x2, double y0,
			double y1, double y2) {
		return equals((y2 - y1) * (x0 - x1), (x2 - x1) * (y0 - y1));
	}

	private static double[] findAngles(double x1, double y1, int[][] points) {

		double[] angles = new double[points.length];

		for (int i = 0; i < points.length; i++) {

			double deltaX = points[i][0] - x1;
			double deltaY = points[i][1] - y1;

			double angle = Math.toDegrees(Math.atan2(deltaY, deltaX));

			if (angle < 0) {
				angle += 360;
			}

			angles[i] = angle;

			// double x2 = points[i][0];
			// double y2 = points[i][1];
			//
			// if(equals(x1, x2)){
			// if(y2 > y1){
			// angles[i] = 90;
			// } else {
			// angles[i] = 270;
			// }
			//
			// } else if(equals(y1, y2)){
			// if(x2 > x1){
			// angles[i] = 0;
			// } else{
			// angles[i] = 180;
			// }
			//
			// } else {
			// double
			// }

		}
		return angles;
	}

	private static boolean equals(double x1, double x2) {
		return Math.abs(x2 - x1) < 2e-12;
	}

	static class Output {

		PrintWriter pw;

		public Output(String filename) throws IOException {
			pw = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
		}

		public void print(String s) {
			pw.print(s);
			System.out.print(s);
		}

		public void println(String s) {
			pw.println(s);
			System.out.println(s);
		}

		public void format(String format, Object... args) {
			pw.format(format, args);
			System.out.format(format, args);
		}

		public void close() {
			pw.close();
		}
	}

}
