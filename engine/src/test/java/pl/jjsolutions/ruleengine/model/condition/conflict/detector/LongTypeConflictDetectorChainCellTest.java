package pl.jjsolutions.ruleengine.model.condition.conflict.detector;

import org.junit.Assert;
import org.junit.Test;
import pl.jjsolutions.ruleengine.model.condition.ConflictingRuleCondition;
import pl.jjsolutions.ruleengine.model.condition.impl.DefaultEqualsRuleCondition;
import pl.jjsolutions.ruleengine.model.condition.impl.InclusiveRangeRuleCondition;

public class LongTypeConflictDetectorChainCellTest {

    ConflictingRuleCondition<Long> cond1;
    ConflictingRuleCondition<Long> cond2;

    LongTypeConflictDetectorChainCell chainCell = new LongTypeConflictDetectorChainCell(null);

    @Test
    public void equalsRulesNoConflict() {
        cond1 = new DefaultEqualsRuleCondition<Long>("a", 5L);
        cond2 = new DefaultEqualsRuleCondition<Long>("a", 6L);

        Assert.assertTrue(Long.class.equals(chainCell.getProcessedType()));
        Assert.assertFalse(chainCell.isConflictBetween(cond1, cond2));
    }

    @Test
    public void equalsRulesConflict() {
        cond1 = new DefaultEqualsRuleCondition<Long>("a", 5L);
        cond2 = new DefaultEqualsRuleCondition<Long>("a", 5L);

        Assert.assertTrue(Long.class.equals(chainCell.getProcessedType()));
        Assert.assertTrue(chainCell.isConflictBetween(cond1, cond2));
    }

    @Test
    public void equalsAndInclusiveRangeRulesNoConflict_1() {
        cond1 = new InclusiveRangeRuleCondition<Long>("b", -44L, 21L);
        cond2 = new DefaultEqualsRuleCondition<Long>("b", 22L);
        Assert.assertFalse(chainCell.isConflictBetween(cond1, cond2));
    }

    @Test
    public void equalsAndInclusiveRangeRulesNoConflict_2() {
        cond1 = new InclusiveRangeRuleCondition<Long>("b", -44L, 21L);
        cond2 = new DefaultEqualsRuleCondition<Long>("b", -45L);
        Assert.assertFalse(chainCell.isConflictBetween(cond1, cond2));
    }

    @Test
    public void equalsAndInclusiveRangeRulesNoConflict_3() {
        cond1 = new DefaultEqualsRuleCondition<Long>("b", 122L);
        cond2 = new InclusiveRangeRuleCondition<Long>("b", -100L, 99L);
        Assert.assertFalse(chainCell.isConflictBetween(cond1, cond2));
    }

    @Test
    public void equalsAndInclusiveRangeRulesNoConflict_4() {
        cond1 = new DefaultEqualsRuleCondition<Long>("b", -101L);
        cond2 = new InclusiveRangeRuleCondition<Long>("b", -100L, 99L);
        Assert.assertFalse(chainCell.isConflictBetween(cond1, cond2));
    }

    @Test
    public void equalsAndInclusiveRangeRulesConflict_1() {
        cond1 = new DefaultEqualsRuleCondition<Long>("b", -101L);
        cond2 = new InclusiveRangeRuleCondition<Long>("b", -101L, 99L);
        Assert.assertTrue(chainCell.isConflictBetween(cond1, cond2));
    }

    @Test
    public void equalsAndInclusiveRangeRulesConflict_2() {
        cond1 = new DefaultEqualsRuleCondition<Long>("b", 90L);
        cond2 = new InclusiveRangeRuleCondition<Long>("b", -101L, 99L);
        Assert.assertTrue(chainCell.isConflictBetween(cond1, cond2));
    }

    @Test
    public void equalsAndInclusiveRangeRulesConflict_3() {
        cond1 = new DefaultEqualsRuleCondition<Long>("b", 99L);
        cond2 = new InclusiveRangeRuleCondition<Long>("b", -101L, 99L);
        Assert.assertTrue(chainCell.isConflictBetween(cond1, cond2));
    }

    @Test
    public void equalsAndInclusiveRangeRulesConflict_4() {
        cond1 = new InclusiveRangeRuleCondition<Long>("b", -101L, 99L);
        cond2 = new DefaultEqualsRuleCondition<Long>("b", 99L);
        Assert.assertTrue(chainCell.isConflictBetween(cond1, cond2));
    }

