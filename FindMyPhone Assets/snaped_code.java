

/*
steps to build app
1- enter phone number
2-pick my trackers
3-track me service
4-find my phone list
5- view on map
6- save into file
*/
// pick phone number

void PickContact(){
    Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
    startActivityForResult(intent, PICK_CONTACT);
}

  // Declare
    static final int PICK_CONTACT=1;
    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        switch (reqCode) {
            case (PICK_CONTACT) :
                if (resultCode == Activity.RESULT_OK) {

                    Uri contactData = data.getData();
                    Cursor c =  getContentResolver().query(contactData, null, null, null, null);
                    if (c.moveToFirst()) {


                        String id =c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));

                        String hasPhone =c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                        String cNumber="No number";
                        if (hasPhone.equalsIgnoreCase("1")) {
                            Cursor phones = getContentResolver().query(
                                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id,
                                    null, null);
                            phones.moveToFirst();
                           cNumber =ManagmentOperations.FormatPhoneNumber (phones.getString(phones.getColumnIndex("data1")));
                            System.out.println("number is:"+cNumber);
                        }
                        String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));


                        if (cNumber.length()<10){
                            ShowAlert(getResources().getString(R.string.PhoneNumber_error));
                            return;
                        }
                       //update firebase and
                        //update list
                        //update database
                    }
                }
                break;
        }
    }
    //**************************************************************
    //format phone number
       public static String FormatPhoneNumber(String Oldnmber){
      try{
          String numberOnly= Oldnmber.replaceAll("[^0-9]", "");
          if(Oldnmber.charAt(0)=='+') numberOnly="+" +numberOnly ;
          if (numberOnly.length()>=10)
              numberOnly=numberOnly.substring(numberOnly.length()-10,numberOnly.length());
          return(numberOnly);
      }
      catch (Exception ex){
          return(" ");
      }
    }

 //**************************************************************

//list my tracking
   databaseReference.child("Users").child(GlobalInfo.PhoneNumber).
                child("Finders").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Map<String, Object> td = (HashMap<String, Object>) dataSnapshot.getValue();

                listnewsData.clear();
                if (td == null)  //no one allow you to find him
                {
                    listnewsData.add(new AdapterItems("NoTicket", "no_desc"));
                     myadapter.notifyDataSetChanged();
                    return;
                }
                // List<Object> values = td.values();


                // get all contact to list
                ArrayList<AdapterItems> list_contact = new ArrayList<AdapterItems>();
                Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
                while (cursor.moveToNext()) {
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

                    String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    list_contact.add(new AdapterItems(  name,GlobalInfo.FormatPhoneNumber(phoneNumber)
                           ));


                }


// if the name is save chane his text
                // case who find me
                String tinfo;
                for (  String Numbers : td.keySet()) {
                    for (AdapterItems cs : list_contact) {

                        //IsFound = SettingSaved.WhoIFindIN.get(cs.Detals);  // for case who i could find list
                        if (cs.PhoneNumber.length() > 0)
                            if (Numbers.contains(cs.PhoneNumber)) {
                                listnewsData.add(new AdapterItems(cs.UserName, cs.PhoneNumber));
                                break;
                            }

                    }

                }
                myadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                // Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
//Global file
                 Context context;
    SharedPreferences ShredRef;
    public  GlobalInfo(Context context){
        this.context=context;
        ShredRef=context.getSharedPreferences("myRef",Context.MODE_PRIVATE);
    }

    void SaveData(){
        String MyTrackersList="" ;
        for (Map.Entry  m:GlobalInfo.MyTrackers.entrySet()){
            if (MyTrackersList.length()==0)
                MyTrackersList=m.getKey() + "%" + m.getValue();
            else
                MyTrackersList =MyTrackersList+ "%" + m.getKey() + "%" + m.getValue();

        }

        if (MyTrackersList.length()==0)
            MyTrackersList="empty";


        SharedPreferences.Editor editor=ShredRef.edit();
        editor.putString("MyTrackers",MyTrackersList);
        editor.putString("PhoneNumber",PhoneNumber);
        editor.commit();
    }

    void LoadData(){
        MyTrackers.clear();
          PhoneNumber= ShredRef.getString("PhoneNumber","empty");
        String MyTrackersList= ShredRef.getString("MyTrackers","empty");
        if (!MyTrackersList.equals("empty")){
            String[] users=MyTrackersList.split("%");
            for (int i=0;i<users.length;i=i+2){
                MyTrackers.put(users[i],users[i+1]);
            }
        }


        if (PhoneNumber.equals("empty")){

            Intent intent=new Intent(context, Login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }

    }


