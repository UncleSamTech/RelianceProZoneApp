package com.reliancehmotest.prozoneapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;

public class RelianceProZoneProviderDetails extends AppCompatActivity {
    Context c = RelianceProZoneProviderDetails.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reliance_pro_zone_provider_details);
        try {
            String name = retrVal("prov_prof_pref_name",MODE_PRIVATE,"prov_prof_pref_name_key");
            String addr = retrVal("prov_prof_pref_addr",MODE_PRIVATE,"prov_prof_pref_addr_key");
            String descr = retrVal("prov_prof_pref_descr",MODE_PRIVATE,"prov_prof_pref_descr_key");
            String state = retrVal("prov_prof_pref_state",MODE_PRIVATE,"prov_prof_pref_state_key");
            String act_status = retrVal("prov_prof_pref_status",MODE_PRIVATE,"prov_prof_pref_status_key");
            String images = retrVal("prov_prof_pref_img",MODE_PRIVATE,"prov_prof_pref_img_key");
            String prov_type = retrVal("prov_prof_pref_type",MODE_PRIVATE,"prov_prof_pref_type_key");
            //setUpProfDetails(name,addr,descr,state,act_status,images,prov_type,getImg(R.id.img_edt_user_prof));
        } catch (Exception e) {
            Toast.makeText(c, "Error as a result of " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public String retrVal(String key,  int mode,String prefKey){
        SharedPreferences sharedPreferences = getSharedPreferences(prefKey,mode);
        //if(sharedPreferences.contains("user_church")){}
        return sharedPreferences.getString(key,null);
    }

    private void setUpProfDetails(String name, String addr, String descr, String state, String act_status, String images, String prov_type, ImageView imgv){
        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(addr) && !TextUtils.isEmpty(descr) && !TextUtils.isEmpty(state) && !TextUtils.isEmpty(act_status) && !TextUtils.isEmpty(images) && !TextUtils.isEmpty(prov_type) ){
            getTInpEdt(R.id.edtProvName).setText(name);
            getTInpEdt(R.id.edtProvState).setText(state);
            getTInpEdt(R.id.edtProvAddr).setText(addr);
            getTInpEdt(R.id.edtProvDescr).setText(descr);
            getTInpEdt(R.id.edtProvStatus).setText(act_status);
            getTInpEdt(R.id.edtProvType).setText(prov_type);
            Glide.with(c).load(images).into(imgv);

        }

        else{
            Toast.makeText(c, " Oops missing values " , Toast.LENGTH_LONG).show();
        }

    }

    private TextInputEditText getTInpEdt(int id){
        return findViewById(id);
    }

    private ImageView getImg(int id){
        return findViewById(id);
    }


}