package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Subject;
import seedu.address.testutil.PersonBuilder;

public class StudentPredicateListTest {

    @Test
    public void add_validPredicate_success() {
        ArrayList<Predicate<Student>> predicateList = new ArrayList<>();
        predicateList.add(new StudentTakesSubjectPredicate(new Subject("Physics")));
        StudentPredicateList testList = new StudentPredicateList();
        testList.add(new StudentTakesSubjectPredicate(new Subject("Physics")));
        assertEquals(new StudentPredicateList(predicateList), testList);
    }

    @Test
    public void reduce_success() {
        StudentHasPhonePredicate phonePredicate = new StudentHasPhonePredicate(new Phone("12345678"));
        StudentIsGenderPredicate genderPredicate = new StudentIsGenderPredicate(new Gender("F"));
        Predicate<Student> reducedPredicate = phonePredicate.and(genderPredicate);
        StudentPredicateList testList = new StudentPredicateList();
        testList.add(phonePredicate);
        testList.add(genderPredicate);
        Student student = new PersonBuilder().withGender("F").withPhone("12345678").build();
        assertEquals(reducedPredicate.test(student), testList.reduce().test(student));
    }

    @Test
    public void toStringMethod() {
        StudentHasPhonePredicate phonePredicate = new StudentHasPhonePredicate(new Phone("91828564"));
        StudentIsGenderPredicate genderPredicate = new StudentIsGenderPredicate(new Gender("F"));
        ArrayList<Predicate<Student>> predicateList = new ArrayList<>();
        predicateList.add(phonePredicate);
        predicateList.add(genderPredicate);
        StudentPredicateList testList = new StudentPredicateList();
        testList.add(phonePredicate);
        testList.add(genderPredicate);
        assertEquals(predicateList.toString(), testList.toString());
    }

    @Test
    public void isEmpty_success() {
        ArrayList<Predicate<Student>> emptyList = new ArrayList<>();
        StudentPredicateList testList = new StudentPredicateList();
        assertEquals(emptyList.isEmpty(), testList.isEmpty());

        StudentTakesSubjectPredicate subjectPredicate = new StudentTakesSubjectPredicate(new Subject("Chemistry"));
        testList.add(subjectPredicate);
        assertFalse(emptyList.isEmpty() == testList.isEmpty());
    }
}
