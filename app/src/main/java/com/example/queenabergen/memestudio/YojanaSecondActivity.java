package com.example.queenabergen.memestudio;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

//TODO: add button to layout that saves image. google how to save image to gallery

public class YojanaSecondActivity extends AppCompatActivity {
    ImageView imageView;
    EditText editText;
    Button okaybutton;
    TextView textView;
    Button buttonSave;
    Drawable drawable;
    Uri URI;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yojana_second_activity);

        imageView = (ImageView) findViewById(R.id.second_image);
        editText = (EditText) findViewById(R.id.edit_text);
        okaybutton = (Button) findViewById(R.id.okay_button);
        textView = (TextView) findViewById(R.id.result_textview);
        buttonSave = (Button) findViewById(R.id.save_button);

        final Integer id = (Integer) getIntent().getExtras().get(getResources().getString(R.string.image_to_pass));

        Picasso.with(getApplicationContext()).load(id).into(imageView);

        okaybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageView.setImageDrawable(writeTextOnDrawable(id, editText.getText().toString()));
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                drawable = imageView.getDrawable();

                bitmap = ((BitmapDrawable)drawable).getBitmap();

                String imagePath = MediaStore.Images.Media.insertImage(
                        getContentResolver(),
                        bitmap,
                        "demo_image",
                        "demo_image"
                );

                URI = Uri.parse(imagePath);

                Toast.makeText(YojanaSecondActivity.this, "Image Saved Successfully", Toast.LENGTH_LONG).show();



            }
        });
    }

    private BitmapDrawable writeTextOnDrawable(int drawableId, String text) {

        Bitmap bm = BitmapFactory.decodeResource(getResources(), drawableId)
                .copy(Bitmap.Config.ARGB_8888, true);

        Typeface tf = Typeface.create("Helvetica", Typeface.BOLD);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GREEN);
        paint.setTypeface(tf);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(convertToPixels(getApplicationContext(), 30));

        Rect textRect = new Rect();
        paint.getTextBounds(text, 0, text.length(), textRect);

        Canvas canvas = new Canvas(bm);

        if(textRect.width() >= (canvas.getWidth() - 4))
            paint.setTextSize(convertToPixels(getApplicationContext(), 7));


        int xPos = (canvas.getWidth() / 2) - 2;

        int yPos = (int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2)) ;

        canvas.drawText(text, xPos, yPos, paint);

        return new BitmapDrawable(getResources(), bm);
    }

    public static int convertToPixels(Context context, int nDP)
    {
        final float conversionScale = context.getResources().getDisplayMetrics().density;

        return (int) ((nDP * conversionScale) + 0.5f) ;

    }

}