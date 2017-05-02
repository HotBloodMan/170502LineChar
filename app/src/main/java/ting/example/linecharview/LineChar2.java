package ting.example.linecharview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.HashMap;

/**
 * Created by 1 on 2017/5/2.
 */

public class LineChar2 extends View{
    private int mWidth,mHeight;
    private float mYFontSize=24;

    private int mLineColor= Color.parseColor("#00BCD4");

    private float mLineWidth=8.0f;
    //点的集合
    private HashMap<Integer,Integer> mPointMap;
    //点的半径
    private float mPointRadius=10;
    //没有数据的时候的内容
    private String mNoDataMsg="no data";

    //X轴的文字 "xgek11","xgek12","xgek13","xgek14","xgek15"
    private String[] mXAxis = {};

    //Y轴的文字"gek11","gek12","gek13","gek14","gek15"
    private String[] mYAxis = {};


    public LineChar2(Context context) {
        this(context,null);
    }

    public LineChar2(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LineChar2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        if(modeWidth==MeasureSpec.EXACTLY){
            mWidth=sizeWidth;
        }else if(widthMeasureSpec == MeasureSpec.AT_MOST){
            throw new IllegalArgumentException("width must be EXACTLY,you should set like android:width=\"200dp\"");
        }
        if(modeHeight==MeasureSpec.EXACTLY){
            mHeight=sizeHeight;
        }else if(widthMeasureSpec == MeasureSpec.AT_MOST){
            throw new IllegalArgumentException("height must be EXACTLY,you should set like android:height=\"200dp\"");
        }
        setMeasuredDimension(mWidth,mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画坐标线的轴
        Paint p=new Paint();
        p.setTextSize(mYFontSize);
        p.setColor(Color.parseColor("#3F51B5"));

            //画Y轴
        int[] yPoints=new int[mYAxis.length];
        int yInterval= (int) ((mHeight-mYFontSize-2)/(mYAxis.length));

        int height = getHeight();
        for(int i=0;i<mYAxis.length;i++){
            canvas.drawText(mYAxis[i],0,yInterval*i+24,p);
            yPoints[i]= (int) (mYFontSize+i*yInterval);
        }



        //画 X 轴
//        int [] xPoints=new int[mXAxis.length];
//        int xInterval=mWidth/mXAxis.length;
//        for(int i=0;i<mXAxis.length;i++){
//            canvas.drawText(mXAxis[i],xInterval*i+24,0,p);
//            xPoints[i]=i*xInterval;
//        }


//
        int[] mXpoints=new int[mXAxis.length];

        int  xItemX = (int) p.measureText(mYAxis[1]);
        int xOffset=50;
        int xInterval=(mWidth-xOffset)/mXAxis.length;
        int xItemY= (int) (mYFontSize+mYAxis.length*yInterval);
        for(int i=0;i<mXAxis.length;i++){
            canvas.drawText(mXAxis[i],xInterval*i+xItemX+xOffset,xItemY,p);
            mXpoints[i]= (int) (i*xInterval+xItemX+p.measureText(mXAxis[i])/2+xOffset+10);
        }
        //画点
        Paint p2 = new Paint();
        p2.setColor(mLineColor);
        Paint paintLine = new Paint();
        paintLine.setColor(mLineColor);
        paintLine.setAntiAlias(true);
        //设置线条宽度
        paintLine.setStrokeWidth(15);
        p2.setStyle(Paint.Style.FILL);

        for(int i=0;i<mXAxis.length;i++){
            //画点
            canvas.drawCircle(mXpoints[i],yPoints[mPointMap.get(i)],mPointRadius,p2);
            if(i>0){
                canvas.drawLine(mXpoints[i-1],yPoints[mPointMap.get(i-1)],mXpoints[i],yPoints[mPointMap.get(i)],paintLine);
            }
        }



    }

    //设置map数据
    public void setData(HashMap<Integer,Integer> data){
        mPointMap=data;
        invalidate();
    }
     public void setmLineColor(int color){
         mLineColor=color;
         invalidate();
     }

    /**
     * 设置Y轴文字
     * @param yItem
     */
    public void setYItem(String[] yItem){
        mYAxis = yItem;
    }

    /**
     * 设置X轴文字
     * @param xItem
     */
    public void setXItem(String[] xItem){
        mXAxis = xItem;
    }


}
