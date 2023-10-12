package seedu.address.model.tag;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Subject in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidSubjectName(String)}
 */
public class Subject {

    public static final String MESSAGE_CONSTRAINTS = "Subjects names should be alphanumeric";
    private static final String MESSAGE_INVALID_DATE_FORMAT = "Date format should be MMM YYYY (e.g. Jul 2023)";
    private static final String VALIDATION_REGEX = "\\p{Alnum}+";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy MMM", Locale.ENGLISH);

    public final String subjectName;
    public final YearMonth enrolDate;

    /**
     * Constructs a {@code Subject}.
     *
     * @param subjectName A valid subject name.
     */
    public Subject(String subjectName) {
        requireNonNull(subjectName);
        checkArgument(isValidSubjectName(subjectName), MESSAGE_CONSTRAINTS);

        this.subjectName = subjectName;
        this.enrolDate = YearMonth.now();
    }

    /**
     * Constructs a {@code Subject}.
     *
     * @param subjectName A valid subject name.
     * @param date Subject enrol date.
     */
    public Subject(String subjectName, String date) {
        requireNonNull(subjectName);
        checkArgument(isValidSubjectName(subjectName), MESSAGE_CONSTRAINTS);

        YearMonth[] enrolDate = new YearMonth[1];
        checkArgument(isValidDate(date, enrolDate), MESSAGE_INVALID_DATE_FORMAT);

        this.subjectName = subjectName;
        this.enrolDate = enrolDate[0];
    }

    /**
     * Returns true if a given string is a valid subject name.
     */
    public static boolean isValidSubjectName(String subjectName) {
        return subjectName.matches(VALIDATION_REGEX);
    }

    public static boolean isValidDate(String date, YearMonth[] enrolDate) {
        try {
            enrolDate[0] = YearMonth.parse(date, FORMATTER);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Subject)) {
            return false;
        }

        Subject otherSubject = (Subject) other;
        return subjectName.equals(otherSubject.subjectName);
    }

    @Override
    public int hashCode() {
        return subjectName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + subjectName + ']';
    }

}
