package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Student} has the given phone number.
 */
public class StudentHasPhonePredicate implements Predicate<Student> {
    private final Phone phone;

    public StudentHasPhonePredicate(Phone phone) {
        this.phone = phone;
    }

    @Override
    public boolean test(Student person) {
        return person.getPhone().equals(this.phone);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentHasPhonePredicate)) {
            return false;
        }

        StudentHasPhonePredicate otherStudentHasPhonePredicate = (StudentHasPhonePredicate) other;
        return phone.equals(otherStudentHasPhonePredicate.phone);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("phone", phone).toString();
    }

}
