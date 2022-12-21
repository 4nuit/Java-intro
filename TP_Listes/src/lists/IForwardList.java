package lists;

import java.util.Iterator;

/**
 * Interface d'une liste simplement chaînée générique d'éléments.
 * @note On considèrera que la liste ne peut pas contenir d'elt null
 * @author David Roussel
 * @param <E> le type des éléments de la liste.
 */
public interface IForwardList<E> extends Iterable<E>
{
	/**
	 * Ajout d'un élément en fin de liste
	 * @param elt l'élément à ajouter en fin de liste
	 * @throws NullPointerException si l'on tente d'ajouter un élément null
	 */
	public abstract void add(E elt) throws NullPointerException;

	/**
	 * Insertion d'un élément en tête de liste
	 * @param elt l'élément à ajouter en tête de liste
	 * @throws NullPointerException si l'on tente d'insérer un élement null
	 */
	public abstract void insert(E elt) throws NullPointerException;

	/**
	 * Insertion d'un élément à la (index+1)ième place
	 * @param elt l'élément à insérer
	 * @param index l'index de l'élément à insérer
	 * @return true si l'élément a pu être inséré à l'index voulu, false sinon
	 * ou si l'élément à insérer était null
	 */
	public abstract boolean insertAt(E elt, int index);

	/**
	 * Suppression de la première occurrence de l'élémént e
	 * @param elt l'élement à rechercher et à supprimer.
	 * @return true si l'élément a été trouvé et supprimé de la liste
	 * @note doit fonctionner même si e est null
	 * @implNote utiliser la méthode {@link Iterator.remove}
	 */
	public default boolean remove(E elt)
	{
		// TODO 01 Compléter en utilisant l'iterator ...
		Iterator<E> iterator = iterator(); //crée un itérateur sur la liste actuelle
		while (iterator.hasNext()) {
			E current = iterator.next();
			//si on trouve elt
			if (current.equals(elt)) {
				//on le supprime
				iterator.remove();
				//on se barre
				return true;
			}
		}
		return false;
	}

	/**
	 * Suppression de toutes les instances de elt dans la liste
	 * @param elt l'élément à supprimer
	 * @return true si au moins un élément a été supprimé
	 * @note doit fonctionner même si e est null
	 * @implNote utiliser la méthode {@link Iterator.remove}
	 */
	public default boolean removeAll(E elt)
	{
		// TODO 02 Compléter en utilisant l'iterator ...
		boolean deleted = false;
		Iterator<E> iterator = iterator(); // crée un itérateur sur la liste actuelle
		while (iterator.hasNext()) {
			E current = iterator.next();
			// si on trouve un élément
			if (current.equals(elt)) {
				// on le supprime
				iterator.remove();
				deleted = true;
			}
			
		}
		return deleted;
	}

	/**
	 * Nombre d'éléments dans la liste
	 * @return le nombre d'éléments actuellement dans la liste
	 * @implNote utiliser l'itérateur
	 */
	public default int size()
	{
		// TODO 03 Compléter en utilisant l'iterator ...
		Iterator<E> iterator = iterator();
		int size = 0;
		while (iterator.hasNext()) {
			size++;
			iterator.next();
		}
		return size;
	}

	/**
	 * Effacement de la liste;
	 * @implNote utiliser l'itérateur et sa méthode {@link Iterator.remove}
	 */
	public default void clear()
	{
		// TODO 04 Compléter en utilisant l'iterator ...
		Iterator<E> iterator = iterator();
		while (iterator.hasNext()) {
			iterator.next(); //NEXT avant  REMOVE -> on avance le curseur
			iterator.remove();
		}
	
	}

	/**
	 * Test de liste vide
	 * @return true si la liste est vide, false sinon
	 * @implNote utiliser l'itérateur et sa méthode {@link Iterator.hasNext}
	 */
	public default boolean empty()
	{
		// TODO 05 Compléter ...
		Iterator<E> iterator = iterator();
		return iterator.hasNext();
	}

	/**
	 * Test d'égalité au sens du contenu de la liste
	 * @param o la liste dont on doit tester le contenu
	 * @return true si o est une liste, que tous les maillons des deux listes
	 * sont identiques (au sens du equals de chacun des maillons), dans
	 * le même ordre, et que les deux listes ont la même longueur. false
	 * sinon
	 * @note On serait tenté d'en faire une "default method" dans la mesure où
	 * l'on peut n'utiliser que l'itérateur pour parcourir les éléments de
	 * la liste MAIS les méthodes par défaut n'ont pas le droit de
	 * surcharger les méthodes de la superclasse Object.
	 */
	@Override
	public abstract boolean equals(Object o);

	/**
	 * HashCode d'une liste
	 * @return le hashCode de la liste
	 * @note On serait tenté d'en faire une "default method" dans la mesure où
	 * l'on peut n'utiliser que l'itérateur pour parcourir les éléments de
	 * la liste MAIS les méthodes par défaut n'ont pas le droit de
	 * surcharger les méthodes de la superclasse Object.
	 */
	@Override
	public abstract int hashCode();

	/**
	 * Représentation de la chaine sous forme de chaine de caractère.
	 * @return une chaine de caractère représentant la liste chainée
	 * @note On serait tenté d'en faire une "default method" dans la mesure où
	 * l'on peut n'utiliser que l'itérateur pour parcourir les éléments de
	 * la liste MAIS les méthodes par défaut n'ont pas le droit de
	 * surcharger les méthodes de la superclasse Object.
	 */
	@Override
	public abstract String toString();

	/**
	 * Obtention d'un itérateur pour parcourir la liste : <code>
	 * ForwardList<Type> l = new ForwardList<Type>();
	 * ...
	 * for (Iterator<Type> it = l.iterator(); it.hasNext(); )
	 * {
	 * 		... it.next() ...
	 * }
	 * ou bien
	 * for (Type elt : l)
	 * {
	 * 		... elt ...
	 * }
	 * </code>
	 * @return un nouvel itérateur sur la liste
	 * @see {@link Iterable#iterator()}
	 */
	@Override
	public abstract Iterator<E> iterator();
}
