package com.walid44443.newsapp.presentation.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private val inputFormats = listOf(
        "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", // With microseconds
        "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",    // With milliseconds
        "yyyy-MM-dd'T'HH:mm:ss'Z'",        // Without milliseconds
        "yyyy-MM-dd'T'HH:mm:ss.SSSSSSX",   // With timezone
        "yyyy-MM-dd'T'HH:mm:ss.SSSX",      // With timezone and milliseconds
        "yyyy-MM-dd'T'HH:mm:ssX"           // With timezone only
    )

    private val outputFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
    private val timeAgoFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

    /**
     * Formats a date string to a readable format (e.g., "Jan 15, 2024")
     */
    fun formatDate(dateString: String): String {
        return try {
            val date = parseDate(dateString)
            outputFormat.format(date)
        } catch (e: Exception) {
            // Fallback: try to extract just the date part
            try {
                val datePart = dateString.substringBefore('T')
                val fallbackFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val date = fallbackFormat.parse(datePart)
                outputFormat.format(date ?: Date())
            } catch (ex: Exception) {
                dateString.substringBefore('T') // Return just the date part as string
            }
        }
    }

    /**
     * Formats a date string to show time ago (e.g., "2 hours ago", "3 days ago")
     */
    fun formatTimeAgo(dateString: String): String {
        return try {
            val date = parseDate(dateString)
            val now = Date()
            val diffInMillis = now.time - date.time

            when {
                diffInMillis < 60 * 1000 -> "Just now"
                diffInMillis < 60 * 60 * 1000 -> "${diffInMillis / (60 * 1000)} minutes ago"
                diffInMillis < 24 * 60 * 60 * 1000 -> "${diffInMillis / (60 * 60 * 1000)} hours ago"
                diffInMillis < 7 * 24 * 60 * 60 * 1000 -> "${diffInMillis / (24 * 60 * 60 * 1000)} days ago"
                else -> formatDate(dateString)
            }
        } catch (e: Exception) {
            formatDate(dateString)
        }
    }

    /**
     * Formats a date string to show both date and time
     */
    fun formatDateTime(dateString: String): String {
        return try {
            val date = parseDate(dateString)
            val dateTimeFormat = SimpleDateFormat("MMM dd, yyyy • HH:mm", Locale.getDefault())
            dateTimeFormat.format(date)
        } catch (e: Exception) {
            formatDate(dateString)
        }
    }

    private fun parseDate(dateString: String): Date {
        for (format in inputFormats) {
            try {
                val sdf = SimpleDateFormat(format, Locale.getDefault())
                sdf.timeZone = TimeZone.getTimeZone("UTC")
                return sdf.parse(dateString) ?: throw Exception("Parse returned null")
            } catch (e: Exception) {
                continue
            }
        }
        throw Exception("Unable to parse date: $dateString")
    }
}