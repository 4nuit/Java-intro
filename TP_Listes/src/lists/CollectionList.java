package lists;

import java.util.AbstractCollection;

/**
 * Collection basée sur une liste simplement chainée
 * @author davidroussel
 */
public class CollectionList<E> extends AbstractCollection<E>
{

	/**
	 * La liste que l'on va utiliser comme conteneur sous-jacent à cette
	 * collection
	 * inverse="collectionList:listes.ForwardList"
	 */
	private ForwardList<E> liste;

	// TODO 24 Implémenter les constructeurs suivants

	/**
	 * Constructeur par défaut. Crée une collection vide en initialisant une
	 * liste vide
	 */

	/**
	 * Constructeur de copie à partir d'une autre {@link Collection} ou d'un
	 * {@link Iterable}
	 * @param col la collection dont on doit copier le contenu
	 */

	// TODO 25 Quick Fix --> Add unimplemented methods

	/**
	 * Ajout d'un élément à cette collection. Ajoute l'élement à la liste
	 * sous-jacente
	 * @param e element whose presence in this collection is to be ensured
	 * @return true if this collection changed as a result of the call
	 * @see java.util.AbstractCollection#add(java.lang.Object)
	 */

	/**
	 * Factory méthode de l'itérateur
	 * @return un itérateur sur cette colelction qui n'est autre que l'itérateur
	 * de la liste sous-jacente
	 * @see java.util.AbstractCollection#iterator()
	 */

	/**
	 * Taille de la collection
	 * @return le nombre d'élements dans la collection
	 * @see java.util.AbstractCollection#size()
	 */

	/**
	 * Hachcode de la collection
	 * @see java.lang.Object#hashCode()
	 */

	/**
	 * Test d'égalité entre cette CollectionListe et l'objet passé en argument.
	 * @return true si obj est un {@link Iterable} contenant les mêmes éléments
	 * dans le même ordre. false dans les cas contraires.
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
}
