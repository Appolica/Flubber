package com.appolica.sample.ui.main;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.appolica.flubber.AnimationBody;
import com.appolica.flubber.Flubber;
import com.appolica.sample.R;
import com.appolica.sample.databinding.ListItemAnimationsBinding;
import com.appolica.sample.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class MainRVAdapter extends RecyclerView.Adapter<MainRVAdapter.ViewHolder> {

    private List<AnimationBody> animations = new ArrayList<>();

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ListItemAnimationsBinding binding;

        public ViewHolder(ListItemAnimationsBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
            binding.setModel(new AnimationsListItemModel());
        }

        public void bindTo(AnimationBody animationBody) {
            final AnimationsListItemModel model = binding.getModel();
            final Flubber.AnimationProvider animation = animationBody.getAnimation();

            final String name = ((Flubber.AnimationPreset) animation).name();
            final String normalizedName = StringUtils.upperUnderScoreToCamel(name);

            model.getAnimation().set(normalizedName);
            binding.executePendingBindings();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final ListItemAnimationsBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.list_item_animations, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindTo(animations.get(position));
    }

    @Override
    public int getItemCount() {
        return animations.size();
    }

    public void add(AnimationBody animationBody) {
        animations.add(animationBody);
        notifyItemInserted(animations.size() - 1);
    }

    public void remove(AnimationBody animationBody) {
        final int index = animations.indexOf(animationBody);
        animations.remove(index);
        notifyItemRemoved(index);
    }

    public List<AnimationBody> getAnimations() {
        return animations;
    }

    public void setAnimations(List<AnimationBody> animations) {
        this.animations = animations;
        notifyDataSetChanged();
    }
}
