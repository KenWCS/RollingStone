package fr.wildcodeschool.rollingstone.builder.metrics;

@SuppressWarnings("unused")
public class Metrics {
  // Single instance of the Metrics class
  private static final Metrics mInstance = new Metrics();

  // Properties
  private float mDensity;
  private int mScreenWidth;
  private int mScreenHeight;

  /**
   * private constructor
   */
  private Metrics() {}

  /**
   * Instance accessor
   * @return Metrics: single instance of the Metrics class
   */
  public static Metrics getInstance() {
    return mInstance;
  }

  /**
   * Convert pixel value to dpi unit
   * @param pixel int: pixel value to convert
   * @return int: dpi value
   */
  public int convertPixelToDpi(int pixel) {
    return (int)(pixel * mDensity);
  }

  /**
   * Convert dpi value to pixel unit
   * @param dpi int: dpi value to convert
   * @return int: pixel value
   */
  public int convertDpiToPixel(int dpi) {
    return (int)(dpi / mDensity);
  }

  // --------------------------------------------------------------------------
  // GETTER ACCESSORS
  // --------------------------------------------------------------------------

  /**
   * @return float: the device pixel density
   */
  public float getDensity() {
    return mDensity;
  }

  /**
   * @return int: the device screen width
   */
  public int getScreenWidth() {
    return mScreenWidth;
  }

  /**
   * @return int: the device screen height
   */
  public int getScreenHeight() {
    return mScreenHeight;
  }


  // --------------------------------------------------------------------------
  // SETTER ACCESSORS WITH BUILER MODE
  // --------------------------------------------------------------------------

  /**
   * Store the device density value
   * @param mDensity float: device pixel density
   * @return Metrics: Single instance of Metrics class
   */
  public Metrics setDensity(float mDensity) {
    this.mDensity = mDensity;
    return this;
  }

  /**
   * Store the device screen width value
   * @param mScreenWidth int: device screen width
   * @return Metrics: Single instance of Metrics class
   */
  public Metrics setScreenWidth(int mScreenWidth) {
    this.mScreenWidth = mScreenWidth;
    return this;
  }

  /**
   * Store the device screen height value
   * @param mScreenHeight int: device screen height
   * @return Metrics: Single instance of Metrics class
   */
  public Metrics setScreenHeight(int mScreenHeight) {
    this.mScreenHeight = mScreenHeight;
    return this;
  }
}
