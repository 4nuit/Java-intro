package points;

/**
 * Classe définissant un point du plan 2D
 * @author David Roussel
 */
public class Point2D
{
	// attributs d'instance --------------------------------------------------
	/**
	 * l'abcisse du point
	 */
	protected double x;
	/**
	 * l'ordonnée du point
	 */
	protected double y;

	// attributs de classe ---------------------------------------------------
	/**
	 * Compteur d'instances : le nombre de points actuellement instanciés
	 */
	protected static int nbPoints = 0;

	/**
	 * Constante servant à comparer deux points entre eux (à {@value} près). On
	 * comparera alors la distance entre deux points.
	 * @see #distance(Point2D)
	 * @see #distance(Point2D, Point2D)
	 */
	protected static final double epsilon = 1e-6;

	/*
	 * Constructeurs
	 */
	/**
	 * DONE Constructeur par défaut. Initialise un point à l'origine du repère [0.0,
	 * 0.0]
	 */
	public Point2D() {
		this(0.0,0.0);
		
	}
	/**
	 * DONE Constructeur valué
	 * @param x l'abcisse du point à créer
	 * @param y l'ordonnée du point à créer
	 */
	public Point2D(double x,double y) {
		this.x=x;
		this.y=y;
		nbPoints++;
	}
	/**
	 * DONE Constructeur de copie
	 * @param p le point dont il faut copier les coordonnées Il s'agit ici d'une
	 * copie profonde de manière à créer une nouvelle instance
	 * possédant les même caractéristiques que celle dont on copie
	 * les coordonnées.
	 */
	public Point2D(Point2D d) {
		this(d.x,d.y);
		
	}
	/**
	 * Nettoyeur avant destruction Permet de décrémenter le compteur d'instances
	 */
	@Override
	protected void finalize()
	{
		nbPoints--;
	}

	/*
	 * DONE Accesseurs
	 * 	- get[X|Y]
	 * 	- set[X|Y]
	 * 	- getEpsilon
	 * 	- getNbPoints
	 * 	- Source > Generate Getters and Setters ...
	 */
	
	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}
	public void setX(double x) {
		this.x=x;
	}
	public void setY(double y) {
		this.y=y;
	}
	public static double getEpsilon() {
		return epsilon;
	}
	public static int getNbPoints() {
		return nbPoints;
	}
	/**
	 * Opérations sur un point
	 * @param dx le déplacement en x
	 * @param dy le déplacement en y
	 * @return renvoie la référence vers l'instance courante (this) de manière à
	 * pouvoir enchainer les traitements du style :
	 * unObjet.uneMéthode(monPoint.deplace(dx,dy))
	 */
	public Point2D move(double dx, double dy)
	{
		x += dx;
		y += dy;
		return this;
	}

	/*
	 * Méthodes de classe : opérations sur les points
	 */
	/**
	 * Calcul de l'écart en abcsisse entre deux points. Cet écart ne concerne
	 * pas plus le premier que le second point c'est pourquoi on en fait une
	 * méthode de classe.
	 * @param p1 le premier point
	 * @param p2 le second point
	 * @return l'écart en x entre les deux points
	 */
	public static double dx(Point2D p1, Point2D p2)
	{
		return (p2.x - p1.x);
	}

	/**
	 * Calcul de l'écart en ordonnée entre deux points. Cet écart ne concerne
	 * pas plus le premier que le second point c'est pourquoi on en fait une
	 * méthode de classe.
	 * @param p1 le premier point
	 * @param p2 le second point
	 * @return l'écart en y entre les deux points
	 */
	public static double dy(Point2D p1, Point2D p2)
	{
		return (p2.y - p1.y);
	}

	/**
	 * DONE Calcul de la distance 2D entre deux points. Cette distance ne concerne
	 * pas plus un point que l'autre c'est pourquoi on en fait une méthode de
	 * classe. Cette méthode utilise les méthodes {@link #dx(Point2D, Point2D)}
	 * et {@link #dy(Point2D, Point2D)} pour calculer la distance entre les
	 * points.
	 * @param p1 le premier point
	 * @param p2 le seconde point
	 * @return la distance entre les points p1 et p2
	 * @see #dx(Point2D, Point2D)
	 * @see #dy(Point2D, Point2D)
	 */
	 public static double distance(Point2D p1,Point2D p2) {
		 return Math.sqrt(dx(p1,p2)*dx(p1,p2)+ dy(p1,p2)*dy(p1,p2));
	 }

	/**
	 * DONE Calcul de distance 2D par rapport au point courant
	 * @param p l'autre point dont on veut calculer la distance
	 * @return la distance entre le point courant et le point p
	 * @see #distance(Point2D, Point2D)
	 */
	 public double distance(Point2D p) {
		 return Math.sqrt(dx(this,p)*dx(this,p)+dy(this,p)*dy(this,p));
	 }
	

	/*
	 * DONE Affichage contenu et test d'égalité
	 * 	- toString
	 * 	- equals avec Point2D puis avec Object
	 * 	- Source > Override/Implements Methods ...
	 */
	 @Override
		public boolean equals(Object obj) {
			// DONE Auto-generated method stub
			if(obj==null) {
				return false;
			}
			if(obj==this) {
				return true;
			}
			if(this.getClass().isInstance(obj)) {
				return distance((Point2D)obj)<this.epsilon;
			}
			else{
				return false;
			}
		}
	 public boolean equals(Point2D p) {
		 return distance(p)<epsilon;
	 }
		@Override
		public String toString() {
			// DONE Auto-generated method stub
			return "x=" +this.x + " y="+this.y;
		}
	// toString est une méthode classique en Java, elle est présente
	// dans les objets de type Object, on pourra donc ainsi l'utiliser
	// dans une éventuelle Liste de points.
}
