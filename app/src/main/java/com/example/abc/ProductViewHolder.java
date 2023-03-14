package com.example.abc;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
class ProductViewHolder extends RecyclerView.ViewHolder {
    TextView tvproductName, tvproductQty,tvproductPrice;
    ImageView deleteProduct;
    ImageView editProduct;
    ProductViewHolder(View itemView) {
        super(itemView);
        tvproductName = itemView.findViewById(R.id.productName);
        tvproductQty = itemView.findViewById(R.id.productQty);
        tvproductPrice  = itemView.findViewById(R.id.productPrice);
        deleteProduct = itemView.findViewById(R.id.deleteContact);
        editProduct = itemView.findViewById(R.id.editContact);
    }
}
