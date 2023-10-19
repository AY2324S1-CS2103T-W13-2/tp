package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBJECT_MATHEMATICS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBJECT_PHYSICS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.StudentPredicateList;
import seedu.address.model.person.StudentTakesSubjectPredicate;
import seedu.address.model.tag.Subject;

public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_oneCondition_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        StudentPredicateList predicateList = new StudentPredicateList();
        predicateList.add(new StudentTakesSubjectPredicate(new Subject(VALID_SUBJECT_MATHEMATICS)));
        FilterCommand command = new FilterCommand(predicateList);
        expectedModel.updateFilteredPersonList(predicateList.reduce());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleConditions_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        StudentPredicateList predicateList = new StudentPredicateList();
        predicateList.add(new StudentTakesSubjectPredicate(new Subject(VALID_SUBJECT_MATHEMATICS)));
        predicateList.add(new StudentTakesSubjectPredicate(new Subject(VALID_SUBJECT_PHYSICS)));
        FilterCommand command = new FilterCommand(predicateList);
        expectedModel.updateFilteredPersonList(predicateList.reduce());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ELLE), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        StudentPredicateList predicateList = new StudentPredicateList();
        predicateList.add(new StudentTakesSubjectPredicate(new Subject(VALID_SUBJECT_MATHEMATICS)));
        predicateList.add(new StudentTakesSubjectPredicate(new Subject(VALID_SUBJECT_PHYSICS)));
        FilterCommand filterCommand = new FilterCommand(predicateList);
        String expected = FilterCommand.class.getCanonicalName() + "{predicateList=" + predicateList + "}";
        assertEquals(expected, filterCommand.toString());
    }
}
