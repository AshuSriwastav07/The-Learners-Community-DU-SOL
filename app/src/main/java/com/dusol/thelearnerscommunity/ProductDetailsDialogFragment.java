/**
 * App developed by:
 * Ashu Sriwastav
 *
 * All rights reserved. This application is the property of Ashu Sriwastav.
 * Unauthorized reproduction, distribution, or modification of this application
 * without the explicit permission of Ashu Sriwastav is prohibited.
 */
package com.dusol.thelearnerscommunity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;


public class ProductDetailsDialogFragment extends DialogFragment {

    private String productLink;

    public static ProductDetailsDialogFragment newInstance(String name, String description, double price, String link) {
        ProductDetailsDialogFragment fragment = new ProductDetailsDialogFragment();
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putString("description", description);
        args.putDouble("price", price);
        args.putString("link", link);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context=this.getContext();

        assert context != null;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_product_details, null);

        if (getArguments() != null) {
            String productName = getArguments().getString("name");
            String productDescription = getArguments().getString("description");
            double productPrice = getArguments().getDouble("price");
            productLink = getArguments().getString("link");

            // Initialize and populate product details views
            TextView productNameView = view.findViewById(R.id.productName);
            TextView productDescriptionView = view.findViewById(R.id.productDescription);
            TextView productPriceView = view.findViewById(R.id.productPrice);

            productNameView.setText(productName);
            productDescriptionView.setText(Html.fromHtml(productDescription));
            productPriceView.setText(String.valueOf(productPrice));
        }

        Button buyButton = view.findViewById(R.id.buyButton);
        buyButton.setOnClickListener(v -> {
            // Handle the "Buy" button click
            // Open the product link in an external browser
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(productLink));
            startActivity(intent);
        });
        Button buyButton2 = view.findViewById(R.id.buyButton2);
        buyButton2.setOnClickListener(v -> {
            // Handle the "Buy" button click
            // Open the product link in an external browser
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(productLink));
            startActivity(intent);
        });

        builder.setView(view)
                .setPositiveButton("Close", (dialog, id) -> {
                    // Close the dialog
                    dialog.dismiss();
                });

        return builder.create();
    }
}
