package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.SortIn;
import seedu.address.model.person.Student;
import seedu.address.model.person.Visual;

public class ExportCommandTest {

    @Test
    public void execute_visualAcceptedByModel_exportSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Visual validVisual = new Visual("TABLE");

        CommandResult commandResult = new ExportCommand(validVisual).execute(modelStub);

        assertEquals(String.format(ExportCommand.MESSAGE_SUCCESS), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_emptyTable_exportFailed() {
        ModelManager model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Visual validVisual = new Visual("TABLE");

        ExportCommand exportCommand = new ExportCommand(validVisual);

        assertThrows(CommandException.class, () -> exportCommand.execute(model));
    }

    @Test
    public void toStringMethod() {
        String visual = "TABLE";
        Visual validVisual = new Visual(visual);
        ExportCommand sortCommand = new ExportCommand(validVisual);
        String expected = ExportCommand.class.getCanonicalName() + "{visual=" + visual + "}";
        assertEquals(expected, sortCommand.toString());
    }

    @Test
    public void equals_barObjectsAreEqual_returnsTrue() {
        Visual visual = new Visual("Bar");
        ExportCommand command1 = new ExportCommand(visual);
        ExportCommand command2 = new ExportCommand(visual);
        assertEquals(command1, command2);
    }

    @Test
    public void equals_barObjectsAreSame_returnsTrue() {
        Visual visual = new Visual("Bar");
        ExportCommand command1 = new ExportCommand(visual);
        assertEquals(command1, command1);
    }

    @Test
    public void equals_tableObjectsAreEqual_returnsTrue() {
        Visual visual = new Visual("Table");
        ExportCommand command1 = new ExportCommand(visual);
        ExportCommand command2 = new ExportCommand(visual);
        assertEquals(command1, command2);
    }

    @Test
    public void equals_tableObjectsAreSame_returnsTrue() {
        Visual visual = new Visual("Table");
        ExportCommand command1 = new ExportCommand(visual);
        assertEquals(command1, command1);
    }

    @Test
    public void equals_objectsAreNotEqual_returnsFalse() {
        Visual visual1 = new Visual("Bar");
        Visual visual2 = new Visual("Table");
        ExportCommand command1 = new ExportCommand(visual1);
        ExportCommand command2 = new ExportCommand(visual2);
        assertNotEquals(command1, command2);
    }

    @Test
    public void equals_objectComparedWithNull_returnsFalse() {
        Visual visual = new Visual("Bar");
        ExportCommand command = new ExportCommand(visual);
        assertNotEquals(command, null);
    }

    @Test
    public void equals_objectComparedWithDifferentClass_returnsFalse() {
        Visual visual = new Visual("Bar");
        ExportCommand command = new ExportCommand(visual);
        assertNotEquals(command, "This is a string");
    }

    /**
     * A default model stub that have all the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Student target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Student target, Student editedStudent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Student> getFilteredPersonList() {
            return null;
        }

        @Override
        public void updateFilteredPersonList(Predicate<Student> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortedPersonList(SortIn sortIn) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void export(Visual visual) {

        }

        /**
         * Get the student object from filtered student list by name if exists, return
         * None if the student does not exist.
         *
         * @param name the name of the student the caller want to get.
         * @return The student object in the filteredlist at the given index.
         */
        @Override
        public Optional<Student> getStudentFromFilteredPersonListByName(Name name) {
            return Optional.empty();
        }

        /**
         * Get the student object from filtered student list by index if exists, return
         * None if the student does not exist.
         *
         * @param index the index of the student the caller want to get.
         * @return The student object in the filteredlist with the given name.
         */
        @Override
        public Optional<Student> getStudentFromFilteredPersonListByIndex(Index index) {
            return Optional.empty();
        }

    }

    /**
     * A Model stub that always accept the student being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Student> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Student student) {
            requireNonNull(student);
            return personsAdded.stream().anyMatch(student::isSamePerson);
        }

        @Override
        public void addPerson(Student student) {
            requireNonNull(student);
            personsAdded.add(student);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
