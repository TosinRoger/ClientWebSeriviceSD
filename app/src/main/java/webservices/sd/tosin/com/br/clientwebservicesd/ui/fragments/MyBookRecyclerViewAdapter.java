package webservices.sd.tosin.com.br.clientwebservicesd.ui.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import webservices.sd.tosin.com.br.clientwebservicesd.R;
import webservices.sd.tosin.com.br.clientwebservicesd.models.Book;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Book} and makes a call to the
 * specified {@link MyBookFragment.OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyBookRecyclerViewAdapter extends RecyclerView.Adapter<MyBookRecyclerViewAdapter.ViewHolder> {

    private List<Book> mValues;
    private final MyBookFragment.OnListFragmentInteractionListener mListener;

    public MyBookRecyclerViewAdapter(List<Book> items, MyBookFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_my_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mAutor.setText(mValues.get(position).author);
        holder.mContentView.setText(mValues.get(position).title);
        holder.mDevolution.setText(mValues.get(position).getTimeDevolution());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void setList(List<Book> books) {
        this.mValues = books;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mAutor;
        public final TextView mContentView;
        public final TextView mDevolution;
        public Book mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mAutor = (TextView) view.findViewById(R.id.textView_item_author);
            mContentView = (TextView) view.findViewById(R.id.textView_item_title);
            mDevolution = (TextView) view.findViewById(R.id.textView_item_devolution);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
