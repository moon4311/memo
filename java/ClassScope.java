import java.util.Vector;

public class ClassScope {
  private static java.lang.reflect.Field LIBRARIES = null;
  static {
      try {
        // java 1.8 : ClassLoader, java 15 : NativeLibraries
        LIBRARIES = ClassLoader.class.getDeclaredField("loadedLibraryNames");
        LIBRARIES.setAccessible(true);
      } catch (NoSuchFieldException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (SecurityException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
  }
  public static boolean isLoaded(final ClassLoader loader, String libraryName) {
    Vector<String> libraries;
    try {
      libraries = (Vector<String>) LIBRARIES.get(loader);
      for(String a: libraries) {
        if(a.contains(libraryName)) return true;
      }
    } catch (IllegalArgumentException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return false;
    
  }
}
