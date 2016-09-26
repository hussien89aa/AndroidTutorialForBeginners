package com.example.hussienalrubaye.seekbar;

    import android.content.Context;
    import android.content.pm.PackageManager;
    import android.database.Cursor;
    import android.media.MediaPlayer;
    import android.net.Uri;
    import android.os.Build;
    import android.provider.MediaStore;
    import android.support.v4.app.ActivityCompat;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.view.LayoutInflater;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.AdapterView;
    import android.widget.ArrayAdapter;
    import android.widget.BaseAdapter;
    import android.widget.ListAdapter;
    import android.widget.ListView;
    import android.widget.SeekBar;
    import android.widget.SimpleCursorAdapter;
    import android.widget.TextView;
    import android.widget.Toast;

    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity  {
    SeekBar seekBar1;
    MyCustomAdapter Adapater;
MediaPlayer mp;
    int SeekValue;
    ListView ls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar1=(SeekBar)findViewById(R.id.seekBar);
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SeekValue=progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
   mp.seekTo(SeekValue);
            }
        });
        ls=(ListView ) findViewById(R.id.listView);
        CheckUserPermsions();
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SongInfo songInfo=SongsList.get(position);
                mp=new MediaPlayer();
                try {
                    mp.setDataSource(songInfo.Path);
                    mp.prepare();
                    mp.start();
                    seekBar1.setMax(mp.getDuration());
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

        mythread my= new mythread();
        my.start();

    }


    ArrayList<SongInfo>  SongsList =new ArrayList<SongInfo>();
    /* online media
public ArrayList<SongInfo> getAllSongs() {
    SongsList.clear();
    SongsList.add(new SongInfo("http://server6.mp3quran.net/thubti/001.mp3","Fataha","bakar","quran"));
    SongsList.add(new SongInfo("http://server6.mp3quran.net/thubti/002.mp3","Bakara","bakar","quran"));
    SongsList.add(new SongInfo("http://server6.mp3quran.net/thubti/003.mp3","Al-Imran","bakar","quran"));
    SongsList.add(new SongInfo("http://server6.mp3quran.net/thubti/004.mp3","An-Nisa'","bakar","quran"));
    SongsList.add(new SongInfo("http://server6.mp3quran.net/thubti/005.mp3","Al-Ma'idah","bakar","quran"));
    SongsList.add(new SongInfo("http://server6.mp3quran.net/thubti/006.mp3","Al-An'am","bakar","quran"));
    SongsList.add(new SongInfo("http://server6.mp3quran.net/thubti/007.mp3","Al-A'raf","bakar","quran"));

    return SongsList;
}*/
    //local
    public ArrayList<SongInfo> getAllSongs() {
        Uri allsongsuri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";

     Cursor   cursor = getContentResolver().query(allsongsuri, null, selection, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String    song_name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                    String fullpath = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                    String    album_name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                    String   artist_name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                    SongsList.add(new SongInfo(fullpath,song_name,album_name,album_name));

                } while (cursor.moveToNext());

            }
            cursor.close();

        }

        return SongsList;
    }


    class  mythread extends  Thread{
        public void run() {


        while(true){
            try {
                Thread.sleep(1000);

            }  catch (Exception e) {}
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                 //seek bar   seekBar1.setProgress(mp .getCurrentPosition());
                    if (mp !=null)
                    seekBar1.setProgress(mp.getCurrentPosition());
                }
            });



        }
    }}

// adapter
private class MyCustomAdapter extends BaseAdapter {
    ArrayList<SongInfo> fullsongpath ;
public  MyCustomAdapter(ArrayList<SongInfo> fullsongpath ){
    this.fullsongpath=fullsongpath;
}





    @Override
    public int getCount() {
        return fullsongpath.size();
    }

    @Override
    public String getItem(int position) {
        return null ;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mInflater = getLayoutInflater();
                View    myView=  mInflater.inflate(R.layout.item, null);
        SongInfo s=fullsongpath.get(position);
        TextView textView = (TextView)myView.findViewById(R.id.textView);
        textView.setText(s.song_name);
        TextView textView1 = (TextView)myView.findViewById(R.id.textView2);
        textView1.setText(s.artist_name);
        return myView;
    }

}


    public void buplay(View view) {
        mp.start();
        //*** play
    }

    public void bustop(View view) {
        //*** stop
        mp.stop();
    }

    public void bupuse(View view) {
        //*** pause
        mp.pause();
    }

    void CheckUserPermsions(){
        if ( Build.VERSION.SDK_INT >= 23){
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) !=
                    PackageManager.PERMISSION_GRANTED  ){
                requestPermissions(new String[]{
                                android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_PERMISSIONS);
                return ;
            }
        }

        LoadSng();

    }
    //get acces to location permsion
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    LoadSng();
                } else {
                    // Permission Denied
                    Toast.makeText( this,"denail" , Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    void LoadSng(){
        Adapater=new  MyCustomAdapter(getAllSongs());
        ls.setAdapter(Adapater);
    }

}
