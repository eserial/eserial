package hu.elte.eserial.testutil.dummy;

import hu.elte.eserial.annotation.IncludeElements;
import hu.elte.eserial.annotation.IncludeMatching;
import hu.elte.eserial.annotation.enumeration.IncludeMatcher;

@IncludeElements({"includedAndNull", "includedAndNotNull"})
@IncludeMatching(IncludeMatcher.NotNull)
public class IncludeFieldsAndMatchingDummy {

    private String includedAndNull = null;
    private String includedAndNotNull = "includedAndNotNullValue";

    private String notIncludedAndNull = null;
    private String notIncludedAndNotNull = "notIncludedAndNotNullValue";

    public String getIncludedAndNull() {
        return includedAndNull;
    }

    public void setIncludedAndNull(String includedAndNull) {
        this.includedAndNull = includedAndNull;
    }

    public String getIncludedAndNotNull() {
        return includedAndNotNull;
    }

    public void setIncludedAndNotNull(String includedAndNotNull) {
        this.includedAndNotNull = includedAndNotNull;
    }

    public String getNotIncludedAndNull() {
        return notIncludedAndNull;
    }

    public void setNotIncludedAndNull(String notIncludedAndNull) {
        this.notIncludedAndNull = notIncludedAndNull;
    }

    public String getNotIncludedAndNotNull() {
        return notIncludedAndNotNull;
    }

    public void setNotIncludedAndNotNull(String notIncludedAndNotNull) {
        this.notIncludedAndNotNull = notIncludedAndNotNull;
    }
}
