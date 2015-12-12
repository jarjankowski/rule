package pl.jjsolutions.ruleengine.model.condition.impl;

public class StringEqualsRuleCondition extends DefaultEqualsRuleCondition<String> {

    private boolean caseSensitive;

    public StringEqualsRuleCondition(String key, String value) {
        super(key, value);
    }

    public StringEqualsRuleCondition(String key, String value, boolean caseSensitive) {
        super(key, value);
        this.caseSensitive = caseSensitive;
    }

    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    public boolean evaluateValueEquality(String aValue) {
        return (caseSensitive ? getValue().equals(aValue) : getValue().equalsIgnoreCase(aValue));
    }
}
