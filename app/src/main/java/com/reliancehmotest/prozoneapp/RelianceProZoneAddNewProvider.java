package com.reliancehmotest.prozoneapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;

public class RelianceProZoneAddNewProvider extends AppCompatActivity {
    private String onBoardStVal;
    private String provTypeVal;
    private Context c = RelianceProZoneAddNewProvider.this;
    private Uri imgUri;
    private String imgPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reliance_pro_zone_add_new_provider);
        try {
            setOnBoardStatus();
            setProvType();
            getImg(R.id.img_edt_user_prof).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getLocalImgPath();
                }
            });
        } catch (Exception e) {
            Toast.makeText(c, "error as a result of " + e.getLocalizedMessage(), RelianceAppProZoneConstants.TOAST_LONG_LENGTH).show();
        }
    }

    private TextInputEditText getTInpEdt(int id){
        return findViewById(id);
    }

    private ImageView getImg(int id){
        return findViewById(id);
    }

    private ImageButton getImgBut(int id){
        return findViewById(id);
    }





    private void setOnBoardStatus(){
        ArrayAdapter arrAd = getArrayAdapter(this, R.array.onb_stat_values, android.R.layout.simple_spinner_item, android.R.layout.simple_dropdown_item_1line);
        Spinner relSpin = getSpin(R.id.spin_onboard_stat);
        relSpin.setAdapter(arrAd);
        relSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onBoardStVal = (String) parent.getItemAtPosition(position);
                if(onBoardStVal.equals("---Select Registration Status---")){
                    Toast.makeText(c, " Make a selection ", RelianceAppProZoneConstants.TOAST_LONG_LENGTH).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setProvType(){
        ArrayAdapter arrAd = getArrayAdapter(this, R.array.provid_type_val, android.R.layout.simple_spinner_item, android.R.layout.simple_dropdown_item_1line);
        Spinner relSpin = getSpin(R.id.spin_onb_prov);
        relSpin.setAdapter(arrAd);
        relSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                provTypeVal = (String) parent.getItemAtPosition(position);
                if(provTypeVal.equals("---Select Provider Type---")){
                    Toast.makeText(c, " Make a selection ", RelianceAppProZoneConstants.TOAST_LONG_LENGTH).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private Spinner getSpin(int id){
        return findViewById(id);
    }

    /**
     * This method is used for returning arrayAdapter
     *
     * @param arr_cont_id
     * @param lay_id
     * @param dropId
     * @return
     */
    private ArrayAdapter getArrayAdapter(Context c, int arr_cont_id, int lay_id, int dropId) {
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(c, arr_cont_id, lay_id);
        arrayAdapter.setDropDownViewResource(dropId);
        return arrayAdapter;
    }


    public void getLocalImgPath() {
        try {
            Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
            galleryIntent.setType("image/*");
            startActivityForResult(galleryIntent, RelianceAppProZoneConstants.GALLERY_REQUEST_CODE);


        } catch (NullPointerException np) {
            Toast.makeText(c, "Error as a result of : " + np.getLocalizedMessage(), RelianceAppProZoneConstants.TOAST_LONG_LENGTH).show();
        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RelianceAppProZoneConstants.GALLERY_REQUEST_CODE && resultCode == RESULT_OK) {
            try {
                imgUri = data.getData();
                imgPath = data.getDataString();
                Glide.with(c).load(imgPath).into(getImg(R.id.img_edt_user_prof));
            } catch (NullPointerException np) {
                Toast.makeText(c, "Error as a result of : " + np.getLocalizedMessage(), RelianceAppProZoneConstants.TOAST_LONG_LENGTH).show();
            }
        }
    }



    public Uri getImgUri(){
        Uri uri = imgUri;
        return uri;}




}