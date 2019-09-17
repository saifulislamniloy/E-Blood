package com.example.eblood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

public class SearchActivity extends AppCompatActivity {
    public static final String EXTRA_TEXT = "com.example.eblood.EXTRA_TEXT";

    Spinner bloodGroup;
    Spinner spinnerDistrict, registrationFormDivision;
    String[] stringBloodGroup;
    String string_bloodGroup;


    Button searchButton;

    ArrayAdapter<String> bg_adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        SetSpinnerFunction();

        searchButton = (Button) findViewById(R.id.search_blood);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, AvailableDonors.class);
                //intent.putExtra(EXTRA_TEXT, string_bloodGroup);
                //string_bloodGroup = "";
                //finish();
                startActivity(intent);
            }
        });

    }

    private void SetSpinnerFunction() {
        stringBloodGroup = getResources().getStringArray(R.array.bloodGroup);

        bloodGroup = (Spinner) findViewById(R.id.registrationFormBloodGroup);

        bg_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stringBloodGroup);
        bloodGroup.setAdapter(bg_adapter);
        bloodGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                string_bloodGroup = "";
                switch (position) {
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
                string_bloodGroup = "A+";
            }
        });

        locationSpinner();

    }

    private void locationSpinner() {
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

        ArrayAdapter<String> divisionAdapter =  new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Division);
        registrationFormDivision.setAdapter(divisionAdapter);


        registrationFormDivision.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String district = Division[position];
                if(district =="Barisal" ) {
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(SearchActivity.this, android.R.layout.simple_list_item_1, Barisal);
                    spinnerDistrict.setAdapter(adapter1);
                }
                else if(district =="Chittagong" ){
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(SearchActivity.this, android.R.layout.simple_list_item_1, Chittagong);
                    spinnerDistrict.setAdapter(adapter2);
                }
                else if(district =="Dhaka" ){
                    ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(SearchActivity.this, android.R.layout.simple_list_item_1, Dhaka);
                    spinnerDistrict.setAdapter(adapter3);
                }
                else if(district =="Khulna" ){
                    ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(SearchActivity.this, android.R.layout.simple_list_item_1, Khulna);
                    spinnerDistrict.setAdapter(adapter4);
                }
                else if(district =="Mymensingh" ){
                    ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(SearchActivity.this, android.R.layout.simple_list_item_1, Mymensingh);
                    spinnerDistrict.setAdapter(adapter4);
                }
                else if(district =="Rajshahi" ){
                    ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(SearchActivity.this, android.R.layout.simple_list_item_1, Rajshahi);
                    spinnerDistrict.setAdapter(adapter5);
                }
                else if(district =="Rangpur" ){
                    ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(SearchActivity.this, android.R.layout.simple_list_item_1, Rangpur);
                    spinnerDistrict.setAdapter(adapter6);
                }
                else if(district =="Sylhet" ){
                    ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(SearchActivity.this, android.R.layout.simple_list_item_1, Sylhet);
                    spinnerDistrict.setAdapter(adapter7);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }


}
