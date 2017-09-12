package lv.teteris.toms.cardinfo.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import lv.teteris.toms.cardinfo.R;
import lv.teteris.toms.cardinfo.models.User;

/**
 * Created by tt007 on 31.07.2017.
 */

public class SimpleUserAdapter extends ArrayAdapter<User> {

    User user;
    Context _context;
    int _layoutId;

    public SimpleUserAdapter(@NonNull Context context, @NonNull List<User> users) {
        super(context, R.layout.user_card, users);
        this._context = context;
        this._layoutId = R.layout.user_card;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        User item = getItem(position);

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater)  this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(this._layoutId, parent, false);
        }

        TextView name = (TextView) view.findViewById(R.id.full_name);
        TextView email = (TextView) view.findViewById(R.id.card_1);

        name.setText(item.getName());
        email.setText(item.getEmail());

        return view;
    }

    @Override
    public void notifyDataSetChanged() {
        System.out.println("Notify - user count-: " + this.getCount());
        super.notifyDataSetChanged();
    }
}
