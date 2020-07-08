package me.windleafy.kity.android.utils.view;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import me.windleafy.kity.R;
import me.windleafy.kity.android.utils.ContextKit;
import me.windleafy.kity.android.utils.InflaterKit;
import me.windleafy.kity.android.utils.LogKit;
import me.windleafy.kity.android.utils.ViewGroupKit;
import me.windleafy.kity.java.collection.MapList;
import me.windleafy.kity.java.judge.JudgeKit;

/**
 * 在目标View前后或者ViewGroup中添加删除新的View
 */
public class TargetView {


    private static final String TAG = "TargetView";

    private static final int TAG_ID_KEY;
    private static final int TAG_RES_ID_KEY;
    private static final int TAG_CODE_ID_KEY;
    private static final int TAG_TYPE_KEY;

//    static {
//        final int KEY = new Random().nextInt();
//        TAG_ID_KEY = KEY + 1;
//        TAG_RES_ID_KEY = KEY + 2;
//        TAG_CODE_ID_KEY = KEY + 3;
//        TAG_TYPE_KEY = KEY + 4;
//    }

    static {
        final int KEY = R.integer.target_view_tag;
        TAG_ID_KEY = KEY + 1;
        TAG_RES_ID_KEY = KEY + 2;
        TAG_CODE_ID_KEY = KEY + 3;
        TAG_TYPE_KEY = KEY + 4;
    }

    private boolean hasParent;//添加了父控件

    public int size() {
        return mDefaultMapList.size(MapListKey.All) + mOriginalMapList.size(MapListKey.All);
    }

    public void print() {
        ViewGroupKit.printAllChild(mParent);
    }

    /**
     * 添加类型
     * 【添加新ResView后，TargetView仍然可见，TargetView与ResView同级】
     * HEAD,   //添加到TargetView所在layout首部
     * TAIL,   //添加到TargetView所在layout尾部
     * BEFORE, //添加到TargetView所在layout，在TargetView之前
     * AFTER,  //添加到TargetView所在layout，在TargetView之后
     * <p>
     * 【添加新ResView后，TargetView不再可见，TargetView上级，ResView下级】
     * REPLACE,//替换TargetView，并且ResView只会存在一个，新的ResView会替换老的ResView
     * COVER,  //替换TargetView，并且相同的ResView只会存在一个，后添加的ResView位置靠后
     * OVERLAP //替换TargetView，ResView无限制，后添加的ResView位置靠后
     */
    public enum AddType {
        //添加新ResView后，TargetView仍然可见，TargetView与ResView同级
        HEAD,   //TargetView所在layout首部
        TAIL,   //TargetView所在layout尾部
        BEFORE, //TargetView所在layout中添加，在TargetView之前
        AFTER,  //TargetView所在layout中添加，在TargetView之后
        //添加新ResView后，TargetView不再可见，TargetView上级，ResView下级
        REPLACE,//替换TargetView，并且ResView只会存在一个，新的ResView会替换老的ResView
        COVER,  //替换TargetView，并且ResView如果相同resId则只会存在一个，后添加的ResView位置靠后
        OVERLAP, //替换TargetView，对ResView无限制，后添加的ResView位置靠后
    }

    /**
     * 删除类型
     */
    public enum RemoveType {
        FIRST,
        LAST,
        ALL
    }


    /**
     * 添加父类类型
     */
    public enum LayoutType {
        FRAME,
        LINEAR
    }

    private MapList<MapListKey, View> mDefaultMapList = new MapList<>();
    private MapList<MapListKey, View> mOriginalMapList = new MapList<>();

    /**
     * 添加父类类型
     */
    public enum MapListKey {
        All,
        Head, Tail, Before, After,
        Replace, Cover, Overlap
    }

    private Activity mActivity;
    private Fragment mFragment;

    private LinkedList<View> mChild;

    private LinkedList<View> mChildHead;
    private LinkedList<View> mChildTail;
    private LinkedList<View> mChildBefore;
    private LinkedList<View> mChildAfter;

    private LinkedList<View> mChildReplace;
    private LinkedList<View> mChildCover;
    private LinkedList<View> mChildOverlap;


