package com.dusol.thelearnerscommunity.pdf_adapter_manage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dusol.thelearnerscommunity.R;

import java.util.List;

public class NotesAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final List<String> notesNameArray;
    private final List<String> notesLinksArray;

    public NotesAdapter(Context context, List<String> notesNameArray, List<String> notesLinksArray) {
        super(context, R.layout.pdf_item_view, notesNameArray);
        this.context = context;
        this.notesNameArray = notesNameArray;
        this.notesLinksArray = notesLinksArray;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Inflate the custom layout if convertView is null
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.pdf_item_view, parent, false);
        }

        // Get the views from the custom layout
        TextView notesName = convertView.findViewById(R.id.pdfTitle);

        // Set data to the views
        String noteName = notesNameArray.get(position);
        String noteLink = notesLinksArray.get(position);

        notesName.setText(noteName);


        return convertView;
    }
}