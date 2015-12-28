package pl.jjsolutions.ruleengine.model.rule;

import java.util.HashMap;
import java.util.Map;

public class MapBackedRuleEvaluationContext implements RuleEvaluationContext {

    private Map<String, Comparable> content;

    public MapBackedRuleEvaluationContext(Map<String, Comparable> content) {
        this.content = content;
    }

    public MapBackedRuleEvaluationContext() {
        content = new HashMap<String, Comparable>();
    }

    public Comparable getValue(String key) {
        return content.get(key);
    }

    public boolean hasKey(String key) {
        return content.containsKey(key);
    }

    public void putLongValue(String key, Long value) {
        content.put(key, value);
    }

    public void putStringValue(String key, String value) {
        content.put(key, value);
    }
}
