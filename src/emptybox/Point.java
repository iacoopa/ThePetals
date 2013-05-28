package emptybox;

public class Point implements Comparable<Point> {
	public float x;
	public float y;
	
	public Point(float x, float y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Point p2) {
		if (x < p2.x) {
			return -1;
		} if (x > p2.x) {
			return 1;
		}
		return 0;
	}
}
