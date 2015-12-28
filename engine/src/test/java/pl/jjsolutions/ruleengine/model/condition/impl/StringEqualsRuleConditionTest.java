package pl.jjsolutions.ruleengine.model.condition.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.jjsolutions.ruleengine.model.rule.MapBackedRuleEvaluationContext;
import pl.jjsolutions.ruleengine.model.rule.RuleEvaluationContext;

import java.util.HashMap;
import java.util.Map;

public class StringEqualsRuleConditionTest {
    StringEqualsRuleCondition ruleCondition;
    RuleEvaluationContext context;

    @Before
    public void init() {
        Map<String, Comparable> map = new HashMap<String, Comparable>();
        map.put("a1", "Value1");
        map.put("a2", "Value2");
        map.put("a3", "Value3");
        map.put("a4", 5L);
        context = new MapBackedRuleEvaluationContext(map);
    }

    @Test
    public void testMatchSuccess1() {
        ruleCondition = new StringEqualsRuleCondition("a1", "Value1");
        Assert.assertTrue(ruleCondition.matches(context));
    }

    @Test
    public void testMatchSuccess2() {
        ruleCondition = new StringEqualsRuleCondition("a1", "vaLUe1");
        Assert.assertTrue(ruleCondition.matches(context));
    }

    @Test
    public void testMatchSuccess3() {
        ruleCondition = new StringEqualsRuleCondition("a2", "Value2", true);
        Assert.assertTrue(ruleCondition.matches(context));
    }

    @Test
    public void testMatchFail1() {
        ruleCondition = new StringEqualsRuleCondition("a2", "value2", true);
        Assert.assertFalse(ruleCondition.matches(context));
    }

    @Test
    public void testMatchFail() {
        ruleCondition = new StringEqualsRuleCondition("a4", "5", true);
        Assert.assertFalse(ruleCondition.matches(context));
    }
}