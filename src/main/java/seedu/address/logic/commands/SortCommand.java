package seedu.address.logic.commands;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.SortBy;
import seedu.address.model.person.SortIn;
import seedu.address.model.person.Student;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Adds a student to the address book.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts student list. "
            + "Parameters: "
            + PREFIX_SORT_IN + "SEQUENCE \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SORT_IN + "ASC ";

    public static final String MESSAGE_SUCCESS = "Student list is sorted";
    private final SortIn sortIn;


    /**
     * Creates an AddCommand to add the specified {@code Student}
     */
    public SortCommand(SortIn sortIn) {
        requireNonNull(sortIn);
        this.sortIn = sortIn;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("sortIn", sortIn)
                .toString();
    }
}
