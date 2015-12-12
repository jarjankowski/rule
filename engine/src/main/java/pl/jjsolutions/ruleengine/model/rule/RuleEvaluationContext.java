package pl.jjsolutions.ruleengine.model.rule;

public interface RuleEvaluationContext {
    Object getValue(String key);
    boolean hasKey(String key);
}
