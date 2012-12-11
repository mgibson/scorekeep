package gibson.scorekeep;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;

public class OraEtLabora implements Game {

  private Context context;
  private List<Row> rows = new ArrayList<Row>();

  public OraEtLabora(Context baseContext) {
    context = baseContext;
  }

  @Override
  public int numberOfScoreRows() {
    return 10;
  }

  @Override
  public List<Row> getRows() {
    if (rows.isEmpty()) {
      Row.setContext(context);
      rows.add(new Row(R.string.mil,Color.RED));
      rows.add(new Row(R.string.mil,Color.RED));
      rows.add(new Row(R.string.mil,Color.RED));
      rows.add(new Row(R.string.mil,Color.RED));
      rows.add(new Row(R.string.mil,Color.RED));
      rows.add(new Row(R.string.mil,Color.RED));
      rows.add(new Row(R.string.mil,Color.RED));
      rows.add(new Row(R.string.coin,Color.YELLOW));
      rows.add(new Row(R.string.wonder,Color.LTGRAY));
      rows.add(new Row(R.string.trade,Color.WHITE));
    }
    return rows;
  }

  @Override
  public Row getRow(int scoreCellNum) {
    return getRows().get(scoreCellNum);
  }

  @Override
  public int getMaxNumberOfPlayers() {
    return 4;
  }

}
