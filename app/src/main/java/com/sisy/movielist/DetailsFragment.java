package com.sisy.movielist;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import Conection.GetPopular;
import Model.Variables;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsFragment extends Fragment {
    Context context;
    View rootView;
    LinearLayout linearLayout;
    ProgressBar progressBar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "id";

    // TODO: Rename and change types of parameters
    private String mParam1;


    public DetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailsFragment newInstance(String param1, String param2) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_details, container, false);
        context = (Context) getActivity();

        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        linearLayout = (LinearLayout) rootView.findViewById(R.id._linear_layout_bank);
        
        Integer pos = Integer.parseInt(mParam1);

         ImageView _image_view_poster = (ImageView) rootView.findViewById(R.id._imageViewPoster);
         TextView _image_view_popularity = (TextView) rootView.findViewById(R.id._textViewPopularity);
         TextView _image_view_votes = (TextView) rootView.findViewById(R.id._textViewVotes);
         TextView _image_view_title = (TextView) rootView.findViewById(R.id._textViewTitle);
         TextView _textViewDescription = (TextView) rootView.findViewById(R.id._textViewDescription);
        TextView _textViewDate = (TextView) rootView.findViewById(R.id._textViewDate);

         _image_view_popularity.setText(GetPopular.results.get(pos).getPopularity().toString());
        _image_view_votes.setText(GetPopular.results.get(pos).getVoteAverage().toString());
        _image_view_title.setText(GetPopular.results.get(pos).getOriginalTitle());
        _textViewDescription.setText(GetPopular.results.get(pos).getOverview());
        _textViewDate.setText(GetPopular.results.get(pos).getReleaseDate());
        Picasso.with(context)
                .load(Variables.getUrlImage()+GetPopular.results.get(pos).getPosterPath())
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round)
                .into(_image_view_poster);


        return rootView;
    }

}
