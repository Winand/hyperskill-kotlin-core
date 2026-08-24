package org.example

import kotlinx.datetime.DateTimePeriod
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.LocalDate
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toInstant
import kotlinx.datetime.minus
import kotlinx.datetime.periodUntil
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayAt
import kotlinx.datetime.until
import java.time.temporal.ChronoUnit
import kotlin.time.Clock
import kotlin.time.Duration
import kotlin.time.Instant


fun printDate(date: String) {
    val dt = LocalDate.parse(date)
    println("${dt.dayOfWeek}, ${dt.month} ${dt.dayOfMonth}, ${dt.year}")
}

fun main() {
    val currentInstant = Clock.System.now()
    val currentInstantInMillisec = currentInstant.toEpochMilliseconds()
    val offsetInstant = currentInstant + Duration.parse("23h")
    //val specificInstant = Instant.fromEpochMilliseconds(currentInstantInMillisec)
    val loc = currentInstant.toLocalDateTime(TimeZone.currentSystemDefault())
    println(currentInstant)
    println(loc)
    println(loc.date.until((currentInstant + Duration.parse("223h")).toLocalDateTime(TimeZone.currentSystemDefault()).date, DateTimeUnit.DAY * 2))
    println(loc.toInstant(TimeZone.currentSystemDefault()))

//    val instant1 = Instant.parse("2000-01-01T00:00:00Z")
//    val period1 = DateTimePeriod(years = 1, months = 1, days = 1, hours = 1, minutes = 1, seconds = 1, nanoseconds = 123456789L)
//    val instant2 = instant1.plus(period1, TimeZone.UTC)
//    println(Duration.parse("P1DT1H1M1.999S"))
//
//    val period2: DateTimePeriod = instant1.periodUntil(instant2, TimeZone.UTC)
//    println(period2)


    val instant1 = Instant.parse("2000-01-01T20:00:00Z")
    val instant2 = Instant.parse("2012-10-14T05:20:30Z")

    val duration = LocalDate(2012, 4, 8).daysUntil(LocalDate(2000, 2, 18))
    println(duration)

    printDate("2021-12-30")
    Clock.System.todayAt(TimeZone.currentSystemDefault())
}
