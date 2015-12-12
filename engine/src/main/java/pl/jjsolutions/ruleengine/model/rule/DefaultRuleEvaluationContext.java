package pl.jjsolutions.ruleengine.model.rule;

import java.util.Map;

public class DefaultRuleEvaluationContext implements RuleEvaluationContext {

    private Map<String, Object> content;

    public DefaultRuleEvaluationContext(Map<String, Object> content) {
        this.content = content;
    }

    public Object getValue(String key) {
        return content.get(key);
    }

    public boolean hasKey(String key) {
        return content.containsKey(key);
    }
}
