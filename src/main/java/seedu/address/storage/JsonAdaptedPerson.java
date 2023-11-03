package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
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

/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String gender;
    private final String secLevel;
    private final String nearestMrtStation;

    private final List<JsonAdaptedTag> subjects = new ArrayList<>();

    private final String remark;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("gender") String gender, @JsonProperty("secLevel") String secLevel,
            @JsonProperty("nearestMrtStation") String nearestMrtStation,
            @JsonProperty("tags") List<JsonAdaptedTag> subjects, @JsonProperty("remark") String remark) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.secLevel = secLevel;
        this.nearestMrtStation = nearestMrtStation;
        if (subjects != null) {
            this.subjects.addAll(subjects);
        }
        this.remark = remark;
    }

    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String address,
                             @JsonProperty("gender") String gender, @JsonProperty("secLevel") String secLevel,
                             @JsonProperty("nearestMrtStation") String nearestMrtStation,
                             @JsonProperty("tags") List<JsonAdaptedTag> subjects) {
        this(name, phone, email, address, gender, secLevel, nearestMrtStation, subjects, "");
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Student source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        gender = source.getGender().value;
        secLevel = source.getSecLevel().value;
        nearestMrtStation = source.getNearestMrtStation().mrtStationName;
        subjects.addAll(source.getSubjects().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        remark = source.getRemark().value;
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code Student} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public Student toModelType() throws IllegalValueException {
        final List<Subject> personSubjects = new ArrayList<>();
        for (JsonAdaptedTag subject : subjects) {
            personSubjects.add(subject.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (gender == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName()));
        }
        if (!Gender.isValidGender(gender)) {
            throw new IllegalValueException(Gender.MESSAGE_CONSTRAINTS);
        }
        final Gender modelGender = new Gender(gender);

        if (secLevel == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, SecLevel.class.getSimpleName()));
        }
        if (!SecLevel.isValidSecLevel(secLevel)) {
            throw new IllegalValueException(SecLevel.MESSAGE_CONSTRAINTS);
        }
        final SecLevel modelSecLevel = new SecLevel(secLevel);

        if (nearestMrtStation == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    MrtStation.class.getSimpleName()));
        }
        if (!MrtStation.isValidMrtStationName(nearestMrtStation)) {
            throw new IllegalValueException(MrtStation.MESSAGE_CONSTRAINTS);
        }
        final MrtStation modelNearestMrtStation = new MrtStation(nearestMrtStation);

        final Remark remark = new Remark(this.remark);
        final Set<Subject> modelSubjects = new HashSet<>(personSubjects);
        return new Student(modelName, modelPhone, modelEmail, modelAddress,
                modelGender, modelSecLevel, modelNearestMrtStation, modelSubjects, remark);
    }

}
