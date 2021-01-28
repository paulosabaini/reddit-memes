package org.sabaini.redditmemes.ui

import android.content.Context
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import org.sabaini.redditmemes.R
import org.sabaini.redditmemes.model.Meme

@GlideModule
class AppGlideModule : AppGlideModule()

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Meme>?) {
    val adapter = recyclerView.adapter as MemeListAdapter
    adapter.submitList(data?.filter { !it.stickied && !it.isVideo })
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()

        GlideApp.with(imgView.context)
                .load(imgUri)
                .placeholder(getLoadingDrawable(imgView.context, 5f, 30f))
                .error(R.drawable.ic_error)
                .into(imgView)
    }
}

//@BindingAdapter("memesApiStatus")
//fun bindStatus(statusImageView: ImageView, status: MemesApiStatus?) {
//    when (status) {
//        MemesApiStatus.ERROR -> {
//            statusImageView.visibility = View.VISIBLE
//            statusImageView.setImageResource(R.drawable.ic_error)
//        }
//    }
//}
//
//@BindingAdapter("memesApiLoading")
//fun bindStatusLoading(progressBar: ProgressBar, status: MemesApiStatus?) {
//    when (status) {
//        MemesApiStatus.LOADING -> {
//            progressBar.visibility = View.VISIBLE
//        }
//        MemesApiStatus.ERROR -> {
//            progressBar.visibility = View.GONE
//        }
//        MemesApiStatus.DONE -> {
//            progressBar.visibility = View.GONE
//        }
//    }
//}
//
fun getLoadingDrawable(context: Context, width: Float, radius: Float): CircularProgressDrawable {
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = width
    circularProgressDrawable.centerRadius = radius
    circularProgressDrawable.setColorSchemeColors(android.R.attr.progressBarStyle)
    circularProgressDrawable.start()
    return circularProgressDrawable
}

