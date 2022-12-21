package lists;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.w3c.dom.ls.LSParserFilter;

/**
 * Liste simplement chainée itérable.
 * @note On considèrera que la liste ne peut pas contenir d'éléments null
 * @author davidroussel
 * @author Gérard Berthelot
 */
public class ForwardList<E> implements IForwardList<E>
{
	/**
	 * Le maillon représentant la tête de liste
	 */
	protected Link<E> head = null;

	/**
	 * Constructeur par défaut : crée une liste vide
	 */
	public ForwardList()
	{
		// TODO 11 Compléter ...
		head = null;
	}

	/**
	 * Constructeur de copie
	 * @param liste la liste à copier
	 */
	public ForwardList(ForwardList<E> liste)
	{
		// TODO 12 Compléter
		for (E e : liste) {
			add(e);
		}
	}
	/**
	 * Vidage de la liste avant destruction
	 * @throws Throwable
	 */
	@Override
	protected void finalize() throws Throwable
	{
		// TODO 12 Compléter ...
		//vider la liste
		clear();
		//supprimer la tête de liste
		if (head != null)
			head = null;
	}

	/**
	 * Ajout d'un nouvel élement en fin de liste
	 * @param e l'élément à ajouter en fin de liste
	 * @throws NullPointerException si l'on essaie d'ajouter un objet null
	 * @see lists.IForwardList#add(E)
	 */
	@Override
	public void add(E e) throws NullPointerException
	{
		// TODO 13 Compléter ...
		if (e == null)
			throw new NullPointerException("si l'on essaie d'ajouter un objet null");
		
		if (head == null){
			insert(e);
			return;
		}
	
		//cd ListeOperations.pdf
		Link<E> current = head;
		while (current.next != null) 
			current = current.next;
		
		Link<E> newLink = new Link<>(e);
		current.setNext(newLink);
		
	}

	/**
	 * Insertion d'un élément en tête de liste
	 * @param e l'élément à ajouter en tête de liste
	 * @throw NullPointerException si l'on tente d'insérer un objet null
	 */
	@Override
	public void insert(E e) throws NullPointerException
	{	
		//On s'assure que e est != null
		if (e == null)
			throw new NullPointerException("si l'on tente d'insérer un objet null");
		
		//Liste vide
		if (head == null) {
			head = new Link<>(e);
			return;
		}
		
		//récupère la tête
		Link<E> previousHead= head;
		//next de la nouvelle tête
		Link<E> newHead = new Link<>(e);
		newHead.setNext(previousHead);
		//on change head
		head = newHead;

	}

	/**
	 * Insertion d'un élément à la (index+1)ième place
	 * @param e l'élément à insérer
	 * @param index l'index de l'élément à insérer
	 * @return true si l'élément a pu être inséré à l'index voulu, false sinon
	 * ou si l'élément à insérer était null
	 */
	@Override
	public boolean insertAt(E e, int index)
	{
		// TODO 15 Compléter ...
		//si null ou index invalide
		if (e==null || index < 0)
			return false;
		
		//insertion au début
		if (index == 0) {
			insert(e); //ne lève pas d'exception
			return true;
		}
		

		Link<E> current = null;
		Link<E> oldNext = null;
		
		int i;
		for (i = 0; i < index -1; i++) {
			current = current.next;
			if (current == null)
				return false;
		}
		
		
		
		//on regarde si on est allés à l'indice
		if (i != index) return false;
		
		
		if (current == null) {
			add(e);
			return true;
		}
		
		oldNext = current.next;
		
		//on crée un link qui a pour suivant l'ancien suivant
		//nouveau parent :
		Link<E> newLink = new Link<>(e);
		newLink.setNext(oldNext);
		
		//newLink est le nouveau suivant
		previous.setNext(newLink);
		
		return true;
	}

	/*
	 * public boolean remove(E elt)
	 * est implémenté comme default method dans IForwardList<E> car on n'utilise
	 * QUE l'itérateur pour parcourir et evt supprimer l'elt
	 */

	/*
	 * public boolean removeAll(E elt)
	 * est implémenté comme default method dans IForwardList<E> car on n'utilise
	 * QUE l'itérateur pour parcourir et evt supprimer les éléments
	 */

	/*
	 * public int size()
	 * est implémenté comme default method dans IForwardList<E> car on n'utilise
	 * QUE l'itérateur pour parcourir la liste et compter les éléments
	 */

	/*
	 * public void clear()
	 * est implémenté comme default method dans IForwardList<E> car on n'utilise
	 * QUE l'itérateur pour parcourir la liste et détruire les éléments
	 */

