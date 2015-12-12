package pl.jjsolutions.ruleengine.model.condition.impl;

import pl.jjsolutions.ruleengine.model.condition.RuleCondition;
import pl.jjsolutions.ruleengine.model.rule.RuleEvaluationContext;

public class AnyCondition implements RuleCondition {

    private String key;

    public AnyCondition(String key) {
        this.key = key;
    }

    public boolean matches(RuleEvaluationContext context) {
        return context.hasKey(key);
    }
}
