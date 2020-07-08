package me.windleafy.kity.android.wiget.debug;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import me.windleafy.kity.R;
import me.windleafy.kity.java.bean.SlowRun;
import me.windleafy.kity.java.timer.Timery;


/**
 * 调试View
 * 使用：  usage()
 */
public class DebugView extends LinearLayout {

    private Context mContext;

    private ViewGroup mRoot;
    private TextView mTitle;
    private TextView mText;
    private View mSubTextLayout;
    private TextView mSubText1;
    private TextView mSubText2;
    private TextView mSubText3;
    private ScrollView mScrollTextLayout;
    private TextView mScrollText;
    private View mEditLayout;
    private EditText mEdit1;
    private EditText mEdit2;
    private View mButtonLayout;
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    private View mButtonLayout2;
    private Button mButton21;
    private Button mButton22;
    private Button mButton23;
    private Button mButton24;
    private View mSubButtonLayout;
    private Button mSubButton1;
    private Button mSubButton2;
    private Button mSubButton3;
    private Button mSubButton4;
    private Button mSubButton5;
    private Button mSubButton6;
    private Button mSubButton7;
    private Button mSubButton8;
    private DebugClickListener mDebugClickListener;
    private DebugClickListener mDebugClickListener2;
    private DebugSubClickListener mDebugSubClickListener;
    private LayoutInflater inflater;
    private boolean isUse = false;
    private SlowRun mReriodRun;//间隔运行
    private StringBuilder mScrollString;
    private Timery mTimery;

    //---------------------------------Activity---------------------------------

