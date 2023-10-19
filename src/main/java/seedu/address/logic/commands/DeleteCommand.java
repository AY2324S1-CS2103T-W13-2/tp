package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.NoSuchElementException;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Student;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index or name of that person.\n"
            + "Parameters: NAME (must be a string)\n"
            + "OR"
            + "Parameters: INDEX (must be a positive integer)"
            + "Example: " + COMMAND_WORD + " Joe"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Student: %1$s";

    private final Name targetName;
    private final Index targetIndex;

    /**
     * Constructs a delete command using Name.
     * @param targetName Name of student to be deleted.
     */
    public DeleteCommand(Name targetName) {
        this.targetName = targetName;
        this.targetIndex = null;
    }

    /**
     * Constructs a delete command using Index.
     * @param targetIndex Index to be deleted.
     */
    public DeleteCommand(Index targetIndex) {
        this.targetName = null;
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            Student studentToDelete = model.getStudentFromFilteredPersonListByName(targetName).orElseGet(() ->
                    model.getStudentFromFilteredPersonListByIndex(targetIndex).get());

            model.deletePerson(studentToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(studentToDelete)));
        } catch (NoSuchElementException exception) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DETAILS);
        }

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return targetName == null
                ? targetIndex.equals(otherDeleteCommand.targetIndex)
                : targetName.equals(otherDeleteCommand.targetName);
    }

    @Override
    public String toString() {
        if (targetName == null) {
            return new ToStringBuilder(this)
                    .add("targetIndex", targetIndex)
                    .toString();
        } else {
            return new ToStringBuilder(this)
                    .add("targetName", targetName)
                    .toString();
        }
    }
}
