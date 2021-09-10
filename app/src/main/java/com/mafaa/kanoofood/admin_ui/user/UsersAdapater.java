package com.mafaa.kanoofood.admin_ui.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.mafaa.kanoofood.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class UsersAdapater extends RecyclerView.Adapter<UsersAdapater.UsersHolder> {
    Context context;
    List<Users> usersList;

    public UsersAdapater(Context context, List<Users> usersList) {
        this.context = context;
        this.usersList = usersList;
    }

    @NonNull
    @NotNull
    @Override
    public UsersHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View userLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_users_list, parent, false);
        return new UsersHolder(userLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull UsersHolder holder, int position) {
        Users users = usersList.get(position);
        holder.Name.setText(users.getName());
        holder.Level.setText(users.getLevel());
        holder.Gender.setText(users.getGender());
        holder.Tgl.setText(users.getTgl());
        holder.No_hp.setText(users.getNo_hp());
        holder.Email.setText(users.getEmail());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextInputEditText teName, teNumber, teEmail;
                TextView tvTgl_Lahir;
                RadioGroup rgGender;
                RadioButton rbGender;
                View editLayout = LayoutInflater.from(context).inflate(R.layout.admin_edit_user, null);
                teName = editLayout.findViewById(R.id.nama);
                teNumber = editLayout.findViewById(R.id.nomor);
                teEmail = editLayout.findViewById(R.id.email);
                rgGender = editLayout.findViewById(R.id.rg_gender);

                int selectedId = rgGender.getCheckedRadioButtonId();
                rbGender = editLayout.findViewById(selectedId);

                teName.setText(users.getName());
                teNumber.setText(users.getNo_hp());
                teEmail.setText(users.getEmail());
                rbGender.setText(users.getGender());

                String name = teName.getText().toString();
                String no_hp = teNumber.getText().toString();
                String gender = rbGender.getText().toString();
                String email = teEmail.getText().toString();
                String oldemail = users.getEmail();
            }
        });
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class UsersHolder extends RecyclerView.ViewHolder {
        TextView Name, Level, Gender, Tgl, No_hp, Email;
        Button btnEdit;

        public UsersHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.tv_Nama);
            Level = itemView.findViewById(R.id.tv_Level);
            Gender = itemView.findViewById(R.id.tv_Gender);
            Tgl = itemView.findViewById(R.id.tv_TglLahir);
            No_hp = itemView.findViewById(R.id.tv_NoHp);
            Email = itemView.findViewById(R.id.tv_Email);
            btnEdit = itemView.findViewById(R.id.btn_EditUser);
        }
    }
}
