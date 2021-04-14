package com.tpnet.patchbooheevip;

import android.content.Context;
import android.os.Bundle;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;


public class Main implements IXposedHookLoadPackage {

    public static Context mContext;

    public static String PackageName = "com.boohee.one";


    /**
     * 每个方法回调
     *
     * @param lpparam
     * @throws Throwable
     */
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

        if (lpparam.packageName.equals(PackageName)) {
            XposedBridge.log("开始Hook");

            initContext(lpparam);


            findAndHookMethod("com.one.common_library.common.UserInfoHelper", lpparam.classLoader, "isVip",
                    XC_MethodReplacement.returnConstant(true));

            findAndHookMethod("com.one.common_library.common.UserInfoHelper", lpparam.classLoader, "isEvaluate",
                    XC_MethodReplacement.returnConstant(true));

            findAndHookMethod("com.one.common_library.common.UserInfoHelper", lpparam.classLoader, "setVip", boolean.class,
                    XposedUtils.replaceArg(true));

            findAndHookMethod("com.one.common_library.common.UserInfoHelper", lpparam.classLoader, "setEvaluate", boolean.class,
                    XposedUtils.replaceArg(true));

            findAndHookMethod("com.boohee.one.app.tools.dietsport.camera.BaseCameraActivity", lpparam.classLoader, "getIsVip",
                    XC_MethodReplacement.returnConstant(true));

            findAndHookMethod("com.boohee.one.app.tools.food.ui.fragment.BaseCameraFragment", lpparam.classLoader, "getIsVip",
                    XC_MethodReplacement.returnConstant(true));


            findAndHookMethod("com.one.common_library.model.account.VipStatusV2", lpparam.classLoader, "getState",
                    XC_MethodReplacement.returnConstant("on_going"));
            findAndHookMethod("com.one.common_library.model.account.VipStatusV2", lpparam.classLoader, "is_member",
                    XC_MethodReplacement.returnConstant(true));

            findAndHookMethod("com.one.common_library.model.account.VipStatusV2", lpparam.classLoader, "setState", String.class,
                    XposedUtils.replaceArg("on_going"));

            findAndHookMethod("com.one.common_library.model.account.VipStatusV2", lpparam.classLoader, "set_member", boolean.class,
                    XposedUtils.replaceArg(true));


            findAndHookMethod("com.boohee.one.app.tools.baby.entity.BaseInfoResponseData", lpparam.classLoader, "component2",
                    XC_MethodReplacement.returnConstant("2"));
            findAndHookMethod("com.boohee.one.app.tools.baby.entity.BaseInfoResponseData", lpparam.classLoader, "getRole_type",
                    XC_MethodReplacement.returnConstant("2"));
            findAndHookMethod("com.boohee.one.app.tools.baby.entity.BaseInfoResponseData", lpparam.classLoader, "setRole_type", String.class,
                    XposedUtils.replaceArg("2"));


            findAndHookMethod("com.boohee.one.app.home.helper.SplashHelper", lpparam.classLoader, "onCreate", Bundle.class,
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            XposedHelpers.callMethod(param.thisObject, "startMainActivity");
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                        }
                    });
        }
    }


    private XC_MethodHook.Unhook mCxtHook = null;
    /**
     * 初始化Context
     *
     * @param lpparam 方法参数
     */
    private void initContext(XC_LoadPackage.LoadPackageParam lpparam) {

        if (mContext == null) {
            try {
                Class<?> ContextClass = XposedHelpers.findClass("android.content.ContextWrapper", lpparam.classLoader);

                mCxtHook = findAndHookMethod(ContextClass, "getApplicationContext", new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
                        mContext = (Context) param.getResult();
                        if (mCxtHook != null && mContext != null) {
                            mCxtHook.unhook();
                        }
                        XposedBridge.log("得到上下文");
                    }
                });
            } catch (Throwable t) {
                XposedBridge.log("获取上下文出错" + t);
            }
        }
    }


}
