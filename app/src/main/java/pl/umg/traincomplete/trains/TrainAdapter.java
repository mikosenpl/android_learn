package pl.umg.traincomplete.trains;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import pl.umg.traincomplete.R;
public class TrainAdapter extends ArrayAdapter<Train> {

    private final ITrainAdapter actions;

    public TrainAdapter(@NonNull Context context, @NonNull List<Train> objects, ITrainAdapter actions) {
        super(context, 0, objects);

        this.actions = actions;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Train train = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.train_card, parent, false);
        }

        TextView hp = (TextView) convertView.findViewById(R.id.trainHP);
        hp.setText("HP " + train.getHp());

        TextView damage = (TextView) convertView.findViewById(R.id.trainDamage);
        damage.setText("Damage " + train.getDamage());

        TextView defense = (TextView) convertView.findViewById(R.id.trainDefense);
        defense.setText("Defense " + train.getDefense());

        ImageView img = (ImageView) convertView.findViewById(R.id.trainImage);
        img.setImageResource(train.getImage());

        LinearLayout card = (LinearLayout) convertView.findViewById(R.id.trainCard);
        card.setOnClickListener(view -> {
            actions.onTrainSelect(train);
        });

        return convertView;
    }
}