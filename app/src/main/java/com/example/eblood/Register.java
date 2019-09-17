package com.example.eblood;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.concurrent.TimeUnit;

public class Register extends AppCompatActivity {

    private EditText registrationFormName, registrationFormPhoneNumber, VerificationCodeNumber;
    private Button registrationFormRegister;
    FirebaseDatabase database;
    DatabaseReference myRef;
    Spinner spinnerBloodGroup, registrationFormDivision;
    Spinner spinnerDistrict;
    String string_bloodGroup;
    String[] stringBloodGroup;
    String[] stringDistrict;
    String string_District = "";
    ArrayAdapter<String> bg_adapter;
    ArrayAdapter<String> district_adapter, divisionAdapter;
    boolean isVerified = false;

    FirebaseAuth firebaseAuth;

    String codeSent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("donor");


        registrationFormName = (EditText) findViewById(R.id.registrationFormName);
        registrationFormPhoneNumber = (EditText) findViewById(R.id.registrationFormPhoneNumber);
        VerificationCodeNumber = (EditText) findViewById(R.id.editText_verification_code);

        spinnerFunction();

        firebaseAuth = FirebaseAuth.getInstance();



        findViewById(R.id.registrationFormGetVerificationCode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = registrationFormPhoneNumber.getText().toString();
                setVerificationCode(phone);
            }
        });

        findViewById(R.id.registrationFormRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /*verifyCode();
                if(isVerified){
                    saveData();
                }else {
                    Toast.makeText(Register.this, "Click again", Toast.LENGTH_LONG).show();
                }*/
                saveData();
            }
        });
    }

    private void spinnerFunction() {
        final String Division[] = {"Barisal", "Chittagong", "Dhaka", "Khulna","Mymensingh", "Rajshahi", "Rangpur", "Sylhet"  };
        final String Barisal[] = {"Barguna", "Barisal", "Bhola", "Jhalokati", "Patuakhali", "Pirojpur"};
        final String Chittagong[] = {"Bandarban", "Brahmanbaria", "Chandpur", "Chittagong",
                                    "Comilla", "Cox’s Bazar", "Feni", "Khagrachari", "Lakshmipur",
                                    "Noakhali", "Rangamati" };
        final String Dhaka[] = {"Dhaka","Faridpur","Gazipur","Gopalganj","Kishoreganj","Madaripur",
                                "Manikganj","Munshiganj", "Naraynganj","Narsingdi","Rajbari","Shariatpur","Tangail"};
        final String Khulna[] = {"Bagerhat","Chuadanga","Jessore","Jhenaidah","khulna","kushtia",
                                    "Magura","Meherpur", "Narail","Satkhira"};
        final String Mymensingh[] = {"Jamalpur","Mymensingh","Netrokona","Sherpur"};
        final String Rajshahi[] = {"Bogra","Chapainawabganj","Joypurhat",  "Naogaon","Natore","Pabna","Rajshahi","Sirajganj"};

        final String Rangpur[] = {"Dinajpur","Gaibandha","Kurigram","Lalmonirhat","Nilphamari",
                "Panchagarh","Rangpur", "Thakurgaon"};

        final String Sylhet[] = {"Habiganj","Moulovibazar","Sunamganj","Sylhet"};

        registrationFormDivision = (Spinner) findViewById(R.id.registrationFormDivision) ;
        spinnerDistrict = (Spinner) findViewById(R.id.registrationFormDistrict);

        divisionAdapter =  new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Division);
        registrationFormDivision.setAdapter(divisionAdapter);


        registrationFormDivision.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String district = Division[position];
                if(district =="Barisal" ) {
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(Register.this, android.R.layout.simple_list_item_1, Barisal);
                    spinnerDistrict.setAdapter(adapter1);
                }
                else if(district =="Chittagong" ){
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(Register.this, android.R.layout.simple_list_item_1, Chittagong);
                    spinnerDistrict.setAdapter(adapter2);
                }
                else if(district =="Dhaka" ){
                    ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(Register.this, android.R.layout.simple_list_item_1, Dhaka);
                    spinnerDistrict.setAdapter(adapter3);
                }
                else if(district =="Khulna" ){
                    ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(Register.this, android.R.layout.simple_list_item_1, Khulna);
                    spinnerDistrict.setAdapter(adapter4);
                }
                else if(district =="Mymensingh" ){
                    ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(Register.this, android.R.layout.simple_list_item_1, Mymensingh);
                    spinnerDistrict.setAdapter(adapter4);
                }
                else if(district =="Rajshahi" ){
                    ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(Register.this, android.R.layout.simple_list_item_1, Rajshahi);
                    spinnerDistrict.setAdapter(adapter5);
                }
                else if(district =="Rangpur" ){
                    ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(Register.this, android.R.layout.simple_list_item_1, Rangpur);
                    spinnerDistrict.setAdapter(adapter6);
                }
                else if(district =="Sylhet" ){
                    ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(Register.this, android.R.layout.simple_list_item_1, Sylhet);
                    spinnerDistrict.setAdapter(adapter7);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });


        stringBloodGroup  =getResources().getStringArray(R.array.bloodGroup);
        spinnerBloodGroup = (Spinner) findViewById(R.id.registrationFormBloodGroup);
        //stringDistrict = getResources().getStringArray(R.array.district);
        bg_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stringBloodGroup);
        //district_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stringDistrict);
        spinnerBloodGroup.setAdapter(bg_adapter);
        //spinnerDistrict.setAdapter(district_adapter);
        spinnerBloodGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                string_bloodGroup = "";
                switch (position){
                    case 0:
                        string_bloodGroup = "A+";
                        break;
                    case 1:
                        string_bloodGroup = "A-";
                        break;
                    case 2:
                        string_bloodGroup = "B+";
                        break;
                    case 3:
                        string_bloodGroup = "B-";
                        break;
                    case 4:
                        string_bloodGroup = "AB+";
                        break;
                    case 5:
                        string_bloodGroup = "AB-";
                        break;
                    case 6:
                        string_bloodGroup = "O+";
                        break;
                    case 7:
                        string_bloodGroup = "O-";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*spinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                string_District = "";
                switch (position){
                    case 0:
                        string_District = "Bagerhat";
                        break;
                    case 1:
                        string_District = "Bandarban";
                        break;
                    case 2:
                        string_District = "Barguna";
                        break;
                    case 3:
                        string_District = "Barisal";
                        break;
                    case 4:
                        string_District = "Bhola";
                        break;
                    case 5:
                        string_District = "Bogra";
                        break;
                    case 6:
                        string_District = "Brahmanbaria";
                        break;
                    case 7:
                        string_District = "Chandpur";
                        break;
                    case 8:
                        string_District = "Chapainobabgunj";
                        break;
                    case 9:
                        string_District = "Chittagong";
                        break;
                    case 10:
                        string_District = "Chuadanga";
                        break;
                    case 11:
                        string_District = "Comilla";
                        break;
                    case 12:
                        string_District = "Coxsbazar";
                        break;
                    case 13:
                        string_District = "Dhaka";
                        break;
                    case 14:
                        string_District = "Dinajpur";
                        break;
                    case 15:
                        string_District = "Faridpur";
                        break;
                    case 16:
                        string_District = "Feni";
                        break;
                    case 17:
                        string_District = "Gaibandha";
                        break;
                    case 18:
                        string_District = "Gazipur";
                        break;
                    case 19:
                        string_District = "Gopalganj";
                        break;
                    case 20:
                        string_District = "Habiganj";
                        break;
                    case 21:
                        string_District = "Jamalpur";
                        break;
                    case 22:
                        string_District = "Jhalokati";
                        break;
                    case 23:
                        string_District = "Jhenaidah";
                        break;
                    case 24:
                        string_District = "Jossore";
                        break;
                    case 25:
                        string_District = "Joypurhat";
                        break;
                    case 26:
                        string_District = "Khagrachori";
                        break;
                    case 27:
                        string_District = "Khulna";
                        break;
                    case 28:
                        string_District = "Kishoreganj";
                        break;
                    case 29:
                        string_District = "Kurigram";
                        break;
                    case 30:
                        string_District = "Kushtia";
                        break;
                    case 31:
                        string_District = "Lakshmipur";
                        break;
                    case 32:
                        string_District = "Lalmonirhat";
                        break;
                    case 33:
                        string_District = "Madaripur";
                        break;
                    case 34:
                        string_District = "Magura";
                        break;
                    case 35:
                        string_District = "Manikganj";
                        break;
                    case 36:
                        string_District = "Meherpur";
                        break;
                    case 37:
                        string_District = "Moulovibazar";
                        break;
                    case 38:
                        string_District = "Munshiganj";
                        break;
                    case 39:
                        string_District = "Mymensingh";
                        break;
                    case 40:
                        string_District = "Naogaon";
                        break;
                    case 41:
                        string_District = "Natore";
                        break;
                    case 42:
                        string_District = "Narail";
                        break;
                    case 43:
                        string_District = "Narayanganj";
                        break;
                    case 44:
                        string_District = "Netrokona";
                        break;
                    case 45:
                        string_District = "Nilphamari";
                        break;
                    case 46:
                        string_District = "Noakhali";
                        break;
                    case 47:
                        string_District = "Norsindhi";
                        break;
                    case 48:
                        string_District = "Pabna";
                        break;
                    case 49:
                        string_District = "Panchagarh";
                        break;
                    case 50:
                        string_District = "Patuakhali";
                        break;
                    case 51:
                        string_District = "Pirojpur";
                        break;
                    case 52:
                        string_District = "Rajbari";
                        break;
                    case 53:
                        string_District = "Rajshahi";
                        break;
                    case 54:
                        string_District = "Rangamati";
                        break;
                    case 55:
                        string_District = "Rangpur";
                        break;
                    case 56:
                        string_District = "Satkhira";
                        break;
                    case 57:
                        string_District = "Sirajganj";
                        break;
                    case 58:
                        string_District = "Shariatpur";
                        break;
                    case 59:
                        string_District = "Sherpur";
                        break;
                    case 60:
                        string_District = "Sunamganj";
                        break;
                    case 61:
                        string_District = "Sylhet";
                        break;
                    case 62:
                        string_District = "Tangail";
                        break;
                    case 63:
                        string_District = "Thakurgaon";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

    }



    public void saveData() {

        String bg = string_bloodGroup;
        String district = string_District;
        String searchKey = bg+district;
        String name = registrationFormName.getText().toString().trim();
        String phoneNo = registrationFormPhoneNumber.getText().toString().trim();

        String key = myRef.push().getKey();
        DataToRegister data = new DataToRegister(searchKey, name,phoneNo);
        myRef.child(key).setValue(data);
        Toast.makeText(getApplicationContext(),"Information successfully" +
                " registered",Toast.LENGTH_LONG).show();
    }



    private void verifyCode() {
        String code = VerificationCodeNumber.getText().toString();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, code);
        signUpWithCredential(credential);
    }

    private void setVerificationCode(String phone) {


        if (phone.isEmpty()) {
            registrationFormPhoneNumber.setError("Phone number required.");
            registrationFormPhoneNumber.requestFocus();
            return;
        }
        if (phone.length() < 10) {
            registrationFormPhoneNumber.setError("Enter a valid phone number.");
            registrationFormPhoneNumber.requestFocus();
            return;
        }
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
             codeSent = phoneAuthCredential.getSmsCode();

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {

        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            codeSent = s;
        }
    };

    private void signUpWithCredential(PhoneAuthCredential credential){
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            isVerified = true;
                        }else {
                            isVerified = false;
                            Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

}