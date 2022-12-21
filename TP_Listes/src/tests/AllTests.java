package tests;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;

/**
 * Suite de tests
 * @author davidroussel
 */
@RunWith(JUnitPlatform.class)
@SuiteDisplayName("All tests")
@SelectClasses({
	ForwardListTest.class,
	lists.ListIteratorTest.class,
	CollectionListTest.class
})
public class AllTests
{
	// Nothing
}
