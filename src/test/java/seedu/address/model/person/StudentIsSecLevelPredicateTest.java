package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class StudentIsSecLevelPredicateTest {
    @Test
    public void equals() {
        SecLevel firstSecLevel = new SecLevel("1");
        SecLevel secondSecLevel = new SecLevel("2");

        StudentIsSecLevelPredicate firstPredicate = new StudentIsSecLevelPredicate(firstSecLevel);
        StudentIsSecLevelPredicate secondPredicate = new StudentIsSecLevelPredicate(secondSecLevel);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StudentIsSecLevelPredicate firstPredicateCopy = new StudentIsSecLevelPredicate(firstSecLevel);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_studentIsSecLevel_returnsTrue() {
        StudentIsSecLevelPredicate predicate = new StudentIsSecLevelPredicate(new SecLevel("1"));
        assertTrue(predicate.test(new PersonBuilder().withSecLevel("1").build()));
    }

    @Test
    public void test_studentIsNotSecLevel_returnsFalse() {
        // Non-matching sec level
        StudentIsSecLevelPredicate predicate = new StudentIsSecLevelPredicate(new SecLevel("3"));
        assertFalse(predicate.test(new PersonBuilder().withSecLevel("2").build()));
    }

    @Test
    public void toStringMethod() {
        String secLevel = "3";
        StudentIsSecLevelPredicate predicate = new StudentIsSecLevelPredicate(new SecLevel(secLevel));

        String expected = StudentIsSecLevelPredicate.class.getCanonicalName() + "{secLevel=" + secLevel + "}";
        assertEquals(expected, predicate.toString());
    }
}


