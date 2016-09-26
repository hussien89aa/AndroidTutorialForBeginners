  AlertDialog.Builder alert= new AlertDialog.Builder(this);
        alert.setMessage("are you sure to delete")
                .setIcon(android.R.drawable.stat_notify_error)
                .setTitle("Alert")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      //do some thing  
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                             //do some thing  
                    }
                }).show();