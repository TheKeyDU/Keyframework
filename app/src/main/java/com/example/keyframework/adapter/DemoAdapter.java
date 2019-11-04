package com.example.keyframework.adapter;
import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;

/**
 * @author Administrator
 */
public class DemoAdapter extends BaseQuickAdapter {


    public DemoAdapter(int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {

    }
 
}
