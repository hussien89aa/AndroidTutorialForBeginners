  NotificationCompat.Builder nbuild= new NotificationCompat.Builder(this);
        nbuild.setContentTitle("Danger")
                .setContentText("it will run soon")
                .setSmallIcon(R.drawable.play);

        NotificationManager manager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        manager.notify(1,nbuild.build());
        manager.cancel(1);