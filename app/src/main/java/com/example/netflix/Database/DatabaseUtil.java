package com.example.netflix.Database;

import androidx.annotation.NonNull;

import com.example.netflix.Database.Models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DatabaseUtil {
    private FirebaseDatabase rootNode = FirebaseDatabase.getInstance("https://mobil-programlama-aeeb5-default-rtdb.firebaseio.com/");

    public void addUser(User user){
        DatabaseReference reference = rootNode.getReference("User");
        Query setRef = reference.orderByChild("mail").equalTo(user.getMail());
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!snapshot.exists()){
                    System.out.println("yok");
                    reference.push().setValue(user);
                }else{
                    System.out.println("var");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("error");
            }
        };
        setRef.addListenerForSingleValueEvent(eventListener);
    }

    public void readUserData(CallWraperUser callWraperUser){
        DatabaseReference reference = rootNode.getReference("User");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            List<User> users = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot user : snapshot.getChildren()){
                    User to_push = new User(user.child("name").getValue(String.class),
                            user.child("username").getValue(String.class),
                            user.child("password").getValue(String.class),
                            user.child("mail").getValue(String.class));
                    users.add(to_push);
                }
                callWraperUser.readUserDataCallback(users);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
