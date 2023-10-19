package seedu.address.model.person;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * Represents a list of student predicates.
 */
public class StudentPredicateList {
    private ArrayList<Predicate<Student>> predicateList;

    public StudentPredicateList() {
        this.predicateList = new ArrayList<>();
    }

    public StudentPredicateList(ArrayList<Predicate<Student>> predicateList) {
        this.predicateList = predicateList;
    }

    /**
     * Adds a student predicate to the StudentPredicateList.
     *
     * @param predicate The student predicate to be added.
     */
    public void add(Predicate<Student> predicate) {
        this.predicateList.add(predicate);
    }

    /**
     * Reduces the StudentPredicateList.
     * @return The reduced StudentPredicateList.
     */
    public Predicate<Student> reduce() {
        return this.predicateList.stream().reduce(Predicate::and).orElse(student -> true);
    }

    @Override
    public String toString() {
        return this.predicateList.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentPredicateList)) {
            return false;
        }

        StudentPredicateList otherStudentPredicateList = (StudentPredicateList) other;
        return predicateList.equals(otherStudentPredicateList.predicateList);
    }

    /**
     * Returns true if the StudentPredicateList is empty and false if it is not.
     * @return A boolean representing whether the StudentPredicateList is empty.
     */
    public boolean isEmpty() {
        return this.predicateList.isEmpty();
    }


}
