package com.tpnet.patchbooheevip;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;


public class Main implements IXposedHookLoadPackage {

//    final Context[] mContext = {null};


    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

        if (lpparam.packageName.equals("com.boohee.one")) {

//            if(mContext[0] == null){
//                try {
//                    Class<?> ContextClass = XposedHelpers.findClass("android.content.ContextWrapper", lpparam.classLoader);
//                    findAndHookMethod(ContextClass, "getApplicationContext", new XC_MethodHook() {
//                        @Override
//                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                            super.afterHookedMethod(param);
//                            mContext[0] = (Context) param.getResult();
//                            XposedBridge.log("得到上下文");
//                        }
//                    });
//                } catch (Throwable t) {
//                    XposedBridge.log("获取上下文出错"+t);
//                }
//            }
//
//            XposedBridge.log("薄荷破解运行了");
//            if(mContext[0] != null){
//                Toast.makeText(mContext[0],"薄荷破解运行了2",Toast.LENGTH_SHORT).show();
//            }

            findAndHookMethod("com.one.common_library.common.UserInfoHelper", lpparam.classLoader, "isVip", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    XposedBridge.log("薄荷破解 UserInfoHelper返回会员为真");
                    param.setResult(true);
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
//                    Toast.makeText(mContext[0],"薄荷破解会员",Toast.LENGTH_SHORT).show();

                }
            });

            findAndHookMethod("com.one.common_library.common.UserInfoHelper", lpparam.classLoader, "setVip",boolean.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    param.args[0] = true;
                    XposedBridge.log("薄荷破解 UserInfoHelper设置会员为真");
                    param.setResult(true);
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                }
            });

            findAndHookMethod("com.boohee.one.app.tools.dietsport.camera.BaseCameraActivity", lpparam.classLoader, "getIsVip", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    XposedBridge.log("薄荷破解 BaseCameraActivity返回会员为真");
                    param.setResult(true);
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);

                }
            });
            findAndHookMethod("com.boohee.one.app.tools.food.ui.fragment.BaseCameraFragment", lpparam.classLoader, "getIsVip", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    XposedBridge.log("薄荷破解 BaseCameraFragment返回会员为真");
                    param.setResult(true);
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                }
            });
            findAndHookMethod("com.one.common_library.model.account.VipStatusV2", lpparam.classLoader, "getState", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    XposedBridge.log("薄荷破解 VipStatusV2返回on_going");
                    param.setResult("on_going");
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
//                    Toast.makeText(mContext[0],"薄荷破解会员",Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
}
