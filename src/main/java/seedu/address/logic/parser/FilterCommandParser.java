package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEAREST_MRT_STATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEC_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.StudentHasAddressPredicate;
import seedu.address.model.person.StudentHasEmailPredicate;
import seedu.address.model.person.StudentHasPhonePredicate;
import seedu.address.model.person.StudentIsGenderPredicate;
import seedu.address.model.person.StudentIsSecLevelPredicate;
import seedu.address.model.person.StudentNearestMrtIsPredicate;
import seedu.address.model.person.StudentPredicateList;
import seedu.address.model.person.StudentTakesSubjectPredicate;
import seedu.address.model.tag.Subject;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns an FilterCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_GENDER, PREFIX_SEC_LEVEL, PREFIX_NEAREST_MRT_STATION, PREFIX_SUBJECT);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_GENDER, PREFIX_GENDER, PREFIX_NEAREST_MRT_STATION);

        StudentPredicateList predicateList = new StudentPredicateList();

        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            predicateList.add(new StudentHasPhonePredicate(ParserUtil
                    .parsePhone(argMultimap.getValue(PREFIX_PHONE).get())));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            predicateList.add(new StudentHasEmailPredicate(ParserUtil
                    .parseEmail(argMultimap.getValue(PREFIX_EMAIL).get())));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            predicateList.add(new StudentHasAddressPredicate(ParserUtil
                    .parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get())));
        }
        if (argMultimap.getValue(PREFIX_GENDER).isPresent()) {
            predicateList.add(new StudentIsGenderPredicate(ParserUtil
                    .parseGender(argMultimap.getValue(PREFIX_GENDER).get())));
        }
        if (argMultimap.getValue(PREFIX_SEC_LEVEL).isPresent()) {
            predicateList.add(new StudentIsSecLevelPredicate(ParserUtil
                    .parseSecLevel(argMultimap.getValue(PREFIX_SEC_LEVEL).get())));
        }
        if (argMultimap.getValue(PREFIX_NEAREST_MRT_STATION).isPresent()) {
            predicateList.add(new StudentNearestMrtIsPredicate(ParserUtil
                    .parseMrtStation(argMultimap.getValue(PREFIX_NEAREST_MRT_STATION).get())));
        }
        parseTagsForFilter(argMultimap.getAllValues(PREFIX_SUBJECT))
                .ifPresent(subjects -> subjects
                        .forEach(subject -> predicateList.add(new StudentTakesSubjectPredicate(subject))));

        if (predicateList.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        return new FilterCommand(predicateList);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Subject>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Subject>} containing zero tags.
     */
    private Optional<Set<Subject>> parseTagsForFilter(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }
}
