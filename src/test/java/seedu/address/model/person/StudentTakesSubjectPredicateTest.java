package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Subject;
import seedu.address.testutil.PersonBuilder;

public class StudentTakesSubjectPredicateTest {
    @Test
    public void equals() {
        Subject firstSubject = new Subject("Chemistry");
        Subject secondSubject = new Subject("Biology");

        StudentTakesSubjectPredicate firstPredicate = new StudentTakesSubjectPredicate(firstSubject);
        StudentTakesSubjectPredicate secondPredicate = new StudentTakesSubjectPredicate(secondSubject);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StudentTakesSubjectPredicate firstPredicateCopy = new StudentTakesSubjectPredicate(firstSubject);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_studentTakesSubject_returnsTrue() {
        StudentTakesSubjectPredicate predicate = new StudentTakesSubjectPredicate(new Subject("English"));
        assertTrue(predicate.test(new PersonBuilder().withSubjects("English").build()));
        assertTrue(predicate.test(new PersonBuilder().withSubjects("English", "Chinese").build()));
    }

    @Test
    public void test_studentDoesNotTakeSubject_returnsFalse() {
        // Non-matching subject
        StudentTakesSubjectPredicate predicate = new StudentTakesSubjectPredicate(new Subject("Mathematics"));
        assertFalse(predicate.test(new PersonBuilder().withSubjects("English", "Chinese").build()));
    }

    @Test
    public void toStringMethod() {
        Subject subject = new Subject("Geography");
        StudentTakesSubjectPredicate predicate = new StudentTakesSubjectPredicate(subject);

        String expected = StudentTakesSubjectPredicate.class.getCanonicalName() + "{subject=" + subject + "}";
        assertEquals(expected, predicate.toString());
    }
}