    private View mTargetView;
    private View mOriginalTargetView;
    //    private ViewGroup mOriginalParent;
    private ViewGroup mParent;
    //    private ViewGroup mGrandpa;
//    private ViewGroup mContainer;
    private int mTargetId;
    private int mTargetIndex;

    private int mVisib;


    /**
     * 构造
     */

    public TargetView(Activity activity, int targetId) {
        mActivity = activity;
        mTargetView = mActivity.findViewById(targetId);
        mTargetId = targetId;
        init();
    }

    public TargetView(Activity activity, View targetView) {
        mActivity = activity;
        mTargetView = targetView;
        init();
    }

    public TargetView(Fragment fragment, int targetId) {
        mFragment = fragment;
        mTargetView = mFragment.getView().findViewById(targetId);
        mTargetId = targetId;
        init();
    }

    public TargetView(Fragment fragment, View targetView) {
        mFragment = fragment;
        mTargetView = targetView;
        init();
    }

    private void init() {
        if (mTargetView == null) return;

        mVisib = mTargetView.getVisibility();

        mParent = (ViewGroup) mTargetView.getParent();
//        mOriginalParent = mParent;

        mChild = new LinkedList<>();
        mChildHead = new LinkedList<>();
        mChildTail = new LinkedList<>();
        mChildBefore = new LinkedList<>();
        mChildAfter = new LinkedList<>();
        mChildReplace = new LinkedList<>();
        mChildCover = new LinkedList<>();
        mChildOverlap = new LinkedList<>();
    }

    public View getTargetView() {
        return mTargetView;
    }

    public int getId() {
        return mTargetId;
    }

    public int getCode() {
        return mTargetView.hashCode();
    }

    private List<View> getChildList(MapList<MapListKey, View> mapList, AddType addType) {
        switch (addType) {
            case HEAD:
                return mapList.getList(MapListKey.Head);
            case TAIL:
                return mapList.getList(MapListKey.Tail);
            case BEFORE:
                return mapList.getList(MapListKey.Before);
            case AFTER:
                return mapList.getList(MapListKey.After);
            case REPLACE:
                return mapList.getList(MapListKey.Replace);
            case COVER:
                return mapList.getList(MapListKey.Cover);
            case OVERLAP:
                return mapList.getList(MapListKey.Overlap);
            default:
                return null;
        }
    }

    private boolean setTagView(View view, int key, String value) {
        Object resTag = view.getTag(key);
        if (resTag == null || resTag.equals("")) {
            view.setTag(key, value);
            return true;
        } else {
            return false;
        }
    }

    private void setResIdViewTag(View resView, String tag) {
        resView.setTag(TAG_RES_ID_KEY, tag);
        resView.setTag(TAG_ID_KEY, tag);
    }

    private void setCodeIdViewTag(View resView, String tag) {
        resView.setTag(TAG_CODE_ID_KEY, tag);
        resView.setTag(TAG_ID_KEY, tag);
    }


    private String getViewTag(View view, int key) {
        Object resTag = view.getTag(key);
        if (resTag == null || resTag.equals("")) {
            return null;
        } else {
            return (String) resTag;
        }
    }


    private MapList<AddType, String> mTimeMapList = new MapList<>();

    /**
     * view添加删除次数
     */
    protected int getViewTimes(int resId, AddType addType) {
        View resView = InflaterKit.inflate(mActivity, mFragment, resId);
        if (resView == null) return 0;
        setResIdViewTag(resView, String.valueOf(resId));
        return getViewTimes(resView, addType);
    }

    protected int getViewTimes(View resView, AddType addType) {
        int count = 0;
        String resTag = getViewTag(resView, TAG_ID_KEY);
        Iterator<String> iterator = mTimeMapList.iterator(addType);
        while (iterator.hasNext()) {
            String tag = iterator.next();
            if (resTag.equals(tag)) {
                count++;
            }
        }
        return count;
    }

    protected void addViewTimes(View resView, AddType addType) {
        mTimeMapList.addValue(addType, getViewTag(resView, TAG_ID_KEY));
    }

