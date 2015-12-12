package pl.jjsolutions.ruleengine.model.condition.impl;

import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.jjsolutions.ruleengine.model.condition.ConflictingRuleCondition;
import pl.jjsolutions.ruleengine.model.rule.RuleEvaluationContext;

public class InclusiveRangeRuleCondition<T extends Comparable<T>> implements ConflictingRuleCondition<T> {

    private String key;
    private T minValue;
    private T maxValue;

    private static final Logger LOG = LogManager.getLogger(DefaultEqualsRuleCondition.class);

    public InclusiveRangeRuleCondition(String key, T minValue, T maxValue) {
        Validate.notBlank(key, "Key must be non empty when constructing InclusiveRangeRuleCondition");
        Validate.notNull(minValue, "Min value must be specified when constructing InclusiveRangeRuleCondition");
        Validate.notNull(maxValue, "Max value must be specified when constructing InclusiveRangeRuleCondition");
        Validate.isTrue(maxValue.compareTo(minValue) == 1, "Max value must be greater than min value");
        this.key = key;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public String getKey() {
        return key;
    }

    public T getMinValue() {
        return minValue;
    }

    public T getMaxValue() {
        return maxValue;
    }

    @SuppressWarnings("unchecked")
    public boolean matches(RuleEvaluationContext context) {
        boolean result = false;

        if (context.hasKey(key)) {
            Object contextValue = context.getValue(key);
            try {
                result = (contextValue != null && fitsInRange((T) contextValue));
            } catch(ClassCastException cce) {
                LOG.error(String.format("Unable to cast context value %s to required type %s", contextValue, getValueType()));
            }
        }
        return result;
    }

    private boolean fitsInRange(T value) {
        return (value.compareTo(minValue) == 1 && value.compareTo(maxValue) == -1);
    }

    public Class<? extends Comparable> getValueType() {
        Class<? extends Comparable> valueType;
        if(minValue != null) {
            valueType = minValue.getClass();
        } else {
            valueType = maxValue.getClass();
        }
        return valueType;
    }
}
