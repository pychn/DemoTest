package demo.py.com.demo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import demo.py.com.demo.R;

/**
 * Created by fly on 15-8-30.
 */
public class MyToggleBtn extends View implements View.OnClickListener {


    /**
     * 做为背景图的图片
     */
    private Bitmap backGround;
    /**
     * 滑动按钮的图片
     */
    private Bitmap slideBtn;
    private Paint paint;
    /**
     * 滑动按钮的左边距
     */
    private float slideLeft;

    public MyToggleBtn(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        backGround = BitmapFactory.decodeResource(getResources(), R.drawable.abc_ab_share_pack_mtrl_alpha);
        slideBtn = BitmapFactory.decodeResource(getResources(), R.drawable.abc_ab_share_pack_mtrl_alpha);

        slideLeftMax = backGround.getWidth() - slideBtn.getWidth();
        
	/*
     * 初始化画笔
	 */
        paint = new Paint();
        paint.setAntiAlias(true);// 打开抗矩齿
        
	/*
	 * 给当前view添加点击事件
	 */
        this.setOnClickListener(this);

    }

    @Override
    /**
     * 设置当前view的测量值大小
     */
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //获得view的测量值的大小，即view自己想要多大
        //        getMeasuredWidth();
        //获得view的真实的大小。
        //        getWidth();

        //设置当前view的测量值大小  与 背景图的大小，一致
        setMeasuredDimension(backGround.getWidth(), backGround.getHeight());
    }

    @Override
    /**
     * 绘制view的内容
     */
    protected void onDraw(Canvas canvas) {

        //绘制背景
        canvas.drawBitmap(backGround, 0, 0, paint);

        //绘制滑动按钮
        canvas.drawBitmap(slideBtn, slideLeft, 0, paint);

    }

    /**
     * 判断当前开关的状态
     */
    private boolean currState = false;

    @Override
	/*
	 * 响应view的点击事件
	 */
    public void onClick(View v) {

        if (isDrop) {// 如果发生了托动，就直接返回，
            return;
        }
        currState = !currState;
        flushState();

    }

    /**
     * 刷新状态
     */
    private void flushState() {
        if (currState) {//开的时候，左边距 = 背景的宽度 - 滑动按钮的宽度
            slideLeft = slideLeftMax;
        } else {
            slideLeft = 0;
        }

        flushView();
    }

    /**
     * down事件时的X坐标
     */
    private int firstX;
    /**
     * 上一个touch事件时的X坐标
     */
    private int lastX;
    /**
     * 滑动按钮，左边距的最大值
     */
    private int slideLeftMax;

    /**
     * 是否是托动，
     * 如果手指在屏幕上移动超过 10个像素，就认为是托动
     */
    private boolean isDrop;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 点击事件，其实是TouchEvent中的一种，只要发生 MotionEvent.UP 事件，系统就认为，发生了onclick这个动作
        super.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                firstX = lastX = (int) event.getX();

                isDrop = false;
                break;
            case MotionEvent.ACTION_MOVE:

                int distance = (int) (event.getX() - lastX);

                lastX = (int) event.getX();

                slideLeft += distance;

                //如果手指在屏幕上移动超过 10个像素，就认为是托动
                if (Math.abs(firstX - event.getX()) > 10) {
                    isDrop = true;
                }

                break;
            case MotionEvent.ACTION_UP:

                if (isDrop) {
                    //如果此时，滑动按钮的左边距 < 最大值的一半，应该是关的状态
                    if (slideLeft < slideLeftMax / 2) {
                        currState = false;
                    } else {
                        //否则，是开的状态
                        currState = true;
                    }

                    flushState();
                }
                break;
        }

        flushView();

        return true;
    }

    /**
     * 刷新页面
     */
    private void flushView() {
        if (slideLeft <= 0) {
            slideLeft = 0;
        }

        if (slideLeft > slideLeftMax) {
            slideLeft = slideLeftMax;
        }

        invalidate();
    }

}