    protected void removeViewTimes(View resView, AddType addType) {
        mTimeMapList.removeValue(addType, getViewTag(resView, TAG_ID_KEY));
    }

    protected void removeViewTimes(int resId, AddType addType) {
        mTimeMapList.removeValue(addType, String.valueOf(resId));
    }

    /**
     * 添加view，父控件不变，但参考目标可能变化，
     */

    public void addView(int resId, AddType addType) {
        addView(resId, addType, null, null);
    }

    public void addView(int resId, AddType addType, View.OnClickListener listener) {
        addView(resId, addType, null, listener);
    }

    public void addView(int resId, AddType addType, ViewGroup.LayoutParams params, View.OnClickListener listener) {
        View resView = InflaterKit.inflate(mActivity, mFragment, resId);
        if (resView == null) return;
        setResIdViewTag(resView, String.valueOf(resId));
        addView(resView, addType, params, listener);
    }

    public void addView(@NonNull View resView, AddType addType) {
        addView(resView, addType, null, null);
    }

    public void addView(@NonNull View resView, AddType addType, View.OnClickListener listener) {
        addView(resView, addType, null, listener);
    }

    public void addView(@NonNull View resView, AddType addType, ViewGroup.LayoutParams params, View.OnClickListener listener) {
        //同一个view对象只能添加一个，添加多个报错
        if (mDefaultMapList.containValue(MapListKey.All, resView)) {
            removeView(resView, addType);
//            return;//最开始是不做处理，但是第二次显示空图片时不能显示，改为先删除再添加（TYPE=REPLACE）
        }
        String resId = getViewTag(resView, TAG_RES_ID_KEY);
        if (TextUtils.isEmpty(resId)) {
            setCodeIdViewTag(resView, String.valueOf(resView.hashCode()));
        }
        if (listener != null)
            resView.setOnClickListener(listener);

        resView.setTag(TAG_TYPE_KEY, addType);

        addViewTimes(resView, addType);//添加计数

        addViewToTarget(resView, addType, params, false);
    }


    /**
     * 添加view，参考目标不变,父控件可能变化
     */

    public void addViewToOriginal(int resId, AddType addType) {
        addViewToOriginal(resId, addType, null, null);
    }

    public void addViewToOriginal(int resId, AddType addType, View.OnClickListener listener) {
        addViewToOriginal(resId, addType, null, listener);
    }

    public void addViewToOriginal(int resId, AddType addType, ViewGroup.LayoutParams params, View.OnClickListener listener) {
        View resView = InflaterKit.inflate(mActivity, mFragment, resId);
        if (resView == null) return;
        setResIdViewTag(resView, String.valueOf(resId));
        addViewToOriginal(resView, addType, params, listener);
    }

    public void addViewToOriginal(@NonNull View resView, AddType addType) {
        addViewToOriginal(resView, addType, null, null);
    }

    public void addViewToOriginal(@NonNull View resView, AddType addType, View.OnClickListener listener) {
        addViewToOriginal(resView, addType, null, listener);
    }

    public void addViewToOriginal(@NonNull View resView, AddType addType, ViewGroup.LayoutParams params, View.OnClickListener listener) {
        //同一个view对象只能添加一个，添加多个报错
        if (mOriginalMapList.containValue(MapListKey.All, resView)) {
            removeView(resView, addType);
//            return;//最开始是不做处理，但是第二次显示空图片时不能显示，改为先删除再添加（TYPE=REPLACE）
        }
        String resId = getViewTag(resView, TAG_RES_ID_KEY);
        if (TextUtils.isEmpty(resId)) {
            setCodeIdViewTag(resView, String.valueOf(resView.hashCode()));
        }
        if (listener != null)
            resView.setOnClickListener(listener);

        resView.setTag(TAG_TYPE_KEY, addType);

        addViewTimes(resView, addType);//添加计数

        addViewToTarget(resView, addType, params, true);
    }


