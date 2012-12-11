package gibson.scorekeep;

import java.util.List;


public interface Game {
  public int numberOfScoreRows();
  public List<Row> getRows();
  public Row getRow(int scoreCellNum);
  public int getMaxNumberOfPlayers();
}
