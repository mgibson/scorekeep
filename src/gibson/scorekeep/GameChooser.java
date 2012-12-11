package gibson.scorekeep;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class GameChooser extends Activity {
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.activity_main, menu);
    return true;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.game_chooser);
    
  }

}
