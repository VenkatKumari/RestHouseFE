package com.sih.resthousefe;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.aakash.resthousefe.components.DaggerListDataServiceComponent;
import com.aakash.resthousefe.components.ListDataServiceComponent;
import com.aakash.resthousefe.modules.ContextModule;
import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SearchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    @Nullable @BindView(R.id.search)
    android.widget.SearchView mSearchView;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;



    @BindView(R.id.fab)
    FloatingActionButton fab;

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private static final String TAG = "MainActivity";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //HolidayHome fragment
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public int Listsize = 0;

    List<String> pname = new ArrayList<>();
    List<String> paddress = new ArrayList<>();
    List<String> pcontact = new ArrayList<>();
    List<String> pavail = new ArrayList<>();
    List<String> pdetail = new ArrayList<>();
    List<String> ids = new ArrayList<>();
    @Inject
    ApolloCall<ViewInfoQuery.Data> apolloCall;
    Observable<ViewInfoQuery.Holidayhome> observable;
    LinearLayout linearLayout;
    LinearLayoutManager linearLayoutManager;

    private OnFragmentInteractionListener mListener;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListDataServiceComponent listDataServiceComponent = DaggerListDataServiceComponent.builder()
                .contextModule(new ContextModule(this.getContext()))
                .build();
        listDataServiceComponent.injectSearchFragment(this);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        observable = Observable.create(e ->{
            linearLayout.setVisibility(View.VISIBLE);
            apolloCall.enqueue(new ApolloCall.Callback<ViewInfoQuery.Data>() {
                @Override
                public void onResponse(@Nonnull Response<ViewInfoQuery.Data> response) {
                    for(int i =0;i<response.data().holidayhome().size();i++)
                        e.onNext(response.data().holidayhome().get(i));
                    e.onComplete();
                }

                @Override
                public void onFailure(@Nonnull ApolloException e) {

                }
            });
        });
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.searchfragment, container, false);
        // Inflate the layout for this fragment
        ButterKnife.bind(this,view);

        mImageUrls.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        mNames.add("Northern Railways");

        mImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mNames.add("Southern Railways");

        mImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mNames.add("North Eastern Railways");

        mImageUrls.add("https://i.redd.it/j6myfqglup501.jpg");
        mNames.add("Eastern Railways");


        mImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mNames.add("Central Railways");

        mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");
        mNames.add("Western Railways");


        mImageUrls.add("https://i.redd.it/glin0nwndo501.jpg");
        mNames.add("North Western Railways");

        mImageUrls.add("https://i.redd.it/obx4zydshg601.jpg");
        mNames.add("West Central");

        mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
        mNames.add("South Western Railways");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapter(getActivity(), mNames, mImageUrls);
        recyclerView.setAdapter(adapter);



        mSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), SearchPage.class);
                startActivity(intent);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), chatbot.class);
                startActivity(intent);
            }
        });

        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this.getActivity()){
            @Override
            public boolean canScrollVertically() {
                super.canScrollVertically();
                return false;
            }
        };
        mRecyclerView.setLayoutManager(mLayoutManager);
        linearLayout = (LinearLayout)view.findViewById(R.id.PbarLayout);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ViewInfoQuery.Holidayhome>() {
                    @Override
                    public void onNext(ViewInfoQuery.Holidayhome holidayhome) {
                        pname.add(holidayhome.hhname);
                        paddress.add(holidayhome.hhlocation);
                        pcontact.add(holidayhome.hhacontact);
                        pdetail.add(holidayhome.hhadetails);
                        pavail.add(holidayhome.hhavail.toString());
                        ids.add(holidayhome.id.toString());
                        Log.d("TAg","OnNext: "+ holidayhome);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        linearLayout.setVisibility(View.GONE);
                        mAdapter = new MyAdapter(ids,pname,paddress,pcontact,pavail,pdetail);
                        mRecyclerView.setAdapter(mAdapter);

                    }
                });
        return view;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ExampleApplication.getRefWatcher(getActivity()).watch(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(adapter != null){
            adapter = null;
        }
    }
}