    /**
     * 添加到Target
     *
     * @param resView
     * @param addType
     * @param params
     * @param isAddToOriginal 是否添加到原始mTargetView，受addParent方法影响
     */
    private void addViewToTarget(View resView, AddType addType, ViewGroup.LayoutParams params, boolean isAddToOriginal) {

        View target = hasParent && isAddToOriginal ? mOriginalTargetView : mTargetView;

        MapList<MapListKey, View> mapList = getMapList(isAddToOriginal);

        if (target == null) return;

        ViewGroup parent = (ViewGroup) target.getParent();


        if (params == null) {
            params = target.getLayoutParams();
        }
//        ViewGroup.LayoutParams targetViewLayoutParams = target.getLayoutParams();
        mTargetIndex = parent.indexOfChild(target);

        switch (addType) {
            case HEAD:
                mapList.addValue(MapListKey.All, resView);
                mapList.addValue(MapListKey.Head, resView);
                parent.addView(resView, 0, params);
                break;
            case TAIL:
                mapList.addValue(MapListKey.All, resView);
                mapList.addValue(MapListKey.Tail, resView);
                parent.addView(resView, parent.getChildCount(), params);
                break;
            case BEFORE:
                mapList.addValue(MapListKey.All, resView);
                mapList.addValue(MapListKey.Before, resView);
                parent.addView(resView, mTargetIndex, params);
                break;
            case AFTER:
                mapList.addValue(MapListKey.All, resView);
                mapList.addValue(MapListKey.After, resView);
                parent.addView(resView, mTargetIndex + 1, params);
                break;
            case REPLACE:
                //先清空原有view
                List<View> replaceList = mapList.getList(MapListKey.Replace);
                if (replaceList.size() > 0) {
                    for (View view : replaceList) {
                        mapList.getList(MapListKey.All).remove(view);
                        removeChildView(view);
                    }
                    replaceList.clear();
                }
                mapList.addValue(MapListKey.All, resView);
                mapList.addValue(MapListKey.Replace, resView);
                //隐藏targetView
                target.setVisibility(View.GONE);
                mTargetIndex = parent.indexOfChild(target);
                parent.addView(resView, mTargetIndex + mapList.getList(MapListKey.Replace).size(), params);
                break;
            case COVER:
                //resview相同则跳过
                if (mapList.getList(MapListKey.Cover).contains(resView) && mapList.getList(MapListKey.Cover).equals(resView))
                    return;
                mapList.addValue(MapListKey.All, resView);
                mapList.addValue(MapListKey.Cover, resView);
                //隐藏targetView
                target.setVisibility(View.GONE);
                //加入resView
                parent.addView(resView, mTargetIndex + mapList.getList(MapListKey.Cover).size(), params);
                break;
            case OVERLAP:
                mapList.addValue(MapListKey.All, resView);
                mapList.addValue(MapListKey.Overlap, resView);
                //隐藏targetView
                target.setVisibility(View.GONE);
                //加入resView
                parent.addView(resView, mTargetIndex + mapList.getList(MapListKey.Overlap).size(), params);
                break;
        }
    }


    /**
     * 移除view
     */

    public void removeView(int resId, AddType addType, RemoveType removeType) {
        View resView = InflaterKit.inflate(mActivity, mFragment, resId);
        if (resView == null) return;
        setResIdViewTag(resView, String.valueOf(resId));
        removeView(resView, addType, removeType);
    }

    public void removeView(View resView, AddType addType, RemoveType removeType) {
        String resId = getViewTag(resView, TAG_RES_ID_KEY);
        if (TextUtils.isEmpty(resId)) {
            resView.setTag(TAG_CODE_ID_KEY, resView.hashCode());
        }
        removeViewFromTarget(resView, addType, removeType, false);
    }

    public void removeView(int resId, AddType addType) {
        View resView = InflaterKit.inflate(mActivity, mFragment, resId);
        if (resView == null) return;
        setResIdViewTag(resView, String.valueOf(resId));
        removeView(resView, addType);
    }

    public void removeView(View resView, AddType addType) {
        String resId = getViewTag(resView, TAG_RES_ID_KEY);
        if (TextUtils.isEmpty(resId)) {
            resView.setTag(TAG_CODE_ID_KEY, resView.hashCode());
        }
        removeViewFromTarget(resView, addType, RemoveType.ALL, false);
    }

    /**
     * 移除view
     */

