  
/********
The previous(commented) code is outdated. Android has brought some changes for Android O version & above 
and added notification channel
********/
/*NotificationCompat.Builder nbuild= new NotificationCompat.Builder(this);
        nbuild.setContentTitle("Danger")
                .setContentText("it will run soon")
                .setSmallIcon(R.drawable.play);

        NotificationManager manager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        manager.notify(1,nbuild.build());
        manager.cancel(1);*/

NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            //Add this if statement code for Android O and above
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel("NOTIFICATION_CHANNEL_ID", 
                                                                      "NOTIFICATION_NAME", 
                                                                      NotificationManager.IMPORTANCE_DEFAULT);
             
                //In NOTIFICATION_CHANNEL_DESCRIPTION, you can write about the type of notification 
                //or anything which describes it in few words.
                channel.setDescription("NOTIFICATION_CHANNEL_DESCRIPTION");
              
                notificationManager.createNotificationChannel(channel);
            }

            if (notificationManager != null) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "YOUR_CHANNEL_ID")
                        //To set icon for notification
                        .setSmallIcon(R.drawable.ic_message)
                        .setContentTitle("TITLE_OF_NOTIFICATION")
                        .setContentText("CONTENT_TO_DISPLAY_IN_NOTIFICATION")
                        //To make notification cancellable
                        .setAutoCancel(true)
                        //To make notification visible in all places
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);

                // To open MainActivity class when notification is clicked
                builder.setContentIntent(PendingIntent.getActivity(getApplicationContext(), 
                                                                   0, 
                                                                   new Intent(getApplicationContext(), MainActivity.class));
                // To launch notification
                notificationManager.notify(0, builder.build());
            }
