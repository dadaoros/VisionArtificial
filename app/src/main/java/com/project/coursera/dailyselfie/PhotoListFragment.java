package com.project.coursera.dailyselfie;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoListFragment extends Fragment {


    public static final String BUNDLE_KEY="bundle key";
    ImageFileManager imageFileManager;
    ListView selfieListView;
    View view;
    public PhotoListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_photo_list, container, false);
        initialize();
        return view;
    }
    public void initialize(){
        imageFileManager=new ImageFileManager();
        selfieListView=(ListView)view.findViewById(R.id.list_selfies);
        List<Selfie> selfies=imageFileManager.getSelfies();
        if(selfies!=null && selfies.isEmpty()==false) {
            SelfiesListAdapter selfiesListAdapter = new SelfiesListAdapter(selfies, view.getContext());
            selfiesListAdapter.setNotifyOnChange(true);
            setListener(selfieListView);
            selfieListView.setAdapter(selfiesListAdapter);
        }
    }
    public void setListener(final ListView listView){

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ((MainActivity)getActivity()).showFullScreen(((Selfie) listView.getAdapter().getItem(position)).getPath());
            }
        });
    }

    /*
    private Bitmap getSelfieThumbnail(String photoPath) {
        Log.i(TAG, "Getting selfie thumbnails");
// Get the dimensions of the View
        int targetW = THUMB_DIM;
        int targetH = THUMB_DIM;
// Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(photoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;
// Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);
// Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;
        return BitmapFactory.decodeFile(photoPath, bmOptions);
    }
    */



}
