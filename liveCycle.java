  protected void onStart(){
        super.onStart();
        Toast.makeText(this,"OnStart",Toast.LENGTH_LONG).show();
    }

    protected void onRestart(){
        super.onRestart();
        Toast.makeText(this,"onRestart",Toast.LENGTH_LONG).show();
    }

    protected void onResume(){
        super.onResume();
        Toast.makeText(this,"onResume",Toast.LENGTH_LONG).show();
    }

    protected void onPause(){
        super.onPause();
        Toast.makeText(this,"onPause",Toast.LENGTH_LONG).show();
    }

    protected void onStop(){
        super.onStop();
        Toast.makeText(this,"onStop",Toast.LENGTH_LONG).show();
    }

    protected void onDestroy(){
        super.onDestroy();
        Toast.makeText(this,"onDestroy",Toast.LENGTH_LONG).show();
    }