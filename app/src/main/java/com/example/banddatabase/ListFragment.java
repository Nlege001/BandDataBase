package com.example.banddatabase;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Objects;

public class ListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        // Click listener for the RecyclerView
        View.OnClickListener onClickListener = itemView -> {

            // Create fragment arguments containing the selected band ID
            int selectedBandId = (int) itemView.getTag();
            Bundle args = new Bundle();
            args.putInt(DetailFragment.ARG_BAND_ID, selectedBandId);
            View detailFragmentContainer = rootView.findViewById(R.id.detail_frag_container);
            if (detailFragmentContainer == null) {
                // Replace list with details
                Navigation.findNavController(itemView).navigate(R.id.show_item_detail, args);
            } else {
                // Show details on the right
                Navigation.findNavController(detailFragmentContainer)
                        .navigate(R.id.fragment_detail, args);
            }

            // Replace list with details

        };

        // Send bands to RecyclerView
        RecyclerView recyclerView = rootView.findViewById(R.id.band_list);
        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(divider);
        List<Band> bands = BandRepository.getInstance(requireContext()).getBands();
        recyclerView.setAdapter(new BandAdapter(bands, onClickListener));

        return rootView;
    }

    private class BandAdapter extends RecyclerView.Adapter<BandHolder> {

        private final List<Band> mBands;
        private final View.OnClickListener mOnClickListener;

        public BandAdapter(List<Band> bands, View.OnClickListener onClickListener) {
            mBands = bands;
            mOnClickListener = onClickListener;
        }

        @NonNull
        @Override
        public BandHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new BandHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(BandHolder holder, int position) {
            Band band = mBands.get(position);
            holder.bind(band);
            holder.itemView.setTag(band.getId());
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mBands.size();
        }
    }

    private class BandHolder extends RecyclerView.ViewHolder {

        private final TextView mNameTextView;
        public ImageView mImage;
        public Context context = getActivity();


        public BandHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_band, parent, false));
            mNameTextView = itemView.findViewById(R.id.band_name);
            mImage = itemView.findViewById(R.id.image);

        }

        public void bind(Band band) {
            mNameTextView.setText(band.getName());
            Glide.with(context).load(band.getUrl()).into(mImage);
        }
    }
}


