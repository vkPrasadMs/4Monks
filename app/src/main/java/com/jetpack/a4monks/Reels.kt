package com.jetpack.a4monks

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpack.a4monks.databinding.VideoBinding
import com.jetpack.a4monks.ui.theme._4MonksTheme
import com.jetpack.a4monks.ui.theme.work_sans
import kotlinx.coroutines.launch


class Reels : AppCompatActivity() {

    private lateinit var binding: VideoBinding


    private var array = ArrayList<Int>()

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = VideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        array.add(R.raw.sunset)
        array.add(R.raw.lake)
        array.add(R.raw.creek)


        // creating object of
        // media controller class
        val mediaController = MediaController(this)

        // sets the anchor view
        // anchor view for the videoView
        mediaController.setAnchorView(binding.videoView)

        // sets the media player to the videoView
        mediaController.setMediaPlayer(binding.videoView)

        // sets the media controller to the videoView
//        binding.videoView.setMediaController(mediaController);


        binding.videoView.setOnPreparedListener {

            Log.d("PRASAD", "onCreate: " + it.duration)
            Log.d("PRASAD", "onCreate: " + it.currentPosition)

        }

//        binding.videoView.


//        mediaController.setMediaPlayer(object : MediaController(this),
//            MediaController.MediaPlayerControl {
//            override fun start() {
//                Log.d("MEDIA_CONTROLLER", "start: ")
//            }
//
//            override fun pause() {
//                Log.d("MEDIA_CONTROLLER", "pause: ")
//            }
//
//            override fun getDuration(): Int {
//                Log.d("MEDIA_CONTROLLER", "getDuration: ")
//                return 0;
//            }
//
//            override fun getCurrentPosition(): Int {
//                Log.d("MEDIA_CONTROLLER", "getCurrentPosition: ")
//                return 0;
//            }
//
//            override fun seekTo(pos: Int) {
//                Log.d("MEDIA_CONTROLLER", "seekTo: $pos")
//            }
//
//            override fun isPlaying(): Boolean {
//                Log.d("MEDIA_CONTROLLER", "isPlaying: ")
//                return true
//            }
//
//            override fun getBufferPercentage(): Int {
//                Log.d("MEDIA_CONTROLLER", "getBufferPercentage: ")
//                return 0
//            }
//
//            override fun canPause(): Boolean {
//                Log.d("MEDIA_CONTROLLER", "canPause: ")
//                return true
//            }
//
//            override fun canSeekBackward(): Boolean {
//                Log.d("MEDIA_CONTROLLER", "canSeekBackward: ")
//                return true
//            }
//
//            override fun canSeekForward(): Boolean {
//                Log.d("MEDIA_CONTROLLER", "canSeekForward: ")
//                return true
//            }
//
//            override fun getAudioSessionId(): Int {
//                Log.d("MEDIA_CONTROLLER", "getAudioSessionId: ")
//                return 0
//            }
//
//        })


