package pl.jjsolutions.ruleengine.model.condition;

public interface ConflictingRuleCondition<T extends Comparable<T>> extends RuleCondition<T> {
    Class<? extends Comparable> getValueType();
}
