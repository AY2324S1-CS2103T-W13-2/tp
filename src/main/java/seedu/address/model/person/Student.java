package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Subject;

/**
 * Represents a Student in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    private static final ZoneId SINGAPORE_ZONE_ID = ZoneId.of("Asia/Singapore");
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy MMM", Locale.ENGLISH);

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Gender gender;
    private final SecLevel secLevel;

    private final MrtStation nearestMrtStation;
    private final Set<Subject> subjects = new HashSet<>();

    private final Remark remark;

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, Phone phone, Email email,
                   Address address, Gender gender,
                   SecLevel secLevel, MrtStation nearestMrtStation,
                   Set<Subject> subjects, Remark remark) {
        requireAllNonNull(name, phone, email, address, gender,
                secLevel, nearestMrtStation, subjects, remark);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.secLevel = secLevel;
        this.nearestMrtStation = nearestMrtStation;
        this.subjects.addAll(subjects);
        this.remark = remark;
    }

    public Student(Name name, Phone phone, Email email,
                   Address address, Gender gender,
                   SecLevel seclevel, MrtStation nearestMrtStation,
                   Set<Subject> subjects) {
        this(name, phone, email, address, gender, seclevel, nearestMrtStation,
        subjects, new Remark(""));
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Gender getGender() {
        return gender;
    }

    public SecLevel getSecLevel() {
        return secLevel;
    }

    public MrtStation getNearestMrtStation() {
        return nearestMrtStation;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Subject> getSubjects() {
        return Collections.unmodifiableSet(subjects);
    }

    /**
     * Returns the int value of sec level
     */
    public int getSecLevelValue() {
        return this.secLevel.getValue();
    }

    /**
     * Returns the remark of the student
     */
    public Remark getRemark() {
        return this.remark;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getName().equals(getName());
    }


    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;

        return name.equals(otherStudent.name)
                && phone.equals(otherStudent.phone)
                && email.equals(otherStudent.email)
                && address.equals(otherStudent.address)
                && gender.equals(otherStudent.gender)
                && secLevel.equals(otherStudent.secLevel)
                && nearestMrtStation.equals(otherStudent.nearestMrtStation)
                && subjects.equals(otherStudent.subjects);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, gender, secLevel, nearestMrtStation, subjects);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("gender", gender)
                .add("secLevel", secLevel)
                .add("nearestMrtStation", nearestMrtStation)
                .add("subjects", subjects)
                .toString();
    }

}
