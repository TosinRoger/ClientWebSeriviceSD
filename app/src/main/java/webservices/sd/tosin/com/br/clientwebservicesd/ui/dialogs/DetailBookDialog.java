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

/**
 * Created by tosin on 05/11/16.
 */

public class DetailBookDialog extends DialogFragment implements View.OnClickListener{

    private TextView author;
    private TextView about;
    private TextView devolution;
    private Button btnCLose;
    private Button btnLoan;
    private Button btnReservation;

    private Book book;

    public static DetailBookDialog newInstance(Book book) {
        DetailBookDialog frag = new DetailBookDialog();

        Bundle args = new Bundle();
        args.putSerializable("book", book);
        frag.setArguments(args);

        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_detail_book, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        author = (TextView) view.findViewById(R.id.textView_detail_author);
        about = (TextView) view.findViewById(R.id.textView_detail_about);
        devolution = (TextView) view.findViewById(R.id.textView_detail_devolution);

        btnCLose = (Button) view.findViewById(R.id.buttonClose);
        btnLoan = (Button) view.findViewById(R.id.buttonLoan);
        btnReservation = (Button) view.findViewById(R.id.buttonReservation);

        if (getArguments().containsKey("book")) {
            this.book = (Book) getArguments().getSerializable("book");
        }

        if (book != null) {
            getDialog().setTitle(book.title);
            author.setText(book.author);
            about.setText(book.about);

            if (book.available) {
                devolution.setText("Disponivel");
                btnLoan.setVisibility(View.VISIBLE);
                btnReservation.setVisibility(View.GONE);
            }
            else {
                devolution.setVisibility(View.GONE);
                btnLoan.setVisibility(View.GONE);
                btnReservation.setVisibility(View.VISIBLE);
            }
        }
        btnCLose.setOnClickListener(this);
        btnLoan.setOnClickListener(this);
        btnReservation.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnCLose) {
            getDialog().dismiss();
        }
        else if (view == btnLoan) {
            getDialog().dismiss();
            BookFragment fragment = (BookFragment) getParentFragment();
            if (fragment != null){
                fragment.clickLoan(book);
            }
        }
        else if (view == btnReservation) {
            getDialog().dismiss();
            BookFragment fragment = (BookFragment) getParentFragment();
            if (fragment != null){
                fragment.clickReservation(book);
            }
        }
    }

    public interface DialogClick {
        void clickLoan(Book book);
        void clickReservation(Book book);
    }
}
