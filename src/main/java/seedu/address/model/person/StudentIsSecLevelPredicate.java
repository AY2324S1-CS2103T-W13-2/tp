package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Student} is the given sec level.
 */
public class StudentIsSecLevelPredicate implements Predicate<Student> {
    private final SecLevel secLevel;

    public StudentIsSecLevelPredicate(SecLevel secLevel) {
        this.secLevel = secLevel;
    }

    @Override
    public boolean test(Student person) {
        return person.getSecLevel().equals(this.secLevel);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentIsSecLevelPredicate)) {
            return false;
        }

        StudentIsSecLevelPredicate otherStudentIsSecLevelPredicate = (StudentIsSecLevelPredicate) other;
        return secLevel.equals(otherStudentIsSecLevelPredicate.secLevel);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("secLevel", secLevel).toString();
    }

}


