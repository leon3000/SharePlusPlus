package com.droidrise.snaptext;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hai on 4/24/17.
 */
public class ClipsViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.text_view)
    TextView mTextView;

    public ClipsViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void onBindViewHolder(int position) {
        mTextView.setText("Hello World" + this);
    }
}