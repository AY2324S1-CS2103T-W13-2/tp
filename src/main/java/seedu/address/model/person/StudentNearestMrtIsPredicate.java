package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Student}'s nearest MRT station is the given station.
 */
public class StudentNearestMrtIsPredicate implements Predicate<Student> {
    private final MrtStation mrtStation;

    public StudentNearestMrtIsPredicate(MrtStation mrtStation) {
        this.mrtStation = mrtStation;
    }

    @Override
    public boolean test(Student person) {
        return person.getNearestMrtStation().equals(this.mrtStation);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentNearestMrtIsPredicate)) {
            return false;
        }

        StudentNearestMrtIsPredicate otherStudentNearestMrtIsPredicate = (StudentNearestMrtIsPredicate) other;
        return mrtStation.equals(otherStudentNearestMrtIsPredicate.mrtStation);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("mrtStation", mrtStation).toString();
    }

}



