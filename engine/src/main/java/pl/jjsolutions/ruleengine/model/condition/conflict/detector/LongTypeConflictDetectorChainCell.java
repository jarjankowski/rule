package pl.jjsolutions.ruleengine.model.condition.conflict.detector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.jjsolutions.ruleengine.model.condition.ConflictingRuleCondition;
import pl.jjsolutions.ruleengine.model.condition.impl.DefaultEqualsRuleCondition;
import pl.jjsolutions.ruleengine.model.condition.impl.InclusiveRangeRuleCondition;

public class LongTypeConflictDetectorChainCell extends ConflictDetectorChainCell<Long> {

    private static final Logger LOG = LogManager.getLogger(LongTypeConflictDetectorChainCell.class);

    public LongTypeConflictDetectorChainCell(ConflictDetectorChainCell successor) {
        super(successor);
    }

    @Override
    public Class<? extends Comparable> getProcessedType() {
        return Long.class;
    }

    @Override
    public boolean isConflictBetween(ConflictingRuleCondition<Long> c1, ConflictingRuleCondition<Long> c2) {
        boolean conflictDetected = false;
        if(c1 instanceof DefaultEqualsRuleCondition) {
            conflictDetected = handleEqualsCondition((DefaultEqualsRuleCondition<Long>) c1, c2);
        } else if(c1 instanceof InclusiveRangeRuleCondition) {
            conflictDetected = handleInclusiveRangeCondition((InclusiveRangeRuleCondition<Long>) c1, c2);
        }
        return conflictDetected;
    }

    private boolean handleEqualsCondition(DefaultEqualsRuleCondition<Long> c1, ConflictingRuleCondition<Long> c2) {
        boolean result = false;
        if(c2 instanceof DefaultEqualsRuleCondition) {
            result = doEqual(c1.getValue(), ((DefaultEqualsRuleCondition<Long>) c2).getValue());
        } else if(c2 instanceof InclusiveRangeRuleCondition) {
            result = isWithinBordersInclusive(c1.getValue(), ((InclusiveRangeRuleCondition<Long>) c2).getMinValue(),
                    ((InclusiveRangeRuleCondition<Long>) c2).getMaxValue());
        }
        return result;
    }

    private boolean handleInclusiveRangeCondition(InclusiveRangeRuleCondition<Long> c1, ConflictingRuleCondition<Long> c2) {
        boolean result = false;
        if(c2 instanceof DefaultEqualsRuleCondition) {
            result = isWithinBordersInclusive(((DefaultEqualsRuleCondition<Long>) c2).getValue(), c1.getMinValue(), c1.getMaxValue());
        } else if(c2 instanceof InclusiveRangeRuleCondition) {
            boolean minValueWithinRange = isWithinBordersInclusive(c1.getMinValue(), ((InclusiveRangeRuleCondition<Long>) c2).getMinValue(),
                    ((InclusiveRangeRuleCondition<Long>) c2).getMaxValue());
            boolean maxValueWithinRange = isWithinBordersInclusive(c1.getMaxValue(), ((InclusiveRangeRuleCondition<Long>) c2).getMinValue(),
                    ((InclusiveRangeRuleCondition<Long>) c2).getMaxValue());
            boolean rangeWithinRange = isLessThan(c1.getMinValue(), ((InclusiveRangeRuleCondition<Long>) c2).getMinValue()) &&
                    isGreaterThan(c1.getMaxValue(), ((InclusiveRangeRuleCondition<Long>) c2).getMaxValue());
            result = minValueWithinRange || maxValueWithinRange || rangeWithinRange;
        }
        return result;
    }
}
