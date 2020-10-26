package com.example.crud_sqlite;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ListViewAdapter extends BaseAdapter{
    private List<dataMahasiswa> listMahasiswa;
    private String[] nama;
    private String[] fakultas;
    private String[] jurusan;
    private String[] semester;
    private Context context;
    private SQLiteHelper helper;

    private TextView tv_nama,
            tv_fakultas,
            tv_jurusan,
            tv_semester;
    private LinearLayout linear;
    private ImageView hapus;

    public ListViewAdapter(List<dataMahasiswa> listMahasiswa, Context context) {
        this.listMahasiswa = listMahasiswa;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listMahasiswa.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_mahasiswa, null);
        tv_nama = v.findViewById(R.id.tv_nama);
        tv_fakultas = v.findViewById(R.id.tv_fakultas);
        tv_jurusan = v.findViewById(R.id.tv_jurusan);
        tv_semester = v.findViewById(R.id.tv_semester);
        linear = v.findViewById(R.id.linear);
        hapus = v.findViewById(R.id.hapus);

        helper = new SQLiteHelper(context);

        tv_nama.setText("Nama : "+listMahasiswa.get(position).getNAMA());
        tv_fakultas.setText("Fakultas : "+listMahasiswa.get(position).getFAKULTAS());
        tv_jurusan.setText("Jurusan : "+listMahasiswa.get(position).getJURUSAN());
        tv_semester.setText("Semester : "+listMahasiswa.get(position).getSEMESTER());

        linear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(context,inputActivity.class);
                intent.putExtra("ID",listMahasiswa.get(position).getID());
                intent.putExtra("NAMA",listMahasiswa.get(position).getNAMA());
                intent.putExtra("FAKULTAS",listMahasiswa.get(position).getFAKULTAS());
                intent.putExtra("JURUSAN",listMahasiswa.get(position).getJURUSAN());
                intent.putExtra("SEMESTER",listMahasiswa.get(position).getSEMESTER());
                intent.putExtra("TANDA", "Ubah");

                context.startActivities(new Intent[]{intent});

                return true;
            }
        });

        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);


                alertDialog.setMessage("Apakah yakin ingin menghapus ini?")
                        .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Integer isDelete = helper.deleteData(listMahasiswa.get(position).getID());
                                if(isDelete> 0){
                                    Toast.makeText(context,"Data Berhasil dihapus",Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context,"Data Gagal dihapus",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });
        return v;
    }
}
