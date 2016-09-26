

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



