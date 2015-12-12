package pl.jjsolutions.ruleengine.model.rule;

import java.util.List;

import pl.jjsolutions.ruleengine.model.condition.RuleCondition;
import pl.jjsolutions.ruleengine.model.rule.value.RuleValue;

public interface Rule<RESULT> {
    RuleValue<RESULT> evaluate(RuleEvaluationContext context);
    List<RuleCondition> getConditions();
}
