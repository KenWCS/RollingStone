package fr.wildcodeschool.rollingstone.builder;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

public class BitmapBuilder extends BitmapData {
  /**
   * Constructor
   * @param ctx NonNull Context: Activity context
   */
  public BitmapBuilder(@NonNull Context ctx) {
    super(ctx);
  }

  /**
   * Get in the tiles bitmap the stone sub part.
   * @return Bitmap: the stone bitmap
   */
  public Bitmap getStoneBitmap() {
    int dpiSize = dpiTileSize;
    return Bitmap.createBitmap(tiles, dpiSize * 4, 0, dpiSize, dpiSize);
  }

  /**
   * Build the board bitmap according to parameter
   * @param board String: board definition
   * @param listener BitmapBuilderListener: listener used to inform the caller when work is finish
   */
  public void getBoardBitmap(String board, BitmapBuilderListener listener) {
    if (board.length() == TILES_PER_LINE * TILES_PER_LINE) {
      // Save board
      mGameBoard = board;

      BitmapWorkerTask task = new BitmapWorkerTask(listener);
      task.execute(this);
    } else {
      listener.onBitmapError("Bitmap format is incorrect");
    }
  }
}
