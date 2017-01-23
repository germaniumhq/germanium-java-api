package com.germaniumhq.germanium.wa;

import java.util.function.Supplier;

/**
 * A workaround that returns a value.
 *
 * @param <T>
 */
public abstract class SupplierWorkaround<T> {
    private Supplier<T> defaultCode;

    public SupplierWorkaround(Supplier<T> defaultCode) {
        this.defaultCode = defaultCode;
    }

    public T execute() {
        if (isApplicable()) {
            return executeWorkAround();
        }

        return defaultCode.get();
    }

    protected abstract T executeWorkAround();

    protected abstract boolean isApplicable();
}
