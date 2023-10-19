package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class StudentHasPhonePredicateTest {
    @Test
    public void equals() {
        Phone firstPhone = new Phone("98765432");
        Phone secondPhone = new Phone("97531357");

        StudentHasPhonePredicate firstPredicate = new StudentHasPhonePredicate(firstPhone);
        StudentHasPhonePredicate secondPredicate = new StudentHasPhonePredicate(secondPhone);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StudentHasPhonePredicate firstPredicateCopy = new StudentHasPhonePredicate(firstPhone);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_studentHasPhone_returnsTrue() {
        StudentHasPhonePredicate predicate = new StudentHasPhonePredicate(new Phone("24682468"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("24682468").build()));
    }

    @Test
    public void test_studentDoesNotHavePhone_returnsFalse() {
        // Non-matching phone
        StudentHasPhonePredicate predicate = new StudentHasPhonePredicate(new Phone("13573579"));
        assertFalse(predicate.test(new PersonBuilder().withPhone("38692736").build()));
    }

    @Test
    public void toStringMethod() {
        String phone = "82957263";
        StudentHasPhonePredicate predicate = new StudentHasPhonePredicate(new Phone(phone));

        String expected = StudentHasPhonePredicate.class.getCanonicalName() + "{phone=" + phone + "}";
        assertEquals(expected, predicate.toString());
    }
}

