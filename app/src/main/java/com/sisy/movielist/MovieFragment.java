package com.sisy.movielist;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import Conection.GetPopular;
import Conection.GetRate;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {
    Context context;
    View rootView;
    LinearLayout linearLayout;
    ProgressBar progressBar;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_movie, container, false);

        context = (Context) getActivity();

        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        linearLayout = (LinearLayout) rootView.findViewById(R.id._linear_layout_bank);

        final GridView gridView = (GridView) rootView.findViewById(R.id._gridViewMovie);
        GetPopular.getPopular(context,gridView,linearLayout,progressBar);

        Button button_popularity = (Button) rootView.findViewById(R.id._button_popularity);
        Button button_votes = (Button) rootView.findViewById(R.id._button_votes);

        button_popularity.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView title = (TextView) rootView.findViewById(R.id._text_view_title);
                        title.setText(R.string.movies_more_popular);
                        GetPopular.getPopular(context,gridView,linearLayout,progressBar);
                    }
                }
        );
        button_votes.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView title = (TextView) rootView.findViewById(R.id._text_view_title);
                        title.setText(R.string.most_voted_movies);
                        GetRate.getRated(context,gridView,linearLayout,progressBar);
                    }
                }
        );

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                DetailsFragment fr = new DetailsFragment();
                Bundle bn = new Bundle();
                bn.putString("id",String.valueOf(position));
                fr.setArguments(bn);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment,fr)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return rootView;
    }

}
