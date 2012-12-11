package gibson.scorekeep;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends Activity {

  private Game currentGame;
  List<Player> players = new ArrayList<Player>();
  
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.activity_main, menu);
    return true;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    // eventually have a game chooser - for now hardwire
    currentGame = new SevenWonders(getBaseContext());
    //currentGame = new OraEtLabora(getBaseContext());
    setUpScorePad();
    
    // init Players
    makePlayers(currentGame.getMaxNumberOfPlayers());
    addClearButton();
  }
  
  private void addClearButton() {
    Button clear = new Button(getBaseContext());
    clear.setText(R.string.clear);
    clear.setOnClickListener(new Clear());
    Layout.layoutClearButton(clear,getBaseContext(),tableLayout());
  }
  private class Clear implements View.OnClickListener {
    public void onClick(View v) {
      for (Player player : players) player.clear();
    }
  }
  
  private TableLayout tableLayout() {
    return (TableLayout)findViewById(R.id.tableLayout);
  }
  
  private TableRow addRow() {
    TableRow row = new TableRow(getBaseContext());
    tableLayout().addView(row);
    return row;
  }
  
  private void setUpScorePad() {
    setUpNameRow();
    for (int i=0; i<currentGame.numberOfScoreRows(); i++) {
      setUpScoreLabels(i);
    }
    setUpSumRow();
  }
  
  private void setUpNameRow() {
    TextView tv = new TextView(getBaseContext());
    Layout.layoutLabelCell(tv);
    tv.setText(R.string.name_hint);
    tv.setBackgroundColor(Color.WHITE);
    tv.setTextColor(Color.BLACK);
    TableRow nameRow = addRow();
    nameRow.addView(tv);
  }
  
  private void setUpScoreLabels(int scoreCellNum) {
    TableRow scoreRow = new TableRow(getBaseContext());
    tableLayout().addView(scoreRow);
    TextView tv = new TextView(getBaseContext());
    Layout.layoutLabelCell(tv);
    Row r = currentGame.getRow(scoreCellNum);
    tv.setText(r.name);
    tv.setBackgroundColor(r.color);
    tv.setTextColor(Color.BLACK);
    scoreRow.addView(tv);
  }
  
  private void setUpSumRow() {
    TableRow sumRow = new TableRow(getBaseContext());
    tableLayout().addView(sumRow);
    TextView tv = new TextView(getBaseContext());
    Layout.layoutSumLabel(tv);
    tv.setText(R.string.total);
    tv.setBackgroundColor(Color.BLACK);
    tv.setTextColor(Color.WHITE);
    sumRow.addView(tv);
  }

  private void makePlayers(int numPlayers) {
    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);

    for (int i=1; i<=numPlayers; i++)
      players.add(new Player(currentGame,tableLayout(),getBaseContext(),imm));
  }
  
}
