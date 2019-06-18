package com.sih.resthousefe;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sih.resthousefe.pojo.Station;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.jar.Attributes;


import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RestHFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RestHFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RestHFragment extends Fragment{

    RailwayPNRServices railwayPNRServices = RailwayPNRServices.retrofit.create(RailwayPNRServices.class);
    String Edit_pnr;
    String Name1,ToName1,GetTrName1,GetTrNo1,GetDate1;

    @BindView(R.id.textpnr)
    TextView TextPnr;
    @BindView(R.id.editpnr)
    EditText EditPnr;
    @BindView(R.id.search)
    Button Search;


    @BindView(R.id.from) TextView From;
    @BindView(R.id.getfrom) TextView Name;
    @BindView(R.id.to) TextView To;
    @BindView(R.id.getto) TextView ToName;
    @BindView(R.id.tr_name) TextView TrName;
    @BindView(R.id.get_tr_name) TextView GetTrName;
    @BindView(R.id.tr_no) TextView TrNo;
    @BindView(R.id.get_tr_no) TextView GetTrNo;
    @BindView(R.id.date) TextView date;
    @BindView(R.id.getdate) TextView GetDate;

    @BindView(R.id.fromst)
    Button FromSt;
    @BindView(R.id.tost)
    Button ToSt;
    @BindView(R.id.txtcheckin) TextView Txt_Checkin;
    @BindView(R.id.checkin)
    Spinner CheckIn;
    @BindView(R.id.txtcheckout) TextView Txt_Checkout;
    @BindView(R.id.checkout)
    Spinner CheckOut;
    @BindView(R.id.txt_no_pass) TextView Txt_No_Pass;
    @BindView(R.id.no_pass)
    Spinner No_Pass;
    @BindView(R.id.check)
    Button Check;




//    private List<Date> dateList = new ArrayList<Date>();
//    int count = 0;
//    Date todayDate = new Date();



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public RestHFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RestHFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RestHFragment newInstance(String param1, String param2) {
        RestHFragment fragment = new RestHFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //Button Search;

        super.onCreate(savedInstanceState);





        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_rest_h,container,false);
        ButterKnife.bind(this,rootview);

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Edit_pnr = EditPnr.getText().toString().trim();

                From.setVisibility(View.VISIBLE);
                Name.setVisibility(View.VISIBLE);
                To.setVisibility(View.VISIBLE);
                ToName.setVisibility(View.VISIBLE);
                TrName.setVisibility(View.VISIBLE);
                GetTrName.setVisibility(View.VISIBLE);
                TrNo.setVisibility(View.VISIBLE);
                GetTrNo.setVisibility(View.VISIBLE);
                date.setVisibility(View.VISIBLE);
                GetDate.setVisibility(View.VISIBLE);
                FromSt.setVisibility(View.VISIBLE);
                ToSt.setVisibility(View.VISIBLE);
                Txt_Checkin.setVisibility(View.VISIBLE);
                CheckIn.setVisibility(View.VISIBLE);
                Txt_Checkout.setVisibility(View.VISIBLE);
                CheckOut.setVisibility(View.VISIBLE);
                Txt_No_Pass.setVisibility(View.VISIBLE);
                No_Pass.setVisibility(View.VISIBLE);
                Check.setVisibility(View.VISIBLE);



                final Call<Station> call = railwayPNRServices.listStations(Edit_pnr);
                call.enqueue(new Callback<Station>() {
                    @Override
                    public void onResponse(Call<Station> call, Response<Station> response) {




                        Name1 = (response.body().getFrom_station().getName());
                        Name.setText(Name1);

                        ToName1 = (response.body().getTo_station().getName());
                        ToName.setText(ToName1);

                        GetTrName1 = (response.body().getTrain().getName());
                        GetTrName.setText(GetTrName1);

                        GetTrNo1 = (response.body().getTrain().getNumber());
                        GetTrNo.setText(GetTrNo1);

                        GetDate1 = (response.body().getDoj());
                        GetDate.setText(GetDate1);

                        Name1 = (response.body().getFrom_station().getName());
                        FromSt.setText(Name1);

                        ToName1 = (response.body().getTo_station().getName());
                        ToSt.setText(ToName1);




                    }


                    @Override
                    public void onFailure(Call<Station> call, Throwable t) {
                        Log.d("tag","error: "+t);
                    }


                });

            }

        });
        Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Room Available",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(), Det_RH_View.class);
                startActivity(intent);
            }
        });

        FromSt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToSt.setVisibility(View.INVISIBLE);
            }
        });
        ToSt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FromSt.setVisibility(View.INVISIBLE);
            }
        });


//        for (int i=0; i<dateList.size(); i++){



        // Check if dates within the array are consecutive from todayDate, if so then increment count by 1.
//        }




//        final Call<Station> call = railwayPNRServices.listStations("2824170178");


        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_rest_h, container, false);
        return rootview;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
}
