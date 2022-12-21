package figures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import points.Point2D;

public class Group extends AbstractFigure implements Figure, Collection<Figure> {
	protected Collection<Figure> figures;
	public Group() {
		// DONE Auto-generated constructor stub
		this.figures=new ArrayList<Figure>();
	}

	public Group(String aName) {
		super(aName);
		// DONE Auto-generated constructor stub
	}
	public Group(Collection<Figure> figures) {
		this.figures=new ArrayList<>();
		for(Figure f:figures) {
			this.figures.add(f);
		}
	
	}
	public Group(Group g) {
		this.figures=new ArrayList<>();
		for(Figure f:g.figures) {
			this.figures.add(f);
		}
	}

	@Override
	public Point2D getBoundingBoxCenter() {
		// TODO Auto-generated method stub
		double x=0.0;
		double y=0.0;
		for(Figure f:this.figures) {
			x+=f.getBoundingBoxCenter().getX();
			y+=f.getBoundingBoxCenter().getY();
		}
		return new Point2D(x/this.figures.size(),y/this.figures.size());
	}

	@Override
	public double width() {
		// TODO Auto-generated method stub
		double width=0.0;
		for(Figure f:this.figures) {
			width+=f.width();
		}
		return width;
	}

	@Override
	public double height() {
		// TODO Auto-generated method stub
		double height=0.0;
		for(Figure f:this.figures) {
			height+=f.height();
		}
		return height;
	}

	@Override
	public Figure move(double dx, double dy) {
		// TODO Auto-generated method stub
		for(Figure f:this.figures) {
			f.move(dx, dy);
		}
		return this;
	}

	@Override
	public boolean contains(Point2D p) {
		// TODO Auto-generated method stub
		for(Figure f:this.figures) {
			if(f.contains(p)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Point2D getCenter() {
		// TODO Auto-generated method stub
		double x=0;
		double y=0;
		for(Figure f:this.figures) {
			x+=f.getCenter().getX();
			y+=f.getCenter().getY();
		}
		return new Point2D(x/figures.size(),y/figures.size());
	}

	@Override
	public double area() {
		// DONE Auto-generated method stub
		double area=0.0;
		for(Figure f:this.figures) {
			area+=f.area();
		}
		return area;
	}

	@Override
	protected boolean equals(Figure f) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// DONE Auto-generated method stub
		return this.figures.size();
	}

	@Override
	public boolean isEmpty() {
		// DONE Auto-generated method stub
		return this.figures.isEmpty();
	}

	@Override
	public boolean contains(Object o) throws NullPointerException{
		// TODO Auto-generated method stub
		if(o==null) {
			throw new NullPointerException();
		}
		if(o==this) {
			return true;
		}
		if(o instanceof Point2D) {
			return this.contains(o);
		}
		if(o instanceof Figure) {
			for(Figure f:this.figures) {
				if(f.equals(o)) {
					return true;
				}
			}
			return false;
		}
		return false;
	}

	@Override
	public Iterator<Figure> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return this.figures.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return  this.figures.toArray();
	}

	@Override
	public boolean add(Figure e) throws NullPointerException{
		// TODO Auto-generated method stub
		if (this.figures==null) {
			throw new NullPointerException();
		}
		if(!this.contains(e)) {
			this.figures.add(e);
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		if (this.figures==null || this.figures.isEmpty()) {
			return false;
		}
		if(o instanceof Figure) {
			if(this.figures.contains(o)) {
			this.figures.remove(o);
			return true;}
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) throws NullPointerException{
		// DONE Auto-generated method stub
		if(c==null || this.figures==null) {
			throw new NullPointerException();
		}
		else {
			for(Object f:c){
				if(!this.figures.contains(f)) {
					return false;
				}
			}
			return true;
		}
	}

	public boolean addAll(Collection<? extends Figure> c) throws NullPointerException{
		// TODO Auto-generated method stub
		if(c==null|| c.contains(null)) {
			throw new NullPointerException();
		}
		else {
			for(Figure f:c) {
				this.figures.add(f);
			}
			return true;
		}
	}

	@Override
	public boolean removeAll(Collection<?> c) throws NullPointerException{
		int size=this.figures.size();
		if(c==null || this.figures==null ) {
			throw new NullPointerException();
		}
		if( this.figures.isEmpty()) {
			return false;
		}
		else {
			for(Object f:c){
				if(this.figures.contains(f)) {
				this.remove(f);
				}
			}
			return size>this.figures.size();
		}
	}

	@Override
	public boolean retainAll(Collection<?> c) throws NullPointerException{
		// TODO Auto-generated method stub
		int size=this.figures.size();
		
		if(c==null || this.figures==null) {
			throw new NullPointerException();
		}
		if(this.figures.isEmpty()) {
			return false;
		}
		else {
			for(Object f:c){
				if(!this.figures.contains(f)) {
					this.remove(f);
				}
			}
			return true;
		}
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

}
