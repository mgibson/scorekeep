package gibson.scorekeep;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

public class Player {
  
  private ViewGroup tableLayout;
  private Context context;
  private List<Integer> scores = new ArrayList<Integer>();
  private List<EditText> scoreCells = new ArrayList<EditText>();
  private EditText nameCell; 
  private int numScoreCells;
  private EditText sumCell;
  private Game game;
  private InputMethodManager inputMethodManager;
  
  private static int CELL_WIDTH = 40;

  public Player(Game game, ViewGroup tableLayout, Context context, InputMethodManager imm) {
    this.inputMethodManager = imm;
    this.tableLayout = tableLayout;
    this.context = context;
    this.numScoreCells = game.numberOfScoreRows();
    this.game = game;
    addNameCell();
    // go through rows and add a column to each one and add a listener to that column
    // that listener will have the column # which will be used to update list of numbers
    setUpScoringCells();
    addSumCell();
  }
  
  private void addNameCell() {
    nameCell = new EditText(context);
    nameCell.setBackgroundColor(Color.WHITE);
    nameCell.setTextColor(Color.BLACK);
    Layout.layoutNameCell(nameCell);
    KeyboardHider l = new KeyboardHider();
    nameCell.setOnEditorActionListener(l);
    nameCell.setImeOptions(EditorInfo.IME_ACTION_DONE);
    getNameRow().addView(nameCell);
  }
  
  private class KeyboardHider implements TextView.OnEditorActionListener {
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
      inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
      TableRow.LayoutParams tableRowParams= new TableRow.LayoutParams();
      int left=3, top=-6, right=3, bottom=10;
      tableRowParams.setMargins(left, top, right, bottom);
      return true;
    }
  }
  
  private TableRow getNameRow() {
    return (TableRow)tableLayout.getChildAt(0);
  }
  
  private void setUpScoringCells() {
    for (int i=0; i<numScoreCells; i++) {
      setUpScoreCell(i);
      scores.add(0); // initialize list with 0's
    }
  }
  
  private void setUpScoreCell(int scoreCellNum) {
    EditText et = new EditText(context);
    et.setWidth(CELL_WIDTH);
    Layout.layoutScoreCell(et);
    int color = game.getRow(scoreCellNum).color;
    color += 0x222222;
    et.setBackgroundColor(color);
    et.setRawInputType(InputType.TYPE_CLASS_NUMBER);
    et.setTextColor(Color.BLACK);
    ScoreChangeListener l = new ScoreChangeListener(scoreCellNum);
    et.setOnEditorActionListener(l);
    et.setOnFocusChangeListener(l);
    et.setImeOptions(EditorInfo.IME_ACTION_DONE);
    // this assume score cells starts at 2nd cell 1 
    // may want to eventually have top of table be more flexible
    ViewGroup vg = (ViewGroup)tableLayout.getChildAt(scoreCellNum+1);
    vg.addView(et);
    scoreCells.add(et);
  }
  
  private class ScoreChangeListener implements TextView.OnEditorActionListener, OnFocusChangeListener {
    private int scoreCellNum;
    public ScoreChangeListener(int scoreCellNum) {
      this.scoreCellNum = scoreCellNum;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
      // should also check for loss of focus
      if (actionId == EditorInfo.IME_ACTION_SEARCH ||
          actionId == EditorInfo.IME_ACTION_DONE ||
          event.getAction() == KeyEvent.ACTION_DOWN &&
          event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
        recalc(v);
        return false;
      }
      return false;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
      if (!(v instanceof TextView)) return; // shouldnt happen
      if (!hasFocus) recalc((TextView)v);
    }
    
    private void recalc(TextView v) {
      Integer value = 0;
      try { value = Integer.parseInt(v.getText().toString()); }
      catch (NumberFormatException e) { return;}
      scores.set(scoreCellNum,value);
      calcSum();
    }
  }
  
  
  private void calcSum() {
    int sum = 0;
    for (int sc : scores) sum += sc;
    sumCell.setText(sum+"");
  }

  private void addSumCell() {
    sumCell = new EditText(context);
    Layout.layoutSumCell(sumCell);
    sumCell.setBackgroundColor(Color.WHITE);
    sumCell.setTextColor(Color.BLACK);
    getSumRow().addView(sumCell);
  }
  
  private ViewGroup getSumRow() {
    return (ViewGroup)tableLayout.getChildAt(numScoreCells+1); // presumptious?
  }

  void clear() {
    nameCell.setText("");
    for(EditText cell : scoreCells) cell.setText("");
    for(int i=0;i<scores.size();i++) scores.set(i, 0);
    //for(Integer score : scores)
    //calcSum();
    sumCell.setText("");
  }

}
