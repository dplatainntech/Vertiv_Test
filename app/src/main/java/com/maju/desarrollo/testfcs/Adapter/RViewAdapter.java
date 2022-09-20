package com.maju.desarrollo.testfcs.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.Model.DatGeneralesF;

import java.util.List;

public class RViewAdapter extends RecyclerView.Adapter<RViewAdapter.DataObjectHolder> {


    List<DatGeneralesF> lista;
    Context mContex;
    String formatoid;
    private onSkuListener monSkuListener;

    public RViewAdapter(List<DatGeneralesF> lista, Context mContex, String formatoid, onSkuListener OnSkuListener){
        this.lista = lista;
        this.mContex = mContex;
        this.formatoid = formatoid;
        this.monSkuListener =  OnSkuListener;
    }


    public class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        onSkuListener clickListener;
        public RelativeLayout layoutABorrar;

        private TextView folioPT;
        private TextView formato;
        private TextView fecha;
        private TextView cliente;
        private TextView SR;
        private TextView task;
        private TextView item;
        private TextView serie;
        private String Nombre_Formato;


        public DataObjectHolder(@NonNull View itemView,onSkuListener onSkulistener) {
            super(itemView);

            this.folioPT =(TextView) itemView.findViewById(R.id.folioPT);
            this.formato=(TextView) itemView.findViewById(R.id.formato);
            this.fecha =(TextView) itemView.findViewById(R.id.fecha);
            this.cliente=(TextView) itemView.findViewById(R.id.cliente);
            this.SR=(TextView) itemView.findViewById(R.id.Gral_SR);
            this.task=(TextView) itemView.findViewById(R.id.Gral_task);
            this.item=(TextView) itemView.findViewById(R.id.item);
            this.serie=(TextView) itemView.findViewById(R.id.serie);
            this.Nombre_Formato = "";
            this.layoutABorrar = itemView.findViewById(R.id.layoutABorrar);
            this.clickListener = onSkulistener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onSkuClick(getAdapterPosition());
        }
    }

    public void removeItem(int position){
        lista.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(DatGeneralesF item,int position){
        lista.add(position, item);
        notifyItemInserted(position);
    }


    @NonNull
    @Override
    public DataObjectHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.recycler_adapter_view, viewGroup, false);


        //return new DataObjectHolder(view);
        return new DataObjectHolder(view, monSkuListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final DataObjectHolder holder, int position) {

        //holder.txtNombre.setText(lista.get(position).getNombre());

        switch (lista.get(position).getID_TIPO_FORMATO())
        {
            case "0":
                holder.Nombre_Formato="Pre-Trabajo";
                break;
            case "1":
                holder.Nombre_Formato="Calidad";
                break;
            case "2":
                holder.Nombre_Formato="Baterías";
                break;
            case "3":
                holder.Nombre_Formato="DCPower";
                break;
            case "4":
                holder.Nombre_Formato="Garantias";
                break;
            case "5":
                holder.Nombre_Formato="Bestel N1";
                break;
            case "6":
                holder.Nombre_Formato="Bestel N2";
                break;
            case "7":
                holder.Nombre_Formato="PDU";
                break;
            case "8":
                holder.Nombre_Formato="Gral. Servicios";
                break;
            case "9":
                holder.Nombre_Formato="STS2";
                break;
            case "10":
                holder.Nombre_Formato="Thermal";
                break;
            case "11":
                holder.Nombre_Formato="UPS";
                break;

        }
        try{holder.folioPT.setText(lista.get(position).getFOLIO_PRETRABAJO());}catch (Exception e){}
        try{holder.formato.setText(holder.Nombre_Formato);}catch (Exception e){}
        try{holder.fecha.setText(lista.get(position).getFECHA_INICIO());}catch (Exception e){
            holder.fecha.setText("S/D");
        }
        try{holder.cliente.setText(lista.get(position).getCLIENTE());}catch (Exception e){}
        try{holder.SR.setText(lista.get(position).getSR());}catch (Exception e){}
        try{holder.task.setText(lista.get(position).getTASK());}catch (Exception e){}
        try{holder.item.setText(lista.get(position).getITEM());}catch (Exception e){}
        try{holder.serie.setText(lista.get(position).getSERIE());}catch (Exception e){}

       // holder.itemView.setOnClickListener { listener(item) }

        /*Glide.with(context).load(listaC.get(position).getImg()).into(holder.img);

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Position: " +
                        holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


    public String  getIdFormafo(int p) {
        return lista.get(p).getID_FORMATO();
    }


    public String  getTipoFormato(int p) {
        return lista.get(p).getID_TIPO_FORMATO();
    }



    public interface onSkuListener{
        void onSkuClick(int position);
    }


}
