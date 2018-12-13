package fr.wildcodeschool.rollingstone.builder;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.AsyncTask;

class BitmapWorkerTask extends AsyncTask<BitmapData, Void, Bitmap> {
  private BitmapBuilderListener mListener;

  public BitmapWorkerTask(BitmapBuilderListener listener) {
    mListener = listener;
  }

  @Override
  protected Bitmap doInBackground(BitmapData... params) {
    BitmapData data = params[0];

    Rect lTileRect = new Rect(0,0, data.dpiTileSize, data.dpiTileSize);

    Bitmap lBitmap = Bitmap.createBitmap(
      data.boardRect.width(),
      data.boardRect.height(),
      Bitmap.Config.ARGB_8888 );
    Canvas lCanvas = new Canvas(lBitmap);

    int hCount = 0;
    for (String tiles: data.grid) {
      for (int wCount = 0; wCount < 10; wCount++) {
        if (tiles.charAt(wCount) == '#') {
          int left = wCount * data.itemSize;
          int top = hCount * data.itemSize;
          Rect dest = new Rect(left, top, left + data.itemSize, top + data.itemSize);
          lCanvas.drawBitmap(data.tiles, lTileRect, dest, data.painter);
        }
      }
      hCount++;
    }

    return lBitmap;
  }

  @Override
  protected void onPostExecute(Bitmap bitmap) {
    mListener.onBitmapReady(bitmap);
  }
}