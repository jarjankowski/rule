package pl.jjsolutions.ruleengine.model.condition.impl;

import org.junit.Test;

public class InclusiveRangeRuleConditionTest {
    private InclusiveRangeRuleCondition cond1;

    @Test(expected = NullPointerException.class)
    public void createWithoutKey() {
        new InclusiveRangeRuleCondition(null, "a", "b");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createWithEmptyKey() {
        new InclusiveRangeRuleCondition("", "a", "b");
    }
}