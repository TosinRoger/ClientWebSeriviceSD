package webservices.sd.tosin.com.br.clientwebservicesd.ui.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import webservices.sd.tosin.com.br.clientwebservicesd.R;
import webservices.sd.tosin.com.br.clientwebservicesd.models.Book;
import webservices.sd.tosin.com.br.clientwebservicesd.ui.fragments.BookFragment;
import webservices.sd.tosin.com.br.clientwebservicesd.ui.fragments.MyBookFragment;

/**
 * Created by tosin on 05/11/16.
 */

public class DetailMyBookDialog extends DialogFragment implements View.OnClickListener{

    private TextView author;
    private TextView about;
    private TextView devolution;
    private Button btnCLose;
    private Button btnRenovation;
    private Button btnDevolution;

    private Book book;

    public static DetailMyBookDialog newInstance(Book book) {
        DetailMyBookDialog frag = new DetailMyBookDialog();

        Bundle args = new Bundle();
        args.putSerializable("book", book);
        frag.setArguments(args);

        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_detail_my_book, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        author = (TextView) view.findViewById(R.id.textView_detail_author);
        about = (TextView) view.findViewById(R.id.textView_detail_about);
        devolution = (TextView) view.findViewById(R.id.textView_detail_devolution);

        btnCLose = (Button) view.findViewById(R.id.buttonClose);
        btnRenovation = (Button) view.findViewById(R.id.buttonRenovation);
        btnDevolution = (Button) view.findViewById(R.id.buttonDevolution);

        if (getArguments().containsKey("book")) {
            this.book = (Book) getArguments().getSerializable("book");
        }

        if (book != null) {
            getDialog().setTitle(book.title);
            author.setText(book.author);
            about.setText(book.about);
        }
        btnCLose.setOnClickListener(this);
        btnRenovation.setOnClickListener(this);
        btnDevolution.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnCLose) {
            getDialog().dismiss();
        }
        else if (view == btnDevolution) {
            getDialog().dismiss();
            MyBookFragment fragment = (MyBookFragment) getParentFragment();
            if (fragment != null){
                fragment.clickDevolution(book);
            }
        }
        else if (view == btnRenovation) {
            getDialog().dismiss();
            MyBookFragment fragment = (MyBookFragment) getParentFragment();
            if (fragment != null){
                fragment.clickRenovation(book);
            }
        }
    }

    public interface DialogClick {
        void clickDevolution(Book book);
        void clickRenovation(Book book);
    }
}
