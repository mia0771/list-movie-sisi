package Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sisy.movielist.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import Model.Result;
import Model.Variables;

public class MovieAdapter   extends ArrayAdapter {
    private Context context;
    private List<Result> items;
    private LayoutInflater mInflater;

    public MovieAdapter(Context context, List<Result> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.items = objects;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Object getItem(int position) {
        Log.i("Posicion", position + " - ");return this.items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private static class ViewHolder {
        public final ImageView _image_view_poster;
        public final TextView _image_view_popularity;
        public final TextView _image_view_votes;
        public final TextView _image_view_title;
        public final RelativeLayout rootView;

        private ViewHolder(ImageView _image_view_poster, TextView _image_view_popularity, TextView _image_view_votes, TextView _image_view_title, RelativeLayout rootView) {
            this._image_view_poster = _image_view_poster;
            this._image_view_popularity = _image_view_popularity;
            this._image_view_votes = _image_view_votes;
            this._image_view_title = _image_view_title;
            this.rootView = rootView;
        }

        public static ViewHolder create(RelativeLayout rootView) {
            ImageView _image_view_poster = (ImageView) rootView.findViewById(R.id._imageViewPoster);
            TextView _image_view_popularity = (TextView) rootView.findViewById(R.id._textViewPopularity);
            TextView _image_view_votes = (TextView) rootView.findViewById(R.id._textViewVotes);
            TextView _image_view_title = (TextView) rootView.findViewById(R.id._textViewTitle);
            return new ViewHolder(_image_view_poster, _image_view_popularity, _image_view_votes, _image_view_title, rootView);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout._layout_movie_adapter, parent, false);
            vh = ViewHolder.create((RelativeLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        Result item = items.get(position);

        vh._image_view_popularity.setText(item.getPopularity().toString());
        vh._image_view_votes.setText(item.getVoteAverage().toString());
        vh._image_view_title.setText(item.getOriginalTitle().toString());
        Picasso.with(context)
                .load(Variables.getUrlImage()+item.getPosterPath())
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round)
                .into(vh._image_view_poster);
        return vh.rootView;
    }
}
