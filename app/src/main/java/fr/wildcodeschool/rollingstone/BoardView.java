package fr.wildcodeschool.rollingstone;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.view.View;

import fr.wildcodeschool.rollingstone.builder.BitmapData;
import fr.wildcodeschool.rollingstone.tool.Metrics;

public class BoardView extends View {
  enum MOVE { UP, DN, LT, RT }

  final private Paint mPainter = new Paint();

  private Bitmap  mBoard = null;
  private Bitmap  mStone = null;
  private Rect    mBoardRect;
  private int     mItemSize;
  private Rect    mStoneSrcRect;
  private Rect    mStoneDstRect;

  private int     xAxis = 1;
  private int     yAxis = 8;

  public BoardView(Context ctx) {
    super(ctx);

    // Compute size of a tile in the screen
    int lScreenWidth = Metrics.getInstance().getScreenWidth();
    mItemSize = lScreenWidth / BitmapData.NB_TILES_PER_LINE;

    // Set stone dimension
    int lDpiSize = Metrics.getInstance().convertPixelToDpi(mItemSize);
    mStoneSrcRect = new Rect(0, 0, lDpiSize, lDpiSize);
    mStoneDstRect = new Rect();

    // Set board dimension
    mBoardRect = new Rect(0,0, lScreenWidth, lScreenWidth);
  }

  @Override
  public boolean performClick() {
    return super.performClick();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    canvas.drawBitmap(mBoard, mBoardRect, mBoardRect, mPainter);
    mStoneDstRect.set(
      mItemSize*xAxis,
      mItemSize*yAxis,
      mItemSize*(xAxis+1),
      mItemSize*(yAxis+1));
    canvas.drawBitmap(mStone, mStoneSrcRect, mStoneDstRect, mPainter);
  }

  public void moveStone(MOVE moveEvent) {
    switch (moveEvent) {
      case UP: yAxis--; break;
      case DN: yAxis++; break;
      case LT: xAxis--; break;
      case RT: xAxis++; break;
    }
    invalidate();
  }


  public BoardView setStoneBitmap(@NonNull Bitmap pBitmap) {
    mStone = pBitmap;
    return this;
  }

  public BoardView setBoardBitmap(@NonNull Bitmap pBitmap) {
    mBoard = pBitmap;
    return this;
  }
}
