package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lists.ForwardList;

/**
 * Classe de test de la liste Chainée
 * @author davidroussel
 */
public class ForwardListTest
{
	/**
	 * La liste à tester.
	 * La nature du contenu de la liste importe peu du moment qu'il est
	 * homogène : donc n'importe quel type ferait l'affaire.
	 */
	private ForwardList<String> liste = null;

	/**
	 * Liste des éléments à insérer dans la liste
	 */
	private static String[] elements;

	/**
	 * Mise en place avant l'ensemble des tests
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	public static void setUpBeforeClass() throws Exception
	{
		System.out.println("-------------------------------------------------");
		System.out.println("Test ForwardList");
		System.out.println("-------------------------------------------------");
	}

	/**
	 * Nettoyage après l'ensemble des tests
	 * @throws java.lang.Exception
	 */
	@AfterAll
	public static void tearDownAfterClass() throws Exception
	{
		System.out.println("-------------------------------------------------");
		System.out.println("Fin Test ForwardList");
		System.out.println("-------------------------------------------------");
	}

	/**
	 * Mise en place avant chaque test
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception
	{
		elements = new String[] {
					"Hello",
					"Brave",
					"New",
					"World"
				};
		liste = new ForwardList<String>();
	}

	/**
	 * Nettoyage après chaque test
	 * @throws java.lang.Exception
	 */
	@AfterEach
	public void tearDown() throws Exception
	{
		liste.clear();
		liste = null;
	}

	/**
	 * Méthode utilitaire de remplissage de la liste avec les éléments
	 * du tableau #elements
	 */
	private final void fillListWithElements()
	{
		if (liste != null)
		{
			for (String elt : elements)
			{
				liste.add(elt);
			}
		}
	}

	/**
	 * Test method for {@link lists.ForwardList#ForwardList()}.
	 */
	@Test
	@DisplayName("ForwardList()")
	public final void testForwardList()
	{
		String testName = new String("ForwardList()");
		System.out.println(testName);

		assertNotNull(liste, testName + " failed with null instance");
		assertTrue(liste.empty(), testName + " failed with non empty list");
	}

	/**
	 * Test method for {@link lists.ForwardList#ForwardList(lists.ForwardList)}.
	 */
	@Test
	@DisplayName("ForwardList(ForwardList<E> liste)")
	public final void testForwardListForwardListOfT()
	{
		String testName = new String("ForwardList(ForwardList<E> liste)");
		System.out.println(testName);

		ForwardList<String> liste2 = new ForwardList<String>();
		liste = new ForwardList<String>(liste2);

		assertNotNull(liste, testName + " failed with null instance");
		assertTrue(liste.empty(), testName + " failed with non empty list");

		fillListWithElements();
		assertFalse(liste.empty(), testName + " failed with empty list");
		liste2 = new ForwardList<String>(liste);
		assertNotNull(liste2, testName + " failed with null instance");
		assertEquals(liste, liste2, testName + " failed with different contents");
	}

	/**
	 * Test method for {@link lists.ForwardList#add(java.lang.Object)}.
	 */
	@Test
	@DisplayName("void add(E e)")
	public final void testAdd()
	{
		String testName = new String("void add(E e)");
		System.out.println(testName);

		// Ajout dans une liste vide
		liste.add(elements[0]);
		assertFalse(liste.empty(), testName + " failed with empty list");
		Iterator<String> it = liste.iterator();
		assertNotNull(it, testName + " failed with null iterator");
		String insertedElt = it.next();
		assertSame(elements[0],
		           insertedElt,
		           testName + " failed with inserted element !=" + elements[0]);
		// Si assertSame réussit asserEquals n'est plus nécessaire

		// Ajout dans une liste non vide
		for (int i=1; i < elements.length; i++)
		{
			liste.add(elements[i]);
			/*
			 * Attention le précédent "it" a été invalidé par l'ajout
			 * Lors du dernier next le current de l'itérateur est passé à null
			 * puisqu'il n'y avait pas (encore) de suivant, donc retenter un
			 * next sur le même itérateur génèrera un NoSuchelementException.
			 * Il faut donc réobtenir un itérateur pour parcourir la liste
			 * après un ajout
			 */
			it = liste.iterator();
			for (int j = 0; j <= i; j++)
			{
				insertedElt = it.next();
			}
			assertSame(elements[i],
			           insertedElt,
			           testName + " failed with inserted element not same as "
			               + elements[i]);
		}
	}

