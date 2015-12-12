package pl.jjsolutions.ruleengine.model.rule;

import java.util.List;

import pl.jjsolutions.ruleengine.model.condition.RuleCondition;
import pl.jjsolutions.ruleengine.model.rule.value.RuleValue;

public class ConjunctionRule<T> implements Rule<T> {

    private RuleValue<T> ruleValue;
    private String ruleId;
    private List<RuleCondition> conditions;

    public RuleValue<T> evaluate(RuleEvaluationContext context) {
        RuleValue<T> result = ruleValue;
        for(RuleCondition rc: conditions) {
            if(!rc.matches(context)) {
                result = null;
                break;
            }
        }
        return result;
    }

    public List<RuleCondition> getConditions() {
        return conditions;
    }
}
