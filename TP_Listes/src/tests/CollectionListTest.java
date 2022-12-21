package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lists.CollectionList;

/**
 * Classe de test de la CollectionList en tant que Collection
 *
 * @author davidroussel
 */
public class CollectionListTest
{
	/**
	 * La liste à tester. La nature du contenu de la liste importe peu du moment
	 * qu'il est homogène : donc n'importe quel type ferait l'affaire.
	 */
	private CollectionList<String> collection;

	/**
	 * Liste des éléments à ajouter à la collection
	 */
	private static String[] elements = new String[] {
		"Hello",
		"Brave",
		"New",
		"World" };

	/**
	 * Element supplémentaire à ajouter à la collection
	 */
	private static String extraElement = new String("Of Pain");

	/**
	 * Mise en place avant l'ensemble des tests
	 *
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	public static void setUpBeforeClass() throws Exception
	{
		System.out.println("-------------------------------------------------");
		System.out.println("Test CollectionList");
		System.out.println("-------------------------------------------------");

	}

	/**
	 * Nettoyage après l'ensemble des tests
	 *
	 * @throws java.lang.Exception
	 */
	@AfterAll
	public static void tearDownAfterClass() throws Exception
	{
		System.out.println("-------------------------------------------------");
		System.out.println("Fin Test CollectionList");
		System.out.println("-------------------------------------------------");

	}

	/**
	 * Mise en place avant chaque test
	 *
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception
	{
		collection = new CollectionList<String>();
	}

	/**
	 * Nettoyage après chaque test
	 *
	 * @throws java.lang.Exception
	 */
	@AfterEach
	public void tearDown() throws Exception
	{
		collection.clear();
		collection = null;
		System.gc();
	}

	/**
	 * Remplissage d'une collection avec les éléments de #elements
	 * @param collection la collection à remplir
	 */
	public static void fillCollection(Collection<String> collection)
	{
		for (String elt : elements)
		{
			collection.add(elt);
		}
	}

	/**
	 * Test method for {@link lists.CollectionList#CollectionList()}.
	 */
	@Test
	@DisplayName("CollectionList()")
	public final void testCollectionList()
	{
		String testName = new String("CollectionList()");
		System.out.println(testName);

		assertNotNull(collection, testName + " failed with null instance");
		assertTrue(collection.isEmpty(), testName + " failed with non empty collection");
		assertEquals(0, collection.size(), testName + " failed with non 0 size");
	}

	/**
	 * Test method for
	 * {@link lists.CollectionList#CollectionList(java.util.Collection)}.
	 */
	@Test
	@DisplayName("CollectionList(Collection<E> col)")
	public final void testCollectionListCollectionOfE()
	{
		String testName = new String("CollectionList(Collection<E> col)");
		System.out.println(testName);

		ArrayList<String> otherCollection = new ArrayList<String>();
		fillCollection(otherCollection);

		collection = new CollectionList<String>(otherCollection);

		assertNotNull(collection, testName + " failed with null instance");
		assertFalse(collection.isEmpty(), testName + " failed with empty collection");
		assertEquals(elements.length, collection.size(), testName + " failed with wrong size");
		int i = 0;
		for (String elt : collection)
		{
			assertSame(elements[i++],
			           elt,
			           testName + " failed with unexpected elt value at ["
			               + String.valueOf(i) + "]");
		}
	}

	/**
	 * Test method for {@link lists.CollectionList#add(java.lang.Object)}.
	 */
	@Test
	@DisplayName("boolean add(E elt)")
	public final void testAddE()
	{
		String testName = new String("boolean add(E elt)");
		System.out.println(testName);

		collection.add(extraElement);

		assertEquals(1, collection.size(), testName + " failed with non 1 size");
		Iterator<String> it = collection.iterator();
		assertTrue(it.hasNext(), testName + " failed with finished iterator");
		assertSame(extraElement, it.next(), testName + " failed with unexpected next value");
		assertFalse(it.hasNext(), testName + " failed with unfinished iterator");

		// check adding a null element returns false
		assertFalse(collection.add(null),
		            testName + " failed with added null elt");
	}

