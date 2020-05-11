package com.example.reservas_gym;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Admin_Activity extends AppCompatActivity {

    ImageView top;
    ImageView logo;
    ImageView bottom;
    ImageButton viewReg;
    TextView txtAdmin;
    TextView ver;
    DbHelper helper;
    ArrayList<Reserva> reservas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        connect();
        /*
        AQUÍ SE IMPLEMENTA LA LISTA CON LAS RESERVAS ACTUALES
         */
        initializeList();
        helper = new DbHelper(getApplicationContext(), "BD", null, 1);
        animate();
        viewReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showReservas();
            }
        });

    }
    /*
        CÓDIGO NUEVO
     */

    private void initializeList() {
        reservas = showReservesDB();
    }

    private void connect()
    {
        top = findViewById(R.id.imgTop);
        logo = findViewById(R.id.imgLogo4);
        bottom = findViewById(R.id.imgbtm);
        viewReg = findViewById(R.id.imgBtn_verReserva);
        txtAdmin = findViewById(R.id.txtadmin);
        ver = findViewById(R.id.txtVer);
    }

    /*
    Hacer animación en el admin activity
     */
    private void animate()
    {
        top.animate().translationY(-1500).setDuration(600).setStartDelay(95);
        bottom.animate().translationY(1600).setDuration(600).setStartDelay(95);
        logo.animate().translationY(-212).setStartDelay(500);
        txtAdmin.animate().alpha(1).setDuration(300).setStartDelay(300);
        viewReg.animate().alpha(1).setDuration(400).setStartDelay(400);
        ver.animate().alpha(1).setDuration(400).setStartDelay(400);
    }

    /*
    Método de la clase Reserva donde obtiene toda la información de la reserva
     */
    /*
    private String getReservas(ArrayList<Reserva> list)
    {
        String info= "";
        for(Reserva e:list)
        {
            int cont = 0;
            info += e.getReservationInfo();
            cont++;
            if(cont == 1){
                info += "\n\n";
            }
        }
        return info;
    }

     */

    /*
    Se crea un Alert dialog para mostrar las reservas con un textview (configurado en reserva_view_layout.xml)
     */
    private void showReservas()
    {
        LayoutInflater inflater = this.getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog alert;
        View view = inflater.inflate(R.layout.reserva_view_layout,  null);
        TextView res = view.findViewById(R.id.txtView_Res);
        builder.setView(view);
        builder.setPositiveButton("Listo!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert = builder.create();
        alert.show();
    }

    /*
    CÓDIGO NUEVO
     */

    private ArrayList<Reserva> showReservesDB(){
        ArrayList<Reserva> reservas = new ArrayList<>();
        SQLiteDatabase db = helper.getWritableDatabase();
        String SQL = "Select * from Reservas";
        Cursor c = db.rawQuery(SQL, null);
        try{
            if(c.moveToFirst()){
                do{
                    String idReserva = c.getString(0);
                    String idEstudiante = c.getString(1);
                    String fecha = c.getString(2);
                    String hora = c.getString(3);
                    reservas.add(new Reserva(idReserva, idEstudiante, fecha, hora));
                }while(c.moveToNext());
            }
            c.close();
            db.close();
        }catch(Exception e){
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return reservas;
    }

    /*
    Intent para lanzar el admin activity
     */
    public static Intent launcheME(Context ctx)
    {
        return new Intent(ctx, Admin_Activity.class);
    }
}
