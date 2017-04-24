package com.gz.hkjs.app.ui.main.linechart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.gz.hkjs.app.R;
import com.gz.hkjs.app.bean.TrainData;

import java.util.List;

/**
 * Created by Administrator on 2017/4/18.
 */

public class BrokenLineCusVisitView extends View {


    private int width;
    private int heigh;

    //网格的宽度与高度
    private int gridspace_width;
    private int gridspace_heigh;
    //底部空白的高度
    private int brokenline_bottom;


    //灰色背景的画笔
    private Paint mPaint_bg;
    //灰色网格的画笔
    private Paint mPaint_gridline;
    //文本数据的画笔
    private Paint mPaint_text;

    //折线圆点的蓝色背景
    private Paint mPaint_point_bg;
    //折线圆点的白色表面
    private Paint mPaint_point_sur;
    //阴影路径的画笔
    private Paint mPaint_path;
    //折线的画笔
    private Paint mPaint_brokenline;

    private Paint mPaint_up;
    //路径
    private Path mpath = new Path();
    //客户拜访的折线（TrainData）数据
    private List<TrainData> mdata;

    public BrokenLineCusVisitView(Context context) {
        this(context, null);
    }

    public BrokenLineCusVisitView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BrokenLineCusVisitView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inite(context);
    }

    private void inite(Context context) {

        mPaint_bg = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint_bg.setColor(Color.argb(0xff, 0xff, 0x83, 0x6A));

        mPaint_gridline = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint_gridline.setColor(Color.argb(0xff, 0xce, 0xCB, 0xce));

        mPaint_brokenline = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint_brokenline.setColor(Color.argb(0xff, 0xFC, 0x4C, 0x4F));
        mPaint_brokenline.setTextSize(40);
        mPaint_brokenline.setTextAlign(Paint.Align.CENTER);

        mPaint_point_bg = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint_point_bg.setColor(Color.argb(0xff, 0xFC, 0x4C, 0x4F));
        //注意path的画笔的透明度已经改变了
        mPaint_path = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint_path.setColor(Color.argb(0x33, 0x91, 0xC8, 0xD6));


        mPaint_point_sur = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint_point_sur.setColor(Color.WHITE);

        mPaint_up = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint_up.setColor(Color.WHITE);

        invalidate();
    }

    //data的set/get方法，用于设置数据
    public List<TrainData> getMdata() {
        return mdata;
    }

    public void setMdata(List<TrainData> mdata) {
        this.mdata = mdata;
        requestLayout();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        //绘制白色背景
        canvas.drawColor(getResources().getColor(R.color.white));
        //绘制灰色矩形区域
        canvas.drawRect(0, 0, width, heigh - brokenline_bottom, mPaint_bg);

        //Y轴不变 X轴绘制直线
//        for (int j = 0; j < 4; j++) {
//            canvas.drawLine(10, gridspace_heigh * (j + 1), width, gridspace_heigh * (j + 1), mPaint_gridline);
//        }
//
//
        for (int i = 0; i < mdata.size(); i++) {
            if (i == 0) {
                mpath.moveTo(gridspace_width * i + 50, heigh - brokenline_bottom - (heigh - brokenline_bottom) * mdata.get(i).getData() / 250);
            }
            canvas.drawLine(gridspace_width * i + 50, 0, gridspace_width * i + 50, heigh - brokenline_bottom, mPaint_gridline);

            if (i != mdata.size() - 1) {
                canvas.drawLine(gridspace_width * i + 50, heigh - brokenline_bottom - (heigh - brokenline_bottom) * mdata.get(i).getData() / 250, gridspace_width * (i + 1) + 50, heigh - brokenline_bottom - (heigh - brokenline_bottom) * mdata.get(i + 1).getData() / 250, mPaint_brokenline);
                mpath.quadTo(gridspace_width * i + 50, heigh - brokenline_bottom - (heigh - brokenline_bottom) * mdata.get(i).getData() / 250, gridspace_width * (i + 1) + 50, heigh - brokenline_bottom - (heigh - brokenline_bottom) * mdata.get(i + 1).getData() / 250);
            }

            canvas.drawCircle(gridspace_width * i + 50, heigh - brokenline_bottom - (heigh - brokenline_bottom) * mdata.get(i).getData() / 250, 15, mPaint_point_bg);
            canvas.drawCircle(gridspace_width * i + 50, heigh - brokenline_bottom - (heigh - brokenline_bottom) * mdata.get(i).getData() / 250, 10, mPaint_point_sur);

            if (i == selectIndex - 1) {
                String data = mdata.get(i).getData() + "";
                drawFloatTextBox(canvas, gridspace_width * i + 50, heigh - brokenline_bottom - (heigh - brokenline_bottom - 50) * mdata.get(i).getData() / 250 - mPaint_brokenline.measureText(data), Integer.valueOf(data));
                mPaint_text = new Paint(Paint.ANTI_ALIAS_FLAG);
                mPaint_text.setColor(getResources().getColor(R.color.main_color));
                mPaint_text.setTextAlign(Paint.Align.CENTER);
                mPaint_text.setTextSize(40);
                String date = mdata.get(i).getDate();
                canvas.drawText(date, gridspace_width * i + 50, heigh - brokenline_bottom / 3, mPaint_text);
            } else {
                String date = mdata.get(i).getDate();
                mPaint_text = new Paint(Paint.ANTI_ALIAS_FLAG);
                mPaint_text.setColor(Color.BLACK);
                mPaint_text.setTextAlign(Paint.Align.CENTER);
                mPaint_text.setTextSize(40);
                canvas.drawText(date, gridspace_width * i + 50, heigh - brokenline_bottom / 3, mPaint_text);
            }

            if (i == mdata.size() - 1) {
                mpath.quadTo(gridspace_width * i + 50, heigh - brokenline_bottom - (heigh - brokenline_bottom) * mdata.get(i).getData() / 250, gridspace_width * i + 50, heigh - brokenline_bottom);
                mpath.quadTo(gridspace_width * i + 50, heigh - brokenline_bottom, 50, heigh - brokenline_bottom);
                mpath.close();
            }
        }
        canvas.drawPath(mpath, mPaint_path);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        gridspace_width = 150;
        if (mdata.size() == 0) {
            width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        } else {
            //根据数据的条数设置宽度
            width = gridspace_width * mdata.size() + 10;
        }

        heigh = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        brokenline_bottom = 80;

        Log.d("bottm", "" + brokenline_bottom);
        gridspace_heigh = heigh - brokenline_bottom;
        setMeasuredDimension(width, heigh);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
//                if (gridspace_width * mdata.size() > width) {//当期的宽度不足以呈现全部数据
//                    float dis = event.getX() - startX;
//                    startX = event.getX();
//                    if (gridspace_width + dis < minXInit) {
//                        gridspace_width = (int) minXInit;
//                    } else {
//                        gridspace_width = gridspace_width + (int) dis;
//                    }
//                    invalidate();
//                }
                break;
            case MotionEvent.ACTION_UP:
                clickAction(event);
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }

    private int selectIndex = 1;

    /**
     * 点击X轴坐标或者折线节点
     *
     * @param event
     */
    private void clickAction(MotionEvent event) {
        int dp8 = dpToPx(8);

        float eventX = event.getX();
        float eventY = event.getY();

        for (int i = 0; i < mdata.size(); i++) {
            float x = gridspace_width * i + 30;
            float y = heigh - brokenline_bottom - (heigh - brokenline_bottom) * mdata.get(i).getData() / 200;

            if (eventX >= x - dp8 && eventX <= x + dp8 &&
                    eventY >= y - dp8 && eventY <= y + dp8 && selectIndex != i + 1) {//每个节点周围8dp都是可点击区域
                selectIndex = i + 1;
                invalidate();
                System.out.println("---------eventX--------:" + eventX);
                return;
            }

            //X轴刻度
            String text = mdata.get(i).getDate();
            Rect rect = getTextBounds(text, mPaint_text);
            x = 30 + gridspace_width * i;
            y = heigh - brokenline_bottom / 3;
            if (eventX >= x - rect.width() / 2 - dp8 && eventX <= x + rect.width() + dp8 / 2 &&
                    eventY >= y - dp8 && eventY <= y + rect.height() + dp8 && selectIndex != i + 1) {
                selectIndex = i + 1;
                invalidate();
                return;
            }
        }


    }

    private Rect getTextBounds(String text, Paint paint) {
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        return rect;
    }


    /**
     * dp转化成为px
     *
     * @param dp
     * @return
     */
    private int dpToPx(int dp) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f * (dp >= 0 ? 1 : -1));
    }

    /**
     * sp转化为px
     *
     * @param sp
     * @return
     */
    private int spToPx(int sp) {
        float scaledDensity = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (scaledDensity * sp + 0.5f * (sp >= 0 ? 1 : -1));
    }

    /**
     * 绘制显示Y值的浮动框
     *
     * @param canvas
     * @param x
     * @param y
     * @param text
     */
    private void drawFloatTextBox(Canvas canvas, float x, float y, int text) {
        int dp6 = dpToPx(8);
        int dp18 = dpToPx(15);
        //p1
        Path path = new Path();
        path.moveTo(x, y);
        //p2
        path.lineTo(x - dp6, y - dp6);
        //p3
        path.lineTo(x - dp18, y - dp6);
        //p4
        path.lineTo(x - dp18, y - dp6 - dp18);
        //p5
        path.lineTo(x + dp18, y - dp6 - dp18);
        //p6
        path.lineTo(x + dp18, y - dp6);
        //p7
        path.lineTo(x + dp6, y - dp6);
        //p1
        path.lineTo(x, y);
        canvas.drawPath(path, mPaint_up);

        Rect rect = getTextBounds(text + "", mPaint_brokenline);
        canvas.drawText(text + "", x, y - dp6 - (dp18 - rect.height()) / 2, mPaint_brokenline);
    }

}
