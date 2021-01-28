package com.example.retrodelsingleactivapplication.ui.upload;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.retrodelsingleactivapplication.R;
import com.example.retrodelsingleactivapplication.model.Post;
import com.example.retrodelsingleactivapplication.remote.RetrofitFactory;
import com.example.retrodelsingleactivapplication.ui.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UploadFragment extends Fragment {

    private static final String ARG_TITLE = "title";
    private static final String ARG_CONTENT = "content";
    private static final String ARG_USER = "user";
    private static final String ARG_GROUP = "group";
    private static final String ARG_ID = "id";


    private static final String UP_LOAD = "upload";


    private String mTitle;
    private String mContent;
    private Integer mUser;
    private Integer mGroup;
    private Integer mId;

    private boolean isUpdating;

    private EditText editTitle, editDesc, editUser, editGroup;
    private Button mBtnUpload;

    public UploadFragment() {
        // Required empty public constructor
    }


    public static UploadFragment newInstance(Post post) {
        UploadFragment fragment = new UploadFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, post.getTitle());
        args.putString(ARG_CONTENT, post.getContent());
        args.putInt(ARG_USER, post.getUser());
        args.putInt(ARG_GROUP, post.getGroup());
        args.putInt(ARG_ID, post.getId());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            isUpdating = true;
            mTitle = getArguments().getString(ARG_TITLE);
            mContent = getArguments().getString(ARG_CONTENT);
            mUser = getArguments().getInt(ARG_USER);
            mGroup = getArguments().getInt(ARG_GROUP);
            mId = getArguments().getInt(ARG_ID);
            ((MainActivity) requireActivity()).navView().setVisibility(View.GONE);
        }

    }

    private void setOnEditFields() {
        editTitle.setText(mTitle);
        editDesc.setText(mContent);
        editUser.setText(mUser.toString());
        editGroup.setText(mGroup.toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_upload, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        if (isUpdating) setOnEditFields();
        mBtnUpload.setOnClickListener(v -> uploadClick());
        Log.e(UP_LOAD, "onViewCreated: " + mTitle + " " + mContent);

    }

    private void uploadClick() {
        String title = editTitle.getText().toString();
        String desc = editDesc.getText().toString();
        String user = editUser.getText().toString();
        String group = editGroup.getText().toString();

        Post post = new Post(title
                , desc
                , Integer.parseInt(user)
                , Integer.parseInt(group));
        if (isUpdating) upDatePost(post);
        else uploadPost(post);
        Navigation.findNavController(getView()).navigateUp();

    }

    private void upDatePost(Post post) {
        RetrofitFactory
                .getInstance()
                .putPost(mId, post)
                .enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        if (response.isSuccessful()) {
                            Log.e(UP_LOAD, "onResponse: success");
                            getParentFragmentManager().popBackStackImmediate();
                        }
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        Log.e(UP_LOAD, "onFailure: " + t.getLocalizedMessage());
                    }
                });

    }

    private void uploadPost(Post post) {
        RetrofitFactory
                .getInstance()
                .postElement(post)
                .enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        if (response.isSuccessful()) {
                            Log.e(UP_LOAD, "onResponse: " + response.body().getTitle());
                        }
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        Log.e(UP_LOAD, "onFailure: " + t.getLocalizedMessage());
                    }
                });

    }

    private void initView() {
        editTitle = getView().findViewById(R.id.edit_title_upload);
        editDesc = getView().findViewById(R.id.edit_desc_upload);
        editUser = getView().findViewById(R.id.edit_user_upload);
        editGroup = getView().findViewById(R.id.edit_group_upload);
        mBtnUpload = getView().findViewById(R.id.upload_btn);
    }
}