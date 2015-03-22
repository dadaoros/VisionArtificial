package com.project.coursera.dailyselfie;

import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;


public class FullScreenImageFragment extends Fragment {
    private static final String TAG = "TAG";
    ImageView imageView;
    String mCurrentPhotoPath;
    ImageButton btnClose;
    public FullScreenImageFragment() {}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCurrentPhotoPath=getArguments().getString(MainActivity.PHOTO_PATH_KEY);
        }else
            Log.e("Argumentos","Nulos");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_full_screen_image, container, false);
        btnClose= (ImageButton)view.findViewById(R.id.btnExit);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        return view ;
    }
    public void onResume(){
        super.onResume();
        if(imageView==null) {
            imageView = (ImageView) getActivity().findViewById(R.id.full_screen_imageview);
            //performFileSearch();
            //setPic();
            File f=new File(mCurrentPhotoPath);
            try {
                imageView.setImageURI(Uri.fromFile(f));
            } catch (Exception e){

            }
        }
    }
    public void onStop(){
        super.onStop();
        imageView.destroyDrawingCache();
    }




}
