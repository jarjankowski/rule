package pl.jjsolutions.ruleengine.model.condition.impl;

import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.jjsolutions.ruleengine.model.condition.ConflictingRuleCondition;
import pl.jjsolutions.ruleengine.model.rule.RuleEvaluationContext;

public class DefaultEqualsRuleCondition<T extends Comparable<T>> implements ConflictingRuleCondition<T> {

    private static final Logger LOG = LogManager.getLogger(DefaultEqualsRuleCondition.class);

    private String key;
    private T value;

    public DefaultEqualsRuleCondition(String key, T value) {
        Validate.notEmpty(key, "DefaultEqualsRuleCondition key must not be empty");
        Validate.notNull(value, "DefaultEqualsRuleCondition value must not be empty");
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public T getValue() {
        return value;
    }

    @SuppressWarnings("unchecked")
    public boolean matches(RuleEvaluationContext context) {
        boolean result = false;
        if (context.hasKey(key)) {
            Object contextValue = context.getValue(key);
            try {
                result = (contextValue != null && evaluateValueEquality((T) contextValue));
            } catch (ClassCastException cce) {
                LOG.error(String.format("Value %s of type %s found in context under %s key. Unable to evaluate equality because of type mismatch: %s",
                        contextValue, contextValue.getClass(), key, this.toString()));
            }
        }
        return result;
    }

    public boolean evaluateValueEquality(T aValue) {
        return value.compareTo(aValue) == 0;
    }

    public Class<? extends Comparable> getValueType() {
        return value.getClass();
    }

    @Override
    public String toString() {
        return "DefaultEqualsRuleCondition{" +
                "key='" + key + '\'' +
                ", value=" + value + ", valueType=" + value.getClass() +
                '}';
    }
}
