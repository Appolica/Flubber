package com.appolica.sample.ui.main.fragment;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;

import com.appolica.sample.R;

public class MainSwipeDecorator extends ItemTouchHelper {

    private static final String TAG = "SwipeDeleteItemDecorato";

    public MainSwipeDecorator(ItemDismissCallback dismissCallback) {
        super(new SwipeCallback(dismissCallback));
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    public interface ItemDismissCallback {
        void onItemDismissed(int position);
    }

    private static class SwipeCallback extends ItemTouchHelper.Callback {

        private ItemDismissCallback dismissCallback;

        private SwipeCallback(ItemDismissCallback dismissCallback) {
            this.dismissCallback = dismissCallback;
        }

        @Override
        public int getMovementFlags(RecyclerView recyclerView,
                                    RecyclerView.ViewHolder viewHolder) {

            if (viewHolder.getAdapterPosition() > 0) {
                final int dragFlags = ItemTouchHelper.LEFT;
                final int swipeFlags = ItemTouchHelper.LEFT;

                return ItemTouchHelper.Callback.makeMovementFlags(dragFlags, swipeFlags);
            }

            return ItemTouchHelper.Callback.makeMovementFlags(0, 0);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            dismissCallback.onItemDismissed(viewHolder.getAdapterPosition());
        }

        @Override
        public boolean isItemViewSwipeEnabled() {
            return true;
        }

        @Override
        public boolean isLongPressDragEnabled() {
            return true;
        }

        @Override
        public void onChildDraw(Canvas c,
                                RecyclerView recyclerView,
                                RecyclerView.ViewHolder viewHolder,
                                float dX, float dY,
                                int actionState,
                                boolean isCurrentlyActive) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

            final float left = viewHolder.itemView.getWidth() - Math.abs(viewHolder.itemView.getX());
            final float top = viewHolder.itemView.getY();
            final float right = recyclerView.getWidth();
            final float bottom = top + viewHolder.itemView.getHeight();

            final RectF rectF = new RectF(left, top, right, bottom);
            final Paint paint = new Paint();

            paint.setColor(ContextCompat.getColor(recyclerView.getContext(), R.color.colorSwipeDismiss));
            c.drawRect(rectF, paint);
        }
    }
}
