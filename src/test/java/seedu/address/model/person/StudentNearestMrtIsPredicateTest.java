package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class StudentNearestMrtIsPredicateTest {
    @Test
    public void equals() {
        MrtStation firstMrtStation = new MrtStation("Bukit Batok");
        MrtStation secondMrtStation = new MrtStation("Bukit Panjang");

        StudentNearestMrtIsPredicate firstPredicate = new StudentNearestMrtIsPredicate(firstMrtStation);
        StudentNearestMrtIsPredicate secondPredicate = new StudentNearestMrtIsPredicate(secondMrtStation);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StudentNearestMrtIsPredicate firstPredicateCopy = new StudentNearestMrtIsPredicate(firstMrtStation);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_studentNearestMrtIs_returnsTrue() {
        StudentNearestMrtIsPredicate predicate = new StudentNearestMrtIsPredicate(new MrtStation("Boon Lay"));
        assertTrue(predicate.test(new PersonBuilder().withNearestMrtStation("Boon Lay").build()));
    }

    @Test
    public void test_studentNearestMrtIsNot_returnsFalse() {
        // Non-matching MRT Station
        StudentNearestMrtIsPredicate predicate = new StudentNearestMrtIsPredicate(new MrtStation("Kallang"));
        assertFalse(predicate.test(new PersonBuilder().withNearestMrtStation("Expo").build()));
    }

    @Test
    public void toStringMethod() {
        String mrtStation = "Tampines";
        StudentNearestMrtIsPredicate predicate = new StudentNearestMrtIsPredicate(new MrtStation(mrtStation));

        String expected = StudentNearestMrtIsPredicate.class.getCanonicalName() + "{mrtStation=" + mrtStation + "}";
        assertEquals(expected, predicate.toString());
    }
}



