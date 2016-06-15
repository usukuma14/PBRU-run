package pbru.it.sukuma.pbrurun;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
//import android.support.v4.media.routing.MediaRouterJellybeanMr1;
import android.util.Log;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double userLatADouble, userLngADouble;
    private LocationManager locationManager;
    private Criteria criteria;
    private String[] userLoginStrings;
    private Mydata myData;
    private static final String urlEditLocation = "http://swiftcodingthai.com/pbru3/edit_location.php";
    private static final String urlUser = "http://swiftcodingthai.com/pbru3/get_user.php";
    private int[] avataInts,buildInts;
    private double [] buildLatDoubles, buildLngDoubles;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        userLoginStrings = getIntent().getStringArrayExtra("Login");

        myData = new Mydata();
        avataInts = myData.getAvatarInts();



        //setup location
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2 = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }   //Main Method

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Maps Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://pbru.it.sukuma.pbrurun/http/host/path")
        );
        AppIndex.AppIndexApi.start(client2, viewAction);
    }


    private class ConnectedLocation extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(urlUser).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();


            } catch (Exception e) {
                return null;
            }

            //return null;
        }   // doIn

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("pbruV6", "s ==> " + s);

            try {

                JSONArray jsonArray = new JSONArray(s);
                for (int i=0;i<jsonArray.length();i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    double douLat = Double.parseDouble(jsonObject.getString("Lat"));
                    double douLng = Double.parseDouble(jsonObject.getString("Lng"));

                    LatLng latLng = new LatLng(douLat, douLng);
                    String strTitle = jsonObject.getString("Name");

                    int intIndex = Integer.parseInt(jsonObject.getString("Avata"));

                    mMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(strTitle)
                    .icon(BitmapDescriptorFactory.fromResource(avataInts[intIndex])));



                }   //for

            } catch (Exception e) {
                e.printStackTrace();
            }




        }   //onPost
    }


    @Override
    protected void onResume() {
        super.onResume();

        locationManager.removeUpdates(locationListener);

        Location fromISPLocation = myFindLoction(LocationManager.NETWORK_PROVIDER);

        if (fromISPLocation != null) {
            userLatADouble = fromISPLocation.getLatitude();
            userLngADouble = fromISPLocation.getLongitude();


        }

        Location fromGPSLocation = myFindLoction(LocationManager.GPS_PROVIDER);
        if (fromGPSLocation != null) {
            userLatADouble = fromGPSLocation.getLatitude();
            userLngADouble = fromGPSLocation.getLongitude();
        }

        Log.d("pbruV5", "userLat ==> " + userLatADouble);
        Log.d("pbruV5", "userLng ==> " + userLngADouble);


    }
    //Log.d("pbruV5", "userLat ==> " )

    @Override
    protected void onStop() {
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Maps Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://pbru.it.sukuma.pbrurun/http/host/path")
        );
        AppIndex.AppIndexApi.end(client2, viewAction);

        locationManager.removeUpdates(locationListener);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2.disconnect();
    }

    public Location myFindLoction(String strProvider) {

        Location location = null;

        if (locationManager.isProviderEnabled(strProvider)) {

            locationManager.requestLocationUpdates(strProvider, 1000, 10, locationListener);
            location = locationManager.getLastKnownLocation(strProvider);


        } else {
            Log.d("pbruV4", "Cannot fine Location");

        }


        return location;
    }   //myFindLocatin


    public final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

            userLatADouble = location.getLatitude();
            userLngADouble = location.getLongitude();


        }   //onLocationChange

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {


        }   //onStateChange

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Setup Center map
        LatLng latLng = new LatLng(myData.getLatADouble(), myData.getLngADouble());
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));

        //Create my loop
        createMyloop();

    }   //onMap

    private void createMyloop() {

        editUserLocationToServer();

        makeAllMarker();


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                createMyloop();

            }
        }, 3000);


    }

    private void makeAllMarker() {

        mMap.clear();

        ConnectedLocation connectedLocation = new ConnectedLocation();
        connectedLocation.execute();

        buildInts = myData.getBuildIconInts();
        buildLatDoubles = myData.getBuildLatDoubles();
        buildLngDoubles = myData.getBuildLngDoubles();

        for (int i=0;i<buildInts.length;i++) {
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(buildLatDoubles[i], buildLngDoubles[i]))
                            .icon(BitmapDescriptorFactory.fromResource(buildInts[i])
                    ));

        }


    }   //makeAllMarker

    private void editUserLocationToServer() {

        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("isAdd", "true")
                .add("id", userLoginStrings[0])
                .add("Lat", Double.toString(userLatADouble))
                .add("Lng", Double.toString(userLngADouble))
                .build();

        Request.Builder builder = new Request.Builder();
        Request request = builder.url(urlEditLocation).post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {

            }
        });


    }
}   //Main Class