    /**
     * 根视图加入
     *
     * @param activity
     */
    public DebugView(Activity activity) {
        super(activity);
        //加载位置为手机全屏（被标题栏遮挡）
        activity.addContentView(this, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mContext = activity;
        init();
    }

    /**
     * 在视图viewGroup中加入
     *
     * @param activity
     * @param viewGroupId
     */
    public DebugView(Activity activity, int viewGroupId) {
        this(activity, (ViewGroup) activity.findViewById(viewGroupId));
    }

    /**
     * 在视图viewGroup中加入
     *
     * @param activity
     * @param viewGroup
     */
    public DebugView(Activity activity, ViewGroup viewGroup) {
        super(activity);
        viewGroup.addView(this, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mContext = activity;
        init();
    }

    //---------------------------------Fragment---------------------------------

    /**
     * 根视图加入
     *
     * @param fragment
     */
    public DebugView(Fragment fragment) {
        super(fragment.getActivity());
        //加载位置为手机全屏（被标题栏遮挡）
        fragment.getActivity().addContentView(this, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mContext = fragment.getActivity();
        init();
    }

    /**
     * 在视图viewGroup中加入
     *
     * @param fragment
     * @param viewGroupId 容器id
     */
    public DebugView(Fragment fragment, int viewGroupId) {
        this(fragment, (ViewGroup) fragment.getView().findViewById(viewGroupId));
    }

    /**
     * 在视图viewGroup中加入
     *
     * @param fragment
     * @param viewGroup
     */
    public DebugView(Fragment fragment, ViewGroup viewGroup) {
        super(fragment.getActivity());
        viewGroup.addView(this, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mContext = fragment.getActivity();
        init();
    }

    //--------------------------------------------------------------------------


    public void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.kity_inflate_debug_view, this);
        initView();
        initEvent();
    }

    public void initView() {
        mRoot = (ViewGroup) findViewById(R.id.debug_root);
        mTitle = (TextView) findViewById(R.id.debug_title);
        mText = (TextView) findViewById(R.id.debug_text);

        mSubTextLayout = findViewById(R.id.debug_sub_text_layout);
        mSubText1 = (TextView) findViewById(R.id.debug_sub_text_1);
        mSubText2 = (TextView) findViewById(R.id.debug_sub_text_2);
        mSubText3 = (TextView) findViewById(R.id.debug_sub_text_3);

        mScrollTextLayout = (ScrollView) findViewById(R.id.debug_scroll_layout);
        mScrollText = (TextView) findViewById(R.id.debug_scroll_text);

        mEditLayout = findViewById(R.id.debug_edit_layout);
        mEdit1 = (EditText) findViewById(R.id.debug_edit_1);
        mEdit2 = (EditText) findViewById(R.id.debug_edit_2);

        mButtonLayout = findViewById(R.id.debug_button_layout);
        mButton1 = (Button) findViewById(R.id.debug_button1);
        mButton2 = (Button) findViewById(R.id.debug_button2);
        mButton3 = (Button) findViewById(R.id.debug_button3);
        mButton4 = (Button) findViewById(R.id.debug_button4);

        mButtonLayout2 = findViewById(R.id.debug_button_layout2);
        mButton21 = (Button) findViewById(R.id.debug_button21);
        mButton22 = (Button) findViewById(R.id.debug_button22);
        mButton23 = (Button) findViewById(R.id.debug_button23);
        mButton24 = (Button) findViewById(R.id.debug_button24);

        mSubButtonLayout = findViewById(R.id.debug_subbutton_layout);
        mSubButton1 = (Button) findViewById(R.id.debug_subbutton1);
        mSubButton2 = (Button) findViewById(R.id.debug_subbutton2);
        mSubButton3 = (Button) findViewById(R.id.debug_subbutton3);
        mSubButton4 = (Button) findViewById(R.id.debug_subbutton4);
        mSubButton5 = (Button) findViewById(R.id.debug_subbutton5);
        mSubButton6 = (Button) findViewById(R.id.debug_subbutton6);
        mSubButton7 = (Button) findViewById(R.id.debug_subbutton7);
        mSubButton8 = (Button) findViewById(R.id.debug_subbutton8);
    }

    public void initEvent() {
        //Button
        mButton1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDebugClickListener != null) {
                    mDebugClickListener.onClick(v, 1);
                }
            }
        });
        mButton2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDebugClickListener != null) {
                    mDebugClickListener.onClick(v, 2);
                }
            }
        });
        mButton3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDebugClickListener != null) {
                    mDebugClickListener.onClick(v, 3);
                }
            }
        });
        mButton4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDebugClickListener != null) {
                    mDebugClickListener.onClick(v, 4);
                }
            }
        });
        //Button2
        mButton21.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDebugClickListener2 != null) {
                    mDebugClickListener2.onClick(v, 1);
                }
            }
        });
        mButton22.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDebugClickListener2 != null) {
                    mDebugClickListener2.onClick(v, 2);
                }
            }
        });
        mButton23.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDebugClickListener2 != null) {
                    mDebugClickListener2.onClick(v, 3);
                }
            }
        });
        mButton24.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDebugClickListener2 != null) {
                    mDebugClickListener2.onClick(v, 4);
                }
            }
        });
        //SubButton
        mSubButton1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDebugSubClickListener != null) {
                    mDebugSubClickListener.onClick(v, 1);
                }
            }
        });
        mSubButton2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDebugSubClickListener != null) {
                    mDebugSubClickListener.onClick(v, 2);
                }
            }
        });
        mSubButton3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDebugSubClickListener != null) {
                    mDebugSubClickListener.onClick(v, 3);
                }
            }
        });
        mSubButton4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDebugSubClickListener != null) {
                    mDebugSubClickListener.onClick(v, 4);
                }
            }
        });
        mSubButton5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDebugSubClickListener != null) {
                    mDebugSubClickListener.onClick(v, 5);
                }
            }
        });
        mSubButton6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDebugSubClickListener != null) {
                    mDebugSubClickListener.onClick(v, 6);
                }
            }
        });
        mSubButton7.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDebugSubClickListener != null) {
                    mDebugSubClickListener.onClick(v, 7);
                }
            }
        });
        mSubButton8.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDebugSubClickListener != null) {
                    mDebugSubClickListener.onClick(v, 8);
                }
            }
        });
        //长按清空
        mScrollText.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                clearScrollText();
                return true;
            }
        });
    }

    public DebugView clearScrollText() {
        mScrollString.delete(0, mScrollString.length());
        mScrollText.setText("");
        return this;
    }

    public DebugView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DebugView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 设置是否使用
     *
     * @param bool
     */
    public DebugView show(boolean bool) {
        isUse = bool;
        mRoot.setVisibility(bool ? VISIBLE : GONE);
        return this;
    }


    /**
     * 显示编辑栏
     *
     * @return
     */
    public DebugView showEditLayout() {
        if (isUse)
            mEditLayout.setVisibility(VISIBLE);
        return this;
    }


    /**
     * 设置点击监听
     *
     * @param debugClickListener
     * @return
     */
    public DebugView setDebugClickListener(DebugClickListener debugClickListener, String... texts) {
        if (isUse) {
            mButtonLayout.setVisibility(VISIBLE);
            mDebugClickListener = debugClickListener;
            setButtonText(texts);
        }
        return this;
    }

    /**
     * 设置按钮文字
     */
    public DebugView setButtonText(String... texts) {
        switch (texts.length) {
            case 4:
                mButton4.setText(texts[3]);
            case 3:
                mButton3.setText(texts[2]);
            case 2:
                mButton2.setText(texts[1]);
            case 1:
                mButton1.setText(texts[0]);
        }
        return this;
    }


    /**
     * 设置点击监听2
     *
     * @param debugClickListener
     * @return
     */
    public DebugView setDebugClickListener2(DebugClickListener debugClickListener, String... texts) {
        if (isUse) {
            mButtonLayout2.setVisibility(VISIBLE);
            mDebugClickListener2 = debugClickListener;
            setButtonText2(texts);
        }
        return this;
    }

    /**
     * 设置按钮文字2
     */
    public DebugView setButtonText2(String... texts) {
        switch (texts.length) {
            case 4:
                mButton24.setText(texts[3]);
            case 3:
                mButton23.setText(texts[2]);
            case 2:
                mButton22.setText(texts[1]);
            case 1:
                mButton21.setText(texts[0]);
        }
        return this;
    }

    /**
     * 设置次按钮点击监听
     *
     * @param debugSubClickListener
     * @return
     */
    public DebugView setDebugSubClickListener(DebugSubClickListener debugSubClickListener) {
        if (isUse) {
            mSubButtonLayout.setVisibility(VISIBLE);
            mDebugSubClickListener = debugSubClickListener;
        }
        return this;
    }


    public DebugView setTitle(String text) {
        if (isUse) {
            mTitle.setVisibility(VISIBLE);
            mTitle.setText(text);
        }
        return this;
    }

    public DebugView setText(String text) {
        if (isUse) {
            mText.setVisibility(VISIBLE);
            mText.setText(text);
        }
        return this;
    }

    public DebugView setText1(String text) {
        if (isUse) {
            mSubTextLayout.setVisibility(VISIBLE);
            mSubText1.setVisibility(VISIBLE);
            mSubText1.setText(text);
        }
        return this;
    }

    public DebugView setText2(String text) {
        if (isUse) {
            mSubTextLayout.setVisibility(VISIBLE);
            mSubText2.setVisibility(VISIBLE);
            mSubText2.setText(text);
        }
        return this;
    }

    public DebugView setText3(String text) {
        if (isUse) {
            mSubTextLayout.setVisibility(VISIBLE);
            mSubText3.setVisibility(VISIBLE);
            mSubText3.setText(text);
        }
        return this;
    }


    /**
     * 打印到scrollview中
     *
     * @param text
     * @return
     */
    public void setScrollText(final String text) {
        if (isUse) {
            setScrollLayout();
            mScrollTextLayout.post(new Runnable() {
                @Override
                public void run() {
                    mScrollString.append(text).append("\n");
                    mScrollText.setText(mScrollString.toString());
                    //滚到底部
                    mScrollTextLayout.fullScroll(ScrollView.FOCUS_DOWN);
                }
            });
        }
    }

    /**
     * 打印到scrollview中
     *
     * @param text    文字
     * @param colorId 字体颜色
     * @return
     */
    private void setScrollText(final String text, final int colorId) {
        if (isUse) {
            setScrollLayout();
            mScrollTextLayout.post(new Runnable() {
                @Override
                public void run() {
                    mScrollString.append(text + "\n");
                    mScrollText.setText(mScrollString.toString());
                    mScrollText.setTextColor(mContext.getResources().getColor(colorId));
                    //滚到底部
                    mScrollTextLayout.fullScroll(ScrollView.FOCUS_DOWN);
                }
            });
        }
    }

    /**
     * 定时器
     */
    private void startTimer() {
        if(mTimery == null || mTimery.isRunning()){
            mTimery = new Timery();
            mTimery.setTask(new Timery.Task() {
                @Override
                public void period(long time, int num) {
                    mScrollTextLayout.fullScroll(ScrollView.FOCUS_DOWN);
                }
            });
            mTimery.setPeriod(100);
            mTimery.start();
        }
    }

    /**
     * 慢速打印，适用于大量log数量的情况，没到时间的打印被丢弃
     *
     * @param text
     */
    public DebugView setScrollTextSlow(final String text) {
        if (isUse) {
            setScrollLayout();
            //间隔运行
            if (mReriodRun != null) {
                mReriodRun.execute(new Runnable() {
                    @Override
                    public void run() {
                        setScrollText(text);
                    }
                });
            }
        }
        return this;
    }

    /**
     * 显示Scroll控件
     */
    @SuppressLint("ClickableViewAccessibility")
    private void setScrollLayout() {
        if (mScrollTextLayout.getVisibility() != VISIBLE) {
            mScrollTextLayout.setVisibility(VISIBLE);
            mScrollTextLayout.setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(mTimery.isRunning())
                        mTimery.stop();
                    return false;
                }
            });
            mScrollString = new StringBuilder();
            mReriodRun = new SlowRun(1000);
            startTimer();
        }else {
            if(!mTimery.isRunning()){
                mTimery.restart();
            }
        }
    }

    public String getEdit1() {
        return mEdit1.getText().toString();
    }

    public void setEdit1(String edit1) {
        if (isUse)
            mEdit1.setText(edit1);
    }

    public String getEdit2() {
        return mEdit2.getText().toString();
    }

    public void setEdit2(String edit2) {
        if (isUse)
            mEdit2.setText(edit2);
    }

    /**
     * index 1-n
     */
    public interface DebugClickListener {
        void onClick(View v, int index);
    }

    /**
     * index 1-n
     */
    public interface DebugSubClickListener {
        void onClick(View v, int index);
    }

    /**
     * 使用方法
     */
    private class usage {

        //使用
//        protected void initDebug() {
//            debugLayout = new DebugView(this, R.id.layout_container);
//            debugLayout.initCenter(true);
//            debugLayout.setVisibility(View.VISIBLE);
//            //        debugLayout.showEditLayout();
//
//            //Button
//            //        debugLayout.setButtonText("TOP", "CENTER", "BOTTOM", "VIEW");
//            debugLayout.setDebugClickListener(new DebugView.DebugClickListener() {
//                @Override
//                public void onClick(View v, int index) {
//                    Toaster.initCenter("button index = " + index);
//                    switch (index) {
//                        case 1:
//                            break;
//                        case 2:
//                            break;
//                        case 3:
//                            break;
//                        case 4:
//                            break;
//                    }
//                }
//            });
//
//            debugLayout.setDebugClickListener2(new DebugView.DebugClickListener() {
//                @Override
//                public void onClick(View v, int index) {
//                    Toaster.initCenter("button2 index = " + index);
//                    switch (index) {
//                        case 1:
//                            break;
//                        case 2:
//                            break;
//                        case 3:
//                            break;
//                        case 4:
//                            break;
//                    }
//                }
//            });
//        }


    }//usage end


}