    public void removeViewFromOriginal(int resId, AddType addType, RemoveType removeType) {
        View resView = InflaterKit.inflate(mActivity, mFragment, resId);
        if (resView == null) return;
        setResIdViewTag(resView, String.valueOf(resId));
        removeViewFromOriginal(resView, addType, removeType);
    }

    public void removeViewFromOriginal(View resView, AddType addType, RemoveType removeType) {
        String resId = getViewTag(resView, TAG_RES_ID_KEY);
        if (TextUtils.isEmpty(resId)) {
            resView.setTag(TAG_CODE_ID_KEY, resView.hashCode());
        }
        removeViewFromTarget(resView, addType, removeType, true);
    }

    public void removeViewFromOriginal(int resId, AddType addType) {
        View resView = InflaterKit.inflate(mActivity, mFragment, resId);
        if (resView == null) return;
        setResIdViewTag(resView, String.valueOf(resId));
        removeViewFromOriginal(resView, addType);
    }

    public void removeViewFromOriginal(View resView, AddType addType) {
        String resId = getViewTag(resView, TAG_RES_ID_KEY);
        if (TextUtils.isEmpty(resId)) {
            resView.setTag(TAG_CODE_ID_KEY, resView.hashCode());
        }
        removeViewFromTarget(resView, addType, RemoveType.ALL, true);
    }


    private void removeViewFromTarget(View resView, AddType addType, RemoveType removeType, boolean isRemoveFromOriginal) {
        MapList<MapListKey, View> mapList = getMapList(isRemoveFromOriginal);
        List<View> childList = getChildList(mapList, addType);
        if (childList == null) return;
        if (removeType == RemoveType.LAST) Collections.reverse(childList);
        removeViewFromTarget(mapList, childList, resView, addType, removeType);
        if (removeType == RemoveType.LAST) Collections.reverse(childList);
    }

    /**
     * 是否
     */
    private MapList<MapListKey, View> getMapList(boolean isOriginal) {
        return hasParent && isOriginal ? mOriginalMapList : mDefaultMapList;
    }

    /**
     * 删除子view
     */
    private void removeViewFromTarget(MapList<MapListKey, View> mapList, List<View> childList, View resView, AddType addType, RemoveType removeType) {
        if (mTargetView == null) return;
        String resTag = getViewTag(resView, TAG_ID_KEY);
        if (resTag == null) return;
        Iterator<View> iterator = childList.iterator();
        while (iterator.hasNext()) {
            View child = iterator.next();
            String childTag = getViewTag(child, TAG_ID_KEY);
            if (childTag == null) return;
            //找到
            if (resTag.equals(childTag)) {

                removeViewTimes(resView, addType);//移除计数

                removeChildView(child);//ViewGroup中删除

                mapList.removeValue(MapListKey.All, child);//集合中删除

                iterator.remove();//子集合中删除

                //判断是否还有子view TODO 此处可能有没有考虑到的地方
                if (childList.size() == 0 && JudgeKit.contain(addType, AddType.REPLACE, AddType.COVER, AddType.OVERLAP)) {
                    mTargetView.setVisibility(mVisib);
                }
                //只删除一个
                if (removeType != RemoveType.ALL) {
                    return;
                }
            }
        }
    }


    /**
     * 删除子view
     */
//    public void removeView(AddType addType) {
//        removeChildList(getChildList(addType));
//    }
//
//    private void removeChildList(List<View> childList) {
//        if (mTargetView == null) return;
//        for (View child : childList) {
//            //移除view
//            mParent.removeView(child);
//            //移除集合
//            mChild.remove(child);
//        }
//        childList.clear();
//        if (mChildReplace.size() == 0 && mChildCover.size() == 0 && mChildOverlap.size() == 0) {
//            mTargetView.setVisibility(mVisib);
//        }
//    }

    private void removeChildView(View view) {
        if (mTargetView == null) return;
        mParent.removeView(view);//移除view
        if (hasParent) ((ViewGroup) mTargetView).removeView(view);
    }


