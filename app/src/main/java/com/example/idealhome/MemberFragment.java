package com.example.idealhome;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.idealhome.Pojo.Member;
import com.example.idealhome.Pojo.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class MemberFragment extends Fragment {

    private ImageView personImage;
    private Button selectPhoto,btnupload;
    private EditText namEdit;
    private static final int REQUEST_IMAGE_CAPTURE = 0;
    private static final int TAKE_IMAGE_CAPTURE = 1;
    private List<String>nameArray;
    private Context context;


    public MemberFragment() {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View vh = inflater.inflate(R.layout.fragment_member,container,false);
            personImage = vh.findViewById(R.id.person);
            selectPhoto= vh.findViewById(R.id.btnperson);
            btnupload = vh.findViewById(R.id.btnupload);
            namEdit = vh.findViewById(R.id.namEdit);
            DatabaseQuery query = new DatabaseQuery(getActivity());



        selectPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectImage(getActivity());
                    Toasty.success(getActivity(), "Data Not Inserted", Toasty.LENGTH_SHORT).show();
                }
            });

        btnupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = namEdit.getText().toString();
                String bitmapimage = String.valueOf(personImage);
                Member member = new Member(name, bitmapimage);
                boolean check_addMember= query.addMember(member);
               if (check_addMember){
                   Toasty.success(getActivity(),"Data Insert Successfully",Toasty.LENGTH_SHORT).show();
               }else{
                   Toasty.success(getActivity(),"Data Not Inserted",Toasty.LENGTH_SHORT).show();
               }
            }
        });

        return vh;
    }


    private void selectImage(Context context) {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                   startActivityForResult(takePicture,REQUEST_IMAGE_CAPTURE);


                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , TAKE_IMAGE_CAPTURE);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        personImage.setImageBitmap(selectedImage);
                    }

                    break;
                case 1:
                    if ( resultCode == RESULT_OK && null != data) {
                        Uri selectedImage = data.getData();
                        Bitmap bitmap = null;
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Bitmap resized = Bitmap.createScaledBitmap(bitmap, 200, 200, true);
                        personImage.setImageBitmap(resized);
                    }

                        break;
            }
        }
    }


}
