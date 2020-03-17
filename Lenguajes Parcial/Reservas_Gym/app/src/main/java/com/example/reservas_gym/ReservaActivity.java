package com.example.reservas_gym;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ReservaActivity extends AppCompatActivity {

    CalendarView calendar;
    Button btnReserve;
    TextView txtDate;
    TextView time;
    String date;
    RadioGroup rgTimes;
    RadioButton selectedRd, rb1, rb2, rb3, rb4, rb5, rb6;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        connect();

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int day) {

                date = day + "/" + (month + 1) + "/" + year;
                txtDate.setText(date);
                rb1.setEnabled(true);
                rb2.setEnabled(true);
                rb3.setEnabled(true);
                rb4.setEnabled(true);
                rb5.setEnabled(true);
                rb6.setEnabled(true);
            }
        });
        rgTimes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                id = rgTimes.getCheckedRadioButtonId();
                selectedRd = findViewById(id);
                time.setText("Hora: " + selectedRd.getText());
            }
        });
        accionBoton();
    }

    private void accionBoton(){
        btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String datePicked = txtDate.getText().toString().trim();
                String timePicked = time.getText().toString().trim();
                if(datePicked.isEmpty() || timePicked.isEmpty()){
                    Toast.makeText(ReservaActivity.this, "Debes llenar obligatoriamente todos los campos", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ReservaActivity.this, "Reserva hecha exitosamente", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void connect()
    {
        calendar = findViewById(R.id.calendar);
        btnReserve = findViewById(R.id.btn_Reserve);
        txtDate = findViewById(R.id.txtDate);
        rgTimes = findViewById(R.id.rgTimes);
        time = findViewById(R.id.txtTime);
        rb1 = findViewById(R.id.rb_810);
        rb2 = findViewById(R.id.rb_1012);
        rb3 = findViewById(R.id.rb_1214);
        rb4 = findViewById(R.id.rb_1416);
        rb5 = findViewById(R.id.rb_1618);
        rb6 = findViewById(R.id.rb_1820);
        rb1.setEnabled(false);
        rb2.setEnabled(false);
        rb3.setEnabled(false);
        rb4.setEnabled(false);
        rb5.setEnabled(false);
        rb6.setEnabled(false);
    }
    public static  Intent launcheME(Context ctx)
    {
        return new Intent(ctx, ReservaActivity.class);
    }

}
