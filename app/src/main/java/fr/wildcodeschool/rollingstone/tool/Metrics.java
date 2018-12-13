package fr.wildcodeschool.rollingstone.tool;

public class Metrics {
  // Single instance of the Metrics class
  private static final Metrics mInstance = new Metrics();

  // Properties
  private float mDensity;
  private int mScreenWidth;
  private int mScreenHeight;

  // private constructor
  private Metrics() {}

  // Instance accessor
  public static Metrics getInstance() {
    return mInstance;
  }

  public float getDensity() {
    return mDensity;
  }

  public int getScreenWidth() {
    return mScreenWidth;
  }

  public int getScreenHeight() {
    return mScreenHeight;
  }


  public Metrics setDensity(float mDensity) {
    this.mDensity = mDensity;
    return this;
  }

  public Metrics setScreenWidth(int mScreenWidth) {
    this.mScreenWidth = mScreenWidth;
    return this;
  }

  public Metrics setScreenHeight(int mScreenHeight) {
    this.mScreenHeight = mScreenHeight;
    return this;
  }

  public int convertPixelToDpi(int pixel) {
    return (int)(pixel * mDensity);
  }

  public int convertDpiToPixel(int dpi) {
    return (int)(dpi / mDensity);
  }
}
