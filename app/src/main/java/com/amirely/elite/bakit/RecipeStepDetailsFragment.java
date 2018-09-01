package com.amirely.elite.bakit;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amirely.elite.bakit.models.Recipe;
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

    Recipe recipe;

    int position;

    boolean isTablet;

    boolean isPortrait;

    ImageButton backImgButton;
    ImageButton forwardImgButton;

    public RecipeStepDetailsFragment() {

    }


    public static RecipeStepDetailsFragment newInstance(Recipe recipe, int position, boolean isTablet) {
        RecipeStepDetailsFragment fragment = new RecipeStepDetailsFragment();
        if(recipe != null) {
            fragment.recipeStep = recipe.getSteps().get(position);
            fragment.stepList = recipe.getSteps();
            fragment.position = position;
            fragment.recipe = recipe;
            fragment.isTablet = isTablet;
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isPortrait = Objects.requireNonNull(getActivity()).getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;

        if(savedInstanceState != null) {
            this.isTablet = savedInstanceState.getBoolean("isTablet");
            this.recipeStep = savedInstanceState.getParcelable("recipeStep");
//            this.stepList = savedInstanceState.getParcelableArrayList("stepList");
            this.recipe = savedInstanceState.getParcelable("currentRecipe");
            assert recipe != null;
            this.stepList = recipe.getSteps();

        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipe_step_details, container, false);

        simpleExoPlayerView = view.findViewById(R.id.details_exoplayer);
        ImageView noVideoImageView = view.findViewById(R.id.no_video_image);


        backImgButton = view.findViewById(R.id.back_button_details);
        backImgButton.setOnClickListener(view1 -> goStepBack());

        forwardImgButton = view.findViewById(R.id.forward_button_details);
        forwardImgButton.setOnClickListener(view1 -> getNextStep());

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


        if (isTablet) {
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) simpleExoPlayerView.getLayoutParams();
            params.width = MATCH_PARENT;
            params.height = 800;
            simpleExoPlayerView.setLayoutParams(params);

            if (Objects.requireNonNull(getActivity()).getResources().getConfiguration().orientation ==
                    Configuration.ORIENTATION_LANDSCAPE) {

                backImgButton.setVisibility(View.GONE);
                forwardImgButton.setVisibility(View.GONE);
            }
        }


        return view;
    }

    private void getNextStep() {
        if(position < stepList.size()-1) {
            Navigator navigator = new Navigator(this.getFragmentManager());
            if(isTablet && Objects.requireNonNull(getActivity()).getResources().getConfiguration().orientation ==
                    Configuration.ORIENTATION_LANDSCAPE) {
                navigator.addSecondFragment(RecipeStepDetailsFragment.newInstance(this.recipe, position + 1, isTablet));
            }
            else {
                navigator.navigateTo(RecipeStepDetailsFragment.newInstance(this.recipe, position + 1, isTablet));
            }
//            navigator.navigateTo(RecipeStepDetailsFragment.newInstance(stepList, position + 1));
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
        simpleExoPlayerView = null;

        exoPlayer = null;

        recipeStep = null;

        stepList = null;

        recipe = null;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("stepsList", stepList);
        outState.putParcelable("currentRecipe", recipe);
        outState.putParcelable("recipeStep", recipeStep);
        outState.putBoolean("isTablet", isTablet);
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

            navigator.navigateTo(RecipeStepDetailsFragment.newInstance(this.recipe, position - 1, isTablet));

//            navigator.navigateTo(RecipeStepDetailsFragment.newInstance(stepList, position - 1));
        }
        else {
            Toast.makeText(getActivity(), "No more steps available", Toast.LENGTH_SHORT).show();
        }
    }




//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//
//
//
//        // Checks the orientation of the screen
//        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            if(isTablet) {
//                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) simpleExoPlayerView.getLayoutParams();
//                params.width = MATCH_PARENT;
//                params.height = 600;
//                simpleExoPlayerView.setLayoutParams(params);
//
//                if (Objects.requireNonNull(getActivity()).getResources().getConfiguration().orientation ==
//                        Configuration.ORIENTATION_LANDSCAPE){
//
//                    backImgButton.setVisibility(View.GONE);
//                    forwardImgButton.setVisibility(View.GONE);
//                }
//            }
//            else {
//                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) simpleExoPlayerView.getLayoutParams();
//
//                params.width = MATCH_PARENT;
//                params.height = MATCH_PARENT;
//                simpleExoPlayerView.setLayoutParams(params);
//
//                Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).hide();
//
//            }
//
//
//
//        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
//
//
//
//        }
//    }



}
