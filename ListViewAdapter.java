/*
Adapter snaped code for Android apps that have 
user id, job title and job description
*/

// adapter class
public class AdapterItems
 {
  public   int ID;
    public  String JobTitle;
    public  String Description;
//for news details
    AdapterItems( int ID, String JobTitle,String Description)
    {
        this. ID=ID;
        this. JobTitle=JobTitle;
        this. Description=Description;
    }
}

//display news list
    private class MyCustomAdapter extends BaseAdapter {
        public  ArrayList<AdapterItems>  listnewsDataAdpater ;

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
           
              TextView txtJobTitle=( TextView)myView.findViewById(R.id.txtJobTitle);
                txtJobTitle.setText(s.JobTitle);

                return myView;
        }

}

//adapter class
ArrayList<AdapterItems>    listnewsData = new ArrayList<AdapterItems>();
MyCustomAdapter myadapter;
 
 //add data and view it
 listnewsData.add(new AdapterItems(1,"developer"," develop apps"));
 myadapter=new MyCustomAdapter(listnewsData);
ListView  lsNews=(ListView)findViewById(R.id.LVNews);
lsNews.setAdapter(myadapter);//intisal with data

//update  data in listview
  listnewsData.add(new AdapterItems(2,"tester"," test apps"));
   myadapter.notifyDataSetChanged();


        //update

//set on clicklinisner



