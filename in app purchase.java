//1- requestion permmion
  <uses-permission android:name="com.android.vending.BILLING" />

  //2- go to men Android SDK Manager -> add Google Play Billing Library .

  //3- got o AndroidSDK/extra/google/play_billing/sample
  // copy all files in util folder to your app util folder
    //chnage apk name of head all files
  // add aidl folder and copy it content from other project


  //4- add product in your play console

  //5- add this code to your activity

    //***************************Payment***************************
   private static final String TAG ="Your_packge_ID";
    // test ITEM_SKU android.test.purchased
    // test token mypurchasetoken
    static final String ITEM_SKU = "android.test.purchased";
    IabHelper mHelper;

  IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener
            = new IabHelper.OnIabPurchaseFinishedListener() {
        public void onIabPurchaseFinished(IabResult result,
                                          Purchase purchase)
        {
            if (result.isFailure()) {
                // Handle error
                return;
            }
            else if (purchase.getSku().equals(ITEM_SKU)) {
                consumeItem();
                //clickButton.setEnabled(false);
            }

        }
    };
    public void consumeItem() {
        mHelper.queryInventoryAsync(mReceivedInventoryListener);
    }

    IabHelper.QueryInventoryFinishedListener mReceivedInventoryListener
            = new IabHelper.QueryInventoryFinishedListener() {
        public void onQueryInventoryFinished(IabResult result,
                                             Inventory inventory) {

            if (result.isFailure()) {
                // Handle failure
            } else {
                mHelper.consumeAsync(inventory.getPurchase(ITEM_SKU),
                        mConsumeFinishedListener);
            }
        }
    };
    IabHelper.OnConsumeFinishedListener mConsumeFinishedListener =
            new IabHelper.OnConsumeFinishedListener() {
                public void onConsumeFinished(Purchase purchase,
                                              IabResult result) {

                    if (result.isSuccess()) {
                     //  purchuase is done
                    } else {
                        // handle error
                    }
                }
            };
 @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data)
    {
        if (!mHelper.handleActivityResult(requestCode,
                resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
      void PayItem(String ITEM_SKU){
        mHelper.launchPurchaseFlow(this, ITEM_SKU, 10001,
                mPurchaseFinishedListener, "mypurchasetoken");
    }
    //***************************************************************
// 7- in oncreate() add this code
             //In app purchase
        String base64EncodedPublicKey = "your_API_key" ;

        mHelper = new IabHelper(this, base64EncodedPublicKey);

        mHelper.startSetup(new
                                   IabHelper.OnIabSetupFinishedListener() {
                                       public void onIabSetupFinished(IabResult result)
                                       {
                                           if (!result.isSuccess()) {
                                               Log.d(TAG, "In-app Billing setup failed: " + result);
                                           } else {
                                               Log.d(TAG, "In-app Billing is set up OK");

                                           }
                                       }
                                   });


        //8- purchuase
      PayItem(ITEM_SKU);
      

