package com.example.wheeliee.util

import android.content.Intent
import android.net.Uri

class Util {
    companion object {
        val emailIntent = Intent(
            Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto",
                "karunasagar@student.tce.edu",
                null
            )
        )
    }
}