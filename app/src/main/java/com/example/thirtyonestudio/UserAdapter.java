package com.example.thirtyonestudio;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.content.DialogInterface;
import android.content.Context;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    List<User> mlist;

    Context context;

    private DatabaseReference database = FirebaseDatabase.getInstance().getReference("Users");


    public UserAdapter(List<User> mlist, Context context){
        this.mlist = mlist;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_user,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position){

        final User item = mlist.get(position);
        holder.TVusername.setText("Username : " + item.getUsername());
        holder.TVemail.setText("Email : " + item.getEmail());
        holder.TVpassword.setText("Password : " + item.getPassword());

        holder.btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteConfirmationDialog(item.getUsername());
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = item.getUsername();
                String email = item.getEmail();
                String password = item.getPassword();

                Intent edit = new Intent(context, EditDataUser.class);
                edit.putExtra("username", username);
                edit.putExtra("email", email);
                edit.putExtra("password", password);
                context.startActivity(edit);

            }
        });
    }

    @Override
    public int getItemCount(){
        return mlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView TVusername, TVemail, TVpassword;
        private Button btnHapus, btnEdit;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            TVusername = itemView.findViewById(R.id.TVusername);
            TVemail = itemView.findViewById(R.id.TVemail);
            TVpassword = itemView.findViewById(R.id.TVpassword);
            btnHapus = itemView.findViewById(R.id.btnHapus);
            btnEdit = itemView.findViewById(R.id.btnEdit);
        }
    }

    private void showDeleteConfirmationDialog(final String username) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Konfirmasi Hapus");
        builder.setMessage("Apakah Anda yakin ingin menghapus pengguna ini?");

        // Tombol Positif (Ya)
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Hapus pengguna dari database
                database.child(username).removeValue();
                Toast.makeText(context, "Data Pengguna Berhasil Dihapus!!!", Toast.LENGTH_SHORT).show();
                dialog.dismiss(); // Tutup dialog setelah penghapusan berhasil
            }
        });

        // Tombol Negatif (Tidak)
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // Tutup dialog jika pengguna memilih tidak
            }
        });

        // Tampilkan AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
