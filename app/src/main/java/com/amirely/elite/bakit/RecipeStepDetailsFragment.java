package com.amirely.elite.bakit;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.amirely.elite.bakit.models.RecipeStep;
import com.amirely.elite.bakit.utils.Navigator;
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
import java.util.ArrayList;
import java.util.Objects;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class RecipeStepDetailsFragment extends Fragment {

    SimpleExoPlayerView simpleExoPlayerView;

    ExoPlayer exoPlayer;

    RecipeStep recipeStep;

    ArrayList<RecipeStep> stepList;

    int position;

    public RecipeStepDetailsFragment() {

    }


//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        Log.d("CONFIGURATION CHANGE", "CONFIGURATION HAS CHANGED")
//
//        // Checking the orientation of the screen
//        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            //First Hide other objects (listview or recyclerview), better hide them using Gone.
//            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) simpleExoPlayerView.getLayoutParams();
//            params.width= MATCH_PARENT;
//            params.height= MATCH_PARENT;
//            simpleExoPlayerView.setLayoutParams(params);
//        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
//            //unhide your objects here.
//            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) simpleExoPlayerView.getLayoutParams();
//            params.width= MATCH_PARENT;
//            params.height=600;
//            simpleExoPlayerView.setLayoutParams(params);
//        }
//    }


    public static RecipeStepDetailsFragment newInstance(ArrayList<RecipeStep> steps, int position) {
        RecipeStepDetailsFragment fragment = new RecipeStepDetailsFragment();
        fragment.recipeStep = steps.get(position);
        fragment.stepList = steps;
        fragment.position = position;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null) {
            recipeStep = savedInstanceState.getParcelable("recipeStep");
            stepList = savedInstanceState.getParcelableArrayList("stepList");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipe_step_details, container, false);

        simpleExoPlayerView = view.findViewById(R.id.details_exoplayer);
        ImageView noVideoImageView = view.findViewById(R.id.no_video_image);


        if (Objects.requireNonNull(getActivity()).getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE) {
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) simpleExoPlayerView.getLayoutParams();
            params.width = MATCH_PARENT;
            params.height = MATCH_PARENT;
            simpleExoPlayerView.setLayoutParams(params);

            Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).hide();
        }

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


        ImageButton backImgButton = view.findViewById(R.id.back_button_details);
        backImgButton.setOnClickListener(view1 -> goStepBack());

        ImageButton forwardImgButton = view.findViewById(R.id.forward_button_details);
        forwardImgButton.setOnClickListener(view1 -> getNextStep());

        return view;
    }

    private void getNextStep() {
        if(position < stepList.size()-1) {
            Navigator navigator = new Navigator(this.getFragmentManager());
            navigator.navigateTo(RecipeStepDetailsFragment.newInstance(stepList, position + 1));
        }
        else {
            Toast.makeText(getActivity(), "No more steps available", Toast.LENGTH_SHORT).show();
        }
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

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("recipeStep", recipeStep);
        outState.putParcelableArrayList("stepList", stepList);
    }


    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        Objects.requireNonNull(getActivity()).setTitle("Step " + recipeStep.getId());
    }

    public void goStepBack() {
        if(position > 1) {
            Navigator navigator = new Navigator(this.getFragmentManager());
            navigator.navigateTo(RecipeStepDetailsFragment.newInstance(stepList, position - 1));
        }
        else {
            Toast.makeText(getActivity(), "No more steps available", Toast.LENGTH_SHORT).show();
        }
    }



}
