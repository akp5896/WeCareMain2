package com.example.wecaremain;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VentList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VentList extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public VentList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VentList.
     */
    // TODO: Rename and change types and number of parameters
    public static VentList newInstance(String param1, String param2) {
        VentList fragment = new VentList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    Button btnCompose;
    ArrayList<ParseObject> stories;
    RecyclerView rvStories;
    PostsAdapter adapter ;
    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvStories = view.findViewById(R.id.rvPosts);
        stories = new ArrayList<>();
        adapter = new PostsAdapter(getContext(), stories);
        rvStories.setLayoutManager(new LinearLayoutManager(getContext()));
        rvStories.setAdapter(adapter);
        btnCompose = view.findViewById(R.id.btnCOmpose);
        btnCompose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ventCompose.class);
                startActivity(i);
            }
        });
        populateQueryPosts();
    }

    protected void populateQueryPosts()
    {
        ParseQuery<VentStory> query = ParseQuery.getQuery(VentStory.class);
        query.setLimit(5);
        query.addDescendingOrder(VentStory.KEY_CREATED_AT);

        query.findInBackground(new FindCallback<VentStory>() {
            @Override
            public void done(List<VentStory> p, ParseException e) {
                if(e != null)
                {
                    return;
                }
                adapter.clear();
                adapter.addAll(p);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        populateQueryPosts();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vent_list, container, false);
    }
}