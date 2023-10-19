package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Student;

/**
 * Import students data to the address book.
 */
public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";
    public static final String MESSAGE_NAME = "Name";
    public static final String MESSAGE_PHONE = "Phone";
    public static final String MESSAGE_EMAIL = "Email";
    public static final String MESSAGE_ADDRESS = "Address";
    public static final String MESSAGE_GENDER = "Gender";
    public static final String MESSAGE_SEC_LEVEL = "Sec Level";
    public static final String MESSAGE_MRT_STATION = "Nearest MRT Station";
    public static final String MESSAGE_SUBJECT = "Subject";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Import a csv file with students details to the address book. "
            + "Parameters: File Path\n"
            + "Example: " + COMMAND_WORD + " StudentData.csv\n"
            + "Note that the order of column should be "
            + "\"" + MESSAGE_NAME + "\" , \"" + MESSAGE_PHONE + "\" , \"" + MESSAGE_EMAIL
            + "\" , \"" + MESSAGE_ADDRESS + "\" , \"" + MESSAGE_GENDER + "\" , \""
            + MESSAGE_SEC_LEVEL + "\" , \"" + MESSAGE_MRT_STATION + "\" , \"" + MESSAGE_SUBJECT + "\"";

    public static final String MESSAGE_SUCCESS = "All students data imported";
    public static final String MESSAGE_DUPLICATE_PERSON = "has duplicates";

    private final String filePath;
    private final List<Student> students;
    private final List<Student> duplicateStudents;

    /**
     * Creates an ImportCommand to import all student details
     */
    public ImportCommand(List<Student> students, String filePath) {
        requireNonNull(students);
        requireNonNull(filePath);
        this.students = students;
        this.filePath = filePath;
        this.duplicateStudents = new ArrayList<>();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        for (Student s : students) {
            if (!model.hasPerson(s)) {
                model.addPerson(s);
            } else if (model.hasPerson(s) && !duplicateStudents.contains(s)) {
                duplicateStudents.add(s);
            }
        }

        if (!duplicateStudents.isEmpty()) {
            StringBuilder duplicates = new StringBuilder();
            for (Student s : duplicateStudents) {
                duplicates.append(s.getName()).append(" (").append(s.getEmail()).append("), ");
            }
            throw new CommandException(duplicates + MESSAGE_DUPLICATE_PERSON);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ImportCommand)) {
            return false;
        }

        ImportCommand otherAddCommand = (ImportCommand) other;
        return filePath.equals(otherAddCommand.filePath);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("Import from ", filePath)
                .toString();
    }
}
