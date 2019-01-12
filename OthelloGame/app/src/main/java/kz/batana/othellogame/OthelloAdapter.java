package kz.batana.othellogame;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class OthelloAdapter extends BaseAdapter {

    private final Context mContext;
    private ArrayList<Cell> cells;

    public OthelloAdapter(Context context, ArrayList<Cell> cells) {
        this.mContext = context;
        this.cells = cells;
    }

    public void draw(ArrayList<Cell> list){
        cells = list;
    }

    @Override
    public int getCount() {
        return cells.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Cell cell = cells.get(position);

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.item_cell, null);
        }

        final ImageView gCell = (ImageView)convertView.findViewById(R.id.ivItemCell);
        switch (cell.inside){
            case -1:
                gCell.setImageResource(R.drawable.empty_green);
                break;
            case 0:
                gCell.setImageResource(R.drawable.dark_cell);
                break;
            case 1:
                gCell.setImageResource(R.drawable.white);
                break;
        }

        return convertView;
    }

}

