package pl.umg.traincomplete;

import android.provider.BaseColumns;

public  class FeedReaderContract {
    private FeedReaderContract() {}

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "STATS";
        public static final String COLUMN_NAME_STAT1 = "stat1";
        public static final String COLUMN_NAME_STAT2 = "stat2";
    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_NAME_STAT1 + " TEXT," +
                    FeedEntry.COLUMN_NAME_STAT2 + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;


}
