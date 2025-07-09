package com.example.ticketbookingapp.Activities.TicketDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.example.ticketbookingapp.Domain.FlightModel
import com.example.ticketbookingapp.R

@Composable
fun TicketDetailContent (
    flight: FlightModel,
    modifier: Modifier
) {
    Column (
        modifier = modifier
            .padding(24.dp)
            .background(
                color = colorResource(R.color.lightPurple),
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        ConstraintLayout (
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            val(logo, arrivalTxt, lineImg, fromTxt, fromShortTxt, toTxt, toShortTxt) = createRefs()

            AsyncImage(
                model = flight.airlineLogo,
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp, 50.dp)
                    .constrainAs(logo) {
                        top.linkTo(parent.top, margin = 8.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                contentScale = ContentScale.Fit
            )
            Text(
                text = flight.arriveTime,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.darkPurple2),
                modifier = Modifier
                    .constrainAs(arrivalTxt) {
                        top.linkTo(logo.bottom)
                        start.linkTo(logo.start)
                        end.linkTo(logo.end)
                    }
            )
            Image(
                painter = painterResource(R.drawable.line_airple_blue),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(lineImg) {
                        top.linkTo(arrivalTxt.bottom, margin = 8.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            Text(
                text = "From",
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier
                    .constrainAs(fromTxt) {
                        top.linkTo(arrivalTxt.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(lineImg.start)
                    }
            )
            Text(
                text = flight.fromShort,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .constrainAs(fromShortTxt) {
                        top.linkTo(fromTxt.bottom, margin = 8.dp)
                        start.linkTo(fromTxt.start)
                        end.linkTo(fromTxt.end)
                        bottom.linkTo(lineImg.bottom)
                    }
            )
            Text(
                text = "To",
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier
                    .constrainAs(toTxt) {
                        top.linkTo(arrivalTxt.bottom)
                        start.linkTo(lineImg.end)
                        end.linkTo(parent.end)
                    }
            )
            Text(
                text = flight.toShort,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .constrainAs(toShortTxt) {
                        top.linkTo(toTxt.bottom, margin = 8.dp)
                        start.linkTo(toTxt.start)
                        end.linkTo(toTxt.end)
                        bottom.linkTo(lineImg.bottom)
                    }
            )
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column (
                modifier = Modifier
                    .weight(0.5f)
            ) {
                Text(text = "From", color = Color.Black)
                Text(text = flight.from, color = Color.Black, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Date", color = Color.Black)
                Text(text = flight.date, color = Color.Black, fontWeight = FontWeight.Bold)
            }
            Column (
                modifier = Modifier
                    .weight(0.5f)
            ) {
                Text(text = "To", color = Color.Black)
                Text(text = flight.to, color = Color.Black, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Time", color = Color.Black)
                Text(text = flight.time, color = Color.Black, fontWeight = FontWeight.Bold)
            }
        }

        Image(
            painter = painterResource(R.drawable.dash_line),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column (
                modifier = Modifier
                    .weight(0.5f)
            ) {
                Text(text = "Class", color = Color.Black)
                Text(text = flight.classSeat, color = Color.Black, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Seats", color = Color.Black)
                Text(text = flight.passenger, color = Color.Black, fontWeight = FontWeight.Bold)
            }
            Column (
                modifier = Modifier
                    .weight(0.5f)
            ) {
                Text(text = "Airlines", color = Color.Black)
                Text(text = flight.airlineName, color = Color.Black, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Price", color = Color.Black)
                Text(text = "\$${String.format("%.2f", flight.price)}", color = Color.Black, fontWeight = FontWeight.Bold)
            }
            Image(
                painter = painterResource(R.drawable.qrcode),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
            )
        }
        Image(
            painter = painterResource(R.drawable.dash_line),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Image(
            painter = painterResource(R.drawable.barcode),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            contentScale = ContentScale.FillWidth
        )
    }
}