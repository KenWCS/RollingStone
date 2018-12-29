package fr.wildcodeschool.rollingstone;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import fr.wildcodeschool.rollingstone.builder.*;
import fr.wildcodeschool.rollingstone.builder.metrics.Metrics;
import fr.wildcodeschool.rollingstone.gestures.OnSwipeTouchListener;

public class MainActivity extends AppCompatActivity {
  // Map definition
  String mGameBoard = "###########    #   ## #  # # ## #    # ## ###### ##   #B   ## # ###  ## # ### ###A#      ###########";
  Point  mStoneAxis = new Point(1, 8);

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ConstraintLayout lLayout = findViewById(R.id.mainConstraint);

    // Should be done first
    initializeMetrics();

    // Build BoardView
    BoardView lView = new BoardView(this);
    // Inflate it
    lView.setId(R.id.board_view);
    lLayout.addView(lView, 0);

    ConstraintSet set = new ConstraintSet();
    set.clone(lLayout);

    set.connect(lView.getId(), ConstraintSet.TOP, lLayout.getId(), ConstraintSet.TOP, 0);
    set.connect(lView.getId(), ConstraintSet.BOTTOM, lLayout.getId(), ConstraintSet.BOTTOM, 0);
    set.connect(lView.getId(), ConstraintSet.LEFT, lLayout.getId(), ConstraintSet.LEFT, 0);
    set.connect(lView.getId(), ConstraintSet.RIGHT, lLayout.getId(), ConstraintSet.RIGHT, 0);
    set.applyTo(lLayout);

    // Create instance of bitmap builder
    BitmapBuilder lBitmapBuilder = new BitmapBuilder(this);

    // Build asynchroniously the board
    lBitmapBuilder.getBoardBitmap(mGameBoard, new BitmapBuilderListener() {
        @Override
        public void onBitmapReady(Bitmap bitmap) {
          lView
            .setStoneAxis(mStoneAxis)
            .setBoardBitmap(bitmap)
            .setStoneBitmap(lBitmapBuilder.getStoneBitmap());
        }

        @Override
        public void onBitmapError(String error) {
          Log.e("BoardBitmap Error", error);
        }
      }
    );


    // Gesture listener instance
    lView.setOnTouchListener(new OnSwipeTouchListener(this) {

      /**
       * Listen top swipe gesture to update stone position
       */
      public void onSwipeTop() {
        // Check if stone is not on the top border
        if (mStoneAxis.y > 1) {
          // Look if stone could move up
          if (mGameBoard.charAt(BitmapBuilder.TILES_PER_LINE * (mStoneAxis.y - 1) + mStoneAxis.x) != '#') {
            // Update position
            mStoneAxis.y--;
            // Force refresh
            lView.invalidate();
          }
        }
      }

      /**
       * Listen right swipe gesture to update stone position
       */
      public void onSwipeRight() {
        // Check if stone is not on the right border
        if (mStoneAxis.x < 8) {
          // Look if stone could move right
          if (mGameBoard.charAt(BitmapBuilder.TILES_PER_LINE * mStoneAxis.y + mStoneAxis.x + 1) != '#') {
            // Update position
            mStoneAxis.x++;
            // Force refresh
            lView.invalidate();
          }
        }
      }

      /**
       * Listen left swipe gesture to update stone position
       */
      public void onSwipeLeft() {
        // Check if stone is not on the left border
        if (mStoneAxis.x > 1) {
          // Look if stone could move right
          if (mGameBoard.charAt(BitmapBuilder.TILES_PER_LINE * mStoneAxis.y + mStoneAxis.x - 1) != '#') {
            // Update position
            mStoneAxis.x--;
            // Force refresh
            lView.invalidate();
          }
        }
      }

      /**
       * Listen bottom swipe gesture to update stone position
       */
      public void onSwipeBottom() {
        // Check if stone is not on the bottom border
        if (mStoneAxis.y < 8) {
          // Look if stone could move down
          if (mGameBoard.charAt(BitmapBuilder.TILES_PER_LINE * (mStoneAxis.y + 1) + mStoneAxis.x) != '#') {
            // Update position
            mStoneAxis.y++;
            // Force refresh
            lView.invalidate();
          }
        }
      }
    });
  }

  /**
   * Make the metrics info accessible from anywhere in the application
   */
  public void initializeMetrics() {
    // DisplayMetrics object to populate
    DisplayMetrics metrics = new DisplayMetrics();
    // Populate it here
    getWindowManager().getDefaultDisplay().getMetrics(metrics);

    // Update the Metrics singleton data
    Metrics.getInstance()
    .setDensity(getResources().getDisplayMetrics().density)
    .setScreenWidth(metrics.widthPixels)
    .setScreenHeight(metrics.heightPixels);
  }
}
