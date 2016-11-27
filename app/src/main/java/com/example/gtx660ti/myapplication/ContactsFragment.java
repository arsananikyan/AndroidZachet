package com.example.gtx660ti.myapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by GTX660TI on 28.11.2016.
 */

public class ContactsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ContactsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View content = inflater.inflate(R.layout.contacts_fragment_layout, container, false);

        recyclerView = (RecyclerView) content.findViewById(R.id.contacts_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ContactsAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Arsen", "095459393"));
        contacts.add(new Contact("Mama", "099004645"));
        contacts.add(new Contact("Papa", "091241866"));
        contacts.add(new Contact("Misha", "094120977"));
        contacts.add(new Contact("Tigran Sahakyan", "055112358"));
        contacts.add(new Contact("Gev", "077306070"));
        contacts.add(new Contact("Arsen", "095459393"));
        contacts.add(new Contact("Mama", "099004645"));
        contacts.add(new Contact("Papa", "091241866"));
        contacts.add(new Contact("Misha", "094120977"));
        contacts.add(new Contact("Tigran Sahakyan", "055112358"));
        contacts.add(new Contact("Gev", "077306070"));
        contacts.add(new Contact("Arsen", "095459393"));
        contacts.add(new Contact("Mama", "099004645"));
        contacts.add(new Contact("Papa", "091241866"));
        contacts.add(new Contact("Misha", "094120977"));
        contacts.add(new Contact("Tigran Sahakyan", "055112358"));
        contacts.add(new Contact("Gev", "077306070"));
        adapter.setContacts(contacts);

        return content;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
