package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Subject;

/**
 * Tests that a {@code Student} takes the given subject.
 */
public class StudentTakesSubjectPredicate implements Predicate<Student> {
    private final Subject subject;

    public StudentTakesSubjectPredicate(Subject subject) {
        this.subject = subject;
    }

    @Override
    public boolean test(Student person) {
        return person.getSubjects().contains(this.subject);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentTakesSubjectPredicate)) {
            return false;
        }

        StudentTakesSubjectPredicate otherStudentTakesSubjectPredicate = (StudentTakesSubjectPredicate) other;
        return subject.equals(otherStudentTakesSubjectPredicate.subject);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("subject", subject).toString();
    }

}