	/**
	 * Test method for {@link lists.ForwardList#add(java.lang.Object)}.
	 */
	@Test
	@DisplayName("void add(null)")
	public final void testAddNull()
	{
		String testName = new String("void add(null)");
		System.out.println(testName);

		liste.add(elements[0]);

		assertFalse(liste.empty(), testName + " failed adding " + elements[0]);

		// Ajout null dans une liste non vide (sinon on fait un insere(null))
		// Doit lever une NullPointerException
		assertThrows(NullPointerException.class,
		             () -> {liste.add(null);},
		             testName + " failed with no NullPointerException thrown "
		             	+ "when adding null elt");
	}

	/**
	 * Test method for {@link lists.ForwardList#insert(java.lang.Object)}.
	 */
	@Test
	@DisplayName("void insert(E e)")
	public final void testInsert()
	{
		String testName = new String("void insert(E e)");
		System.out.println(testName);

		// Insertion elt null
		assertThrows(NullPointerException.class,
		             () -> {liste.insert(null);},
		            testName + " failed with insert(null) not throwing NullPointerException");

		// Insertion dans une liste vide
		int lastIndex = elements.length - 1;
		try
		{
			liste.insert(elements[lastIndex]);
		}
		catch (NullPointerException e)
		{
			fail(testName + " failed with unnecessary NullPointerException");
		}

		assertFalse(liste.empty(), testName + "failed with empty list");
		Iterator<String> it = liste.iterator();
		String insertedElt = it.next();
		assertSame(elements[lastIndex],
		           insertedElt,
		           testName + " failed with inserted element not same as "
		               + elements[lastIndex]);
		// Si assertSame réussit asserEquals n'est plus nécessaire

		// Ajout dans une liste non vide
		for (int i=1; i < elements.length; i++)
		{
			liste.insert(elements[lastIndex - i]);

			insertedElt = liste.iterator().next();
			assertSame(elements[lastIndex - i],
			           insertedElt,
			           testName + " failed with inserted element not same as "
			               + elements[lastIndex - i]);
		}
	}

	/**
	 * Test method for {@link lists.ForwardList#insert(java.lang.Object)}.
	 */
	@Test
	@DisplayName("void insert(null)")
	public final void testInsertNull()
	{
		String testName = new String("void insert(null)");
		System.out.println(testName);

		// Insertion dans une liste vide
		// Doit soulever une NullPointerException
		assertThrows(NullPointerException.class,
		             () -> {liste.insert(null);},
		             testName + " failed with no NullPointerException thrown "
		             	+ "when inserting null elt");

		fillListWithElements();

		assertEquals(elements.length,
		             liste.size(),
		             testName + " failed with wrong list size");

		// Insertion dans une liste non vide
		// Doit soulever une NullPointerException
		assertThrows(NullPointerException.class,
		             () -> {liste.insert(null);},
		             testName + " failed with no NullPointerException thrown "
		             	+ "when inserting null elt");

	}

	/**
	 * Test method for {@link lists.ForwardList#insertAt(java.lang.Object, int)}.
	 */
	@Test
	@DisplayName("boolean insertAt(E e, int index)")
	public final void testInsertAt()
	{
		String testName = new String("boolean insert(E e, int index)");
		System.out.println(testName);

		int[] nextIndex = new int[] {1, 0, 3, 2};
		int index = 0;

		// - insertion d'un élément null
		boolean result = liste.insertAt(null, 0);
		assertFalse(result, testName + " failed with null element inserted");
		assertTrue(liste.empty(),
		           testName
		               + " failed with non empty list after null elt insertion");

		// - insertion dans une liste vide avec un index invalide
		result = liste.insertAt(elements[nextIndex[index]], 1);
		assertFalse(result,
		            testName + " failed with elt inserted at invalid index");
		assertTrue(liste.empty(),
		           testName + " failed with non empty list after invalid index insertion");

		// + insertion dans une liste vide avec un index valide
		result = liste.insertAt(elements[nextIndex[index]], 0);
		// liste = Brave ->
		assertTrue(result, testName + " failed to insert element");
		assertFalse(liste.empty(),
		            testName + " failed with empty list after element insertion");
		index++;

		// - insertion dans une liste non vide avec un index invalide
		result = liste.insertAt(elements[nextIndex[index]], 5);
		assertFalse(result,
		            testName
		                + " insertion ds liste non vide, index invalide failed");

		// + insertion en début de liste non vide avec un index valide
		result = liste.insertAt(elements[nextIndex[index]], 0);
		// liste = Hello -> Brave ->
		assertTrue(result,
		           testName + " failed to insert elt at list's head");
		index++;

		// + insertion en fin de liste non vide avec un index valide
		result = liste.insertAt(elements[nextIndex[index]], 2);
		// liste = Hello -> Brave -> World
		assertTrue(result,
		           testName+ " failed to insert elt at list's end");
		index++;

		// + insertion en milieu de liste non vide avec un index valide
		result = liste.insertAt(elements[nextIndex[index]], 2);
		// liste = Hello -> Brave -> New -> World
		assertTrue(result,
		           testName + " failed to insert elt at list's middle");
	}

