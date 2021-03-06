package com.endpoint.ghair.activities_fragments.activity_contact;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import com.endpoint.ghair.R;
import com.endpoint.ghair.databinding.ActivityContactBinding;
import com.endpoint.ghair.interfaces.Listeners;
import com.endpoint.ghair.language.Language;
import com.endpoint.ghair.models.ContactUsModel;
import com.endpoint.ghair.remote.Api;
import com.endpoint.ghair.share.Common;
import com.endpoint.ghair.tags.Tags;

import java.io.IOException;
import java.util.Locale;

import io.paperdb.Paper;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactActivity extends AppCompatActivity implements Listeners.BackListener , Listeners.ContactListener {
    private ActivityContactBinding binding;
    private String lang;
    private ContactUsModel contactUsModel;

    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(Language.updateResources(newBase, Paper.book().read("lang", Locale.getDefault().getLanguage())));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contact);
        initView();

    }


    private void initView() {
        contactUsModel = new ContactUsModel();
        binding.setContactUs(contactUsModel);
        binding.setContactListener(this);
        Paper.init(this);
        lang = Paper.book().read("lang", Locale.getDefault().getLanguage());binding.setLang(lang);
        binding.setBackListener(this);


    }


    @Override
    public void back() {
        finish();
    }

    @Override
    public void sendContact(ContactUsModel contactUsModel)
    {

        if (contactUsModel.isDataValid(this))
        {
sendmessge(contactUsModel);
        }

    }

    private void sendmessge(ContactUsModel contactUsModel) {
        try {
            ProgressDialog dialog = Common.createProgressDialog(this, getString(R.string.wait));
            dialog.setCancelable(false);
            dialog.show();
            Api.getService(Tags.base_url)
                    .sendContact(contactUsModel.getName(),contactUsModel.getEmail(),contactUsModel.getSubject(),contactUsModel.getMessage())
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            dialog.dismiss();
                            if (response.isSuccessful() ) {
                               // Toast.makeText(ContactActivity.this, getString(R.string.suc), Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                try {
                                    Log.e("errorssss",response.code()+"_"+response.errorBody().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                if (response.code() == 422) {
                                  //  Toast.makeText(ContactActivity.this,getString(R.string.failed), Toast.LENGTH_SHORT).show();
                                } else if (response.code() == 500) {
                                 //   Toast.makeText(ContactActivity.this, "Server Error", Toast.LENGTH_SHORT).show();

                                }else
                                {
                                 //   Toast.makeText(ContactActivity.this, getString(R.string.failed), Toast.LENGTH_SHORT).show();

                                    try {

                                        Log.e("errorssss",response.code()+"_"+response.errorBody().string());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            try {
                                dialog.dismiss();
                                if (t.getMessage() != null) {
                                    Log.e("error", t.getMessage());
                                    if (t.getMessage().toLowerCase().contains("failed to connect") || t.getMessage().toLowerCase().contains("unable to resolve host")) {
                                        Toast.makeText(ContactActivity.this, R.string.something, Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(ContactActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }

                            } catch (Exception e) {
                            }
                        }
                    });
        } catch (Exception e) {
        }
    }
}
