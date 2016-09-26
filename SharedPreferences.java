public class SharedRef {
    SharedPreferences ShredRef;

 public SharedRef(Context context){
     ShredRef=context.getSharedPreferences("myRef",Context.MODE_PRIVATE);
 }

    public  void SaveData(String UserName,String Password){
        SharedPreferences.Editor editor=ShredRef.edit();
        editor.putString("UserName",UserName);
        editor.putString("Password",Password);
        editor.commit();
    }

    public String LoadData(){
         String FileContent="UserName:"+ShredRef.getString("UserName","No name");
        FileContent+=",Password:"+ShredRef.getString("Password","No Password");
        return FileContent;
    }

}