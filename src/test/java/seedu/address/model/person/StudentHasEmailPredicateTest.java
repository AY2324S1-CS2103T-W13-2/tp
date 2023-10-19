package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class StudentHasEmailPredicateTest {
    @Test
    public void equals() {
        Email firstEmail = new Email("inanis@ao.com");
        Email secondEmail = new Email("kronii@time.com");

        StudentHasEmailPredicate firstPredicate = new StudentHasEmailPredicate(firstEmail);
        StudentHasEmailPredicate secondPredicate = new StudentHasEmailPredicate(secondEmail);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StudentHasEmailPredicate firstPredicateCopy = new StudentHasEmailPredicate(firstEmail);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_studentHasEmail_returnsTrue() {
        StudentHasEmailPredicate predicate = new StudentHasEmailPredicate(new Email("suichan@cute.com"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("suichan@cute.com").build()));
    }

    @Test
    public void test_studentDoesNotHaveEmail_returnsFalse() {
        // Non-matching email
        StudentHasEmailPredicate predicate = new StudentHasEmailPredicate(new Email("watame@warukunai.org"));
        assertFalse(predicate.test(new PersonBuilder().withEmail("asa@coco.com").build()));
    }

    @Test
    public void toStringMethod() {
        String email = "email@gmail.com";
        StudentHasEmailPredicate predicate = new StudentHasEmailPredicate(new Email(email));

        String expected = StudentHasEmailPredicate.class.getCanonicalName() + "{email=" + email + "}";
        assertEquals(expected, predicate.toString());
    }
}
