package com.example.retrodelsingleactivapplication.ui.home;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrodelsingleactivapplication.R;
import com.example.retrodelsingleactivapplication.interfaces.OnClickListener;
import com.example.retrodelsingleactivapplication.interfaces.OnLongClick;
import com.example.retrodelsingleactivapplication.model.Post;
import com.example.retrodelsingleactivapplication.remote.RetrofitFactory;
import com.example.retrodelsingleactivapplication.ui.MainActivity;
import com.example.retrodelsingleactivapplication.ui.upload.UploadFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements OnLongClick, OnClickListener {

    private static final String TAG = "ui.home";
    private RecyclerView mRecycler;
    private FloatingActionButton mBtnUpload;
    private AdapterHome adapter;
    private List<Post> list;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        ((MainActivity) requireActivity()).navView().setVisibility(View.VISIBLE);
        loadData();

    }

    /*loading the data */
    private void loadData() {
        RetrofitFactory
                .getInstance()
                .getPosts()
                .enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            list = response.body();
                            adapter.addList(list);
                            logMessage("loadResponse", "success");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Post>> call, Throwable t) {
                        logMessage("onFailureLoad", t.getLocalizedMessage());
                    }
                });
    }

    public void logMessage(String methodCall, String message) {
        Log.e(TAG, methodCall + " was called " + message);

    }

    private void initView() {
        list = new ArrayList<>();
        mRecycler = getView().findViewById(R.id.recycler_home);
        adapter = new AdapterHome();
        mRecycler.setAdapter(adapter);
        mRecycler.addItemDecoration(
                new DividerItemDecoration(getContext()
                        , DividerItemDecoration.VERTICAL));
        adapter.setLongListener(this);
        adapter.setOnClickListener(this);
        mBtnUpload = getView().findViewById(R.id.btn_fab);
        mBtnUpload.setOnClickListener(v ->
                fabClick()
        );
    }

    @Override
    public void onLongClick(Post post) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext())
                .setMessage("Are you sure, want to delete ...?")
                .setTitle("Delete element")
                .setIcon(R.drawable.delete_icon)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteElementById(post);
                    }
                });
        builder.create().show();
    }

    private void deleteElementById(Post post) {
        RetrofitFactory
                .getInstance()
                .deletePost(post.getId())
                .enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        if (response.isSuccessful()) {
                            adapter.deleteElement(post);
                        }
                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {
                        Log.e(TAG, "onResponse: " + t.getLocalizedMessage());
                    }
                });
    }

    @Override
    public void onClickListener(Post post) {
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_host_fragment,UploadFragment.newInstance(post))
                .addToBackStack(null)
                .commit();

    }
    private void fabClick() {
        Navigation.findNavController(getView()).navigate(R.id.uploadFragment);
    }
}