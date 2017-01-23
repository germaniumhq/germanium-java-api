package com.germaniumhq.germanium.wa;

public abstract class RunnableWorkaround {
    private Runnable defaultCode;

    public RunnableWorkaround(Runnable defaultCode) {
        this.defaultCode = defaultCode;
    }

    public void execute() {
        if (isApplicable()) {
            executeWorkAround();
            return;
        }

        defaultCode.run();
    }

    protected abstract void executeWorkAround();

    protected abstract boolean isApplicable();
}
