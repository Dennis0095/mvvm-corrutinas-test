package com.example.retotecnico.core

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions

class ImageUtil {

    companion object{
        fun load(context: Context, view: ImageView, url: String, resPlaceHolder:Int, resError: Int, options: RequestOptions) {
            Glide.with(context)
                .load(url)
                .apply(
                    options
                        .placeholder(resPlaceHolder)
                        .error(resError)
                        .priority(Priority.HIGH)
                )
                .into(view)
        }
    }
}