package com.example.castriwolf.getup2.Clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.castriwolf.getup2.R;

import java.util.List;

public class ListViewInflater extends BaseAdapter {

    private Context context;
    private List<Alarma> listAlarmas;

    public ListViewInflater(Context context, List<Alarma>listAlarmas) {
        this.context = context;
        this.listAlarmas = listAlarmas;
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
            viewHolder.textViewName = convertView.findViewById(R.id.textViewName);
            viewHolder.textViewDescription = convertView.findViewById(R.id.textViewDescription);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Alarma alarma = listAlarmas.get(position);


        viewHolder.textViewDescription.setText(String.valueOf(alarma.getHoraLlegada() +" "+ alarma.getMinutoLlegada()));
       // viewHolder.imageViewProfilePic.setImageDrawable(getImageDrawable(person.getImageName()));

        return convertView;
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
    }
}
