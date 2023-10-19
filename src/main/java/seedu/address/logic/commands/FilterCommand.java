package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Student;
import seedu.address.model.person.StudentPredicateList;

/**
 * Finds and lists all persons in address book who fulfills the given conditions.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons who fulfill"
            + " the specified conditions and displays them as a list with index numbers.\n"
            + "Parameters: prefix/[field value]...\n"
            + "Example: " + COMMAND_WORD + " s/Mathematics" + " " + "s/English";

    private final StudentPredicateList predicateList;

    public FilterCommand(StudentPredicateList predicateList) {
        this.predicateList = predicateList;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<Student> predicate = predicateList.reduce();
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterCommand)) {
            return false;
        }

        FilterCommand otherFilterCommand = (FilterCommand) other;
        return predicateList.equals(otherFilterCommand.predicateList);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicateList", predicateList)
                .toString();
    }
}