    protected View inflate(int id) {
        if (mActivity != null) {
            return LayoutInflater.from(mActivity).inflate(id, null);
        }
        if (mFragment != null && mFragment.getActivity() != null) {
            return LayoutInflater.from(mFragment.getContext()).inflate(id, null);
        }
        return null;
    }

    /**
     * 底层添加Layout
     * <p>
     * 注意：
     * 添加完成后，调用addView的目标参考变成了mTargetView的父控件，即刚刚添加的Layout
     * 如果还想以mTargetView作为目标参考，则调用addViewToOriginal
     */

    public void addParent(LayoutType layout) {

        if (mTargetView == null) return;

        //先限制添加父层个数为1个
        if (hasParent) return;

//        mGrandpa = mParent;
        ViewGroup tempTargetView = null;
        ViewGroup.LayoutParams layoutParams = null;
        switch (layout) {
            case FRAME:
                tempTargetView = new FrameLayout(ContextKit.get(mActivity, mFragment));
                layoutParams = new FrameLayout.LayoutParams(-1, -1);
                break;
            case LINEAR:
                tempTargetView = new LinearLayout(ContextKit.get(mActivity, mFragment));
                ((LinearLayout) tempTargetView).setOrientation(LinearLayout.VERTICAL);
                layoutParams = new LinearLayout.LayoutParams(-1, -2);
                break;
        }

        //保存位置
        mTargetIndex = mParent.indexOfChild(mTargetView);
        mParent.removeView(mTargetView);

        ViewGroup.LayoutParams parentParams = mTargetView.getLayoutParams();
        tempTargetView.setLayoutParams(parentParams);
        mTargetView.setLayoutParams(layoutParams);

        tempTargetView.addView(mTargetView);
        mParent.addView(tempTargetView, mTargetIndex);

        //保存对象
        mOriginalTargetView = mTargetView;
        mTargetView = tempTargetView;

        hasParent = true;

    }

    public void removeParent() {

        if (mTargetView == null) return;
//        if (mGrandpa == null) return;
        if (!hasParent) return;

//        ViewGroup.LayoutParams parentChildParams = mTargetView.getLayoutParams();
//        mOriginalTargetView.setLayoutParams(parentChildParams);

        //保存位置
        mTargetIndex = mParent.indexOfChild(mTargetView);

        ((ViewGroup) mTargetView).removeView(mOriginalTargetView);
        mParent.removeView(mTargetView);

        //保留状态
        ViewGroup.LayoutParams targetParams = mTargetView.getLayoutParams();
        mOriginalTargetView.setLayoutParams(targetParams);
        int visible = mTargetView.getVisibility();
        mOriginalTargetView.setVisibility(visible);

        //切换对象
        mTargetView = mOriginalTargetView;
        mOriginalTargetView = null;

        mParent.addView(mTargetView, mTargetIndex);

        hasParent = false;

    }

    public boolean hasParent() {
        return hasParent;
    }

//    public int getViewCount(int resId, AddType addType) {
//        View resView = InflaterKit.inflate(mActivity, mFragment, resId);
//        if (resView == null) return 0;
//        setResIdViewTag(resView, String.valueOf(resId));
//        return getViewCount(resView, addType);
//    }
//
//    public int getViewCount(View resView, AddType addType) {
//        String resId = getViewTag(resView, TAG_RES_ID_KEY);
//        if (TextUtils.isEmpty(resId)) {
//            resView.setTag(TAG_CODE_ID_KEY, resView.hashCode());
//        }
//        return getViewCountFromTarget(resView, addType);
//    }
//
//    private int getViewCountFromTarget(View resView, AddType addType) {
//        return getViewCountFromChildList(getChildList(mapList, addType), resView);
//    }

    private int getViewCountFromChildList(List<View> childList, View resView) {
        if (mTargetView == null) return 0;
        String resTag = getViewTag(resView, TAG_ID_KEY);
        if (resTag == null) return 0;
        int count = 0;
        Iterator<View> iterator = childList.iterator();
        while (iterator.hasNext()) {
            View child = iterator.next();
            String childTag = getViewTag(child, TAG_ID_KEY);
            if (childTag == null) return 0;
            if (resTag.equals(childTag)) {
                count++;
            }
        }
        return count;
    }

}