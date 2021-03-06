package com.example.reservas_gym;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Admin_Activity extends AppCompatActivity {

    ImageView top;
    ImageView logo;
    ImageView bottom;
    ImageButton viewReg;
    TextView txtAdmin;
    TextView ver;
    Archivos arch = new Archivos(this, "reserves.txt");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        connect();
        animate();
        viewReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showReservas();
            }
        });

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
        res.setText(getReservas(arch.listaReservas()));
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
    Intent para lanzar el admin activity
     */
    public static Intent launcheME(Context ctx)
    {
        return new Intent(ctx, Admin_Activity.class);
    }
}
