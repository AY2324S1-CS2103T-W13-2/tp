package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Student} has the given email.
 */
public class StudentHasEmailPredicate implements Predicate<Student> {
    private final Email email;

    public StudentHasEmailPredicate(Email email) {
        this.email = email;
    }

    @Override
    public boolean test(Student person) {
        return person.getEmail().equals(this.email);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentHasEmailPredicate)) {
            return false;
        }

        StudentHasEmailPredicate otherStudentHasEmailPredicate = (StudentHasEmailPredicate) other;
        return email.equals(otherStudentHasEmailPredicate.email);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("email", email).toString();
    }

}
