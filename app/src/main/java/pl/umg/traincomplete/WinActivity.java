package pl.umg.traincomplete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WinActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
    }

    public void onClickBtn(View view)
    {
        Intent intent = new Intent(getApplicationContext(), MainStartActivity.class);
        startActivity(intent);
    }
}