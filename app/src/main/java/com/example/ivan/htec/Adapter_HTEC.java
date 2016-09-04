package com.example.ivan.htec;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;

/**
 * Created by Ivan on 9/2/2016.
 */
public class Adapter_HTEC extends ArrayAdapter<ListObject> {

    private final Context context;
    private final ArrayList<ListObject> arrayList;

    public Adapter_HTEC(Context context, ArrayList<ListObject> arrayList) {
        super(context, -1, arrayList);
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item_layout, parent, false);

        TextView textTitle = (TextView) rowView.findViewById(R.id.text_title);
        textTitle.setText(arrayList.get(position).getTitle());

        TextView textDesc = (TextView) rowView.findViewById(R.id.text_description);
        textDesc.setText(arrayList.get(position).getDescription());

        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView);

        AppController.getInstance(getContext()).getImageLoader().get(arrayList.get(position).getImage(),
                ImageLoader.getImageListener(imageView, R.mipmap.ic_loading, R.mipmap.ic_error_img));

        return rowView;
    }
}
