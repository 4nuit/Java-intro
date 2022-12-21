/**
 * 
 */
package points;

/**
 * @author aurelien.bourgerie
 *
 */
public class Point3D extends Point2D {
	protected double z;
	protected static int nbpoints;
	/**
	 * 
	 */
	public Point3D() {
		this(0.0,0.0,0.0);
		// DONE Auto-generated constructor stub
	}

	/**
	 * @param x
	 * @param y
	 */
	public Point3D(double x, double y,double z) {
		super(x, y);
		this.z=z;
		nbpoints++;
		// DONE Auto-generated constructor stub
	}

	/**
	 * @param d
	 */
	public Point3D(Point2D d) {
		super(d);
		this.z=0.0;
		return;
		//  Auto-generated constructor stub
	}
	
	public Point3D(Point3D p) {
		this(p.x,p.y,p.z);
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public static int getNbpoints() {
		return nbpoints;
	}

	@Override
	public boolean equals(Object obj) {
		// DONE Auto-generated method stub
		if(obj==null) {
			return false;
		}
		if(obj==this) {
			return true;
		}
		if(obj instanceof Point3D) {
			return equals(obj);
		}
		if((obj instanceof Point2D) && (Math.abs(this.z)<epsilon)) {
			return equals((Point2D) obj); 
		}
		return false;
	}
	protected boolean equals(Point3D p) {
		return distance(this,p)<epsilon;
	}
	@Override
	public String toString() {
		// DONE Auto-generated method stub
		return super.toString() + "z= "+ this.z;
	}
	public Point3D move(double x,double y,double z) {
		super.move(x, y);
		this.z+=z;
		return this;
	}
	public static double dz(Point3D P1,Point3D P2) {
		return (P1.z-P2.z);
	}
	public static double distance(Point3D P1,Point3D P2) {
		double dx= dx(P1,P2);
		double dy=dy(P1,P2);
		double dz=dz(P1,P2);
		return Math.sqrt(dx*dx+dy*dy+dz*dz);
	}
	
	

}
