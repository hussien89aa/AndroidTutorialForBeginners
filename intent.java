
//intent send
  Intent intent=new Intent(this,Main2Activity.class);
        intent.putExtra("name","hussein alruabye");
        intent.putExtra("age","26");
          startActivity(intent);


    // intent reciver
         Bundle b= getIntent().getExtras();
        TextView textView2=(TextView)findViewById(R.id.textView2);
        textView2.setText("Name:"+ b.getString("name")+
                "age:"+ b.getString("age"));