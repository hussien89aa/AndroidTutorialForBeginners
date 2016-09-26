package com.hussienalrubaye.androiddemowork;


import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DBManager dbManager;
    EditText etUserName;
    EditText etPassword;
    static int RecordID;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
           etUserName=(EditText)findViewById(R.id.etUserName);
           etPassword=(EditText)findViewById(R.id.etPassword);
           dbManager=new DBManager(this);
    }


    public void buSave(View view) {

        ContentValues values= new ContentValues();
        values.put(DBManager.ColUserName,etUserName.getText().toString());
        values.put(DBManager.ColPassWord,etPassword.getText().toString());
       long id= dbManager.Insert(values);
        if (id>0)
            Toast.makeText(getApplicationContext(),"Data is added and user id:"+id,Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(),"cannot insert",Toast.LENGTH_LONG).show();
     }

    ArrayList<AdapterItems>    listnewsData = new ArrayList<AdapterItems>();
    MyCustomAdapter myadapter;

    public void buLoad(View view) {
        LoadElement();


    }

    void LoadElement()
    {
        String[] SelectionsArgs={ "%"+etUserName.getText().toString()+"%"};
        //add data and view it
        listnewsData.clear();
        Cursor cursor=dbManager.query(null,"UserName like ? ",SelectionsArgs,DBManager.ColUserName);

        if (cursor.moveToFirst()){
            String tableData="";
            do {
                /*tableData+=cursor.getString(cursor.getColumnIndex(DBManager.ColUserName))+ ","+
                        cursor.getString( cursor.getColumnIndex(DBManager.ColPassWord)) +"::";
               */

                listnewsData.add(new AdapterItems(cursor.getInt(cursor.getColumnIndex(DBManager.ColID))
                        ,cursor.getString(cursor.getColumnIndex(DBManager.ColUserName))
                        ,cursor.getString( cursor.getColumnIndex(DBManager.ColPassWord))));

            }while (cursor.moveToNext());

            Toast.makeText(getApplicationContext(),tableData,Toast.LENGTH_LONG).show();
        }

        myadapter=new MyCustomAdapter(listnewsData);

        ListView lsNews=(ListView)findViewById(R.id.LVNews);
        lsNews.setAdapter(myadapter);//intisal with data
    }

    public void buUpdate(View view) {
        ContentValues values= new ContentValues();
        values.put(DBManager.ColUserName,etUserName.getText().toString());
        values.put(DBManager.ColPassWord,etPassword.getText().toString());
        //values.put(DBManager.ColID,RecordID);
        String[] SelectionArgs={String.valueOf(RecordID)};
        dbManager.Update(values,"ID=?",SelectionArgs);
        Toast.makeText(getApplicationContext(),String.valueOf(RecordID),Toast.LENGTH_LONG).show();

    }

    private class MyCustomAdapter extends BaseAdapter {
        public ArrayList<AdapterItems> listnewsDataAdpater ;

        public MyCustomAdapter(ArrayList<AdapterItems>  listnewsDataAdpater) {
            this.listnewsDataAdpater=listnewsDataAdpater;
        }


        @Override
        public int getCount() {
            return listnewsDataAdpater.size();
        }

        @Override
        public String getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater mInflater = getLayoutInflater();
            View myView = mInflater.inflate(R.layout.layout_ticket, null);

            final   AdapterItems s = listnewsDataAdpater.get(position);

            TextView tvID=( TextView)myView.findViewById(R.id.tvID);
            tvID.setText(String.valueOf( s.ID));

            TextView tvUserName=( TextView)myView.findViewById(R.id.tvUserName);
            tvUserName.setText(s.UserName);


            TextView tvPassword=( TextView)myView.findViewById(R.id.tvPassword);
            tvPassword.setText(s.Password);

            Button buDelet=(Button)myView.findViewById(R.id.buDelet);
            buDelet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String[] SelectionArgs={String.valueOf( s.ID)};
                   int count= dbManager.Delete("ID=?",SelectionArgs);
                    if (count>0)
                    {
                        LoadElement();
                    }
                }
            });

            Button buUpdate=(Button)myView.findViewById(R.id.buUpdate);
            buUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    etUserName.setText(s.UserName);
                    etPassword.setText(s.Password);
                    RecordID=s.ID;
                }
            });
            return myView;
        }

    }
}
