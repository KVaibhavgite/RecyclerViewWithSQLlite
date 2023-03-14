package com.example.abc;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Objects;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder>
        implements Filterable {
    private Context context;
    private ArrayList<Product> listProducts;
    private ArrayList<Product> mArrayList;
    private SqliteDatabase mDatabase;
    ProductAdapter(Context context, ArrayList<Product> listProducts) {
        this.context = context;
        this.listProducts = listProducts;
        this.mArrayList = listProducts;
        mDatabase = new SqliteDatabase(context);
    }
    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_layout, parent, false);
        return new ProductViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        final Product products = listProducts.get(position);
        holder.tvproductName.setText(products.getProduct_Name());
        holder.tvproductQty.setText(products.getProduct_Qty());
        holder.tvproductPrice.setText(products.getProduct_Price());
        holder.editProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTaskDialog(products);
            }
        });
        holder.deleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.deleteContact(products.getId());
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    listProducts = mArrayList;
                }
                else {
                    ArrayList<Product> filteredList = new ArrayList<>();
                    for (Product products : mArrayList) {
                        if (products.getProduct_Name().toLowerCase().contains(charString)) {
                            filteredList.add(products);
                        }
                    }
                    listProducts = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = listProducts;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listProducts = (ArrayList<Product>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
    @Override
    public int getItemCount() {
        return listProducts.size();
    }
    //
    private void editTaskDialog(final Product product) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View subView = inflater.inflate(R.layout.add_contacts, null);
        final EditText productName = subView.findViewById(R.id.enterName);
        final EditText productQty = subView.findViewById(R.id.enterQTY);
        //final EditText productPrice = subView.findViewById(R.id.enterPhoneNum);
        if (product != null) {
            productName.setText(product.getProduct_Name());
            productQty.setText(String.valueOf(product.getProduct_Qty()));
           // productPrice.setText(String.valueOf(product.getProduct_Price()));
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit contact");
        builder.setView(subView);
        builder.create();
        builder.setPositiveButton("EDIT CONTACT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String name = productName.getText().toString();
                final String qty = productQty.getText().toString();
                //final String price = productPrice.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(context, "Something went wrong. Check your input values", Toast.LENGTH_LONG).show();
                } else {
                    mDatabase.updateProducts(new
                            Product(Objects.requireNonNull(product).getId(), name, qty));//,price));
                    ((Activity) context).finish();
                    context.startActivity(((Activity)
                            context).getIntent());
                }
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Task cancelled",Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }
}