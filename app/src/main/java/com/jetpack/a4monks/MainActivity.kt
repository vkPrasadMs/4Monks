package com.jetpack.a4monks

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpack.a4monks.model.CartList
import com.jetpack.a4monks.ui.theme._4MonksTheme
import com.jetpack.a4monks.ui.theme.work_sans
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            _4MonksTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .fillMaxHeight(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    CartItem()

                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalTextApi::class, ExperimentalComposeUiApi::class)
@Composable
fun CartItem() {


    val showKeyboard = remember { mutableStateOf(true) }
    val focusRequester = remember { FocusRequester() }
    val keyboard = LocalSoftwareKeyboardController.current

    val focusManager = LocalFocusManager.current

    LaunchedEffect(focusRequester) {
        if (showKeyboard.equals(true)) {
            focusRequester.requestFocus()
            delay(100)
            keyboard?.show()
        }
    }

    val list: ArrayList<CartList> = arrayListOf(CartList(1, 2500), CartList(2, 2500))
    val couponApply = remember {
        mutableStateOf(false)
    }

    val applyCodeClick = remember {
        mutableStateOf(false)
    }


    var discountedPrice = 0

    val taxValue = 200

    var totalCartValue = 0

    val gradientBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xff81FBB9),
            Color(0xffBEFD4F)
        ),
        startY = 0f,
        endY = 500f
    )
    repeat(list.size) {
        totalCartValue += list[it].itemPrice
    }


    Surface(
        color = Color(0xff1F2326),
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Column {

//          Your Shopping Cart
            Text(modifier = Modifier.padding(16.dp).fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "Your Shopping Cart",
                style = TextStyle(
                    color = Color(0xffFFFFFF),
                    fontFamily = work_sans,
                    fontWeight = FontWeight.Medium,
                    fontSize = 19.sp
                )
            )

            Surface(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                color = Color(0xffffffff).copy(alpha = 0.1f)
            ) {

//         #1F2326
                Column {

                    LazyColumn {
                        items(list.size) {

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                shape = RoundedCornerShape(8.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color(0xff1F2326)
                                )
                            ) {

                                Row(modifier = Modifier.padding(16.dp)) {
                                    Image(
                                        modifier = Modifier
                                            .width(98.dp)
                                            .height(81.dp),
                                        painter = painterResource(id = R.drawable.cart_item_img),
                                        contentDescription = "cart item img"
                                    )

                                    Spacer(modifier = Modifier.width(16.dp))

                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = "Burberry T-shirt",
                                            style = TextStyle(
                                                color = Color(0xffFFFEFE),
                                                fontFamily = work_sans,
                                                fontSize = 12.sp
                                            )
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = "₹ ${list[it].itemPrice} ",
                                            style = TextStyle(
                                                color = Color(0xffD0FFF0),
                                                fontFamily = work_sans,
                                                fontSize = 14.sp
                                            )
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))

                                        Text(

                                            buildAnnotatedString {
                                                withStyle(
                                                    style = SpanStyle(
                                                        color = Color(
                                                            0xffAFAFAF
                                                        ),
                                                        fontSize = 10.sp, fontFamily = work_sans
                                                    )
                                                ) {
                                                    append("Size:")
                                                }
                                                append(" ")
                                                withStyle(
                                                    style = SpanStyle(
                                                        color = Color(
                                                            0xffFFFEFE
                                                        ),
                                                        fontSize = 10.sp, fontFamily = work_sans
                                                    )
                                                ) {
                                                    append("Medium")
                                                }
                                            }

                                        )

                                        Text(

                                            buildAnnotatedString {
                                                withStyle(
                                                    style = SpanStyle(
                                                        color = Color(
                                                            0xffAFAFAF
                                                        ),
                                                        fontSize = 10.sp, fontFamily = work_sans
                                                    )
                                                ) {
                                                    append("Qty:")
                                                }

                                                append(" ")
                                                withStyle(
                                                    style = SpanStyle(
                                                        color = Color(
                                                            0xffFFFEFE
                                                        ),
                                                        fontSize = 10.sp, fontFamily = work_sans
                                                    )
                                                ) {
                                                    append("2")
                                                }
                                            }

                                        )

                                    }


                                    Surface(
                                        modifier = Modifier.size(23.dp),
                                        shape = CircleShape,
                                        color = Color(0xffffffff).copy(alpha = 0.1f)
                                    ) {

                                        Image(
                                            modifier = Modifier.padding(4.dp),
                                            painter = painterResource(id = R.drawable.delete_icon),
                                            contentDescription = "delete icon"
                                        )
                                    }

                                }

                            }

                        }
                    }

                    Row(
                        modifier = Modifier.padding(
                            top = 0.dp,
                            start = 12.dp,
                            end = 12.dp,
                            bottom = 12.dp
                        )
                    ) {

                        Row(
                            modifier = Modifier.weight(1f),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Blueberry store",
                                style = TextStyle(
                                    color = Color(0xffFFFEFE),
                                    fontFamily = work_sans,
                                    fontSize = 10.sp
                                )
                            )
                            Spacer(modifier = Modifier.width(7.dp))
                            Image(
                                alignment = Alignment.Center,
                                modifier = Modifier.size(10.dp),
                                painter = painterResource(id = R.drawable.badge_check),
                                contentDescription = "delete icon"
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "buyok verifed",
                                style = TextStyle(
                                    color = Color(0xffAFAFAF),
                                    fontFamily = work_sans,
                                    fontSize = 10.sp
                                )
                            )
                        }

                        Text(
                            text = "ETA 5-7 working days",
                            style = TextStyle(
                                color = Color(0xffFFFEFE),
                                fontFamily = work_sans,
                                fontSize = 10.sp
                            )
                        )


                    }


                    Divider(thickness = 1.dp, color = Color(0xffffffff).copy(alpha = 0.1f))


                    // apply coupon button

                    if (!applyCodeClick.value) {
                        Row(
                            modifier = Modifier
                                .padding(18.dp)
                                .fillMaxWidth()

                                .clickable {
                                    keyboard?.show()
                                    applyCodeClick.value = true
                                },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                alignment = Alignment.Center,
                                modifier = Modifier.size(10.dp),
                                painter = painterResource(id = R.drawable.tag),
                                contentDescription = "delete icon"
                            )
                            Spacer(modifier = Modifier.width(7.dp))
                            Text(
                                text = "Apply promo code",
                                style = TextStyle(
                                    color = Color(0xffFFFEFE),
                                    fontFamily = work_sans,
                                    fontSize = 10.sp
                                )
                            )
                        }
                    } else {

                        //edittext coupon code
                        var value by remember { mutableStateOf("") }


                        val context = LocalContext.current
                        val onSubmitOtp = remember(Unit) {
                            {
                                focusManager.clearFocus()
                                keyboard?.hide()

                                if (value.isNotEmpty()) {

                                    couponApply.value = true
                                    value = "$value (50% discount)"
                                    keyboard?.hide()
                                    focusManager.clearFocus()

                                } else {
                                    Toast
                                        .makeText(
                                            context,
                                            "Please Enter promo code",
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                }

                            }
                        }

                        Box(modifier = Modifier.fillMaxWidth()) {

                            OutlinedTextField(modifier = Modifier
                                .fillMaxWidth()
                                .focusable(
                                    !couponApply.value
                                )
                                .focusRequester(focusRequester)
                                .align(Alignment.CenterStart)
                                .padding(16.dp),
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Done
                                ),
                                enabled = !couponApply.value,
                                value = value,
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    placeholderColor = Color(0xff81FBB9),
                                    textColor = Color(0xff81FBB9),
                                    focusedBorderColor = Color(0xff81FBB9),
                                    cursorColor = Color(0xff81FBB9)
                                ), textStyle = TextStyle(brush = gradientBrush),
                                onValueChange = { value = it },
                                keyboardActions = KeyboardActions {
                                    onSubmitOtp()
                                },
                                label = {
                                    Text(
                                        text = "Promo code",
                                        style = TextStyle(
                                            brush = gradientBrush,
                                            fontFamily = work_sans,
                                            fontSize = 12.sp
                                        )
                                    )
                                }
                            )


                            if (!couponApply.value) {
                                Text(
                                    modifier = Modifier
                                        .align(Alignment.CenterEnd)
                                        .padding(end = 28.dp)
                                        .clickable {

                                            if (value.isNotEmpty()) {

                                                couponApply.value = true
                                                value = "$value (50% discount)"
                                                keyboard?.hide()
                                                focusManager.clearFocus()

                                            } else {
                                                Toast
                                                    .makeText(
                                                        context,
                                                        "Please Enter promo code",
                                                        Toast.LENGTH_SHORT
                                                    )
                                                    .show()
                                            }


                                        },
                                    text = "Apply",
                                    style = TextStyle(
                                        brush = gradientBrush,
                                        fontFamily = work_sans,
                                        fontSize = 12.sp
                                    )
                                )
                            } else {

                                Text(
                                    modifier = Modifier
                                        .align(Alignment.CenterEnd)
                                        .padding(end = 28.dp)
                                        .clickable {

                                            couponApply.value = false
                                            value = ""

                                        },
                                    text = "remove",
                                    style = TextStyle(
                                        brush = gradientBrush,
                                        fontFamily = work_sans,
                                        fontSize = 12.sp
                                    )
                                )

                            }

                        }

                    }


                }


            }


            Surface(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                color = Color(0xffffffff).copy(alpha = 0.1f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {


                    Column {

                        Row(modifier = Modifier.padding(bottom = 12.dp)) {

                            Text(
                                text = "Price",
                                style = TextStyle(
                                    color = Color(0xffAFAFAF),
                                    fontFamily = work_sans,
                                    fontSize = 12.sp
                                )
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = "₹ ${totalCartValue}",
                                style = TextStyle(
                                    color = Color(0xffFFFEFE),
                                    fontFamily = work_sans,
                                    fontSize = 12.sp
                                )
                            )
                        }


                        Row(modifier = Modifier.padding(bottom = 12.dp)) {

                            Text(
                                text = "Delivery Charge",
                                style = TextStyle(
                                    color = Color(0xffAFAFAF),
                                    fontFamily = work_sans,
                                    fontSize = 12.sp
                                )
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = "Free",
                                style = TextStyle(
                                    color = Color(0xffFFFEFE),
                                    fontFamily = work_sans,
                                    fontSize = 12.sp
                                )
                            )
                        }

                        Row(modifier = Modifier.padding(bottom = 12.dp)) {

                            Text(
                                text = "Coupon Discount",
                                style = TextStyle(
                                    color = Color(0xffAFAFAF),
                                    fontFamily = work_sans,
                                    fontSize = 12.sp
                                )
                            )
                            Spacer(modifier = Modifier.weight(1f))

                            discountedPrice = if (couponApply.value) {
                                (totalCartValue * 0.50).toInt()
                            } else {
                                0
                            }
                            Text(
                                text = "- ₹ $discountedPrice",
                                style = TextStyle(
                                    color = Color(0xffFFFEFE),
                                    fontFamily = work_sans,
                                    fontSize = 12.sp
                                )
                            )
                        }


                        Row(modifier = Modifier.padding(bottom = 12.dp)) {

                            Text(
                                text = "Tax",
                                style = TextStyle(
                                    color = Color(0xffAFAFAF),
                                    fontFamily = work_sans,
                                    fontSize = 12.sp
                                )
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = "₹ ${taxValue}",
                                style = TextStyle(
                                    color = Color(0xffFFFEFE),
                                    fontFamily = work_sans,
                                    fontSize = 12.sp
                                )
                            )
                        }

                        Divider(thickness = 1.dp, color = Color(0xFFFFFFFF).copy(alpha = 0.1f))


                        Row(modifier = Modifier.padding(vertical = 12.dp)) {

                            Text(
                                text = "Total",
                                style = TextStyle(
                                    color = Color(0xffFFFEFE),
                                    fontFamily = work_sans,
                                    fontSize = 14.sp
                                )
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = "₹ ${totalCartValue + taxValue - discountedPrice}",
                                style = TextStyle(
                                    color = Color(0xffD0FFF0),
                                    fontFamily = work_sans,
                                    fontSize = 14.sp
                                )
                            )
                        }


                    }


                }
            }


            Surface(modifier = Modifier.padding(16.dp), shape = RoundedCornerShape(4.dp)) {


                Box(
                    modifier = Modifier
                        .fillMaxWidth()

                        .background(brush = gradientBrush)
                ) {

                    Text(
                        modifier = Modifier
                            .padding(12.dp)
                            .background(color = Color(0xFFFFFFFF).copy(alpha = 0f))
                            .align(
                                Alignment.Center
                            ),
                        textAlign = TextAlign.Center,
                        text = "Proceed to checkout",
                        style = TextStyle(
                            color = Color(0xff0D0D0D),
                            fontFamily = work_sans,
                            fontSize = 16.sp
                        )

                    )


                }


            }
        }

    }


}
