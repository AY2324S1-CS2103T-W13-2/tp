package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class StudentIsGenderPredicateTest {
    @Test
    public void equals() {
        Gender firstGender = new Gender("F");
        Gender secondGender = new Gender("M");

        StudentIsGenderPredicate firstPredicate = new StudentIsGenderPredicate(firstGender);
        StudentIsGenderPredicate secondPredicate = new StudentIsGenderPredicate(secondGender);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StudentIsGenderPredicate firstPredicateCopy = new StudentIsGenderPredicate(firstGender);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_studentIsGender_returnsTrue() {
        StudentIsGenderPredicate predicate = new StudentIsGenderPredicate(new Gender("F"));
        assertTrue(predicate.test(new PersonBuilder().withGender("F").build()));
    }

    @Test
    public void test_studentIsNotGender_returnsFalse() {
        // Non-matching gender
        StudentIsGenderPredicate predicate = new StudentIsGenderPredicate(new Gender("F"));
        assertFalse(predicate.test(new PersonBuilder().withGender("M").build()));
    }

    @Test
    public void toStringMethod() {
        String gender = "M";
        StudentIsGenderPredicate predicate = new StudentIsGenderPredicate(new Gender(gender));

        String expected = StudentIsGenderPredicate.class.getCanonicalName() + "{gender=" + gender + "}";
        assertEquals(expected, predicate.toString());
    }
}

