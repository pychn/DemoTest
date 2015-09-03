package demo.py.com.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by fly on 15-8-30.
 */
public class MainActivity extends Activity {

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 100;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item, parent, false);
                }

                final TextView view1 = GetViewHodler.getAdapterView(convertView, R.id.view1);
                final TextView view2 = GetViewHodler.getAdapterView(convertView, R.id.view2);
                final TextView view3 = GetViewHodler.getAdapterView(convertView, R.id.view3);
                final TextView view4 = GetViewHodler.getAdapterView(convertView, R.id.view4);

                view1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        view1.setVisibility(View.GONE);
                        view2.setVisibility(View.GONE);
                        view3.setVisibility(View.VISIBLE);
                        view4.setVisibility(View.GONE);
                    }
                });
                view2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        view1.setVisibility(View.GONE);
                        view1.setVisibility(View.GONE);
                        view3.setVisibility(View.GONE);
                        view4.setVisibility(View.VISIBLE);
                    }
                });
                view3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        view1.setVisibility(View.VISIBLE);
                        view2.setVisibility(View.VISIBLE);
                        view3.setVisibility(View.GONE);
                        view4.setVisibility(View.GONE);
                    }
                });
                view4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        view1.setVisibility(View.VISIBLE);
                        view2.setVisibility(View.VISIBLE);
                        view3.setVisibility(View.GONE);
                        view4.setVisibility(View.GONE);
                    }
                });

                return convertView;
            }
        });
    }
}