	/**
	 * Test method for {@link lists.ForwardList#remove(java.lang.Object)}.
	 */
	@Test
	@DisplayName("boolean remove(E elt)")
	public final void testRemove()
	{
		String testName = new String("boolean remove(E elt)");
		System.out.println(testName);

		// suppression d'un élément non null d'une liste vide
		boolean result = liste.remove(elements[0]);
		assertFalse(result, testName + " failed with non present element removed");
		assertTrue(liste.empty(), testName + " failed with non empty list");

		// suppresion d'un élement null d'une liste vide
		result = liste.remove(null);
		assertFalse(result, testName + " failed with null element removed");
		assertTrue(liste.empty(), testName + " failed with non empty list");

		fillListWithElements();
		liste.add("Hello"); // "Hello" not same as elements[0]
		// liste = Hello -> Brave -> New -> World -> Hello

		// suppression d'un élément null d'une liste non vide
		result = liste.remove(null);
		assertFalse(result, testName + " failed with null elt removed");

		// suppression d'un élément inexistant d'une liste non vide
		result = liste.remove("Coucou");
		assertFalse(result, testName + " failed with non present elt removed");

		// suppression d'un élement existant en début de liste
		result = liste.remove("Hello");
		// liste = Brave -> New -> World -> Hello
		assertTrue(result, testName + " failed to remove present elt");
		String nextElt = liste.iterator().next();
		assertSame(elements[1], nextElt, testName + " failed with wrong next element");

		// suppresion d'un élement existant en fin de liste
		result = liste.remove("Hello");
		// liste = Brave -> New -> World
		assertTrue(result, testName + " failed to remove \"Hello\"");
		Iterator<String> it = liste.iterator();
		it.next(); // Brave
		it.next(); // New
		String lastElt = it.next(); // World
		assertSame(elements[3], lastElt, testName + " failed with wrong last elt");

		// suppression d'un élement existant en milieu de liste
		result = liste.remove(elements[2]);
		// liste = Brave -> World
		assertTrue(result, testName + " failed to remove middle elt");
		it = liste.iterator();
		String firstElt = it.next(); // Brave
		lastElt = it.next(); // World
		assertSame(elements[1], firstElt, testName + " failed with wrong first elt");
		assertSame(elements[3], lastElt, testName + " failed with wrong last elt");
	}

	/**
	 * Test method for {@link lists.ForwardList#removeAll(java.lang.Object)}.
	 */
	@Test
	@DisplayName("boolean removeAll(E elt)")
	public final void testRemoveAll()
	{
		String testName = new String("boolean removeAll(E elt)");
		System.out.println(testName);

		// suppression d'un élément non null d'une liste vide
		boolean result = liste.removeAll(elements[0]);
		assertFalse(result, testName + " failed with all elt removed from emtpy list");
		assertTrue(liste.empty(), testName + " failed with non empty list");

		// suppresion d'un élement null d'une liste vide
		result = liste.removeAll(null);
		assertFalse(result, testName + " failed with all null elts removed from empty list");
		assertTrue(liste.empty(), testName + " failed with non empty list");

		elements[2] = new String("Hello");
		fillListWithElements();
		liste.add("Hello"); // "Hello" not same as elements[0]
		// liste = Hello -> Brave -> Hello -> World -> Hello

		// suppression d'un élément null d'une liste non vide
		result = liste.removeAll(null);
		assertFalse(result, testName + " failed with all null elts removed");

		// suppression d'un element existant au début, au milieu et à la fin
		result = liste.removeAll("Hello");
		// liste = Brave -> World
		assertTrue(result, testName + " failed with all \"Hello\" elts not removed");
		Iterator<String> it = liste.iterator();
		String firstElt = it.next();
		String lastElt = it.next();
		assertFalse(it.hasNext(),
		            testName
		                + " with wrong nb of elts after all \"Hello\" elts removed");
		assertSame(elements[1],
		           firstElt,
		           testName
		               + " failed with wrong secd elt after all \"Hello\" removed");
		assertSame(elements[3],
		           lastElt,
		           testName
		               + " failed with wrong 1st last after all \"Hello\" removed");
	}

