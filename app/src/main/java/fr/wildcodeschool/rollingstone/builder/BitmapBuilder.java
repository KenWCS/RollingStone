package fr.wildcodeschool.rollingstone.builder;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

public class BitmapBuilder {
  private BitmapData mData;

  /**
   * Constructor
   * @param ctx Activity context
   */
  public BitmapBuilder(@NonNull Context ctx) {
    mData = new BitmapData(ctx);
  }

  public Bitmap getStoneBitmap() {
    int dpiSize = mData.dpiTileSize;
    return Bitmap.createBitmap(mData.tiles, dpiSize * 4, 0, dpiSize, dpiSize);
  }

  public void getBoardBitmap(BitmapBuilderListener listener) {
    String[] lTiles = {
      "##########",
      "#    #   #",
      "# #  # # #",
      "# #    # #",
      "# ###### #",
      "#   #B   #",
      "# # ###  #",
      "# # ### ##",
      "#A#      #",
      "##########",
    };

    mData.setGrid(lTiles);
    BitmapWorkerTask task = new BitmapWorkerTask(listener);
    task.execute(mData);
  }
}
