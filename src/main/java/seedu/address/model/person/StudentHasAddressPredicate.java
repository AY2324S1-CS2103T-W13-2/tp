package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Student} has the given address.
 */
public class StudentHasAddressPredicate implements Predicate<Student> {
    private final Address address;

    public StudentHasAddressPredicate(Address address) {
        this.address = address;
    }

    @Override
    public boolean test(Student person) {
        return person.getAddress().equals(this.address);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentHasAddressPredicate)) {
            return false;
        }

        StudentHasAddressPredicate otherStudentHasAddressPredicate = (StudentHasAddressPredicate) other;
        return address.equals(otherStudentHasAddressPredicate.address);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("address", address).toString();
    }

}