	/**
	 * Test method for {@link lists.ForwardList#size()}.
	 */
	@Test
	@DisplayName("int size()")
	public final void testSize()
	{
		String testName = new String("int size()");
		System.out.println(testName);

		// taille d'une liste vide
		assertEquals(0, liste.size(), testName + " failed with non 0 size");

		fillListWithElements();
		assertFalse(liste.empty(), testName + " failed with empty list");

		// taille d'une liste non vide
		assertEquals(elements.length,
		             liste.size(),
		             testName + " failed with different sizes");
	}

	/**
	 * Test method for {@link lists.ForwardList#clear()}.
	 */
	@Test
	@DisplayName("void clear()")
	public final void testClear()
	{
		String testName = new String("void clear()");
		System.out.println(testName);

		// effacement d'une liste vide
		liste.clear();
		assertTrue(liste.empty(), testName + " failed with non empty list");

		fillListWithElements();
		assertFalse(liste.empty(), testName + " failed with empty list");

		// effacement d'une liste non vide
		liste.clear();
		assertTrue(liste.empty(), testName + " failed with non empty list");
	}

	/**
	 * Test method for {@link lists.ForwardList#empty()}.
	 */
	@Test
	@DisplayName("boolean empty()")
	public final void testEmpty()
	{
		String testName = new String("boolean empty()");
		System.out.println(testName);

		double startTime = System.nanoTime();
		boolean isEmpty = liste.empty();
		double emptyDuration = System.nanoTime() - startTime;

		assertTrue(isEmpty, testName + " failed with non empty list");

		startTime = System.nanoTime();
		boolean isZeroSize = liste.size() == 0;
		double zeroSizeDuration = System.nanoTime() - startTime;
		double minTimeFactor = 6.5;
		double expectedMaxDuration = zeroSizeDuration / minTimeFactor;

//		System.err.println("empty duration = " + emptyDuration);
//		System.err.println("zeroSize duration = " + zeroSizeDuration);
//		System.err.println("Time factor = " + (zeroSizeDuration / emptyDuration));

		assertTrue(isZeroSize, testName + " failed with non empty list");
		assertTrue(emptyDuration < expectedMaxDuration,
		           testName + " failed with empty duration too long : "
		               + emptyDuration + " >= expected " + expectedMaxDuration);

		fillListWithElements();

		assertFalse(liste.empty(), testName + " failed with empty list");
	}

	/**
	 * Test method for {@link lists.ForwardList#equals(java.lang.Object)}.
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Test
	@DisplayName("boolean equals(Object o)")
	public final void testEqualsObject()
	{
		String testName = new String("boolean equals(Object o)");
		System.out.println(testName);
		long tic, toc;

		fillListWithElements();

		// Inegalite sur objet null
		boolean result  = liste.equals(null);
		assertFalse(result, testName + " failed with equals to null object");

		// Egalite sur soi-même
		tic = System.nanoTime();
		result = liste.equals(liste);
		toc = System.nanoTime();
		long compareSelfTime = toc - tic;

		// Egalite sur liste copiée
		ForwardList<String> liste2 = new ForwardList<String>(liste);
		tic = System.nanoTime();
		boolean resultb = liste.equals(liste2);
		toc = System.nanoTime();
		long compareOtherTime = toc - tic;

		assertTrue(result, testName + " failed with non equals to self");
		assertTrue(resultb, testName + " failed with non equals to self copy");
		/*
		 * DONE Check that equality test to self takes signifivatively less
		 * time (5x) than comparing with another list even with same content.
		 * ==> Ensure obj == this is used in equals
		 */
		long timeout = (compareOtherTime / 5);
		assertTrue(compareSelfTime < timeout,
		           testName + " failed with self comparison taking too much "
		           	+ "time : " + compareSelfTime + ">= expected "  + timeout);

