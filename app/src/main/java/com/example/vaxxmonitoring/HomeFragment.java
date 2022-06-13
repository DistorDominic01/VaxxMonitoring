package com.example.vaxxmonitoring;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryDataEventListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeFragment extends Fragment implements LocationListener {

    private View mView;
    private GoogleMap mMap;
    private Location mLocation;

    private LocationManager mLocationManager;
    private GeoFire mGeoPersonAvailable;

    private DatabaseReference mDatabase;
    private FirebaseUser mUser;

    public HomeFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        mLocationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Toast.makeText(getContext(), "On Attach", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getContext(), "On Resume", Toast.LENGTH_SHORT).show();

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5, 500, (LocationListener) this);
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5, 500, (LocationListener) this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.home_fragment, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                mMap = googleMap;
            }
        });

        return mView;
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        Toast.makeText(getContext(), "On Location change", Toast.LENGTH_LONG).show();
        if (location != null) {
            mLocation = location;

            //mMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).title("Me"));
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("I'M HERE");
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
            mMap.addMarker(markerOptions);


            mMap.addMarker(new MarkerOptions().position(new LatLng( 14.1894, 121.1634))
                    .title("Justin Rubillos")
                    .snippet("Justin Rubilos : PZIHER : Jan 2022")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

            mMap.addMarker(new MarkerOptions().position(new LatLng( 14.1894, 121.1652))
                    .title("Gene Romuga")
                    .snippet("Gene Romuga : PZIHER : Jan 2022")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

            mMap.addMarker(new MarkerOptions().position(new LatLng( 14.1890, 121.1651))
                    .title("Steven Universe")
                    .snippet("Steven Universe : PZIHER : Jan 2022")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

            mMap.addMarker(new MarkerOptions().position(new LatLng( 14.1885, 121.1657))
                    .title("Jorge Maunahan")
                    .snippet("Jorge Maunahan : PZIHER : Jan 2022")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

            mMap.addMarker(new MarkerOptions().position(new LatLng( 14.1881, 121.1655))
                    .title("Marco Banda")
                    .snippet("Marco Banda : PZIHER : Jan 2022")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

            mMap.addMarker(new MarkerOptions().position(new LatLng( 14.1880, 121.1649))
                    .title("Marco Banda")
                    .snippet("Marco Banda : PZIHER : Jan 2022")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

            mMap.addMarker(new MarkerOptions().position(new LatLng( 14.1879, 121.1650))
                    .title("Jane Doe")
                    .snippet("Jane Doe : PZIHER : Jan 2022")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

            mMap.addMarker(new MarkerOptions().position(new LatLng( 14.1878, 121.1651))
                    .title("Janice Baguilat")
                    .snippet("Janice Baguilat : PZIHER : Jan 2022")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

            mMap.addMarker(new MarkerOptions().position(new LatLng( 14.1915, 121.1663))
                    .title("Dominic Distor")
                    .snippet("Dominic Distor : MODERNA : June 2022")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

            mMap.addMarker(new MarkerOptions().position(new LatLng( 14.1901, 121.1644))
                    .title("Kobe Vicente")
                    .snippet("Kobe Vicente : Sinovax : April 2022")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

            mMap.addMarker(new MarkerOptions().position(new LatLng( 14.1915, 121.1686))
                    .title("John Maala")
                    .snippet("John Maala: Sinovax : Feb 2022")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

            mMap.addMarker(new MarkerOptions().position(new LatLng( 14.1924, 121.1681))
                    .title("Joseph DelaCruz")
                    .snippet("Joseph DelaCruz : Sinovax : March 2022")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

            mMap.addMarker(new MarkerOptions().position(new LatLng( 14.1925, 121.1676))
                    .title("Kobe Ho")
                    .snippet("JKobe Ho : MODERNA : March 2021")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

            mMap.addMarker(new MarkerOptions().position(new LatLng( 14.1912, 121.1672))
                    .title("Peter Parker")
                    .snippet("Peter Parker: MODERNA : March 2021")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

            mMap.addMarker(new MarkerOptions().position(new LatLng( 14.1914, 121.1667))
                    .title("Seth Rogen")
                    .snippet("Seth Rogen : MODERNA : March 2021")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

            mMap.addMarker(new MarkerOptions().position(new LatLng( 14.1929, 121.1667))
                    .title("Jonah Hill")
                    .snippet("Jonah Hill : Sinovax : May 2021")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

            mMap.addMarker(new MarkerOptions().position(new LatLng( 14.1901, 121.1664))
                    .title("Peter Mamaril")
                    .snippet("Peter Mamaril : Unvaccinated")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            mMap.addMarker(new MarkerOptions().position(new LatLng( 14.1894, 121.1677))
                    .title("Mark Barretto")
                    .snippet("Mark Barretto : Unvaccinated")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            mMap.addMarker(new MarkerOptions().position(new LatLng( 14.1899, 121.1677))
                    .title("Luvy Espartero")
                    .snippet("Luvy Espartero : Unvaccinated")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            mMap.addMarker(new MarkerOptions().position(new LatLng( 14.1910, 121.1647))
                    .title("Luvy Espartero")
                    .snippet("Luvy Espartero : Unvaccinated")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            mMap.addMarker(new MarkerOptions().position(new LatLng( 14.1898, 121.1638))
                    .title("Mark Rufallo")
                    .snippet("Mark Rufallo : Unvaccinated")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            mMap.addMarker(new MarkerOptions().position(new LatLng( 14.1895, 121.1636))
                    .title("Steven Rogers")
                    .snippet("Steven Rogers : Unvaccinated")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            mMap.addMarker(new MarkerOptions().position(new LatLng( 14.1904, 121.1637))
                    .title("Russel Espino")
                    .snippet("Russel Espino : Unvaccinated")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            mMap.addMarker(new MarkerOptions().position(new LatLng( 14.1906, 121.1641))
                    .title("Karl Magabo")
                    .snippet("Karl Magabo : Unvaccinated")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            mMap.addMarker(new MarkerOptions().position(new LatLng( 14.1907, 121.1659))
                    .title("Vincent Fabellore")
                    .snippet("Vincent Fabellore : Unvaccinated")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            mMap.addMarker(new MarkerOptions().position(new LatLng( 14.1903, 121.1655))
                    .title("Vincent Fabellore")
                    .snippet("Vincent Fabellore : Unvaccinated")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            mMap.addMarker(new MarkerOptions().position(new LatLng( 14.1901, 121.1659))
                    .title("Armin Garmin")
                    .snippet("Armin Garmin : Unvaccinated")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));








            CameraPosition cameraPosition = CameraPosition.builder().target(new LatLng(location.getLatitude(), location.getLongitude())).zoom(15).bearing(0).tilt(45).build();
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            updateLocationCoordinates();

            getAvailablePerson();
        } else {
            Toast.makeText(getActivity(), "null location", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Toast.makeText(getContext(), "On Pause", Toast.LENGTH_SHORT).show();
        mLocationManager.removeUpdates(this);
    }

    private void updateLocationCoordinates() {

        String mUid = mUser.getUid();
        Log.i("MUID", mUid);
        DatabaseReference runnersAvailable = mDatabase.child("personAvailable").child(mUid);
        mGeoPersonAvailable = new GeoFire(runnersAvailable);

        double lat = mLocation.getLatitude();
        double lon = mLocation.getLongitude();
//
//
        mGeoPersonAvailable.setLocation(mUid, new GeoLocation(lat, lon), new GeoFire.CompletionListener() {
            @Override
            public void onComplete(String key, DatabaseError error) {

            }
        });

    }
    private void getAvailablePerson() {

        DatabaseReference personAvailable = mDatabase.child("personAvailable");
        mGeoPersonAvailable = new GeoFire(personAvailable);

        GeoQuery geoQuery = mGeoPersonAvailable.queryAtLocation(new GeoLocation(mLocation.getLatitude(), mLocation.getLongitude()), 50);
        geoQuery.addGeoQueryDataEventListener(new GeoQueryDataEventListener() {
            @Override
            public void onDataEntered(DataSnapshot dataSnapshot, GeoLocation location) {
                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                    if(!TextUtils.equals(mUser.getUid().toString(),dataSnapshot.getKey().toString())){
                        LatLng latLng = new LatLng(location.latitude,location.longitude);
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(latLng);
                        markerOptions.title("Other");
                        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                        mMap.addMarker(markerOptions);
                    }
                }
            }

            @Override
            public void onDataExited(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onDataMoved(DataSnapshot dataSnapshot, GeoLocation location) {

            }

            @Override
            public void onDataChanged(DataSnapshot dataSnapshot, GeoLocation location) {

            }

            @Override
            public void onGeoQueryReady() {

            }

            @Override
            public void onGeoQueryError(DatabaseError error) {

            }
        });
 }

}


