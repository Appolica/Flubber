package com.appolica.sample.ui.main.fragment;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appolica.sample.R;
import com.appolica.sample.databinding.ListItemAnimationsBinding;
import com.appolica.sample.ui.animation.CustomAnimationBody;
import com.appolica.sample.ui.main.AnimationsListItemModel;

import java.util.ArrayList;
import java.util.List;

public class MainRVAdapter
        extends RecyclerView.Adapter<MainRVAdapter.BaseViewHolder<?>> {

    private static final int HEADER = 23;
    private static final int LIST_ITEM = 836;
    private List<AnimationsListItemModel> data = new ArrayList<>();
    private OnItemClickListener clickListener;

    public MainRVAdapter() {
        setHasStableIds(true);
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
            holder.bindTo(data.get(position - 1));
            ((ItemViewHolder) holder).getBinding().setClickListener(clickListener);
        }
    }

    @Override
    public int getItemCount() {
        return data.size() + 1;
    }

    public void add(CustomAnimationBody animationBody) {
        data.add(new AnimationsListItemModel(animationBody));
        notifyDataSetChanged();
    }

    public void add(int position, AnimationsListItemModel toRemove) {
        data.add(position, toRemove);
        notifyDataSetChanged();
    }

    public void remove(AnimationsListItemModel model) {
        final int index = data.indexOf(model);
        data.remove(index);
        notifyDataSetChanged();
    }

    public List<AnimationsListItemModel> getData() {
        return data;
    }

    public void setData(List<AnimationsListItemModel> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        final int viewType = getItemViewType(position);
        if (viewType == HEADER) {
            return 0;
        } else {
            return data.get(position - 1).hashCode();
        }
    }

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface OnItemClickListener {
        void onItemClicked(AnimationsListItemModel model);
    }

    public class BaseViewHolder<DataType> extends RecyclerView.ViewHolder {

        public BaseViewHolder(View itemView) {
            super(itemView);
        }

        public void bindTo(DataType item) {

        }

    }

    public class ItemViewHolder extends BaseViewHolder<AnimationsListItemModel> {

        private ListItemAnimationsBinding binding;

        public ItemViewHolder(ListItemAnimationsBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        @Override
        public void bindTo(AnimationsListItemModel model) {
            binding.setModel(model);
            binding.executePendingBindings();
        }

        public ListItemAnimationsBinding getBinding() {
            return binding;
        }

    }
}
