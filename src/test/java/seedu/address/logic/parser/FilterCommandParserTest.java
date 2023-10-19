package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBJECT_MATHEMATICS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBJECT_PHYSICS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEAREST_MRT_STATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.person.Gender;
import seedu.address.model.person.MrtStation;
import seedu.address.model.person.StudentIsGenderPredicate;
import seedu.address.model.person.StudentNearestMrtIsPredicate;
import seedu.address.model.person.StudentPredicateList;
import seedu.address.model.person.StudentTakesSubjectPredicate;
import seedu.address.model.tag.Subject;

public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_singleValidArg_returnsFilterCommand() {
        StudentPredicateList predicateList = new StudentPredicateList();
        predicateList.add(new StudentIsGenderPredicate(new Gender("M")));
        FilterCommand expectedFilterCommand = new FilterCommand(predicateList);
        assertParseSuccess(parser, " g/M", expectedFilterCommand);
        assertParseSuccess(parser, "\n g/M \t", expectedFilterCommand);
    }

    @Test
    public void parse_multipleValidArgs_returnsFilterCommand() {
        StudentPredicateList predicateList = new StudentPredicateList();
        predicateList.add(new StudentIsGenderPredicate(new Gender("M")));
        predicateList.add(new StudentNearestMrtIsPredicate(new MrtStation("Kent Ridge")));
        FilterCommand expectedFilterCommand = new FilterCommand(predicateList);
        assertParseSuccess(parser, " g/M m/Kent Ridge", expectedFilterCommand);

        predicateList.add(new StudentTakesSubjectPredicate(new Subject(VALID_SUBJECT_MATHEMATICS)));
        expectedFilterCommand = new FilterCommand(predicateList);
        assertParseSuccess(parser, "  m/Kent Ridge s/Mathematics g/M", expectedFilterCommand);

        predicateList.add(new StudentTakesSubjectPredicate(new Subject(VALID_SUBJECT_PHYSICS)));
        expectedFilterCommand = new FilterCommand(predicateList);
        assertParseSuccess(parser, " s/Mathematics s/Physics g/M m/Kent Ridge", expectedFilterCommand);
    }

    @Test
    public void parse_repeatedNonSubjectArgs_throwsParseException() {
        assertParseFailure(parser, " m/Clementi m/Buona Vista",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NEAREST_MRT_STATION));
        assertParseFailure(parser, " m/Clementi m/Buona Vista g/M g/F",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NEAREST_MRT_STATION, PREFIX_GENDER));
        assertParseFailure(parser, " m/Clementi p/12345678 p/23456789",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));
    }

}
