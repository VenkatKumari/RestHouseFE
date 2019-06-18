package com.sih.resthousefe;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnull;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;


public class Gen_OTP extends AppCompatActivity {
    @BindView(R.id.txtid)TextView Getid;
    @BindView(R.id.rec_otp)Button Rec_otp;
    @BindView(R.id.enter_otp)EditText Enter_Otp;
    @BindView(R.id.otp)Button Otp;
    @BindView(R.id.sendotp)TextView Resend_otp;
    @BindView(R.id.empidf)EditText empid;
    OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
    ApolloClient apolloClient = ApolloClient.builder()
            .serverUrl("https://resthousegraphql.herokuapp.com/graphql")
            .okHttpClient(okHttpClient)
            .build();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;



    String ID = "[0-9]{11}";
    String OTP = "[0-9]{6}";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gen_otp1);
        ButterKnife.bind(this);
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d("Tag", "onVerificationCompleted:" + credential);
                // [START_EXCLUDE silent]
//                mVerificationInProgress = false;
                // [END_EXCLUDE]

                // [START_EXCLUDE silent]
                // Update the UI and attempt sign in with the phone credential
//                updateUI(STATE_VERIFY_SUCCESS, credential);
                // [END_EXCLUDE]
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w("Tag", "onVerificationFailed", e);
                // [START_EXCLUDE silent]
//                mVerificationInProgress = false;
                // [END_EXCLUDE]

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    // [START_EXCLUDE]
//                    mPhoneNumberField.setError("Invalid phone number.");
                    // [END_EXCLUDE]
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    // [START_EXCLUDE]
                    Snackbar.make(findViewById(android.R.id.content), "Quota exceeded.",
                            Snackbar.LENGTH_SHORT).show();
                    // [END_EXCLUDE]
                }

                // Show a message and update the UI
                // [START_EXCLUDE]
//                updateUI(STATE_VERIFY_FAILED);
                // [END_EXCLUDE]
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d("Tag", "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later
//                mVerificationId = verificationId;
//                mResendToken = token;

                // [START_EXCLUDE]
                // Update UI
//                updateUI(STATE_CODE_SENT);
                // [END_EXCLUDE]
            }
        };
        Rec_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!Getid.getText().toString().matches(ID)) {


                    if (Getid.getText().toString().trim().length() == 0){
                        Getid.setError("Enter Employee Id");
                        Getid.requestFocus();

                    }

                    else if (!Getid.getText().toString().matches(ID)){
                        Getid.setError("Enter 11 digit ID");
                        Getid.requestFocus();
                    }

                }
            }
        });

        Otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getotp("9003982002");
                MobQuery mobQuery = MobQuery.builder().id(empid.getText().toString()).build();
                apolloClient.query(mobQuery)
                        .enqueue(new ApolloCall.Callback<MobQuery.Data>() {
                            @Override
                            public void onResponse(@Nonnull Response<MobQuery.Data> response) {
                                String phoneNumber = response.data().employee().get(0).empmobile;
                                Log.d("TAg", "mobno: " + phoneNumber);

                            }

                            @Override
                            public void onFailure(@Nonnull ApolloException e) {

                                if (Enter_Otp.getText().toString().trim().length() == 0){
                                    Enter_Otp.setError("Enter Employee Id");
                                    Enter_Otp.requestFocus();

                                }

                                else if (!Enter_Otp.getText().toString().matches(OTP)){
                                    Enter_Otp.setError("Enter 6 digit Employee ID");
                                    Enter_Otp.requestFocus();
                                }
                            }
                        });

            }
        });

    }


    private void getotp(String no) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                no,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);
    }




    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Tag", "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w("Tag", "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }
}
