package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.MrtStation;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.SecLevel;
import seedu.address.model.person.Student;
import seedu.address.model.tag.Subject;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Student objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_GENDER = "F";
    public static final String DEFAULT_SEC_LEVEL = "2";
    public static final String DEFAULT_NEAREST_MRT_STATION = "Buona Vista";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Gender gender;
    private SecLevel secLevel;
    private MrtStation nearestMrtStation;
    private Set<Subject> subjects;

    private Remark remark;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        gender = new Gender(DEFAULT_GENDER);
        secLevel = new SecLevel(DEFAULT_SEC_LEVEL);
        nearestMrtStation = new MrtStation(DEFAULT_NEAREST_MRT_STATION);
        subjects = new HashSet<>();
        remark = new Remark();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code studentToCopy}.
     */
    public PersonBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        phone = studentToCopy.getPhone();
        email = studentToCopy.getEmail();
        address = studentToCopy.getAddress();
        gender = studentToCopy.getGender();
        secLevel = studentToCopy.getSecLevel();
        nearestMrtStation = studentToCopy.getNearestMrtStation();
        subjects = new HashSet<>(studentToCopy.getSubjects());
        remark = studentToCopy.getRemark();
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code subjects} into a {@code Set<Subject>} and set it to the {@code Student} that we are building.
     */
    public PersonBuilder withSubjects(String ... subjects) {
        this.subjects = SampleDataUtil.getSubjectSet(subjects);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Student} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Student} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Student} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Student} that we are building.
     */
    public PersonBuilder withGender(String gender) {
        this.gender = new Gender(gender);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Student} that we are building.
     */
    public PersonBuilder withSecLevel(String secLevel) {
        this.secLevel = new SecLevel(secLevel);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Student} that we are building.
     */
    public PersonBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Student} that we are building.
     */
    public PersonBuilder withNearestMrtStation(String nearestMrtStation) {
        this.nearestMrtStation = new MrtStation(nearestMrtStation);
        return this;
    }

    /**
     * Creates a student object.
     *
     * @return a student.
     */
    public Student build() {
        return new Student(name, phone, email, address,
                gender, secLevel, nearestMrtStation,
                subjects, remark);
    }

}