	/**
	 * Teste si la liste est vide
	 * @return true si la liste ne contient aucun élement, false sinon
	 * @note réimplémentation de l'implémentation par défaut sans avoir
	 * à utiliser la méthode size()
	 */
	@Override
	public boolean empty()
	{
		// TODO 16 Compléter ...
		return head == null;
	}

	/**
	 * Test d'égalité avec une autre liste : Les listes sont considérées comme
	 * identiques ssi elles ont les mêmes éléments (dans le même ordre) et
	 * qu'elles ont la même longueur.
	 * @return true si les deux listes sont identiques, false sinon.
	 */
	@Override
	public boolean equals(Object o)
	{
		// TODO 17 Compléter ...
		if(o == null) 
			return false;
		if (o==this)
			return true;
		
		//o possède la méthode iterator()?
		if (!(o instanceof Iterable<?>))
			return false;
		
		Iterator<?> otherIterator = ((Iterable<?>) o).iterator();
		Iterator<?> iterator = iterator();
		
		//parcourt les 2 itérateurs et on s'assure
		//qu'ils ont le même élément au même endroit
		while (iterator.hasNext() && otherIterator.hasNext()) {
			Object e1 = iterator.next();
			Object e2 = otherIterator.next();
			if (e1.equals(e2)) continue; // ok même élément : next
			//fin de la boucle
			return false;
		}
		
		//on vérifie que les 2 listes sont vides
		boolean isEmpty = !iterator.hasNext();
		boolean isOtherEmpty = !otherIterator.hasNext();
		return isEmpty && isOtherEmpty;
	}

	/**
	 * hashCode d'une liste basé sur le hashcode de ses maillons (qui lui même
	 * doit être basé sur le hashCode du contenu seulement pour pouvoir être
	 * utilisé plus tard dans la comparaison de collections. L'agorithme de
	 * hachage fournit le même résultat que celui de Vector ce qui permettra de
	 * comparer des collections à base de liste avec des collections à base de
	 * Vector
	 * @see java.lang.Object#hashCode()
	 * @return le code de hashage de la liste
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int hash = 1;

		// TODO 18 Compléter ...
		for (E e : this) {
			hash = prime * hash + ((e == null) ? 0 : e.hashCode());
		}
		
		return hash;
	}

	/**
	 * Représentation sous forme de chaine de caractère de la liste.
	 * @return une chaîne de caractère réprésentant la liste
	 * @implNote exemple : [Hello->Brave->New->World]
	 */
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder("[");

