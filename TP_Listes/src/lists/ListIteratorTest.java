package lists;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Classe pour tester l'itérateur de {@link ForwardList}
 * @author davidroussel
 * @apiNote Cette classe a besoin d'être dans le même package de la {@link ForwardList}
 * pour pouvoir accéder à la classe interne {@link ForwardList.ListIterator}
 */
public class ListIteratorTest
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
	static void setUpBeforeClass() throws Exception
	{
		System.out.println("-------------------------------------------------");
		System.out.println("Test ForwardList.ListIterator");
		System.out.println("-------------------------------------------------");
	}

	/**
	 * Nettoyage après l'ensemble des tests
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception
	{
		System.out.println("-------------------------------------------------");
		System.out.println("Fin Test ForwardList.ListIterator");
		System.out.println("-------------------------------------------------");

	}

	/**
	 * Mise en place avant chaque test
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception
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
	void tearDown() throws Exception
	{
		/*
		 * TODO check the iterator finalizer didn't empty the list
		 */
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
	 * Test method for {@link lists.ForwardList.ListIterator#ListIterator()}
	 */
	@Test
	@DisplayName("ListIterator()")
	final void testListIterator()
	{
		String testName = new String("ListIterator()");
		System.out.println(testName);

		Iterator<String> it = liste.iterator();
		assertNotNull(it, testName +  " failed with null iterator");
	}

	/**
	 * Test method for {@link lists.ForwardList.ListIterator#hasNext()}
	 */
	@Test
	@DisplayName("boolean hasNext()")
	final void testHasNext()
	{
		String testName = new String("boolean hasNext()");
		System.out.println(testName);

		Iterator<String> it = liste.iterator();
		assertNotNull(it, testName +  " failed with null iterator");
		assertFalse(it.hasNext(), testName + " failed with unfinished iterator over empty list");

		fillListWithElements();

		it = liste.iterator();
		assertNotNull(it, testName +  " failed with null iterator");
		assertTrue(it.hasNext(), testName + " failed with finished iterator over list");
		for (int i = 0; i < elements.length; i++)
		{
			assertTrue(it.hasNext(),
			           testName + " failed with finished iterator over list");
			it.next();
		}

		assertFalse(it.hasNext(), testName + " failed with unfinished iterator over list");
	}

	/**
	 * Test method for {@link lists.ForwardList.ListIterator#next()}
	 */
	@Test
	@DisplayName("F next()")
	final void testNext()
	{
		String testName = new String("F next()");
		System.out.println(testName);

		Iterator<String> it = liste.iterator();
		assertNotNull(it, testName +  " failed with null iterator");
		assertFalse(it.hasNext(), testName + " failed with unfinished iterator over empty list");

		fillListWithElements();

		it = liste.iterator();
		assertNotNull(it, testName +  " failed with null iterator");
		for (int i = 0; i < elements.length; i++)
		{
			String elt = it.next();
			assertEquals(elements[i],
			             elt,
			             testName + " failed with unexpected iterated value");
		}

		assertThrows(NoSuchElementException.class,
		             it::next,
		             testName + " failed with next over last element not throwing NoSuchElementException");
	}

	/**
	 * Test method for {@link lists.ForwardList.ListIterator#remove()}
	 */
	@Test
	@DisplayName("void remove()")
	final void testRemove()
	{
		String testName = new String("void remove()");
		System.out.println(testName);

		Iterator<String> it = liste.iterator();
		assertNotNull(it, testName +  " failed with null iterator");

		fillListWithElements();

		it = liste.iterator();
		assertNotNull(it, testName +  " failed with null iterator");
		for (int i = 0; i < elements.length; i++)
		{
			int currentSize = liste.size();
			it.next();
			it.remove();
			assertEquals(currentSize - 1, liste.size(),
			             testName + " failed with wrong list size");
			assertThrows(IllegalStateException.class,
			             it::remove,
			             testName + " failed with remove called twice");
		}
	}


	/**
	 * Test method for {@link lists.ForwardList.ListIterator#finalize()}
	 * To ensure finalizing an iterator does not clears the list
	 */
	@Test
	@DisplayName("void finalize()")
	final void testFinalize()
	{
		String testName = new String("void finalize()");
		System.out.println(testName);

		{
			Iterator<String> it = liste.iterator();
			assertNotNull(it, testName +  " failed with null iterator");

			fillListWithElements();

			it = liste.iterator();
			assertNotNull(it, testName +  " failed with null iterator");
			for (int i = 0; i < (elements.length-1); i++)
			{
				assertTrue(it.hasNext(), testName + " failed with finished iterator");
				assertEquals(elements[i],
				             it.next(),
				             testName + " failed with wrong iterated value");
			}

			it = null;
		}
		assertDoesNotThrow(() -> {
			System.gc();
		}, testName + " failed with garbage collectior throwing");
		assertFalse(liste.empty(),
		            testName + " failed with empty list after iterator gc");
	}

}