    @Test
    public void rangeAndRangeConflict_1() {
        cond1 = new InclusiveRangeRuleCondition<Long>("c", 20L, 40L);
        cond2 = new InclusiveRangeRuleCondition<Long>("c", 30L, 35L);
        Assert.assertTrue(chainCell.isConflictBetween(cond1, cond2));
    }

    @Test
    public void rangeAndRangeConflict_2() {
        cond1 = new InclusiveRangeRuleCondition<Long>("c", 20L, 40L);
        cond2 = new InclusiveRangeRuleCondition<Long>("c", 30L, 40L);
        Assert.assertTrue(chainCell.isConflictBetween(cond1, cond2));
    }

    @Test
    public void rangeAndRangeConflict_3() {
        cond1 = new InclusiveRangeRuleCondition<Long>("c", 20L, 40L);
        cond2 = new InclusiveRangeRuleCondition<Long>("c", 19L, 41L);
        Assert.assertTrue(chainCell.isConflictBetween(cond1, cond2));
    }

    @Test
    public void rangeAndRangeConflict_4() {
        cond1 = new InclusiveRangeRuleCondition<Long>("c", 19L, 41L);
        cond2 = new InclusiveRangeRuleCondition<Long>("c", 20L, 40L);
        Assert.assertTrue(chainCell.isConflictBetween(cond1, cond2));
    }

    @Test
    public void rangeAndRangeConflict_5() {
        cond1 = new InclusiveRangeRuleCondition<Long>("c", 19L, Long.MAX_VALUE);
        cond2 = new InclusiveRangeRuleCondition<Long>("c", 20L, Long.MAX_VALUE);
        Assert.assertTrue(chainCell.isConflictBetween(cond1, cond2));
    }

    @Test
    public void rangeAndRangeConflict_6() {
        cond1 = new InclusiveRangeRuleCondition<Long>("c", 20L, Long.MAX_VALUE);
        cond2 = new InclusiveRangeRuleCondition<Long>("c", 18L, Long.MAX_VALUE);
        Assert.assertTrue(chainCell.isConflictBetween(cond1, cond2));
    }

    @Test
    public void rangeAndRangeConflict_7() {
        cond1 = new InclusiveRangeRuleCondition<Long>("c", Long.MIN_VALUE, 12L);
        cond2 = new InclusiveRangeRuleCondition<Long>("c", Long.MIN_VALUE, 15L);
        Assert.assertTrue(chainCell.isConflictBetween(cond1, cond2));
    }

    @Test
    public void rangeAndRangeConflict_8() {
        cond1 = new InclusiveRangeRuleCondition<Long>("c", Long.MIN_VALUE, 15L);
        cond2 = new InclusiveRangeRuleCondition<Long>("c", Long.MIN_VALUE, 12L);
        Assert.assertTrue(chainCell.isConflictBetween(cond1, cond2));
    }

    @Test
    public void rangeAndRangeNoConflict_1() {
        cond1 = new InclusiveRangeRuleCondition<Long>("c", Long.MIN_VALUE, 15L);
        cond2 = new InclusiveRangeRuleCondition<Long>("c", 18L, Long.MAX_VALUE);
        Assert.assertFalse(chainCell.isConflictBetween(cond1, cond2));
    }

    @Test
    public void rangeAndRangeNoConflict_2() {
        cond1 = new InclusiveRangeRuleCondition<Long>("c", 1L, 15L);
        cond2 = new InclusiveRangeRuleCondition<Long>("c", 16L, Long.MAX_VALUE);
        Assert.assertFalse(chainCell.isConflictBetween(cond1, cond2));
    }

    @Test
    public void rangeAndRangeNoConflict_3() {
        cond1 = new InclusiveRangeRuleCondition<Long>("c", 1L, 15L);
        cond2 = new InclusiveRangeRuleCondition<Long>("c", 16L, 25L);
        Assert.assertFalse(chainCell.isConflictBetween(cond1, cond2));
    }

    @Test
    public void rangeAndRangeNoConflict_4() {
        cond1 = new InclusiveRangeRuleCondition<Long>("c", -100L, 100L);
        cond2 = new InclusiveRangeRuleCondition<Long>("c", -110L, -101L);
        Assert.assertFalse(chainCell.isConflictBetween(cond1, cond2));
    }

    @Test
    public void rangeAndRangeNoConflict_5() {
        cond1 = new InclusiveRangeRuleCondition<Long>("c", 1L, 15L);
        cond2 = new InclusiveRangeRuleCondition<Long>("c", -100L, -50L);
        Assert.assertFalse(chainCell.isConflictBetween(cond1, cond2));
    }
}