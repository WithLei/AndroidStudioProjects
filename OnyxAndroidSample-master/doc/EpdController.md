Provide [EPD Screen Update](./EPD-Screen-Update.md) API and [Scribble API](./Scribble-API.md) or [Touch Disable Function](./EPD-Touch.md)


[EPD Screen Update](./EPD-Screen-Update.md) API provided by `EpdController` is rather primitive, for APPs to update screen, it's recommended to use [EpdDeviceManager](./EpdDeviceManager.md) as wrapper

provided api to prevent webview enter A2 mode, for more detail to see [PenStylusWebViewDemoActivity](../app/sample/src/main/java/com/onyx/android/sample/PenStylusWebViewDemoActivity.java)
```
   /**
     * 
     * @param view
     * @param enabled
     */
    public static void setWebViewContrastOptimize(WebView view, boolean enabled)
```