package com.example.ivan.htec;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by Ivan on 9/3/2016.
 */
public class ListItemActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item_layout);

        TextView title = (TextView) findViewById(R.id.textView_secondScreen_title);
        TextView description = (TextView) findViewById(R.id.textView_secondScreen_description);
        ImageView imageView = (ImageView) findViewById(R.id.imageView_secondScreen);
        Intent i = getIntent();
        title.setText(i.getStringExtra(ListObject.TITLE_C));
        description.setText(i.getStringExtra(ListObject.DESCRIPTION_C));
        String str = i.getStringExtra(ListObject.IMAGE_C);
        AppController.getInstance(this).getImageLoader().get(i.getStringExtra(ListObject.IMAGE_C),
                ImageLoader.getImageListener(imageView, R.mipmap.ic_loading, R.mipmap.ic_error_img));



    }
}