	/**
	 * Test method for
	 * {@link java.util.AbstractCollection#addAll(java.util.Collection)}.
	 */
	@Test
	@DisplayName("boolean addAll(Collection<? extends E> c)")
	public final void testAddAll()
	{
		String testName = new String("boolean addAll(Collection<? extends E> c)");
		System.out.println(testName);

		ArrayList<String> otherCollection = new ArrayList<String>();
		fillCollection(otherCollection);

		collection.addAll(otherCollection);

		assertNotNull(collection, testName + " failed with null instance");
		assertFalse(collection.isEmpty(), testName + " failed wit empty collection");
		assertEquals(elements.length, collection.size(), testName + " with wrong collection size");
		int i = 0;
		for (String elt : collection)
		{
			assertSame(elements[i++],
			           elt,
			           testName + "failed with unexpected elt at ["
			               + String.valueOf(i) + "]");
		}
	}

	/**
	 * Test method for {@link java.util.AbstractCollection#clear()}.
	 */
	@Test
	@DisplayName("void clear()")
	public final void testClear()
	{
		String testName = new String("void clear()");
		System.out.println(testName);
		boolean result;

		// Remplissage
		fillCollection(collection);

		// Non vide après remplissage
		result = collection.isEmpty();
		assertFalse(result, testName + " failed with empty collection");

		long startTime = System.nanoTime();
		collection.clear();
		double clearDuration = System.nanoTime() - startTime;

		// Vide après clear
		result = collection.isEmpty();
		assertTrue(result, testName + " failed with non empty collection");

		// Remplissage
		fillCollection(collection);

		/*
		 * Check clear time is much smaller than multiple remove times
		 */
		startTime = System.nanoTime();
		for (String s : collection)
		{
            collection.remove(s);
        }
		double removeDuration = System.nanoTime() - startTime;

//		System.err.println("clear duration = " + clearDuration);
//		System.err.println("All remove duration = " + removeDuration);
//		System.err.println("Factor = " + (removeDuration/clearDuration));

		double minTimeFactor = 2.5;
		double expectedMaxDuration = removeDuration / minTimeFactor;

		assertTrue(clearDuration < expectedMaxDuration,
		           testName + " failed with clear time too long :"
		               + clearDuration + " >= expected " + expectedMaxDuration);
	}

	/**
	 * Test method for
	 * {@link java.util.AbstractCollection#contains(java.lang.Object)}.
	 */
	@Test
	@DisplayName("boolean contains(Object o)")
	public final void testContains()
	{
		String testName = new String("boolean contains(Object o)");
		System.out.println(testName);
		boolean result;

		// Recherche contenu null sur une collection vide
		result = collection.contains(null);
		assertFalse(result, testName + " failed with empty collection containing null element");

		// Recherche contenu non null sur une collection vide
		result = collection.contains("Bonjour");
		assertFalse(result, testName + " failed with empty collection containing non present element");

		// Remplissage
		fillCollection(collection);

		// Contenu null non trouvé sur liste remplie
		result = collection.contains(null);
		assertFalse(result, testName + " failed with collection containing null element");

		// Recherche contenu non null non contenu sur une collection remplie
		result = collection.contains("Bonjour");
		assertFalse(result, testName + " failed with collection containing non present element");

		for (String elt : elements)
		{
			// Recherche contenu non null contenu dans collection remplie
			result = collection.contains(elt);
			assertTrue(result, testName + " failed with collection not containing expected elt");
		}
	}

