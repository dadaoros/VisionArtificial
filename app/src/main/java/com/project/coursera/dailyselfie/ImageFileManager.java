package com.project.coursera.dailyselfie;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 21/03/15.
 */
public class ImageFileManager {
    private static ImageFileManager imageFileManager=new ImageFileManager();
    private static File STORAGE_DIR = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
    private static final String TAG = "Debug";

    public List<Selfie> getSelfies() {

        List<Selfie> selfieList= new ArrayList<Selfie>();
        if (STORAGE_DIR.exists()) {
// For now we are starting from fresh and rebuilding the list.
            Log.i(TAG, "Storage directory exists!!");
            for (File file : STORAGE_DIR.listFiles(new SelfieFileFilter())) {
                selfieList.add(
                        new Selfie(
                                file.getName()
                                , new Date(file.lastModified())
                                , getSelfieThumbnail(file.getAbsolutePath())
                                , file.getAbsolutePath()
                        )
                );
            }
        }
        return selfieList;
    }

    private Bitmap getSelfieThumbnail(String absolutePath) {
        return null;
    }
    public File createImageFile(String mCurrentPhotoPath) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "SELFIE_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        image.getPath();
        mCurrentPhotoPath = image.getAbsolutePath();
        Log.d("absolutePath",mCurrentPhotoPath);
        Log.d("Path", image.toURI().toString());

        return image;
    }
    public static ImageFileManager getImageFileManager(){
        return imageFileManager;
    }
}
