package com.example.imagelist.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemDecoration  : RecyclerView.ItemDecoration {

    private val top: Int
    private val right: Int
    private val bottom: Int
    private val left: Int

    constructor(space: Int) : super() {
        this.top = space
        this.right = space
        this.bottom = space
        this.left = space
    }

    constructor(top: Int, right: Int, bottom: Int, left: Int) : super() {
        this.top = top
        this.right = right
        this.bottom = bottom
        this.left = left
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.left = left
        outRect.right = right
        outRect.bottom = bottom

        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = top
        }
    }
}