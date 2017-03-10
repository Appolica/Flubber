package com.appolica.sample.ui.main;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appolica.flubber.AnimationBody;
import com.appolica.flubber.Flubber;
import com.appolica.sample.R;
import com.appolica.sample.databinding.ListItemAnimationsBinding;
import com.appolica.sample.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class MainRVAdapter extends RecyclerView.Adapter<MainRVAdapter.BaseViewHolder<?>> {

    private static final int HEADER = 23;
    private static final int LIST_ITEM = 836;
    private List<AnimationBody> animations = new ArrayList<>();

    public class BaseViewHolder<DataType> extends RecyclerView.ViewHolder {

        public BaseViewHolder(View itemView) {
            super(itemView);
        }

        public void bindTo(DataType item) {

        }
    }

    public class ItemViewHolder extends BaseViewHolder<AnimationBody> {

        private ListItemAnimationsBinding binding;

        public ItemViewHolder(ListItemAnimationsBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
            binding.setModel(new AnimationsListItemModel());
        }

        @Override
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
    public int getItemViewType(int position) {
        return position == 0 ? HEADER : LIST_ITEM;
    }

    @Override
    public BaseViewHolder<?> onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == HEADER) {
            return new BaseViewHolder<>(inflater.inflate(R.layout.list_item_main_panel_header, parent, false));
        } else {
            final ListItemAnimationsBinding binding =
                    DataBindingUtil.inflate(inflater, R.layout.list_item_animations, parent, false);

            return new ItemViewHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (getItemViewType(position) == LIST_ITEM) {
            holder.bindTo(animations.get(position - 1));
        }
    }

    @Override
    public int getItemCount() {
        return animations.size() + 1;
    }

    public void add(AnimationBody animationBody) {
        animations.add(animationBody);
        notifyItemInserted(animations.size());
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
