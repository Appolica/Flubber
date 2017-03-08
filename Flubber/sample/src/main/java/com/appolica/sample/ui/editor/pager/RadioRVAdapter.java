package com.appolica.sample.ui.editor.pager;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.appolica.sample.R;
import com.appolica.sample.databinding.ListItemRadioBinding;

import java.util.ArrayList;
import java.util.List;

public class RadioRVAdapter extends RecyclerView.Adapter<RadioRVAdapter.BindingHolder> {

    private ObservableField<RadioElementModel> checked = new ObservableField<>();
    private List<RadioElementModel> data = new ArrayList<>();

    private Context context;

    private OnElementSelectedListener selectedListener;

    public RadioRVAdapter(Context context) {
        this.context = context;
        checked.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                if (selectedListener != null) {
                    final ObservableField observableField = (ObservableField) observable;
                    selectedListener.onElementSelected((RadioElementModel) observableField.get());
                }
            }
        });
    }

    public void setSelectedListener(OnElementSelectedListener selectedListener) {
        this.selectedListener = selectedListener;
    }

    public void setSelected(int index) {
        final RadioElementModel checkedModel = checked.get();
        final int oldCheckedIndex = data.indexOf(checkedModel);

        if (oldCheckedIndex == index) {
            checked.notifyPropertyChanged(BR.checked);
        } else {
            if (oldCheckedIndex >= 0) {
                checkedModel.getChecked().set(false);
                notifyItemChanged(oldCheckedIndex);
            }

            checked.set(data.get(index));
            notifyItemChanged(index);
        }
    }

    public class BindingHolder extends RecyclerView.ViewHolder {

        private ListItemRadioBinding binding;

        public BindingHolder(ListItemRadioBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindTo(RadioElementModel model) {
            binding.setModel(model);
            binding.setChecked(checked);
            binding.executePendingBindings();
        }
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(context);

        final ListItemRadioBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.list_item_radio, parent, false);

        return new BindingHolder(binding);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        holder.bindTo(getData().get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public List<RadioElementModel> getData() {
        return data;
    }

    public void setData(List<RadioElementModel> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public interface OnElementSelectedListener {
        void onElementSelected(RadioElementModel model);
    }
}