		// Inegalité sur listes de tailles différentes
		liste2.add("of Pain");
		result = liste.equals(liste2);
		assertFalse(result, testName + " failed with equals to different list");

		// Inegalite sur liste à contenu dans une autre ordre
		liste2.clear();
		for (String elt : elements)
		{
			liste2.insert(elt);
		}
		result = liste.equals(liste2);
		assertFalse(result, testName + " failed with equals to reverse list");

		// Egalite avec une collection standard de même contenu
		// SSI equals compare un Iterable plutôt qu'une Liste
		List<String> alist = new ArrayList<String>();
		for (String elt : elements)
		{
			alist.add(elt);
		}
		assertTrue(liste.equals(alist),
		           testName + " failed with non equals to another Iterable "
		           	+ "with same content");
	}

	/**
	 * Test method for {@link lists.ForwardList#toString()}.
	 */
	@Test
	@DisplayName("String toString()")
	public final void testToString()
	{
		String testName = new String("String toString()");
		System.out.println(testName);

		fillListWithElements();

		assertEquals("[Hello->Brave->New->World]",
		             liste.toString(),
		             testName + " failed with content not equals to [Hello->Brave->New->World]");
	}

	/**
	 * Test method for {@link lists.ForwardList#iterator()}.
	 */
	@Test
	@DisplayName("Iterator<E> iterator()")
	public final void testIterator()
	{
		String testName = new String("Iterator<E> iterator()");
		System.out.println(testName);

		Iterator<String> it = liste.iterator();
		assertNotNull(it, testName + " failed with null iterator");
		assertFalse(it.hasNext(),
		            testName + " failed with elts to iterate on empty list");

		fillListWithElements();

		it = liste.iterator();
		assertTrue(it.hasNext(),
		           testName
		               + " failed with no elts to iterate on non empty list");

		int i = 0;
		while (it.hasNext())
		{
			String nextElt = it.next();
			assertNotNull(nextElt, testName + " failed with next null elt");
			assertSame(elements[i++],
			           nextElt,
			           testName + " failed with unexpected next elt");
			it.remove(); // ne doit pas invalider l'itérateur
		}

		assertFalse(it.hasNext(), testName + " failed with more elts to iterate");

		// Un appel supplémentaire à next sur un itérateur terminé
		// doit soulever une NoSuchElementException
		assertThrows(NoSuchElementException.class,
		             it::next, // Method reference
		             testName + " failed with next() call not throwing "
		             	+ "NoSuchElementException");
	}

	/**
	 * Test method for {@link lists.ForwardList#hashCode()}.
	 */
	@Test
	@DisplayName("int hashCode()")
	public final void testHashCode()
	{
		String testName = new String("int hashCode()");
		System.out.println(testName);

		// hashcode d'une liste vide = 1
		int listeHash = liste.hashCode();
		assertEquals(1, listeHash, testName + " failed with wrong hash value");

		fillListWithElements();

		// hashcode de la liste standard
		listeHash = liste.hashCode();
		assertEquals(1161611233,
		             listeHash,
		             testName + " failed with wrong hash value");

		/*
		 * Contrat hashCode : Si a.equals(b) alors a.hashcode() == b.hashcode()
		 */
		ForwardList<String> liste2 = new ForwardList<String>(liste);
		assertNotSame(liste, liste2, testName + " failed with same lists");
		assertEquals(liste, liste2, testName + " failed with not equals lists");
		assertEquals(liste.hashCode(),
		             liste2.hashCode(),
		             testName
		                 + " failed with identical lists but different hash codes");

		liste2.add("Hourra");
		assertFalse(liste.equals(liste2), testName + " failed with different lists equality");
		assertNotEquals(liste.hashCode(),
		                liste2.hashCode(),
		                testName + " failed with identical has on different lists");

		// HashCode similaire à celui d'une collection standard
		ArrayList<String> collection = new ArrayList<String>();
		for (String elt : elements)
		{
			collection.add(elt);
		}
		int collectionHash = collection.hashCode();
		assertEquals(listeHash, collectionHash, testName + " failed with different has codes");
	}
}
