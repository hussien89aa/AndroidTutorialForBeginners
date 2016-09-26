 /* this will be our strJsonstring
  {
   "info":
   {
      "name":"Hussein",
      "age":27
   },
   "jobs":[
   {
      "id":1,
      "title":"Developer",
      "desc":"Develop apps for user",
   },
    {
      "id":2,
      "title":"Tester",
      "desc":"test apps",
   }
]

}

*/

  String JsonFromURL="{" +
                "'info':{'name':'hussein','age':27 }," +
                "'jobs':" +
                "[" +
                "{'id':1, 'title':'developer','desc':'nyc'}," +
                "{'id':2, 'title':'developer','desc':'nyc'}," +
                "{'id':3, 'title':'developer','desc':'nyc'}" +
                "]" +
                "}";
        try {
            JSONObject json= new JSONObject(JsonFromURL);
            JSONObject info=json.getJSONObject("info");
            String name=info.getString("name");
            int age=info.getInt("age");
            JSONArray jobs=json.getJSONArray("jobs");

            for ( int i=0; i<jobs.length();i++){
               JSONObject Jobs= jobs.getJSONObject(i)  ;
                String title=Jobs.getString("title");
                String desc=Jobs.getString("desc");
                int id=Jobs.getInt("id");

            }
        }
        catch (Exception ex){}
 