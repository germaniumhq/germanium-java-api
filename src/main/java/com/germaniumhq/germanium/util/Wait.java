package com.germaniumhq.germanium.util;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

/**
 * A very simple wait condition to wait for closures to be
 * true.
 */
public class Wait {
    private final static Logger log = Logger.getLogger(Wait.class);
    public static final float DEFAULT_TIMEOUT_IN_SECONDS = 10f;

    private float timeout;

    private List<Supplier> whileNotConditions = new ArrayList<>();

    public Wait() {
        this(DEFAULT_TIMEOUT_IN_SECONDS);
    }

    public Wait(float timeout) {
        this.timeout = timeout;
    }

    /**
     * While not conditions.
     * @param conditions
     * @return
     */
    public Wait whileNot(Supplier...conditions) {
        whileNotConditions.addAll(Arrays.asList(conditions));

        return this;
    }

    public void waitFor(Supplier<?>...conditions) {
        long startingTime = System.currentTimeMillis();
        long currentTime = startingTime;
        long delta = (long) (timeout * 1000);

        if (delta < 0) {
            throw new IllegalArgumentException(
                    "You need to specify a timeout that is greater " +
                    "than 0. The timeout is expressed in seconds.");
        }

        boolean closureResult = false;

        while (currentTime - startingTime < delta) {
            long startingClosureEvalTime = currentTime;

            for (Supplier whileNotCondition : whileNotConditions) {
                try {
                    if (isTrue(whileNotCondition)) {
                        throw new IllegalArgumentException("Waiting failed since whileNot condition matched.");
                    }
                } catch (Exception e) {
                    throw new IllegalArgumentException("Waiting failed, since while_not condition raised exception", e);
                }
            }

            closureResult = isClosureTryCatch(conditions);

            if (closureResult) {
                break;
            }

            currentTime = System.currentTimeMillis();
            long endingClosureEvalTime = currentTime;

            long actualExecutionTime = endingClosureEvalTime -
                    startingClosureEvalTime;

            if (actualExecutionTime < 400) {
                try {
                    Thread.sleep(400 - actualExecutionTime);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e.getMessage(), e);
                }
            }

            if (currentTime - startingTime > 2000) {
                log.warn("Waiting takes more than 2 seconds");
            }
        }

        if (!closureResult) {
            throw new IllegalArgumentException("Waiting has failed");
        }
    }

    private boolean isClosureTryCatch(Supplier[] conditions) {
        for (Supplier condition : conditions) {
            try {
                if (isTrue(condition.get())) {
                    return true;
                }
            } catch (Exception e) {
                log.info("Waiting as false, since condition threw exception: " + e.getMessage());
                log.debug(e);
            }
        }

        return false;
    }

    private boolean isTrue(Supplier condition) {
        Object result = condition.get();

        return isTrue(result);
    }

    private boolean isTrue(Object result) {
        if (result == null) {
            return false;
        }

        if (result instanceof Supplier) {
            return isTrue(((Supplier)result).get());
        }

        if (result instanceof Boolean) {
            if (result == Boolean.FALSE) {
                return false;
            }
        }

        if (result instanceof String) {
            if (((String)result).isEmpty()) {
                return false;
            }
        }

        if (result instanceof Collection) {
            if (((Collection)result).isEmpty()) {
                return false;
            }
        }

        return true;
    }
}
