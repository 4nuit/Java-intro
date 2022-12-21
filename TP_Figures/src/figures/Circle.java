package figures;

import points.Point2D;

/**
 * Classe cercle héritière de la classe abstraite Figure doit donc implémenter
 * (entre-autres) les méthodes abstraites suivantes
 * @see AbstractFigure#move
 * @see AbstractFigure#contains
 * @see AbstractFigure#getCenter
 * @see AbstractFigure#area
 */
public class Circle extends AbstractFigure
{
	/**
	 * Centre
	 */
	protected Point2D center;

	/**
	 * Rayon
	 */
	protected double radius;

	// Constructeurs --------------------------------------------------------
	/**
	 * Constructeur par défaut.
	 * Crée un cercle de centre (0, 0) et de rayon 1.
	 */
	public Circle()
	{
		// super() est implicite
		// DONE Compléter ...
		this(0.0,0.0,1);
	}

	/**
	 * Constructeur valué : position + rayon
	 * @param x abcisse
	 * @param y ordonnée
	 * @param r rayon
	 */
	public Circle(double x, double y, double r)
	{
		this.center=new Point2D(x,y);
		this.radius=r;
	}

	/**
	 * constructeur valué : position + rayon
	 * @param p Point central
	 * @param r rayon
	 */
	public Circle(Point2D p, double r)
	{
		// DONE Compléter ...
		this.center=p;
		this.radius=r;
	}

	/**
	 * Contructeur de copie à partir d'un autre cercle
	 * @param c le Cercel à copier
	 */
	public Circle(Circle c)
	{
		this(c.center,c.radius);
		return;
		// DONE Compléter ...
	}

	// Accesseurs -----------------------------------------------------------
	/**
	 * Accesseur en lecture pour le centre
	 * @return le point central du cercle
	 */
	@Override
	public Point2D getCenter()
	{
		// DONE Remplacer par l'implémentation ...
		return this.center;
	}

	/**
	 * Accesseur en lecture du centre de la boite englobante
	 * @return le point central du cercle
	 * @see figures.Figure#getBoundingBoxCenter()
	 */
	@Override
	public Point2D getBoundingBoxCenter()
	{
		// DONE Remplacer par l'implémentation ...
		return this.center;
	}

	/**
	 * Accesseur en lecture pour le rayon
	 * @return le rayon du cercle
	 */
	public double getRadius()
	{
		return radius;
	}

	/**
	 * Accesseur en écriture pour le rayon
	 * @param r rayon du cercle : si r est négatif le rayon est alors mis à 0.0
	 */
	public void setRadius(double r)
	{
		if (r < 0.0)
		{
			radius = 0.0;
		}
		else
		{
			radius = r;
		}
	}

	/**
	 * Déplacement du cercle = deplacement du centre
	 * @param dx déplacement suivant x
	 * @param dy déplacement suivant y
	 * @return une référence vers la figure déplacée
	 */
	@Override
	public Figure move(double dx, double dy)
	{
		this.center.move(dx, dy);
		return this;
	}

	/**
	 * Affichage contenu
	 * @return une chaine représentant l'objet (centre + rayon)
	 * @implSpec "name : centerpoint, r = radius"
	 */
	@Override
	public String toString()
	{
		return new String("name = "+ this.getName() + "r = "+ this.radius);
	}

	/**
	 * Test de contenu : teste si le point passé en argument est contenu à
	 * l'intérieur du cercle
	 * @param p point à tester
	 * @return une valeur booléenne indiquent si le point est contenu ou pas à
	 * l'intérieur du cercle
	 */
	@Override
	public boolean contains(Point2D p)
	{
		// DONE Remplacer par l'implémentation
		return Point2D.distance(this.center, p)<=this.radius;
	}

	/**
	 * Largeur du cercle
	 * @return le rayon du cercle
	 */
	@Override
	public double width()
	{
		// DONE Remplacer par l'implémentation
		return this.radius;
	}

	/**
	 * Hauteur du cercle
	 * @return le rayon du cercle
	 */
	@Override
	public double height()
	{
		// DONE Remplacer par l'implémentation
		return this.radius;
	}

	/**
	 * Aire
	 * @return renvoie l'aire couverte par le cercle
	 */
	@Override
	public double area()
	{
		// DONE Remplacer par l'implémentation
		return Math.PI*this.radius*this.radius;
	}

	@Override
	public boolean equals(Figure figure)
	{
		// DONE Remplacer par l'implémentation
		if(figure==null) {
			return false;
		}
		if(figure==this) {
			return true;
		}
		if(figure instanceof Circle) {
			return this.getCenter().equals(figure.getCenter()) && this.radius==figure.width();
		}
		return false;
	}
}
