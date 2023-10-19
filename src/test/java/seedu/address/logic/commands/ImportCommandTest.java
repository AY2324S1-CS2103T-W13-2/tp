package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Student;

class ImportCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ImportCommand(null, "test_data.csv"));
        assertThrows(NullPointerException.class, () -> new ImportCommand(new ArrayList<>(), null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        Path relativePath = Paths.get("src", "test", "data", "ImportDataTest");
        String fileName1 = relativePath + "\\" + "test_data_successful.csv";
        List<Student> expectedList1 = new ArrayList<>();
        expectedList1.add(AMY);
        expectedList1.add(BOB);

        assertThrows(NullPointerException.class, () -> new ImportCommand(expectedList1, fileName1).execute(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Path relativePath = Paths.get("src", "test", "data", "ImportDataTest");
        String fileName1 = relativePath + "\\" + "test_data_successful.csv";
        List<Student> expectedList1 = new ArrayList<>();
        expectedList1.add(AMY);
        expectedList1.add(BOB);

        CommandResult commandResult = new ImportCommand(expectedList1, fileName1).execute(modelStub);

        assertEquals(String.format(ImportCommand.MESSAGE_SUCCESS),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Path relativePath = Paths.get("src", "test", "data", "ImportDataTest");
        String fileName1 = relativePath + "\\" + "test_data_duplicates_name.csv";
        List<Student> expectedList1 = new ArrayList<>();
        expectedList1.add(AMY);
        expectedList1.add(BOB);

        ImportCommand importCommand = new ImportCommand(expectedList1, fileName1);
        ModelStub modelStub = new ModelStubWithPerson(AMY);

        assertThrows(
                CommandException.class,
                "Amy Bee (amy@example.com), "
                        + ImportCommand.MESSAGE_DUPLICATE_PERSON, () -> importCommand.execute(modelStub)
        );
    }

    @Test
    public void equals() {
        Path relativePath = Paths.get("src", "test", "data", "ImportDataTest");
        String fileName1 = relativePath + "\\" + "test_data_successful.csv";
        List<Student> expectedList1 = new ArrayList<>();
        expectedList1.add(AMY);
        expectedList1.add(BOB);
        ImportCommand importCommand1 = new ImportCommand(expectedList1, fileName1);

        String fileName2 = relativePath + "\\" + "test_data_no_subjects.csv";
        List<Student> expectedList2 = new ArrayList<>();
        expectedList2.add(HOON);
        expectedList2.add(IDA);
        ImportCommand importCommand2 = new ImportCommand(expectedList2, fileName2);

        // same object -> returns true
        assertTrue(importCommand1.equals(importCommand1));

        // same values -> returns true
        ImportCommand importCommandCopy = new ImportCommand(expectedList1, fileName1);
        assertTrue(importCommand1.equals(importCommandCopy));

        // different types -> returns false
        assertFalse(importCommand1.equals(1));

        // null -> returns false
        assertFalse(importCommand1.equals(null));

        // different student -> returns false
        assertFalse(importCommand1.equals(importCommand2));
    }

    @Test
    public void toStringMethod() {
        Path relativePath = Paths.get("src", "test", "data", "ImportDataTest");
        String fileName = relativePath + "\\" + "test_data_successful.csv";
        ImportCommand importCommand = new ImportCommand(new ArrayList<>(), fileName);
        String expected = ImportCommand.class.getCanonicalName() + "{Import from =" + fileName + "}";
        assertEquals(expected, importCommand.toString());
    }

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
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Student> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Student> getStudentFromFilteredPersonListByName(Name name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Student> getStudentFromFilteredPersonListByIndex(Index index) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single student.
     */
    private class ModelStubWithPerson extends ImportCommandTest.ModelStub {
        private final Student student;

        ModelStubWithPerson(Student student) {
            requireNonNull(student);
            this.student = student;
        }

        @Override
        public boolean hasPerson(Student student) {
            requireNonNull(student);
            return this.student.isSamePerson(student);
        }
    }

    /**
     * A Model stub that always accept the student being added.
     */
    private class ModelStubAcceptingPersonAdded extends ImportCommandTest.ModelStub {
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
