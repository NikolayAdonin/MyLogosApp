package com.example.logosapp.fragments

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.logosapp.R
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.ScaleBarOverlay
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay
import org.osmdroid.views.overlay.gridlines.LatLonGridlineOverlay2
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider

import org.osmdroid.views.overlay.compass.CompassOverlay

class NavigationFragment : Fragment() {
    private lateinit var map: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var osm = inflater.inflate(R.layout.fragment_navigation, container, false)
        map = osm.findViewById(R.id.mapview)
        map.setTileSource(TileSourceFactory.MAPNIK);
        val mapController = map.controller
        mapController.setZoom(16)
        val startPoint = GeoPoint(50.6107, 36.5802);
        mapController.setCenter(startPoint);
        //50.3455 36.3445

        val dm : DisplayMetrics = osm.resources.displayMetrics
        val scaleBarOverlay = ScaleBarOverlay(map)
        scaleBarOverlay.setCentred(true)
        scaleBarOverlay.setScaleBarOffset(dm.widthPixels / 2, 10);
        map.overlays.add(scaleBarOverlay);

        val rotationGestureOverlay = RotationGestureOverlay(context, map);
        rotationGestureOverlay.isEnabled
        map.setMultiTouchControls(true);
        map.overlays.add(rotationGestureOverlay);

        var compassOverlay = CompassOverlay(context, InternalCompassOrientationProvider(context), map)
        compassOverlay.enableCompass()
        map.overlays.add(compassOverlay)

        return osm
    }

}