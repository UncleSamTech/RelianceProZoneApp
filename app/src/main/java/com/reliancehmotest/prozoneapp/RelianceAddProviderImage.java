package com.reliancehmotest.prozoneapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;

import java.lang.reflect.Array;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RelianceAddProviderImage extends AppCompatActivity {
    private Uri imgUri;
    private String imgPath;
    Context c = RelianceAddProviderImage.this;
    private NetworkInfo networkInfo;
    private ConnectivityManager connMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reliance_add_provider_image);

        getImg(R.id.img_prov_pics).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocalImgPath();
            }
        });

        getBut(R.id.btn_upload_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addImages("provider",getTInp(R.id.tinp_prov_id).getText().toString().trim(),getImgPath(),new Array[]{});
            }
        });

        connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //the NetworkInfo class gets the current state of the device network connection
        networkInfo = connMgr.getActiveNetworkInfo();
    }

    private ImageView getImg(int id){
        return findViewById(id);
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
                Glide.with(c).load(imgUri).into(getImg(R.id.img_prov_pics));
            } catch (NullPointerException np) {
                Toast.makeText(c, "Error as a result of : " + np.getLocalizedMessage(), RelianceAppProZoneConstants.TOAST_LONG_LENGTH).show();
            }
        }
    }



    public Uri getImgUri(){
        Uri uri = imgUri;
        return uri;}


        private String getImgPath(){
        return imgPath;
        }

        private void addImages(String ref,
                String refId,
                String field,
                 Array arrImg []){
            if (networkInfo != null && networkInfo.isConnectedOrConnecting() && networkInfo.isConnected()) {
                if (!TextUtils.isEmpty(ref) && !TextUtils.isEmpty(refId) && TextUtils.isEmpty(field) && arrImg.length > 0){
                    Retrofit retrofit = new Retrofit.Builder().baseUrl(RelianceAppProZoneConstants.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    RelProviderInterf proInt = retrofit.create(RelProviderInterf.class);
                    RelianceUploadFilesModel relianceUploadFilesModel =  new RelianceUploadFilesModel(ref,refId,field,arrImg);
                    Call<RelianceUploadFilesModel> img_upl_call = proInt.relUplFiles(relianceUploadFilesModel);
                    img_upl_call.enqueue(new Callback<RelianceUploadFilesModel>() {
                        @Override
                        public void onResponse(Call<RelianceUploadFilesModel> call, Response<RelianceUploadFilesModel> response) {
                            if (!response.isSuccessful()) {
                                Toast.makeText(c, " Error as a result of " + response.code(), RelianceAppProZoneConstants.TOAST_LONG_LENGTH).show();
                            }

                                Toast.makeText(c, " upload successful" , RelianceAppProZoneConstants.TOAST_LONG_LENGTH).show();
                        }

                        @Override
                        public void onFailure(Call<RelianceUploadFilesModel> call, Throwable t) {
                            Toast.makeText(c, " error as a result of " + t.getLocalizedMessage(),RelianceAppProZoneConstants.TOAST_LONG_LENGTH).show();
                        }
                    });
                }
                else{
                    Toast.makeText(c, "values are missing ", RelianceAppProZoneConstants.TOAST_LONG_LENGTH).show();
                }

            }

        }

        private Button getBut(int id){
        return findViewById(id);
        }

        private TextInputEditText getTInp(int id){
        return findViewById(id);
        }

}