package com.example.wubin.swipelayoutmodule;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.daimajia.swipe.SwipeLayout;
import com.nineoldandroids.view.ViewHelper;

public class SwipeLayoutActivity extends AppCompatActivity {

    private SwipeLayout sample1, sample2, sample3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_swipe_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.my);

        initSample1();

        initSample2();

        initSample3();

    }

    private void initSample1() {

        sample1 = (SwipeLayout) findViewById(R.id.sample1);

        sample1.setShowMode(SwipeLayout.ShowMode.PullOut);

        sample1.addDrag(SwipeLayout.DragEdge.Left, sample1.findViewById(R.id.bottom_wrapper));
        sample1.addDrag(SwipeLayout.DragEdge.Right, sample1.findViewById(R.id.bottom_wrapper_2));

        View starBottView = sample1.findViewById(R.id.starbott);
        sample1.addDrag(SwipeLayout.DragEdge.Top, starBottView);
        sample1.addDrag(SwipeLayout.DragEdge.Bottom, starBottView);

        sample1.addRevealListener(R.id.delete, new SwipeLayout.OnRevealListener() {
            @Override
            public void onReveal(View child, SwipeLayout.DragEdge edge, float fraction, int distance) {

            }
        });

        sample1.getSurfaceView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SwipeLayoutActivity.this, "Click on surface", Toast.LENGTH_SHORT).show();
                Log.d(SwipeLayoutActivity.class.getName(), "click on surface");
            }
        });
        sample1.getSurfaceView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(SwipeLayoutActivity.this, "longClick on surface", Toast.LENGTH_SHORT).show();
                Log.d(SwipeLayoutActivity.class.getName(), "longClick on surface");
                return true;
            }
        });
        sample1.findViewById(R.id.star2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SwipeLayoutActivity.this, "Star", Toast.LENGTH_SHORT).show();
            }
        });

        sample1.findViewById(R.id.trash2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SwipeLayoutActivity.this, "Trash Bin", Toast.LENGTH_SHORT).show();
            }
        });

        sample1.findViewById(R.id.magnifier2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SwipeLayoutActivity.this, "Magnifier", Toast.LENGTH_SHORT).show();
            }
        });

        sample1.addRevealListener(R.id.starbott, new SwipeLayout.OnRevealListener() {
            @Override
            public void onReveal(View child, SwipeLayout.DragEdge edge, float fraction, int distance) {

                View star = child.findViewById(R.id.star);
                float d = child.getHeight() / 2 - star.getHeight() / 2;
                ViewHelper.setTranslationY(star, d * fraction);
                ViewHelper.setScaleX(star, fraction + 0.6f);
                ViewHelper.setScaleY(star, fraction + 0.6f);

            }
        });

    }

    private void initSample2() {

        sample2 = (SwipeLayout) findViewById(R.id.sample2);

        sample2.setShowMode(SwipeLayout.ShowMode.LayDown);

        sample2.addDrag(SwipeLayout.DragEdge.Right, sample2.findViewWithTag("Bottom2"));
//        sample2.setShowMode(SwipeLayout.ShowMode.PullOut);

        sample2.findViewById(R.id.star).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SwipeLayoutActivity.this, "Star", Toast.LENGTH_SHORT).show();
            }
        });

        sample2.findViewById(R.id.trash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SwipeLayoutActivity.this, "Trash Bin", Toast.LENGTH_SHORT).show();
            }
        });

        sample2.findViewById(R.id.magnifier).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SwipeLayoutActivity.this, "Magnifier", Toast.LENGTH_SHORT).show();
            }
        });

        sample2.findViewById(R.id.click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SwipeLayoutActivity.this, "Yo", Toast.LENGTH_SHORT).show();
            }
        });
        sample2.getSurfaceView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SwipeLayoutActivity.this, "Click on surface", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initSample3() {

        sample3 = (SwipeLayout) findViewById(R.id.sample3);

        sample3.addDrag(SwipeLayout.DragEdge.Top, sample3.findViewWithTag("Bottom3"));

        sample3.addRevealListener(R.id.bottom_wrapper_child1, new SwipeLayout.OnRevealListener() {
            @Override
            public void onReveal(View child, SwipeLayout.DragEdge edge, float fraction, int distance) {

                View star = child.findViewById(R.id.star);
                float d = child.getHeight() / 2 - star.getHeight() / 2;
                ViewHelper.setTranslationY(star, d * fraction);
                ViewHelper.setScaleX(star, fraction + 0.6f);
                ViewHelper.setScaleY(star, fraction + 0.6f);
                int c = (Integer) evaluate(fraction, Color.parseColor("#dddddd"), Color.parseColor("#4C535B"));
                child.setBackgroundColor(c);

            }
        });
        sample3.findViewById(R.id.bottom_wrapper_child1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SwipeLayoutActivity.this, "Yo!", Toast.LENGTH_SHORT).show();
            }
        });
        sample3.getSurfaceView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SwipeLayoutActivity.this, "Click on surface", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_nested) {
            startActivity(new Intent(this, NestedExample.class));
            return true;
        } else if (id == R.id.action_listview) {
            startActivity(new Intent(this, ListViewExample.class));
            return true;
        } else if (id == R.id.action_gridview) {
            startActivity(new Intent(this, GridViewExample.class));
            return true;
        } else if (id == R.id.action_recycler) {
            startActivity(new Intent(this, RecyclerViewExample.class));
        }
        return super.onOptionsItemSelected(item);
    }

    /*
    Color transition method.
     */
    public Object evaluate(float fraction, Object startValue, Object endValue) {

        int startInt = (Integer) startValue;
        int startA = (startInt >> 24) & 0xff;
        int startR = (startInt >> 16) & 0xff;
        int startG = (startInt >> 8) & 0xff;
        int startB = startInt & 0xff;

        int endInt = (Integer) endValue;
        int endA = (endInt >> 24) & 0xff;
        int endR = (endInt >> 16) & 0xff;
        int endG = (endInt >> 8) & 0xff;
        int endB = endInt & 0xff;

        return (int) ((startA + (int) (fraction * (endA - startA))) << 24) |
                (int) ((startR + (int) (fraction * (endR - startR))) << 16) |
                (int) ((startG + (int) (fraction * (endG - startG))) << 8) |
                (int) ((startB + (int) (fraction * (endB - startB))));

    }
}
