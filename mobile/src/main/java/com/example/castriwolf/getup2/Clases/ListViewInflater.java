package com.example.castriwolf.getup2.Clases;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.castriwolf.getup2.Base_Datos.Mihelper;
import com.example.castriwolf.getup2.Activitys.Menu_Alarma;
import com.example.castriwolf.getup2.R;

import java.util.List;

public class ListViewInflater extends BaseAdapter {

    private Context context;
    public static List<Alarma> listAlarmas;
    ImageView borrar;
    Mihelper db;


    public ListViewInflater(Context context, List<Alarma>listAlarmas) {
        this.context = context;
        this.listAlarmas = listAlarmas;
        db=new Mihelper(context);


    }

    @Override
    public int getCount() {
        return listAlarmas.size();
    }

    @Override
    public Object getItem(int position) {
        return listAlarmas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;





        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_view_custom, null);

            viewHolder = new ViewHolder();

            //viewHolder.imageViewProfilePic = convertView.findViewById(R.id.imageViewProfilePic);
            //viewHolder.textViewName = convertView.findViewById(R.id.textViewName);
            viewHolder.textViewDescription = convertView.findViewById(R.id.textViewDescription);
            viewHolder.lunes=convertView.findViewById(R.id.lunes);
            viewHolder.martes=convertView.findViewById(R.id.martes);
            viewHolder.miercoles=convertView.findViewById(R.id.miercoles);
            viewHolder.jueves=convertView.findViewById(R.id.jueves);
            viewHolder.viernes=convertView.findViewById(R.id.viernes);
            viewHolder.sabado=convertView.findViewById(R.id.sabado);
            viewHolder.domingo=convertView.findViewById(R.id.domingo);

            if(Container.alarmas.get(position).getLunes()==1){
            viewHolder.lunes.setTextColor(Color.GREEN);
            }else{   viewHolder.lunes.setTextColor(Color.RED);}

            if(Container.alarmas.get(position).getMartes()==1){
                viewHolder.martes.setTextColor(Color.GREEN);
            }else{   viewHolder.martes.setTextColor(Color.RED);}

            if(Container.alarmas.get(position).getMiercoles()==1){
                viewHolder.miercoles.setTextColor(Color.GREEN);
            }else{   viewHolder.miercoles.setTextColor(Color.RED);}

            if(Container.alarmas.get(position).getJueves()==1){
                viewHolder.jueves.setTextColor(Color.GREEN);
            }else{   viewHolder.jueves.setTextColor(Color.RED);}

            if(Container.alarmas.get(position).getViernes()==1){
                viewHolder.viernes.setTextColor(Color.GREEN);
            }else{   viewHolder.viernes.setTextColor(Color.RED);}

            if(Container.alarmas.get(position).getSabado()==1){
                viewHolder.sabado.setTextColor(Color.GREEN);
            }else{   viewHolder.sabado.setTextColor(Color.RED);}

            if(Container.alarmas.get(position).getDomingo()==1){
                viewHolder.domingo.setTextColor(Color.GREEN);
            }else{   viewHolder.domingo.setTextColor(Color.RED);}



            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Alarma alarma = listAlarmas.get(position);

        if(alarma.getHoraSalida()<10&&alarma.getMinutoSalida()<10)
        {
            viewHolder.textViewDescription.setText("0"+String.valueOf(alarma.getHoraSalida() +":0"+ String.valueOf(alarma.getMinutoSalida())));

        }
        if(alarma.getHoraSalida()<10)
        {
            viewHolder.textViewDescription.setText("0"+String.valueOf(alarma.getHoraSalida() +":"+ String.valueOf(alarma.getMinutoSalida())));



        }

        if(alarma.getMinutoSalida()<10){
            viewHolder.textViewDescription.setText(String.valueOf(alarma.getHoraSalida() +":0"+ String.valueOf(alarma.getMinutoSalida())));
        }
        if(alarma.getHoraSalida()>=10&&alarma.getMinutoSalida()>=10)
        {
            viewHolder.textViewDescription.setText(String.valueOf(alarma.getHoraSalida() +":"+ String.valueOf(alarma.getMinutoSalida())));


        }

       // viewHolder.imageViewProfilePic.setImageDrawable(getImageDrawable(person.getImageName()));

        borrar=(ImageView)convertView.findViewById(R.id.buttonEliminar);
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(context);
                } else {
                    builder = new AlertDialog.Builder(context);
                }
                builder.setView(R.layout.custom_layout);
                builder.setTitle("Borrar alarma");
                builder.setMessage("Deseas borrar la alarma número: "+String.valueOf(position+1)+"?");
                builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                        db.eliminarAlarma(listAlarmas.get(position).getHoraSalida(),listAlarmas.get(position).getMinutoSalida());
                        borrarServiciosdeAlarma(listAlarmas.get(position));
                        listAlarmas.remove(position);
                        Menu_Alarma.listView.invalidateViews();
                        Toast.makeText(context,"Alarma numero "+String.valueOf(position+1)+" borrada",Toast.LENGTH_SHORT).show();


                    }
                });
                builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setIcon(R.drawable.alarma_copy);
                builder.show();






            }




        });


        return convertView;
    }

    private void borrarServiciosdeAlarma(Alarma alarma)
    {

        Intent intent = new Intent(context, MyAlarmReceiver.class);
        PendingIntent sender=PendingIntent.getBroadcast(context,alarma.getId_alarma()+1,intent,0);
        PendingIntent sender2=PendingIntent.getBroadcast(context,alarma.getId_alarma()+2,intent,0);
        PendingIntent sender3=PendingIntent.getBroadcast(context,alarma.getId_alarma()+3,intent,0);
        PendingIntent sender4=PendingIntent.getBroadcast(context,alarma.getId_alarma()+4,intent,0);
        PendingIntent sender5=PendingIntent.getBroadcast(context,alarma.getId_alarma()+5,intent,0);
        PendingIntent sender6=PendingIntent.getBroadcast(context,alarma.getId_alarma()+6,intent,0);
        PendingIntent sender7=PendingIntent.getBroadcast(context,alarma.getId_alarma()+7,intent,0);


        AlarmManager alarmManager= (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
        alarmManager.cancel(sender2);
        alarmManager.cancel(sender3);
        alarmManager.cancel(sender4);
        alarmManager.cancel(sender5);
        alarmManager.cancel(sender6);
        alarmManager.cancel(sender7);


    }
    /*private Drawable getImageDrawable(String imageName) {
        int id = context.getResources().getIdentifier(imageName, "drawable",
                context.getPackageName());
        return context.getResources().getDrawable(id);
    }
*/
    class ViewHolder {
        //ImageView imageViewProfilePic;
        TextView textViewName;
        TextView textViewDescription;
        TextView lunes;
        TextView martes;
        TextView miercoles;
        TextView jueves;
        TextView viernes;
        TextView sabado;
        TextView domingo;
    }
}
