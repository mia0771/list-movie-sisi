package Conection;

import android.content.Context;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import Adapter.MovieAdapter;
import Model.Movies;
import Model.Result;
import Model.Variables;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetRate {
    public static List<Result> results;

    public static void getRated(final Context context, final GridView listView, final LinearLayout linearLayout, final ProgressBar progressBar) {
        Factory.getIntance()
                .getRated(Variables.getPublic_key()).enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                if (response.isSuccessful()) {
                    try {
                        results = response.body().getResults();
                        listView.setAdapter(new MovieAdapter(context, results));
                    } catch (Exception x) {
                        Toast.makeText(context, "Error al asignar adapter " + x.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(context, "Error al obtener los datos",
                            Toast.LENGTH_LONG).show();
                }
                linearLayout.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                Toast.makeText(context, "Error de conexion",
                        Toast.LENGTH_LONG).show();
                linearLayout.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}

