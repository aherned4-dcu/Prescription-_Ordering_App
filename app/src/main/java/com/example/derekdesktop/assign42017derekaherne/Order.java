package com.example.derekdesktop.assign42017derekaherne;

import android.content.ContextWrapper;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.io.File;



public class Order extends Fragment {
    /**
     * Order class extends Fragment
     *
     * @return the order layout is returned as a view.
     */
    Uri URI = null;
    private static final int PICK_FROM_GALLERY = 101;
    public String photoFileName = "photo.jpg";
    private ImageView mPhotoCapturedImageView;
    private String mImageFileLocation = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.order, container, false);

        ImageView imView = (ImageView) rootView.findViewById(R.id.imageView);
        imView.setOnClickListener(new View.OnClickListener() {

            @Override
                public void onClick(View view) {
                /** Citation: Class contains code adapted from
                 * URL: //https://developer.android.com/guide/components/intents-common.html
                 * Permission: MIT Licence Retrieved on:26th Ddecember 2017  */

                /** Citation: Class contains code adapted from
                 *  https://www.nigeapptuts.com/camera-intent-android-nougat-support/
                 * Permission: MIT Licence Retrieved on:26th Ddecember 2017  */

                // open camera in still mode
                Intent takePictureIntent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
                // Create a File reference to access to future access
               // File photoFile;

               // photoFile = null;//getPhotoFileUri(photoFileName);
               ContextWrapper cw = new ContextWrapper(getActivity());
                //String authorities = cw.getApplicationContext().getPackageName() + ".fileprovider";
                //Uri fileProvider = FileProvider.getUriForFile(cw.getApplicationContext(), authorities, photoFile);
                //takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);
                //URI = Uri.fromFile(photoFile);
                // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
                // So as long as the result is not null, it's safe to use the intent.
                if (takePictureIntent.resolveActivity(cw.getPackageManager()) != null) {
                    // Start the image capture intent to take photo
                    startActivityForResult(takePictureIntent, PICK_FROM_GALLERY);
                }
            }
        });

        return rootView;
    }
}