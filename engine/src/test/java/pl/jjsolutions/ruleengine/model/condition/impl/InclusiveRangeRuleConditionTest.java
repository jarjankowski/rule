package pl.jjsolutions.ruleengine.model.condition.impl;

import org.junit.Assert;
import org.junit.Test;
import pl.jjsolutions.ruleengine.model.condition.ConflictingRuleCondition;
import pl.jjsolutions.ruleengine.model.rule.MapBackedRuleEvaluationContext;
import pl.jjsolutions.ruleengine.model.rule.RuleEvaluationContext;

import java.util.HashMap;
import java.util.Map;

public class InclusiveRangeRuleConditionTest {
    private InclusiveRangeRuleCondition cond1;

    @Test(expected = NullPointerException.class)
    public void createWithoutKey() {
        new InclusiveRangeRuleCondition(null, "a", "b");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createWithEmptyKey() {
        new InclusiveRangeRuleCondition<String>("", "a", "b");
    }

    @Test(expected = NullPointerException.class)
    public void createWithoutMinValue() {
        new InclusiveRangeRuleCondition<Long>("aaa", null, 34L);
    }

    @Test(expected = NullPointerException.class)
    public void createWithoutMaxValue() {
        new InclusiveRangeRuleCondition<Long>("aaa", 1L, null);
    }

    @Test
    public void createLongConditionSuccess() {
        new InclusiveRangeRuleCondition<Long>("aaa", 1L, 2L);
    }

    @Test
    public void testGetValueTypeForLongCondition() {
        ConflictingRuleCondition<Long> c = new InclusiveRangeRuleCondition<Long>("a", 3L, 7L);
        Assert.assertEquals(Long.class, c.getValueType());
    }

    @Test
    public void matchesSuccessfully() {
        Map<String, Comparable> map = new HashMap<String, Comparable>();
        map.put("ab", 3L);
        map.put("cd", 4L);
        map.put("ef", 5L);
        RuleEvaluationContext context = new MapBackedRuleEvaluationContext(map);
        InclusiveRangeRuleCondition condition = new InclusiveRangeRuleCondition("ab", 3L, 100L);
        Assert.assertTrue(condition.matches(context));
    }

    @Test
    public void testLongNoMatch() {
        Map<String, Comparable> map = new HashMap<String, Comparable>();
        map.put("ab", 3L);
        RuleEvaluationContext context = new MapBackedRuleEvaluationContext(map);
        InclusiveRangeRuleCondition condition = new InclusiveRangeRuleCondition("ab", 4L, 100L);
        Assert.assertFalse(condition.matches(context));
    }

    @Test
    public void testLongNoMatch2() {
        Map<String, Comparable> map = new HashMap<String, Comparable>();
        map.put("ab", 8L);
        RuleEvaluationContext context = new MapBackedRuleEvaluationContext(map);
        InclusiveRangeRuleCondition condition = new InclusiveRangeRuleCondition("xyz", 4L, 100L);
        Assert.assertFalse(condition.matches(context));
    }

    @Test
    public void testLongMatch() {
        Map<String, Comparable> map = new HashMap<String, Comparable>();
        map.put("ab", -8L);
        RuleEvaluationContext context = new MapBackedRuleEvaluationContext(map);
        InclusiveRangeRuleCondition condition = new InclusiveRangeRuleCondition("ab", -40L, -2L);
        Assert.assertTrue(condition.matches(context));
    }

    @Test
    public void testMatchFailedWhenContextTypeDiffer() {
        Map<String, Comparable> map = new HashMap<String, Comparable>();
        map.put("ab", "-8");
        RuleEvaluationContext context = new MapBackedRuleEvaluationContext(map);
        InclusiveRangeRuleCondition condition = new InclusiveRangeRuleCondition("ab", -40L, -2L);
        Assert.assertFalse(condition.matches(context));
    }
}