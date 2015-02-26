package org.arcadefire.adapters;

/**
 * Created by Angelo Marchesin on 25/02/15.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class ParallaxHeaderRecyclerAdapter<T> extends RecyclerView.Adapter {
	private static final int HEADER_VIEW = 0;
	private static final int NON_HEADER_VIEW = 1;
	private static final float DEFAULT_FRICTION = .4f;
	private final List<T> items = new ArrayList<>();
	private HeaderViewHolder mHeaderHolder;
	private int mHeaderResourceId;
	private float mVerticalAmount, mFriction = DEFAULT_FRICTION;

	protected abstract RecyclerView.ViewHolder onCreateNonHeaderViewHolder(ViewGroup viewGroup, int viewType);

	protected abstract void onBindNonHeaderViewHolder(RecyclerView.ViewHolder viewHolder, int position, T item);

	public ParallaxHeaderRecyclerAdapter(RecyclerView recyclerView, int headerResourceId) {
		mHeaderResourceId = headerResourceId;

		recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				super.onScrolled(recyclerView, dx, dy);
				mVerticalAmount += dy * mFriction;
				mHeaderHolder.rootView.setTranslationY(mVerticalAmount);
			}
		});
	}

	public void appendAll(List<T> items) {
		items.addAll(items);
	}

	public void append(T item) {
		items.add(item);
	}

	@Override
	public final void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
		switch (position) {
			case HEADER_VIEW: {
				break;
			}

			default: {
				onBindNonHeaderViewHolder(viewHolder, position, items.get(position - 1));
			}
		}
	}

	@Override
	public int getItemCount() {
		return items.size() + 1;
	}

	@Override
	public int getItemViewType(int position) {
		switch (position) {
			case HEADER_VIEW:
				return HEADER_VIEW;

			default:
				return NON_HEADER_VIEW;
		}
	}

	@Override
	public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
		final View view = LayoutInflater.from(viewGroup.getContext()).inflate(mHeaderResourceId, viewGroup, false);

		switch (viewType) {
			case HEADER_VIEW:
				mHeaderHolder = new HeaderViewHolder(view);
				return mHeaderHolder;

			default: {
				return onCreateNonHeaderViewHolder(viewGroup, viewType);
			}
		}
	}

	public float getFriction() {
		return mFriction;
	}

	public void setFriction(float friction) {
		mFriction = friction;
	}

	public void setHeaderViewResource(int headerResourceId) {
		mHeaderResourceId = headerResourceId;
	}

	public HeaderViewHolder getHeaderViewHolder() {
		return mHeaderHolder;
	}

	public static class HeaderViewHolder extends RecyclerView.ViewHolder {
		public View rootView;

		public HeaderViewHolder(View view) {
			super(view);
			rootView = view;
		}
	}
}
