package com.tpnet.patchbooheevip;

import de.robv.android.xposed.XC_MethodHook;

public class XposedUtils {


    public static XC_MethodHook replaceArg(final Object... args){
        return new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                param.args = args;
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
            }
        };
    }
}
