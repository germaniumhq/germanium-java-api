package com.germaniumhq.germanium.wa;

import java.util.function.Supplier;

public class Workaround {
    public static void workaround(Supplier<Boolean> condition, Runnable waCode, Runnable defaultCode) {
        if (condition.get()) {
            waCode.run();
            return;
        }

        defaultCode.run();
    }
}
