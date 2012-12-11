package gibson.scorekeep;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;

public class SevenWonders implements Game {
  
  private Context context;
  private List<Row> rows = new ArrayList<Row>();
  
  private static int red = 0xFFD63333;//Color.rgb(0xD6,0x33,0x33);
  private static int yellow = 0xFFDDDB4D;//Color.rgb(0xFF,0xDB,0x4D);
  private static int grey = 0xFFAAAAAA;
  private static int offWhite = 0xFFDDDDDD;
  private static int blue = 0xFF4D94DD;//Color.rgb(0x4D,0x94,0xFF);
  private static int purple = 0xFF944D94;//Color.rgb(0x94,0x4D,0x94);
  private static int green = 0xFF47A347;//Color.rgb(0x47,0xA3,0x47);
  

  public SevenWonders(Context baseContext) {
    context = baseContext;
  }

  @Override
  public int numberOfScoreRows() {
    return 7;
  }

  @Override
  public List<Row> getRows() {
    if (rows.isEmpty()) {
      Row.setContext(context);
      rows.add(new Row(R.string.mil,red));
      rows.add(new Row(R.string.coin,yellow));
      rows.add(new Row(R.string.wonder,grey));
      rows.add(new Row(R.string.civilian,blue));
      rows.add(new Row(R.string.trade,offWhite));
      rows.add(new Row(R.string.guilds,purple));
      rows.add(new Row(R.string.science,green));
    }
    return rows;
  }

  @Override
  public Row getRow(int scoreCellNum) {
    return getRows().get(scoreCellNum);
  }

  @Override
  public int getMaxNumberOfPlayers() {
    return 7;
  }

}
