package catalin.seatbeavers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void startSeatingBeavers(View view) {

        Intent intent = new Intent(this, MyPlaneActivity.class);
        startActivity(intent);
    }

    public void  startNamesApp(View view) {
        Intent intent = new Intent(this, MyNameActivity.class);
        startActivity(intent);
    }

    public void startWork(View view) {
        Intent intent = new Intent(this, SK06Activity.class);
        startActivity(intent);
    }
}