package com.dusol.thelearnerscommunity.NotesStoreManage;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dusol.thelearnerscommunity.ProductDetailsDialogFragment;
import com.dusol.thelearnerscommunity.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NotesManagerAdapter extends RecyclerView.Adapter<NotesManagerAdapter.MyViewHolder> {

    private final Activity context; // Ensure this is passed correctly
    private final String[] notesTitles;
    private final String[] notesImageView;
    private final String[] openNotesPageLinks;
    private final String[] productAllDetails;
    private final FragmentManager fragmentManager;

    // Updated constructor to accept context properly
    public NotesManagerAdapter(Activity context, List<String> notesTitles, List<String> notesImageView, List<String> openNotesPageLinks, List<String> productDetails, FragmentManager fragmentManager) {
        this.context = context;
        this.notesTitles = notesTitles.toArray(new String[0]);
        this.notesImageView = notesImageView.toArray(new String[0]);
        this.openNotesPageLinks = openNotesPageLinks.toArray(new String[0]);
        this.productAllDetails = productDetails.toArray(new String[0]);
        this.fragmentManager = fragmentManager;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        Button openNotesLink;
        ImageView NotesImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.notesSubjectName);
            openNotesLink = itemView.findViewById(R.id.openNotesLink);
            NotesImage = itemView.findViewById(R.id.notesImageView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // Implement click handling here
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Use the context passed through the constructor
        View itemView = LayoutInflater.from(context).inflate(R.layout.notes_product_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(notesTitles[position]);

        Picasso.get()
                .load(notesImageView[position])
                .error(R.drawable.nopictures)
                .placeholder(R.drawable.loading)
                .into(holder.NotesImage);

        holder.openNotesLink.setOnClickListener(v -> {

            String productName = notesTitles[position];

            showProductDetailsDialog(productName,productAllDetails[position],0,openNotesPageLinks[position]);
//            Uri notesLink = Uri.parse(openNotesPageLinks[position]);
//            Intent intent = new Intent(Intent.ACTION_VIEW, notesLink);
//            context.startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
        return notesTitles.length;
    }

    private void showProductDetailsDialog(String productName, String productDescription, double productPrice, String productLink) {
        DialogFragment dialogFragment = ProductDetailsDialogFragment.newInstance( productName, productDescription, productPrice, productLink);

        dialogFragment.show(fragmentManager, "ProductDetailsDialogFragment");
    }
}
