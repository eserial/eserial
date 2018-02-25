package hu.elte.eserial.testutil.dummy;

import hu.elte.eserial.annotation.ExcludeThis;

public class ExcludeThisDummy {

    private String fieldIncluded = "fieldIncludedValue";

    @ExcludeThis
    private String fieldExcluded = "fieldExcludedValue";

    private String fieldWithGetterExcluded = "fieldWithGetterExcludedValue";

    private String fieldWithSetterExcluded = "fieldWithSetterExcludedValue";

    public String getFieldIncluded() {
        return fieldIncluded;
    }

    public void setFieldIncluded(String fieldIncluded) {
        this.fieldIncluded = fieldIncluded;
    }

    public String getFieldExcluded() {
        return fieldExcluded;
    }

    public void setFieldExcluded(String fieldExcluded) {
        this.fieldExcluded = fieldExcluded;
    }

    @ExcludeThis
    public String getFieldWithGetterExcluded() {
        return fieldWithGetterExcluded;
    }

    public void setFieldWithGetterExcluded(String fieldWithGetterExcluded) {
        this.fieldWithGetterExcluded = fieldWithGetterExcluded;
    }

    public String getFieldWithSetterExcluded() {
        return fieldWithSetterExcluded;
    }

    @ExcludeThis
    public void setFieldWithSetterExcluded(String fieldWithSetterExcluded) {
        this.fieldWithSetterExcluded = fieldWithSetterExcluded;
    }
}
