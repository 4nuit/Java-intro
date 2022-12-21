package figures;

import points.Point2D;

public class Rectangle extends AbstractFigure implements Figure {
	
	protected Point2D pMin;
	protected Point2D pMax;
	public Rectangle() {
		// DONE Auto-generated constructor stub
		this.pMin=new Point2D();
		this.pMax=new Point2D();
	}
	public Rectangle(double xmin,double ymin, double xmax,double ymax) {
		this.pMin=new Point2D(xmin,ymin);
		this.pMax=new Point2D(xmax,ymax);
	}
	public Rectangle(Point2D pMin,Point2D pMax) {
		this.pMax=pMax;
		this.pMin=pMin;
	}
	public Rectangle(Rectangle r) {
		this(r.pMin,r.pMax);
		return;
	}
	@Override
	public Point2D getBoundingBoxCenter() {
		// DONE Auto-generated method stub
		return this.getCenter();
	}
	public Point2D bottomLeft() {
		return pMin;
	}
	public void setpMin(Point2D pMin) {
		this.pMin = pMin;
	}
	public Point2D topRight() {
		return pMax;
	}
	public void setpMax(Point2D pMax) {
		this.pMax = pMax;
	}
	@Override
	public double width() {
		// DONE Auto-generated method stub
		return Math.abs(Point2D.dy(this.pMin,this.pMax));
	}
	public void setWidth(double width) {
		if(width>0) {
			double halfwidth=width/2;
			double centerY=getCenter().getY();
			this.pMin.setY(centerY-halfwidth);
			this.pMax.setY(centerY+halfwidth);
		}
	}
	public void setHeight(double height) {
		if(height>0) {
			double halfheight=height/2;
			double CenterX=getCenter().getX();
			this.pMin.setX(CenterX-halfheight);
			this.pMax.setX(CenterX+halfheight);
		}
	}
	@Override
	public double height() {
		// DONE Auto-generated method stub
		return Math.abs(Point2D.dx(this.pMin,this.pMax));
	}

	@Override
	public Figure move(double dx, double dy) {
		// DONE Auto-generated method stub
		this.pMax.move(dx, dy);
		this.pMin.move(dx, dy);
		return this;
	}

	@Override
	public boolean contains(Point2D p) {
		// DONE Auto-generated method stub
		return this.pMax.equals(p) || this.pMin.equals(p);
	}

	@Override
	public Point2D getCenter() {
		// DONE Auto-generated method stub
		return new Point2D((this.pMin.getX()+this.pMax.getX())/2,(this.pMax.getY()+this.pMin.getY())/2);
	}

	@Override
	public double area() {
		// DONE Auto-generated method stub
		return this.width()*this.height();
	}

	@Override
	protected boolean equals(Figure f) {
		// DONE Auto-generated method stub
		if(f==null) {
			return false;
		}
		if(f==this) {
			return true;
		}
		if(f instanceof Rectangle) {
			return ((Rectangle) f).bottomLeft().equals(this.pMin) && ((Rectangle) f).topRight().equals(this.pMax);
		}
		return false;
	}
	

}
