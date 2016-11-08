package webservices.sd.tosin.com.br.clientwebservicesd.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import webservices.sd.tosin.com.br.clientwebservicesd.R;
import webservices.sd.tosin.com.br.clientwebservicesd.models.Book;
import webservices.sd.tosin.com.br.clientwebservicesd.models.CustomResponse;
import webservices.sd.tosin.com.br.clientwebservicesd.models.User;
import webservices.sd.tosin.com.br.clientwebservicesd.rest.BookClient;
import webservices.sd.tosin.com.br.clientwebservicesd.rest.LoanClient;
import webservices.sd.tosin.com.br.clientwebservicesd.rest.ServiceGenerator;
import webservices.sd.tosin.com.br.clientwebservicesd.ui.activities.MainActivity;
import webservices.sd.tosin.com.br.clientwebservicesd.ui.dialogs.CustomMessageDialog;
import webservices.sd.tosin.com.br.clientwebservicesd.ui.dialogs.DetailBookDialog;
import webservices.sd.tosin.com.br.clientwebservicesd.ui.dialogs.DetailMyBookDialog;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class MyBookFragment extends Fragment implements DetailMyBookDialog.DialogClick {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private MyBookRecyclerViewAdapter adapter;
    private User user;
    private List<Book> books;
    private Context context;
    private RecyclerView recyclerView;
    private TextView emptyList;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MyBookFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static MyBookFragment newInstance(int columnCount, User user) {
        MyBookFragment fragment = new MyBookFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putSerializable("user", user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            user = (User) getArguments().getSerializable("user");
        }
        if (books == null)
            books = new ArrayList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_book_list, container, false);

        context = view.getContext();

        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        emptyList = (TextView) view.findViewById(R.id.emptyList);

        Context context = view.getContext();
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        adapter = new MyBookRecyclerViewAdapter(books, mListener);
        recyclerView.setAdapter(adapter);

        if (books.isEmpty())
            emptyList.setVisibility(View.VISIBLE);
        else
            emptyList.setVisibility(View.GONE);

        fetchBooks();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                fetchBooks();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void clickDevolution(Book book) {
        LoanClient client = ServiceGenerator.createService(LoanClient.class, MainActivity.host);
        Map<String, Integer> map = new HashMap<>();
        map.put("id", book.id);
        String authorization = user.base64();
        Call<CustomResponse> call = client.devolution(authorization, map);
        call.enqueue(new Callback<CustomResponse>() {
            @Override
            public void onResponse(Call<CustomResponse> call, Response<CustomResponse> response) {
                if (response.isSuccessful()) {
                    fetchBooks();
                    CustomResponse resp = response.body();
                    CustomMessageDialog.message(context, resp.status, resp.msg);
                } else {
                    CustomMessageDialog.message(context, "ERRO", "Problemas na requisição");
                }
            }

            @Override
            public void onFailure(Call<CustomResponse> call, Throwable t) {
                CustomMessageDialog.message(context, "Erro", "Erro");
            }
        });
    }

    @Override
    public void clickRenovation(Book book) {
        LoanClient client = ServiceGenerator.createService(LoanClient.class, MainActivity.host);
        Map<String, Integer> map = new HashMap<>();
        map.put("id", book.id);
        String authorization = user.base64();
        Call<CustomResponse> call = client.renovation(authorization, map);
        call.enqueue(new Callback<CustomResponse>() {
            @Override
            public void onResponse(Call<CustomResponse> call, Response<CustomResponse> response) {
                if (response.isSuccessful()) {
                    fetchBooks();
                    CustomResponse resp = response.body();
                    CustomMessageDialog.message(context, resp.status, resp.msg);
                } else {
                    CustomMessageDialog.message(context, "ERRO", "Problemas na requisição");
                }
            }

            @Override
            public void onFailure(Call<CustomResponse> call, Throwable t) {
                CustomMessageDialog.message(context, "Erro", "Erro");
            }
        });
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Book item);
    }

    private void fetchBooks() {
        BookClient client = ServiceGenerator.createService(BookClient.class, MainActivity.host);
        String authorization = user.base64();
        Call<List<Book>> call = client.getMyBooks(authorization);
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if (response.isSuccessful()) {

                    Object object = response.body();
                    if (object instanceof CustomResponse)
                        CustomMessageDialog.message(context, ((CustomResponse) object).status, ((CustomResponse) object).msg);
                    else {
                        books = response.body();
                        adapter.setList(books);

                        if (books.isEmpty())
                            emptyList.setVisibility(View.VISIBLE);
                        else
                            emptyList.setVisibility(View.GONE);
                    }
                } else {
                    CustomMessageDialog.message(context, "Erro", "problemas na requisicao");
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                CustomMessageDialog.message(context, "Erro", "Erro");
            }
        });
    }

    private OnListFragmentInteractionListener mListener = new OnListFragmentInteractionListener() {
        @Override
        public void onListFragmentInteraction(Book item) {
            FragmentManager fm = getChildFragmentManager();
            DetailMyBookDialog dialog = DetailMyBookDialog.newInstance(item);
            dialog.show(fm, "detail_my_book");

        }
    };
}