        binding.composeView.apply {

            setContent {
                _4MonksTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = Color.Transparent
                    ) {
//                        val listState = rememberLazyListState()
                        val position = remember {
                            mutableStateOf(0)
                        }
                        val coroutineScope = rememberCoroutineScope()
                        val state = rememberLazyListState()
                        binding.videoView.setOnCompletionListener {
//                            position.value += position.value
                            coroutineScope.launch {
                                state.animateScrollToItem(index = state.firstVisibleItemIndex + 1)
                            }
                        }
//                        coroutineScope.launch {
//                            // Animate scroll to the first item
//                            state.animateScrollToItem(index = position.value)
//                        }


                        LazyColumn(
                            state = state,
                            flingBehavior = rememberSnapFlingBehavior(lazyListState = state)
                        ) {
                            var currentCount = 0
                            items(100) {

                                if (currentCount >= array.size - 1) {
                                    currentCount = 0
                                } else {
                                    currentCount += 1
                                }

                                val uri =
                                    Uri.parse("http://65.0.70.203/rfid/production_id_4434242%20%282160p%29.mp4")
                                val path =
                                    "android.resource://" + packageName + "/" + array[currentCount]
//                                view.setVideoURI(Uri.parse(path))
                                // sets the resource from the
                                // videoUrl to the videoView
                                binding.videoView.setVideoURI(Uri.parse(path))

                                binding.videoView.start()

                                GreetingPreview2()
                            }
                        }


                    }
                }
            }

        }

    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {

    val gradientBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xff262626).copy(alpha = 0.38f),
            Color(0xff262626).copy(alpha = 0.31f)
        ), // Gradient colors
        startY = 0f, // Starting Y position of the gradient
        endY = 500f // Ending Y position of the gradient
    )


    val gradientBrush2 = Brush.verticalGradient(
        colors = listOf(
            Color(0xffFFFFFF).copy(alpha = 0.1f),
            Color(0xffFFFFFF).copy(alpha = 0.1f)
        ), // Gradient colors
        startY = 0f, // Starting Y position of the gradient
        endY = 500f // Ending Y position of the gradient
    )

    val gradientBrush3 = Brush.verticalGradient(
        colors = listOf(
            Color(0xff81FBB9),
            Color(0xffBEFD4F)
        ), // Gradient colors
        startY = 0f, // Starting Y position of the gradient
        endY = 500f // Ending Y position of the gradient
    )

    val gradientBrush4 = Brush.verticalGradient(
        colors = listOf(
            Color(0xff000000).copy(alpha = 0f),
            Color(0xff000000).copy(alpha = 0.1f),
            Color(0xff000000).copy(alpha = 0.2f),
            Color(0xff000000).copy(alpha = 0.3f),
            Color(0xff000000).copy(alpha = 0.4f),
            Color(0xff000000).copy(alpha = 0.5f),
        ), // Gradient colors
        startY = 0f, // Starting Y position of the gradient
        endY = 500f // Ending Y position of the gradient
    )


    var currentProgress by remember { mutableStateOf(0f) }

    _4MonksTheme() {
        val configuration = LocalConfiguration.current

        val screenHeight = configuration.screenHeightDp.dp
        Surface(modifier = Modifier.height(screenHeight), color = Color.Transparent) {

            Box(modifier = Modifier.fillMaxHeight()) {


//                val context = LocalContext.current
//                val url =
//                    "http://65.0.70.203/rfid/production_id_4678261%20%281080p%29.mp4"
//
//                val exoPlayer = ExoPlayer.Builder(context).build()
//                val mediaItem = MediaItem.fromUri(Uri.parse(url))
//                exoPlayer.setMediaItem(mediaItem)
//
//                val playerView = StyledPlayerView(context)
//                playerView.player = exoPlayer
//
//                DisposableEffect(AndroidView(factory = {
//                    StyledPlayerView(context).apply {
//                        player = exoPlayer
//                        layoutParams =
//                            FrameLayout.LayoutParams(
//                                ViewGroup.LayoutParams.MATCH_PARENT,
//                                ViewGroup.LayoutParams.MATCH_PARENT
//                            )
//                    }
//                }, modifier = Modifier.height(screenHeight))) {
//
//                    exoPlayer.prepare()
//                    exoPlayer.playWhenReady = true
//                    exoPlayer.play()
//
//                    onDispose {
//                        exoPlayer.release()
//                    }
//                }


                Column(
                    modifier = Modifier
                        .background(color = Color.Transparent)
                        .fillMaxHeight()
                        .fillMaxWidth()
                ) {

                    Row(modifier = Modifier.weight(1f)) {
                        Column(modifier = Modifier.weight(1f)) {

                            Row(
                                modifier = Modifier.padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Surface(shape = CircleShape, modifier = Modifier.size(20.dp)) {

                                    Image(
                                        painter = painterResource(id = R.drawable.ellipse),
                                        contentDescription = "cake Cheese"
                                    )
                                }

                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    modifier = Modifier,
                                    textAlign = TextAlign.Center,
                                    text = "Cakes & Cheese",
                                    style = TextStyle(
                                        color = Color(0xffFFFEFE),
                                        fontFamily = work_sans,
                                        fontSize = 12.sp
                                    )
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Button(
                                    onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(
                                            0xff1F2326
                                        )
                                    )
                                ) {
                                    Text(
                                        modifier = Modifier,
                                        textAlign = TextAlign.Center,
                                        text = "Fallow",
                                        style = TextStyle(
                                            color = Color(0xffFFFEFE),
                                            fontFamily = work_sans,
                                            fontSize = 12.sp
                                        )
                                    )
                                }


                            }

                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier
                            ) {

                                Text(
                                    modifier = Modifier.padding(start = 16.dp, bottom = 16.dp),
                                    textAlign = TextAlign.Center,
                                    text = "Get your fresh birthday cake today",
                                    style = TextStyle(
                                        color = Color(0xffFFFFFF),
                                        fontFamily = work_sans,
                                        fontSize = 12.sp
                                    )
                                )


                            }


                        }


                        Row(modifier = Modifier.padding(top = 24.dp, end = 16.dp)) {

                            Image(
                                modifier = Modifier.size(20.dp),
                                painter = painterResource(id = R.drawable.more_circle),
                                contentDescription = "more circle"
                            )
                            Spacer(modifier = Modifier.width(24.dp))
                            Image(
                                modifier = Modifier.size(20.dp),
                                painter = painterResource(id = R.drawable.combined_shape),
                                contentDescription = "more circle"
                            )
                            Spacer(modifier = Modifier.width(24.dp))
                            Image(
                                modifier = Modifier.size(20.dp),
                                painter = painterResource(id = R.drawable.cancle),
                                contentDescription = "more circle"
                            )


                        }


                    }


                    Box(modifier = Modifier.background(gradientBrush4)) {

                        Column(modifier = Modifier.padding(16.dp)) {

                            Row {

                                Surface(
                                    color = Color.Transparent,
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier.weight(1f)
                                ) {

                                    Box(modifier = Modifier.background(gradientBrush)) {
                                        Surface(
                                            color = Color.Transparent,
                                            shape = RoundedCornerShape(8.dp),
                                            modifier = Modifier
                                        ) {


                                            Column {

                                                Row(modifier = Modifier.padding(16.dp)) {

                                                    Image(
                                                        modifier = Modifier
                                                            .width(85.dp)
                                                            .height(89.dp),
                                                        painter = painterResource(id = R.drawable.rectangle_266),
                                                        contentDescription = "profile"
                                                    )

                                                    Spacer(modifier = Modifier.width(16.dp))

                                                    Column(modifier = Modifier) {

                                                        Row(
                                                            verticalAlignment = Alignment.CenterVertically,
                                                            horizontalArrangement = Arrangement.End
                                                        ) {
                                                            Spacer(modifier = Modifier.weight(1f))
                                                            Image(
                                                                modifier = Modifier
                                                                    .size(10.dp),
                                                                painter = painterResource(id = R.drawable.star),
                                                                contentDescription = "star"
                                                            )

                                                            Text(
                                                                modifier = Modifier,
                                                                textAlign = TextAlign.Center,
                                                                text = "Watch",
                                                                style = TextStyle(
                                                                    color = Color(0xffFFFFFF),
                                                                    fontFamily = work_sans,
                                                                    fontSize = 10.sp
                                                                )
                                                            )

                                                        }

                                                        Text(
                                                            modifier = Modifier.padding(top = 11.dp),
                                                            textAlign = TextAlign.Center,
                                                            text = "HightechShop | MacBook Pro 2022",
                                                            style = TextStyle(
                                                                color = Color(0xffFFFFFF),
                                                                fontFamily = work_sans,
                                                                fontSize = 10.sp
                                                            )
                                                        )

                                                        Text(
                                                            modifier = Modifier.padding(top = 4.dp),
                                                            textAlign = TextAlign.Center,
                                                            text = "High Bid : Rs. 2500/-",
                                                            style = TextStyle(
                                                                color = Color(0xffFFD42D),
                                                                fontFamily = work_sans,
                                                                fontSize = 15.sp
                                                            )
                                                        )


                                                        Text(
                                                            modifier = Modifier.padding(top = 4.dp),
                                                            textAlign = TextAlign.Center,
                                                            text = "3 Bids",
                                                            style = TextStyle(
                                                                color = Color(0xffFFFFFF),
                                                                fontFamily = work_sans,
                                                                fontSize = 10.sp
                                                            )
                                                        )

                                                    }


                                                }


                                                Row(
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(
                                                            start = 16.dp,
                                                            end = 16.dp,
                                                            bottom = 16.dp
                                                        )
                                                ) {
                                                    Surface(
                                                        color = Color.Transparent,
                                                        shape = RoundedCornerShape(4.dp)
                                                    ) {

                                                        Box(
                                                            modifier = Modifier.background(
                                                                gradientBrush2
                                                            )
                                                        ) {
                                                            Row(
                                                                verticalAlignment = Alignment.CenterVertically,
                                                                modifier = Modifier.padding(
                                                                    horizontal = 10.dp,
                                                                    vertical = 4.dp
                                                                )
                                                            ) {
                                                                Image(
                                                                    modifier = Modifier.size(12.dp),
                                                                    painter = painterResource(id = R.drawable.path_1320),
                                                                    contentDescription = "more circle"
                                                                )
                                                                Spacer(modifier = Modifier.width(8.dp))

                                                                Text(
                                                                    text = "10 : 01: 24",
                                                                    style = TextStyle(
                                                                        color = Color(0xffBEFD4F),
                                                                        fontFamily = work_sans,
                                                                        fontSize = 10.sp
                                                                    )
                                                                )
                                                            }
                                                        }
                                                    }

                                                    Spacer(modifier = Modifier.weight(1f))

                                                    Surface(
                                                        color = Color.Transparent,
                                                        shape = RoundedCornerShape(60.dp)
                                                    ) {

                                                        Box(
                                                            modifier = Modifier.background(
                                                                gradientBrush3
                                                            )
                                                        ) {
                                                            Row(
                                                                verticalAlignment = Alignment.CenterVertically,
                                                                modifier = Modifier.padding(
                                                                    horizontal = 10.dp,
                                                                    vertical = 4.dp
                                                                )
                                                            ) {
                                                                Text(
                                                                    modifier = Modifier.padding(
                                                                        horizontal = 16.dp,
                                                                        vertical = 8.dp
                                                                    ),
                                                                    textAlign = TextAlign.Center,
                                                                    text = "Place a Bid",
                                                                    style = TextStyle(
                                                                        color = Color(0xff0D0D0D),
                                                                        fontFamily = work_sans,
                                                                        fontSize = 10.sp
                                                                    )
                                                                )
                                                            }
                                                        }
                                                    }

                                                }

                                            }


                                        }
                                    }

                                }
                                Spacer(modifier = Modifier.width(14.dp))


                                Column {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Surface(
                                            color = Color(0xFF000000).copy(alpha = 0.2f),
                                            shape = RoundedCornerShape(8.dp),
                                            modifier = Modifier.size(36.dp)
                                        ) {

                                            Image(
                                                modifier = Modifier.padding(8.dp),
                                                painter = painterResource(id = R.drawable.heart),
                                                contentDescription = "heart"
                                            )

                                        }
                                        Text(
                                            modifier = Modifier,
                                            textAlign = TextAlign.Center,
                                            text = "1.4k",
                                            style = TextStyle(
                                                color = Color(0xffFFFFFF),
                                                fontFamily = work_sans,

                                                fontSize = 10.sp
                                            )
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(16.dp))

                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                                        Surface(
                                            color = Color(0xFF000000).copy(alpha = 0.2f),
                                            shape = RoundedCornerShape(8.dp),
                                            modifier = Modifier.size(36.dp)
                                        ) {

                                            Image(
                                                modifier = Modifier.padding(8.dp),
                                                painter = painterResource(id = R.drawable.chat),
                                                contentDescription = "heart"
                                            )

                                        }
                                        Text(
                                            modifier = Modifier,
                                            textAlign = TextAlign.Center,
                                            text = "200",
                                            style = TextStyle(
                                                color = Color(0xffFFFFFF),
                                                fontFamily = work_sans,

                                                fontSize = 10.sp
                                            )
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Surface(
                                        color = Color(0xFF000000).copy(alpha = 0.2f),
                                        shape = RoundedCornerShape(8.dp),
                                        modifier = Modifier.size(36.dp)
                                    ) {

                                        Image(
                                            modifier = Modifier.padding(8.dp),
                                            painter = painterResource(id = R.drawable.logout),
                                            contentDescription = "heart"
                                        )


                                    }


                                }


                            }

                            Row(
                                modifier = Modifier.padding(vertical = 24.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                LinearProgressIndicator(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f),
                                    progress = currentProgress
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    modifier = Modifier,
                                    textAlign = TextAlign.Center,
                                    text = "10 : 05 min",
                                    style = TextStyle(
                                        color = Color(0xffFFFFFF),
                                        fontFamily = work_sans,
                                        fontSize = 10.sp
                                    )
                                )

                            }

                        }

                    }
                }
            }


        }
    }
}