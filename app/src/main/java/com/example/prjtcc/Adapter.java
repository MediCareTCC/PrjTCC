package com.example.prjtcc;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.EditViewHolder> {

    private Context mContext;
    private Cursor mCursor;

    public Adapter(Context context, Cursor cursor){
        mContext = context;
        mCursor = cursor;
    }

    public class EditViewHolder extends RecyclerView.ViewHolder{

        public EditText nameText;
        public EditText cpfText;
        public EditText emailText;
        public EditText sizeText;
        public EditText phoneText;
        public EditViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.txtUser);
            cpfText = itemView.findViewById(R.id.txtCPF);
            emailText = itemView.findViewById(R.id.txtEmail);
            sizeText = itemView.findViewById(R.id.txtSize);
            phoneText = itemView.findViewById(R.id.txtPhone);
        }
    }
    @NonNull
    @Override
    public EditViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.alterar, parent, false);
        return new EditViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EditViewHolder holder, int position) {
        if(!mCursor.moveToPosition(position)){
            return;
        }

        String name = mCursor.getString(mCursor.getColumnIndex(Contract.EditEntry.COLUMN_NAME));
        String cpf = mCursor.getString(mCursor.getColumnIndex(Contract.EditEntry.COLUMN_CPF));
        String email = mCursor.getString(mCursor.getColumnIndex(Contract.EditEntry.COLUMN_EMAIL));
        String size = mCursor.getString(mCursor.getColumnIndex(Contract.EditEntry.COLUMN_SIZE));
        String phone = mCursor.getString(mCursor.getColumnIndex(Contract.EditEntry.COLUMN_PHONE));

        holder.nameText.setText(name);
        holder.cpfText.setText(cpf);
        holder.emailText.setText(email);
        holder.sizeText.setText(size);
        holder.phoneText.setText(phone);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }
    public void swapCursor(Cursor newCursor){
        if(mCursor != null){
            mCursor.close();
        }
        mCursor = newCursor;

        if(newCursor != null){
            notifyDataSetChanged();
        }
    }

}
