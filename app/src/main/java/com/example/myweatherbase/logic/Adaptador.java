package com.example.myweatherbase.logic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweatherbase.R;
import com.example.myweatherbase.activities.model.Root;
import com.example.myweatherbase.base.ImageDownloader;
import com.example.myweatherbase.base.Parameters;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {
    private Root root;
    private LayoutInflater inflater;

    public Adaptador(Context context, Root root) {
        this.root = root;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public Adaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.simple_element, parent, false);
        return new ViewHolder(view);
    }
//sys - dt_txt
    @Override
    public void onBindViewHolder(@NonNull Adaptador.ViewHolder holder, int position) {
        ImageDownloader.downloadImage(Parameters.ICON_URL_PRE + root.list.get(position).weather.get(0).icon + Parameters.ICON_URL_POST, holder.imageView);
        holder.textViewTemp.setText("Temp " + root.list.get(position).main.temp + "º ");
        holder.textViewMaxTemp.setText("Max " + root.list.get(position).main.temp_max + " ");
        holder.textViewMinTemp.setText("Min " + root.list.get(position).main.temp_min + " ");
        holder.textViewCielo.setText(root.list.get(position).weather.get(0).description);


        //  Date date = new Date((long) root.list.get(position).dt * 1000);
        SimpleDateFormat dateDayOfWeek = new SimpleDateFormat("E");
        SimpleDateFormat dateWeek = new SimpleDateFormat("d MMM yyyy");

        SimpleDateFormat dateDay = new SimpleDateFormat("EEE, d MMM yyyy");
        SimpleDateFormat dateHour = new SimpleDateFormat("HH:mm");

        holder.textViewFecha.setText(dateWeek.format((long)root.list.get(position).dt*1000));
        holder.textViewHora.setText(dateHour.format((long)root.list.get(position).dt*1000) );
        holder.txtViewDia.setText(dateDayOfWeek.format((long)root.list.get(position).dt*1000));




    }

    @Override
    public int getItemCount() {
        return root.list.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtViewDia;
        private TextView textViewCielo;
        private TextView textViewTemp;
        private TextView textViewFecha;
        private TextView textViewMaxTemp;
        private TextView textViewHora;
        private TextView textViewMinTemp;
        private ImageView imageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtViewDia = itemView.findViewById(R.id.txtViewDia);
            textViewCielo = itemView.findViewById(R.id.textViewCielo);
            textViewTemp = itemView.findViewById(R.id.textViewTemp);
            textViewFecha = itemView.findViewById(R.id.textViewFecha);
            textViewMaxTemp = itemView.findViewById(R.id.textViewMaxTemp);
            textViewHora = itemView.findViewById(R.id.textViewHora);
            textViewMinTemp = itemView.findViewById(R.id.textViewMinTemp);
            imageView = itemView.findViewById(R.id.imageViewImg);
        }
    }
}
