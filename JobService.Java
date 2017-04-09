
//1- Add service class
public class MyJobService extends JobService {
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        // CALL URL,
        Log.i("Job"," job is started succefuly");
        jobFinished(jobParameters,false);
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.i("Job"," job is stopped succefuly");
        return false;
    }
}
//2- Add service to Mainfest.xml
/*
  <service android:name=".MyJobService"
            android:permission="android.permission.BIND_JOB_SERVICE"
            />

 */
//33- Call service

            int JOBID=0;
    public void bustart(View view) {

        JobInfo.Builder builder= new
                JobInfo.Builder(JOBID++,
                new ComponentName( this,MyJobService.class));
        builder.setMinimumLatency(1000);
        builder.setOverrideDeadline(2000);
       // builder.setPeriodic(2000);
        //builder.setRequiresCharging(true);
        //builder.setRequiresDeviceIdle(true);
        //builder.setPersisted(true);
       // builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED);
        JobScheduler jobScheduler=
                (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(builder.build());
    }