		// TODO 19 Compléter ...
		iterator<E> iterator = iterator();
		while (iterator.hasNext()) {
			E e = iterator.next();
			sb.append(e);
			if (iterator.hasNext())
				sb.append("->");
		}
		
		
		sb.append(']');
		return sb.toString();
	}

	/**
	 * Factory method fournissant l'itérateur sur la liste
	 * @return un nouvel {@link ListIterator} pour parcourir la liste
	 */
	@Override
	public Iterator<E> iterator()
	{
		// TODO 20 Compléter ...
		return new ListIterator<>();
	}

	/**
	 * Classe représentant un maillon d'une liste. Déclaré en tant que classe
	 * imbriquée car elle n'a pas à être connue en dehors de la liste d'une part
	 * et d'autre par le maillon ne doit pas avoir conscience de la Liste qui le
	 * contient
	 * @author David Roussel
	 */
	protected static class Link<E>
	{
		/**
		 * Donnée du maillon
		 */
		private E data = null;

		/**
		 * Maillon suivant
		 */
		private Link<E> next;

		/**
		 * constructeur à partir d'un objet
		 * @param elt l'objet à insérer dans le maillon.
		 * @note on autorise ici l'insertion d'éléments null.
		 */
		public Link(E elt)
		{
			// TODO 06 Compléter ...
			data = elt;
			next = null;
			
		}

		/**
		 * Nettoyage d'un maillon
		 * @throws Throwable
		 */
		@Override
		protected void finalize() throws Throwable
		{
			// TODO 07 Compléter ...
			if (data != null)
				data = null;
			if (next != null)
				//next.finalize();
				next = null;
				
		}
		

		/**
		 * Accesseur en lecture du contenu du maillon
		 * @return renvoie l'objet (la donnée) contenu dans le maillon.
		 */
		public E getData()
		{
			// TODO 08 Compléter ...
			
			return data;
		}

		/**
		 * Accesseur en lecture du maillon suivant
		 * @return le maillon suivant
		 */
		public Link<E> getNext()
		{
			// TODO 09 Compléter ...
			return next;
		}

		/**
		 * Accesseur en écriture du maillon suivant
		 * @param suivant le nouveau maillon suivant à mettre en place
		 */
		public void setNext(Link<E> suivant)
		{
			// TODO 10 Compléter ...
			next = suivant;
		}
	}

	/**
	 * Itérateur de liste. Déclarée en tant que classe interne de manière à ce
	 * qu'elle puisse avoir accès à la tête de liste.
	 * @param <F> type d'éléments à parcourir avec l'itérateur
	 * @note On ne peut pas utiliser <E> ici car le compilateur nous dirait que
	 * le <E> de Liste masque le <E> de l'itérateur, il faut donc utiliser
	 * une autre dénomination
	 * @note Attention, il est important que le ListIterator soit une classe
	 * interne et non pas une classe imbriquée afin qu'elle puisse accéder
	 * directement à l'attribut {@link ForwardList#head} qui peut être modifié lors
	 * de la suppression du premier élément de la liste.
	 */
	protected class ListIterator<F> implements Iterator<F>
	{
		/**
		 * Référence vers le maillon à partir duquel on commence l'itération. En
		 * général on commence à partir de la tête.
		 */
		private Link<F> current = null;

		/**
		 * Donnée du maillon courant. Utilisée pour stocker la dernière
		 * donnée renvoyée dans la méthode {@link ListIterator#next()}
		 */
		private F element;

		/**
		 * Indique que la méthode {@link #next()} vient d'être appelée ce qui
		 * autorise un appel éventuel à la méthode {@link #remove()}
		 */
		private boolean nextCalled;

		/**
		 * Maillon précédent le maillon courant. C'est ce maillon qui devra
		 * éventuellement être détruit par la méthode {@link #remove()}
		 */
		private Link<F> last = null;

		/**
		 * Maillon précédent du maillon précédant le maillon courant. Celui que
		 * l'on devra relinker avec le {@link #current} pour supprimer le
		 * dernier maillon atteint.
		 */
		private Link<F> penultimate = null;

		/**
		 * Constructeur de l'itérateur de liste
		 * @param liste référence vers la liste à itérer.
		 */
		// à cause du cast en (Maillon<F>)
		@SuppressWarnings("unchecked")
		public ListIterator()
		{
			/*
			 * TODO 21 Compléter ...
			 * Initialisation de tous les attributs
			 * Note vous pouvez accéder à ForwardList#head et evt le caster
			 * en Link<F> pour initialiser current
			 */
			current = null;
			element = null;//valeur de last?->current
			nextCalled = false;
			last = null; //le truc qui sera remove
			penultimate = null; //le truc juste avant le truc remove
		}

		/**
		 * Définit s'il reste des éléments à itérer dans la liste.
		 * @return true s'il reste des éléments à itérer dans la liste
		 */
		@Override
		public boolean hasNext()
		{
			// TODO 22 Compléter ...
			return false;
		}

		/**
		 * Obtention de la donnée du maillon courant de la liste et passage à
		 * l'élément suivant.
		 * @return la donnée du maillon courant
		 * @throws NoSuchElementException lorsqu'il n'y a plus d'élément à
		 * renvoyer lorsque l'itération est finie
		 */
		@Override
		public F next() throws NoSuchElementException
		{
			// TODO 23 Compléter ...
			throw new NoSuchElementException("Liste::ListeIterator::next");
		}

		/**
		 * Suppression du dernier élément renvoyé par next. Sachant que remove
		 * est appelé après next, il nous faut donc supprimer le Maillon suivant
		 * de penultimate (last) puis on recolle sur le nouveau current.
		 * @throws IllegalStateException lorsque l'on tente d'utiliser remove
		 * alors que la méthode {@link #next()} n'a pas encore été
		 * appellée.
		 */
		@Override
		public void remove() throws IllegalStateException
		{
			if (nextCalled) // next a été appelé on peut utiliser remove
			{
				if (last == head) // ==> current = second maillon, on décapite
				{
					head = head.getNext();
					last = null;
					// penultimate est toujours null
					// la tete de liste se retrouve sur current
				}
				else // last != head
				{
					if (penultimate != null)
					{
						penultimate.setNext(current);
						last = penultimate;
						penultimate = null;
					}
					else // penultimate == null
					{
						throw new IllegalStateException("remove: illegal penultimate null state");
					}
				}
				nextCalled = false;
			}
			else // next n'a pas été appelé : illegal state
			{
				throw new IllegalStateException("remove: next not called yet");
			}
		}

		/*
		 * (non-Javadoc)
		 * @see java.lang.Object#finalize()
		 */
		@Override
		protected void finalize()
		{
			current = null;
			element = null;
			last = null;
			penultimate = null;
		}
	}
}
