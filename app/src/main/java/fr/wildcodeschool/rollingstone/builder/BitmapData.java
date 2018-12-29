package fr.wildcodeschool.rollingstone.builder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;

import fr.wildcodeschool.rollingstone.builder.metrics.Metrics;
import fr.wildcodeschool.rollingstone.R;

class BitmapData {
  // Define the expected dimension of board
  public static final int TILES_PER_LINE = 10;

  protected Bitmap tiles;
  int itemSize;
  int dpiTileSize;
  Rect boardRect;
  Paint painter = new Paint();
  String mGameBoard;

  /**
   * Constructor
   * @param ctx NonNull Context: Activity context
   */
  BitmapData(@NonNull Context ctx) {
    Metrics lMetrics = Metrics.getInstance();

    // Get tiles bitmap store in raw folder
    tiles = BitmapFactory.decodeResource(ctx.getResources(), R.raw.tiles);

    // Get tile element size
    dpiTileSize = lMetrics.convertPixelToDpi(
      ctx.getResources().getInteger(R.integer.tile_size));

    // Compute size of a tile in the screen
    int lScreenWidth = lMetrics.getScreenWidth();
    itemSize = lScreenWidth / TILES_PER_LINE;

    // Define the Rectangular zone of game board
    boardRect = new Rect(0,0, lScreenWidth, lScreenWidth);
  }
}
