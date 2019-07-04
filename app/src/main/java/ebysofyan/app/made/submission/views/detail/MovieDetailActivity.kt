package ebysofyan.app.made.submission.views.detail

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import ebysofyan.app.made.submission.R
import ebysofyan.app.made.submission.common.extensions.loadWithGlidePlaceholder
import ebysofyan.app.made.submission.common.extensions.toDateFormat
import ebysofyan.app.made.submission.common.extensions.toast
import ebysofyan.app.made.submission.common.utils.Constants
import ebysofyan.app.made.submission.data.Movie
import ebysofyan.app.made.submission.data.TvShow
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.toolbar.*


/**
 * Created by @ebysofyan on 7/2/19
 */
class MovieDetailActivity : AppCompatActivity() {
    private lateinit var parcelable: Parcelable
    override fun onCreate(savedInstanceState: Bundle?) {
        setRequestWindowFeature()
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movie_detail)
        setData()

        initActionBar()
    }

    private fun initActionBar() {
        setSupportActionBar(_toolbar)
        supportActionBar?.apply {
            title = ""
            setDisplayHomeAsUpEnabled(true)
        }
        _toolbar.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent))
        _toolbar.setNavigationOnClickListener {
            supportFinishAfterTransition()
        }
    }

    private fun setRequestWindowFeature() {
        if (Build.VERSION.SDK_INT in 19..20) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }

    private fun setData() {
        parcelable = intent.getParcelableExtra(Constants.PARCELABLE_OBJ)

        when (parcelable) {
            is Movie -> {
                setDetailAsMovie(parcelable as Movie)
            }
            is TvShow -> {
                setDetailAsTvShow(parcelable as TvShow)
            }
        }
    }

    private fun setDetailAsMovie(movie: Movie) {
        movie_detail_header.loadWithGlidePlaceholder(Constants.getImageUrl("w780", movie.backdropPath))
        movie_detail_poster.loadWithGlidePlaceholder(Constants.getImageUrl(fileName = movie.posterPath))

        movie_detail_title.text = movie.title
        movie_detail_rating.text = movie.voteAverage.toString()
        movie_detail_release_date.text = movie.releaseDate.toDateFormat()
        movie_detail_desc.text = movie.overview

        movie_detail_trailer.setOnClickListener {
            toast(getString(R.string.no_trailer_found))
        }
    }

    private fun setDetailAsTvShow(tvShow: TvShow) {
        movie_detail_header.loadWithGlidePlaceholder(Constants.getImageUrl("w780", tvShow.backdropPath))
        movie_detail_poster.loadWithGlidePlaceholder(Constants.getImageUrl(fileName = tvShow.posterPath))

        movie_detail_title.text = tvShow.name
        movie_detail_rating.text = tvShow.voteAverage.toString()
        movie_detail_release_date.text = tvShow.firstAirDate.toDateFormat()
        movie_detail_desc.text = tvShow.overview

        movie_detail_trailer.setOnClickListener {
            //            val webIntent = Intent(
//                Intent.ACTION_VIEW,
//                Uri.parse(movie.trailer)
//            ).apply { putExtra("force_fullscreen", true) }
//            try {
//                startActivity(webIntent)
//            } catch (ex: ActivityNotFoundException) {
//                toast("Youtube player not found!")
//            }
            toast(getString(R.string.no_trailer_found))
        }
    }
}