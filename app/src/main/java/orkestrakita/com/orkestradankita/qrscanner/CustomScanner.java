package orkestrakita.com.orkestradankita.qrscanner;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;

import me.dm7.barcodescanner.core.IViewFinder;
import me.dm7.barcodescanner.core.ViewFinderView;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by irfananda on 29/07/16.
 */
public class CustomScanner extends ZXingScannerView {

    public CustomScanner(Context context) {
        super(context);
    }

    public CustomScanner(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    @Override
    protected IViewFinder createViewFinderView(Context context) {
        ViewFinderView finderView = new ViewFinderView(context) {
            private int width;
            private int height;
            private int div;
            private float linLen;
            private float satu;
            private float minSatu;
            private float minDua;

            private int[] SCANNER_ALPHA = new int[]{0, 64, 128, 192, 255, 192, 128, 64};
            private int scannerAlpha;

            public void setter(int x) {
                div = x;
                satu = width/div;
                minSatu = satu*(div-1);
                minDua = satu*(div-2);
            }

            @Override
            public void drawViewFinderMask(Canvas canvas) {
                width = canvas.getWidth();
                height = canvas.getHeight();
                linLen = satu/3;
                canvas.drawRect(0.0F,0.0F,satu,height,mFinderMaskPaint);
                canvas.drawRect(minSatu,0.0F,width,height,mFinderMaskPaint);
                canvas.drawRect(satu,0.0F,minSatu,(height/2)-minDua/2,mFinderMaskPaint);
                canvas.drawRect(satu,(height/2)+minDua/2,minSatu,height,mFinderMaskPaint);
            }

            @Override
            public void drawViewFinderBorder(Canvas canvas) {
                // kiri atas
                canvas.drawLine(satu-1,(height/2)-minDua/2,satu+linLen,(height/2)-minDua/2,mBorderPaint);
                canvas.drawLine(satu,(height/2)-minDua/2-1,satu,(height/2)-minDua/2+linLen,mBorderPaint);
                // kiri bawah
                canvas.drawLine(satu,(height/2)+minDua/2-linLen+1,satu,(height/2)+minDua/2,mBorderPaint);
                canvas.drawLine(satu-1,(height/2)+minDua/2-1,satu+linLen,(height/2)+minDua/2-1,mBorderPaint);
                // kanan atas
                canvas.drawLine(minSatu-linLen,(height/2)-minDua/2,minSatu,(height/2)-minDua/2,mBorderPaint);
                canvas.drawLine(minSatu-2,(height/2)-minDua/2-1,minSatu-2,(height/2)-minDua/2+linLen,mBorderPaint);
                // kanan bawah
                canvas.drawLine(minSatu-2,(height/2)+minDua/2-linLen+1,minSatu-2,(height/2)+minDua/2,mBorderPaint);
                canvas.drawLine(minSatu-linLen+1,(height/2)+minDua/2-1,minSatu,(height/2)+minDua/2-1,mBorderPaint);
            }

            @Override
            public void drawLaser(Canvas canvas) {
                canvas.drawRect(satu+2,height/2-1,minSatu-1,height/2+2,mLaserPaint);
            }

            @Override
            public void onDraw(Canvas canvas) {
                setter(15);
                drawViewFinderMask(canvas);
                setter(14);
                drawViewFinderBorder(canvas);
                //drawLaser(canvas);
            }
        };

        finderView.setBorderColor(Color.GREEN);
        finderView.setLaserColor(Color.WHITE);

        return finderView ;
    }

}
