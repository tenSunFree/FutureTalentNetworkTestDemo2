package com.home.futuretalentnetworktestdemo2.common.extension

import android.view.View
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import com.google.android.material.shape.TriangleEdgeTreatment
import com.home.futuretalentnetworktestdemo2.R

/**
 * View click event
 * Avoid continuous clicks
 */
inline fun View.click(crossinline function: (view: View) -> Unit) {
    this.setOnClickListener {
        val tag = this.getTag(R.id.id_tag_click)
        val millisecond = 1000
        if (tag == null || System.currentTimeMillis() - tag.toString().toLong() > millisecond) {
            this.setTag(R.id.id_tag_click, System.currentTimeMillis())
            function.invoke(it)
        }
    }
}

/**
 * Custom Shape Background
 */
fun View.setShapeBackground() {
    val cornerSize = resources.getDimension(R.dimen.corner_size)
    background = MaterialShapeDrawable(
        ShapeAppearanceModel.builder()
            .setAllCornerSizes(cornerSize)
            .setTopEdge(TriangleEdgeTreatment(cornerSize, true))
            .setBottomEdge(TriangleEdgeTreatment(cornerSize, false))
            .build()
    )
}