package com.project.coursera.dailyselfie;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
    private static final String TAG = "Tag";
    ImageFileManager imageFileManager;
    ListView selfieListView;
    View view;
    List<Selfie> selfies;
    public PhotoListFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imageFileManager=ImageFileManager.getImageFileManager();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_photo_list, container, false);
        initialize();
        return view;
    }
    private void initialize(){
        selfies =imageFileManager.getSelfies();
        selfieListView=(ListView)view.findViewById(R.id.list_selfies);
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
    public void update(){
        initialize();
    }







}
