package pl.umg.traincomplete;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import pl.umg.traincomplete.trains.ITrainAdapter;
import pl.umg.traincomplete.trains.TrainAdapter;
import pl.umg.traincomplete.trains.TrainStore;

public class ChooseTrainActivity extends AppCompatActivity {

    private final ITrainAdapter actions = train -> {
        TrainStore.setPlayerTrain(train);
        Intent intent = new Intent(getApplicationContext(), MainGameActivity.class);
        System.out.println("call");
        startActivity(intent);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_train);

        TrainAdapter adapter = new TrainAdapter(this, TrainStore.trains, actions);
        ListView trainsListView = (ListView) findViewById(R.id.trainsList);
        trainsListView.setAdapter(adapter);
    }


}