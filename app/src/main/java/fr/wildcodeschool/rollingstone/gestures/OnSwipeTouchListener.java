package fr.wildcodeschool.rollingstone.gestures;

import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public abstract class OnSwipeTouchListener implements OnTouchListener {
  // Detects various gestures and events using the supplied MotionEvent
  private final GestureDetector gestureDetector;

  /**
   * Constructor
   * @param ctx Activity context
   */
  protected OnSwipeTouchListener(Context ctx) {
    gestureDetector = new GestureDetector(ctx, new GestureListener());
  }

  /**
   * Called when a touch event is dispatched to a view.
   * This allows listeners to get a chance to respond before the target view.
   * @param v View: The view the touch event has been dispatched to.
   * @param event MotionEvent: The MotionEvent object containing full information about the event.
   * @return True if the listener has consumed the event, false otherwise.
   */
  @Override
  public boolean onTouch(View v, MotionEvent event) {
    return gestureDetector.onTouchEvent(event);
  }

  /**
   * Inner Class overloading GestureDetector.SimpleOnGestureListener
   */
  private final class GestureListener extends SimpleOnGestureListener {
    // Internal swipe thresholds
    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;

    /**
     * Notified when a tap occurs with the down MotionEvent that triggered it.
     * This will be triggered immediately for every down event.
     * All other events should be preceded by this.
     * @param e MotionEvent: The down motion event.
     * @return boolean
     */
    @Override
    public boolean onDown(MotionEvent e) {
      return true;
    }

    /**
     * Notified of a fling event when it occurs with the initial on down MotionEvent and the matching up MotionEvent.
     * The calculated velocity is supplied along the x and y axis in pixels per second.
     * @param e1 MotionEvent: The first down motion event that started the fling.
     * @param e2 MotionEvent: The move motion event that triggered the current onFling.
     * @param velocityX float: The velocity of this fling measured in pixels per second along the x axis.
     * @param velocityY float: The velocity of this fling measured in pixels per second along the y axis.
     * @return true if the event is consumed, else false
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
      boolean result = false;
      try {
        float diffY = e2.getY() - e1.getY();
        float diffX = e2.getX() - e1.getX();
        if (Math.abs(diffX) > Math.abs(diffY)) {
          if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
            if (diffX > 0) {
              onSwipeRight();
            } else {
              onSwipeLeft();
            }
            result = true;
          }
        }
        else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
          if (diffY > 0) {
            onSwipeBottom();
          } else {
            onSwipeTop();
          }
          result = true;
        }
      } catch (Exception exception) {
        exception.printStackTrace();
      }
      return result;
    }
  }

  /**
   * Methods to be implemented by the overload class
   */
  public abstract void onSwipeRight();
  public abstract void onSwipeLeft();
  public abstract void onSwipeTop();
  public abstract void onSwipeBottom();
}
