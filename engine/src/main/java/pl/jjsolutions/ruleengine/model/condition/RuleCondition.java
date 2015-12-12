package pl.jjsolutions.ruleengine.model.condition;

import pl.jjsolutions.ruleengine.model.rule.RuleEvaluationContext;

public interface RuleCondition<T> {
    boolean matches(RuleEvaluationContext context);
}
