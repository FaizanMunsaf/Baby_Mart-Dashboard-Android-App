package com.example.babymart_dashboard;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class dashboard extends AppCompatActivity {
ImageView addproduct,logout,edit;
TextView name,email;
RecyclerView recyclerlist;
RecyclerAdapter adapter;
Context context;
ArrayList<UserModel> mylist;
FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        addproduct = findViewById(R.id.addProductBtn);
        logout = findViewById(R.id.logoutBtn);
        edit = findViewById(R.id.editProfileBtn);
        name = findViewById(R.id.nameTV);
        email = findViewById(R.id.emailTv);


        recyclerlist = findViewById(R.id.recyclerlist);

        mylist = new ArrayList<>();

        //this is a first method to use constructor usermodel class
        db = FirebaseFirestore.getInstance();



        db.collection("Products").orderBy("Name", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null){
                    Log.e("Product Error", error.getMessage() );
                    return;
                }
                for (DocumentChange dc : value.getDocumentChanges()){
                    if(dc.getType() == DocumentChange.Type.ADDED) {
                        QueryDocumentSnapshot snapshot=dc.getDocument();
                        ArrayList<String> image= (ArrayList<String>) snapshot.get("Images");
                        mylist.add(new UserModel(snapshot.get("Name").toString(),snapshot.get("Rating").toString(),snapshot.getId(),image.get(0)));
                    }
                }
            }
        });


        recyclerlist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerlist.setHasFixedSize(true);
        adapter = new RecyclerAdapter(dashboard.this,mylist);
        recyclerlist.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        addproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(dashboard.this,AddproductActivity2.class);
                startActivity(i);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionManagement sessionManagement = new SessionManagement(dashboard.this);
                sessionManagement.removeSession();
                movetologin();
            }
        });
    }
    private void movetologin(){
        Intent i = new Intent(dashboard.this,MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}