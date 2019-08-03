package com.example.homework_2

import android.view.View
import android.widget.LinearLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior

fun <V : View?> BottomSheetBehavior<V>.expand() {
    setState(BottomSheetBehavior.STATE_EXPANDED)
}

fun <V : View?> BottomSheetBehavior<V>.hide() {
    setState(BottomSheetBehavior.STATE_HIDDEN)
}