package com.example.appointmenttothedoctor.ui.clinic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.appointmenttothedoctor.R;
import com.example.appointmenttothedoctor.databinding.FragmentClinicBinding;
import com.example.appointmenttothedoctor.databinding.FragmentHistoryBinding;
import com.example.appointmenttothedoctor.ui.history.HistoryViewModel;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Circle;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;


public class ClinicFragment extends Fragment {

    private FragmentClinicBinding binding;

    private MapView mapview;


    private final Point TARGET_LOCATION = new Point(53.632471,55.923876 );

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        ClinicViewModel dashboardViewModel =
                new ViewModelProvider(this).get(ClinicViewModel.class);

        binding = FragmentClinicBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        createCart(savedInstanceState);


        return root;
    }

    void createCart(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        MapKitFactory.initialize(getActivity());
        mapview = binding.mapview;
       // Point point = new Point(53.632155,55.923876 );
        mapview.getMap().move(
                new CameraPosition(TARGET_LOCATION, 18.47f, 0.0f, 0.0f),
                new Animation(Animation.Type.LINEAR, 0),null
                );

        Bitmap bitmap = getBitmapFromVectorDrawable(requireContext(), R.drawable.logo_64);

        //mapview.getMap().getMapObjects().addPlacemark(point).setIcon(ImageProvider.fromResource(requireContext(), android.R.drawable.checkbox_on_background));
        mapview.getMap().getMapObjects().addPlacemark(TARGET_LOCATION).setIcon(ImageProvider.fromBitmap(bitmap));

        mapview.getMap().getMapObjects().addPlacemark(new Point(53.629276, 55.937113)).setIcon(ImageProvider.fromBitmap(bitmap));
    }



    @Override
    public void onStop() {
        mapview.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapview.onStart();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public static Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

}
