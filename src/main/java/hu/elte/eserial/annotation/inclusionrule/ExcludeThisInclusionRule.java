package hu.elte.eserial.annotation.inclusionrule;

import hu.elte.eserial.annotation.ExcludeThis;
import hu.elte.eserial.model.EserialElement;

/**
 * Evaluates {@link ExcludeThis} annotations.
 * @see AbstractInclusionRule
 */
public class ExcludeThisInclusionRule extends AbstractInclusionRule<ExcludeThis> {

    ExcludeThisInclusionRule(ExcludeThis annotation) {
        super(annotation);
    }

    /**
     * For any element having the {@link ExcludeThis} annotation, the element should be excluded.
     * @param element not used
     * @return {@code false}
     */
    @Override
    public boolean evaluate(EserialElement element) {
        return false;
    }

    @Override
    public boolean isInclusionRule() {
        return false;
    }
}
