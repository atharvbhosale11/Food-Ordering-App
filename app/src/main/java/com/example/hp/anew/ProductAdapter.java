package com.example.hp.anew;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nex3z.notificationbadge.NotificationBadge;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    private Context mCtx;
    private List<Items> productList;
    private DatabaseReference db;

    public ProductAdapter(Context mCtx, List<Items> productList) {
        this.mCtx = mCtx;
        this.productList = productList;

    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_bev,parent,false);

        return new ProductViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {

        Items pro=productList.get(position);
        holder.txtname.setText(pro.getName());
        holder.txtprice.setText(String.valueOf(pro.getPrice()));
        Picasso.with(mCtx)  .load(pro.getImg()).into(holder.imgicon);
        //holder.imgicon.setImageDrawable(mCtx.getResources().getDrawable(pro.getImage()));
        holder.inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String present_value_string = holder.quan.getText().toString();
                String name=holder.txtname.getText().toString();
                String price=holder.txtprice.getText().toString();
                int n=Integer.parseInt(price);
                int img=holder.imgicon.getId();
                int k = Integer.parseInt(present_value_string);
                k++;

                holder.quan.setText(String.valueOf(k));
                caart c=new caart(name,k,n);
                db= FirebaseDatabase.getInstance().getReference();
                FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                if(user != null)
                {

                    db.child(user.getUid()).child("orders").child(c.name).setValue(c);

                }


            }
        });
        holder.dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m=holder.quan.getText().toString();
                String name=holder.txtname.getText().toString();
                String price=holder.txtprice.getText().toString();
                int n=Integer.parseInt(price);
                int img=holder.imgicon.getId();
                int l=Integer.parseInt(m);
                l--;

                caart c=new caart(name,l,n);
                db= FirebaseDatabase.getInstance().getReference();
                FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                if(l<=0)
                {
                    holder.quan.setText("0");
                    db.child(user.getUid()).child("orders").child(c.name).setValue(null);
                }
                else {
                    holder.quan.setText(String.valueOf(l));
                    db.child(user.getUid()).child("orders").child(c.name).setValue(c);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imgicon;
        TextView txtname,txtprice,quan;
        ImageButton dec,inc;

        public ProductViewHolder(View itemView) {
            super(itemView);
            imgicon=(ImageView) itemView.findViewById(R.id.imgicon);
            txtname=(TextView) itemView.findViewById(R.id.txtname);
            txtprice=(TextView) itemView.findViewById(R.id.txtprice);
            quan=(TextView) itemView.findViewById(R.id.quan);
            dec=(ImageButton) itemView.findViewById(R.id.dec);
            inc=(ImageButton) itemView.findViewById(R.id.inc);
        }
    }
}