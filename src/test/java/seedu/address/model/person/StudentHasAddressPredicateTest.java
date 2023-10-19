package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class StudentHasAddressPredicateTest {
    @Test
    public void equals() {
        Address firstAddress = new Address("ABC Street");
        Address secondAddress = new Address("XYZ Street");

        StudentHasAddressPredicate firstPredicate = new StudentHasAddressPredicate(firstAddress);
        StudentHasAddressPredicate secondPredicate = new StudentHasAddressPredicate(secondAddress);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StudentHasAddressPredicate firstPredicateCopy = new StudentHasAddressPredicate(firstAddress);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_studentHasAddress_returnsTrue() {
        StudentHasAddressPredicate predicate = new StudentHasAddressPredicate(new Address("Hoshimachi Street"));
        assertTrue(predicate.test(new PersonBuilder().withAddress("Hoshimachi Street").build()));
    }

    @Test
    public void test_studentDoesNotHaveAddress_returnsFalse() {
        // Non-matching address
        StudentHasAddressPredicate predicate = new StudentHasAddressPredicate(new Address("Tsunomaki Lane"));
        assertFalse(predicate.test(new PersonBuilder().withAddress("Kiryu Avenue").build()));
    }

    @Test
    public void toStringMethod() {
        String address = "Address Road";
        StudentHasAddressPredicate predicate = new StudentHasAddressPredicate(new Address(address));

        String expected = StudentHasAddressPredicate.class.getCanonicalName() + "{address=" + address + "}";
        assertEquals(expected, predicate.toString());
    }
}
