package com.senra.assing.quicknews

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import android.widget.TextView
import androidx.annotation.NonNull
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter


@BindingAdapter("visibilityCondition")
fun View.visibleIf(condition: Boolean?) {
    this.visibility = if (condition == true) View.VISIBLE else View.GONE
}


@BindingAdapter("imageUrl")
fun ImageView.imageUrl(imageUrl: String?) {
    if (imageUrl.isNullOrEmpty()) return
    Glide.with(context).load(imageUrl).into(this)
}

@BindingAdapter("bindPublishDateDate")
fun bindPublishDateDate(textView: TextView, date: String) {
    val parser =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val formatter = SimpleDateFormat("dd MMM hh:mm aa")
    val d = parser.parse(date)
    d?.let {
        textView.text = formatter.format(d)
    }

}