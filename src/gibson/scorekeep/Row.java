package gibson.scorekeep;

import android.content.Context;

class Row {
  
  private static Context context;
  int color;
  String name;

  public static void setContext(Context context) {
    Row.context = context;
  }

  public Row(int string_int, int col) {
    name = context.getString(string_int);
    color = col;
  }
}