	/**
	 * Test method for
	 * {@link java.util.AbstractCollection#containsAll(java.util.Collection)}.
	 */
	@Test
	@DisplayName("boolean containsAll(Collection<?> c)")
	public final void testContainsAll()
	{
		String testName = new String("boolean containsAll(Collection<?> c)");
		System.out.println(testName);
		boolean result;

		// Recherche contenu null sur une collection vide
		assertThrows(NullPointerException.class,
		             () -> {collection.containsAll(null);},
		             testName + " failed not throwing a NullPointerException");

		// Remplissage autre collection dans l'ordre direct
		ArrayList<String> forwardCollection = new ArrayList<String>();
		fillCollection(forwardCollection);

		// Ajout dans autre collection directe d'un elt supplémentaire
		ArrayList<String> forwardCollectionPlus = new ArrayList<String>(
				forwardCollection);
		forwardCollectionPlus.add(extraElement);

		// Recherche contenu non null sur une collection vide
		result = collection.containsAll(forwardCollection);
		assertFalse(result, testName + " failed with empty collection containing an non empy collection");

		// Remplissage autre collection dans l'ordre inverse
		ArrayList<String> reverseCollection = new ArrayList<String>();
		for (int i = elements.length - 1; i >= 0; i--)
		{
			reverseCollection.add(elements[i]);
		}
		// Ajout dans autre collection inverse d'un elt supplémentaire
		ArrayList<String> reverseCollectionPlus = new ArrayList<String>(
				reverseCollection);
		reverseCollectionPlus.add(extraElement);

		// Remplissage autre collection différente
		ArrayList<String> differentCollection = new ArrayList<String>();
		differentCollection.add("Bonjour");
		differentCollection.add("Brave");
		differentCollection.add("Nouveau");
		differentCollection.add("Monde");

		// Remplissage collection
		fillCollection(collection);

		CollectionList<String> collectionPlus = new CollectionList<String>(
				collection);
		collectionPlus.add(extraElement);

		// Contenu null non trouvé sur liste remplie
		assertThrows(NullPointerException.class,
		             () -> {collection.containsAll(null);},
		             testName + " failed with no NullPointerException thrown");

		// Recherche contenu non null non contenu sur une collection remplie
		result = collection.containsAll(differentCollection);
		assertFalse(result, testName + " failed with collection containing different collection");

		// Recherche contenu identique
		result = collection.containsAll(forwardCollection);
		assertTrue(result, testName + " failed with collection not containing identical collection");
		result = collection.containsAll(reverseCollection);
		assertTrue(result, testName + " failed with collection not containing reversed collection");

		// Recherche contenu plus petit
		result = collectionPlus.containsAll(forwardCollection);
		assertTrue(result, testName + " failed with collection not containing sub-collection");
		result = collectionPlus.containsAll(reverseCollection);
		assertTrue(result, testName + " failed with collection not containing reversed sub-collection");

		// Recherche contenu plus grand
		result = collection.containsAll(forwardCollectionPlus);
		assertFalse(result, testName + " failed with collection containing super collection");
		result = collection.containsAll(reverseCollectionPlus);
		assertFalse(result, testName + " failed with collection containing reversed super collection");
	}

	/**
	 * Test method for {@link java.util.AbstractCollection#isEmpty()}.
	 */
	@Test
	@DisplayName("boolean isEmpty()")
	public final void testIsEmpty()
	{
		String testName = new String("boolean isEmpty()");
		System.out.println(testName);
		boolean result = collection.isEmpty();

		assertTrue(result, testName + " failed with non empty collection");

		// Remplissage
		fillCollection(collection);

		result = collection.isEmpty();
		assertFalse(result, testName + " failed with empty collection");
	}

	/**
	 * Test method for {@link lists.CollectionList#iterator()}.
	 */
	@Test
	@DisplayName("Iterator<E> iterator()")
	public final void testIterator()
	{
		String testName = new String("Iterator<E> iterator()");
		System.out.println(testName);

		// Itérateur sur liste vide
		Iterator<String> result = collection.iterator();

		assertNotNull(result, testName + " failed with null iterator");
		assertFalse(result.hasNext(), testName + " failed with unfinished iterator");

		// Remplissage
		fillCollection(collection);

		// Itérateur sur liste non vide
		result = collection.iterator();
		assertNotNull(result, testName + " failed with null iterator");
		assertTrue(result.hasNext(), testName + " failed with finished iterator");

		for (int i = 0; i < elements.length; i++)
		{
			assertSame(elements[i],
			           result.next(),
			           testName + " failed with unexpected value at iteration["
			               + String.valueOf(i) + "]");
		}
		assertFalse(result.hasNext(), testName + " failed with unfinished iterator");
	}

