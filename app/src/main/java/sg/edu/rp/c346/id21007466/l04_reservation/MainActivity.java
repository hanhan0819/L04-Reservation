package sg.edu.rp.c346.id21007466.l04_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText etName, etMobileNumber, etGroupSize;
    DatePicker datePicker;
    TimePicker timePicker;
    RadioGroup radioGroup;
    Button btnConfirm, btnClear;
    TextView tvReservationInfo;

    RadioButton rbSmoking;
    RadioButton rbNonSmoking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etMobileNumber = findViewById(R.id.etMobileNumber);
        etGroupSize = findViewById(R.id.etGroupSize);
        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);
        radioGroup = findViewById(R.id.radioGroup);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnClear = findViewById(R.id.btnClear);
        tvReservationInfo = findViewById(R.id.tvReservationInfo);
        rbNonSmoking = findViewById(R.id.rbNonSmoking);
        btnConfirm = findViewById(R.id.btnConfirm);
        rbSmoking=findViewById(R.id.rbSmoking);


        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String mobile = etMobileNumber.getText().toString();
                int groupSize = Integer.parseInt(etGroupSize.getText().toString());
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();

                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1;
                int year = datePicker.getYear();

                String tableArea;
                if (rbSmoking.isChecked()) {
                    tableArea = "Smoking Area";
                } else {
                    tableArea = "Non-Smoking Area";
                }

                String reservationInfo = "Name: " + name + "\n" + "Mobile Number: " + mobile + "\n"
                        + "Group Size: " + groupSize + "\n" + "Date: " + day + "/" + month + "/" + year + "\n"
                        + "Time: " + formatTime(hour, minute) + "\n" + "Area: " + tableArea;

                tvReservationInfo.setText(reservationInfo);
                Toast.makeText(MainActivity.this, "Reservation confirmed!", Toast.LENGTH_SHORT).show();
            }
        });


        btnClear.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    clearInputs();
                }
            });
        }

        private void setDefaultDateTime() {
            Calendar calendar = Calendar.getInstance();

            int year = 2020;
            int month = Calendar.JUNE;
            int day = 1;

            int hour = 19;
            int minute = 30;

            datePicker.init(year, month, day, null);
            timePicker.setCurrentHour(hour);
            timePicker.setCurrentMinute(minute);
        }

        private String formatTime(int hour, int minute) {
            return String.format(Locale.getDefault(), "%02d:%02d", hour, minute);
        }

        private String getSelectedArea() {
            int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
            if (selectedRadioButtonId == rbSmoking.getId()) {
                return "Smoking Area";
            } else if (selectedRadioButtonId == rbNonSmoking.getId()) {
                return "Non-Smoking Area";
            }
            return "";
        }

        private void clearInputs() {
            tvReservationInfo.setText("");
            setDefaultDateTime();
            radioGroup.clearCheck();
        }
    }