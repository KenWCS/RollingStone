package fr.wildcodeschool.rollingstone.builder;

import android.graphics.Bitmap;

/**
 * BitmapBuilderListener: used to return the state of bitmap creation
 */
public interface BitmapBuilderListener {
  /**
   * Called when bitmap creation succeed
   * @param bitmap Bitmap: board texture
   */
  void onBitmapReady(Bitmap bitmap);

  /**
   * Called when bitmap creation failed
   * @param error String: problem encountered during bitmap creation
   */
  void onBitmapError(String error);
}
