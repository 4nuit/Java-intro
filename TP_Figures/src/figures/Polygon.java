/**
 * 
 */
package figures;

import java.util.ArrayList;
import java.util.Collection;

import points.Point2D;

/**
 * @author aurelien.bourgerie
 *
 */
public class Polygon extends AbstractFigure implements Figure {
	
	protected ArrayList<Point2D> points;
	protected Rectangle BoundingBox;
	/**
	 * 
	 */
	public Polygon() {
		this.points=new ArrayList<>();
		this.points.add(new Point2D(2.0,1.0));
		this.points.add(new Point2D(4.0,1.0));
		this.points.add(new Point2D(5.0,3.0));
		this.points.add(new Point2D(4.0,5.0));
		this.points.add(new Point2D(2.0,5.0));
		// DONE Auto-generated constructor stub
		this.BoundingBox=new Rectangle();
	}
	public Polygon(Point2D ... pts) {
		this.points=new ArrayList<>();
		for(int i=0;i<pts.length;i++) {
			points.add(pts[i]);
		}
		this.BoundingBox=new Rectangle();
	}
	public Polygon(Collection<Point2D> pts) {
		this.points=new ArrayList<>();
		for(Point2D p : pts) {
			points.add(p);
		}
		this.BoundingBox=new Rectangle();
	}
	@SuppressWarnings("unchecked")
	public Polygon(Polygon p) {
		this.points=(ArrayList<Point2D>) p.points.clone();
		this.BoundingBox=(Rectangle) p.BoundingBox;
	}
	public boolean add(double x,double y) {
		Point2D p=new Point2D(x,y);
		if(!this.contains(p)){
			this.points.add(p);
			return true;
		}
		else {
			return false;
		}
	}

	public boolean add(Point2D p) throws NullPointerException{
		if(p==null) {
			throw new NullPointerException();
		}
		if(!this.contains(p)){
			this.points.add(p);
			return true;
		}
		else {
			return false;
		}
	}
	public boolean remove(Point2D p) throws NullPointerException{
		if(p==null) {
			throw new NullPointerException();
		}
		if(!this.contains(p)){
			this.points.remove(p);
			return true;
		}
		else {
			return false;
		}
	}
	public boolean remove(){
		if(this.points.isEmpty() || this.points==null) {
			return false;
		}
		else {
			this.points.remove(-1);
			return true;
		}
	}
	
	@Override
	public Point2D getBoundingBoxCenter() {
		// DONE Auto-generated method stub
		return this.BoundingBox.getCenter();
	}

	@Override
	public double width() {
		// DONE Auto-generated method stub
		return this.BoundingBox.width();
	}

	@Override
	public double height() {
		// DONE Auto-generated method stub
		return this.BoundingBox.height();
	}

	@Override
	public Figure move(double dx, double dy) {
		// DONE Auto-generated method stub
		for(Point2D p:this.points) {
			p.move(dx, dy);
		}
		return this;
	}

	@Override
	public boolean contains(Point2D p) {
		// DONE Auto-generated method stub
		int j=this.points.size()-1;
		boolean oddnodes=false;
		for(int i=0;i<this.points.size();++i) {
			if(this.points.get(i).getY()<p.getY() && this.points.get(j).getY()>=p.getY() || this.points.get(j).getY()<p.getY() && this.points.get(i).getY()>=p.getY()) {
				if((this.points.get(i).getX()+p.getY()-this.points.get(i).getY())/((this.points.get(j).getY()-this.points.get(i).getY())*(this.points.get(j).getX()-this.points.get(i).getX()))<p.getX()) {
				oddnodes=!oddnodes;}
			}
		j=i;
		}
		return oddnodes;
	}

	@Override
	public Point2D getCenter() {
		// DONE Auto-generated method stub
		double cx=0;
		double cy=0;
		for (int i=0;i<this.points.size()-1;i++) {
			cx+=(this.points.get(i).getX()+this.points.get(i+1).getX())*(this.points.get(i).getX()*this.points.get(i+1).getY()-this.points.get(i+1).getX()*this.points.get(i).getY());
			cy+=(this.points.get(i).getY()+this.points.get(i+1).getY())*(this.points.get(i).getX()*this.points.get(i+1).getY()-this.points.get(i+1).getX()*this.points.get(i).getY());
		}
		double diviseur=1.0;
		if(this.area()>0) {
		diviseur=6*this.area();}
		
		
		return new Point2D(cx/diviseur,cy/diviseur);
	}

	@Override
	public double area() {
		// DONE Auto-generated method stub
		double area=0;
		for(int i=0;i<this.points.size()-1;++i) {
			area+=(this.points.get(i).getX()*this.points.get(i+1).getY()-this.points.get(i+1).getX()*this.points.get(i).getY());
					
		}
		return area/2;
	}

	@Override
	protected boolean equals(Figure f) {
		// DONE Auto-generated method stub
		if(f==null) {
			return false;
		}
		if(this==f) {
			return true;
		}
		if(f instanceof Polygon) {
			return this.BoundingBox.equals(((Polygon) f).BoundingBox) && this.points.equals(((Polygon) f).points);
		}
		return false;
	}

}