package catalin.seatbeavers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

//    @BindView(R.id.startButton) Button startButton;7
//   @BindView(R.id.activity_seat_beavers) RelativeLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void startSeatingBeavers(View view) {
        Intent intent = new Intent(this, MyPlaneActivity.class);

        startActivity(intent);
    }

    public void startNamesApp(View view) {
        Intent intent = new Intent(this, MyNameActivity.class);
        //Intent intent = new Intent(this, NamesActivity.class);

        startActivity(intent);
    }

    public void anotherStart(View view) {
        Intent intent = new Intent(this, MyNameActivity.class);

        startActivity(intent);
    }
}