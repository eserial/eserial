package hu.elte.eserial.annotation.inclusionrule;

import java.util.Arrays;
import java.util.Map;

import hu.elte.eserial.annotation.IncludeElements;
import hu.elte.eserial.recursion.model.EserialElement;
import hu.elte.eserial.util.FieldUtils;

/**
 * Evaluates {@link hu.elte.eserial.annotation.IncludeElements} annotations.
 * @see AbstractInclusionRule
 */
public class IncludeElementsInclusionRule extends AbstractInclusionRule<IncludeElements> {

    IncludeElementsInclusionRule(IncludeElements annotation) {
        super(annotation);
    }

    /**
     * The field name is extracted from the {@code element}'s accessor method (even if it doesn't exist)
     * and used to decide whether the value should be included.
     * @param element {@inheritDoc}
     * @param objectMap {@inheritDoc}
     * @return {@code true} if the (virtual) field name is in the {@code annotation}'s property list
     */
    @Override
    public boolean evaluate(EserialElement element, Map<String, Object> objectMap) {
        String fieldName = FieldUtils.getFieldName(element.getAccessor());
        return Arrays.asList(this.annotation.value()).contains(fieldName);
    }

    @Override
    public boolean isInclusionRule() {
        return true;
    }
}