	/**
	 * Test method for
	 * {@link java.util.AbstractCollection#remove(java.lang.Object)}.
	 */
	@Test
	@DisplayName("boolean remove(Object o)")
	public final void testRemove()
	{
		String testName = new String("boolean remove(Object o)");
		System.out.println(testName);

		// Retrait d'un élément null sur collection vide
		boolean result = collection.remove(null);
		assertFalse(result, testName + " failed with removed null elt on empty collection");

		// Retrait d'un élement non null sur collection vide
		result = collection.remove("Bonjour");
		assertFalse(result, testName + " failed with removed elt on empty collection");

		// Double Remplissage (pour vérifier l'ordre des retraits)
		fillCollection(collection);
		fillCollection(collection);
		// collection = Hello -> Brave -> New -> World -> Hello -> Brave -> New -> World

		// Retrait d'un élément null sur collection remplie
		result = collection.remove(null);
		assertFalse(result, testName + " failed with removed null elt on collection");

		for (String elt : elements)
		{
			// retrait de la première occurrence
			result = collection.remove(elt);
			// la seconde occurrence est toujours présence
			assertTrue(result, testName + " failed to remove first occurence of elt");
			assertTrue(collection.contains(elt),
			           testName + " failed to retain 2nd occurence of elt");

			// retrait de la seconde occurrence
			result = collection.remove(elt);
			assertTrue(result, testName + " failed to remove 2nd occurrence of elt");
			assertFalse(collection.contains(elt),
			            testName + " failed to remove 2nd occurrence of elt");

			// retrait elt non présent
			result = collection.remove(elt);
			assertFalse(result, testName + " failed with non present elt removed");
		}
	}

	/**
	 * Test method for
	 * {@link java.util.AbstractCollection#removeAll(java.util.Collection)}.
	 */
	@Test
	@DisplayName("boolean removeAll(Collection<?> c)")
	public final void testRemoveAll()
	{
		String testName = new String("boolean removeAll(Collection<?> c)");
		System.out.println(testName);
		boolean result;

		// Retrait collection nulle sur collection vide
		// Devrait générer un exception
		assertThrows(NullPointerException.class,
		             () -> {collection.removeAll(null);},
		             testName + " failed with no NullPointerException thrown");

		// Double Remplissage autre collection
		ArrayList<String> otherCollection = new ArrayList<String>();
		fillCollection(otherCollection);
		fillCollection(otherCollection);

		// Retrait othercollection sur collection vide
		result = collection.removeAll(otherCollection);
		assertFalse(result, testName + " failed with non present collection removed");

		// Remplissage collection
		fillCollection(collection);

		// Retrait collection nulle sur collection remplie
		assertThrows(NullPointerException.class,
		             () -> {collection.removeAll(null);},
		             testName + " failed with no NullPointerException thrown");

		// Retrait otherCollection de collection même taille
		result = collection.removeAll(otherCollection);
		assertTrue(result, testName + " failed to remove other collection");
		result = collection.isEmpty();
		assertTrue(result, testName + " failed with non empty collection after removal");

		// Re-remplissages
		otherCollection.clear();
		fillCollection(collection);
		fillCollection(otherCollection);

		CollectionList<String> collectionPlus =
		    new CollectionList<String>(collection);
		collectionPlus.add(extraElement);

		// Retrait collection plus grande
		result = collection.removeAll(collectionPlus);
		assertTrue(result, testName + " failed to remove super collection");
		assertTrue(collection.isEmpty(),
		           testName + " failed with non empty collection after removal of super collection");

		// Retrait collection plus petite
		result = collectionPlus.removeAll(otherCollection);
		assertTrue(result, testName + " failed to remove sub collection");
		assertEquals(1,
		             collectionPlus.size(),
		             testName + " failed with wrong size after removal of sub collection");
	}

