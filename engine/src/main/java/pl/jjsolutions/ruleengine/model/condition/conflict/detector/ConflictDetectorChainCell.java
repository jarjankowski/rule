package pl.jjsolutions.ruleengine.model.condition.conflict.detector;

import pl.jjsolutions.ruleengine.model.condition.ConflictingRuleCondition;

public abstract class ConflictDetectorChainCell<T extends Comparable<T>> {

    public ConflictDetectorChainCell(ConflictDetectorChainCell successor) {
        this.successor = successor;
    }

    private ConflictDetectorChainCell successor;

    public abstract Class<? extends Comparable> getProcessedType();

    public boolean performConflictDetection(ConflictingRuleCondition c1, ConflictingRuleCondition c2) {
        if (getProcessedType().equals(c1.getValueType())
                && getProcessedType().equals(c2.getValueType())) {
            return isConflictBetween(c1, c2);
        } else {
            return successor.performConflictDetection(c1, c2);
        }
    }

    public abstract boolean isConflictBetween(ConflictingRuleCondition<T> c1, ConflictingRuleCondition<T> c2);

    protected boolean doEqual(T value1, T value2) {
        return value1.compareTo(value2) == 0;
    }

    protected boolean isGreaterThan(T value, T comparedTo) {
        return value.compareTo(comparedTo) == 1;
    }

    protected boolean isLessThan(T value, T comparedTo) {
        return value.compareTo(comparedTo) == -1;
    }

    protected boolean isWithinBordersInclusive(T value, T leftBorder, T rightBorder) {
        boolean greaterOrEqualsLeftBorder = value.compareTo(leftBorder) >= 0;
        boolean lessOrEqualRightBorder = value.compareTo(rightBorder) <= 0;
        return greaterOrEqualsLeftBorder && lessOrEqualRightBorder;
    }
}