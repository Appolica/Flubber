package com.appolica.sample.ui.editor.pager;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import java.io.Serializable;

public class RadioElementModel implements Serializable {

    private ObservableField<String> name = new ObservableField<>();
    private ObservableBoolean checked = new ObservableBoolean();

    public ObservableField<String> getName() {
        return name;
    }

    public void setName(ObservableField<String> name) {
        this.name = name;
    }

    public ObservableBoolean getChecked() {
        return checked;
    }

    public void setChecked(ObservableBoolean checked) {
        this.checked = checked;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof RadioElementModel) {
            final RadioElementModel query = (RadioElementModel) obj;

            return query.getName().get().equals(name.get())
                    && query.getChecked().get() == checked.get();
        }

        return super.equals(obj);
    }
}
