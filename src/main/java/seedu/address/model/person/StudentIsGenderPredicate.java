package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Student} is the given gender.
 */
public class StudentIsGenderPredicate implements Predicate<Student> {
    private final Gender gender;

    public StudentIsGenderPredicate(Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean test(Student person) {
        return person.getGender().equals(this.gender);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentIsGenderPredicate)) {
            return false;
        }

        StudentIsGenderPredicate otherStudentIsGenderPredicate = (StudentIsGenderPredicate) other;
        return gender.equals(otherStudentIsGenderPredicate.gender);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("gender", gender).toString();
    }

}

