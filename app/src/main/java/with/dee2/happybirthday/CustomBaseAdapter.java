package with.dee2.happybirthday;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomBaseAdapter extends BaseAdapter {

    String names[];
    int imges[];
    String times[];
    String messages[];
    LayoutInflater inflater;

    public CustomBaseAdapter(Context ctx, String[] names, int[] imges, String[] times,String[] messages ) {
        this.names = names;
        this.imges = imges;
        this.times = times;
        this.messages=messages;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=inflater.inflate(R.layout.activity_custom_list_view,null);
        TextView name=view.findViewById(R.id.name);
        ImageView img=view.findViewById(R.id.imageIcon);
        TextView time=view.findViewById(R.id.timestamp);
        TextView m=view.findViewById(R.id.user_message);
        name.setText(names[i]);
        img.setImageResource(imges[i]);
        time.setText(times[i]);
        m.setText(messages[i]);


        return view;
    }
}