	/**
	 * Test method for
	 * {@link java.util.AbstractCollection#retainAll(java.util.Collection)}.
	 */
	@Test
	@DisplayName("boolean retainAll(Collection<?> c)")
	public final void testRetainAll()
	{
		String testName = new String("boolean retainAll(Collection<?> c)");
		System.out.println(testName);
		boolean result;

		// Retain collection null sur collection vide
		// Devrait générer une exception
		assertThrows(NullPointerException.class,
		             () -> {collection.retainAll(null);},
		             testName + " failed with no NullPointerException thrown");

		// Remplissage otherCollection
		ArrayList<String> otherCollection = new ArrayList<String>();
		fillCollection(otherCollection);

		// Retain otherCollection sur collection vide
		result = collection.retainAll(otherCollection);
		assertFalse(result, testName + " failed with retained other collection on empty collection");

		// Remplissage collection
		collection.addAll(otherCollection);
		collection.add(extraElement);

		// Retain null collection sur collection remplie
		assertThrows(NullPointerException.class,
		             () -> {collection.retainAll(null);},
		             testName + " failed with no NullPointerException thrown");

		// Retain otherCollection sur collection remplie + extra element
		result = collection.retainAll(otherCollection);
		assertTrue(result, testName + " failed to retain super collection");
		assertEquals(otherCollection.size(),
		             collection.size(),
		             testName + " with different collections sizes after retaining other collection");
		Iterator<String> it1 = collection.iterator();
		Iterator<String> it2 = otherCollection.iterator();
		for (; it1.hasNext() && it2.hasNext();)
		{
			assertSame(it1.next(),
			           it2.next(),
			           testName + " failed with different elts after retaining other collection");
		}
	}

	/**
	 * Test method for {@link lists.CollectionList#size()}.
	 */
	@Test
	@DisplayName("int size()")
	public final void testSize()
	{
		String testName = new String("int size()");
		System.out.println(testName);
		int result;

		// Taille nulle sur collection vide
		result = collection.size();
		assertEquals(0, result, testName + " failed with non 0 size on empty collection");

		// Remplissage
		fillCollection(collection);

		// Taille  après remplissage
		result = collection.size();
		assertEquals(elements.length,
		             result,
		             testName + " failed with unexpected size after filled collection");
	}

	/**
	 * Test method for {@link java.util.AbstractCollection#toArray()}.
	 */
	@Test
	@DisplayName("Object[] toArray()")
	public final void testToArray()
	{
		String testName = new String("Object[] toArray()");
		System.out.println(testName);
		Object[] result;

		// toArray sur collection vide
		result = collection.toArray();
		assertEquals(0, result.length, testName + " failed with resulting non empty array on empty collection");

		// Remplissage
		fillCollection(collection);

		// toArray après remplissage
		result = collection.toArray();
		assertEquals(elements.length,
		             result.length,
		             testName + " failed with unexpected resulting array size");
		for (int i = 0; i < elements.length; i++)
		{
			assertSame(elements[i],
			           result[i],
			           testName + " failed with different element at ["
			               + String.valueOf(i) + "] on resulting array");
		}
	}

	/**
	 * Test method for {@link java.util.AbstractCollection#toArray(T[])}.
	 */
	@Test
	@DisplayName("T[] toArray(T[] a)")
	public final void testToArrayTArray()
	{
		String testName = new String("T[] toArray(T[] a)");
		System.out.println(testName);
		String[] result;

		// toArray sur collection vide
		result = collection.toArray(new String[0]);
		assertEquals(0, result.length, testName + " failed with non empty resulting array on empty collection");

		// Remplissage
		fillCollection(collection);

		// toArray après remplissage
		result = collection.toArray(new String[0]);
		assertEquals(elements.length,
		             result.length,
		             testName + " failed with unexpected resulting array size");
		for (int i = 0; i < elements.length; i++)
		{
			assertSame(elements[i],
			           result[i],
			           testName + " failed with different element at ["
			               + String.valueOf(i) + "] on resulting array");
		}
	}

