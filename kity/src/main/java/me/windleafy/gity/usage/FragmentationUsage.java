package me.windleafy.gity.usage;

/**
 * 简介：https://github.com/YoKeyword/Fragmentation/blob/master/README_CN.md
 * API: https://github.com/YoKeyword/Fragmentation/wiki/2.-API
 */
public interface FragmentationUsage {

    /**
     * 前言：核心概括
     * loadRootX()系列方法，操作的对象是 孩子Fragment，为避免被强杀重启后重复load，建议在findChildFragment(ChildFragment.class)==null情况下才load；
     * startX()，popX()，find/getX()系列方法，操作的对象是 兄弟Fragment；
     * popChildX()，find/getChildX()系列方法，操作的对象是 孩子Fragment。
     * <p>
     * Application初始化API
     * Fragmentation.builder()
     * // 设置 栈视图 模式为 悬浮球模式   SHAKE: 摇一摇唤出   NONE：隐藏
     * .stackViewMode(Fragmentation.BUBBLE)
     * .debug(BuildConfig.DEBUG)
     * // 在遇到After onSaveInstanceState时，不会抛出异常，会回调到下面的ExceptionHandler
     * .handleException(new ExceptionHandler() {
     *
     * @Override public void onException(Exception e) {
     * // 建议在该回调处上传至我们的Crash监测服务器
     * // 以Bugtags为例子: 手动把捕获到的 Exception 传到 Bugtags 后台。
     * // Bugtags.sendException(e);
     * }
     * })
     * .install();
     */
    void overview();


    /**
     * SupportActivity独有API
     * // 当Fragment根布局没有设定background属性时,
     * // Fragmentation默认使用Theme的android:windowbackground作为Fragment的背景,
     * // 可以通过该方法改变Fragment背景。
     * setDefaultFragmentBackground(@DrawableRes int backgroundRes);
     */
    void activity();


    /**
     * 装载根Fragment，一般在findChildFragment(ChildFragment.class)==null时load
     * <p>
     * // 装载根Fragment, 即Activity内的第一个Fragment 或 Fragment内的第一个子Fragment
     * loadRootFragment(int containerId, SupportFragment toFragment)
     * loadRootFragment(int containerId, SupportFragment toFragment, boolean addToBackStack, boolean allowEnterAnim)
     * <p>
     * // 装载多个根Fragment，用于同级Fragment的场景，详情见新Demo的MainActivity
     * loadMultipleRootFragment(int containerId, int showPosition, SupportFragment... toFragments);
     * <p>
     * 同级Fragment场景下的切换
     * // show一个Fragment，hide一个Fragment； 主要用于类似微信主页那种 切换tab的情况
     * showHideFragment(SupportFragment showFragment, SupportFragment hideFragment);
     */
    void load();

    /**
     * 启动Fragment
     * 如果在Activity使用，则本质是activity.getSupportFragmentManager().getTopFragment().start(f)；
     * <p>
     * // 启动新的Fragment，启动者和被启动者是在同一个栈的
     * start(SupportFragment fragment)
     * <p>
     * // 以某种启动模式，启动新的Fragment
     * start(SupportFragment fragment, int launchMode)
     * <p>
     * // 启动新的Fragment，并能接收到新Fragment的数据返回
     * startForResult(SupportFragment fragment,int requestCode)
     * <p>
     * // 启动目标Fragment，并关闭当前Fragment
     * startWithPop(SupportFragment fragment)
     * <p>
     * // 启动目标Fragment，并关闭targetFragment之上的Fragments
     * startWithPopTo(SupportFragment fragment, Class targetFragment, boolean includeTargetFragment)
     * <p>
     * // 1.0.0 New:  你可以使用extraTransaction() + start() 来实现上面的各种startXX()设置更多功能
     * supportFragment.extraTransaction()
     * .setTag(tag)  // 自定义tag
     * .addSharedElement(xx).setLaunchMode(SINGLETASK).withPop(true).forResult(1)
     * .start()
     * .popTo(tag, includeTagFragment)
     * //.dontAddToBackStack()
     * //.add()
     * //.remove(f) ...
     */
    void start();

    /**
     * 出栈
     * <p>
     * // 出栈当前Fragment(在当前Fragment所在栈内pop)
     * pop();
     * <p>
     * // 出栈targetFragment之上的所有Fragments
     * popTo(Class targetFragment, boolean includeTargetFragment);
     * <p>
     * // 如果想出栈后，紧接着.beginTransaction()开始一个新事务，请使用下面的方法：
     * // 该方法可以自定义出栈动画，可以让动画看起来更自然，如果对动画无要求，也可以使用popTo() + 事务来执行
     * popTo(Class targetFragment, boolean includeTargetFragment, Runnable afterTransaction, int animation)
     * <p>
     * 下面的方法是SupportFragment才有的，操作目标是子Fragment：
     * popChild();
     * popToChild(Class fragmentClass, boolean includeSelf);
     * popToChild(Class fragmentClass, boolean includeSelf, Runnable afterTransaction)
     * popToChild(Class fragmentClass, boolean includeSelf, Runnable afterTransaction,int popAnim)
     */
    void pop();


    /**
     * 查找Fragment
     * <p>
     * // 获取所在栈内的栈顶Fragment
     * getTopFragment();
     * <p>
     * // 获取当前Fragment所在栈内的前一个Fragment
     * getPreFragment();
     * <p>
     * // 通过class获取所在栈内的某个Fragment
     * findFragment(Class fragmentClass);
     * <p>
     * 下面的方法是SupportFragment才有的，从子栈内查找：
     * ````java
     * getTopChildFragment();
     * findChildFragment(Class fragmentClass);
     */
    void find();

    /**
     * 输入法相关
     * 因为Fragment被出栈时，不会自动隐藏软键盘，以及弹出软键盘有些麻烦，故提供下面2个方法
     * <p>
     * // 隐藏软键盘 一般用在hide时
     * hideSoftInput();
     * <p>
     * // 显示软键盘，调用该方法后，会在onPause时自动隐藏软键盘
     * showSoftInput(View view);
     */
    void keyboard();

}
