package seedu.address.logic.commands.tableresults;

import java.util.Map;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.tag.Subject;

/**
 * Represents for the command result for enrol date table generation.
 */
public class EnrolDateTableCommandResult extends CommandResult {
    private static final String MESSAGE_SUCCESS = "A table categorized by EnrolDate is shown";

    private int janCount;
    private int febCount;
    private int marCount;
    private int aprCount;
    private int mayCount;
    private int junCount;
    private int julCount;
    private int augCount;
    private int sepCount;
    private int octCount;
    private int novCount;
    private int decCount;

    /**
     * Constructor for EnrolDateTableCommandResult.
     * @param columnValueMapping hash map instance containing column titles(String) as keys and counts(Long) as values.
     */
    public EnrolDateTableCommandResult(Map<String, Integer> columnValueMapping) {
        super(MESSAGE_SUCCESS);
        this.janCount = columnValueMapping.get("Jan");
        this.febCount = columnValueMapping.get("Feb");
        this.marCount = columnValueMapping.get("Mar");
        this.aprCount = columnValueMapping.get("Apr");
        this.mayCount = columnValueMapping.get("May");
        this.junCount = columnValueMapping.get("Jun");
        this.julCount = columnValueMapping.get("Jul");
        this.augCount = columnValueMapping.get("Aug");
        this.sepCount = columnValueMapping.get("Sep");
        this.octCount = columnValueMapping.get("Oct");
        this.novCount = columnValueMapping.get("Nov");
        this.decCount = columnValueMapping.get("Dec");
    }

    /**
     * Check if this CommandResult instance is meant for creating table window.
     * @return always return true for EnrolDateTableCommandResult instance.
     */
    @Override
    public boolean isShowTable() {
        return true;
    }

    /**
     * Getter method for janCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for Jan category.
     */
    public int getJanCount() {
        return janCount;
    }

    /**
     * Getter method for febCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for Feb category.
     */
    public int getFebCount() {
        return febCount;
    }

    /**
     * Getter method for marCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for Mar category.
     */
    public int getMarCount() {
        return marCount;
    }

    /**
     * Getter method for aprCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for Apr category.
     */
    public int getAprCount() {
        return aprCount;
    }

    /**
     * Getter method for mayCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for May category.
     */
    public int getMayCount() {
        return mayCount;
    }

    /**
     * Getter method for junCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for Jun category.
     */
    public int getJunCount() {
        return junCount;
    }

    /**
     * Getter method for julCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for Jul category.
     */
    public int getJulCount() {
        return julCount;
    }

    /**
     * Getter method for augCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for Aug category.
     */
    public int getAugCount() {
        return augCount;
    }

    /**
     * Getter method for sepCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for Sep category.
     */
    public int getSepCount() {
        return sepCount;
    }

    /**
     * Getter method for octCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for Oct category.
     */
    public int getOctCount() {
        return octCount;
    }

    /**
     * Getter method for novCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for Nov category.
     */
    public int getNovCount() {
        return novCount;
    }

    /**
     * Getter method for decCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for Dec category.
     */
    public int getDecCount() {
        return decCount;
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EnrolDateTableCommandResult)) {
            return false;
        }

        EnrolDateTableCommandResult otherCommandResult = (EnrolDateTableCommandResult) other;

        return super.equals(otherCommandResult)
                && otherCommandResult.janCount == janCount
                && otherCommandResult.febCount == febCount
                && otherCommandResult.marCount == marCount
                && otherCommandResult.aprCount == aprCount
                && otherCommandResult.mayCount == mayCount
                && otherCommandResult.junCount == junCount
                && otherCommandResult.julCount == julCount
                && otherCommandResult.augCount == augCount
                && otherCommandResult.sepCount == sepCount
                && otherCommandResult.octCount == octCount
                && otherCommandResult.novCount == novCount
                && otherCommandResult.decCount == decCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getFeedbackToUser(), super.isShowTable(), super.isExit(),
                janCount, febCount, marCount, aprCount, mayCount, junCount, julCount,
                augCount, sepCount, octCount, novCount, decCount);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("feedbackToUser", MESSAGE_SUCCESS)
                .add("showHelp", isShowHelp())
                .add("showTable", isShowTable())
                .add("showBarChart", isShowBarChart())
                .add("exit", isExit())
                .toString();
    }

}
