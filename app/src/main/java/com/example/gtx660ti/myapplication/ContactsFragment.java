package com.example.gtx660ti.myapplication;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by GTX660TI on 28.11.2016.
 */

public class ContactsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ContactsAdapter adapter;
    private ContactsDBHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View content = inflater.inflate(R.layout.contacts_fragment_layout, container, false);
        View addContactView = content.findViewById(R.id.add_contact);
        addContactView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddContactDialog();
            }
        });
        dbHelper = new ContactsDBHelper(getActivity());
        recyclerView = (RecyclerView) content.findViewById(R.id.contacts_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ContactsAdapter(getActivity());
        adapter.setOnItemClickListener(new ContactsAdapter.OnItemClickListener() {
            @Override
            public void onClick(Contact contact) {
                showEditContactDialog(contact);
            }
        });

        adapter.setOnItemLongClickListener(new ContactsAdapter.OnItemLongClickListener() {
            @Override
            public boolean onLongClick(Contact contact) {
                showRemoveContactDialog(contact);
                return true;
            }
        });
        recyclerView.setAdapter(adapter);
        adapter.setContacts(dbHelper.getAllContacts());
        return content;
    }

    private void showAddContactDialog() {
        View dialogContent = getActivity().getLayoutInflater().inflate(R.layout.add_edit_contac_popup, null);
        final EditText editName = (EditText) dialogContent.findViewById(R.id.edit_contact_name);
        final EditText editNumber = (EditText) dialogContent.findViewById(R.id.edit_contact_number);
        new AlertDialog.Builder(getActivity()).setMessage(getString(R.string.add_contact))
                .setCancelable(true)
                .setView(dialogContent)
                .setPositiveButton(getString(R.string.add), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Contact contact = new Contact(editName.getText().toString(),
                                editNumber.getText().toString());

                        dbHelper.addContact(contact);
                        updateContacts();
                    }
                })
                .setNegativeButton(getString(R.string.cancel), null).show();
    }

    private void updateContacts() {
        if (adapter == null)
            adapter = new ContactsAdapter(getActivity());
        adapter.clear();
        adapter.setContacts(dbHelper.getAllContacts());
    }

    private void showRemoveContactDialog(final Contact contact) {
        new AlertDialog.Builder(getActivity()).setMessage(getString(R.string.remove_contact_confirm))
                .setCancelable(true)
                .setPositiveButton(getString(R.string.remove), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dbHelper.deleteContact(contact);
                        adapter.removeContact(contact);
                    }
                })
                .setNegativeButton(getString(R.string.cancel), null).show();
        ;
    }

    private void showEditContactDialog(final Contact contact) {
        View dialogContent = getActivity().getLayoutInflater().inflate(R.layout.add_edit_contac_popup, null);
        final EditText editName = (EditText) dialogContent.findViewById(R.id.edit_contact_name);
        final EditText editNumber = (EditText) dialogContent.findViewById(R.id.edit_contact_number);
        editName.setText(contact.getName());
        editNumber.setText(contact.getNumber());
        new AlertDialog.Builder(getActivity()).setMessage(getString(R.string.edit_contact))
                .setCancelable(true)
                .setView(dialogContent)
                .setPositiveButton(getString(R.string.edit), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        contact.setName(editName.getText().toString());
                        contact.setNumber(editNumber.getText().toString());
                        dbHelper.updateContact(contact);
                        adapter.updateContact(contact);
                    }
                })
                .setNegativeButton(getString(R.string.cancel), null).show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
