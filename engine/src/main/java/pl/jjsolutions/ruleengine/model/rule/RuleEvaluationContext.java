package pl.jjsolutions.ruleengine.model.rule;

public interface RuleEvaluationContext {
    Comparable getValue(String key);
    boolean hasKey(String key);
}
