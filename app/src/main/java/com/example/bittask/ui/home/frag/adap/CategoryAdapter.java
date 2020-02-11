/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.example.bittask.ui.home.frag.adap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bittask.R;
import com.example.bittask.ui.base.BaseViewHolder;
import com.example.bittask.ui.home.module.CategoryResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by amitshekhar on 10/07/17.
 */

public class CategoryAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;

    public static final int VIEW_TYPE_NORMAL = 1;

    private List<CategoryResponse.Category> mCategoryResponseList;

    private CategoryAdapterListener mListener;

    private Callback mCallback;

    private Context mContext;

    public CategoryAdapter(List<CategoryResponse.Category> CategoryResponseList, Context context) {
        this.mCategoryResponseList = CategoryResponseList;
        this.mContext = context;
    }

    public interface Callback {
        void onRepoEmptyViewRetryClick();
        void onCategoryViewClick(String itemTitle, int itemId, int chechLayout);
    }

    @Override
    public int getItemCount() {
        if (mCategoryResponseList != null && mCategoryResponseList.size() > 0) {
            return mCategoryResponseList.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mCategoryResponseList != null && !mCategoryResponseList.isEmpty()) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_view, parent, false));
            case VIEW_TYPE_EMPTY:
            default:
                return new EmptyViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty_view, parent, false));
        }
    }

    public void addItems(List<CategoryResponse.Category> CategoryList) {
        mCategoryResponseList.addAll(CategoryList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mCategoryResponseList.clear();
    }

    public void setListener(CategoryAdapterListener listener) {
        this.mListener = listener;
    }

    public interface CategoryAdapterListener {
        void onItemClick(CategoryResponse.Category Category);
        void onRetryCategoryClick();
    }

    public class ViewHolder extends BaseViewHolder implements View.OnClickListener {

        @BindView(R.id.cvCategory) CardView cvCategory;

        @BindView(R.id.clBase) ConstraintLayout clBase;

        @BindView(R.id.ivCategory) ImageView ivCategory;

        @BindView(R.id.tvCategory) TextView tvCategory;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        @Override
        protected void clear() {

        }

        @Override
        public void onBind(int position) {
            final CategoryResponse.Category category = mCategoryResponseList.get(position);

            tvCategory.setText(category.getCategoryName());

            Glide.with(ivCategory.getContext())
                    .load(category.getCategoryImage())
                    .centerCrop().into(ivCategory);

            cvCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemClick(category);
                }
            });

        }

        @OnClick({ R.id.cvCategory })
        @Override
        public void onClick(View view) {

            switch (view.getId()){
                case R.id.cvCategory:
                    break;
            }

        }
    }

    public class EmptyViewHolder extends BaseViewHolder {

        @BindView(R.id.btn_retry)
        Button retryButton;

        @BindView(R.id.tv_message)
        TextView messageTextView;

        public EmptyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {

        }

        @OnClick(R.id.btn_retry)
        void onRetryClick() {
            if (mCallback != null)
                mCallback.onRepoEmptyViewRetryClick();
        }
    }
}