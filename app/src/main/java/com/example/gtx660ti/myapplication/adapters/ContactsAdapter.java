package com.example.gtx660ti.myapplication.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gtx660ti.myapplication.R;
import com.example.gtx660ti.myapplication.models.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GTX660TI on 28.11.2016.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactViewHolder> {

    private ArrayList<Contact> contacts;
    private LayoutInflater inflater;
    private OnItemLongClickListener onItemLongClickListener;
    private OnItemClickListener onItemClickListener;

    public ContactsAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = new ArrayList<>(contacts);
        notifyDataSetChanged();
    }

    private int findPosOfContactWithID(int id) {
        if (contacts == null || contacts.size() == 0)
            return -1;
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public void updateContact(Contact contact) {
        int position = findPosOfContactWithID(contact.getId());
        if (position == -1)
            return;
        contacts.set(position, contact);
        notifyItemChanged(position);
    }

    public void removeContact(Contact contact) {
        int position = findPosOfContactWithID(contact.getId());
        if (position == -1)
            return;
        contacts.remove(position);
        if (position != 0) {
            notifyItemChanged(position - 1);
        }
        if (position != contacts.size() - 1) {
            notifyItemChanged(position + 1);
        }
        notifyItemRemoved(position);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactViewHolder(inflater.inflate(R.layout.item_contact, parent, false));
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, final int position) {
        final Contact contact = contacts.get(position);
        holder.name.setText(contact.getName());
        holder.number.setText(contact.getNumber());
        holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemLongClickListener != null) {
                    return onItemLongClickListener.onLongClick(contact);
                }
                return false;
            }
        });

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(contact);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void clear() {
        if (contacts == null || contacts.size() == 0)
            return;
        contacts.clear();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        private View layout;
        private TextView name;
        private TextView number;

        public ContactViewHolder(View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout);
            name = (TextView) itemView.findViewById(R.id.contact_name);
            number = (TextView) itemView.findViewById(R.id.contact_number);
        }
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemLongClickListener {
        boolean onLongClick(Contact contact);
    }

    public interface OnItemClickListener {
        void onClick(Contact contact);
    }
}
