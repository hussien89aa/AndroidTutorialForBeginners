private SQLiteDatabase db;
    static final String DATABASE_NAME = "College";
    static final String STUDENTS_TABLE_NAME = "students";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_DB_TABLE =
            " CREATE TABLE IF NOT EXISTS " + STUDENTS_TABLE_NAME +
                    " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " name TEXT NOT NULL, " +
                    " age TEXT NOT NULL);";

    /**
     * Helper class that actually creates and manages
     * the provider's underlying data repository.
     */
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL(CREATE_DB_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " +  STUDENTS_TABLE_NAME);
            onCreate(db);
        }
    }



    //user class

    DatabaseHelper dbHelper = new DatabaseHelper(context);
      db = dbHelper.getWritableDatabase();

       public long insert(Uri uri, ContentValues values)
    {
        long rowID = db.insert( STUDENTS_TABLE_NAME, "", values);
        /*
        if (rowID > 0)
        {
            //added
        }*/
      return rowID;  
}
//select
//projection coloum
String[] projection={"name"};
          public Cursor query(Uri uri, String[] projection, String selection,String[] selectionArgs, String sortOrder)
    {
         SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(STUDENTS_TABLE_NAME);
//get all
           //     qb.setProjectionMap(STUDENTS_PROJECTION_MAP);
 //get one
            //    qb.appendWhere( _ID + "=" + uri.getPathSegments().get(1));
            

  Cursor c = qb.query(db,   projection, selection, selectionArgs,null, null, sortOrder);

return c;
}

public int delete(Uri uri, String selection, String[] selectionArgs)
    {
        int count = db.delete(STUDENTS_TABLE_NAME, selection, selectionArgs);
         return count;
    }

 public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs)
    {
        int count = 0;

                count = db.update(STUDENTS_TABLE_NAME, values, selection, selectionArgs);
           
               // count = db.update(STUDENTS_TABLE_NAME, values, _ID + " = " + uri.getPathSegments().get(1) +
                   //     (!TextUtils.isEmpty(selection) ? " AND (" +selection + ')' : ""), selectionArgs);
         
        return count;
    }


      // Add a new student record
        ContentValues values = new ContentValues();
// insert value
        values.put(StudentsProvider.NAME, "a");
        values.put(StudentsProvider.Age,"b");
// define the play to insert the values in
        insert( values);
 
//select
    Cursor c = query(students, null, null, null, "name");
// move through all items
        if (c.moveToFirst()) {
            do{
                // load the record name and age and id
                Toast.makeText(this,
                        c.getString(c.getColumnIndex(StudentsProvider._ID)) +
                                ", " +  c.getString(c.getColumnIndex( StudentsProvider.NAME)) +
                                ", " + c.getString(c.getColumnIndex( StudentsProvider.Age)),
                        Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());






