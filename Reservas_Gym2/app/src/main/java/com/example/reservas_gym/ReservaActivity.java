package com.example.reservas_gym;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.core.content.ContextCompat;

import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.style.UpdateAppearance;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.spark.submitbutton.SubmitButton;
import com.varunest.sparkbutton.SparkButton;
import com.varunest.sparkbutton.SparkButtonBuilder;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;

public class ReservaActivity extends AppCompatActivity {

    private CalendarView calendar;
    private TimePicker clock;
    private TextView phrase1, phrase2, phrase3, phrase4, phrase5, tvNombreEstudiante, tvCarreraEstudiante, reservaInfo, reservaName;
    private int daySelected, monthSelected, yearSelected, hourSelected, minuteSelected;
    private String[] userData;
    private ImageView taskComplete1, taskComplete2, emoji;
    private SparkButton btnReservar, btnVerReservas, btnSetReservaDate, btnSetReservaTime, btnCancel, btnshowUserOptions;
    SubmitButton  btnDateSelected, btnTimeSelected;
    private Button okDateWarningBtn, okTimeWarningBtn, okTimeWarningBtn2, okNoReservaWarning, yesDeleteReservaBtn, noDeleteReservaBtn, reservaEliminadaOkBtn, yaTienesReservaOkBtn, btnHacerReserva;
    private Calendar calendario = Calendar.getInstance();
    private boolean reservaButtonTouched, dateSelected;
    private boolean verReservasBtnTouched, timeSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        Bundle recup = getIntent().getExtras();
        userData = recup.getStringArray("userData");
        connect();
        tvNombreEstudiante.setText(userData[1] + " " + userData[2]);
        startingProcesses();
        ButtonListeners();
    }
    private void connect()
    {
       phrase1 = findViewById(R.id.P1);
       phrase2 = findViewById(R.id.P2);
       phrase3 = findViewById(R.id.P3);
       phrase4 = findViewById(R.id.P4);
       phrase5 = findViewById(R.id.P5);
       reservaInfo = findViewById(R.id.txtReservaInfo);
       reservaName = findViewById(R.id.txtNameReserva);
       emoji = findViewById(R.id.emoji);
       btnReservar = findViewById(R.id.btnReservar);
       btnVerReservas = findViewById(R.id.btn_verReservas);
       btnHacerReserva = findViewById(R.id.btnHacerReserva);
       btnSetReservaTime = findViewById(R.id.btnClock);
       btnSetReservaDate = findViewById(R.id.btnCalendar);
       btnCancel = findViewById(R.id.btnCancel);
       btnshowUserOptions = findViewById(R.id.btnUserMenu);
       taskComplete1 = findViewById(R.id.completed1);
       taskComplete2 = findViewById(R.id.completed2);
       tvNombreEstudiante = findViewById(R.id.txtNombreEstudiante);
       tvCarreraEstudiante = findViewById(R.id.txtCarreraEstudiante);
    }
    private void startingProcesses()
    {
        btnCancel.setEnabled(false);
        btnCancel.setClickable(false);
        btnHacerReserva.setEnabled(false);
        btnHacerReserva.setClickable(false);
        btnSetReservaTime.setEnabled(false);
        btnSetReservaTime.setClickable(false);
        btnSetReservaDate.setEnabled(false);
        btnSetReservaDate.setClickable(false);
        tvNombreEstudiante.setText(userData[1] + " " + userData[2]);
        tvCarreraEstudiante.setText(userData[3]);

        //Animations.
        phrase1.animate().setStartDelay(700).setDuration(1000).alpha(1);
        phrase2.animate().setStartDelay(2000).setDuration(800).alpha(1);
        phrase3.animate().setStartDelay(3700).setDuration(2000).alpha(1);
        phrase4.animate().setStartDelay(5500).setDuration(800).alpha(1);
        phrase5.animate().setStartDelay(6500).setDuration(800).alpha(1);
    }

    private void animations(int which)
    {
        //reservar buttons animations.
        if(which == 1)
        {
                reservaName.animate().setDuration(250).setStartDelay(0).alpha(0);
                reservaInfo.animate().setDuration(250).setStartDelay(0).alpha(0);
                emoji.animate().setDuration(250).setStartDelay(0).alpha(0);
                phrase1.animate().setStartDelay(0).setDuration(300).alpha(0);
                phrase2.animate().setStartDelay(0).setDuration(300).alpha(0);
                phrase3.animate().setStartDelay(0).setDuration(300).alpha(0);
                phrase4.animate().setStartDelay(0).setDuration(300).alpha(0);
                phrase5.animate().setStartDelay(0).setDuration(300).alpha(0);
                taskComplete1.animate().setStartDelay(100).setDuration(300).alpha(1);
                taskComplete2.animate().setStartDelay(100).setDuration(300).alpha(1);
                btnSetReservaTime.animate().setStartDelay(100).setDuration(300).alpha(1);
                btnSetReservaDate.animate().setStartDelay(100).setDuration(300).alpha(1);
                btnHacerReserva.animate().setStartDelay(100).setDuration(300).alpha(1);
                btnCancel.animate().setStartDelay(0).setDuration(300).alpha(1);
        }
        //ver reservas button animations
         else if(which == 2)
        {
            if(!verReservasBtnTouched)
            {
                phrase1.animate().setStartDelay(0).setDuration(300).alpha(0);
                phrase2.animate().setStartDelay(0).setDuration(300).alpha(0);
                phrase3.animate().setStartDelay(0).setDuration(300).alpha(0);
                phrase4.animate().setStartDelay(0).setDuration(300).alpha(0);
                phrase5.animate().setStartDelay(0).setDuration(300).alpha(0);
                verReservasBtnTouched = true;
            }
        }
        //cancel button animation
         else if(which == 3)
        {
            phrase1.animate().setStartDelay(0).setDuration(300).alpha(1);
            phrase2.animate().setStartDelay(0).setDuration(300).alpha(1);
            phrase3.animate().setStartDelay(0).setDuration(300).alpha(1);
            phrase4.animate().setStartDelay(0).setDuration(300).alpha(1);
            phrase5.animate().setStartDelay(0).setDuration(300).alpha(1);
            taskComplete1.animate().setStartDelay(0).setDuration(300).alpha(0);
            taskComplete2.animate().setStartDelay(0).setDuration(300).alpha(0);
            btnSetReservaTime.animate().setStartDelay(0).setDuration(300).alpha(0);
            btnSetReservaDate.animate().setStartDelay(0).setDuration(300).alpha(0);
            btnHacerReserva.animate().setStartDelay(0).setDuration(300).alpha(0);
            btnCancel.animate().setStartDelay(0).setDuration(300).alpha(0);
            reservaName.animate().setDuration(250).setStartDelay(0).alpha(0);
            reservaInfo.animate().setDuration(250).setStartDelay(0).alpha(0);
            emoji.animate().setDuration(250).setStartDelay(0).alpha(0);
        }

         else if(which == 4)
        {
            reservaName.animate().setDuration(630).setStartDelay(800).alpha(1);
            reservaInfo.animate().setDuration(630).setStartDelay(800).alpha(1);
            emoji.animate().setDuration(400).setStartDelay(800).alpha(1);

            taskComplete1.animate().setStartDelay(0).setDuration(300).alpha(0);
            taskComplete2.animate().setStartDelay(0).setDuration(300).alpha(0);
            btnSetReservaTime.animate().setStartDelay(0).setDuration(300).alpha(0);
            btnSetReservaDate.animate().setStartDelay(0).setDuration(300).alpha(0);
            btnHacerReserva.animate().setStartDelay(0).setDuration(300).alpha(0);
            btnCancel.animate().setStartDelay(0).setDuration(300).alpha(1);

            phrase1.animate().setStartDelay(0).setDuration(300).alpha(0);
            phrase2.animate().setStartDelay(0).setDuration(300).alpha(0);
            phrase3.animate().setStartDelay(0).setDuration(300).alpha(0);
            phrase4.animate().setStartDelay(0).setDuration(300).alpha(0);
            phrase5.animate().setStartDelay(0).setDuration(300).alpha(0);
        }
    }

    public static  Intent launcheME(Context ctx)

    {
        return new Intent(ctx, ReservaActivity.class);
    }

    private void ButtonListeners()
    {
        btnReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animations(1);
                btnHacerReserva.setBackgroundResource(R.drawable.empty_reservar_button);
                btnCancel.setEnabled(true);
                btnCancel.setClickable(true);
                btnSetReservaTime.setEnabled(true);
                btnSetReservaTime.setClickable(true);
                btnSetReservaDate.setEnabled(true);
                btnSetReservaDate.setClickable(true);
                btnReservar.setEnabled(false);
                btnReservar.setClickable(false);
                btnVerReservas.setEnabled(true);
                btnVerReservas.setClickable(true);
                reservaButtonTouched = true;
                taskComplete1.setImageResource(R.drawable.task_not_completed);
                taskComplete2.setImageResource(R.drawable.task_not_completed);
            }
        });
        btnHacerReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!verificarSiYaReservo())
                {
                    btnHacerReserva.setBackgroundResource(R.drawable.reserva_exitosa_button);
                    String dia = daySelected + "";
                    String mes = monthSelected + "";
                    String año = yearSelected + "";
                    String h = hourSelected + "";
                    String m = minuteSelected + "0";
                    String fecha = dia + "/" + mes + "/" + año;
                    String hora = h + ":" + m;
                    GuardarReserva(userData[0], fecha, hora);
                    btnHacerReserva.setEnabled(false);
                    btnHacerReserva.setClickable(false);
                    btnReservar.setEnabled(true);
                    btnReservar.setClickable(true);
                    btnVerReservas.setEnabled(true);
                    btnVerReservas.setClickable(true);
                    animations(3);
                }
                else
                {
                    makeDialogs(9);
                }

            }
        });
        btnVerReservas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verReservas();
                btnCancel.setClickable(true);
                btnCancel.setEnabled(true);
                btnVerReservas.setEnabled(false);
                btnVerReservas.setClickable(false);
                btnReservar.setEnabled(true);
                btnReservar.setClickable(true);
                verReservasBtnTouched = true;
                animations(4);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verReservasBtnTouched || reservaButtonTouched)
                {
                    animations(3);
                    taskComplete1.setImageResource(R.drawable.task_not_completed);
                    taskComplete2.setImageResource(R.drawable.task_not_completed);
                    btnCancel.setEnabled(false);
                    btnCancel.setClickable(false);
                    btnSetReservaTime.setEnabled(false);
                    btnSetReservaTime.setClickable(false);
                    btnSetReservaDate.setEnabled(false);
                    btnSetReservaDate.setClickable(false);
                    btnHacerReserva.setEnabled(false);
                    btnHacerReserva.setClickable(false);
                    btnVerReservas.setEnabled(true);
                    btnVerReservas.setClickable(true);
                    btnReservar.setEnabled(true);
                    btnReservar.setClickable(true);
                    verReservasBtnTouched = false;
                    reservaButtonTouched = false;
                    hourSelected = 0;
                    minuteSelected = -1;
                    monthSelected = 0;
                    daySelected = 0;
                    yearSelected = 0;
                    timeSelected = false;
                    dateSelected = false;
                }
            }
        });
        btnSetReservaDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daySelected = 0;
                monthSelected = 0;
                yearSelected = 0;
                btnHacerReserva.setEnabled(false);
                btnHacerReserva.setClickable(false);
                taskComplete1.setImageResource(R.drawable.task_not_completed);
                makeDialogs(1);
            }
        });
        btnSetReservaTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hourSelected = 0;
                minuteSelected = -1;
                btnHacerReserva.setEnabled(false);
                btnHacerReserva.setClickable(false);
                taskComplete2.setImageResource(R.drawable.task_not_completed);
                makeDialogs(2);
            }
        });

        btnshowUserOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //PopupMenu popupMenu = new PopupMenu(ReservaActivity.this, btnshowUserOptions);
                ContextThemeWrapper ctw = new ContextThemeWrapper(ReservaActivity.this, R.style.CustomPopupTheme);
                PopupMenu popupMenu = new PopupMenu(ctw, v, Gravity.START);
                popupMenu.inflate(R.menu.show_user_options);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                       switch (item.getItemId())
                       {
                           case R.id.option1:
                               makeDialogs(7);
                               return true;
                           case R.id.option2:
                                goBack();
                                finish();
                               return true;
                           default:
                               return false;
                       }
                    }
                });
                popupMenu.show();
            }
        });
    }

    private void GuardarReserva(String cedula, String fecha, String hora)
    {
        DbHelper helper = new DbHelper(this, "BD", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();

        try{
            String insert = "INSERT INTO Reservas(Id_estudiante, Fecha, Hora) VALUES('"+cedula+"', '"+fecha+"', '"+hora+"')";

            db.execSQL(insert);
            db.close();

            Toast.makeText(this, "Reserva exitosa", Toast.LENGTH_LONG).show();
        }catch (Exception ex){
            Toast.makeText(this, "Error: " + ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    private void makeDialogs(int whichOne)
    {

        if(whichOne == 1)
        {
            final Dialog dialog = new Dialog(ReservaActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.my_calendar_layout);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            btnDateSelected = dialog.findViewById(R.id.btnDateSetComplete);
            calendar = dialog.findViewById(R.id.myDatePicker);
            calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                    daySelected = dayOfMonth;
                    monthSelected = (month + 1);
                    yearSelected = year;
                }
            });
            btnDateSelected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    verifyDate(daySelected, monthSelected, yearSelected, dialog);
                }
            });
            dialog.show();
        }
        else if(whichOne == 2)
        {
            final Dialog dialog = new Dialog(ReservaActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.my_clock_layout);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            btnTimeSelected = dialog.findViewById(R.id.btnTimeSetComplete);
            clock = dialog.findViewById(R.id.myTimePicker);
            clock.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                @Override
                public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                    minuteSelected = minute;
                    hourSelected = hourOfDay;
                }
            });
            btnTimeSelected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    verifyTime(hourSelected, minuteSelected, dialog);
                }
            });
            dialog.show();
        }

        else if(whichOne == 3)
        {
            final Dialog dialog = new Dialog(ReservaActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.date_warning);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            okDateWarningBtn = dialog.findViewById(R.id.OkDateWarningBtn);
            okDateWarningBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    makeDialogs(1);
                }
            });
            dialog.show();
        }

        //error dialog for hour passed the gym attention time
        else if(whichOne == 4)
        {
            final Dialog dialog = new Dialog(ReservaActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.time_warning);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            okTimeWarningBtn = dialog.findViewById(R.id.OkTimeWarningBtn);
            okTimeWarningBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    makeDialogs(2);
                }
            });
            dialog.show();
        }

        //error message for hour not being pair
        else if(whichOne == 5)
        {
            final Dialog dialog = new Dialog(ReservaActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.time2_warning);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            okTimeWarningBtn2 = dialog.findViewById(R.id.OkTimeWarningBtn2);
            okTimeWarningBtn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    makeDialogs(2);
                }
            });
            dialog.show();
        }

        //no reserva warning dialog
        else if(whichOne == 6)
        {
            final Dialog dialog = new Dialog(ReservaActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.no_reserva_warning);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            okNoReservaWarning = dialog.findViewById(R.id.noReservaOkBtn);
            okNoReservaWarning.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }

        //dialog preguntando si en realidad quiero eliminar la reserva
        else if(whichOne == 7)
        {
            final Dialog dialog = new Dialog(ReservaActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.eliminar_reserva_warning);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
             yesDeleteReservaBtn = dialog.findViewById(R.id.deleteReservaWrningYesBtn);
             noDeleteReservaBtn = dialog.findViewById(R.id.deleteReservaWrningCancelBtn);
            yesDeleteReservaBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    eliminarReserva();
                    dialog.dismiss();
                }
            });
            noDeleteReservaBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }

        //reserva eliminada exitosamente
        else if(whichOne == 8)
        {
            final Dialog dialog = new Dialog(ReservaActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.reserva_eliminada_exitosamente);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            reservaEliminadaOkBtn = dialog.findViewById(R.id.reservaEliminadaOkBtn);
            reservaEliminadaOkBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }

        //Ya tienes reserva dialog
        else if(whichOne == 9)
        {
            final Dialog dialog = new Dialog(ReservaActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.ya_tiene_reserva);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            yaTienesReservaOkBtn = dialog.findViewById(R.id.yaTieneReservaOkBtn);
            yaTienesReservaOkBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    }
    /*
   Verifica que la fecha que eligió el usuario sea la correcta (Un día después del día actual)
    */
    private void verifyDate(int daySelected, int monthSelected, int yearSelected, Dialog currentDialog)
    {
        CountDownTimer myTimer;
        final Dialog mycurrentDialog = currentDialog;
        int actualDay = calendario.get(Calendar.DAY_OF_MONTH);
        int actualMonth = calendario.get(Calendar.MONTH) + 1;
        int actualYear = calendario.get(Calendar.YEAR);
        String date = actualDay + " " + actualMonth + " " + actualYear;
        if(yearSelected == actualYear && monthSelected == actualMonth && daySelected == (actualDay+1))
        {
            myTimer = new CountDownTimer(1200, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                }

                @Override
                public void onFinish() {
                    mycurrentDialog.dismiss();
                    dateSelected = true;
                    btnHacerReserva.setEnabled(true);
                    btnHacerReserva.setClickable(true);
                    taskComplete1.setImageResource(R.drawable.task_completed);
                }
            }.start();

        }
        else
        {
            currentDialog.dismiss();
            makeDialogs(3);
        }

    }

    private void verifyTime(int hourSelected, int minuteSelected, Dialog mycurrentDialog)
    {
        final Dialog myCurrentDialog = mycurrentDialog;
        if(hourSelected % 2 == 0 && hourSelected <= 18 && hourSelected >= 8 && minuteSelected == 0)
        {
            CountDownTimer myTimer;
            myTimer = new CountDownTimer(1200, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                }

                @Override
                public void onFinish() {
                    myCurrentDialog.dismiss();
                    timeSelected = true;
                    btnHacerReserva.setEnabled(true);
                    btnHacerReserva.setClickable(true);
                    taskComplete2.setImageResource(R.drawable.task_completed);
                }
            }.start();
        }
        else if(hourSelected > 18)
        {
            mycurrentDialog.dismiss();
            makeDialogs(4);
        }
        else if(hourSelected < 8)
        {
            mycurrentDialog.dismiss();
            makeDialogs(4);
        }
        else if(minuteSelected != 0)
        {
            mycurrentDialog.dismiss();
            makeDialogs(5);
        }
        else if(hourSelected % 2 != 0)
        {
            mycurrentDialog.dismiss();
            makeDialogs(5);
        }
    }

    private void verReservas()
    {
        int horaConvertida = 0;
        String tardeOmorning = "";
        DbHelper helper = new DbHelper(this, "BD", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();
        String getTimeSql = "Select Hora from Reservas where Id_Estudiante = '" + userData[0] + "'";
        String hora = "";
        Cursor cursor2 = db.rawQuery(getTimeSql, null);
        if(cursor2.moveToFirst())
        {
            hora = cursor2.getString(0);
            horaConvertida = convertTime(hora);
            if(horaConvertida == 12)
            {
                tardeOmorning = ":00 del medio día";
            }
            else if(horaConvertida > 11)
            {
                tardeOmorning = ":00 de la tarde";
            }
            else
            {
                tardeOmorning = ":00 de la mañana";
            }
        }
        cursor2.close();


        String[] userName = userData[1].split(" ");
        String line = "";
        String sqlSequence = "Select Fecha from Reservas where Id_Estudiante = '" + userData[0] + "'";
        Cursor cursor = db.rawQuery(sqlSequence, null);
        if(cursor.moveToFirst())
        {
           line ="\n" + "Abajo encontrarás los datos de tu reserva actual \n\n Fecha: " + cursor.getString(0)  + "\n" + "Hora: " + horaConvertida + tardeOmorning;
        }
        else
        {
            line = "\n" + " En este momento no tienes ninguna reserva, ¡Anímate a reservar ahora! \n\n" + "Que tengas un lindo resto del día, y recuerda: #QuédateEnCasa";
        }
        reservaName.setText("Hola " + userName[0]);
        reservaInfo.setText(line);
        db.close();
    }

    private void eliminarReserva()
    {
        DbHelper myHelper = new DbHelper(ReservaActivity.this, "BD", null, 1);
        SQLiteDatabase myDB = myHelper.getWritableDatabase();
        try
        {
            String sqlSequence = "select Id_estudiante from Reservas where Id_estudiante= '"+ userData[0] +"'";
            Cursor cursor = myDB.rawQuery(sqlSequence, null);
            if(cursor.moveToFirst())
            {
                makeDialogs(8);
                myDB.execSQL("delete from Reservas where Id_estudiante= '"+ userData[0] +"'");
            }
            else
            {
                makeDialogs(6);
            }
            cursor.close();
            myDB.close();
        }
        catch (Exception ex)
        {
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
        }
    }

    private int convertTime(String timeToConvert)
    {
        String[] getHoraTexto = timeToConvert.split(":");
        int convertirHora = Integer.parseInt(getHoraTexto[0]);
        return convertirHora;
    }

    public void goBack()
    {
        Intent intent = Login.launcheME(ReservaActivity.this);
        startActivity(intent);
    }

    private boolean verificarSiYaReservo()
    {
        boolean found = false;
        DbHelper myHelper = new DbHelper(ReservaActivity.this, "BD", null, 1);
        SQLiteDatabase myDB = myHelper.getWritableDatabase();
        try
        {
            String sqlSequence = "select Id_estudiante from Reservas where Id_estudiante= '" + userData[0] + "'";
            Cursor cursor = myDB.rawQuery(sqlSequence, null);
            if (cursor.moveToFirst()) {
                found = true;
            } else {
            }
            cursor.close();
            myDB.close();
        }
        catch (Exception ex)
        {
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
        }
        return found;
    }


}
