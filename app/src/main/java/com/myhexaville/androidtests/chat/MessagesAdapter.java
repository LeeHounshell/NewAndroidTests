package com.myhexaville.androidtests.chat;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myhexaville.androidtests.R;

import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.Holder> {
    private final static String TAG = "LEE: <" + MessagesAdapter.class.getSimpleName() + ">";

    private List<String> list;

    public MessagesAdapter() {
        Log.v(TAG, "MessagesAdapter");
    }

    public MessagesAdapter(List<String> list) {
        Log.v(TAG, "MessagesAdapter List");
        this.list = list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.v(TAG, "onCreateViewHolder");
        View inflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.messages_list_item, parent, false);
        return new Holder(inflate);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Log.v(TAG, "onBindViewHolder");
        String message = list.get(position);
        holder.text.setText(message);
    }

    @Override
    public int getItemCount() {
        Log.v(TAG, "getItemCount");
        return list.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        TextView text;

        Holder(View itemView) {
            super(itemView);
            Log.v(TAG, "Holder");
            text = itemView.findViewById(R.id.text);
        }
    }

}
