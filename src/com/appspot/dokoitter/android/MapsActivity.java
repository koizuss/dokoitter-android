package com.appspot.dokoitter.android;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

import android.location.LocationManager;
import android.os.Bundle;

public class MapsActivity extends MapActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final MapView mapView = new MapView(this, getResources().getString(R.string.maps_key));
        mapView.setEnabled(true);
        mapView.setClickable(true);
        mapView.setBuiltInZoomControls(true);
        final MyLocationOverlay overlay =
            new MyLocationOverlay(getApplicationContext(), mapView);
        overlay.onProviderEnabled(LocationManager.GPS_PROVIDER); // GPS を使用する
        overlay.enableMyLocation();
        overlay.runOnFirstFix(new Runnable() {
            @Override
            public void run() {
                mapView.getController().animateTo(
                    overlay.getMyLocation()); // 現在位置を自動追尾する
            }
        });
        mapView.getOverlays().add(overlay);
        mapView.invalidate();
        setContentView(mapView);
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}