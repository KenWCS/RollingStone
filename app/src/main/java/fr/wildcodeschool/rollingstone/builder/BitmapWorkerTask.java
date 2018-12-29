package fr.wildcodeschool.rollingstone.builder;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.AsyncTask;

class BitmapWorkerTask extends AsyncTask<BitmapData, Void, Bitmap> {
  // Listener used to return the build bitmap
  private BitmapBuilderListener mListener;

  /**
   * Constructor
   * @param listener BitmapBuilderListener: used to return the build bitmap
   */
  BitmapWorkerTask(BitmapBuilderListener listener) {
    mListener = listener;
  }

  /**
   * ASync workerThread method
   * @param params BitmapData: Used to build the board bitmap
   * @return Bitmap: The board bitmap
   */
  @Override
  protected Bitmap doInBackground(BitmapData... params) {
    BitmapData data = params[0];

    Rect lTileRect = new Rect(0,0, data.dpiTileSize, data.dpiTileSize);

    Bitmap lBitmap = Bitmap.createBitmap(
      data.boardRect.width(),
      data.boardRect.height(),
      Bitmap.Config.ARGB_8888 );
    Canvas lCanvas = new Canvas(lBitmap);

    String board = data.mGameBoard;
    for (int i = 0; i < board.length(); i++) {
      if (board.charAt(i) == '#') {
        int left = (i % BitmapBuilder.TILES_PER_LINE) * data.itemSize;
        int top  = (i / BitmapBuilder.TILES_PER_LINE) * data.itemSize;
        Rect dest = new Rect(left, top, left + data.itemSize, top + data.itemSize);
        lCanvas.drawBitmap(data.tiles, lTileRect, dest, data.painter);
      }
    }

    return lBitmap;
  }

  /**
   * Called in the UI thread
   * @param bitmap Bitmap: The build board bitmap
   */
  @Override
  protected void onPostExecute(Bitmap bitmap) {
    mListener.onBitmapReady(bitmap);
  }
}