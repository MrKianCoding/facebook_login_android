package com.example.facebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView iconLayout, buttonSwitchPassword;
    EditText editTextUserName, editTextPassword;
    ConstraintLayout bannerLayout, footer;
    Button buttonLogin, buttonCreateNewAccount;
    private boolean passwordIsShow = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findObject();

        buttonCreateNewAccount.setOnClickListener(this::onClick);
        buttonSwitchPassword.setOnClickListener(this::onClick);

        editTextUserName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    if (bannerLayout.getVisibility() == View.VISIBLE){
                        bannerLayout.setVisibility(View.GONE);
                        footer.setVisibility(View.GONE);
                        iconLayout.setVisibility(View.VISIBLE);
                    }
                } else {
                    if (iconLayout.getVisibility() == View.VISIBLE){
                        hideKeyboard(v);
                        iconLayout.setVisibility(View.GONE);
                        bannerLayout.setVisibility(View.VISIBLE);
                        footer.setVisibility(View.VISIBLE);
                    }
                }

            }
        });

        editTextPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    if (bannerLayout.getVisibility() == View.VISIBLE){
                        bannerLayout.setVisibility(View.GONE);
                        footer.setVisibility(View.GONE);
                        iconLayout.setVisibility(View.VISIBLE);
                    }
                } else {
                    if (iconLayout.getVisibility() == View.VISIBLE){
                        hideKeyboard(v);
                        iconLayout.setVisibility(View.GONE);
                        bannerLayout.setVisibility(View.VISIBLE);
                        footer.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!editTextPassword.getText().toString().trim().isEmpty()){
                    buttonSwitchPassword.setVisibility(View.VISIBLE);
                } else {
                    buttonSwitchPassword.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }

    private void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void findObject() {
        iconLayout = findViewById(R.id.iconLayout);
        bannerLayout = findViewById(R.id.bannerLayout);
        footer = findViewById(R.id.footer);
        buttonSwitchPassword = findViewById(R.id.buttonSwitchPassword);
        editTextUserName = findViewById(R.id.editTextUserName);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonCreateNewAccount = findViewById(R.id.buttonCreateNewAccount);
    }


    public void onClick(View view){
        switch (view.getId()){
            case R.id.buttonCreateNewAccount:
                // calling create new account activity
                break;
            case R.id.buttonSwitchPassword:
                if (passwordIsShow){
                    hidePassword();
                } else {
                    showPassword();
                }
                break;
        }
    }

    private void showPassword() {
        buttonSwitchPassword.setImageDrawable(this.getResources().getDrawable(R.drawable.seen_icon));
        editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        editTextPassword.setSelection(editTextPassword.getText().length());
        passwordIsShow = true;
    }

    private void hidePassword() {
        buttonSwitchPassword.setImageDrawable(this.getResources().getDrawable(R.drawable.hide_icon));
        editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        editTextPassword.setSelection(editTextPassword.getText().length());
        passwordIsShow = false;

    }
}