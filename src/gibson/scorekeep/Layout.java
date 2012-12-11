package gibson.scorekeep;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Layout {
  
  private static final int ROW_HEIGHT = 40;
  private static final int TEXT_SIZE = 15;
  private static final int THICK = 3;
  
  static void layoutScoreCell(TextView tv) {
    sharedLayout(tv);
  }
  
  static void layoutNameCell(TextView tv) {
    sharedLayout(tv);
    margins(tv,1,1,1,THICK);
  }
  
  static void layoutLabelCell(TextView tv) {
    sharedLayout(tv);
    margins(tv,1,1,THICK,1);
  }
  
  static void layoutSumLabel(TextView tv) {
    sharedLayout(tv);
    margins(tv,1,THICK,THICK,1);
  }
  
  static void layoutNameLabelCell(TextView tv) {
    sharedLayout(tv);
    margins(tv,1,1,THICK,1);
  }
  
  static void layoutSumCell(TextView tv) {
    sharedLayout(tv);
    margins(tv,1,THICK,1,1);
  }
  
  static private void sharedLayout(TextView tv) {
    // edit view & text view have different default text sizes
    tv.setHeight(ROW_HEIGHT);
    tv.setTextSize(TEXT_SIZE);
    tv.setPadding(5, 1, 0, 0);
    tv.setGravity(Gravity.BOTTOM);
    margins(tv,1,1,1,1);
  }
  
  private static void margins(View v, int l,int t,int r,int b) {
    TableRow.LayoutParams tableRowParams= new TableRow.LayoutParams();
    tableRowParams.setMargins(l, t, r, b);
    v.setLayoutParams(tableRowParams);
  }
  
  public static void layoutClearButton(Button clear,Context ctxt,TableLayout table) {
    clear.setBackgroundColor(Color.WHITE);
    clear.setTextColor(Color.BLACK);
    clear.setTextSize(18);
    //clear.set font???
    TableLayout tl = new TableLayout(ctxt);
    TableRow tr = new TableRow(ctxt);
    tr.setGravity(Gravity.CENTER);

    TableRow.LayoutParams lp = new TableRow.LayoutParams();
    lp.weight = 1;
    lp.setMargins(5, 20, 87, 15);
    clear.setLayoutParams(lp);
    
    tr.addView(clear);
    tl.addView(tr);
    table.addView(tl);
  }
 
  

}