	/**
	 * Test method for {@link java.util.AbstractCollection#toString()}.
	 */
	@Test
	@DisplayName("String toString()")
	public final void testToString()
	{
		String testName = new String("String toString()");
		System.out.println(testName);
		String result;

		// Remplissage
		fillCollection(collection);

		String expected = new String("[Hello, Brave, New, World]");

		result = collection.toString();

		assertEquals(expected, result, testName + " failed with unexpected resulting string");
	}

	/**
	 * Test method for {@link lists.CollectionList#equals(java.lang.Object)}.
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Test
	@DisplayName("boolean equals(Object obj)")
	public final void testEqualsObject()
	{
		String testName = new String("boolean equals(Object obj)");
		System.out.println(testName);
		boolean result;

		// Equals sur null
		result = collection.equals(null);
		assertFalse(result, testName + " failed with equality to null object");

		// Remplissage
		fillCollection(collection);

		// Equals sur this
		result = collection.equals(collection);
		assertTrue(result, testName + " failed with inequality to self");

		// Equals sur objet de nature différente
		result = collection.equals(new Object());
		assertFalse(result, testName + " failed with equality to new Object()");

		// Equals sur CollectionList non semblable
		CollectionList<String> otherCollectionList =
		    new CollectionList<String>(collection);
		collection.add(extraElement);
		result = collection.equals(otherCollectionList);
		assertFalse(result, testName + " failed with equality to different collection");

		// Equals sur CollectionList semblable
		otherCollectionList.add(extraElement);
		result = collection.equals(otherCollectionList);
		assertTrue(result, testName + " failed with inequality so identical collection");

		// Equals sur Collection non semblable
		collection.remove(extraElement);
		ArrayList<String> otherCollection = new ArrayList<String>(collection);
		collection.add(extraElement);
		result = collection.equals(otherCollection);
		assertFalse(result, testName + " failed with equality to different collection");

		// Equals sur Collection semblable
		// CollectionList<E> peut se comparer à toute Collection<E>
		otherCollection.add(extraElement);
		result = collection.equals(otherCollection);
		assertTrue(result, testName + " failed with inequality to indentical CollectionList");
		boolean resultInverse = otherCollection.equals(collection);
		assertFalse(resultInverse, testName + " failed with ArrayList equality to identical CollectionList");
	}

	/**
	 * Test method for {@link lists.CollectionList#hashCode()}.
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Test
	@DisplayName("int hashCode()")
	public final void testHashCode()
	{
		String testName = new String("int hashCode()");
		System.out.println(testName);
		int result1, result2;

		ArrayList<String> otherCollection = new ArrayList<String>();

		// hashCode collection vide = 1
		result1 = collection.hashCode();
		result2 = otherCollection.hashCode();
		assertEquals(1, result1, testName + " failed with non 1 hash on empty collection");
		assertEquals(result2, result1, testName + " failed with different hash on empty collections");

		// Remplissages
		fillCollection(collection);
		fillCollection(otherCollection);

		// hasCode collections semblables
		result1 = collection.hashCode();
		result2 = otherCollection.hashCode();
		assertEquals(result2,
		             result1,
		             testName + " failed with different hash on identical collections");

		// hasCode collections dissemblables
		collection.add(extraElement);
		result1 = collection.hashCode();
		assertTrue(result2 != result1, testName + " failed with identical has on different collections");

		// [Optionnel]
		// Les collections dissemblables ne sont plus égales
		assertFalse(collection.equals(otherCollection),
		            testName + " failed with equality on different collections");
	}
}
