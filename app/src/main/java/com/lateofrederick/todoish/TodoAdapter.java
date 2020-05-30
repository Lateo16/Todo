package com.lateofrederick.todoish;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.MyViewHolder> {
    private ArrayList<Todos> mTodos;


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public final TextView mfirstname;
        public final TextView mlastname;
        public final TextView mtelephone;
        public final View mView;

        public MyViewHolder(View v) {
            super(v);
            this.mView = v;
            mfirstname = mView.findViewById(R.id.firstname_view);
            mlastname = mView.findViewById(R.id.lastname_view);
            mtelephone = mView.findViewById(R.id.telephone_view);
        }


    }

    public TodoAdapter(ArrayList<Todos> myDataset) {
        mTodos = myDataset;
    }

    @NonNull
    @Override
    public TodoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_list_layout,parent,false);

        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder( @NonNull MyViewHolder holder, int position) {
        Todos todo = mTodos.get(position);
        holder.mfirstname.setText(todo.getFirstname());
        holder.mlastname.setText(todo.getLastname());
        holder.mtelephone.setText(todo.getTelephone());
    }

    @Override
    public int getItemCount() {
        return mTodos.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}
