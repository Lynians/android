package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraAnimation;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;
import com.naver.maps.map.util.FusedLocationSource;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private FusedLocationSource locationSource;
    NaverMap myMap;

    Marker marker = new Marker();
    LatLng myPosition = new LatLng(35.945282, 126.682157);
    InfoWindow infoWindow = new InfoWindow();

    public void deleteVisibility(View view){
        Button btn1 = (Button) findViewById(R.id.terrain);
        Button btn2 = (Button) findViewById(R.id.satellite);
        Button btn3 = (Button) findViewById(R.id.hybrid);

        btn1.setVisibility(view.GONE);
        btn2.setVisibility(view.GONE);
        btn3.setVisibility(view.GONE);
    }
    public void deleteVisibility2(View view){
        Button btn1 = (Button) findViewById(R.id.building);
        Button btn2 = (Button) findViewById(R.id.traffic);

        btn1.setVisibility(view.GONE);
        btn2.setVisibility(view.GONE);
    }


    public void moveCamera(){
        CameraUpdate cameraUpdate = CameraUpdate.scrollTo(myPosition).animate(CameraAnimation.Fly,5000);
        myMap.moveCamera(cameraUpdate);
    }

    public void putMarker(){
        marker.setPosition(myPosition);
        marker.setMap(myMap);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment)fm.findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map, mapFragment).commit();
        }

        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.myMap = naverMap;
        View view1 = null;
        Button btn1 = (Button) findViewById(R.id.terrain);
        Button btn2 = (Button) findViewById(R.id.satellite);
        Button btn3 = (Button) findViewById(R.id.hybrid);
        Button btn4 = (Button) findViewById(R.id.menu);
        Button btn5 = (Button) findViewById(R.id.layout);
        Button btn6 = (Button) findViewById(R.id.building);
        Button btn7 = (Button) findViewById(R.id.traffic);

        btn1.setVisibility(view1.GONE);
        btn2.setVisibility(view1.GONE);
        btn3.setVisibility(view1.GONE);
        btn6.setVisibility(view1.GONE);
        btn7.setVisibility(view1.GONE);

        moveCamera();
        putMarker();

        btn1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteVisibility(view);
                myMap.setMapType(NaverMap.MapType.Terrain);
            }
        });

        btn2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteVisibility(view);
                myMap.setMapType(NaverMap.MapType.Satellite);
            }
        });

        btn3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteVisibility(view);
                myMap.setMapType(NaverMap.MapType.Hybrid);
            }
        });

        btn4.setOnClickListener(new Button.OnClickListener() {
            Button btn1 = (Button) findViewById(R.id.terrain);
            Button btn2 = (Button) findViewById(R.id.satellite);
            Button btn3 = (Button) findViewById(R.id.hybrid);
            @Override
            public void onClick(View view) {
                if(btn1.getVisibility() == view.GONE) {
                    btn1.setVisibility(view.VISIBLE);
                } else {
                    btn1.setVisibility(view.GONE);
                }
                if(btn2.getVisibility() == view.GONE) {
                    btn2.setVisibility(view.VISIBLE);
                } else {
                    btn2.setVisibility(view.GONE);
                }
                if(btn3.getVisibility() == view.GONE) {
                    btn3.setVisibility(view.VISIBLE);
                } else {
                    btn3.setVisibility(view.GONE);
                }
            }
        });
        btn5.setOnClickListener(new Button.OnClickListener() {
            Button btn1 = (Button) findViewById(R.id.building);
            Button btn2 = (Button) findViewById(R.id.traffic);
            @Override
            public void onClick(View view) {
                if(btn1.getVisibility() == view.GONE) {
                    btn1.setVisibility(view.VISIBLE);
                } else {
                    btn1.setVisibility(view.GONE);
                }
                if(btn2.getVisibility() == view.GONE) {
                    btn2.setVisibility(view.VISIBLE);
                } else {
                    btn2.setVisibility(view.GONE);
                }
            }
        });
        btn6.setOnClickListener(new Button.OnClickListener() {
            Button btn1 = (Button) findViewById(R.id.building);
            boolean click = true;
            @Override
            public void onClick(View view) {
                if (click) {
                    btn1.setBackgroundColor(Color.YELLOW);
                    click = false;
                    myMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_BUILDING, true);
                } else {
                    btn1.setBackgroundColor(Color.parseColor("#d3d3d3"));
                    click = true;
                    myMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_BUILDING, false);
                }
            }
        });
        btn7.setOnClickListener(new Button.OnClickListener() {
            Button btn1 = (Button) findViewById(R.id.traffic);
            boolean click = true;
            @Override
            public void onClick(View view) {
                if (click) {
                    btn1.setBackgroundColor(Color.YELLOW);
                    click = false;
                    myMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_TRAFFIC, true);
                } else {
                    btn1.setBackgroundColor(Color.parseColor("#d3d3d3"));
                    click = true;
                    myMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_TRAFFIC, false);
                }
            }
        });
        infoWindow.setAdapter(new InfoWindow.DefaultTextAdapter(context) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "군산대학교";
            }
        });
        Overlay.OnClickListener listener = overlay -> {
            marker = (Marker)overlay;

            if (marker.getInfoWindow() == null) {
                infoWindow.open(marker);
            } else {
                infoWindow.close();
            }

            return true;
        };
    }
}