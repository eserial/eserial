package hu.elte.eserial.annotation.inclusionrule;

import hu.elte.eserial.annotation.MaxDepth;
import hu.elte.eserial.recursion.model.EserialElement;
import hu.elte.eserial.util.MapUtils;
import hu.elte.eserial.util.TypeUtils;

import java.util.Map;

/**
 * Evaluates {@link MaxDepth} annotations.
 * @see AbstractInclusionRule
 */
public class MaxDepthInclusionRule extends AbstractInclusionRule<MaxDepth> {

    MaxDepthInclusionRule(MaxDepth annotation) {
        super(annotation);
    }

    /**
     * The {@code objectMap}'s depth and the {@code element}'s value is checked to
     * decide whether the value should be included.
     * @param element {@inheritDoc}
     * @param objectMap {@inheritDoc}
     * @return {@code true} if one of these is true:
     * <ul>
     *     <li>the {@code objectMap}'s depth is less than the {@link MaxDepth}'s</li>
     *     <li>the {@code objectMap}'s depth is equal to the {@link MaxDepth}'s and the value is null
     *     or a "simple object" meaning it can be serialized inline</li>
     * </ul>
     *
     * @see TypeUtils#isSimpleType(Class)
     */
    @Override
    public boolean evaluate(EserialElement element, Map<String, Object> objectMap) {
        int currentDepth = MapUtils.getDepth(objectMap);
        if (currentDepth < this.annotation.value()) {
            return true;
        }
        else if (currentDepth == this.annotation.value()) {
            return element.getValue() == null || TypeUtils.isSimpleType(element.getValue().getClass());
        }
        else {
            return false;
        }
    }

    @Override
    public boolean isInclusionRule() {
        return false;
    }
}
