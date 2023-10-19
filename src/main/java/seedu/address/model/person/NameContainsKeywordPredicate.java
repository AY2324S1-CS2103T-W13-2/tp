package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Student}'s {@code Name} matches the keyword given.
 */
public class NameContainsKeywordPredicate implements Predicate<Student> {
    private final String keyword;

    public NameContainsKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Student person) {
        return StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NameContainsKeywordPredicate)) {
            return false;
        }

        NameContainsKeywordPredicate otherNameContainsKeywordsPredicate = (NameContainsKeywordPredicate) other;
        return keyword.equals(otherNameContainsKeywordsPredicate.keyword);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keyword", keyword).toString();
    }
}
