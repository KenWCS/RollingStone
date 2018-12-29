package fr.wildcodeschool.rollingstone;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.view.View;

import fr.wildcodeschool.rollingstone.builder.BitmapBuilder;
import fr.wildcodeschool.rollingstone.builder.metrics.Metrics;

public class BoardView extends View {
  // Painter used to draw in view canvas
  final private Paint mPainter = new Paint();

  // Game board draw data
  private Bitmap  mBoard = null;
  private Rect    mBoardRect;
  private int     mBoardTileSize;

  // Stone draw data
  private Bitmap  mStone = null;
  private Point   mStoneAxis;
  private Rect    mStoneSrcRect;
  private Rect    mStoneDstRect;

  /**
   * Default constructor
   * @param ctx Context: Activity context
   */
  public BoardView(Context ctx) {
    super(ctx);

    mBoardTileSize = 0;
    mStoneSrcRect = new Rect();
    mStoneDstRect = new Rect();
    mStoneAxis = new Point(1, 1);

    // Compute size of a tile in the screen
    int lScreenWidth = Metrics.getInstance().getScreenWidth();
    mBoardTileSize = lScreenWidth / BitmapBuilder.TILES_PER_LINE;

    // Set stone dimension
    int lDpiSize = Metrics.getInstance().convertPixelToDpi(mBoardTileSize);
    mStoneSrcRect.set(0, 0, lDpiSize, lDpiSize);

    // Set board dimension
    mBoardRect = new Rect(0,0, lScreenWidth, lScreenWidth);
  }

  /**
   * Override performClick View method because IntelliJ asked to do.
   * @return True there was an assigned OnClickListener that was called, false otherwise is returned.
   */
  @Override
  public boolean performClick() {
    return super.performClick();
  }

  /**
   * Override onDraw method to do specific drawing.
   * @param canvas Canvas: the canvas on which the background will be drawn
   */
  @Override
  protected void onDraw(Canvas canvas) {
    if (null != mBoard) {
      canvas.drawBitmap(mBoard, mBoardRect, mBoardRect, mPainter);

      if (null != mStone) {
        mStoneDstRect.set(
          mBoardTileSize * mStoneAxis.x,
          mBoardTileSize * mStoneAxis.y,
          mBoardTileSize * (mStoneAxis.x + 1),
          mBoardTileSize * (mStoneAxis.y + 1));
        canvas.drawBitmap(mStone, mStoneSrcRect, mStoneDstRect, mPainter);
      }
    }
  }


  // --------------------------------------------------------------------------
  // SETTER ACCESSORS WITH BUILER MODE
  // --------------------------------------------------------------------------

  /**
   * Store th reference of the stone coordinates
   * @param pAxis Point: coordinates of the stone
   * @return BoardView current instance
   */
  public BoardView setStoneAxis(Point pAxis) {
    mStoneAxis = pAxis;
    return this;
  }

  /**
   * Store the reference of @NonNull stone texture
   * @param pBitmap Bitmap: the buffer containing the stone texture
   * @return BoardView current instance
   */
  public BoardView setStoneBitmap(@NonNull Bitmap pBitmap) {
    mStone = pBitmap;
    invalidate();
    return this;
  }

  /**
   * Store the reference of @NonNull board texture
   * @param pBitmap Bitmap: the buffer containing the board texture
   * @return BoardView current instance
   */
  public BoardView setBoardBitmap(@NonNull Bitmap pBitmap) {
    // Set board bitmap at the end because it is the onDraw conditioner
    mBoard = pBitmap;
    invalidate();
    return this;
  }
}
