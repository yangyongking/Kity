package me.windleafy.kity.android.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;

import java.util.List;

/**
 * Android跳转到应用商店详情页面
 * https://www.jianshu.com/p/81c3503e4a0f
 */
public class MarketKit {

    private MarketKit() {
        /**cannot be instantiated **/
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    //小米应用商店
    private static final String PACKAGE_MI_MARKET = "com.xiaomi.market";
    private static final String MI_MARKET_PAGE = "com.xiaomi.market.ui.AppDetailActivity";
    //魅族应用商店
    private static final String PACKAGE_MEIZU_MARKET = "com.meizu.mstore";
    private static final String MEIZU_MARKET_PAGE = "com.meizu.flyme.appcenter.activitys.AppMainActivity";
    //VIVO应用商店
    private static final String PACKAGE_VIVO_MARKET = "com.bbk.appstore";
    private static final String VIVO_MARKET_PAGE = "com.bbk.appstore.ui.AppStoreTabActivity";
    //OPPO应用商店
    private static final String PACKAGE_OPPO_MARKET = "com.oppo.market";
    private static final String OPPO_MARKET_PAGE = "a.a.a.aoz";
    //华为应用商店
    private static final String PACKAGE_HUAWEI_MARKET = "com.huawei.appmarket";
    private static final String HUAWEI_MARKET_PAGE = "com.huawei.appmarket.service.externalapi.view.ThirdApiActivity";
    //ZTE应用商店
    private static final String PACKAGE_ZTE_MARKET = "zte.com.market";
    private static final String ZTE_MARKET_PAGE = "zte.com.market.view.zte.drain.ZtDrainTrafficActivity";
    //360手机助手
    private static final String PACKAGE_360_MARKET = "com.qihoo.appstore";
    private static final String PACKAGE_360_PAGE = "com.qihoo.appstore.distribute.SearchDistributionActivity";
    //酷市场 -- 酷安网
    private static final String PACKAGE_COOL_MARKET = "com.coolapk.market";
    private static final String COOL_MARKET_PAGE = "com.coolapk.market.activity.AppViewActivity";
    //应用宝
    private static final String PACKAGE_TENCENT_MARKET = "com.tencent.android.qqdownloader";
    private static final String TENCENT_MARKET_PAGE = "com.tencent.pangu.link.LinkProxyActivity";
    //PP助手
    private static final String PACKAGE_ALI_MARKET = "com.pp.assistant";
    private static final String ALI_MARKET_PAGE = "com.pp.assistant.activity.MainActivity";
    //豌豆荚
    private static final String PACKAGE_WANDOUJIA_MARKET = "com.wandoujia.phoenix2";
    // 低版本可能是 com.wandoujia.jupiter.activity.DetailActivity
    private static final String WANDOUJIA_MARKET_PAGE = "com.pp.assistant.activity.PPMainActivity";
    //UCWEB
    private static final String PACKAGE_UCWEB_MARKET = "com.UCMobile";
    private static final String UCWEB_MARKET_PAGE = "com.pp.assistant.activity.PPMainActivity";


    // 进入应用市场详情页
    public static boolean gotoMarket(Context context, String packageName) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=" + packageName));
        String[] keys = getKeys(context);
        if (keys != null) {
            intent.setClassName(keys[0], keys[1]);
        }
        //修复某些老手机会因为找不到任何市场而报错
        if (isIntentAvailable(context, intent)) {
            context.startActivity(intent);
            return true;
        } else {
            //无应用商店
            return false;
        }
    }

    private static String[] getKeys(Context context) {
        String[] keys = new String[2];
        if (isPackageExist(context, PACKAGE_MI_MARKET)) {
            keys[0] = PACKAGE_MI_MARKET;
            keys[1] = MI_MARKET_PAGE;
        } else if (isPackageExist(context, PACKAGE_VIVO_MARKET)) {
            keys[0] = PACKAGE_VIVO_MARKET;
            keys[1] = VIVO_MARKET_PAGE;
        } else if (isPackageExist(context, PACKAGE_OPPO_MARKET)) {
            keys[0] = PACKAGE_OPPO_MARKET;
            keys[1] = OPPO_MARKET_PAGE;
        } else if (isPackageExist(context, PACKAGE_HUAWEI_MARKET)) {
            keys[0] = PACKAGE_HUAWEI_MARKET;
            keys[1] = HUAWEI_MARKET_PAGE;
        } else if (isPackageExist(context, PACKAGE_ZTE_MARKET)) {
            keys[0] = PACKAGE_ZTE_MARKET;
            keys[1] = ZTE_MARKET_PAGE;
        } else if (isPackageExist(context, PACKAGE_COOL_MARKET)) {
            keys[0] = PACKAGE_COOL_MARKET;
            keys[1] = COOL_MARKET_PAGE;
        } else if (isPackageExist(context, PACKAGE_360_MARKET)) {
            keys[0] = PACKAGE_360_MARKET;
            keys[1] = PACKAGE_360_PAGE;
        } else if (isPackageExist(context, PACKAGE_MEIZU_MARKET)) {
            keys[0] = PACKAGE_MEIZU_MARKET;
            keys[1] = MEIZU_MARKET_PAGE;
        } else if (isPackageExist(context, PACKAGE_TENCENT_MARKET)) {
            keys[0] = PACKAGE_TENCENT_MARKET;
            keys[1] = TENCENT_MARKET_PAGE;
        } else if (isPackageExist(context, PACKAGE_ALI_MARKET)) {
            keys[0] = PACKAGE_ALI_MARKET;
            keys[1] = ALI_MARKET_PAGE;
        } else if (isPackageExist(context, PACKAGE_WANDOUJIA_MARKET)) {
            keys[0] = PACKAGE_WANDOUJIA_MARKET;
            keys[1] = WANDOUJIA_MARKET_PAGE;
        } else if (isPackageExist(context, PACKAGE_UCWEB_MARKET)) {
            keys[0] = PACKAGE_UCWEB_MARKET;
            keys[1] = UCWEB_MARKET_PAGE;
        }
        if (TextUtils.isEmpty(keys[0])) {
            return null;
        } else {
            return keys;
        }
    }

    /**
     * @param context
     * @param packageName
     * @return
     * @Title isPackageExist
     * @Description .判断package是否存在
     * @date 2013年12月31日 上午9:49:59
     */
    private static boolean isPackageExist(Context context, String packageName) {
        PackageManager manager = context.getPackageManager();
        Intent intent = new Intent().setPackage(packageName);
        List<ResolveInfo> infos = manager.queryIntentActivities(intent,
                PackageManager.GET_INTENT_FILTERS);
        if (infos == null || infos.size() < 1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 检测 响应某个Intent的Activity 是否存在
     *
     * @param context
     * @param intent
     * @return
     */
    @SuppressLint("WrongConstant")
    private static boolean isIntentAvailable(Context context, Intent intent) {
        final PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent,
                PackageManager.GET_ACTIVITIES);
        return list.size() > 0;
    }

}