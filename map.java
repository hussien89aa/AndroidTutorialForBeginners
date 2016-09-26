  // Add a marker in Sydney and move the camera
       LatLng sydney = new LatLng(-34, 151);
        LatLng sydney2 = new LatLng(-34.01, 151.02);
        LatLng sydney3 = new LatLng(-34.02, 151.03);


        //without image
        mMap.addMarker(new MarkerOptions().position(sydney).
                title("Marker in Sydney") );

//with image
   mMap.addMarker(new MarkerOptions().position(sydney).
                title("Marker in Sydney")
        .snippet("details here")
        .icon(BitmapDescriptorFactory.fromResource(R.drawable.android)));

        mMap.addCircle(new CircleOptions()
        .center(sydney)
        .radius(200)
        .strokeColor(Color.RED)
                .fillColor(Color.BLUE)
        );

        mMap.addPolyline( new PolylineOptions()
        .add(sydney,sydney2)
        .width(25)
        .color(Color.BLUE)
        .geodesic(true));

        mMap.addPolygon(new PolygonOptions()
        .add(sydney,sydney2,sydney3)
        );




        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,14));