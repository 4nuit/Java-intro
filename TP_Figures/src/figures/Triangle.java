package figures;

import java.util.ArrayList;
import java.util.Collection;

import points.Point2D;

public class Triangle extends Polygon {
	
	public Triangle() {
		super();
		// DONE Auto-generated constructor stub
		this.points=new ArrayList<>();
		this.points.add(new Point2D(0,0));
		this.points.add(new Point2D(1,0));
		this.points.add(new Point2D(0.5,1));
		
	}

	public Triangle(Point2D... pts) {
		super(pts);
		// DONE Auto-generated constructor stub
	}

	public Triangle(Collection<Point2D> pts) {
		super(pts);
		// DONE Auto-generated constructor stub
	}

	public Triangle(Polygon p) {
		super(p);
		// DONE Auto-generated constructor stub
	}
	public Triangle(Point2D p1,Point2D p2,Point2D p3) {
		this.points=new ArrayList<>();
		this.points.add(p1);
		this.points.add(p2);
		this.points.add(p3);
	}
	@SuppressWarnings("unchecked")
	public Triangle(Triangle t) {
		this.points=(ArrayList<Point2D>) t.points.clone();
	}

}
