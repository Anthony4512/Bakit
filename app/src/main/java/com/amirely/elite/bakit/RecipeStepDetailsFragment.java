package com.amirely.elite.bakit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amirely.elite.bakit.models.RecipeStep;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.Objects;


public class RecipeStepDetailsFragment extends Fragment {

    SimpleExoPlayerView simpleExoPlayerView;

    ExoPlayer exoPlayer;

    RecipeStep recipeStep;

    public RecipeStepDetailsFragment() {

    }

    public static RecipeStepDetailsFragment newInstance(RecipeStep step) {
        RecipeStepDetailsFragment fragment = new RecipeStepDetailsFragment();

        fragment.recipeStep = step;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipe_step_details, container, false);

        simpleExoPlayerView = view.findViewById(R.id.details_exoplayer);
        ImageView noVideoImageView = view.findViewById(R.id.no_video_image);

        if (recipeStep.getVideoURL() == null || recipeStep.getVideoURL().isEmpty()) {
            simpleExoPlayerView.setVisibility(View.GONE);
            noVideoImageView.setVisibility(View.VISIBLE);

        } else {
            Uri videoUri = Uri.parse(recipeStep.getVideoURL());
            initializeExoplayer(videoUri);
        }


        TextView stepTitle = view.findViewById(R.id.step_details_title);
        TextView stepDetails = view.findViewById(R.id.step_details_description);


        stepTitle.setText(recipeStep.getShortDescription());
        stepDetails.setText(recipeStep.getDescription());

        return view;
    }

    private void initializeExoplayer(Uri videoUri) {
        if (exoPlayer == null) {

            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();

            exoPlayer = ExoPlayerFactory.newSimpleInstance(this.getContext(), trackSelector, loadControl);

            simpleExoPlayerView.setPlayer(exoPlayer);

            String userAgent = Util.getUserAgent(getActivity(), "Bakit");

            MediaSource mediaSource = new ExtractorMediaSource(videoUri, new DefaultDataSourceFactory(Objects.requireNonNull(getActivity()), userAgent), new DefaultExtractorsFactory(), null, null);

            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(true);
        }

    }

    public void releasePlayer() {
        exoPlayer.stop();
        exoPlayer.release();
        exoPlayer = null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (exoPlayer != null) {
            releasePlayer();
        }
    }

}
