package lv.teteris.toms.cardinfo.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import lv.teteris.toms.cardinfo.R;
import lv.teteris.toms.cardinfo.models.Card;

/**
 * Created by tt007 on 03.08.2017.
 */

public class SimpleCardAdapter extends ArrayAdapter<Card>{
    Context _context;
    ArrayList<Card> _cards;
    Card card;
    int _layoutId;

    public SimpleCardAdapter(@NonNull Context context, ArrayList<Card> cards) {
        super(context, R.layout.shop_card, cards);
        this._context = context;
        this._cards = cards;
        this._layoutId = R.layout.shop_card;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Card item = getItem(position);

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater)  this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(this._layoutId, parent, false);
        }

        TextView name = (TextView) view.findViewById(R.id.owner_full_name);
        TextView cardNo = (TextView) view.findViewById(R.id.card_no);

        name.setText(item.getName());
        cardNo.setText(item.getCardNo());

        return view;
    }
}
