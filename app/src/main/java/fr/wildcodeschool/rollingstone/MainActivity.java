package fr.wildcodeschool.rollingstone;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import fr.wildcodeschool.rollingstone.builder.BitmapBuilder;
import fr.wildcodeschool.rollingstone.tool.Metrics;
import fr.wildcodeschool.rollingstone.tool.OnSwipeTouchListener;

public class MainActivity extends AppCompatActivity {

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
  int x = 1;
  int y = 8;

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

    lBitmapBuilder.getBoardBitmap(bitmap ->
      lView
        .setBoardBitmap(bitmap)
        .setStoneBitmap(lBitmapBuilder.getStoneBitmap())
    );

    lView.setOnTouchListener(new OnSwipeTouchListener(this) {
      public void onSwipeTop() {
        if (lTiles[y-1].charAt(x) != '#') {
          lView.moveStone(BoardView.MOVE.UP);
          y--;
        }
      }

      public void onSwipeRight() {
        if (lTiles[y].charAt(x+1) != '#') {
          lView.moveStone(BoardView.MOVE.RT);
          x++;
        }
      }

      public void onSwipeLeft() {
        if (lTiles[y].charAt(x-1) != '#') {
          lView.moveStone(BoardView.MOVE.LT);
          x--;
        }
      }

      public void onSwipeBottom() {
        if (lTiles[y+1].charAt(x) != '#') {
          lView.moveStone(BoardView.MOVE.DN);
          y++;
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
