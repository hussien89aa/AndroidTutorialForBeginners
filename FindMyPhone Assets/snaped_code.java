

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

list my tracking
   mDatabase.child("FindMyPhoneUsers").child(SettingSaved.PhoneNumber).child("Finders").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Map<String, Object> td = (HashMap<String, Object>) dataSnapshot.getValue();

                list.clear();
                if (td == null)  //no one allow you to find him
                {
                    list.add(new ListItem("NoTicket", "no_desc", R.drawable.dmap));
                    listv.setAdapter(new UserListAdapter(list));

                    return;
                }
                // List<Object> values = td.values();


                // get all contact to list
                ArrayList<ListItem> list_contact = new ArrayList<ListItem>();
                Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
                while (cursor.moveToNext()) {
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

                    String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    list_contact.add(new ListItem(name, ManagmentOperations.FormatPhoneNumber(phoneNumber), R.drawable.cover));


                }


// if the name is save chane his text
                // case who find me
                String tinfo;
                for (  String Numbers : td.keySet()) {
                    for (ListItem cs : list_contact) {

                        //IsFound = SettingSaved.WhoIFindIN.get(cs.Detals);  // for case who i could find list
                        if (cs.PhoneNumber.length() > 0)
                            if (Numbers.contains(cs.PhoneNumber)) {
                                list.add(new ListItem(cs.UserName, cs.PhoneNumber, R.drawable.dmap));
                                break;
                            }

                    }

                }
                // add new one
                // list.add(new ListItem("Loading", "no_desc", R.drawable.dmap));
              
                //ask for add users to track you
             // if (list.size()==0  && IsDisplayMessage==false) {
                  //  IsDisplayMessage=true;
                  // ShowNoUsers();

               // }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                // Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


