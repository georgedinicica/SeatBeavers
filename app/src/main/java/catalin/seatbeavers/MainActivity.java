package catalin.seatbeavers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_beavers);
        startButton=(Button) findViewById(R.id.startButton);
    }
    public void startSeatingBeavers(View view) {
        Intent intent=new Intent(this,PlaneActivity.class);

        startActivity(intent);
    }

    public void startNamesApp(View view) {
        Intent intent=new Intent(this,NamesActivity.class);

        startActivity(intent);
    }
}