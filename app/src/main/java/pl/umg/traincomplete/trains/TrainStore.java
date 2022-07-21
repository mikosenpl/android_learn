package pl.umg.traincomplete.trains;

import java.util.ArrayList;
import java.util.Arrays;

import pl.umg.traincomplete.R;

public class TrainStore {

    private static Train playerTrain;

    public static ArrayList<Train> trains = new ArrayList<>(Arrays.asList(
            new Train(50, 10, 1, 10, 2, R.drawable.train),
            new Train(40, 5, 2, 10, 5, R.drawable.train1),
            new Train(80, 20, 3, 10, 3, R.drawable.train2),
            new Train(100, 100, 4, 20, 1, R.drawable.train3)
    ));

    public static Train getPlayerTrain() {
        return playerTrain;
    }

    public static void setPlayerTrain(Train train) {
        playerTrain = train;
    }
}
