package br.com.ettec.produtividade.util;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.ettec.produtividade.domain.Turno;

/**
 * Created by usuario on 01/04/2017.
 */

public class SpinAdapterTurno extends ArrayAdapter<Turno> {

    // Your sent context
    private Context context;

    private ArrayList<Turno> values;

    public SpinAdapterTurno(Context context, int resource, ArrayList<Turno> turnos) {
        super(context, resource, turnos);
        this.context = context;
        this.values = turnos;
    }

    public int getCount(){
        return values.size();
    }

    public Turno getItem(int position){
        return values.get(position);
    }

    public long getItemId(int position){
        return position;
    }

    // And the "magic" goes here
    // This is for the "passive" state of the spinner
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        TextView label = new TextView(context);
        label.setTextColor(Color.BLUE);
        // Then you can get the current item using the values array (Users array) and the current position
        // You can NOW reference each method you has created in your bean object (User class)
        label.setText(values.get(position).getDescricao());

        // And finally return your dynamic (or custom) view for each spinner item
        return label;
    }

    // And here is when the "chooser" is popped up
    // Normally is the same view, but you can customize it if you want
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLUE);
        label.setText(values.get(position).getDescricao());

        return label;
    }

}
