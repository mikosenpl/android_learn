package pl.umg.traincomplete;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainStartActivity extends AppCompatActivity {
    boolean appExited = false;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView photo;
    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_start);
        FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_STAT1, 80);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_STAT2, 150);
        long newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);
        db = dbHelper.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_STAT1,
                FeedReaderContract.FeedEntry.COLUMN_NAME_STAT2
        };

        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_STAT1 + " = ?";
        String[] selectionArgs = { "My Stats" };

        String sortOrder =
                FeedReaderContract.FeedEntry.COLUMN_NAME_STAT2 + " DESC";

        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
        startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(view -> startGame());
        photo = findViewById(R.id.takenPhoto);
        Button photoButton = findViewById(R.id.takePhoto);
        photoButton.setOnClickListener(view -> capturePhoto("trainImage"));
    }

    public void capturePhoto(String targetFilename) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT,
//                Uri.withAppendedPath(locationForPhotos, targetFilename));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("Zdjęcie zostało utworzone " + resultCode + " " + requestCode);

        if (requestCode == REQUEST_IMAGE_CAPTURE) {
//            Bitmap thumbnail = data.getParcelableExtra("data");
            // Do other work with full size photo saved in locationForPhotos
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            photo.setImageBitmap(bitmap);
        }
    }

    public void startGame() {
        Intent intent = new Intent(getApplicationContext(), ChooseTrainActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onPause() {
        this.appExited = true;
        super.onPause();
    }

    @Override
    protected void onResume() {
        if (appExited)
            Toast.makeText(getApplicationContext(), getString(R.string.resume), Toast.LENGTH_LONG).show();

        this.appExited = false;
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        System.out.println("ON DESTROY");
        super.onDestroy();
    }
}